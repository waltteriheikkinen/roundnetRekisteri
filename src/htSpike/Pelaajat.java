/**
 * 
 */
package htSpike;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * @author waltt
 * @version 12.3.2023
 *
 */
public class Pelaajat {
    private Pelaaja[] lista = new Pelaaja[10];
    private int lkm = 0;

    
    /**
     * @param hakemisto mihin tallennetaan
     * @throws FileNotFoundException poikkeus
     * Tiedoston muoto:
     * 1|Heppu1|4|38|Oikea|Mies
     * @example
     * <pre name="test">
     * 
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
     * pelaajat.getPelaaja(1);
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
     * @param args ei käytössä
     * @throws FileNotFoundException poikkeus jos tiedostoa ei ole
     */
    public static void main(String[] args) throws FileNotFoundException {
        Pelaajat pelaajat = new Pelaajat();
        Pelaaja simo1 = new Pelaaja();
        Pelaaja simo2 = new Pelaaja();
        Pelaaja simo3 = new Pelaaja();
        simo1.luojotain();
        simo2.luojotain();
        simo3.luojotain();
        simo1.tulosta();
        pelaajat.lisaa(simo1);
        pelaajat.lisaa(simo2);
        pelaajat.lisaa(simo3);
        for (int i = 0; i < pelaajat.getLkm(); i++) {
            pelaajat.lista[i].tulosta();
        }
        Pelaaja uusi = pelaajat.getPelaaja(3);
        uusi.tulosta();
        System.out.println("================================");
        pelaajat.tulosta();
        pelaajat.tallenna("tallennustestit");
    
    }

}
