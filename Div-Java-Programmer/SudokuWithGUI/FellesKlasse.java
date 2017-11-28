


public class FellesKlasse {

	int[] r;
	int dim;
	boolean tall[];
	Rute rutene[];

	int teller = 0;

	FellesKlasse(int dim) {
		this.dim = dim;
		tall = new boolean[dim];
		r = new int[dim];
		rutene = new Rute[dim];
	}
	/*
	 * add metode som adder rader kolonner og bokser
	 */
	public void addRute(Rute r) {
		if (teller <= dim) { 
			rutene[teller] = r;
			teller++;
		}
	}
	/*
	 * sjekker tallet finnes i ruten
	 */
	public boolean sjekkTall(int i){
		for ( int x = 0; x < dim; x++){
			if ( rutene[x].nummer == i) {
				return true;
			}
		}
		return false;
	}
}
