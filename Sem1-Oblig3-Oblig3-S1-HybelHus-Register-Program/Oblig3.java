//Navn: manhal M Al-khayyat
//brukernavn: mmalkhay
import easyIO.*;
class Oblig3 {
    public static void main(String[] args) {
	HybelHus HH = new HybelHus();
	HH.hybel();
    }
}

class HybelHus {
    
    In tast = new In();
    Out skjerm = new Out();
    In fil = new In();
    Hybel [][] hybler = new Hybel [3][6];
    int aar, maaned, totalfortjeneste, totaltAntallMoneder, etasje, saldo;
    char hbokstav;
    String navn;

    HybelHus() {
	
	In infil = new In("hybeldata.txt");
        maaned = infil.inInt(";");
	aar = infil.inInt(";");
	totalfortjeneste = infil.inInt(";");
	totaltAntallMoneder = infil.inInt(";");
	
	while (!infil.endOfFile()) {
	    
	    etasje = infil.inInt(";");
	    hbokstav = infil.inChar(";");
	    saldo = infil.inInt(";");
	    navn = infil.inLine();

	    int husleie = 6000;
	    if (etasje == 3) {
		husleie = 7000;
	    }
	   
	    int romTall = (int) (hbokstav - 'A');
	    Student b = new Student(navn,saldo);
	    hybler [etasje-1][romTall] = new Hybel();
	    hybler [etasje-1][romTall].beboer = b; 
	    hybler [etasje-1][romTall].husleie = husleie;
	    
	    b.skrivData(hbokstav,etasje);
	}
    }
    
    void hybel() {
	
	int order = 0;
	while (order != 7) {
	    skjerm.outln("\t***Du har fّlgende valgmuligheter: ***");
	    skjerm.outln("1) Skriv oversikt");
	    skjerm.outln("2) Registrer ny leietager");
	    skjerm.outln("3) Register betaling fra leietager");
	    skjerm.outln("4) Registrer frivillig utflytting");
	    skjerm.outln("5) Mهnedskjّring av husleie");
	    skjerm.outln("6) Kast ut leietagere");
	    skjerm.outln("7) Avslutt");
	    skjerm.outln("");
	    
	    if (order >= 8) {
		skjerm.outln("\nFeil tall.... :");
		skjerm.outln("");
		order = 0;
	    }
	    else {
		skjerm.outln("\t***Velg kommando (1-7):  ***");
		order = tast.inInt();
		
		switch (order) {
		    
		case 1: SkrivOversikt(); break;
		case 2: RegistrerNyLeietager(); break;
		case 3: RegisterBetalingFraLeietager(); break;
		case 4: RegistrerFrivilligUtflytting(); break;
		case 5: MهnedskjّringAvHusleie(); break;
  		case 6: KastUtLeietagere(); break;
		case 7: Avslutt(); break;
		}
	    }
	}
    }
    
    
    void SkrivOversikt() {
	
	skjerm.outln("\t***Skriv oversikt: ");
	skjerm.outln("\nHybel Leietager                 Saldo");
	skjerm.outln("\n----- ------------------------ --------");
	for (int i=0; i<3; i++) {
	    for (int j=0; j<6; j++) {
		
		char bokstav = (char) (j + 'A');
		skjerm.outln(" "+ (i+1) + "" + bokstav + "\t" + hybler[i][j].beboer.navn+"\t\t"+ hybler[i][j].beboer.saldo);
	    }
	}
		skjerm.outln("");
		skjerm.outln("\nMهned/هr, og driftstid: " + maaned + "/"+ aar + ",  "+ totaltAntallMoneder+" mnd. i drift" );
		skjerm.outln("\nTotalfortjeneste: "+ totalfortjeneste +" kr");
		skjerm.outln("");
    }
    void RegistrerNyLeietager() {
	skjerm.outln("\t***Registrer Ny Leietager: ");
	boolean ledig = false;
	for (int i=0; i < 3; i++) {
	    for (int j=0; j < 6; j++) {
		if (hybler[i][j].beboer.navn.equals("TOM HYBEL")) {
		    ledig = true;
		    char bokstav = (char) (j + 'A');
		    skjerm.outln("\nHyblen " + (i+1) + "" + bokstav + " er ledig");
		}
	    }
	} 
	skjerm.outln("");
	if (ledig == false) {
	    skjerm.outln("\nDet finnes ingen ledige hybler");
	}
	else {
	    skjerm.outln("\nVelg en av disse hybelne");
	    skjerm.outln("\nOppgi etasje nummer pه ّnsket hybel: ");
	    int etasjenummer = (tast.inInt()-1);
	    skjerm.out("\nOppgi bokstav pه ّnsket hybel: ");
	    char hybelnummer = tast.inChar("\n");
	    int tall = (int) (hybelnummer - 'A');
	    if( (etasjenummer >= 0) && (etasjenummer < 3) && (tall >= 0) && (tall < 6)) {
		if (hybler[etasjenummer][tall].beboer.navn.equals("TOM HYBEL")){
		    skjerm.outln("\nOppgi Leietager navn og etter navn: ");
		    String navn = tast.inLine();
		    hybler[etasjenummer][tall].beboer.navn = navn;
		    int depositum = 10000;
		    hybler[etasjenummer][tall].beboer.saldo = depositum - hybler[etasjenummer][tall].husleie;
		    totalfortjeneste += hybler[etasjenummer][tall].husleie;
		    skjerm.outln("\nHyblen er nه utleid ");
		    skjerm.outln("");
		}
		else {
		    skjerm.outln("\nHybelen er opptatt");
		}
	    }
	    else {
		skjerm.outln("\nNummeret finnes ikke! ");
	    }
	}
    }
    void RegisterBetalingFraLeietager() {
	
	skjerm.outln("\t***Register Betaling Fra Leietager");
	skjerm.outln("\nOppgi etasjenummer: ");
	int etasjenummer = (tast.inInt()-1);
	skjerm.outln("\nOppgi hybelnummer: ");
	char hybelnummer = tast.inChar("\n");
	int tall = (int) (hybelnummer - 'A');
	if (hybler[etasjenummer][tall].beboer.navn.equals("TOM HYBEL")){
	    skjerm.outln("\nDu eier ikke denne hyblen");
	    skjerm.outln("");
	}
	else {
	    skjerm.outln("\nOppgi belّpet du ّnsker ه betale: ");
	    int  LeietagerBetaling = tast.inInt();
	    hybler[etasjenummer][tall].beboer.saldo += LeietagerBetaling;
	    
	    if (hybler[etasjenummer][tall].beboer.saldo < 0) {
		totalfortjeneste = (hybler[etasjenummer][tall].beboer.saldo) * -1;
	    }
	}
	skjerm.outln("\nDu har nه lagt til ditt ّnskede belّp til din saldo. ");
	skjerm.outln("");
    }
    void RegistrerFrivilligUtflytting() {
	skjerm.outln("\t***Register Frivillig Ut flytting");
	skjerm.outln("\nOppgi ditt navn: ");
	boolean funnet = false;
	String  navnf = tast.inLine();
	for (int i=0; i<3; i++) {
	    for (int j=0; j<6; j++) {
		if (hybler[i][j].beboer.navn.equals(navnf)) {
		    funnet = true;
		    if (hybler[i][j].beboer.saldo < 0) {
			skjerm.outln("\nDin saldo er ikke hّy nok til utflytting ");
		    } 
		    else {
			hybler[i][j].beboer.navn = "TOM HYBEL";
			hybler[i][j].beboer.saldo = 0;
			skjerm.outln("\nBeboeren har nه flyttet ut");
		    }	
		}
	    }
	}
	if(funnet == false) {
	    skjerm.outln("\nNavnet ble ikke funnet ");
	}
    }
    void MهnedskjّringAvHusleie() {

	skjerm.outln("\t***Mهnedskjّring Av Husleie");
	skjerm.outln("\nطnsker du ه utfّre mهnedskjّring for mهned " + maaned + " / " + aar + " (j/n)? ");
	char valg = tast.inChar("\n");
	if (valg == 'j') {
	    
	    int maanedfortjeneste = 0;
	    int utgifter = 31500;
	    int intekter= 0;
	    int g = 0;
	    if (maaned >= 12) {
		maaned = 1;
		aar++;
	    } else {
		maaned++;
	    }
	    totaltAntallMoneder++;
	    	    
	    for (int i=0; i<3; i++) {
		for (int s=0; s<6; s++) {
		    if (!hybler[i][s].beboer.navn.equals("TOM HYBEL")){
			hybler[i][s].beboer.saldo = hybler[i][s].beboer.saldo - hybler[i][s].husleie;
			maanedfortjeneste += hybler[i][s].husleie;
			intekter = maanedfortjeneste - utgifter;
			totalfortjeneste = maanedfortjeneste + totalfortjeneste;
			g = totalfortjeneste / totaltAntallMoneder;
		    }
		}
	    }
	    skjerm.outln("\nMهnedskjّringen gjelder for; "+ maaned +" / "+ aar +" og systemet har vوrt i drift i "+ totaltAntallMoneder);
	    skjerm.outln("\nMهnedens fortjeneste er : "+ maanedfortjeneste);
	    skjerm.outln("\nTotalfortjeneste er : "+ totalfortjeneste);
	    skjerm.outln("\nGjennomsnittlig Maanedsfortjeneste er : "+ g);
	    skjerm.outln("");
	}
	else {
	    skjerm.outln(" Du valgte ه ikke kjّre mهnedskjّringen!");
	}
    }
    void KastUtLeietagere() {
	
	skjerm.outln("\t***Kast ut leietagere ");
	int pengeKrav = 0;
	int studentSaldo = 0;
	int husleiePaaMinus = -6000;
	int utkastingsgebyr = 3000;
	if (etasje == 2) {
	    husleiePaaMinus = -7000;
	}
	for (int i=0; i<3; i++) {
	    for (int j=0; j<6; j++) {
		if (hybler[i][j].beboer.saldo < husleiePaaMinus) {
		    
		    char bokstav = (char) (j + 'A');
		    skjerm.outln("\nStudenten "+ hybler[i][j].beboer.navn + " i hybel nummer " + (i+1) + "" + bokstav + " har blitt kastet ut !");
		    studentSaldo = (hybler[i][j].beboer.saldo) * -1;
		    pengeKrav = studentSaldo + utkastingsgebyr;
		    skjerm.outln("\nVedkommende skylder "+ pengeKrav +" kr. ");
		    totalfortjeneste += pengeKrav - 1500; // egendel til G

		    Out skjerm = new Out("hole.txt",true);
		    skjerm.out((i+1)+ ";");
		    skjerm.out( bokstav +  ";");
		    skjerm.out( hybler[i][j].beboer.navn+ ";");
		    skjerm.outln(pengeKrav);
		    
		    skjerm.close();
		    hybler[i][j].beboer.navn = "TOM HYBEL";
		    hybler[i][j].beboer.saldo = 0;
		}
	    }
	}
    }
    
    void Avslutt() {
	// avslutt
	Out skjerm = new Out("hybeldata.txt");
	skjerm.out(maaned+ ";");
	skjerm.out(aar+ ";");
	skjerm.out(totalfortjeneste+ ";");
	skjerm.outln(totaltAntallMoneder);
	
	for(int etg = 0; etg < hybler.length; etg++){
	    for(int rom = 0; rom < hybler[etg].length; rom++){ 
		int etasje = etg +1;
		char bokstav = (char) (rom + 'A');
		skjerm.out(etasje + ";");
		skjerm.out(bokstav+ ";");
		skjerm.out(hybler[etg][rom].beboer.saldo +";");
		skjerm.outln(hybler[etg][rom].beboer.navn);
	    }
	}
	skjerm.close();
	skjerm.outln("\n***Programmet avsluttet***");
	skjerm.outln("");
	
	System.exit(0);
    }
}
class Hybel {
    int husleie;
    Student beboer;
    
}

class Student {
    String navn;
    int saldo;
    
    Student(String n, int s) {
	this.navn = n;
	this.saldo = s;
    }
    void skrivData(char hbokstav,int etasje){
    }    
}
