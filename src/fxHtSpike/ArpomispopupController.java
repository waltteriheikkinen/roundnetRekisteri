/**
 * 
 */
package fxHtSpike;

import java.awt.*;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;


/**
 * @author waltt
 * @version 24.2.2023
 * TODO: 4 Arpominen kokoonaan
 */
public class ArpomispopupController implements ModalControllerInterface<String> {

    @FXML private Button arvoButton;

    @FXML
    private Button peruutaButton;

    @FXML
    void handleArvo() {
        ModalController.showModal(ArpomispopupController.class.getResource("OttelutTulokset.fxml"), "Ottelut ja tulokset", null, "");
    }

    @FXML
    void handlePeruuta() {
        Dialogs.showMessageDialog("Vielä ei osata peruuttaa");
    }
    
    @Override
    public String getResult() {
        // 
        return null;
    }

    @Override
    public void handleShown() {
        // 
        
    }

    @Override
    public void setDefault(String oletus) {
        // 
        
    }
    //

}
