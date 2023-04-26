package fxHtSpike;



import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import fi.jyu.mit.fxgui.*;
import htSpike.*;
import htSpike.Pelaaja;
import htSpike.TiistaiSpike;
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
 * TODO: 1a etsi toiminto
 */
public class TiistaiSpikeGUIController implements Initializable{
    @FXML private ListChooser<Pelaaja> chooserPelaajat;
    @FXML private ListChooser<Pelaaja> chooserValittavat;
    @FXML private ListChooser<Pelaaja> chooserValitut;
    @FXML private StringGrid<Object> gridRanking;
    @FXML private Tab tabRanking;
    @FXML private GridPane gridPelaaja;
    @FXML private BorderPane borderPelaaja;
    @FXML private TextField tiedotNimi;
    @FXML private TextField tiedotIka;
    @FXML private TextField tiedotSukupuoli;
    @FXML private TextField tiedotKatisyys;
    @FXML private TextField tiedotRating;
    @FXML private TextField tiedotTaso;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();
        
    }
    
    @FXML
    void HandleLuoOttelu() {
        List<Pelaaja> pelaajat = chooserValitut.getObjects();
        int[] iideet = {pelaajat.get(0).getId(), pelaajat.get(1).getId(), pelaajat.get(2).getId(), pelaajat.get(3).getId()}; 
        Ottelu ottelu = new Ottelu(iideet);
        tiistaispike.lisaa(ottelu);
        // ModalController.showModal(TiistaiSpikeGUIController.class.getResource("YksiOttelu.fxml"), "Ottelu", null, null);
    }

    
    
    @FXML void HandleEteenPain() {
        if (tarkistaValitut()) {
            List<Pelaaja> parit = ParitSkabatController.luoPariLista(null, chooserValitut.getObjects());
            
            OttelutTuloksetController.luoOttelulista(null, parit);
            
            
        }
        else Dialogs.showMessageDialog("Pelaajia täytyy olla vähintään 4 ja parillinen määrä!");
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
        uusiPelaaja();
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
        paivitaRanking();
    }
    
    //=======================================================================================================================
    // Tästä eteenpäin ei suoraan käyttöliittymään liittyvää koodia
    private TiistaiSpike tiistaispike;
    
    private void alusta() {
        chooserPelaajat.clear();
        chooserValittavat.clear();
        chooserValitut.clear();
        gridRanking.setSortable(-1, false);
        chooserPelaajat.addSelectionListener(e -> naytaPelaaja());
        chooserValittavat.setOnMouseClicked( e -> {if (e.getClickCount() == 2)  valitsePelaaja();} );
        chooserValittavat.setOnKeyPressed( e -> {if ( e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.SPACE ) valitsePelaaja();});
        chooserValitut.setOnMouseClicked( e -> {if (e.getClickCount() == 2) poistaValittu();} );
        chooserValitut.setOnKeyPressed( e -> {if ( e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.SPACE ) poistaValittu();});
        
    }
    
    
    
    /**
     * @param hakemisto missä tiedot sijaitsevat
     */
    protected void lueTiedosto(String hakemisto) {
        try {
            tiistaispike.lueTiedostosta(hakemisto);
            hae(0);
        } catch (FileNotFoundException e) {
            Dialogs.showMessageDialog("Ongelmia tiedoston kanssa");
        }
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
    
    private void paivitaRanking() {
        gridRanking.clear();
        tiistaispike.rankkaa();
        TreeMap<Integer, Double> ranking = tiistaispike.getRanking();        
        int i = 1;
        for (Map.Entry<Integer, Double> entry : ranking.entrySet()) {
            String nimi = tiistaispike.getPelaaja(entry.getKey()).getNimi();
            String[] rivi = {Integer.toString(i), nimi, entry.getValue().toString()};
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
        
        //TODO: 2 hae rating/ranking tieto tietojen näyttöön
        tiedotRating.setText("");
    }
    
    
    /**
     * Luodaan uusi pelaaja
     */
    private void uusiPelaaja() {
        Pelaaja uusi = new Pelaaja();
        uusi.luojotain();
        
        tiistaispike.lisaa(uusi);
        hae(uusi.getId());
    }
    
    
    /**
     * 
     * @param id pelaajan jäsennumero
     */
    private void hae(int id) {
        chooserPelaajat.clear();
        chooserValittavat.clear();
        int index = 0;
        for (int i = 0; i < tiistaispike.getPelaajia(); i++) {
            Pelaaja pelaaja = tiistaispike.annaPelaaja(i);
            if (pelaaja.getId() == id) index = i;
            chooserPelaajat.add(pelaaja.getNimi(), pelaaja);
            chooserValittavat.add(pelaaja.getNimi(), pelaaja);
        }
        chooserPelaajat.setSelectedIndex(index);
      //chooserPelaajatSkabat.setSelectedIndex(index);
    }

    
    /**
     * @param tiistaispike mitä tiistaispikeä käytetään
     */
    public void setKerho(TiistaiSpike tiistaispike) {
        this.tiistaispike = tiistaispike;        
    }
    
    /**
     * @return viitteen tiistaispikeen
     */
    public TiistaiSpike getTiistaiSpike() {
        return this.tiistaispike;
    }
    
    

}