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

/**
 * Controlleri pelaajan poistamiselle
 * @author waltt
 * @version 24.2.2023
 *
 */
public class PoistaPelaajaController implements ModalControllerInterface<Pelaaja>, Initializable {

    @FXML private Button eiButton;
    @FXML private Button kyllaButton;
    @FXML private TextField textNimi;
    private Pelaaja poistettava;
    private boolean poistetaanko = false;

    @FXML void handleEi() {
        ModalController.closeStage(textNimi);
    }

    @FXML void handleKylla() {
        this.poistetaanko = true;
        ModalController.closeStage(textNimi);
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
        this.textNimi.setText(this.poistettava.getNimi());
    }
}
