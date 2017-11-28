
public class MatRegister {
	Retter forste;
	int size;

	boolean settInn(Retter rett) {

		if (rett == null) {
			return false;
		}
		if (forste == null) {
			forste = rett;
			size++;
			return true;
		} else {
			Retter beholder = forste;
			while (beholder.neste != null) {
				beholder = beholder.neste;
			}
			beholder.neste = rett;
			size++;
			return true;
		}
	}
	Retter hentRett(String navn) {
		if(forste == null){
			return null;
		}
		Retter temp = forste;
		if (temp.hentRettNavn().equals(navn)){
			return temp;
		} else {
			while (! temp.hentRettNavn().equals(navn)){
				if (temp.neste == null){
					return null;
				}
				temp = temp.neste;
			}
			return temp;
		}
	}

	int hentAntall() {
		int teller = 0;
		Retter rett = forste;
		while (rett !=null) {
			teller++;
			rett = rett.neste;
		}
		return teller;
	}

	Retter [] hentAlle() {
		Retter[] retter = new Retter[hentAntall()];
		Retter r = forste;
		for (int s = 0; s < retter.length; s++) {
			retter[s] = r;
			r = r.neste;
		}
		return retter;
	}
}
