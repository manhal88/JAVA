
import easyIO.*;
class Oblig2 {
    public static void main(String[] args) {
	Olje ol = new Olje();
	ol.oljeFelt();
    }
}

class Olje {
    In tast = new In();
    Out skjerm = new Out();
    int sumalt = 0; 
    String [][] eier = new String [8][20];
    int [][] utvunnet = new int [8][20];
    
    void oljeFelt() {
	int ordre = 0;
	while (ordre != 6) {
	    skjerm.outln("\t***Du har fّlgende valgmuligheter: ***");
	    System.out.println("1) Kjّp et felt");
	    System.out.println("2) Annuller kjّp av et felt");
	    System.out.println("3) Lag oversiktskart");
	    System.out.println("4) Oppdater oljeutvinning");
	    System.out.println("5) Finn sum oljeutvinning");
	    System.out.println("6) Avslutt");
	    skjerm.outln("\t***Velg kommando (1-6):  ***");
	    ordre = tast.inInt();


	    switch (ordre) {

	    case 1: Kjopetfelt(); break;
	    case 2: Annullerkjop(); break;
	    case 3: oversiktskart(); break;
	    case 4: Oppdateroljeutvinning(); break;
	    case 5: sumoljeutvinning(); break;
	    case 6: Avslutt(); break;
		    }  
	}
    }
    void  Kjopetfelt() {
	
	skjerm.outln("\n***Kjّp et felt: ");
	boolean ledigFelter = false;
	for (int i=0; i < 8; i++) {
	    for (int j=0; j < 20; j++) {
		if (eier[i][j] == null) {
		    ledigFelter = true;
		}
	    }
	}
	if (ledigFelter == false ) {
	    System.out.println("\nDet er desverre ingen felt igjen til salgs.");
	    
	}
	else {
	    
	    skjerm.out("\nOppgi radnummer (0-7) i feltet som ّnskes kjّpt: ");
	    int radnummer = tast.inInt();
	    skjerm.out("\nOppgi kolonnenummer (0-19) i feltet som ّnskes kjّpt: ");
	    int kolonnenummer = tast.inInt();
	    skjerm.out("\nOppgi oljeselskapets navn: ");
	    String navn = tast.inLine();

	    if (eier[radnummer][kolonnenummer] == null) {
		eier[radnummer][kolonnenummer] = navn;
		skjerm.outln("\nTakk, Feltet F(" + radnummer + "," + kolonnenummer + ") selges til " + navn +  ". ");
		System.out.println();
	    }
	    else {
		skjerm.outln("\nFeltet er allerde kjّpt av et annet selskap.");
	    }
	    System.out.println("");
	}
    }
    void Annullerkjop () {

	skjerm.outln("\n***Annuller kjّp av et felt. ");
	skjerm.out("\nOppgi radnummer (0-7) i feltet som ّnskes annulert: ");
	int radnummer = tast.inInt();
	skjerm.out("\nOppgi  kolonnenummer (0-19) i feltet som ّnskes annulert: ");
	int kolonnenummer = tast.inInt();
	skjerm.out("\nOppgi oljeselskapets navn: ");
	String navn = tast.inLine();
	if ( eier [radnummer][kolonnenummer] != null && (eier[radnummer][kolonnenummer].equals(navn))) {
	    eier [radnummer][kolonnenummer] = null;
	    skjerm.outln("\nFeltet F(" + radnummer + "," + kolonnenummer + "," + navn + ") er annulert. ");
	}   

	else {
	    System.out.println("\nDu eier ikke dette feltet. ");
	}
	System.out.println("");
    }

    void oversiktskart () {

	skjerm.outln("\n***Lag oversiktskart. ");
	System.out.println("\n   0   1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  ");
	for (int i=0; i<8; i++) {
	    System.out.print(i);
	    for (int j=0; j<20; j++) {
		if(eier[i][j] == null) {
		    System.out.print("  . "); 
		}
		else {
		    System.out.print("  X ");
		}
	    }
	    System.out.println("");
	}
    }
    
    void Oppdateroljeutvinning () {

	skjerm.outln("\n***Oppdater oljeutvinning. ");
	skjerm.out("\nOppgi radnummer (0-7) i feltet som ّnskes oppdatert: ");
	int radnummer = tast.inInt();
	skjerm.out("\nOppgi kolonnenummer (0-19) i feltet som ّnskes oppdatert: ");
	int kolonnenummer = tast.inInt();
	skjerm.out("\nOppgi navn pه selskap: ");
	String navn = tast.inLine();

	if (eier[radnummer][kolonnenummer] == null || !(eier[radnummer][kolonnenummer].equals(navn))) {
	    System.out.println("\nDu eier ikke dette feltet. ");
	}
	else {
	    skjerm.outln("\nOppgi utvunnet oljemengde i fat: ");

	    int oljeutvunnet = tast.inInt();
	    utvunnet[radnummer][kolonnenummer]= oljeutvunnet;
	    sumalt += oljeutvunnet;
	    System.out.println("Takk");
	    System.out.println("");
		
	}
    }
    void sumoljeutvinning () {

	skjerm.outln("\n***Finn sum oljeutvinning: ");

	System.out.println("\nOljen som er utvunnet mهlt i fat i lّpet av det siste هret er: " + sumalt + ". ");
	System.out.println("");
    }
    void Avslutt () {

	System.out.print("\n***Programmet avsluttet***");
	System.out.println("");

	System.exit(0);
    }   
}
