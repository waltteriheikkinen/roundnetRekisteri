package fxHtSpike;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controlleri pelaajan poistamiselle
 * @author waltt
 * @version 24.2.2023
 *
 */
public class PoistaPelaajaController implements ModalControllerInterface<String> {

    @FXML private Button eiButton;

    @FXML private Button kyllaButton;

    @FXML void handleEi() {
        Dialogs.showMessageDialog("Vielä ei osata sanoa ei lol");
    }

    @FXML void handleKylla() {
        Dialogs.showMessageDialog("Vielä ei osata poistaa pelaajaa");
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
