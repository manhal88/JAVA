

public class FerdigUtFylt extends Rute {

	FerdigUtFylt(int nummer, Kolonne kolonne,Rad rad, Boks boks, Brett brett){
		super(nummer, kolonne, rad, boks, brett);
	}
	/*
	 * (non-Javadoc)
	 * @see Oblig.Rute#settTallMegOgResten()
	 * 
	 * metode som sjekker hvis det finnes tall sه hهpper den over til neste
	 */
	public void settTallMegOgResten() {
		if(neste == null) {
			antallLosninger++;
			brett.leggTilLosningen(antallLosninger);
		}
		else {
			neste.settTallMegOgResten();
		}
	}
}

