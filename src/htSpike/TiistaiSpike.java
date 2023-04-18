/**
 * 
 */
package htSpike;

import java.util.TreeMap;

/**
 * @author waltt
 * @version 5.4.2023
 *
 */
public class TiistaiSpike {
    private final Pelaajat pelaajat = new Pelaajat();
    private final Tasot tasot = new Tasot();
    private final Ottelut ottelut = new Ottelut();
    
    
    /**
     * Ohjelma muodostaa ranking listan otteluiden perusteella
     */
    public void rankkaa() {
        this.ottelut.rankkaa();
    }
    
    
    /**
     * @return palauttaa otteluiden ranking listan
     */
    public TreeMap<Double, Integer> getRanking(){
        return this.ottelut.getRanking();
    }
    
    /**
     * @param ottelu lisättävä ottelu
     */
    public void lisaa(Ottelu ottelu) {
        this.ottelut.lisaa(ottelu);
    }
    
    
    /**
     * @param id tason id
     * @return palauttaa ideetä vastaavan tason
     */
    public Taso getTaso(int id) {
        return tasot.getTaso(id);
    }
    
    /**
     * @param pelaaja lisättävä pelaaja
     */
    public void lisaa(Pelaaja pelaaja) {
        pelaajat.lisaa(pelaaja);
    }
    
    
    /**
     * @return pelaajien lukumäärän
     */
    public int getPelaajia() {
        return pelaajat.getLkm();
    }
    
    
    /**
     * @param i indeksi pelaajalle taulukossa
     * @return pelaajan
     */
    public Pelaaja annaPelaaja(int i) {
        return pelaajat.annaPelaaja(i);
    }
    
    
    /**
     * @param id pelaajan id numero
     * @return palauttaa pelaajan id numeron perusteella
     */
    public Pelaaja getPelaaja(int id) {
        return this.pelaajat.getPelaaja(id);
    }

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        TiistaiSpike tiistaispike = new TiistaiSpike();
        
        Pelaaja simo1 = new Pelaaja();
        Pelaaja simo2 = new Pelaaja();
        simo1.luojotain();
        simo2.luojotain();
        tiistaispike.lisaa(simo1);
        tiistaispike.lisaa(simo2);
        
        for (int i = 0; i < tiistaispike.getPelaajia(); i++) {
            Pelaaja pelaaja = tiistaispike.annaPelaaja(i);
            pelaaja.tulosta();;
        }
        
        tiistaispike.rankkaa();
        System.out.println(tiistaispike.getRanking().toString());
        int id = tiistaispike.annaPelaaja(0).getId();
        Pelaaja testi = tiistaispike.getPelaaja(id);
        System.out.println(testi.getNimi());
    
    }

}
