
import java.io.*;
import java.util.*;

public class Files {
	int dim;
	int teller = 0;
	String[] ikkeSortert;
	String[] sortert;
	int antalT;
	Monitor m;
	public File innFil, tilFil;
	boolean ferdig = false;

	Files(int antalT, File innFil, File tilFil){
		this.antalT = antalT;
		this.innFil = innFil;
		this.tilFil = tilFil;
	}

	void start(){

		Scanner	hent = null;
		/* leser fra fil */
		try {
			hent = new Scanner(innFil);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		/* leser inn f�rste linje (st�rrelsen)  og opprette en ny array */
		dim = Integer.parseInt(hent.nextLine());
		ikkeSortert = new String[dim];
		while (hent.hasNextLine()) { // fylle arrayen med resten av fil-linjer //
			ikkeSortert[teller] = hent.nextLine();
			teller++;
		}
		hent.close();
		/* starter opp tr�der for � sortere dem */
		int n = dim/antalT;
		int rest = dim % antalT;
		int min = 0;
		int max = 0;
		int z = Integer.valueOf(antalT).intValue();
		long forsteT =  System.currentTimeMillis(); // metode som teller tiden fra forste gang vi oppretter trodene.
		m = new Monitor(this, forsteT);
		for (int i = 0; i < rest; i++) {
			max = max + n + 1;
			Sortering s = new Sortering(i, Arrays.copyOfRange(ikkeSortert, min, max), m);
			min = max;
			s.start();
		}
		for (int i = rest; i < z; i++) {
			max = max + n;
			Sortering s = new Sortering(i, Arrays.copyOfRange(ikkeSortert, min, max), m);
			min = max;
			s.start();
		}
	}
}
