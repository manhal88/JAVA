
public class Sortering extends Thread {

	int plass = 0;
	Monitor m;
	int trodNr;
	String []delArr;
	String [] delArr2;
	boolean yea = false;

	Sortering( int trodNr, String [] delArr, Monitor m) {

		this.trodNr = trodNr;
		this.delArr = delArr;
		this.m = m;
	}// end constructor

	public void run(){

		/* sorterer en delarray og setter denne delen i en LinkedList */
		sorting(delArr);
		delArr2 = m.settIn(delArr);
		while ( delArr2 != null) {
			delArr = fletting(delArr, delArr2);
			delArr2 = m.settIn(delArr);
		}
	}

	/* sortering metode som sortere en delarray*/
	void sorting(String[] delArr) {
		for (int k = 0; k < delArr.length-1; k++) {
			String t = delArr[k+1];
			int i = k;
			while (i >= 0 && delArr[i].compareTo(t) > 0) {
				delArr[i+1] = delArr[i];
				i--;
			}
			delArr[i+1] = t;
		}
	}
	
	/* fletting metode som fletter to arrayer */
	String[] fletting(String[] delArr, String[] delArr2) {
		String[] answer = new String[delArr.length + delArr2.length];
		int i = 0, j = 0, k = 0;
		while (i < delArr.length && j < delArr2.length) {
			if (delArr[i].compareTo(delArr2[j]) < 0) {
				answer[k] = delArr[i];
				i++;
			} else {
				answer[k] = delArr2[j];
				j++;
			} k++;
		}
		while (i < delArr.length) {
			answer[k] = delArr[i];
			i++;
			k++;
		}
		while (j < delArr2.length) {
			answer[k] = delArr2[j];
			j++;
			k++;
		}
		return answer;
	}

}
