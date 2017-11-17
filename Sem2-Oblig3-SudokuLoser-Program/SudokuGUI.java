

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/** 
 * Tegner ut et Sudoku-brett.
 * @author Christian Tryti
 * @author Stein Gjessing
 */
public class SudokuGUI extends JFrame {

	Brett b;
	private final int RUTE_STRELSE = 50;	/* St�rrelsen p� hver rute */
	private final int PLASS_TOPP = 50;	/* Plass p� toppen av brettet */

	private JTextField[][] brett;   /* for � tegne ut alle rutene */
	private int dimensjon;		/* st�rrelsen p� brettet (n) */
	private int vertikalAntall;	/* antall ruter i en boks loddrett */
	private int horisontalAntall;	/* antall ruter i en boks vannrett */

	/** Lager et brett med knapper langs toppen. */
	public SudokuGUI(int dim, int hd, int br, int[][] intArray, Brett b) {
		this.b = b;
		dimensjon = dim;
		vertikalAntall = hd;
		horisontalAntall = br;
		brett = new JTextField[dimensjon][dimensjon];

		setPreferredSize(new Dimension(dimensjon * RUTE_STRELSE, dimensjon  * RUTE_STRELSE + PLASS_TOPP));
		setTitle("Sudoku " + dimensjon +" x "+ dimensjon );
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		JPanel knappePanel = lagKnapper();
		JPanel brettPanel = lagBrettet(intArray);
		getContentPane().add(knappePanel,BorderLayout.NORTH);
		getContentPane().add(brettPanel,BorderLayout.CENTER);
		pack();
		setVisible(true);
	}

	/** 
	 * Lager et panel med alle rutene. 
	 * @param ruter 
	 * @return en peker til panelet som er laget.
	 */
	private JPanel lagBrettet(int[][] intArray) {
		int topp, venstre;
		JPanel brettPanel = new JPanel();
		brettPanel.setLayout(new GridLayout(dimensjon,dimensjon));
		brettPanel.setAlignmentX(CENTER_ALIGNMENT);
		brettPanel.setAlignmentY(CENTER_ALIGNMENT);
		setPreferredSize(new Dimension(new Dimension(dimensjon * RUTE_STRELSE, 
				dimensjon * RUTE_STRELSE)));		
		for(int i = 0; i < dimensjon; i++) {
			/* finn ut om denne raden trenger en tykker linje p� toppen: */
			topp = (i % vertikalAntall == 0 && i != 0) ? 4 : 1;
			for(int j = 0; j < dimensjon; j++) {
				/* finn ut om denne ruten er en del av en kolonne 
				   som skal ha en tykkere linje til venstre:       */
				venstre = (j % horisontalAntall == 0 && j != 0) ? 4 : 1;

				JTextField ruten = new JTextField();
				ruten.setBorder(BorderFactory.createMatteBorder
						(topp,venstre,1,1, Color.GRAY));
				ruten.setHorizontalAlignment(SwingConstants.CENTER);
				ruten.setPreferredSize(new Dimension(RUTE_STRELSE, RUTE_STRELSE));
				if(intArray[i][j] == 0){
					ruten.setText(".");
				} else {
					ruten.setText(""+intArray[i][j]);
				}
				brett[i][j] = ruten;
				brettPanel.add(ruten);
			}
		}
		return brettPanel;
	}

	/** 
	 * Lager et panel med noen knapper. 
	 * @return en peker til panelet som er laget.
	 */
	private JPanel lagKnapper() {
		JPanel knappPanel = new JPanel();
		knappPanel.setLayout(new BoxLayout(knappPanel, BoxLayout.X_AXIS));
		JButton finnSvarKnapp = new JButton("Skriv ut til fil");
		finnSvarKnapp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev){
				b.buffer.skrivAntallLosninger();
			}
		});

		JButton nesteKnapp = new JButton("Neste laasning");
		nesteKnapp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev){
				int[][] losninger = b.buffer.taUt();
			
				for (int w = 0 ; w < brett.length; w++) {
					for ( int h = 0; h< brett.length; h++ ) {
						brett[w][h].setText("" +losninger[w][h]);
					}
				}
			}
		});

		knappPanel.add(nesteKnapp);
		knappPanel.add(finnSvarKnapp);

		return knappPanel;
	}
}
