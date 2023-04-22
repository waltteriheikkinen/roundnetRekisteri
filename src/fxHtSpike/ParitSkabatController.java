package fxHtSpike;



import java.util.ArrayList;
import java.util.List;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.StringGrid;
import htSpike.Pelaaja;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

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
    private List<Pelaaja> listaChooser = new ArrayList<Pelaaja>();

    
    @FXML void handleArvoParit() {
        ModalController.showModal(ParitSkabatController.class.getResource("Arpomispopup.fxml"), "Arvontakriteerit", null, "");
    }

    @FXML void handleHyvaksy() {
        if (tarkistaParit()) {
            ArrayList<Pelaaja> parit = luoParit(); //TODO: KORJAA TÄMÄ METODI JA TIETOJEN VIEMINEN
            ModalController.showModal(ParitSkabatController.class.getResource("OttelutTulokset.fxml"), "Ottelut ja tulokset", null, parit);
            Stage stage = (Stage) hyvaksyButton.getScene().getWindow();        
            stage.close();
            //TODO: tarkista ikkunan sulkeminen sulkeminen!
        }
        else Dialogs.showMessageDialog("Muodosta kaikki parit!");
        
    }

    @FXML
    void handleLisaaPelaaja() {
        siirraPelaaja();
    }

    @FXML
    void handlePoistaPelaaja() {
        poistaValittu();
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
        this.listaChooser.addAll(oletus);
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
        gridParit.setOnMouseClicked( e -> {if (e.getClickCount() == 2)  siirraPelaaja();} );
        gridParit.setOnKeyPressed( e -> {if ( e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.SPACE ) siirraPelaaja();});
        gridParit.select(0, 1);
        
        chooserValitut.clear();
        chooserValitut.setOnKeyPressed( e -> {if ( e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.SPACE ) siirraPelaaja();});
        for (Pelaaja pelaaja : this.valitut) {
            chooserValitut.add(pelaaja.getNimi(), pelaaja);
        }        
    }
    
    
    private ArrayList<Pelaaja> luoParit() {
        ArrayList<Pelaaja> parit = new ArrayList<Pelaaja>();
        for (int i = 0; i < this.valitut.size() / 2; i++) {
            for (int j = 1; j <= 2; j++) {
                String nimi = gridParit.get(i, j);
                Pelaaja pelaaja = new Pelaaja();
                for (int k = 0; k < valitut.size(); k++) {
                    if (valitut.get(k).getNimi() == nimi) {
                        pelaaja = valitut.get(k);
                        break;
                    }
                }
                parit.add(pelaaja);
            }
        }
        return parit;
    }
    
    
    private Boolean tarkistaParit() {
        if (this.listaChooser.size() < 1) return true;
        return false;
    }
    
    
    private void siirraPelaaja() {
        //TODO: Lisää oikeellisuustarkistukset
        
        int r = gridParit.getRowNr();
        int c = gridParit.getColumnNr();
        String sisalto = gridParit.get(r, c);
        if (sisalto.length() > 0) poistaValittu();
        else {
            Pelaaja valittava = chooserValitut.getSelectedObject();
            if (valittava == null || c < 1) return;
            gridParit.set(valittava.getNimi(), r, c);
            poistaListasta(valittava);
        }
        chooserValitut.setSelectedIndex(0);
        if (c == 1) c++;
        else {
            c = 1; r++;  
            if (r == this.valitut.size() / 2) {
                c = 1;
                r = 0;
            }
        }
        gridParit.select(r, c);
        
    }
    
    
    private void poistaValittu() {
        int r = gridParit.getRowNr();
        int c = gridParit.getColumnNr();
        if (c < 1) return;
        String sisalto = gridParit.get(r, c);
        for (int i = 0; i < valitut.size(); i++) {
            if (valitut.get(i).getNimi() == sisalto) this.listaChooser.add(valitut.get(i));
        }
        gridParit.set("", r, c);
        
        this.chooserValitut.clear();
        for (Pelaaja pelaaja : this.listaChooser) {
            this.chooserValitut.add(pelaaja.getNimi(), pelaaja);
        }
    }
    
    
    private void poistaListasta(Pelaaja valittava) {
        this.listaChooser.remove(valittava);
        this.chooserValitut.clear();
        for (Pelaaja pelaaja : this.listaChooser) {
            this.chooserValitut.add(pelaaja.getNimi(), pelaaja);
        }
    }
}
