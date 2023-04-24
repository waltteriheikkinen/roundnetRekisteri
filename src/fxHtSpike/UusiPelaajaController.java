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

    @FXML void handlePeruuta() {
        Dialogs.showMessageDialog("Vielä ei osata peruuttaa");
    }

    @FXML void handleTallenna() {
        Dialogs.showMessageDialog("Vielä ei osata tallentaa pelaajaa");
    }

    
    @Override
    public Pelaaja getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(Pelaaja arg0) {
        // TODO Auto-generated method stub
        
    }

    
    //======================================================================================
    
    
    
    /**
     * @param modalitystage mikä stage
     * @param pelaaja pelaaja
     * @return palauttaa uuden pelaajan
     */
    public static Pelaaja uusiPelaaja(Stage modalitystage, Pelaaja pelaaja) {
        return ModalController.showModal(TiistaiSpikeGUIController.class.getResource("uusipelaaja.fxml"), "Uusi pelaaja", modalitystage, pelaaja);
        
    }
}
