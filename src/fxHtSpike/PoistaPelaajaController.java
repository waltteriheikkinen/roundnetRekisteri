package fxHtSpike;


import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import htSpike.Pelaaja;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controlleri pelaajan poistamiselle
 * @author waltt
 * @version 24.2.2023
 *
 */
public class PoistaPelaajaController implements ModalControllerInterface<Pelaaja>, Initializable {

    @FXML private Button eiButton;
    @FXML private Button kyllaButton;
    @FXML private Label textPoistettava;
    private Pelaaja poistettava;
    private boolean poistetaanko = false;

    @FXML void handleEi() {
        ModalController.closeStage(textPoistettava);
    }

    @FXML void handleKylla() {
        this.poistetaanko = true;
        ModalController.closeStage(textPoistettava);
    }
    
    @Override
    public Pelaaja getResult() {
        if (poistetaanko) return this.poistettava;
        return null;
    }

    @Override
    public void handleShown() {
        // 
        
    }

    @Override
    public void setDefault(Pelaaja oletus) {
        this.poistettava = oletus;
        laitaTeksti();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //
    }
    
    //==========================================================================
    
    private void laitaTeksti() {
        this.textPoistettava.setText(this.poistettava.getNimi() + "?");
    }

    /**
     * @param modalitystage mikä stage
     * @param poistettava poistettava pelaaja
     * @return Pelaaja
     */
    public static Pelaaja kysy(Stage modalitystage, Pelaaja poistettava) {
        return ModalController.showModal(TiistaiSpikeGUIController.class.getResource("PoistaPelaaja.fxml"), "Pelaajan poisto", modalitystage, poistettava);
        
    }
}
