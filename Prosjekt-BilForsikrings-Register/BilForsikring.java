//Klasse for أ¥ konstruere Bilforsikrings objekter
import java.text.NumberFormat;

public class BilForsikring implements Forsikring {

  public static final double PRIS_PER_KM = 1.5;
  private String biltype = "";
  private int registreringsAr = -1;
  private String registeringsnummer = "";
  private int kjorelengde = -1;
  private double bonus = 0;

  public BilForsikring(String b, int year, String reg, int lengde, double bon) {
    biltype = b;
    registreringsAr = year;
    registeringsnummer = reg;
    kjorelengde = lengde;
    bonus = bon;
  }
 
  @Override
  public double premie() {
    double sum = PRIS_PER_KM * kjorelengde;
    double bon = (sum/100) * bonus;
    
    return sum -= bon;
  }

  @Override
  public int getForsikringsType() {
    return BIL;
  }
  
  @Override
  public String toString() {
    NumberFormat kroneformat = NumberFormat.getCurrencyInstance();
    return "BIL - Forsikring\nBiltype: " + biltype + "\nregistreringsAr: " + registreringsAr + "\nRegistreringsnr: " + registeringsnummer + 
            "\nkjorelengde: " + kjorelengde + "km\nBonus: " + 
            (int)bonus + "\nArlig premie: " + kroneformat.format(premie());  
  }
}
