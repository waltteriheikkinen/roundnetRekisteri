package fxHtSpike;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.StringGrid;
import htSpike.Pelaaja;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * @author waltt
 * @version 25.4.2023
 * kontrolleri yhden ottelun luomiselle
 */
public class YksiOtteluController implements ModalControllerInterface<Pelaaja>, Initializable{

    @FXML
    private StringGrid<?> gridOttelut;

    @FXML
    private Button peruutaButton;

    @FXML
    private Button tallennaJaLopetaButton;

    @FXML
    void handlePeruuta() {
        //
    }

    @FXML
    void handleTallennaJaLopeta() {
        //
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
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
    public void setDefault(Pelaaja arg0) {
        // TODO Auto-generated method stub
        
    }

}
