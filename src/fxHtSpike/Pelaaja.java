/**
 * 
 */
package fxHtSpike;

/**
 * @author waltt
 * @version 12.3.2023
 * Pelaaja luokka pelaajien luomista varten
 */
public class Pelaaja {
    private int id;
    private int tid = 1;
    private int ika;
    private String nimi = "";
    private String katisyys = "Oikea";
    private String sukupuoli = "";
    

    
    /**
     * @return pelaajan tiedot merkkijonona
     * @example
     * <pre name="test">
     * Pelaaja simo = new Pelaaja();
     * simo.toString() === "";
     * </pre>
     */
    @Override
    public String toString() {
        return "";
    }
    
    /**
     * tulostaa pelaajan tiedot
     */
    public void tulosta() {
        System.out.println(this.id + "\n" + this.nimi + "\n" + this.tid + "\n"
                + this.ika + "\n" + this.sukupuoli + this.katisyys + "\n");
    }
    
    
    /**
     * Asetetaan pelaajan nimi
     * @param nimi pelaajalle annettava nimi
     */
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    
    
    /**
     * Asetetaan pelaajan taso
     * @param taso pelaajan taso
     */
    public void setTid(int taso) {
        this.tid = taso;
    }
    
    
    /**
     * Asetetaan pelaajan ikä
     * @param ika pelaajan ikä
     */
    public void setIka(int ika) {
        this.ika = ika;
    }
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Pelaaja siili = new Pelaaja();
        siili.tulosta();
        siili.setNimi("Simo Siili");
        siili.setTid(5);
        siili.setIka(26);
        siili.tulosta();
        

    }

}
