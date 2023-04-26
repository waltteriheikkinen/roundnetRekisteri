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
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author waltt
 * @version 14.4.2023
 * Luokka kaikkien otteluiden tallettamista ja käsittelyä varten
 */
public class Ottelut {
    private ArrayList<Ottelu> ottelulista = new ArrayList<Ottelu>();
    private TreeMap<Integer, Double> ranking = new TreeMap<Integer, Double>();
    
    
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
     * TODO: 3 taulukot mielellään mapeiksi
     */
    public void rankkaa() {
        /*
        int[] ottelumaara = new int[this.ottelulista.size()*4];
        int[] voitetut = new int[this.ottelulista.size()*2];
        for (Ottelu ottelu : this.ottelulista) {
            for(int pelaaja : ottelu.getParit()) {
                ottelumaara[pelaaja] = ottelumaara[pelaaja] + 1;
            }            
            if (ottelu.voittaja() == 1) {
                voitetut[ottelu.getParit()[0]]++;
                voitetut[ottelu.getParit()[1]]++;
            }
            if (ottelu.voittaja() == 2) {
                voitetut[ottelu.getParit()[2]]++;
                voitetut[ottelu.getParit()[3]]++;
            }
        }
        
        double[] voittoratio = new double[this.ottelulista.size()*4];
        for (int i = 1; i < ottelumaara.length; i++) {
            if (ottelumaara[i] > 0) {
                voittoratio[i] = voitetut[i] / ottelumaara[i];
            }
        }
        for (int i = 1; i < voittoratio.length; i++) {
            if (voittoratio[i] > 0) this.ranking.put(voittoratio[i], i);
        }
        */
        
        this.ranking.put(1, 0.4 );
        this.ranking.put(2, 0.6);
        this.ranking.put(3, 0.3);
        this.ranking.put(4, 0.8);
        this.ranking.put(5, 0.95);
        this.ranking.put(6, 0.7);
        this.ranking.put(7, 0.15);
        this.ranking.put(8, 0.45);
        
    }
    
    
    /**
     * @return palauttaa otteluista muodostetun ranking listan
     */
    public TreeMap<Integer, Double> getRanking(){
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
        
        
        int[] pelaajat = {1,2,3,4};
        int[] tulos = {1,2,3,4,5,6};
        Ottelu peli1 = new Ottelu(pelaajat, tulos);
        ottelut.lisaa(peli1);

        Ottelu peli2 = new Ottelu(pelaajat, tulos);
        ottelut.lisaa(peli2);
        
        
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
