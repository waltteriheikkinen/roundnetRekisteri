/**
 * 
 */
package fxHtSpike;

/**
 * @author waltt
 * @version 5.4.2023
 *
 */
public class TiistaiSpike {
    private final Pelaajat pelaajat = new Pelaajat();
    
    
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
    
    }

}
