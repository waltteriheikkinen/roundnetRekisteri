/**
 * 
 */
package htSpike;

import java.io.PrintStream;

/**
 * @author waltt
 * @version 13.4.2023
 *
 */
public class Tasot {
    private Taso[] tasotaulukko = {new Taso(1, "Aloittelija"), new Taso(2, "Aloittelija+"),
            new Taso(3, "Keskitaso"), new Taso(4, "Edistynyt"), new Taso(5, "Kansallinen taso"),
            new Taso(6, "Pro")};
    
    
    /**
     * @param os tietovirta mihin tulostetaan
     */
    public void tulosta(PrintStream os) {
        for (Taso t : tasotaulukko) {
            t.tulosta(os);
        }
    }
    
    /**
     * @param tid pelaajan taso id jolla tarkistetaan taso
     * @return ideetä vastaava taso
     */
    public Taso getTaso(int tid) {
        for (int i = 0; i < this.tasotaulukko.length; i++) {
            if (tid == this.tasotaulukko[i].getTid()) {
                return tasotaulukko[i];
            }
        }
        return tasotaulukko[0];
    }
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Tasot tasot = new Tasot();
        tasot.tulosta(System.out);
        Pelaaja simo = new Pelaaja();
        simo.luojotain();
        simo.tulosta();
        int simoniidee = simo.getTaso();
        Taso simontaso = tasot.getTaso(simoniidee);
        simontaso.tulosta(System.out);
        
    
    }

}
