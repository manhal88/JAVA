import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Interface {

	Scanner in = new Scanner(System.in);
	String name = null;

	void Interface(BinarySearchTree bst) {

		int order = 0;
		String line = null;
		String[] temp = new String[0];//temp[] have all the words that is not in the list and the result from spell-check. temp[] helping to print the words to file
		while (order != 3) {
			System.out.println("");
			System.out.println("***   Choose one from the following:   ***");
			System.out.println("*  1)  Search                            *");
			System.out.println("*  2)  Add                               *");
			System.out.println("*  3)  Quit                              *");
			System.out.println("*                                        *");
			System.out.println("***   Choose between (1-3):            ***");
			System.out.print(">");
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				line = br.readLine();
				order = Integer.parseInt(line);
			} catch (NumberFormatException ex) {
				System.err.println("Not a valid number: " + line);
				order = 0;
			} catch (IOException e) {
				System.err.println("Unexpected IO ERROR: " + e);
			}

			if (order >= 4) {
				System.out.println("*** (" + order + ") is wrong choice. Try again...  ***");
				System.out.println("");
				order = 0;
			}

			switch (order) {
			case 1: temp = SearchInDictionary(bst , temp); break;
			case 2: AddInDictionary(bst); break;
			case 3: Avslutt(bst , temp); break;
			}
		}
	}
	// search and spell-check
	// printing out the result and put them in temp[]
	String[] SearchInDictionary(BinarySearchTree bst, String[] temp){

		System.out.println("Enter a word");
		System.out.print(">");
		name = in.next().toLowerCase();
		if(name.length() > 1){
			if(bst.search(name)){
				System.out.println("(" + name + ") is found in the list");
			} else {
				int teller = 0;
				System.out.println("(" + name + ") is not found in the list");
				//(addAndExpand) extend the String[] and add a string to the end
				temp = addAndExpand(temp, ";[" + name + "]>");// adding the word to temp
				System.out.println("***   Swapping One Letter              ***");
				long startTime = System.nanoTime();
				String[] names1 = swapOneChar(name);
				names1 = removeDuplicate(names1);//if found, remove one of two semiler word
				for (int i = 0; i < names1.length; i++){
					if(bst.search(names1[i])){
						System.out.println(" -" + names1[i]);
						temp = addAndExpand(temp, names1[i]);
						teller++;
					}
				}
				System.out.println(" -Total matches :" + teller);
				teller = 0;
				String[] names2 = exchangeOneChar(name);
				names2 = removeDuplicate(names2);
				System.out.println("***   Exchange One Letter              ***");
				for (int i = 0; i < names2.length; i++){
					if(bst.search(names2[i])){
						System.out.println(" -" + names2[i]);
						temp = addAndExpand(temp, names2[i]);
						teller++;
					}
				}
				System.out.println(" -Total matches :" + teller);
				teller = 0;
				String[] names3 = addOneChar(name);
				names3 = removeDuplicate(names3);
				System.out.println("***   Add One Letter                   ***");
				for (int i = 0; i < names3.length; i++){
					if(bst.search(names3[i])){
						System.out.println(" -" + names3[i]);
						temp = addAndExpand(temp, names3[i]);
						teller++;
					}
				}
				System.out.println(" -Total matches :" + teller);
				teller = 0;
				String[] names4 = removeOneChar(name);
				names4 = removeDuplicate(names4);
				System.out.println("***   Remove One Letter                ***");
				for (int i = 0; i < names4.length; i++){
					if(bst.search(names4[i])){
						System.out.println(" -" + names4[i]);
						temp = addAndExpand(temp, names4[i]);
						teller++;
					}
				}
				System.out.println(" -Total matches :" + teller);
				teller = 0;
				long endTime = System.nanoTime();
				System.out.println("That took " + ((double)(endTime - startTime)/ 1000000) + " Milliseconds");
			}
		} else {
			System.out.println("can't search for one letter");
		}
		return temp;
	}
	//(addAndExpand) extend the String[] and add a string to the end
	private String[] addAndExpand(String[] array, String word) {
		int size = array.length + 1;
		String[] temp = new String[size];
		System.arraycopy(array, 0, temp, 0, array.length);
		for(int j = array.length; j < size; j++){
			temp[j] = word;
		}
		return temp;
	}
	//removing one of two semiler word after each other
	private String[] removeDuplicate(String[] array) {
		int size = array.length;
		String[] temp = new String[size];
		String current = array[0];
		int teller = 0;
		boolean found = false;
		for (int i = 0; i < array.length; i++) {
			if (current.equals(array[i]) && found == false) {
				found = true;
			} else if(!current.equals(array[i])){
				temp[teller] = current;
				current = array[i];
				teller++;
				found = false;
			}
		}
		temp[teller] = current;
		String[] temp2 = new String[teller+1];
		for(int i = 0; i < temp2.length; i++){
			temp2[i] = temp[i];
		}
		return temp2;
	}
	//add 
	void AddInDictionary(BinarySearchTree bst) {
		System.out.println("Enter name");
		System.out.print(">");
		name = in.next().toLowerCase();
		if(bst.search(name)){
			System.out.println("(" + name + ") is already found in the list");
		} else {
			bst.add(name);
			System.out.println("(" + name + ") is added to the list");
		}
	}

	void Avslutt(BinarySearchTree bst, String[] temp) {
		writeToFile(bst , temp);
		System.out.println("System out");
		System.exit(0);
	}

	public String[] swapOneChar(String word){
		char[] word_array = word.toCharArray();
		char[] tmp;

		String[] words = new String[word_array.length-1];
		for(int i = 0; i < word_array.length - 1; i++){
			tmp = word_array.clone();
			words[i] = swap(i, i+1, tmp);
		}
		return words;
	}
	public String swap(int a, int b, char[] word){
		char tmp = word[a];
		word[a] = word[b];
		word[b] = tmp;
		return new String(word);
	}

	public String[] exchangeOneChar(String ord){
		char[] word_array = ord.toCharArray();
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		char[] tmp;
		int words_length = word_array.length*alphabet.length;
		int teller = 0;
		String[] words = new String[words_length];	
		for(int i = 0; i < word_array.length ; i++){
			for(int x = 0; x < alphabet.length ; x++){
				tmp = word_array.clone();
				tmp[i] = alphabet[x];
				words[teller] = new String(tmp);
				teller++;
			}
		}
		return words;
	}

	public String[] addOneChar(String ord){

		char[] word_array = ord.toCharArray();
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		int wordsLength = (word_array.length + 1)* alphabet.length;
		StringBuilder sb= new StringBuilder(ord);
		String[] words = new String[wordsLength];
		int teller = 0;
		for(int x = 0; x < alphabet.length ; x++){
			for(int y = 0; y < word_array.length +1; y++){
				sb.insert(y, alphabet[x]);
				words[teller] = new String(sb);
				sb = new StringBuilder(ord);
				teller++;
			}
		}
		return words;
	}

	public String[] removeOneChar(String ord){

		char[] word_array = ord.toCharArray();
		StringBuilder sb= new StringBuilder(ord);
		String[] words = new String[word_array.length];
		int teller = 0;
		for(int y = 0; y < word_array.length; y++){
			sb.deleteCharAt(y);
			words[teller] = new String(sb);
			sb = new StringBuilder(ord);
			teller++;
		}
		return words;
	}

	public void writeToFile(BinarySearchTree bst, String[] temp) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		int[] depth = new int[bst.depthOfTree()+1];
		depth = bst.DepthEachNode(depth);
		PrintWriter file;
		try {
			file = new PrintWriter("utskrift.txt");

			file.write("*****(" + dateFormat.format(date) + ")*****");
			file.println();
			for(int i = 0; i < temp.length; i++){
				if(temp[i].startsWith(";")){
					file.println();
				}
				file.write(" " + temp[i]);
			}

			file.println();
			file.write(" ***** Tree statistics *****");
			file.println();
			file.write(" * The depth of the tree is: " + bst.depthOfTree());
			file.println();
			file.write(" * Number of nodes for each depth: ");
			file.println();
			for (int i=0; i< depth.length; i++) {
				file.write("    Depth:" + i + "  Number of Nodes:"+depth[i]);
				file.println();
			}
			file.println();
			file.write(" * The average depth of all the nodes :" + bst.averageDepth(depth));
			file.println();
			file.write(" * The alphabetically first word is: " + bst.findMinimum());
			file.println();
			file.write(" * The alphabetically last  word is: " + bst.findMaximum());
			file.close();
		} catch (FileNotFoundException e) {
			System.err.println("Error at PrintWriter");
		}
	}
}
