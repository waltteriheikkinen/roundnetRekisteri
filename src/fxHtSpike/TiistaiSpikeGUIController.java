package fxHtSpike;



import java.io.FileNotFoundException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.*;
import fi.jyu.mit.ohj2.Mjonot;
import htSpike.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;




/**
 * @author waltt
 * @version 22.2.2023
 * Controlleri koko ojelmalle
 * TODO: 3 Lisää etsi toimintoja
 * TODO: 4 Järjestä jäsenet aakkosjärjestykseen
 * TODO: 3 otteluiden luomisessa haku ei toimi
 */
public class TiistaiSpikeGUIController implements Initializable{
    @FXML private ListChooser<Pelaaja> chooserPelaajat;
    @FXML private ListChooser<Pelaaja> chooserValittavat;
    @FXML private ListChooser<Pelaaja> chooserValitut;
    @FXML private StringGrid<Object> gridRanking;
    @FXML private StringGrid<Object> gridHistoria;
    @FXML private ComboBoxChooser<String> boxChooserHakuEhto;
    @FXML private ComboBoxChooser<String> boxChooserHakuEhto2;
    @FXML private TextField textHakuEhto;
    @FXML private TextField textHakuEhto2;
    @FXML private Tab tabRanking;
    @FXML private GridPane gridPelaaja;
    @FXML private BorderPane borderPelaaja;
    @FXML private TextField tiedotNimi;
    @FXML private TextField tiedotIka;
    @FXML private TextField tiedotSukupuoli;
    @FXML private TextField tiedotKatisyys;
    @FXML private TextField tiedotRating;
    @FXML private TextField tiedotTaso;
    @FXML private TextField tiedotJnro;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();
        
    }
    
    
    @FXML void HandleHakuEhto() {
        hae(0, this.chooserPelaajat, this.textHakuEhto);
    }
    
    @FXML void HandleHakuEhto2() {
        hae(0, this.chooserValittavat, this.textHakuEhto2);
    }

    
    @FXML
    void HandleLuoOttelu() {
        List<Pelaaja> pelaajat = chooserValitut.getObjects();
        int[] iideet = {pelaajat.get(0).getId(), pelaajat.get(1).getId(), pelaajat.get(2).getId(), pelaajat.get(3).getId()}; 
        Ottelu ottelu = new Ottelu(iideet);
        tiistaispike.lisaa(ottelu);
        // ModalController.showModal(TiistaiSpikeGUIController.class.getResource("YksiOttelu.fxml"), "Ottelu", null, null);
    }

    
    
    @SuppressWarnings("unchecked")
    @FXML void HandleEteenPain() {
        if (tarkistaValitut()) {
            List<Pelaaja> parit = ParitSkabatController.luoPariLista(null, chooserValitut.getObjects());
            if (parit == null) return;
            Object[] ottelutjanimet = luoOttelut(parit);
            Object[] pelatutottelut = OttelutTuloksetController.syotaTulokset(null, ottelutjanimet);
            if (pelatutottelut == null) return;
            List<Ottelu> tulokset = (List<Ottelu>) pelatutottelut[0];
            for (Ottelu ottelu : tulokset) {
                tiistaispike.lisaa(ottelu);
            }
            tallenna();
            paivitaRanking();
            paivitaHistoria();
        }
        else {
            Dialogs.showMessageDialog("Pelaajia täytyy olla vähintään 4 ja parillinen määrä!", dlg -> dlg.getDialogPane().setPrefWidth(400));
        }
        return;
    }

    

    @FXML
    void HandlePoista() {
        Pelaaja poistettava = chooserPelaajat.getSelectedObject();
        if (PoistaPelaajaController.kysy(null,poistettava) == null) return;
        tiistaispike.poista(poistettava);
        int id = tiistaispike.annaPelaaja(0).getId();
        hae(id);
    }

    @FXML
    void HandlePoistaPelaaja() {
        poistaValittu();
    }

    @FXML
    void HandleTallenna() {
        tallenna();
    }

    @FXML
    void HandleUusiPelaaja() {
        Pelaaja uusi = UusiPelaajaController.uusiPelaaja(null, null);
        if (uusi == null) return;
        uusi.rekisteroi();
        tiistaispike.lisaa(uusi);
        hae(uusi.getId());
    }
    
    @FXML
    void HandleMuokkaa() {
        Pelaaja muokattava = chooserPelaajat.getSelectedObject();
        if (MuokkaaPelaajaaController.kysyPelaaja(null, muokattava) == null) return; 
        hae(muokattava.getId());
        }

    @FXML
    void HandleValitsePelaaja() {
      //  Dialogs.showMessageDialog("Vielä ei osata valita pelaajaa");
        valitsePelaaja();
    }
    
    @FXML
    void handleRankingAvaus() {
       // paivitaRanking();
    }
    
    //=======================================================================================================================
    // Tästä eteenpäin ei suoraan käyttöliittymään liittyvää koodia
    private TiistaiSpike tiistaispike;
    
    private void alusta() {
        chooserPelaajat.clear();
        chooserValittavat.clear();
        chooserValitut.clear();
        gridRanking.setSortable(-1, false);
        gridHistoria.setColumnWidth(0, 300);
        
        chooserPelaajat.addSelectionListener(e -> naytaPelaaja());
        chooserValittavat.setOnMouseClicked( e -> {if (e.getClickCount() == 2)  valitsePelaaja();} );
        chooserValittavat.setOnKeyPressed( e -> {if ( e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.SPACE ) valitsePelaaja();});
        chooserValitut.setOnMouseClicked( e -> {if (e.getClickCount() == 2) poistaValittu();} );
        chooserValitut.setOnKeyPressed( e -> {if ( e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.SPACE ) poistaValittu();});
    }
    
    
    private Object[] luoOttelut(List<Pelaaja> parit) {
        List<Ottelu> ottelut = new ArrayList<Ottelu>();
        List<String> pelaajalista = new ArrayList<String>();
        Object[] ottelutjapelaajat = new Object[2];
        
        for (int i = 0; i < parit.size(); i = i + 2) {
            for (int j = i + 2; j < parit.size(); j = j + 2 ) {
            int[] pelaajat = {parit.get(i).getId(), parit.get(i+1).getId(), parit.get(j).getId(), parit.get(j+1).getId()}; 
            String pelaajatString = Mjonot.erota(new StringBuilder(parit.get(i).getNimi())) + " & " +
                                    Mjonot.erota(new StringBuilder(parit.get(i+1).getNimi())) + "  -VS-  " +
                                    Mjonot.erota(new StringBuilder(parit.get(j).getNimi())) + " & " + 
                                    Mjonot.erota(new StringBuilder(parit.get(j+1).getNimi()));
            int[] tulos = {0,0,0,0,0,0};
            ottelut.add(new Ottelu(pelaajat, tulos));
            pelaajalista.add(pelaajatString);
            }
        }
        ottelutjapelaajat[0] = ottelut;
        ottelutjapelaajat[1] = pelaajalista;
        return ottelutjapelaajat;
    }
    
    
    
    /**
     * @param hakemisto missä tiedot sijaitsevat
     */
    protected void lueTiedosto(String hakemisto) {
        try {
            tiistaispike.lueTiedostosta(hakemisto);
            paivitaRanking();
            paivitaHistoria();
            hae(0);
        } catch (FileNotFoundException e) {
            Dialogs.showMessageDialog("Ongelmia tiedoston kanssa");
        }
    }
    
    
    private void paivitaHistoria() {
        gridHistoria.clear();
        for (Ottelu ottelu : this.tiistaispike.getOtteluLista()) {
            int[] parit = ottelu.getParit();
            if (!tarkistaOlemassaOlo(parit)) continue;
            String pelaajat = Mjonot.erota(new StringBuilder(tiistaispike.getPelaaja(parit[0]).getNimi())) + " & " +
                    Mjonot.erota(new StringBuilder(tiistaispike.getPelaaja(parit[1]).getNimi())) + "  -VS-  " +
                    Mjonot.erota(new StringBuilder(tiistaispike.getPelaaja(parit[2]).getNimi())) + " & " + 
                    Mjonot.erota(new StringBuilder(tiistaispike.getPelaaja(parit[3]).getNimi())); 
            int[] pisteet = ottelu.getPisteet();
            String era1 = pisteet[0] + "-" + pisteet[1];
            String era2 = pisteet[2] + "-" + pisteet[3];
            String era3 = pisteet[4] + "-" + pisteet[5];
                
            String[] rivi = {pelaajat, era1, era2, era3};
            gridHistoria.add(rivi);
            }
        }


    private boolean tarkistaOlemassaOlo(int[] parit) {
        for (int i : parit) {
            if (tiistaispike.getPelaaja(i) == null) return false;
        }
        return true;
    }


    private void tallenna() {
        try {
            tiistaispike.tallenna();
        } catch (FileNotFoundException e) {
            Dialogs.showMessageDialog("Ongelmia tiedoston kanssa");
        }
    }
    
    
    /**
     * Ohjelma tarkistaa onko pelaajia oikea määrä
     * @return true jos pelaajia on yli neljä ja parillinen määrä, muuten false
     */
    private Boolean tarkistaValitut() {
        int pelaajia = this.chooserValitut.getObjects().size();
        if (pelaajia < 4 || pelaajia % 2 != 0) return false;
        return true;
    }
    
    
    private void valitsePelaaja() {
        Pelaaja valittava = chooserValittavat.getSelectedObject();
        if (valittava == null) return;
        
        List<Pelaaja> valitut = chooserValitut.getObjects();
        for (Pelaaja alkio : valitut) {
            if (valittava.equals(alkio)) {
                Dialogs.showMessageDialog("Pelaaja on jo valittu!");
                return;
            }
        }
        
        chooserValitut.add(valittava.getNimi(), valittava);
    }
    
    
    private void poistaValittu() {
        Pelaaja poistettava = chooserValitut.getSelectedObject();
        List<Pelaaja> valitut = chooserValitut.getObjects();
        valitut.remove(poistettava);
        chooserValitut.clear();
        for (Pelaaja alkio : valitut) {
            chooserValitut.add(alkio.getNimi(), alkio);
        }
    }
    
    /**
     * paivittaa ranking listan
     */
    private void paivitaRanking() {
        gridRanking.clear();
        tiistaispike.rankkaa();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        LinkedHashMap<Integer, Double> ranking = tiistaispike.getRanking();        
        int i = 1;
        for (Map.Entry<Integer, Double> entry : ranking.entrySet()) {
            if (this.tiistaispike.getPelaaja(entry.getKey()) == null) continue;
            String nimi = tiistaispike.getPelaaja(entry.getKey()).getNimi();
            String ratio = decimalFormat.format(entry.getValue());
            String[] rivi = {Integer.toString(i), nimi, ratio};
            gridRanking.add(rivi);
            i++;
        }
        
        
        
    }
    
    
    private void naytaPelaaja() {
        Pelaaja pelaajaKohdalla = chooserPelaajat.getSelectedObject();
       
        if (pelaajaKohdalla == null) return;
       
        tiedotNimi.setText(pelaajaKohdalla.getNimi());
        tiedotTaso.setText(Integer.toString(pelaajaKohdalla.getTaso()));
        tiedotIka.setText(Integer.toString(pelaajaKohdalla.getIka()));
        tiedotSukupuoli.setText(pelaajaKohdalla.getSukupuoli());
        tiedotKatisyys.setText(pelaajaKohdalla.getKatisyys());
        tiedotJnro.setText(Integer.toString(pelaajaKohdalla.getId()));
        
        double rating = 0.0;
        if (this.tiistaispike.getRanking().get(pelaajaKohdalla.getId()) != null) {
            rating = this.tiistaispike.getRanking().get(pelaajaKohdalla.getId());
        }
        String ratingString = String.format("%.2f", rating);
        tiedotRating.setText(ratingString);
    }
    
    
    /**
     * Luodaan uusi pelaaja
     */
    /*
    private void uusiPelaaja() {
        Pelaaja uusi = new Pelaaja();
        uusi.luojotain();
        
        tiistaispike.lisaa(uusi);
        hae(uusi.getId());
    }
    */
    
    
    /**
     * 
     * @param id pelaajan jäsennumero
     */
    private void hae(int id, ListChooser<Pelaaja> chooseri, TextField hakuehto) {
        chooseri.clear();
        String ehto = hakuehto.getText();
        int index = 0;
        for (int i = 0; i < tiistaispike.getPelaajia(); i++) {
            Pelaaja pelaaja = tiistaispike.annaPelaaja(i);
            if (pelaaja.getId() == id) index = i;
            if (!pelaaja.getNimi().toLowerCase().contains(ehto.toLowerCase())) continue;
            chooseri.add(pelaaja.getNimi(), pelaaja);
        }
        chooseri.setSelectedIndex(index);
    }
    
    private void hae(int id) {
        hae(id, this.chooserPelaajat, this.textHakuEhto);
        hae(id, this.chooserValittavat, this.textHakuEhto2);
    }

    
    /**
     * @param tiistaispike mitä tiistaispikeä käytetään
     */
    public void setKerho(TiistaiSpike tiistaispike) {
        this.tiistaispike = tiistaispike;        
    }
}