

import java.io.File;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class Brett {

	int[][] intArray;
	Rute[][] ruter;
	Rad[] rader;
	Kolonne[] kolonner;
	Boks[][] bokser;
	Rute prev;
	int dim;
	int boxL;
	int boxB;
	int dimensjon;
	int vertikal;
	int horisontal;
	Sudokubuffer buffer = new Sudokubuffer();

	Brett(){
	}

	Brett(String filNavn){
		buffer.skrivFilNavn(filNavn);
	}

	void lesFraFil(File file) {

		Scanner hent = null;
		try {
			hent = new Scanner(file);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * leser fّrst tre linjer fra fil
		 */
		dim = Integer.parseInt(hent.nextLine());
		boxL = Integer.parseInt(hent.nextLine());
		boxB = Integer.parseInt(hent.nextLine());

		intArray = new int[dim][dim];

		int teller = 0;
		/*
		 * lager tomme ruter,rader,kolonner og bokser
		 */
		ruter = new Rute[dim][dim];
		rader = new Rad[dim];
		kolonner = new Kolonne[dim];
		bokser = new Boks[boxB][boxL];
		/*
		 * lager rader og kolonner med riktig stّrlse
		 */
		for (int i = 0; i < dim; i++) {
			rader [i] = new Rad(dim);
			kolonner [i] = new Kolonne(dim);
		}
		/*
		 * lager boks med riktig stّrlse
		 */
		for (int m = 0; m < dim/boxL; m++) {
			for (int n = 0; n < dim/boxB; n++){
				bokser[m][n] = new Boks(dim);
			}
		}

		while (hent.hasNextLine()) {
			String linje = hent.nextLine(); //leser restin av filen
			for(int i = 0; i < dim; i++ ) {
				char c = linje.charAt(i);
				int number = Character.getNumericValue(c);//<====== bytte fra char til int
				if ( number == -1) {
					number = 0;
					ruter[teller][i] = new IkkeFerdige(number, kolonner[i], rader[teller], bokser[teller/(dim/boxB)][i/(dim/boxL)], this);//setter verdien til rutene som har 0er
					if (prev != null) {
						prev.neste = ruter[teller][i];
					}
					prev = ruter[teller][i];
				} else {
					ruter[teller][i] = new FerdigUtFylt(number, kolonner[i], rader[teller], bokser[teller/(dim/boxB)][i/(dim/boxL)], this);//setter verdien til rutene som har verdig
					if (prev != null) {
						prev.neste = ruter[teller][i];
					}
					prev = ruter[teller][i];
				}
			}
			teller++;
		}
		/*
		 * setter kolonner, rader og bokser pه riktig plass i ruter array
		 */
		for (int a = 0; a < ruter.length; a++) {
			for (int b = 0; b < ruter.length; b++){

				ruter[a][b].kolonne = kolonner[b];//<===
				kolonner[b].addRute(ruter[a][b]);
				ruter[a][b].rad = rader[a]; //<====
				rader[a].addRute(ruter[a][b]);
				ruter[a][b].boks = bokser[a/(dim/boxB)][b/(dim/boxL)];//<=====
				bokser[a/(dim/boxB)][b/(dim/boxL)].addRute(ruter[a][b]);
				intArray[a][b] = ruter[a][b].nummer;
				
			}
		}
		
		new SudokuGUI(dim, boxL, boxB, intArray, this);// lager et SudokuGUI program
	}

	public void lesFraArray(int dimensjon, int vertikal, int horisontal, int[][] ruterFraArray) {
		dim = dimensjon;
		intArray = new int[dimensjon][dimensjon];
		ruter = new Rute[dimensjon][dimensjon];
		rader = new Rad[dimensjon];
		kolonner = new Kolonne[dimensjon];
		bokser = new Boks[horisontal][vertikal];
		for (int i = 0; i < dimensjon; i++) {
			rader [i] = new Rad(dimensjon);
			kolonner [i] = new Kolonne(dimensjon);
		}
		for (int m = 0; m < dimensjon/vertikal; m++) {
			for (int n = 0; n < dimensjon/horisontal; n++){
				bokser[m][n] = new Boks(dimensjon);
			}
		}
		for (int x = 0; x < dimensjon; x++) {
			for (int y = 0; y < dimensjon; y++) {
				if ( ruterFraArray[x][y] == 0) {
					ruter[x][y] = new IkkeFerdige(ruterFraArray[x][y], kolonner[y], rader[x], bokser[x/(dimensjon/horisontal)][y/(dimensjon/vertikal)], this);//setter verdien til rutene som har 0er
					if (prev != null) {
						prev.neste = ruter[x][y];
					}
					prev = ruter[x][y];
				} else {
					ruter[x][y] = new FerdigUtFylt(ruterFraArray[x][y], kolonner[y], rader[x], bokser[x/(dimensjon/horisontal)][y/(dimensjon/vertikal)], this);//setter verdien til rutene som har verdig
					if (prev != null) {
						prev.neste = ruter[x][y];
					}
					prev = ruter[x][y];
				}
			}
		}
		for (int a = 0; a < ruter.length; a++) {
			for (int b = 0; b < ruter.length; b++) {

				ruter[a][b].kolonne = kolonner[b];//<===
				kolonner[b].addRute(ruter[a][b]);
				ruter[a][b].rad = rader[a]; //<====
				rader[a].addRute(ruter[a][b]);
				ruter[a][b].boks = bokser[a/(dimensjon/horisontal)][b/(dimensjon/vertikal)];//<=====
				bokser[a/(dimensjon/horisontal)][b/(dimensjon/vertikal)].addRute(ruter[a][b]);
				intArray[a][b] = ruter[a][b].nummer;
			}
		}
		new SudokuGUI(dimensjon, vertikal, horisontal, intArray, this);// lager et SudokuGUI program
		lagLosninger();
	}

	/*
	 * metode til ه starte lّsninger begynner fra rute o-o
	 */
	public void lagLosninger(){
		ruter[0][0].settTallMegOgResten();
		System.out.println("Rekursjon er ferdig") ;
		JOptionPane.showMessageDialog(null, "number of solutions:"+ buffer.index());
	}

	/*
	 * 
	 * metode til ه skrive ut lّsninger pه terminalen
	 */
	public void leggTilLosningen(int antallLosninger){
		int[][] losning = new int[dim][dim];

		System.out.print(antallLosninger + ": ");
		for (int x = 0; x < ruter.length; x++) {
			for (int y = 0; y < ruter[x].length; y++) {
				losning[x][y] = ruter[x][y].nummer;
				System.out.print(losning[x][y]);
			}
			System.out.print("// ");
		}
		buffer.settIn(losning);
		System.out.println();
	}
}

