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
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author waltt
 * @version 14.4.2023
 * Luokka kaikkien otteluiden tallettamista ja käsittelyä varten
 */
public class Ottelut {
    private ArrayList<Ottelu> ottelulista = new ArrayList<Ottelu>();
    private LinkedHashMap<Integer, Double> ranking = new LinkedHashMap<Integer, Double>();
    
    
    /**
     * @param hakemisto mistä tiedosto löytyy
     * @throws FileNotFoundException poikkeus jos tiedostoa ei löydy
     * Tiedoston muoto:
     * 1|2|3|4|21|15|21|15|0|0
     * @example
     * <pre name="test">
     * #THROWS IOException
     * #import java.io.IOException;
     * #import fi.jyu.mit.ohj2.VertaaTiedosto;
     * #STATICIMPORT
     * #import htSpike.*;
     * #import java.util.ArrayList;
     * VertaaTiedosto.kirjoitaTiedosto("comtestTied/ottelut.dat",
     *      "1|2|3|4|1|0|1|0|1|0\n"+
     *      "1|2|3|4|1|0|1|0|1|1\n"+
     *      "1|2|3|4|1|0|1|0|1|2\n");
     * 
     *  Ottelut ottelut = new Ottelut();
     *  ottelut.lueTiedostosta("comtestTied");
     *  ArrayList<Ottelu> lista = ottelut.getOtteluLista();
     *  lista.get(0).toString() === "1|2|3|4|1|0|1|0|1|0"
     *  lista.get(1).toString() === "1|2|3|4|1|0|1|0|1|1"
     *  lista.get(2).toString() === "1|2|3|4|1|0|1|0|1|2"
     *  
     *  VertaaTiedosto.tuhoaTiedosto("comtestTied/ottelut.dat");
     * </pre>
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
     * #THROWS IOException
     * #import java.io.IOException;
     * #import fi.jyu.mit.ohj2.VertaaTiedosto;
     * #STATICIMPORT
     * #import htSpike.*;
     * Ottelu ottelu1 = new Ottelu(new int[]{1,2,3,4}, new int[]{1,0,1,0,1,0});
     * Ottelu ottelu2 = new Ottelu(new int[]{1,2,3,4}, new int[]{1,0,1,0,1,0});
     * Ottelu ottelu3 = new Ottelu(new int[]{1,2,3,4}, new int[]{1,0,1,0,1,0});
     * Ottelut ottelut = new Ottelut();
     * ottelut.lisaa(ottelu1);
     * ottelut.lisaa(ottelu2);
     * ottelut.lisaa(ottelu3);
     * 
     *  String tulos =
     *      "1|2|3|4|1|0|1|0|1|0\n"+
     *      "1|2|3|4|1|0|1|0|1|0\n"+
     *      "1|2|3|4|1|0|1|0|1|0\n";
     *  ottelut.tallenna("comtestTied");
     *  VertaaTiedosto.vertaaFileString("comtestTied/ottelut.dat", tulos) === null;
     *  VertaaTiedosto.tuhoaTiedosto("comtestTied/ottelut.dat");
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
    public ArrayList<Ottelu> getOtteluLista() {
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
     * ottelut.getOtteluLista().size() === 0;
     * ottelut.lisaa(peli1);
     * ottelut.lisaa(peli2);
     * ottelut.lisaa(peli3);
     * Ottelu eka = ottelut.getOtteluLista().get(0);
     * ottelut.getOtteluLista().size() === 3;
     * ottelut.lisaa(peli4);
     * ottelut.lisaa(peli5);
     * ottelut.getOtteluLista().size() === 5;
     * eka === ottelut.getOtteluLista().get(2);
     * </pre>
     */
    public void lisaa(Ottelu peli) {
        this.ottelulista.add(0, peli);
        if (this.ottelulista.size() > 30) this.ottelulista.remove(30);
    }
    
    
    /**
     * Aliohjelma luo ranking listan
     * TODO: 3 taulukot mielellään mapeiksi
     */
    public void rankkaa() {
        this.ranking.clear();
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
                double voitot = voitetut[i];
                double kaikki = ottelumaara[i];
                voittoratio[i] = voitot / kaikki;
            }
        }
        
        HashMap<Integer, Double> lajittelematon = new HashMap<Integer,Double>();
        for (int i = 1; i < voittoratio.length; i++) {
            if (voittoratio[i] > 0) lajittelematon.put(i, voittoratio[i]);
        }
        
        List<Map.Entry<Integer, Double>> listaRanking = new ArrayList<>(lajittelematon.entrySet());
        listaRanking.sort(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()));
        for (Map.Entry<Integer, Double> entry : listaRanking) {
            this.ranking.put(entry.getKey(), entry.getValue());
        }
    }
    
    
    /**
     * @return palauttaa otteluista muodostetun ranking listan
     */
    public LinkedHashMap<Integer, Double> getRanking(){
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
