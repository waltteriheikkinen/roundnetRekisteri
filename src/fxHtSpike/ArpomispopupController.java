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
 *
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(String oletus) {
        // TODO Auto-generated method stub
        
    }
    //TODO

}
