

public class IkkeFerdige extends Rute  {
	IkkeFerdige(int nummer,Kolonne kolonne, Rad rad, Boks boks, Brett brett){
		super(nummer,kolonne, rad, boks, brett);
	}
	/*
	 * (non-Javadoc)
	 * @see Oblig.Rute#settTallMegOgResten()
	 * 
	 * metoden sjekker om verdien til et tall i rutene om den finnes i rad,kolonne eller boks. hvis ikke sه setter rute verdi til tallet
	 */
	public void settTallMegOgResten() {
		for (int s  = 1; s <= rad.dim; s++) {
			if (rad.sjekkTall(s) == false && kolonne.sjekkTall(s) == false && boks.sjekkTall(s) == false) {
				settVerdi(s);
				if( neste == null) {
					antallLosninger++;
					brett.leggTilLosningen(antallLosninger);
				}
				else {
					neste.settTallMegOgResten();
				}
			}
			settVerdi(0);
		}
	}
}
