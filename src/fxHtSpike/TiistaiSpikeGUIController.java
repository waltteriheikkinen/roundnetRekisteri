package fxHtSpike;



import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import fi.jyu.mit.fxgui.*;
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
 *
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
    
    
    
    @FXML void HandleEteenPain() {
        if (tarkistaValitut()) {
            ModalController.showModal(ParitSkabatController.class.getResource("ParitSkabat.fxml"), "Parien valinta", null, chooserValitut.getObjects());
        }
        else Dialogs.showMessageDialog("Pelaajia täytyy olla vähintään 4 ja parillinen määrä!");
        return;
        }

    @FXML
    void HandlePoista() {
        ModalController.showModal(TiistaiSpikeGUIController.class.getResource("PoistaPelaaja.fxml"), "Pelaajan poisto", null, "");
        }

    @FXML
    void HandlePoistaPelaaja() {
        poistaValittu();
    }

    @FXML
    void HandleTallenna() {
        Dialogs.showMessageDialog("Vielä ei osata tallentaa pelaajaa");
    }

    @FXML
    void HandleUusiPelaaja() {
        // ModalController.showModal(TiistaiSpikeGUIController.class.getResource("uusipelaaja.fxml"), "Uusi pelaaja", null, "");
        uusiPelaaja();
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
        TreeMap<Double, Integer> ranking = tiistaispike.getRanking();        
        int i = 1;
        for (Map.Entry<Double, Integer> entry : ranking.entrySet()) {
            String nimi = tiistaispike.getPelaaja(entry.getValue()).getNimi();
            String[] rivi = {Integer.toString(i), nimi, entry.getKey().toString()};
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
        
     //   Random rand = new Random(); //TODO: poista myöhemmin
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