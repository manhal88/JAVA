import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;



public class ReadTheFile {


	String  thisLine = null;
	//ArrayList<String> rettNavn = new ArrayList<String>();
	void startRead(MatRegister mr){
		try {

			BufferedReader br = new BufferedReader(new FileReader("src/MatListe.txt"));
			br.readLine();// hopp over forste linje
			while ((thisLine = br.readLine()) != null) {
				String[] navn = thisLine.split(";");
				String[] ingArr = navn[1].split(",");
				Retter rett = new Retter(navn[0], navn[1], ingArr,  navn[2]);
				mr.settInn(rett);
			}
		} catch(Exception e){
			System.out.println("Error while reading file line by line:" + e.getMessage());
		}
	}
}
