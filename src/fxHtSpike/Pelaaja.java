/**
 * 
 */
package fxHtSpike;

import java.util.Random;

/**
 * @author waltt
 * @version 12.3.2023
 * Pelaaja luokka pelaajien luomista varten
 */
public class Pelaaja {
    private int id;
    private int tid = 1;
    private int ika;
    private String nimi = "";
    private String katisyys = "Oikea";
    private String sukupuoli = "Ei tiedossa";
    private static int jasennro = 1;
    

    
    /**
     * @return pelaajan tiedot merkkijonona
     * @example
     * <pre name="test">
     * Pelaaja simo = new Pelaaja();
     * simo.toString() === "";
     * </pre>
     */
    @Override
    public String toString() {
        return "";
    }
    
    /**
     * tulostaa pelaajan tiedot
     */
    public void tulosta() {
        System.out.println("Jäsen nro: " + this.id);
        System.out.println("Nimi : " + this.nimi);
        System.out.println("Taso: " + this.tid);
        System.out.println("Ikä: " + this.ika);
        System.out.println("Sukupuoli : " + this.sukupuoli);
        System.out.println("Kätisyys: " + this.katisyys + "\n");
    }
    
    
    /**
     * Asetetaan pelaajan nimi
     * @param nimi pelaajalle annettava nimi
     */
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    
    
    /**
     * Asetetaan pelaajan taso
     * @param taso pelaajan taso
     */
    public void setTid(int taso) {
        this.tid = taso;
    }
    
    
    /**
     * Asetetaan pelaajan ikä
     * @param ika pelaajan ikä
     */
    public void setIka(int ika) {
        this.ika = ika;
    }
    
    
    /**
     * @return pelaajan nimen
     */
    public String getNimi() {
        return this.nimi;
    }
    
    
    /**
     * luo satunnaiset tiedot pelaajan testaamista varten
     */
    public void luojotain() {
        Random rand = new Random();
        this.id = rand.nextInt(100);
        this.tid = rand.nextInt(6);
        this.ika = rand.nextInt(60);
        this.nimi = "Simo Siili " + rand.nextInt(100);
        if (rand.nextBoolean()) this.katisyys = "Oikea";
        else this.katisyys = "Vasen";
        int arpa = rand.nextInt(3);
        if (arpa == 0) this.sukupuoli = "Ei tiedossa";
        if (arpa == 1) this.sukupuoli = "Mies";
        if (arpa == 2) this.sukupuoli = "Nainen";
    }
    
    
    /**
     * @return pelaajan id numeron
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Pelaaja siili = new Pelaaja();
        siili.tulosta();
        
        siili.setNimi("Simo Siili");
        siili.setTid(5);
        siili.setIka(26);
        siili.tulosta();
        
        siili.luojotain();
        siili.tulosta();
        

    }

    

}
