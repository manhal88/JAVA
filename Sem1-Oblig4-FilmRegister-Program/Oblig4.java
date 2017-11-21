import java.util.HashMap;
import java.util.Iterator;

import easyIO.In;
import easyIO.Out;
class Oblig4 {

    public static void main(String[] args) {
	new Filmregister().ordrelokke();
    }
}

class Filmregister {

    In tast = new In();
    Out skjerm = new Out();
    
    HashMap<String,Person> personer = new HashMap<String,Person>();
    HashMap<String,Film> filmer = new HashMap<String,Film>();
  
    Filmregister() {
	In innfil = new In("/persondata.txt");
	innfil.inLine();
	while (!innfil.endOfFile()) {
	    String kodeA = innfil.inWord();
	    String navn = innfil.inLine();
	    Person enPerson = new Person(kodeA, navn);
	    personer.put(kodeA, enPerson);
	}
	innfil.close();

	innfil = new In("filmdata.txt");
	innfil.inLine(); // hهppe over fّrste linje
	while (!innfil.endOfFile()) {
	    String linje = innfil.inLine();
            String[] felt = linje.split("\t"); // splitter linjen med tab
	    String kodeB = felt[0];
            String tittel = felt[1];
            String aarA = felt[2];
            String regissor = felt[3];
            String skuespillere = felt[4];
            String sjanger = felt[5];

	    
	    Film enFilm = new Film(kodeB, tittel, aarA, regissor, skuespillere, sjanger);
	    filmer.put(kodeB, enFilm);
	}

	innfil.close();
    }

    void ordrelokke(){
	String ordre = ""; // kommandoen fra tastatur.
	char char0 = 'm';

	visMeny();
	
	while(! ordre.equals("q")) {

	    int ordreLengde = ordre.trim().length();
	    if (ordreLengde > 0) {
		char0 = ordre.charAt(0);
	    }
	    if (ordreLengde == 1 && char0 == 'm') {
		visMeny();
	    }
	    else if (ordreLengde == 1 && char0 == 's' ) {
		VisStatistikk();
	    }
	    else if (ordreLengde >= 4 && char0 >= 'A' && char0 <= 'Z') {
		VisInfoOmFilm(ordre);
	    }
	    else if (ordreLengde == 3 && char0 >= 'A' && char0 <= 'Z') {
		FinnFilm(ordre);
	    }
	    else if (ordreLengde >= 4 && char0 >= 'a' && char0 <= 'z') {
		VisInfoOmPerson(ordre);
	    }
	    else if (ordreLengde == 3 && char0 >= 'a' && char0 <= 'z') {
		FinnPerson(ordre);
	    }
	    else if (ordre.matches("[0-9][0-9][0-9]0s")) {
		VisInfoOmTiهr(ordre);
	    }

	    skjerm.outln("");
	    skjerm.outln("\tKommando 'm' = meny. ");
	    skjerm.outln("\tTast inn kommandoen: ");
	    ordre = tast.readLine().trim();
	    skjerm.outln();
	}
    }
    void visMeny() { //Meny

	skjerm.outln("");
	skjerm.outln("************** Meny: ***************");
	skjerm.outln("*                                  *");
	skjerm.outln("* m       = Vis meny               *");
	skjerm.outln("* s       = Vis statistikk         *");
	skjerm.outln("* Aaa1    = Vis info om en film    *");
	skjerm.outln("* Aaa     = Finn film              *");
	skjerm.outln("* tom1    = Vis info om en person  *");
	skjerm.outln("* tom     = Finn person            *");
	skjerm.outln("* 2000s   = Vis info om et tiهr    *");
	skjerm.outln("* q       = Avslutt                *");
	skjerm.outln("************************************");
	
    }

    /*  
     * her gهr den gjennom هrene til filmer i HashMap
     * og setter de tre siset tre ti إrene i tre lister
     */
    void VisStatistikk() {//Statistikk
	int ant80 = 0;
	int ant90 = 0;
	int ant20 = 0;
	skjerm.outln("************ Vis statistikk *************");
	skjerm.outln("\nTotalt antall filmer : " + filmer.size());
	Iterator it = filmer.values().iterator(); // gهr gjennom hele HasMapen.filmer
	while (it.hasNext()) {
	    Film f = (Film) it.next();

	    if (f.aar >= 1990 && f.aar <= 1999) {
		ant80++;
	    }
	    else if (f.aar >= 2000 && f.aar <= 2009) {
		ant90++;
	    }

	    else if (f.aar >= 2010 && f.aar <= 2019) {
		ant20++;
	    }

	}
	skjerm.outln("Antall filmer i 1990-1999: " + ant80 + ".");
	skjerm.outln("Antall filmer i 2000-2009: " + ant90 + ".");
	skjerm.outln("Antall filmer i 2010-2019: " + ant20 + ".");
	skjerm.outln("*****************************************");
    }
    /*
     * sjekker i filmer i HashMapen om det matcer med ordre (film kode)
     * og viser film navn, og den tar skuespillere og regissor til filmen og splitter 
     * de med (;) og lager de i en array og viser den pه skjermen
     */

    void VisInfoOmFilm(String kodeB) { // Info Om Fil
	if (filmer.containsKey(kodeB)) {
	    Film enFilm = filmer.get(kodeB);
	    skjerm.outln("**************Info Om Film****************");
	    skjerm.outln("Filmkode:       " + enFilm.kodeB);
	    skjerm.outln("Tittel:         " + enFilm.tittel);
	    skjerm.outln("إr:             " + enFilm.aar);
	    skjerm.outln();
	    int loper = 0;
	    String [] skuespillere = enFilm.skuespillere.split(";");
	    String [] regissor = enFilm.regissor.split(";");
	    if (!skuespillere[0].equals("-")) {
		skjerm.outln("Skuespillerne i denne filmen er : ");
		for (int a = 0; a < skuespillere.length; a++) {
		    kodeB = skuespillere[a];
		    Person enPerson = personer.get(skuespillere[a]);
		    skjerm.outln(enPerson.navn);
		    loper++;
		}
	    }
	    else {
		skjerm.outln("Ingen Skuespillere");
	    }

	    if (!regissor[0].equals("-")) {
		skjerm.outln();
		skjerm.outln("Regissّr i denne filmen er : ");
		for (int n = 0; n < regissor.length; n++) {
		    kodeB = regissor[n];
		    Person enPerson = personer.get(regissor[n]);
		    skjerm.outln(enPerson.navn);
		    loper++;
		}
	    }
	    else {
		skjerm.outln("Ingen Regissّr");
	    }
	}
	else {
	    skjerm.outln("\tKoden du tastet inn finnes ikke ");
	    skjerm.outln("");
		
	}
	skjerm.outln("******************************************");
    }
   
    void FinnFilm(String kodeB) { // Finn Film
	skjerm.outln("*************** Finn film ***************");
	int printMeny = 1; // printe menyen en gang
	boolean funnet = false;
	for (Film enFilm: filmer.values()) {
	    if (enFilm.kodeB.contains(kodeB)) {
		while(printMeny == 1) {
		    skjerm.outln("Filmer som matcher ditt sّk er : ");
		    skjerm.outln("Kode" + "\t" + "إr" + "\t" + "Tittel");
		    skjerm.outln("-----------------------------------");
		    printMeny++;
		}
		funnet = true;
		skjerm.outln(enFilm.kodeB + "\t" + enFilm.aar + "\t" + enFilm.tittel);
	    }
	}
	if (!funnet) {
	    skjerm.outln("Ingen film matcher ditt sّk.");
	}
	skjerm.outln("*****************************************");
    }
    void VisInfoOmPerson(String kodeA) { //Info Om Person
	skjerm.outln("***********Vis info om person************");
	int printMeny = 1;
	if (personer.containsKey(kodeA)) {
	    Person enPerson = (Person) personer.get(kodeA);
	    skjerm.outln("Person kode: " + enPerson.kodeA);
	    skjerm.outln("Skuespiller navn: " + enPerson.navn);
	    skjerm.outln();
	    Iterator <Film> it = filmer.values().iterator();
	    skjerm.outln("skuespilleren har spilt i: ");
	    while (it.hasNext()) {
		Film f = it.next();
		String s = f.skuespillere;
		String t = f.tittel;
		String [] a = s.split(";");
		for (int i = 0; i < a.length; i++) {
		    if (kodeA.equals(a[i])) {
			skjerm.outln(f.aar + "\t" + f.tittel);
		    }
		}
	    }
	    it = filmer.values().iterator();
	    skjerm.outln();
	    skjerm.outln("Skuespilleren har regissert: ");
	    while (it.hasNext()) {
		Film m = it.next();
		String r = m.regissor;
		String u = m.tittel;
		String [] b = r.split(";");
		for (int j = 0; j < b.length; j++) {
		    if (kodeA.equals(b[j])) {
			
			skjerm.outln("\t" + m.tittel);
		    }
		}
	    }
	}
	else {
	    skjerm.outln("\tKoden du tastet inn finnes ikke ");
	    skjerm.outln("");
	}
	skjerm.outln("*****************************************");
    }
    void FinnPerson(String kodeA) {
	skjerm.outln("**************Finn person****************");
	int printMeny = 1;
	boolean funnet = false;
	for (Person enPerson: personer.values()) {
	    if (enPerson.kodeA.contains(kodeA)) {
		while(printMeny == 1) {
		    skjerm.outln("Personer som matcher ditt sّk er : ");
		    skjerm.outln("Kode" + "\t" + "Navn");
		    skjerm.outln("-----------------------------------------");
		    printMeny++;
		}
		funnet = true;
		skjerm.outln(enPerson.kodeA + "\t" + enPerson.navn);
	    }
	}
	
	if (!funnet) {
	    skjerm.outln("Ingen personer matcher ditt sّk.");
	}
	skjerm.outln("*****************************************");
    }
    /*
     * her gهr den gjennom هrene i HashMapen og
     * leser den fra andre og tredje siffer fra order(ti هr)
     * og sammenlikner de, hvis det er samme sه viser pه
     *skjermen alle filmene som machet order
     */
    void VisInfoOmTiهr(String order) { //Ti هr
	skjerm.outln("**************Beste filmer***************");
	char andreBokstav = order.charAt(1); // andre sifferet i هret (fra order)som blitt tastet
	char tredjeBokstav = order.charAt(2); // tredje sifferet i هret (fra order)som blitt tastet
	Iterator <Film> it2 = filmer.values().iterator();
	while (it2.hasNext()) {
	    Film  p = it2.next();
	    String A = p.aarA;
	    int antPriser = 0;
	    char aar2bokstav = A.charAt(1);
	    char aar3bokstav = A.charAt(2);
	    if ( andreBokstav == aar2bokstav && tredjeBokstav == aar3bokstav) {
	       	if (p.sjangre.matches("[0-9][0-9].+")) { // fّrste og andre sifferet fra sjangre er tall eller ikke

		    skjerm.outln(p.tittel);
		
		}
	    }
	}
    	skjerm.outln("*****************************************");
    }
}

class Person {

    String kodeA;
    String navn;
    Out skjerm = new Out();

    Person (String kodeA, String navn) {
	this.kodeA = kodeA;
	this.navn = navn;
    }
}

class Film {

    Out skjerm = new Out();
    String kodeB;
    String tittel;
    int aar;
    String aarA;
    String regissor;
    String skuespillere;
    String sjangre;

    Film (String kodeB, String tittel, String aarA, String regissor, String skuespillere, String sjangre) {

	this.kodeB = kodeB;
	this.tittel = tittel;
	this.aarA = aarA;
	this.aar = Integer.parseInt(aarA);
	this.regissor = regissor;
	this.skuespillere = skuespillere;
	this.sjangre = sjangre;
    }
}
