/**
 * 
 */
package fxHtSpike;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controlleri uuden pelaajan pop upille.
 * @author waltt
 * @version 24.2.2023
 *
 */
public class UusiPelaajaController implements ModalControllerInterface<String>  {

    @FXML private Button peruutaButton;

    @FXML private Button tallennaButton;

    @FXML void handlePeruuta() {
        Dialogs.showMessageDialog("Vielä ei osata peruuttaa");
    }

    @FXML void handleTallenna() {
        Dialogs.showMessageDialog("Vielä ei osata tallentaa pelaajaa");
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
    public void setDefault(String arg0) {
        // TODO Auto-generated method stub
        
    }
    //TODO
}
