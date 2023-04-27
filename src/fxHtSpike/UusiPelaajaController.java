/**
 * 
 */
package fxHtSpike;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import htSpike.Pelaaja;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controlleri uuden pelaajan pop upille.
 * @author waltt
 * @version 24.2.2023
 *
 */
public class UusiPelaajaController implements ModalControllerInterface<Pelaaja>, Initializable  {

    @FXML private Button peruutaButton;
    @FXML private Button tallennaButton;
    @FXML private TextField textNimi;
    @FXML private TextField textIka;
    @FXML private TextField textTaso;
    @FXML private TextField textSukuPuoli;
    @FXML private TextField textKatisyys;
    private boolean muutos = false;
    private Pelaaja uusipelaaja = new Pelaaja();

    @FXML void handlePeruuta() {
        ModalController.closeStage(textNimi);
    }

    @FXML void handleTallenna() {
        kasitteleMuutos();
        if (this.muutos) {
            ModalController.closeStage(textNimi);
        }
        return;
    }

    
    

    @Override
    public Pelaaja getResult() {
        if (this.muutos) return this.uusipelaaja;
        return null;
    }

    @Override
    public void handleShown() {
        // 
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // 
    }

    @Override
    public void setDefault(Pelaaja arg0) {
        // 
    }

    
    //======================================================================================
    
    
    private void kasitteleMuutos() {
        if (uusipelaaja == null) return;
        String muutettu = this.textNimi.getText(); 
        if (muutettu.equals("")) {
            Dialogs.showMessageDialog("Nimi ei voi olla tyhjä!");
            return;
        }
        this.uusipelaaja.setNimi(this.textNimi.getText());
        
        muutettu = this.textIka.getText(); 
        if (!muutettu.matches("[0-9]+")) {
            Dialogs.showMessageDialog("Väärä muoto iälle!");
            return;
        }
        this.uusipelaaja.setIka(Integer.parseInt(this.textIka.getText()));
        
        muutettu = this.textKatisyys.getText().toLowerCase(); 
        if (!muutettu.equals("oikea") && !muutettu.equals("vasen") && !muutettu.equals("molempikätinen")) {
            Dialogs.showMessageDialog("Korjaa kätisyys!\nVaihtoehdot ovat:\nOikea\nVasen\nMolempikätinen");
            return;
        }
        StringBuilder s = new StringBuilder(muutettu.substring(1));
        s.insert(0, muutettu.toUpperCase().substring(0,1));
        this.uusipelaaja.setKatisyys(s.toString());
        
        muutettu = this.textSukuPuoli.getText().toLowerCase(); 
        if (!muutettu.equals("mies") && !muutettu.equals("nainen") && !muutettu.equals("muu")) {
            Dialogs.showMessageDialog("Korjaa sukupuoli!\nVaihtoehdot ovat:\nMies\nNainen\nMuu");
            return;
        }
        s = new StringBuilder(muutettu.substring(1));
        s.insert(0, muutettu.toUpperCase().substring(0,1));
        this.uusipelaaja.setSukuPuoli(s.toString());
        
        muutettu = this.textTaso.getText(); 
        if (!muutettu.matches("[1-5]")) {
            Dialogs.showMessageDialog("Tason täytyy olla väliltä 1-5!");
            return;
        }
        this.uusipelaaja.setTid(Integer.parseInt(this.textTaso.getText()));
        
        this.muutos = true;
    }
    
    
    /**
     * @param modalitystage mikä stage
     * @param pelaaja pelaaja
     * @return palauttaa uuden pelaajan
     */
    public static Pelaaja uusiPelaaja(Stage modalitystage, Pelaaja pelaaja) {
        return ModalController.showModal(TiistaiSpikeGUIController.class.getResource("uusipelaaja.fxml"), "Uusi pelaaja", modalitystage, pelaaja);
        
    }
}
