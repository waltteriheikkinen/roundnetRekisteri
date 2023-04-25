/**
 * 
 */
package htSpike;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.TreeMap;

/**
 * @author waltt
 * @version 5.4.2023
 *
 */
public class TiistaiSpike {
    private Pelaajat pelaajat = new Pelaajat();
    private final Tasot tasot = new Tasot();
    private Ottelut ottelut = new Ottelut();
    
    private String hakemisto = "";
    
    
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
     * @param nimi mistä luetaan
     * @throws FileNotFoundException poikkeus jos tiedostoissa häikkää
     */
    public void lueTiedostosta(String nimi) throws FileNotFoundException {
        File dir = new File(nimi);
        dir.mkdir();
        this.pelaajat = new Pelaajat();
        this.ottelut = new Ottelut();
        
        this.hakemisto = nimi;
        this.pelaajat.lueTiedostosta(nimi);
        this.ottelut.lueTiedostosta(nimi);
    }
    
    
    /**
     * @throws FileNotFoundException poikkeus ongelmalle tiedoston kanssa
     * TODO: 3 Kopion ja muutoksien seuraaminen tallentaessa?
     */
    public void tallenna() throws FileNotFoundException {
        String virhe = "";
        try {
            pelaajat.tallenna(hakemisto);
        } catch (FileNotFoundException e) {
            virhe = e.getMessage();
        }
        
        try {
            ottelut.tallenna(hakemisto);
        } catch (FileNotFoundException e) {
            virhe += e.getMessage();
        }
        if (!"".equals(virhe)) throw new FileNotFoundException(virhe);
    }
    

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        TiistaiSpike tiistaispike = new TiistaiSpike();
        
        try {
            tiistaispike.lueTiedostosta("tallennustestit");
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
        
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
        
        int[] pelaajat = {1,0,0,0};
        int[] tulos = {1,2,3,4,5,6};
        Ottelu peli1 = new Ottelu(pelaajat, tulos);
        tiistaispike.ottelut.lisaa(peli1);
        
        
        pelaajat[0] = 2;
        Ottelu peli2 = new Ottelu(pelaajat, tulos);
        tiistaispike.ottelut.lisaa(peli2);
        
        pelaajat[0] = 3;
        Ottelu peli3 = new Ottelu(pelaajat, tulos);
        tiistaispike.ottelut.lisaa(peli3);
        
        try {
            tiistaispike.tallenna();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    
    }

}
