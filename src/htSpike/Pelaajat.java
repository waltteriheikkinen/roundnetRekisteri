/**
 * 
 */
package htSpike;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author waltt
 * @version 12.3.2023
 *
 */
public class Pelaajat {
    private Pelaaja[] lista = new Pelaaja[10];
    private int lkm = 0;

    
    //TODO: 1 testit tähän
    /**
     * @param hakemisto mihin tallennetaan
     * @throws FileNotFoundException poikkeus
     * Tiedoston muoto:
     * 1|Heppu1|4|38|Oikea|Mies
     * @example
     * <pre name="test">
     * #THROWS IOException
     * #import java.io.IOException;
     * #import fi.jyu.mit.ohj2.VertaaTiedosto;
     * Pelaaja pelaaja1 = new Pelaaja();
     * pelaaja1.parse("1|Heppu1|1|1|Oikea|Nainen");
     * Pelaaja pelaaja2 = new Pelaaja();
     * pelaaja2.parse(""2|Heppu2|2|2|Vasen|Nainen");
     * Pelaaja pelaaja3 = new Pelaaja();
     * pelaaja3.parse("3|Heppu3|3|3|Oikea|Mies");
     * Pelaajat pelaajat = new Pelaajat();
     * pelaajat.lisaa(pelaaja1);
     * pelaajat.lisaa(pelaaja2);
     * pelaajat.lisaa(pelaaja3);
     * 
     *  VertaaTiedosto.kirjoitaTiedosto("pelaajatallennus.txt",
     *      "1|Heppu1|1|1|Oikea|Nainen\n"+
     *      "2|Heppu2|2|2|Vasen|Nainen\n"+
     *      "3|Heppu3|3|3|Oikea|Mies\n");
     *  String tulos =
     *      "33 hiljaa 1 hiipii\n"+
     *      "36 1 3 5 55\n";
     *  VertaaTiedosto.tuhoaTiedosto("pelaajatallennus.txt"); 
     *  main(new String[]{"hiljaa.txt","tulos.txt"});
     *  VertaaTiedosto.vertaaFileString("tulos.txt",tulos) === null;
     *  VertaaTiedosto.tuhoaTiedosto("tulos.txt");
     *  VertaaTiedosto.tuhoaTiedosto("hiljaa.txt");
     * </pre>
     */
    public void tallenna(String hakemisto) throws FileNotFoundException {
        File ftied = new File(hakemisto + "/pelaajat.dat");
        try (PrintStream fo = new PrintStream(new FileOutputStream(ftied, false))){
            for (int i = 0; i < this.lkm; i++) {
                Pelaaja pelaaja = this.annaPelaaja(i);
                fo.println(pelaaja.toString());
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Tiedosto " + ftied.getAbsolutePath() + " ei aukea!\n" + e.getMessage());
        }
    }
    
    
    /**
     * @param pelaaja taulukkoon lisättävä pelaaja
     * @example
     * <pre name="test">
     * #STATICIMPORT
     * #CLASSIMPORT
     * Pelaajat pelaajat = new Pelaajat();
     * Pelaaja pelaaja1 = new Pelaaja();
     * pelaaja1.luojotain();
     * Pelaaja pelaaja2 = new Pelaaja();
     * pelaaja2.luojotain();
     * pelaajat.getLkm() === 0;
     * pelaajat.lisaa(pelaaja1);
     * pelaajat.annaPelaaja(0) === pelaaja1;
     * pelaajat.getLkm() === 1;
     * pelaajat.lisaa(pelaaja2);
     * pelaajat.annaPelaaja(1) === pelaaja2;
     * pelaajat.getLkm() === 2;
     * </pre>
     */
    public void lisaa(Pelaaja pelaaja) { //Todo joku exception
        if (this.lista.length <= this.lkm) {
            Pelaaja[] vanhatpelaajat = this.lista;
            lista = new Pelaaja[lkm + 5]; 
            for (int i = 0; i < vanhatpelaajat.length; i++) {
                this.lista[i] = vanhatpelaajat[i];
            }
        }                
        this.lista[this.getLkm()] = pelaaja;
        this.lkm++;       
    }
    
    
    
    /**
     * @return pelaajien lukumäärän
     */
    public int getLkm() {
        return this.lkm;
    }
    
    
    /**
     * tulostaa kaikki pelaajat
     */
    public void tulosta() {
         for (int i = 0; i < lkm; i++) {
             this.lista[i].tulosta();
         }
        
    }
    
    
    /**
     * @param i pelaajan indeksi taulukossa
     * @return palauttaa pelaajan indeksin mukaan
     */
    public Pelaaja annaPelaaja(int i) {
        return lista[i];
    }
    
    /**
     * @param id pelaajan id
     * @return palauttaa pelaajan id numeron perusteella
     * @example
     * <pre name="test">
     * #STATICIMPORT
     * #CLASSIMPORT
     * Pelaajat pelaajat = new Pelaajat();
     * Pelaaja simo1 = new Pelaaja();
     * Pelaaja simo2 = new Pelaaja();
     * Pelaaja simo3 = new Pelaaja();
     * simo1.luojotain();
     * simo2.luojotain();
     * simo3.luojotain();
     * pelaajat.lisaa(simo1);
     * pelaajat.lisaa(simo2);
     * pelaajat.lisaa(simo3);
     * pelaajat.tulosta();
     * pelaajat.getPelaaja(1) === simo1;
     * pelaajat.getPelaaja(5) === null;
     * 
     * </pre>
     */
    public Pelaaja getPelaaja(int id) {
        for (int i = 0; i < this.lkm; i++) {
            if (this.lista[i].getId() == id) return lista[i];
        }
        return null;
        
    }
    
    
    /**
     * @param hakemisto mistä tiedosto löytyy
     * @throws FileNotFoundException poikkeus jos tiedostoa ei löydy
     */
    public void lueTiedostosta(String hakemisto) throws FileNotFoundException {
        String tiedostonimi = hakemisto + "/pelaajat.dat";
        File tiedosto = new File(tiedostonimi);
        
        try (Scanner fi = new Scanner(new FileInputStream(tiedosto))){
            while (fi.hasNext()) {
                String s = fi.nextLine();
                if (s == null || "".equals(s) || s.charAt(0) == '#') continue;
                Pelaaja pelaaja = new Pelaaja();
                pelaaja.parse(s);
                this.lisaa(pelaaja);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage() + "Tiedosto ei aukea!");
        }
        
    }
    
    
    /**
     * @param poistettava pelaaja joka poistetaan
     * @example
     * <pre name="test">
     * #STATICIMPORT
     * #import htSpike.*;
     * Pelaajat pelaajat = new Pelaajat();
     * Pelaaja simo1 = new Pelaaja();
     * Pelaaja simo2 = new Pelaaja();
     * Pelaaja simo3 = new Pelaaja();
     * simo1.luojotain();
     * simo2.luojotain();
     * simo3.luojotain();
     * pelaajat.lisaa(simo1);
     * pelaajat.lisaa(simo2);
     * pelaajat.lisaa(simo3);
     * int id = simo2.getId();
     * pelaajat.getLkm() === 3;
     * pelaajat.poista(simo2);
     * pelaajat.getLkm() === 2;
     * pelaajat.annaPelaaja(1).getId() === 3;
     * pelaajat.getPelaaja(id) === null;
     * </pre>
     */
    public void poista(Pelaaja poistettava) {
        int indeksi = 0;
        for (int i = 0; i < this.lkm; i++) {
            if (this.lista[i] == poistettava) {
                indeksi = i;
                break;
            }
        }
        Pelaaja[] uusi = new Pelaaja[this.lkm - 1];
        this.lista[indeksi] = this.lista[lkm - 1];
        for (int i = 0; i < lkm - 1; i++) {
            uusi[i] = this.lista[i];
        }
        this.lista = uusi;
        this.lkm--;
    }
    
       
    /**
     * @param args ei käytössä
     * @throws FileNotFoundException poikkeus jos tiedostoa ei ole
     */
    public static void main(String[] args) throws FileNotFoundException {
        Pelaajat pelaajat = new Pelaajat();
        
        try {
            pelaajat.lueTiedostosta("tallennustestit");
        } catch(FileNotFoundException e) {
            System.err.println(e.getMessage());
            //throw new FileNotFoundException(e.getMessage() + "Tiedostoa ei löydy!");
        }
        
        Pelaaja simo1 = new Pelaaja();
        Pelaaja simo2 = new Pelaaja();
        Pelaaja simo3 = new Pelaaja();
        simo1.luojotain();
        simo2.luojotain();
        simo3.luojotain();
        pelaajat.lisaa(simo1);
        pelaajat.lisaa(simo2);
        pelaajat.lisaa(simo3);
        pelaajat.tulosta();
        
        System.out.println("================================");
        
        int id = pelaajat.annaPelaaja(3).getId();
        pelaajat.poista(pelaajat.getPelaaja(id));
        System.out.println(pelaajat.getPelaaja(id));
        
        System.out.println("================================");
        
        pelaajat.tulosta();
        pelaajat.tallenna("tallennustestit");
    
    }

}
