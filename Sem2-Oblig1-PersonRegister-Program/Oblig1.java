//mmalkhay Oblig1 inf1010
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;


public class Oblig1 {

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] argumenter) throws Exception {
	
	if (argumenter.length == 0) {
	    System.out.println("INF1010 2012 - Obligatorisk oppgave 1");
	    System.out.println("Bruk:");
	    System.out.println("Kjore testene: java Oblig1 test");
	    System.out.println("Kjore programmet: java Oblig1 program");
	}
	else if (argumenter[0].equals("test")) {
			
		
	    PersonListe personlist = new PersonListe();		     
	    Oblig1Test tester = new Oblig1Test(personlist);
	    tester.testivei();    
	} else if (argumenter[0].equals("program")) {
	    Oblig1 ob = new Oblig1();
	    PersonListe personliste = new PersonListe();
	    ob.ordrelokke(personliste);
	    ob.program();
	}
    }
    //Metode for aa kopiere epost adresser
    public String[] kopierEmails(String[] emails) {
	int index;
	String[] adr = new String[emails.length -3];
	int i = 0;
	for ( index = 3; index < emails.length; index++) {
	    adr[i++] = emails[index];
       }
	return adr;
    }

    void ordrelokke(PersonListe l){
	String ordre;
	boolean funnet = true;
	while (funnet) {
	    program();
	    ordre = scan.nextLine();
	    if (ordre.startsWith("nyperson") || ordre.startsWith("tlf")) { //leggTilTlfnr
		String[] liste = ordre.split(" ");
		String navn = liste[1];
		if (liste.length == 2){
		    String tlfnr = null;
		    String [] epostadr = null;
		    if (l.leggTilPerson(navn, tlfnr, epostadr)) {
		    System.out.println(navn + " har blitt registerert. ");
		    }
		}else if(liste.length == 3){
		    String tlfnr = liste[2];
		    String [] epostadr = null;
		    if (l.leggTilPerson(navn, tlfnr, epostadr)) {
			System.out.println(navn + " har blitt registerert med (" + tlfnr + ") Tl-nummer");
			}
		}else if(liste.length >= 4){
		    String tlfnr = liste[2];
		    String [] epostadr = ordre.split(" ");
		    String[] emailAdresser = kopierEmails(epostadr);
		    
		    if (l.leggTilPerson(navn, tlfnr, emailAdresser)) {
			System.out.print(navn + " har blitt registerert med Tl-nummer:(" + tlfnr + ") og e-postadres-er: ");
			for( int i = 0; i < emailAdresser.length; i++ ) {
			    System.out.print( emailAdresser[i] + " ");
			}   
		    }
		    
		}else {
		    System.out.println(navn + " finnes allerede i listen, eller du har tastet manglende informasjon.");
		}
	    } else if (ordre.startsWith("fjern")) {
		String[] liste = ordre.split(" ");
		String navn = liste[1];
		if (l.fjernPerson(navn)) {
		    System.out.println(navn + " har fjernet.");
		}else{
		    System.out.println("Navnet finnes ikke.");
		} 

	    } else if (ordre.startsWith("venner")) {
		String[] liste = ordre.split(" ");
		String navn = liste[1];
		String vnavn = liste[2];
		if (l.nyVenn(navn, vnavn)) {
		    System.out.println(vnavn + " har lagt til " + navn + "s venner .");
		} else {
		    System.out.println("feil");
		}
	    } else if (ordre.startsWith("uvenner")) {
		String[] liste = ordre.split(" ");
		String navn = liste[1];
		String vnavn = liste[2];
		if (l.fjernVenn(navn, vnavn) == true) {
		    System.out.println(vnavn + " er fjernet fra " + navn + "s venner."); 
		} else {
		    System.out.println("feil");
		}

	    } else if (ordre.startsWith("vis")) {
		String[] liste = ordre.split(" ");
		String navn = liste[1];
		if (l.hentPerson(navn) != null) {
		    Person [] alle = l.hentPersoner();
		    for(Person p : alle){
			System.out.print("navn: " + p.hentNavn() + " Phone: " + p.hentTlfnr() + " e-post: ");
			for( int i = 0; i < p.epostadr.length; i++ ) {
			    System.out.print( p.epostadr[i] + " ");
			} 
			break;
		    }
		}
	    } else if (ordre.startsWith("alle")) {
		Person [] alleP = l.hentPersoner();
		if (alleP.length == 0) {
		    System.out.println("Det er ingen i listen");
		} else {
		    for(Person p : alleP){
			System.out.println("navn: " + p.hentNavn());
		    }
		}
	    } else if (ordre.startsWith("tilfil")) {
		String[] FN = ordre.split(" ");
		String filnavn = FN[1];

		File file = new File(filnavn);

		try {
		    PrintWriter pw = new PrintWriter(file);
		    Person[] alleP = l.hentPersoner();
		    for (Person f : alleP) {
			pw.write("nyperson " + f.hentNavn() + " " + f.hentTlfnr() + " ");
			for(int i = 0; i < f.epostadr.length; i++ ) {
			    pw.write(f.epostadr[i] + " ");
			}
			pw.write(" \n");
		    Person[] alleV = f.hentVenner();
		    for (Person p : alleV) {
			pw.write("venner " + f.hentNavn() + " " + p.hentNavn() + "\n");
		    }
		} pw.close();
	    } catch (FileNotFoundException e) {
		System.err.println("feil under skriving til fil");
	    }
	    } else if (ordre.startsWith("frafil")) {
		String[] FF = ordre.split(" ");
		String filnavn = FF[1];
		File file = new File(filnavn);
		
		try {
		    Scanner hent = new Scanner(file);
		    
		    while (hent.hasNextLine()) {
			String linje = hent.nextLine();
			String[] setning = linje.split(" ");
			String order = setning[0];
			if (order.equals("nyperson")) {//  metoden tar ikke e-post adreser
			    String navn = setning[1];
			    String tlfnr = setning[2];
			    l.leggTilPerson(navn, tlfnr, null);
			    //} else if (order.equals("nyperson") && setning.length <= 4) {
			} else if (order.equals("venner")) {// her mه jeg sjekke forst om navn og vennavn er i lista
			    String navn = setning[1];
			    String vnavn = setning[2];
			    l.nyVenn(navn, vnavn);
			}
		    }
		} catch (FileNotFoundException e) {
		    System.err.println("feil under skriving til fil");
		}	
	    } else if (ordre.startsWith("hjelp")) {
			program();
		    } else if (ordre.startsWith("slutt")) {
			funnet = false;
			scan.close();
	    }else{
		System.out.println("feil commando");
	    }
	}
    }
    void program() { //Meny
	System.out.println("");
	System.out.println("nyperson <navn> <tlfnr> <e-postadr1>  <e-postadr2>");
	System.out.println("tlf <navn> <tlfnr>");
	System.out.println("fjern <navn> ");
	System.out.println("venner <navn> <navn pa venn>");
	System.out.println("uvenner <navn> <navn pa ikke-venn>");
	System.out.println("vis <navn> ");
	System.out.println("alle");
	System.out.println("tilfil <filnavn> ");
	System.out.println("frafil <filnavn> ");
	System.out.println("hjelp ");
	System.out.println("slutt");
	System.out.println("");
    }
}

class Venn {
    Person vennavn;
    Venn nesteVenn;
    
    Venn(Person vnavn) {
	vennavn = vnavn;
    }
}
class Person {

    String navn,tlfnr;
    String[] epostadr;
    Venn forsteVenn;
    Person nestePerson;
    //Personliste vennliste;
    public Person() {
    }

    Person(String navn, String tlfnr, String [] epostadr) {
	this.navn = navn;
	this.tlfnr = tlfnr;
	this.epostadr =epostadr;

    }
    public String hentNavn() {
	return navn;
    }
    public void setTl(String tlfnr) {
	this.tlfnr = tlfnr;
    }
    public String hentTlfnr() {
	return tlfnr;
    }
    public String [] hentEpostadr() {
	return epostadr;
    }
    public Person [] hentVenner() {
	int teller = 0;
	Venn v = forsteVenn;
	while (v != null) {
	    teller++;
	    v = v.nesteVenn;
	}
	if (teller == 0){
	    Person[] venner = new Person[0];
	    return venner;
	}
	Person[] venner = new Person[teller];
	v = forsteVenn;
	for (int i = 0; i < venner.length; i++) {
	    venner[i] = v.vennavn;
	    v = v.nesteVenn;
	}
	return venner;
    }
}

class PersonListe {
    Person forstePerson;
    Person forsteVenn;
    public boolean leggTilPerson(String navn, String tlfnr, String[] epostadr) {
	Person p1 = hentPerson(navn);
	
	if (forstePerson == null) {
	    forstePerson = new Person(navn, tlfnr, epostadr);
	    return true;
	} else if (p1 == null) {
	    for (Person p = forstePerson; ;p = p.nestePerson) {
		if (p.nestePerson == null) {
		    p.nestePerson = new Person(navn, tlfnr, epostadr);
		    return true;
		}
	    }
	}
	return false;
    }
    public boolean fjernPerson(String navn) {
	Person p = hentPerson(navn);
	if (p == null) {
	    return false;
	} else {
	    p = forstePerson;
	    if (forstePerson.hentNavn().equals(navn)){
		if (p.nestePerson == null) {
		    Person p1 = forstePerson;
		    while (p1 !=null) {
			fjernVenn(p1.hentNavn(), navn);
			p1 = p1.nestePerson;
		    }
		    forstePerson = null;
		    return true;
		} else {
		    Person p1 = forstePerson;
		    while (p1 !=null) {
			fjernVenn(p1.hentNavn(), navn);
			p1 = p1.nestePerson;
			return true;
		    }
		}
	    }
	    while( p.nestePerson != null){
		if (p.nestePerson.hentNavn().equals(navn)) {
		    Person p1 = forstePerson;
		    while (p1 !=null) {
			fjernVenn(p1.hentNavn(), navn);
			p1 = p1.nestePerson;
		    }
		    p.nestePerson = p.nestePerson.nestePerson;
		    return true;
		} 
		p = p.nestePerson;
	    }
	}
	return false;
    }
    public boolean nyVenn(String navn, String vnavn) {
	
	Person p1 = hentPerson(navn);
	Person p2 = hentPerson(vnavn);

	if (p1 == null && p2 == null) {
	    return false;
	}

	Venn v = new Venn(p2);
	if (p1.forsteVenn == null){
	    p1.forsteVenn = v;
	    return true;
	}
	Venn f = p1.forsteVenn;
	while ( f != null) {
	    if ( f.vennavn.hentNavn().equals(vnavn)){
		return false;
	    }
	    f = f.nesteVenn;
	}
	v.nesteVenn = p1.forsteVenn;
	p1.forsteVenn = v;
	return true;
    
    }
    
    public boolean fjernVenn(String navn, String vnavn) {
	int a = 0;
	Person p1 = hentPerson(navn);
	Person p2 = hentPerson(vnavn);
	
	if (p1 == null || p2 == null) {
	    return false;
	}
	Person[] ar = p1.hentVenner();
	for ( a = 0; a < ar.length; a++) {
	    if (ar[a].hentNavn().equals(vnavn)){
		break;
	    }
	} if ( a == ar.length) {
	    return false;
	} Venn f = p1.forsteVenn;
	if (f == null) {
	    return false;
	} if (f.vennavn.hentNavn().equals(vnavn)) {
	    p1.forsteVenn = f.nesteVenn;
	    return true;
	} while (f.nesteVenn != null) {
	    if (f.nesteVenn.vennavn.hentNavn().equals(vnavn)) {
		f.nesteVenn = f.nesteVenn.nesteVenn;
		return true;
	    } f = f.nesteVenn;
	} return false;
    }
    public int hentAntall() {
	int teller = 0;
	Person p = forstePerson;
	while (p !=null) {
	    teller++;
	    p = p.nestePerson;
	}
	return teller;
    }
    public Person [] hentPersoner() {
	Person[] personer = new Person[hentAntall()];
	Person p = forstePerson;
	for (int s = 0; s < personer.length; s++) {
	    personer[s] = p;
	    p = p.nestePerson;
	}
	return personer;
    }
    public Person hentPerson(String navn) {
	for (Person p = forstePerson; p != null; p= p.nestePerson) {
	    if (p.hentNavn().equals(navn))
		return p;
	}
	return null;
    }
}
