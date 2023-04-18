package fxHtSpike;



import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.StringGrid;
import htSpike.Pelaaja;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controlleri parien valitsemiselle
 * @author waltt
 * @version 24.2.2023
 *
 */
public class ParitSkabatController implements ModalControllerInterface<String> {

    @FXML private Button LisaaPelaajaButton;
    @FXML private Button PoistaPelaajaButton;
    @FXML private Button arvoParitButton;
    @FXML private Button hyvaksyButton;
    @FXML private StringGrid<Pelaaja> gridParit;
  //  private List<Pelaaja> valitut;

    
    @FXML void handleArvoParit() {
        ModalController.showModal(ParitSkabatController.class.getResource("Arpomispopup.fxml"), "Arvontakriteerit", null, "");
    }

    @FXML void handleHyvaksy() {
        ModalController.showModal(ParitSkabatController.class.getResource("OttelutTulokset.fxml"), "Ottelut ja tulokset", null, "");
    }

    @FXML
    void handleLisaaPelaaja() {
        Dialogs.showMessageDialog("Vielä ei osata lisätä pelaajaa");
    }

    @FXML
    void handlePoistaPelaaja() {
        Dialogs.showMessageDialog("Vielä ei osata poistaa pelaajaa");
    }
    
    
    @Override
    public String getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        alusta();
        
    }

    @Override
    public void setDefault(String oletus) {
        // TODO Auto-generated method stub
        
    }
    
    
    //=========================================================================================
    
    private void alusta() {
        gridParit.setSortable(0, false);
        gridParit.setSortable(1, false);
        gridParit.setSortable(2, false);
        gridParit.clear();
        
    }
}
