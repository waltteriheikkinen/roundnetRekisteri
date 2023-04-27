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
 * @author waltt
 * @version 24.4.2023
 *
 */
public class MuokkaaPelaajaaController implements ModalControllerInterface<Pelaaja>, Initializable   {

    @FXML private Button peruutaButton;
    @FXML private Button tallennaButton;
    @FXML private TextField textIka;
    @FXML private TextField textKatisyys;
    @FXML private TextField textNimi;
    @FXML private TextField textSukuPuoli;
    @FXML private TextField textTaso;
    private Pelaaja muokattavapelaaja;
    private boolean muutos = false;
    
    @FXML void handlePeruuta() {
        ModalController.closeStage(textNimi);
    }

    @FXML
    void handleTallenna() {
        kasitteleMuutos();
        if (!muutos) {
            return;
        }
        ModalController.closeStage(textNimi);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //
        
    }

    @Override
    public Pelaaja getResult() {
        if (muutos) return muokattavapelaaja;
        return null;
    }

    @Override
    public void handleShown() {
        // 
        
    }

    @Override
    public void setDefault(Pelaaja oletus) {
        this.muokattavapelaaja = oletus;
        naytaPelaaja();
    }
  
    

    //======================================================================
    
    
    
    private void kasitteleMuutos() {
        if (muokattavapelaaja == null) return;
        String muutettu = this.textNimi.getText(); 
        if (muutettu.equals("")) {
            Dialogs.showMessageDialog("Nimi ei voi olla tyhjä!");
            return;
        }
        this.muokattavapelaaja.setNimi(this.textNimi.getText());
        
        muutettu = this.textIka.getText(); 
        if (!muutettu.matches("[0-9]+")) {
            Dialogs.showMessageDialog("Väärä muoto iälle!");
            return;
        }
        this.muokattavapelaaja.setIka(Integer.parseInt(this.textIka.getText()));
        
        muutettu = this.textKatisyys.getText().toLowerCase(); 
        if (!muutettu.equals("oikea") && !muutettu.equals("vasen") && !muutettu.equals("molempikätinen")) {
            Dialogs.showMessageDialog("Korjaa kätisyys!\nVaihtoehdot ovat:\nOikea\nVasen\nMolempikätinen");
            return;
        }
        StringBuilder s = new StringBuilder(muutettu.substring(1));
        s.insert(0, muutettu.toUpperCase().substring(0,1));
        this.muokattavapelaaja.setKatisyys(s.toString());
        
        muutettu = this.textSukuPuoli.getText().toLowerCase(); 
        if (!muutettu.equals("mies") && !muutettu.equals("nainen") && !muutettu.equals("muu")) {
            Dialogs.showMessageDialog("Korjaa sukupuoli!\nVaihtoehdot ovat:\nMies\nNainen\nMuu");
            return;
        }
        s = new StringBuilder(muutettu.substring(1));
        s.insert(0, muutettu.toUpperCase().substring(0,1));
        this.muokattavapelaaja.setSukuPuoli(s.toString());
        
        muutettu = this.textTaso.getText(); 
        if (!muutettu.matches("[1-5]")) {
            Dialogs.showMessageDialog("Tason täytyy olla väliltä 1-5!");
            return;
        }
        this.muokattavapelaaja.setTid(Integer.parseInt(this.textTaso.getText()));
        
        this.muutos = true;
    }
    
    
    private void naytaPelaaja() {
        this.textIka.setText(Integer.toString(this.muokattavapelaaja.getIka()));     
        this.textKatisyys.setText(this.muokattavapelaaja.getKatisyys());
        this.textNimi.setText(this.muokattavapelaaja.getNimi());
        this.textSukuPuoli.setText(this.muokattavapelaaja.getSukupuoli());
        this.textTaso.setText(Integer.toString(this.muokattavapelaaja.getTaso()));
    }
    
    /**
     * @param modalitystage mikä stage
     * @param muokattava muokattava pelaaja
     * @return viite muokattuun pelaajaan tai null
     */
    public static Pelaaja kysyPelaaja(Stage modalitystage, Pelaaja muokattava) {
        return ModalController.showModal(TiistaiSpikeGUIController.class.getResource("MuokkaaPelaajaa.fxml"), "Muokkaa", modalitystage, muokattava);
        
    }

}
