/**
 * 
 */
package fxHtSpike;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.StringGrid;
import fi.jyu.mit.ohj2.Mjonot;
import htSpike.Ottelu;
import htSpike.Pelaaja;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;


/**
 * @author waltt
 * @version 24.2.2023
 * Kontrolleri ohjaa otteluiden tulosten merkkaamista ja tallentamista
 */
public class OttelutTuloksetController implements ModalControllerInterface<Object[]>, Initializable {

    @FXML private Button peruutaButton;
    @FXML private Button tallennaJaLopetaButton;
    @FXML private StringGrid<Object> gridOttelut;
    private List<Ottelu> ottelut;
    private List<String> pelaajat;
    private List<Ottelu> pelatutlista = new ArrayList<Ottelu>();
    private Object[] pelatut = {new ArrayList<Ottelu>(), new ArrayList<Pelaaja>()};
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //
    }
    
    @FXML void handlePeruuta() {
        pelatut = null;
        ModalController.closeStage(tallennaJaLopetaButton);
    }

    @FXML void handleTallennaJaLopeta() {
        if (!tallennaOttelut()) {
            Dialogs.showMessageDialog("Korjaa tulokset");
            return;
        }
        this.pelatut[0] = this.pelatutlista;
        ModalController.closeStage(tallennaJaLopetaButton);
    }

    
    @Override
    public Object[] getResult() {
        return this.pelatut;
    }

    @Override
    public void handleShown() {
        //
    }


    @SuppressWarnings("unchecked")
    @Override
    public void setDefault(Object[] ottelut) {
        this.ottelut = (List<Ottelu>) ottelut[0];
        this.pelaajat = (List<String>) ottelut[1];
        alusta();
    }
    
    //====================================================================
    
    private void alusta() {
        gridOttelut.setSortable(-1, false);
        gridOttelut.clear();
        gridOttelut.getSelectionModel().setCellSelectionEnabled(true);
        gridOttelut.setColumnWidth(0, 300);
        luoOttelut();
    }
    
    

    private void luoOttelut() {
        for (String parit : this.pelaajat) {
            String[] rivi = {parit, "","",""};
            this.gridOttelut.add(rivi);
        }
    }
    
    /*
     * Aliohjelma tallentaa ottelut
     */
    private Boolean tallennaOttelut() {
        int rivit = this.ottelut.size();
        String[] erat = new String[3];
        
        for (int i = 0; i < rivit; i++) {
            for (int j = 1; j <= erat.length; j++) {
                if (j == 1) erat[0] = this.gridOttelut.get(i, j);
                if (j == 2) erat[1] = this.gridOttelut.get(i, j);
                if (j == 3) erat[2] = this.gridOttelut.get(i, j);
            }
            
            int[] pisteet = kasitteleErat(erat);
            if (pisteet[0] == -1) return false;
            if (tarkistapisteet(pisteet)) {
                int[] pelatunparit = this.ottelut.get(i).getParit();
                this.pelatutlista.add(new Ottelu(pelatunparit, pisteet));
            }
            
        }
        return true;
        
    }
    
    
    /**
     * 
     * @param pisteet ottelun pisteet
     * @return true jos pisteet on merkattu oikein, muuten false
     */
    private boolean tarkistapisteet(int[] pisteet) {
        int summa = 0;
        for (int i : pisteet) {
            if (i < 0) return false;
            summa = summa + i;
        }
        if (summa <= 0) return false;
        return true;
    }

    
    //TODO: 2 Oikeellusuustarkistukset numeroille
    /**
     * @param erat erien pisteet merkkijonoina
     * @return taulukon jossa on erien tulokset int lukuina
     * @example
     * <pre name="test">
     * #STATICIMPORT
     * int[] t1 = kasitteleErat(new String[] {"21-15", "21-15", "21-15"});
     * Arrays.toString(t1) === "[21, 15, 21, 15, 21, 15]";
     * int[] t2 = kasitteleErat(new String[] {"21-15", "21-15", null});
     * Arrays.toString(t2) === "[21, 15, 21, 15, 0, 0]";
     * int[] t3 = kasitteleErat(new String[] {"2-1", "", null});
     * Arrays.toString(t3) === "[2, 1, 0, 0, 0, 0]";
     * </pre>
     */
    public static int[] kasitteleErat(String[] erat) {
        int[] tulos = new int[6];
        int i = 0;
        for (String era : erat) {
            
            if (era == null || era.equals("")) {
                i = i + 2;
                continue;
            }
            if (!era.matches("^\\d+-\\d+$")) {
                int[] kusi = {-1};
                return kusi;
            }
            StringBuilder eraB = new StringBuilder(era);
            int tulos1 = Integer.parseInt(Mjonot.erota(eraB, '-'));
            int tulos2 = Integer.parseInt(eraB.substring(0));
            tulos[i++] = tulos1;
            tulos[i++] = tulos2;
        }
        return tulos;
    }
    
    

    /**
     * @param modalitystage mikä stage
     * @param oletus parametrit
     * @return pelaajalista
     */
    public static Object[] syotaTulokset(Stage modalitystage, Object[] oletus) {
        return ModalController.showModal(ParitSkabatController.class.getResource("OttelutTulokset.fxml"), "Ottelut ja tulokset", modalitystage, oletus);
    }

    
}
