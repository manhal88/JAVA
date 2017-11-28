import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


@SuppressWarnings("serial")
public class Windows extends JFrame{
	
	MatRegister matRegister;
	MatRegister mr;
	
	
	void windows(MatRegister mr){
		matRegister = mr;
		Lytter1 knappelytter1;
		Lytter2 knappelytter2;
		Lytter3 knappelytter3;
		
		JFrame windows = new JFrame("MatOppskrifter");
		windows.setVisible(true);
		windows.setSize(500,200);
		JPanel box = new JPanel(new GridLayout(2,3,1,1));
		JLabel etikett1 = new JLabel("Velg måten du vil finne en oppskrift på:");
		JLabel etikett2 = new JLabel("");
		JLabel etikett3 = new JLabel("");
		JButton knapp1 = new JButton("Vis antal");
		JButton knapp2 = new JButton("Vis alle");
		JButton knapp3 = new JButton("Søk etter");
		box.add(etikett1);
		box.add(etikett2);
		box.add(etikett3);
		box.add(knapp1);
		box.add(knapp2);
		box.add(knapp3);
		windows.getContentPane().add(box);

		knappelytter1 = new Lytter1();
		knapp1.addActionListener(knappelytter1);
		knappelytter2 = new Lytter2();
		knapp2.addActionListener(knappelytter2);
		knappelytter3 = new Lytter3();
		knapp3.addActionListener(knappelytter3);
	}
	class Lytter1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int antall = matRegister.hentAntall();
			System.out.println("Antal retter: " + antall);
		}
	}
	class Lytter2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			Retter[] alle = matRegister.hentAlle();

			for(Retter rett : alle){
				System.out.println("-- " + rett.hentRettNavn());
			}
		}
	}
	class Lytter3 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Retter rett = matRegister.hentRett("teshrib");
			if( rett == null) return;
			System.out.println("Her er det du har bedt om: \n(" + rett.hentRettNavn()+ ") Ingredienser: (" + rett.ing + ") Framgangsmåte: (" + rett.fgm + ")");
		}
	}
}
