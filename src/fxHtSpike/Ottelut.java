/**
 * 
 */
package fxHtSpike;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

/**
 * @author waltt
 * @version 14.4.2023
 *
 */
public class Ottelut {
    private ArrayList<Ottelu> ottelulista = new ArrayList<Ottelu>();
    private TreeMap<Double, Integer> ranking = new TreeMap<Double, Integer>(Collections.reverseOrder());
    
    
    
    
    
    /**
     * @param peli mikä ottelu lisätään listaan
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
     */
    public static void main(String[] args) {
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
    
    }

    

}
