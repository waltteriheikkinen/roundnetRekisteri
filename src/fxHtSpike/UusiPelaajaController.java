/**
 * 
 */
package fxHtSpike;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import htSpike.Pelaaja;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controlleri uuden pelaajan pop upille.
 * @author waltt
 * @version 24.2.2023
 *
 */
public class UusiPelaajaController implements ModalControllerInterface<Pelaaja>, Initializable  {

    @FXML private Button peruutaButton;
    @FXML private Button tallennaButton;
    @FXML private TextField textNimi;
    @FXML private TextField textIka;
    @FXML private TextField textTaso;
    @FXML private TextField textSukuPuoli;
    @FXML private TextField textKatisyys;
    private boolean muutos;
    private Pelaaja uusipelaaja = new Pelaaja();

    @FXML void handlePeruuta() {
        this.muutos = false;
        ModalController.closeStage(textNimi);
       // Dialogs.showMessageDialog("Vielä ei osata peruuttaa");
    }

    @FXML void handleTallenna() {
        kasitteleMuutos();
        if (this.muutos) {
            ModalController.closeStage(textNimi);
        }
        return;
       // Dialogs.showMessageDialog("Vielä ei osata tallentaa pelaajaa");
    }

    
    

    @Override
    public Pelaaja getResult() {
        if (this.muutos) return this.uusipelaaja;
        return null;
    }

    @Override
    public void handleShown() {
        // 
        
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // 
        
    }

    @Override
    public void setDefault(Pelaaja arg0) {
        // 
        
    }

    
    //======================================================================================
    
    
    private void kasitteleMuutos() {
       /*
        for (TextField kentta : this.tekstit) {
            if (kentta == null || kentta.getText() == "") {
                Dialogs.showMessageDialog("Täytä kaikki kentät!");
                this.muutos = false;
                return;
            }
        }
        */
        this.uusipelaaja.setNimi(this.textNimi.getText());
        this.uusipelaaja.setIka(Integer.parseInt(this.textIka.getText()));
        this.uusipelaaja.setKatisyys(this.textKatisyys.getText());
        this.uusipelaaja.setSukuPuoli(this.textSukuPuoli.getText());
        this.uusipelaaja.setTid(Integer.parseInt(this.textTaso.getText()));
        this.muutos = true;  
    }
    
    
    /**
     * @param modalitystage mikä stage
     * @param pelaaja pelaaja
     * @return palauttaa uuden pelaajan
     */
    public static Pelaaja uusiPelaaja(Stage modalitystage, Pelaaja pelaaja) {
        return ModalController.showModal(TiistaiSpikeGUIController.class.getResource("uusipelaaja.fxml"), "Uusi pelaaja", modalitystage, pelaaja);
        
    }
}
