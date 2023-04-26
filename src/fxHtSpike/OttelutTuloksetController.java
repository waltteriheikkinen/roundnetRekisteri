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
    private ArrayList<Pelaaja> parit;
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
        
        /*
        int n = this.parit.size()/2;
        int rivit = n * ( n - 1 ) / 2;
        String[] erat = new String[3];
        int[] matsinParit = new int[4];
        
        for (int i = 0; i < rivit; i++) {
            matsinParit = kasitteleParit(gridOttelut.get(i, 0));
            for (int j = 1; j <= erat.length; j++) {
                if (j == 1) erat[0] = this.gridOttelut.get(i, j);
                if (j == 2) erat[1] = this.gridOttelut.get(i, j);
                if (j == 3) erat[2] = this.gridOttelut.get(i, j);
            }
            System.out.println(Arrays.toString(erat));
            int[] pisteet = kasitteleErat(erat);
            System.out.println(Arrays.toString(pisteet));
            System.out.println(Arrays.toString(matsinParit));
            Ottelu ottelu = new Ottelu(matsinParit, pisteet);
            ottelu.tulosta(System.out);
            
            */
        
    }
    
    
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
    
    
    //TODO: 3 Korjaa testi. Testitiedosto ei nää testattavaa metodia.
    /**
     * @param pelaajat pelaamassa olleet pelaajat
     * @return pelaajien id numerot
     * @example
     * <pre name="test">
     * #STATICIMPORT
     * #import htSpike.Pelaaja;
     * #import java.util.Arrays;
     * Pelaaja heppu1 = new Pelaaja();
     * Pelaaja heppu2 = new Pelaaja();
     * Pelaaja heppu3 = new Pelaaja();
     * Pelaaja heppu4 = new Pelaaja();
     * heppu1.luojotain();
     * heppu2.luojotain();
     * heppu3.luojotain();
     * heppu4.luojotain();
     * String pelaajat = "Heppu1&Heppu2 VS Heppu3&Heppu4";
     * int[] parieniideet = kasitteleParit(pelaajat);
     * Arrays.toString(parieniideet) === "[1, 2, 3, 4]"; 
     * </pre>
     */
    public int[] kasitteleParit(String pelaajat) {
        StringBuilder pelaajatbuilder = new StringBuilder(pelaajat);
        String pari1string;
        String pari2string;
        pelaajatbuilder.delete(pelaajatbuilder.indexOf(" "), pelaajatbuilder.lastIndexOf(" "));
        pari1string = pelaajatbuilder.substring(0, pelaajatbuilder.indexOf(" "));
        pari2string = pelaajatbuilder.substring(pelaajatbuilder.indexOf(" ") + 1);
        StringBuilder pari1 = new StringBuilder(pari1string);
        StringBuilder pari2 = new StringBuilder(pari2string);
        
        System.out.println(pari1);
        System.out.println(pari2);
        
        String pelaaja1 = Mjonot.erota(pari1, '&');
        String pelaaja2 = pari1.substring(pari1.indexOf("&") + 1);
        String pelaaja3 = Mjonot.erota(pari2, '&');
        String pelaaja4 = pari2.substring(pari2.indexOf("&") + 1);
        
        int[] pelaajaId = new int[4];
        for (int i = 0; i < this.parit.size(); i++) {
            if (parit.get(i).getNimi().equals(pelaaja1)) pelaajaId[0] = parit.get(i).getId();
            if (parit.get(i).getNimi().equals(pelaaja2)) pelaajaId[1] = parit.get(i).getId();
            if (parit.get(i).getNimi().equals(pelaaja3)) pelaajaId[2] = parit.get(i).getId();
            if (parit.get(i).getNimi().equals(pelaaja4)) pelaajaId[3] = parit.get(i).getId();
        }
        System.out.println(pelaaja1);
        System.out.println(pelaaja2);
        System.out.println(pelaaja3);
        System.out.println(pelaaja4);        
        return pelaajaId;
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
