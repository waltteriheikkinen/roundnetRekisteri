package htSpike;

import java.util.Random;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author Waltteri
 * @version 12.3.2023
 * Pelaaja luokka pelaajien luomista sekä yksittäisen pelaajan tietojen muokkausta varten
 */
public class Pelaaja {
    private int id  = 0;
    private int tid = 1;
    private int ika = 0;
    private String nimi = "";
    private String katisyys = "";
    private String sukupuoli = "";
    private static int seuraavanro = 1;
    

    
    /**
     * @return pelaajan tiedot merkkijonona
     */
    @Override
    public String toString() {
        return this.id + "|" + this.nimi + "|" + this.tid + "|" + this.ika + "|" +
               this.katisyys + "|" + this.sukupuoli;
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
     * Asetetaan pelaajan taso id
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
     * @return pelaajan iän
     */
    public int getIka() {
        return this.ika;
    }
    
    
    /**
     * luo satunnaiset tiedot pelaajan testaamista varten
     */
    public void luojotain() {
        Random rand = new Random();
        this.id = seuraavanro++;
        this.tid = rand.nextInt(5) + 1;
        this.ika = rand.nextInt(60);
        this.nimi = "Heppu" + this.id;
        if (rand.nextBoolean()) this.katisyys = "Oikea";
        else this.katisyys = "Vasen";
        int arpa = rand.nextInt(11);
        if (arpa == 0) this.sukupuoli = "Ei tiedossa";
        if (1 <= arpa && arpa <= 5) this.sukupuoli = "Mies";
        if (arpa > 5) this.sukupuoli = "Nainen";
    }
    
    
    /**
     * @return pelaajan id numeron
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * @return tason id numero
     */
    public int getTaso() {
        return this.tid;
    }
    
    
    /**
     * @return pelaajan suokupuolen
     */
    public String getSukupuoli() {
        return this.sukupuoli;
    }
    
    
    /**
     * @return pelaajan kätisyyden
     */
    public String getKatisyys() {
        return this.katisyys;
    }
    
    
    /**
     * @param s merkkijono pelaajan tiedoista
     * @example
     * <pre name="test">
     * #STATICIMPORT
     * #import htSpike.*;
     * Pelaajat pelaajat = new Pelaajat();
     * String s = "3|Heppu1|4|38|Oikea|Mies";
     * Pelaaja pelaaja = new Pelaaja();
     * pelaaja.parse(s);
     * pelaaja.getId() === 3;
     * pelaajat.lisaa(pelaaja);
     * int n = pelaaja.getId();
     * pelaaja.parse("" + (n + 20));
     * pelaaja.luojotain();
     * pelaaja.getId() === n + 20 + 1;
     * </pre>
     */
    public void parse(String s) {
        StringBuilder rivi = new StringBuilder(s);
        setId(Mjonot.erota(rivi, '|', this.getId()));
        this.nimi = Mjonot.erota(rivi, '|', this.nimi);
        this.tid = Mjonot.erota(rivi, '|', this.tid);
        this.ika = Mjonot.erota(rivi, '|', this.ika);
        this.katisyys = Mjonot.erota(rivi, '|', this.katisyys);
        this.sukupuoli = Mjonot.erota(rivi, '|', this.sukupuoli);
    }
    
    
    /**
     * @param id pelaajan id
     */
    public void setId(int id) {
        this.id = id;
        if (seuraavanro <= id) seuraavanro = id + 1;
    }
    
    
    /**
     * @param s katisyys
     */
    public void setKatisyys(String s) {
        this.katisyys = s;
    }
    
    
    /**
     * @param s sukupuoli
     */
    public void setSukuPuoli(String s) {
        this.sukupuoli = s;
    }
    
    
    /**
     * ohjelma antaa pelaajalle jäsennumeron
     */
    public void rekisteroi() {
        this.id = seuraavanro;
        
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
        siili.setKatisyys("Oikea");
        siili.setSukuPuoli("Mies");
        siili.tulosta();
        
        siili.luojotain();
        siili.tulosta();
        System.out.println(siili);
        

    }

    

    

    

}
