/**
 * 
 */
package htSpike;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * @author waltt
 * @version 13.4.2023
 *
 */
public class Taso {
    private int tid;
    private String tasoteksti;
    
    
    /**
     * @param tid tason id numero
     * @param tasoteksti tason selitys
     */
    public Taso(int tid, String tasoteksti) {
        this.tid = tid;
        this.tasoteksti = tasoteksti;
    }
    
    
    /**
     * @return tason id
     */
    public int getTid() {
        return this.tid;
    }
    
    
    
    /**
     * @return tason tekstiselityksen
     */
    public String getTasoteksti() {
        return this.tasoteksti;
    }
    
    /**
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        PrintStream out = new PrintStream(os);
        out.println("Taso id: " + this.getTid() + "\nSelitys: " + this.getTasoteksti());
    }

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Taso yksi = new Taso(1, "Aloittelija");
        yksi.tulosta(System.out);
    
    }

}
