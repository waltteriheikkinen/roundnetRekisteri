package fxHtSpike;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import htSpike.Pelaaja;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author waltt
 * @version 24.4.2023
 *
 */
public class MuokkaaPelaajaaController implements ModalControllerInterface<Pelaaja>, Initializable   {

    @FXML private Button peruutaButton;
    @FXML private Button tallennaButton;
    @FXML private TextField textIka;
    @FXML private TextField textKatisyys;
    @FXML private TextField textNimi;
    @FXML private TextField textSukuPuoli;
    @FXML private TextField textTaso;
    private Pelaaja muokattavapelaaja;
    private boolean muutos = false;
    
    @FXML void handlePeruuta() {
        ModalController.closeStage(textNimi);
    }

    @FXML
    void handleTallenna() {
        kasitteleMuutos();
        this.muutos = true;
        ModalController.closeStage(textNimi);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //
        
    }

    @Override
    public Pelaaja getResult() {
        if (muutos) return muokattavapelaaja;
        return null;
    }

    @Override
    public void handleShown() {
        // 
        
    }

    @Override
    public void setDefault(Pelaaja oletus) {
        this.muokattavapelaaja = oletus;
        naytaPelaaja();
    }
  
    

    //======================================================================
    
    
    
    //TODO: 2 Oikeellisuustarkistukset ja sama metodi muille osille
    private void kasitteleMuutos() {
        if (muokattavapelaaja == null) return;
        this.muokattavapelaaja.setNimi(this.textNimi.getText());
        this.muokattavapelaaja.setIka(Integer.parseInt(this.textIka.getText()));
        this.muokattavapelaaja.setKatisyys(this.textKatisyys.getText());
        this.muokattavapelaaja.setSukuPuoli(this.textSukuPuoli.getText());
        this.muokattavapelaaja.setTid(Integer.parseInt(this.textTaso.getText()));
    }
    
    
    private void naytaPelaaja() {
        this.textIka.setText(Integer.toString(this.muokattavapelaaja.getIka()));     
        this.textKatisyys.setText(this.muokattavapelaaja.getKatisyys());
        this.textNimi.setText(this.muokattavapelaaja.getNimi());
        this.textSukuPuoli.setText(this.muokattavapelaaja.getSukupuoli());
        this.textTaso.setText(Integer.toString(this.muokattavapelaaja.getTaso()));
    }
    
    /**
     * @param modalitystage mikä stage
     * @param muokattava muokattava pelaaja
     * @return viite muokattuun pelaajaan tai null
     */
    public static Pelaaja kysyPelaaja(Stage modalitystage, Pelaaja muokattava) {
        return ModalController.showModal(TiistaiSpikeGUIController.class.getResource("MuokkaaPelaajaa.fxml"), "Muokkaa", modalitystage, muokattava);
        
    }

}
