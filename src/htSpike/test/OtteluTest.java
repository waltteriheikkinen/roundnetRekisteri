package htSpike.test;
// Generated by ComTest BEGIN
import htSpike.Pelaaja;
import htSpike.Ottelu;
import fi.jyu.mit.ohj2.Suuntaaja;
import static org.junit.Assert.*;
import org.junit.*;
import static htSpike.Ottelu.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2023.04.23 12:33:48 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class OtteluTest {



  // Generated by ComTest BEGIN
  /** testToString124 */
  @Test
  public void testToString124() {    // Ottelu: 124
    Pelaaja simo1 = new Pelaaja(); 
    Pelaaja simo2 = new Pelaaja(); 
    Pelaaja simo3 = new Pelaaja(); 
    Pelaaja simo4 = new Pelaaja(); 
    simo1.luojotain(); 
    simo2.luojotain(); 
    simo3.luojotain(); 
    simo4.luojotain(); 
    int[] pelaajat = { simo1.getId(), simo2.getId(), simo3.getId(), simo4.getId()} ; 
    int[] tulos = { 21,15,15,21,21,15} ; 
    Ottelu ottelu = new Ottelu(pelaajat, tulos); 
    assertEquals("From: Ottelu line: 138", "Parit: [1, 2, 3, 4], pisteet: [21, 15, 15, 21, 21, 15]", ottelu.toString()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testVoittaja150 */
  @Test
  public void testVoittaja150() {    // Ottelu: 150
    int[] parit = { 1,2,3,4} ; 
    int[] pisteet = { 21,15,14,21,21,16} ; 
    Ottelu ottelu = new Ottelu(parit, pisteet); 
    assertEquals("From: Ottelu line: 156", 1, ottelu.voittaja()); 
    int[] parit2 = { 1,2,3,4} ; 
    int[] pisteet2 = { 15,21,21,15,13,21} ; 
    Ottelu ottelu2 = new Ottelu(parit2, pisteet2); 
    assertEquals("From: Ottelu line: 160", 2, ottelu2.voittaja()); 
    int[] parit3 = { 1,2,3,4} ; 
    int[] pisteet3 = { 21,15,14,21,0,0} ; 
    Ottelu ottelu3 = new Ottelu(parit3, pisteet3); 
    assertEquals("From: Ottelu line: 164", -1, ottelu3.voittaja()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testTulosta185 */
  @Test
  public void testTulosta185() {    // Ottelu: 185
    Suuntaaja.StringOutput so = new Suuntaaja.StringOutput(); 
    int[] pelaajat = { 1,2,3,4} ; 
    int[] tulos = { 21,13,12,21,21,15} ; 
    Ottelu ottelu = new Ottelu(pelaajat, tulos); 
    ottelu.tulosta(System.out); 
    String tulostus = "1&2 VS 3&4\n" +
    "Erä 1: 21 - 13\n" +
    "Erä 2: 12 - 21\n" +
    "Erä 3: 21 - 15\n" +
    "Pari 1 voitti!\n"; 
    assertEquals("From: Ottelu line: 197", null, so.ero(tulostus)); 
    so.palauta(); 
  } // Generated by ComTest END
}