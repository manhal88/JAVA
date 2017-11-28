import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ReadFromFile {

	Scanner scanner;
	File file = new File("dictionary.txt");
	String thisLine;
	int size = 0;
	void readFromFile(BinarySearchTree bst){
		try {
			if ( !file.exists()) {
				System.out.println("Du mangler dictionary.txt");
				return ;
			} else {
				scanner = new Scanner(file);
				thisLine = scanner.nextLine();
				size++;
				bst.add(thisLine);
				while (scanner.hasNextLine()) {
					thisLine = scanner.nextLine();
					size++;
					bst.add(thisLine);
				}
				scanner.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
