import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class Monitor {
	Files f;
	long forste;
	LinkedList<String[]> sortertlist = new LinkedList<String[]>();

	public Monitor(Files f, long forste) {
		this.f = f;
		this.forste = forste;
	}
	
	/* fjerne en array liste fra  LinkedList */
	synchronized void fjern(String[] delArr){
		sortertlist.remove(delArr);
	}
	
	/* sette in en array til LinkedList */
	synchronized String[] settIn(String[] delArr) {
		String[] nyArr;
		if (delArr.length == f.dim) { //hvis delArr er likk storrelse som fil-storrelse, da kaller vi po metoden skrivUt
			skrivUt(delArr);
			return null;
		}
		if (sortertlist.size() == 0) { // hvis lengden til sortertlist er null, da adder vi den delarrayen til sortertlist
			sortertlist.add(delArr);
			return null;
		}
		else {
			nyArr = sortertlist.remove();
			return nyArr;
		}
	}
	
	/* hent en array liste fra  LinkedList */
	synchronized String [] hent(int i) {
		return sortertlist.get(i);
	}
	
	/* skriver til fil */
	synchronized void skrivUt(String[] delArr) {
		try {
			PrintWriter writer = new PrintWriter(f.tilFil);
			if (delArr.length != 0) {
				for (int a = 0; a < delArr.length; a++) {
					if (delArr[a] != null) {
						writer.println(delArr[a]);
					}
				}
			}
			System.out.println("New file ("+ f.tilFil + ") skrevet ");
			writer.close();
			long sisteT = System.currentTimeMillis();
			System.out.println("Tiden er :" + (sisteT - forste) + " ms");
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
	}
}

