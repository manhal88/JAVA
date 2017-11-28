
public class Retter {
	String navn;
	String ing;
	String[] ingArr;
	String fgm;
	Retter neste;
	
	Retter(){}
	
	Retter(String navn, String ing, String[] ingArr, String fgm){
		this.navn = navn;
		this.ing = ing;
		this.ingArr = ingArr;
		this.fgm = fgm;
	}

	String hentRettNavn(){
		return navn;
	}

	String hentIng(){
		return ing;	
	}
	String[] hentIngArr(){
		return ingArr;
	}

	String hentFgm(){
		return fgm;
	}
	
	void settRettNavn(String navn){
		this.navn = navn;
	}
	
	void setIng(String ing){
		this.ing = ing;
	}
	
	void settFgm(String fgm){
		this.fgm = fgm;
	}
}
