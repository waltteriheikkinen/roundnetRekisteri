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
 * TODO: Listaan pelaajien nimet vielä
 */
public class OttelutTuloksetController implements ModalControllerInterface<ArrayList<Ottelu>>, Initializable {

    @FXML private Button peruutaButton;
    @FXML private Button tallennaJaLopetaButton;
    @FXML private StringGrid<Object> gridOttelut;
    private List<Ottelu> ottelut;
    private ArrayList<Ottelu> pelatut = new ArrayList<Ottelu>();
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //
    }
    
    @FXML void handlePeruuta() {
        Dialogs.showMessageDialog("Vielä ei osata peruuttaa");
    }

    @FXML void handleTallennaJaLopeta() {
        tallennaOttelut();
        ModalController.closeStage(tallennaJaLopetaButton);
    }

    
    @Override
    public ArrayList<Ottelu> getResult() {
        return this.pelatut;
    }

    @Override
    public void handleShown() {
        //
    }


    @Override
    public void setDefault(ArrayList<Ottelu> ottelut) {
        this.ottelut = ottelut;
        alusta();
    }
    
    //====================================================================
    
    private void alusta() {
        gridOttelut.setSortable(-1, false);
        gridOttelut.clear();
        luoOttelut();
                
        gridOttelut.setColumnWidth(0, 300);
     //   gridOttelut.setColumnWidth(1,60);
     //   gridOttelut.setColumnWidth(2,60);
     //   gridOttelut.setColumnWidth(3,60);
        
    }
    
    

    private void luoOttelut() {
        for (Ottelu ottelu : this.ottelut) {
            String otteluparit = ottelu.getParit()[0] + "&" + ottelu.getParit()[1] + "  VS  " + ottelu.getParit()[2] + "&" + ottelu.getParit()[0];
            String[] rivi = {otteluparit, "","",""};
            this.gridOttelut.add(rivi);
        }
    }
    
    /*
     * Aliohjelma tallentaa ottelut
     */
    private void tallennaOttelut() {
        int rivit = this.ottelut.size();
        String[] erat = new String[3];
        
        for (int i = 0; i < rivit; i++) {
            for (int j = 1; j <= erat.length; j++) {
                if (j == 1) erat[0] = this.gridOttelut.get(i, j);
                if (j == 2) erat[1] = this.gridOttelut.get(i, j);
                if (j == 3) erat[2] = this.gridOttelut.get(i, j);
            }
            int[] pisteet = kasitteleErat(erat);
            if (tarkistapisteet(pisteet)) {
                int[] pelatunparit = this.ottelut.get(i).getParit();
                this.pelatut.add(new Ottelu(pelatunparit, pisteet));
            }
        }
        
        
    }
    
    
    /**
     * 
     * @param pisteet ottelun pisteet
     * @return true jos pisteet on merkattu oikein, muuten false
     */
    private boolean tarkistapisteet(int[] pisteet) {
        int summa = 0;
        for (int i = 0; i < pisteet.length; i++) {
            if (pisteet[i] < 0) return false;
            summa = summa + pisteet[i];
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
    public static List<Ottelu> syotaTulokset(Stage modalitystage, List<Ottelu> oletus) {
        return ModalController.showModal(ParitSkabatController.class.getResource("OttelutTulokset.fxml"), "Ottelut ja tulokset", modalitystage, oletus);
    }

    
}
