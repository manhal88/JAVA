
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;


public class Sudokubuffer {

	int index = 0;
	int teller = 0;
	String filnavn;
	LinkedList<int[][]> losninger = new LinkedList<int[][]>();

	public void tomBuffer(){
		losninger = new LinkedList<int[][]>();
	}

	public void settIn(int[][] losningChar) {
		losninger.add(losningChar);
	}

	public int[][] taUt(int i) {
		int temp = index++;
		if (temp < i){
			return losninger.get(temp);
		}else {
			index = 0;
			temp = index;
			return losninger.get(temp);
		}
	}
	public int index(){

		int lengde = 0;
		for (int[][] l : losninger) {
			for (int x = 0; x < l.length; x++) {
				for (int y = 0; y < l[x].length; y++) {
				}
			}
			lengde++;
		}
		filnavn = ""+lengde;
		return lengde;
	}
	public void skrivAntallLosninger() {

		filnavn = filnavn + "-losningen.txt";
		File file = new File(filnavn);
		try {
			PrintWriter pw = new PrintWriter(file);
			for (int[][] l : losninger) {
				for (int x = 0; x < l.length; x++) {
					for (int y = 0; y < l[x].length; y++) {
						pw.write(l[x][y] + "");
					}
					pw.write("// ");
				}
				pw.write("\n");
			}
			pw.close();
		} catch (FileNotFoundException e) {
			System.err.println("feil under skriving til fil");
		}
	}

	public void skrivFilNavn(String filNavn) {
		this.filnavn = filNavn;
	}
}
