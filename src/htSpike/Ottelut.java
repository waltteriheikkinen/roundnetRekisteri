/**
 * 
 */
package htSpike;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

/**
 * @author waltt
 * @version 14.4.2023
 * Luokka kaikkien otteluiden tallettamista ja käsittelyä varten
 */
public class Ottelut {
    private ArrayList<Ottelu> ottelulista = new ArrayList<Ottelu>();
    private TreeMap<Double, Integer> ranking = new TreeMap<Double, Integer>(Collections.reverseOrder());
    
    
    /**
     * @param hakemisto mihin tallennetaan
     * @throws FileNotFoundException poikkeus
     * Tiedoston muoto:
     * 1|2|3|4|21|15|21|15|0|0
     * @example
     * <pre name="test">
     * 
     * </pre>
     */
    public void tallenna(String hakemisto) throws FileNotFoundException {
        File ftied = new File(hakemisto + "/ottelut.dat");
        try (PrintStream fo = new PrintStream(new FileOutputStream(ftied, false))){
            for (int i = 0; i < this.ottelulista.size(); i++) {
                Ottelu ottelu = this.ottelulista.get(i);
                fo.println(ottelu.toString());
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Tiedosto " + ftied.getAbsolutePath() + " ei aukea!\n" + e.getMessage());
        }
    }
    
    
    /**
     * @return listan otteluista
     */
    public ArrayList<Ottelu> getOttelulista() {
        return this.ottelulista;
    }
    
    
    /**
     * Aliohjelma lisää ottelulistaan pelejä ja poistaa vanhimman pelin kun listan ennalta
     * määritetty koko on täynnä
     * @param peli mikä ottelu lisätään listaan
     * @example
     * <pre name="test">
     * #STATICIMPORT
     * #import htSpike.*;
     * Ottelut ottelut = new Ottelut();
     * Ottelu peli1 = new Ottelu();
     * Ottelu peli2 = new Ottelu();
     * Ottelu peli3 = new Ottelu();
     * Ottelu peli4 = new Ottelu();
     * Ottelu peli5 = new Ottelu();
     * ottelut.getOttelulista().size() === 0;
     * ottelut.lisaa(peli1);
     * ottelut.lisaa(peli2);
     * ottelut.lisaa(peli3);
     * Ottelu eka = ottelut.getOttelulista().get(0);
     * ottelut.getOttelulista().size() === 3;
     * ottelut.lisaa(peli4);
     * ottelut.lisaa(peli5);
     * ottelut.getOttelulista().size() === 3;
     * eka === ottelut.getOttelulista().get(2);
     * </pre>
     */
    public void lisaa(Ottelu peli) {
        this.ottelulista.add(0, peli);
        if (this.ottelulista.size() > 3) this.ottelulista.remove(3);
    }
    
    
    /**
     * Aliohjelma luo ranking listan
     */
    public void rankkaa() {
        //TODO: korjaa oikein, nyt vain esimerkki
        ranking.put(0.5, 1);
        ranking.put(0.9, 2);
        ranking.put(0.3, 3);
        ranking.put(0.6, 4);
        ranking.put(0.1, 5);

    }
    
    
    /**
     * @return palauttaa otteluista muodostetun ranking listan
     */
    public TreeMap<Double, Integer> getRanking(){
        return this.ranking;
    }
    

    /**
     * @param args ei käytössä
     * @throws FileNotFoundException poikkeus jos tiedostoa ei löydy
     */
    public static void main(String[] args) throws FileNotFoundException {
        Ottelut ottelut = new Ottelut();
        Ottelu peli1 = new Ottelu();
        Ottelu peli2 = new Ottelu();
        Ottelu peli3 = new Ottelu();
        ottelut.lisaa(peli1);
        ottelut.lisaa(peli2);
        ottelut.lisaa(peli3);
        
        for (Ottelu peli : ottelut.ottelulista) {
            peli.tulosta(System.out);
        }
        
        Ottelu peli4 = new Ottelu();
        Ottelu peli5 = new Ottelu();
        Ottelu peli6 = new Ottelu();        
        ottelut.lisaa(peli4);
        ottelut.lisaa(peli5);
        ottelut.lisaa(peli6);
        
        for (Ottelu peli : ottelut.ottelulista) {
            peli.tulosta(System.out);
        }
        
        ottelut.rankkaa();
        System.out.println(ottelut.ranking.toString());
        ottelut.tallenna("tallennustestit");
    
    }

    

}
