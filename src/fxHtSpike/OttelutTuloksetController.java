/**
 * 
 */
package fxHtSpike;

import java.util.ArrayList;
import java.util.Arrays;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.StringGrid;
import fi.jyu.mit.ohj2.Mjonot;
import htSpike.Ottelu;
import htSpike.Pelaaja;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
        tallennaOttelut();
        //TODO: tallenna tiedostoon metodi 
        Stage stage = (Stage) tallennaJaLopetaButton.getScene().getWindow();
        //TODO: Tarkista onko sulkeminen tehty oikein
        stage.close();
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
            String pari1 = this.parit.get(i).getNimi() + "&" + this.parit.get(i + 1).getNimi();
            String pari2 = this.parit.get(i + 2).getNimi() + "&" + this.parit.get(i + 3).getNimi();
            String[] rivi = {pari1 + "  VS  " + pari2, "","",""};
            gridOttelut.add(rivi);
        }
       
        gridOttelut.setColumnWidth(0, 300);
        gridOttelut.setColumnWidth(1,50);
        gridOttelut.setColumnWidth(2,50);
        gridOttelut.setColumnWidth(3,50);      
    }
    
    
    private void tallennaOttelut() {
      //  int pelit = this.parit.size()/2; TODO: algoritmi pelien muodostamiseen
        int rivit = this.parit.size()/4;     // pelit * ( pelit - 1 ) / 2;
        String[] erat = new String[3];
        int[] matsinParit = new int[4];
        
        for (int i = 0; i < rivit; i++) {
            matsinParit = kasitteleParit(gridOttelut.get(i, 0));
            for (int j = 1; j < 3; j++) {
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
        }
        
    }
    
    
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
            if (era == null || era == "") {
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
    
    
    //TODO: Korjaa alla oleva testi
    /**
     * @param pelaajat pelaama olleet pelaajat
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
}
