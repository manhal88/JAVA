import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/** Manhal Al.khayyat
 * mmalkhay 
 * Oblig4 INF1010*/


public class Oblig4 extends JFrame {
	public static void main(String[] args){
		if (args.length < 3) {
			new Vindu();

		} else if (args.length == 3) {

			int antalT = Integer.parseInt(args[0]);
			File innFil = new File(args[1]);
			File tilFil = new File(args[2]);
			Files f = new Files(antalT, innFil, tilFil);
			f.start();
		}
		else {
			System.out.println("feil kommand");
		}
	} // end main
} // end Oblig4

class Vindu extends JFrame{

	File tilFil;
	File selectedfile;
	int antalT;

	private Lytter knappelytter;
	private Lytter2 knappelytter2;
	JFrame frame = new JFrame("JFileChooser Demo");
	JButton knapp = new JButton("OK");
	JButton knapp2 = new JButton("Velg file");
	JLabel etikett = new JLabel("Skriv antal tr�er");
	JLabel etikett2 = new JLabel("Skriv UtFile navn");
	JTextField tekstfelt = new JTextField(30);
	JTextField tekstfelt2 = new JTextField(30);

	public Vindu( ) {

		setSize(300, 130);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(new GridLayout(3, 2));
		add(etikett, BorderLayout.NORTH);
		add(tekstfelt,BorderLayout.EAST);
		add(etikett2, BorderLayout.NORTH);
		add(tekstfelt2,BorderLayout.EAST);
		add(knapp,BorderLayout.SOUTH);
		add(knapp2,BorderLayout.SOUTH);
		
		knappelytter = new Lytter();
		knapp.addActionListener(knappelytter);
		knappelytter2 = new Lytter2();
		knapp2.addActionListener(knappelytter2);

	}
	class Lytter implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			File tilFil = new File(tekstfelt2.getText());
			antalT = tekstfelt.getX();

			Files f = new Files(antalT, selectedfile, tilFil);
			f.start();
		}
	}

	class Lytter2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			final JFrame frame = new JFrame("JFileChooser Demo");
			final JFileChooser fc = new JFileChooser();
			fc.setMultiSelectionEnabled(false);
			fc.setCurrentDirectory(new File("C:\\tmp"));

			int retVal = fc.showOpenDialog(frame);
			if (retVal == JFileChooser.APPROVE_OPTION) {

				selectedfile = fc.getSelectedFile();

			}
		}
	}
}
