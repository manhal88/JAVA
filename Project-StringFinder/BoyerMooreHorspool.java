import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class BoyerMooreHorspool {
	char[] needle, haystack;
	Scanner scanner;
	int [] badCharacterTable = new int [256];

	public void start(String needleIn, String haystackIn) {

		needle = readFromFile(needleIn).toCharArray();
		haystack = readFromFile(haystackIn).toCharArray();
		if (haystack.length == 0 || needle.length == 0) {
			System.out.println("Empty needle.txt or haystack.txt");
			System.exit(1);
		}

		printHaystackAndNeedle(needle, haystack);
		createBadCharacterTable(needle);
		boyerMooreHorsepool(haystack, needle);
		runTest();
	}// end start

	private void runTest() {
		System.out.print("\n\t*** Test 1***\n\n");
		
		printHaystackAndNeedle("wh_t".toCharArray(),"what you give is what you get".toCharArray());
		createBadCharacterTable("wh_t".toCharArray());
		boyerMooreHorsepool("what you give is what you get".toCharArray(), "wh_t".toCharArray());
		
		System.out.print("\n\t*** Test 2***\n\n");
		
		printHaystackAndNeedle("_ou".toCharArray(),"When you are courting a nice girl an hour seems like a second. When you sit on a red-hot cinder a second seems like an hour. That's relativity.".toCharArray());
		createBadCharacterTable("_ou".toCharArray());
		boyerMooreHorsepool("When you are courting a nice girl an hour seems like a second. When you sit on a red-hot cinder a second seems like an hour. That's relativity.".toCharArray(), "_ou".toCharArray());
	}

	private void printHaystackAndNeedle(char[] needle, char[] haystack){
		System.out.print("Haystack ->\t[ ");
		for (int i = 0; i < haystack.length; i++) {
			System.out.print(haystack[i] + " ");
		}
		System.out.print("]\n");
		System.out.print("Needle \t->\t[ ");
		for (int i = 0; i < needle.length; i++) {
			System.out.print(needle[i] + " ");
		}
		System.out.print("]\n");
	}// end printHaystackAndNeedle
	
	private int findWildCards(char[] needle) {
		int counter = 0;

		for (int i = 0; i < needle.length; i++) {
			if (needle[i] == '_') {
				counter++;
			}
		}
		return counter;
	}// findWildCards
	
	private void createBadCharacterTable(char [] needle) {
		System.out.println("\n***BadCharacterTable***");
		int lastWildCardPos = findWildCards(needle);
		int last = needle.length - 1;
		for(int i = 0; i < 256; i++) {
			badCharacterTable[i] = needle.length;
		}
		for(int i = 0; i < badCharacterTable.length; i++){
			badCharacterTable[i] = lastWildCardPos;
		}
		for(int i = 0; i < last; i++) {
			badCharacterTable[(int)needle[i]] = last - i;
		}
		
		// printing BadCharacterTable
		System.out.println("|CHAR\t|SHIFT\t|");
		for (int i = (needle.length-1)-1; i >= 0; i--) {
			System.out.println("| " + needle[i] + "\t| " + badCharacterTable[(int) needle[i]] + "\t|");
		}
	}// end createBadCharacterTable

	
	private void boyerMooreHorsepool(char[] haystack, char[] needle) {
		System.out.println();
		int offset = 0;
		int scan = 0;
		int last = needle.length - 1;
		int maxoffset = haystack.length - needle.length;
		while(offset <= maxoffset) {
			for(scan = last ; scan >= 0 && (needle[scan] == haystack[scan+offset] || needle[scan] == '_'); scan--) {
				if(scan == 0) {
					System.out.println("Needle match at haystack[" + (scan+offset) + "]!");
					printMatch(haystack, needle, (scan+offset));
				}
			}
			offset += badCharacterTable[(int)haystack[last+offset]];
		}
	}// end boyerMooreHorsepool
	
	private void printMatch(char[] haystack2, char[] needle2, int pos){
		pos *= 2;
		System.out.print("[ ");
		for (int i = 0; i < haystack2.length; i++) {
			System.out.print(haystack2[i] + " ");
		}
		System.out.print("]\n");
		while(pos != 0){
			System.out.print(" ");
			pos--;
		}
		System.out.print("[ ");
		for (int x = 0; x < needle2.length; x++) {
			System.out.print(needle2[x] + " ");
		}
		System.out.print("]\n");
	} // end printMatch


	public String readFromFile(String args) {
		File file;
		String thisLine = null;
		file = new File(args);
		try {
			if ( !file.exists()) {
				System.out.println("Feil i lesing av (" + args + ")");
				System.exit(0);
			} else {
				scanner = new Scanner(file);
				thisLine = scanner.nextLine();
				if(thisLine.equals("")){
					System.out.println(args + " er tom");
					System.exit(0);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return thisLine;
	}
}// end readFromFile
