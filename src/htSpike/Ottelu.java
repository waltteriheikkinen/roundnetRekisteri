package htSpike;
import java.io.PrintStream;
import java.util.Random;



/**
 * @author waltt
 * @version 13.4.2023
 *
 */
public class Ottelu {
    private int[] parit = new int[4];
    private int[] era1 = new int[2];
    private int[] era2 = new int[2];
    private int[] era3 = new int[2];
    
    
    /**
     * Parametriton muodostaja
     */
    public Ottelu() {
        Random rand = new Random();
        this.parit[0] = 1;
        this.parit[1] = 2;
        this.parit[2] = 3;
        this.parit[3] = 4;
        
        if (rand.nextBoolean()) {
            this.era1[0] = 21;
            this.era1[1] = rand.nextInt(20);
            this.era2[0] = rand.nextInt(20);
            this.era2[1] = 21;
            this.era3[0] = 21;
            this.era3[1] = rand.nextInt(20);
        }
        else {
            this.era1[0] = rand.nextInt(20);
            this.era1[1] = 21;
            this.era2[0] = 21;
            this.era2[1] = rand.nextInt(20);
            this.era3[0] = rand.nextInt(20);
            this.era3[1] = 21;
        }
        
        
    }
    
    
    /**
     * @param pelaaja1 pelaaja1 id
     * @param pelaaja2 pelaaja2 id
     * @param pelaaja3 pelaaja3 id
     * @param pelaaja4 pelaaja4 id
     * @param era1pari1 ekan erän parin 1 pisteet
     * @param era1pari2 ekan erän parin 2 pisteet
     * @param era2pari1 tokan erän parin 1 pisteet
     * @param era2pari2 tokan erän parin 2 pisteet
     * @param era3pari1 kolmannen erän parin 1 pisteet
     * @param era3pari2 kolmannen erän parin 2 pisteet
     */
    public Ottelu(int pelaaja1, int pelaaja2, int pelaaja3, int pelaaja4, int era1pari1,
            int era1pari2, int era2pari1, int era2pari2, int era3pari1, int era3pari2) {
        this.parit[0] = pelaaja1;
        this.parit[1] = pelaaja2;
        this.parit[2] = pelaaja3;
        this.parit[3] = pelaaja4;
        this.era1[0] = era1pari1;
        this.era1[1] = era1pari2;
        this.era2[0] = era2pari1;
        this.era2[1] = era2pari2;
        this.era3[0] = era3pari1;
        this.era3[1] = era3pari2;
    }
    
    
    /**
     * @param pelaaja1 pelaaja1 id
     * @param pelaaja2 pelaaja2 id
     * @param pelaaja3 pelaaja3 id
     * @param pelaaja4 pelaaja4 id
     * @param era1pari1 ekan erän parin 1 pisteet
     * @param era1pari2 ekan erän parin 2 pisteet
     * @param era2pari1 tokan erän parin 1 pisteet
     * @param era2pari2 tokan erän parin 2 pisteet
     */
    public Ottelu(int pelaaja1, int pelaaja2, int pelaaja3, int pelaaja4, int era1pari1,
            int era1pari2, int era2pari1, int era2pari2) {
        
        this(pelaaja1, pelaaja2, pelaaja3, pelaaja4, era1pari1,
                era1pari2, era2pari1, era2pari2, 0, 0); 
    }
    
    
    /**
     * @param pelaaja1 pelaaja1 id
     * @param pelaaja2 pelaaja2 id
     * @param pelaaja3 pelaaja3 id
     * @param pelaaja4 pelaaja4 id
     * @param era1pari1 ekan erän parin 1 pisteet
     * @param era1pari2 ekan erän parin 2 pisteet
     */
    public Ottelu(int pelaaja1, int pelaaja2, int pelaaja3, int pelaaja4, int era1pari1,
            int era1pari2) {
        
        this(pelaaja1, pelaaja2, pelaaja3, pelaaja4, era1pari1,
                era1pari2, 0, 0, 0, 0); 
    }
    
    /**
     * @return 1 jos pari 1 voitti, 2 jos pari2 voitti, muuten -1
     */
    public int voittaja() {
        int pari1erat = 0;
        int pari2erat = 0;
        if (this.era1[0] < this.era1[1]) pari2erat++;
        if (this.era1[0] > this.era1[1]) pari1erat++;
        if (this.era2[0] < this.era2[1]) pari2erat++;
        if (this.era2[0] > this.era2[1]) pari1erat++;
        if (this.era3[0] < this.era3[1]) pari2erat++;
        if (this.era3[0] > this.era3[1]) pari1erat++;
        if (pari1erat < pari2erat) return 2;
        if (pari1erat > pari2erat) return 1;
        return -1;
    }
    
    
    /**
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream os) {
        PrintStream out = new PrintStream(os);
        out.println(parit[0] + "&" + parit[1] + " VS " + parit[2] + "&" + parit[3]);
        out.println("Erä 1: " + era1[0] + " - " + era1[1]);
        if (era2[0] > 0 || era2[1] > 0) {
            out.println("Erä 2: " + era2[0] + " - " + era2[1]);
        }
        if (era3[0] > 0 || era3[1] > 0) {
            out.println("Erä 3: " + era3[0] + " - " + era3[1]);
        }
        
        if (this.voittaja() == 1) out.println("Pari 1 voitti!\n");
        if (this.voittaja() == 2) out.println("Pari 2 voitti!\n");
        if (this.voittaja() < 0) out.println("Tasapeli\n");
        
    }
    
    
    /**
     * @param indeksi mikä pelaajan indeksi on taulukossa
     * @return pelaajan iidee
     */
    public int getPelaaja(int indeksi) {
        return parit[indeksi - 1];
    }

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Pelaaja simo1 = new Pelaaja();
        Pelaaja simo2 = new Pelaaja();
        Pelaaja simo3 = new Pelaaja();
        Pelaaja simo4 = new Pelaaja();
        simo1.luojotain();
        simo2.luojotain();
        simo3.luojotain();
        simo4.luojotain();
        Ottelu ottelu1 = new Ottelu(simo1.getId(), simo2.getId(), simo3.getId(), simo4.getId(),
                21, 15);
        Ottelu ottelu2 = new Ottelu(simo1.getId(), simo2.getId(), simo3.getId(), simo4.getId(),
                21, 15, 21, 17);
        ottelu1.tulosta(System.out);
        ottelu2.tulosta(System.out);
    
    }

}
