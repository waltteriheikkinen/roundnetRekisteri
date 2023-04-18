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
    private Pelaaja[] lista = new Pelaaja[10];
    private int lkm = 0;

    
    /**
     * @param pelaaja taulukkoon lisättävä pelaaja
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
     * tulostaa pelaajat listaan
     * @param pelaaja tulostettava pelaaja
     */
    public void tulosta(Pelaaja pelaaja) {
         pelaaja.tulosta();
        
    }
    
    
    /**
     * @param i pelaajan indeksi taulukossa
     * @return palauttaa pelaajan
     */
    public Pelaaja annaPelaaja(int i) {
        return lista[i];
    }
    
    /**
     * @param id pelaajan id
     * @return palauttaa pelaajan id numeron perusteella
     */
    public Pelaaja getPelaaja(int id) {
        for (int i = 0; i < this.lkm; i++) {
            if (this.lista[i].getId() == id) return lista[i];
        }
        return null;
        
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
        for (int i = 0; i < pelaajat.getLkm(); i++) {
            pelaajat.lista[i].tulosta();
        }
        Pelaaja uusi = pelaajat.getPelaaja(4);
        uusi.tulosta();
        
    
    }

}
