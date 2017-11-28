
public class MatOppskrifter {

	public static void main(String[] args) {
		
		
		MatRegister mr = new MatRegister();
		ReadTheFile f = new ReadTheFile();
		Windows w = new Windows();
		f.startRead(mr);
		w.windows(mr);
		
	}
}
