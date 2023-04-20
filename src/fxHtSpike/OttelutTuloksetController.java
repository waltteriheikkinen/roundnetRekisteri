/**
 * 
 */
package fxHtSpike;

import java.util.ArrayList;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.StringGrid;
import htSpike.Pelaaja;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @author waltt
 * @version 24.2.2023
 *
 */
public class OttelutTuloksetController implements ModalControllerInterface<ArrayList<Pelaaja>> {

    @FXML private Button peruutaButton;
    @FXML private Button tallennaJaLopetaButton;
    @FXML private StringGrid<Object> gridOttelut;

    private ArrayList<Pelaaja> parit;
    
    @FXML void handlePeruuta() {
        Dialogs.showMessageDialog("Vielä ei osata peruuttaa");
    }

    @FXML void handleTallennaJaLopeta() {
        Dialogs.showMessageDialog("Vielä ei osata tallentaa ja lopettaa");
    }

    
    @Override
    public ArrayList<Pelaaja> getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        alusta();
        
    }


    @Override
    public void setDefault(ArrayList<Pelaaja> parit) {
        this.parit = parit;
        
    }
    
    //====================================================================
    
    private void alusta() {
        gridOttelut.setSortable(-1, false);
        gridOttelut.clear();
        for(int i = 0; i < this.parit.size(); i = i + 4) {
            String pari1 = this.parit.get(i).getNimi() + " & " + this.parit.get(i + 1).getNimi();
            String pari2 = this.parit.get(i + 2).getNimi() + " & " + this.parit.get(i + 3).getNimi();
            String[] rivi = {pari1 + " VS " + pari2, "","",""};
            gridOttelut.add(rivi);
        }
       
        gridOttelut.setColumnWidth(0, 300);
        gridOttelut.setColumnWidth(1,50);
        gridOttelut.setColumnWidth(2,50);
        gridOttelut.setColumnWidth(3,50);
        
        
    }
    
}
