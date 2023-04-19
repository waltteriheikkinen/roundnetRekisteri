package fxHtSpike;



import java.util.List;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
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
public class ParitSkabatController implements ModalControllerInterface<List<Pelaaja>> {

    @FXML private Button LisaaPelaajaButton;
    @FXML private Button PoistaPelaajaButton;
    @FXML private Button arvoParitButton;
    @FXML private Button hyvaksyButton;
    @FXML private StringGrid<Pelaaja> gridParit;
    @FXML private ListChooser<Pelaaja> chooserValitut;
    private List<Pelaaja> valitut;

    
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
    public List<Pelaaja> getResult() {
        return null;
    }

    @Override
    public void handleShown() {
        alusta();
        
    }

    @Override
    public void setDefault(List<Pelaaja> oletus) {
        this.valitut = oletus;
        
    }
    
    
    //=========================================================================================
    
    private void alusta() {
        gridParit.setSortable(-1, false);
        gridParit.clear(); 
        for(int i = 0; i < this.valitut.size()/2; i++) {
            String[] rivi = {Integer.toString(i + 1), "",""};
            gridParit.add(rivi);
        }
        gridParit.getSelectionModel().setCellSelectionEnabled(true); 
        gridParit.setOnMouseClicked( e -> {if (e.getClickCount() == 2)  valitsePelaaja();} );
        
        chooserValitut.clear();
        for (Pelaaja pelaaja : this.valitut) {
            chooserValitut.add(pelaaja.getNimi(), pelaaja);
        }
        
        
    }
    
    
    private void valitsePelaaja() {
        //TODO: Lisää oikeellisuustarkistukset
        Pelaaja valittava = chooserValitut.getSelectedObject();
        int r = gridParit.getRowNr();
        int c = gridParit.getColumnNr();
        if (valittava == null || c < 1) return;
        gridParit.set(valittava.getNimi(), r, c);
    }
}
