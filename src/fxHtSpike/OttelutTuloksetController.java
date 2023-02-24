/**
 * 
 */
package fxHtSpike;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @author waltt
 * @version 24.2.2023
 *
 */
public class OttelutTuloksetController implements ModalControllerInterface<String> {

    @FXML private Button peruutaButton;

    @FXML private Button tallennaJaLopetaButton;

    @FXML void handlePeruuta() {
        Dialogs.showMessageDialog("Vielä ei osata peruuttaa");
    }

    @FXML void handleTallennaJaLopeta() {
        Dialogs.showMessageDialog("Vielä ei osata tallentaa ja lopettaa");
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
