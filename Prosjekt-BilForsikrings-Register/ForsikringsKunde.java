//Klasse for أ¥ konstruere ForsikringsKunde objekter

import java.text.NumberFormat;

public class ForsikringsKunde
{
  private String navn;
  private String fakturaAdresse;
  private int forsikringsNr;
  private static int nesteNr = 1;
  public static final int MAX = 3; //Maks antall forsikringer kunden kan ha.
  private Forsikring[] forsikringer;
  
  public ForsikringsKunde(String navn, String fakturaAdresse) {
    this.navn = navn;
    this.fakturaAdresse = fakturaAdresse;
    forsikringer = new Forsikring[MAX];
    forsikringsNr = nesteNr++; 
  }

  //Sjekker om kunden har forsikringer av typen f og returnerer true, ellers false
  public boolean erForsikret(Forsikring f) {
    for(int i=0; i < forsikringer.length; i++) {
      if(forsikringer[i] != null && forsikringer[i].getForsikringsType() == f.getForsikringsType()) {
        return true;
      }
    }  
    return false;
  }
  
  public String getNavn() {
    return navn;
  }
  
  public int getForsikringsNr() {
    return forsikringsNr;
  }
  
  //PRINTER ALDRI Forsikring ble ikke registert!! FIKS
  //Registrerer en ny forsikring pأ¥ brukeren 
  public String tegnForsikring(Forsikring f)
  {
    if (!erForsikret(f)) {
      for(int i=0; i < forsikringer.length; i++) {
        if(forsikringer[i] == null) {
          forsikringer[i] = f;
          
          return "Ny forsikring er registert\n";
        }
      }
    }
    return "Forsikring ble ikke registert\n";
  }
  
  /* Sjekker om brukeren har alle 3 forsikringstypene, selv om denne metoden
     ikke vil returnere true nأ¥ som programmet kun omhandler bilforsikring */
  public boolean totalkunde()
  {
    for (int i=0; i < forsikringer.length; i++) {
      if (forsikringer[i] == null) {
        return false;
      }
    }
    return true;
  }
  
  /*Regner ut forsikringspremien, og gir kunden en bonus om kunden er 
    totalkunde */
  public double premie()
  {
    double premie = 0;
    
    for(int i=0; i < forsikringer.length; i++) {
      if(forsikringer[i] != null) {
        premie += forsikringer[i].premie();
      }
    }
    if(totalkunde()) {
      premie = premie - ((premie/100) * Forsikring.TOTALKUNDERABATT);
    }
    return premie;
  }

  //toString som printer all informasjon om forsikringskunden
  @Override
  public String toString() {
    String utskrift = "";
    NumberFormat kroneformat = NumberFormat.getCurrencyInstance();
    
    utskrift += "Forsikringstaker: " + getNavn() + "\nFakturaadresse: " + 
            fakturaAdresse + "\nKundenummer: " + getForsikringsNr() + "\n\n";
            
    
    for(int i=0; i < forsikringer.length; i++) {
      if(forsikringer[i] != null) {
        utskrift += forsikringer[i].toString();
      }
    }
                
    if(totalkunde()) {
      utskrift += "Forsikringstager er totalkunde i TRYGG FORSIKRING\n "
              + "og fأ¥r dermed" + Forsikring.TOTALKUNDERABATT + "%\n\n";
    }
    
    utskrift += "\n\nSUM FORSIKRINGSPREMIER PR أ…R: kr " + kroneformat.format(premie())+"\n";
    
    for(int i=0; i < 30; i++) {
      utskrift += "=";
    }
    
    return utskrift;
  }

}
