package fxHtSpike;



import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;




/**
 * @author waltt
 * @version 22.2.2023
 *
 */
public class TiistaiSpikeGUIController implements Initializable{
    @FXML private ListChooser<Pelaaja> chooserPelaajat;
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
        ModalController.showModal(TiistaiSpikeGUIController.class.getResource("ParitSkabat.fxml"), "Parien valinta", null, "");
        }

    @FXML
    void HandlePoista() {
        ModalController.showModal(TiistaiSpikeGUIController.class.getResource("PoistaPelaaja.fxml"), "Pelaajan poisto", null, "");
        }

    @FXML
    void HandlePoistaPelaaja() {
        Dialogs.showMessageDialog("Vielä ei osata poistaa pelaajaa");
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
        Dialogs.showMessageDialog("Vielä ei osata valita pelaajaa");
    }
    
    //=======================================================================================================================
    // Tästä eteenpäin ei suoraan käyttöliittymään liittyvää koodia
    private TiistaiSpike tiistaispike;
    private TextArea textPelaaja = new TextArea(); //TODO: poista lopuksi
    
    private void alusta() {
        chooserPelaajat.clear();
        chooserPelaajat.addSelectionListener(e -> naytaPelaaja());
    }
    
    private void naytaPelaaja() {
        Pelaaja pelaajaKohdalla = chooserPelaajat.getSelectedObject();
       
        if (pelaajaKohdalla == null) return;
       
        tiedotNimi.setText(pelaajaKohdalla.getNimi());
        tiedotTaso.setText(Integer.toString(pelaajaKohdalla.getTaso()));
        tiedotIka.setText(Integer.toString(pelaajaKohdalla.getIka()));
        tiedotSukupuoli.setText(pelaajaKohdalla.getSukupuoli());
        tiedotKatisyys.setText(pelaajaKohdalla.getKatisyys());
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
        int index = 0;
        for (int i = 0; i < tiistaispike.getPelaajia(); i++) {
            Pelaaja pelaaja = tiistaispike.annaPelaaja(i);
            if (pelaaja.getId() == id) index = i;
            chooserPelaajat.add(pelaaja.getNimi(), pelaaja);
        }
        chooserPelaajat.setSelectedIndex(index);
    }

    
    /**
     * @param tiistaispike mitä tiistaispikeä käytetään
     */
    public void setKerho(TiistaiSpike tiistaispike) {
        this.tiistaispike = tiistaispike;        
    }

    

}