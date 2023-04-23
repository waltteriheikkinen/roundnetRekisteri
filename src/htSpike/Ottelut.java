/**
 * 
 */
package htSpike;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
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
     * @param hakemisto mistä tiedosto löytyy
     * @throws FileNotFoundException poikkeus jos tiedostoa ei löydy
     */
    public void lueTiedostosta(String hakemisto) throws FileNotFoundException {
        String tiedostonimi = hakemisto + "/ottelut.dat";
        File tiedosto = new File(tiedostonimi);
        
        try (Scanner fi = new Scanner(new FileInputStream(tiedosto))){
            while (fi.hasNext()) {
                String s = fi.nextLine();
                if (s == null || "".equals(s) || s.charAt(0) == '#') continue;
                Ottelu ottelu = new Ottelu();
                ottelu.parse(s);
                this.ottelulista.add(ottelu);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage() + "Tiedosto ei aukea!");
        }
    }
    
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
        if (this.ottelulista.size() > 10) this.ottelulista.remove(10);
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
        
        
        try {
            ottelut.lueTiedostosta("tallennustestit");
        } catch(FileNotFoundException e) {
            System.err.println(e.getMessage());
            //throw new FileNotFoundException(e.getMessage() + "Tiedostoa ei löydy!");
        }
        
        
        int[] pelaajat = {1,0,0,0};
        int[] tulos = {1,2,3,4,5,6};
        Ottelu peli1 = new Ottelu(pelaajat, tulos);
        ottelut.lisaa(peli1);
        
        
        pelaajat[0] = 2;
        Ottelu peli2 = new Ottelu(pelaajat, tulos);
        ottelut.lisaa(peli2);
        
        pelaajat[0] = 3;
        Ottelu peli3 = new Ottelu(pelaajat, tulos);
        ottelut.lisaa(peli3);
        
        for (Ottelu peli : ottelut.ottelulista) {
            peli.tulosta(System.out);
        }
        
        ottelut.rankkaa();
        System.out.println(ottelut.ranking.toString());
        ottelut.tallenna("tallennustestit");
        for (Ottelu ottelu : ottelut.ottelulista) {
            System.out.println(ottelu.toString());
        }
        System.out.println();
    
    }

    

}
