package htSpike;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;

import fi.jyu.mit.ohj2.Mjonot;



/**
 * @author waltt
 * @version 13.4.2023
 * Luokka yksittäisen ottelun luomista ja käsittelyä varten
 */
public class Ottelu {
    private int[] parit = new int[4];
    private int[] pisteet = new int[6];
    
    
    /**
     * @param parit pelaajat ottelussa
     * @param pisteet ottelun pisteet
     */
    public Ottelu(int[] parit, int[] pisteet) {
        for (int i = 0; i < parit.length && i < this.parit.length; i++) {
            this.parit[i] = parit[i];
        }               
        for (int i = 0; i < pisteet.length && i < this.pisteet.length; i++) {
            this.pisteet[i] = pisteet[i];
        }       
    }
    
    
    /**
     * Parametriton muodostaja
     */
    public Ottelu() {
        Random rand = new Random();
        this.parit[0] = 1;
        this.parit[1] = 2;
        this.parit[2] = 3;
        this.parit[3] = 4;
        
        if (rand.nextBoolean()) {
            this.pisteet[0] = 21;
            this.pisteet[1] = rand.nextInt(20);
            this.pisteet[2] = rand.nextInt(20);
            this.pisteet[3] = 21;
            this.pisteet[4] = 21;
            this.pisteet[5] = rand.nextInt(20);
        }
        else {
            this.pisteet[0] = rand.nextInt(20);
            this.pisteet[1] = 21;
            this.pisteet[2] = 21;
            this.pisteet[3] = rand.nextInt(20);
            this.pisteet[4] = rand.nextInt(20);
            this.pisteet[5] = 21;
        }
        
        
    }
    
    
    /**
     * @param pelaaja1 pelaaja1 id
     * @param pelaaja2 pelaaja2 id
     * @param pelaaja3 pelaaja3 id
     * @param pelaaja4 pelaaja4 id
     * @param era1pari1 ekan erän parin 1 pisteet
     * @param era1pari2 ekan erän parin 2 pisteet
     * @param era2pari1 tokan erän parin 1 pisteet
     * @param era2pari2 tokan erän parin 2 pisteet
     * @param era3pari1 kolmannen erän parin 1 pisteet
     * @param era3pari2 kolmannen erän parin 2 pisteet
     */
    public Ottelu(int pelaaja1, int pelaaja2, int pelaaja3, int pelaaja4, int era1pari1,
            int era1pari2, int era2pari1, int era2pari2, int era3pari1, int era3pari2) {
        this.parit[0] = pelaaja1;
        this.parit[1] = pelaaja2;
        this.parit[2] = pelaaja3;
        this.parit[3] = pelaaja4;
        this.pisteet[0] = era1pari1;
        this.pisteet[1] = era1pari2;
        this.pisteet[2] = era2pari1;
        this.pisteet[3] = era2pari2;
        this.pisteet[4] = era3pari1;
        this.pisteet[5] = era3pari2;
    }
    
    
    /**
     * @param pelaaja1 pelaaja1 id
     * @param pelaaja2 pelaaja2 id
     * @param pelaaja3 pelaaja3 id
     * @param pelaaja4 pelaaja4 id
     * @param era1pari1 ekan erän parin 1 pisteet
     * @param era1pari2 ekan erän parin 2 pisteet
     * @param era2pari1 tokan erän parin 1 pisteet
     * @param era2pari2 tokan erän parin 2 pisteet
     */
    public Ottelu(int pelaaja1, int pelaaja2, int pelaaja3, int pelaaja4, int era1pari1,
            int era1pari2, int era2pari1, int era2pari2) {
        
        this(pelaaja1, pelaaja2, pelaaja3, pelaaja4, era1pari1,
                era1pari2, era2pari1, era2pari2, 0, 0); 
    }
    
    
    /**
     * @param pelaaja1 pelaaja1 id
     * @param pelaaja2 pelaaja2 id
     * @param pelaaja3 pelaaja3 id
     * @param pelaaja4 pelaaja4 id
     * @param era1pari1 ekan erän parin 1 pisteet
     * @param era1pari2 ekan erän parin 2 pisteet
     */
    public Ottelu(int pelaaja1, int pelaaja2, int pelaaja3, int pelaaja4, int era1pari1,
            int era1pari2) {
        
        this(pelaaja1, pelaaja2, pelaaja3, pelaaja4, era1pari1,
                era1pari2, 0, 0, 0, 0); 
    }
    
    /**
     * Aliohjelma palauttaa ottelun merkkijonona
     * @example
     * <pre name="test">
     * #STATICIMPORT
     * #import htSpike.Pelaaja;
     * Pelaaja simo1 = new Pelaaja();
     * Pelaaja simo2 = new Pelaaja();
     * Pelaaja simo3 = new Pelaaja();
     * Pelaaja simo4 = new Pelaaja();
     * simo1.luojotain();
     * simo2.luojotain();
     * simo3.luojotain();
     * simo4.luojotain();
     * int[] pelaajat = {simo1.getId(), simo2.getId(), simo3.getId(), simo4.getId()};
     * int[] tulos = {21,15,15,21,21,15};
     * Ottelu ottelu = new Ottelu(pelaajat, tulos);
     * ottelu.toString() === "1|2|3|4|21|15|15|21|21|15";
     * </pre>
     */
    @Override
    public String toString() {
        return this.parit[0] + "|" + this.parit[1] + "|" + this.parit[2] + "|" + this.parit[3] + "|" +
               this.pisteet[0] + "|" + this.pisteet[1] + "|" + this.pisteet[2] + "|" + this.pisteet[3] + "|" +
               this.pisteet[4] + "|" + this.pisteet[5];
    }
    
    
    /**
     * @return 1 jos pari 1 voitti, 2 jos pari2 voitti, muuten -1
     * @example
     * <pre name="test">
     * #STATICIMPORT
     * #import htSpike.Ottelu;
     * int[] parit = {1,2,3,4};
     * int[] pisteet = {21,15,14,21,21,16};
     * Ottelu ottelu = new Ottelu(parit, pisteet);
     * ottelu.voittaja() === 1;
     * int[] parit2 = {1,2,3,4};
     * int[] pisteet2 = {15,21,21,15,13,21};
     * Ottelu ottelu2 = new Ottelu(parit2, pisteet2);
     * ottelu2.voittaja() === 2;
     * int[] parit3 = {1,2,3,4};
     * int[] pisteet3 = {21,15,14,21,0,0};
     * Ottelu ottelu3 = new Ottelu(parit3, pisteet3);
     * ottelu3.voittaja() === -1;
     * </pre>
     */
    public int voittaja() {
        int pari1erat = 0;
        int pari2erat = 0;
        if (this.pisteet[0] < this.pisteet[1]) pari2erat++;
        if (this.pisteet[0] > this.pisteet[1]) pari1erat++;
        if (this.pisteet[2] < this.pisteet[3]) pari2erat++;
        if (this.pisteet[2] > this.pisteet[3]) pari1erat++;
        if (this.pisteet[4] < this.pisteet[5]) pari2erat++;
        if (this.pisteet[4] > this.pisteet[5]) pari1erat++;
        if (pari1erat < pari2erat) return 2;
        if (pari1erat > pari2erat) return 1;
        return -1;
    }
    
    
    /**
     * @param os tietovirta johon tulostetaan
     * @example
     * <pre name="test">
     * #import fi.jyu.mit.ohj2.Suuntaaja;
     * Suuntaaja.StringOutput so = new Suuntaaja.StringOutput();
     * int[] pelaajat = {1,2,3,4};
     * int[] tulos = {21,13,12,21,21,15};
     * Ottelu ottelu = new Ottelu(pelaajat, tulos);
     * ottelu.tulosta(System.out);
     * String tulostus = "1&2 VS 3&4\n" +
     *                "Erä 1: 21 - 13\n" +
     *                "Erä 2: 12 - 21\n" +
     *                "Erä 3: 21 - 15\n" +
     *                "Pari 1 voitti!\n";
     * so.ero(tulostus) === null;
     * so.palauta();
     * </pre>
     */
    public void tulosta(PrintStream os) {
        PrintStream out = new PrintStream(os);
        out.println(parit[0] + "&" + parit[1] + " VS " + parit[2] + "&" + parit[3]);
        out.println("Erä 1: " + this.pisteet[0] + " - " + this.pisteet[1]);
        if (this.pisteet[2] > 0 || this.pisteet[3] > 0) {
            out.println("Erä 2: " + this.pisteet[2] + " - " + this.pisteet[3]);
        }
        if (this.pisteet[4] > 0 || this.pisteet[5] > 0) {
            out.println("Erä 3: " + this.pisteet[4] + " - " + this.pisteet[5]);
        }
        
        if (this.voittaja() == 1) out.println("Pari 1 voitti!\n");
        if (this.voittaja() == 2) out.println("Pari 2 voitti!\n");
        if (this.voittaja() < 0) out.println("Tasapeli\n");        
    }
    
    
    /**
     * @param indeksi mikä pelaajan indeksi on taulukossa
     * @return pelaajan iidee
     */
    public int getPelaaja(int indeksi) {
        return parit[indeksi - 1];
    }
    
    
    /**
     * @param s merkkijono pelaajan tiedoista
     * @example
     * <pre name="test">
     * #STATICIMPORT
     * #import htSpike.*;
     * String s = "1|2|3|4|21|15|21|15|0|0";
     * Ottelu ottelu = new Ottelu();
     * ottelu.parse(s);
     * ottelu.voittaja() === 1;
     * ottelu.toString() === "1|2|3|4|21|15|21|15|0|0"
     * </pre>
     */
    public void parse(String s) {
        StringBuilder rivi = new StringBuilder(s);
        for (int i = 0; i < this.parit.length; i++) {
            this.parit[i] = Mjonot.erota(rivi, '|', this.parit[i]);
        }
        for (int i = 0; i < this.pisteet.length; i++) {
            this.pisteet[i] = Mjonot.erota(rivi, '|', this.pisteet[i]);
        }
    }

    
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Pelaaja simo1 = new Pelaaja();
        Pelaaja simo2 = new Pelaaja();
        Pelaaja simo3 = new Pelaaja();
        Pelaaja simo4 = new Pelaaja();
        simo1.luojotain();
        simo2.luojotain();
        simo3.luojotain();
        simo4.luojotain();
        Ottelu ottelu1 = new Ottelu(simo1.getId(), simo2.getId(), simo3.getId(), simo4.getId(),
                21, 15);
        Ottelu ottelu2 = new Ottelu(simo1.getId(), simo2.getId(), simo3.getId(), simo4.getId(),
                21, 15, 21, 17);
        ottelu1.tulosta(System.out);
        ottelu2.tulosta(System.out);
        int[] pelaajat = {simo1.getId(), simo2.getId(), simo3.getId(), simo4.getId()};
        int[] tulos = {21,13,12,21,21,15};
        Ottelu ottelu3 = new Ottelu(pelaajat, tulos);
        ottelu3.tulosta(System.out);
        System.out.println(Arrays.toString(ottelu3.pisteet));
    
    }

}
