/**
 * 
 */
package fxHtSpike;

/**
 * @author waltt
 * @version 12.3.2023
 *
 */
public class Pelaajat {
    private Pelaaja[] pelaajat = new Pelaaja[10];
    private int lkm = 0;

    
    /**
     * @param pelaaja taulukkoon lisättävä pelaaja
     */
    public void lisaa(Pelaaja pelaaja) { //TODO: Lisää tähän joku exception
        this.pelaajat[this.getLkm()] = pelaaja;
        this.lkm++;
    }
    
    
    
    /**
     * @return pelaajien lukumäärän
     */
    public int getLkm() {
        return this.lkm;
    }
    
    
    /**
     * tulostaa pelaajat listaan
     */
    public void tulosta() {
        for (int i = 0; i < this.lkm; i++) {
            this.pelaajat[i].tulosta();
        }
    }
    
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
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
        pelaajat.tulosta();
        
    
    }

}
