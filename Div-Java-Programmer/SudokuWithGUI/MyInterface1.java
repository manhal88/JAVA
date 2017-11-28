
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;

import java.awt.*;

import javax.swing.*;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;



public class MyInterface1 extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	String password = "";
	private JPasswordField passwordField;
	Sudokubuffer buffer = new Sudokubuffer();
	Brett b;
	int dimensjon;
	private int vertikalAntall;
	private int horisontalAntall;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyInterface1 window = new MyInterface1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MyInterface1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		final JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, "name_178789732633269");
		panel_1.setLayout(null);

		final JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, "name_178795552132008");
		panel_2.setLayout(null);

		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try{
					if(password.compareTo(passwordField.getText()) == 0){
						panel_2.setVisible(true);
						panel_1.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "Wrong Password Biatch. ");
						passwordField.setText(null);
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnNewButton.setBounds(167, 220, 89, 23);
		panel_1.add(btnNewButton);

		passwordField = new JPasswordField();
		passwordField.setEchoChar('$');
		passwordField.setBounds(204, 171, 140, 32);
		panel_1.add(passwordField);

		JLabel lblNewLabel = new JLabel("Enter password:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(76, 160, 118, 50);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		//lblNewLabel_1.setIcon(new ImageIcon("D:\\Java\\workspace\\SudokuWithGUI\\sudoku-icon.png"));
		lblNewLabel_1.setIcon(new ImageIcon("sudoku-icon.png"));
		lblNewLabel_1.setBounds(139, 11, 154, 154);
		panel_1.add(lblNewLabel_1);



		JRadioButton rdbtnNewRadioButton6x6 = new JRadioButton("  6 X 6");
		rdbtnNewRadioButton6x6.setSelected(true);
		rdbtnNewRadioButton6x6.setBounds(58, 80, 67, 23);
		rdbtnNewRadioButton6x6.setActionCommand("6");
		panel_2.add(rdbtnNewRadioButton6x6);

		JRadioButton rdbtnNewRadioButton9x9 = new JRadioButton("  9 X 9");
		rdbtnNewRadioButton9x9.setBounds(149, 80, 67, 23);
		rdbtnNewRadioButton9x9.setActionCommand("9");
		panel_2.add(rdbtnNewRadioButton9x9);

		JRadioButton rdbtnNewRadioButton12x12 = new JRadioButton("  12 X 12");
		rdbtnNewRadioButton12x12.setBounds(240, 80, 89, 23);
		rdbtnNewRadioButton12x12.setActionCommand("12");
		panel_2.add(rdbtnNewRadioButton12x12);

		JRadioButton rdbtnNewRadioButton16x16 = new JRadioButton("  16 X 16");
		rdbtnNewRadioButton16x16.setBounds(331, 80, 89, 23);
		rdbtnNewRadioButton16x16.setActionCommand("16");
		panel_2.add(rdbtnNewRadioButton16x16);

		JRadioButton rdbtnNewRadioButtonText = new JRadioButton("Choose a text file");
		rdbtnNewRadioButtonText.setBounds(161, 150, 131, 23);
		rdbtnNewRadioButtonText.setActionCommand("text");
		panel_2.add(rdbtnNewRadioButtonText);

		JLabel lblNewLabel_2 = new JLabel("Select the size of sudoku");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(142, 18, 150, 43);
		panel_2.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Or");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(206, 118, 21, 23);
		panel_2.add(lblNewLabel_3);

		final ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNewRadioButton6x6);
		group.add(rdbtnNewRadioButton9x9);
		group.add(rdbtnNewRadioButton12x12);
		group.add(rdbtnNewRadioButton16x16);
		group.add(rdbtnNewRadioButtonText);

		JButton btnNewButton_1 = new JButton("OK");
		btnNewButton_1.setBounds(167, 220, 89, 23);
		panel_2.add(btnNewButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("<html> 6<BR>2<BR>3<BR>. . 1 . . 3<BR>. . . . . .<BR>. . . . 2 .<BR>2 6 . . . .<BR>. . . 3 . .<BR>. . 1 . 2</html>");
		lblNewLabel_4.setForeground(Color.ORANGE);
		lblNewLabel_4.setBackground(UIManager.getColor("Button.foreground"));
		lblNewLabel_4.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(291, 110, 133, 140);
		panel_2.add(lblNewLabel_4);

		rdbtnNewRadioButton6x6.addActionListener(this);
		rdbtnNewRadioButton9x9.addActionListener(this);
		rdbtnNewRadioButton12x12.addActionListener(this);
		rdbtnNewRadioButton16x16.addActionListener(this);
		rdbtnNewRadioButtonText.addActionListener(this);

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(group.getSelection().getActionCommand().compareTo("text") == 0){
					final JFrame frame = new JFrame("JFileChooser Demo");
					final JFileChooser fc = new JFileChooser();
					fc.setMultiSelectionEnabled(false);
					fc.setCurrentDirectory(new File("C:\\"));

					int retVal = fc.showOpenDialog(frame);
					if (retVal == JFileChooser.APPROVE_OPTION) {
						File selectedfile;
						selectedfile = fc.getSelectedFile();
						Brett b = new Brett();

						b.lesFraFil(selectedfile);
						b.lagLosninger();
					}
				} else {
					b = new Brett();
					if(group.getSelection().getActionCommand().compareTo("6") == 0){
						dimensjon = 6;
						panel_2.setVisible(false);
						lagTomBrett(dimensjon, 2, 3);
					} else if(group.getSelection().getActionCommand().compareTo("9") == 0){
						dimensjon = 9;
						panel_2.setVisible(false);
						lagTomBrett(dimensjon, 3, 3);
					} else if(group.getSelection().getActionCommand().compareTo("12") == 0){
						dimensjon = 12;
						panel_2.setVisible(false);
						lagTomBrett(dimensjon, 3, 4);
					} else if(group.getSelection().getActionCommand().compareTo("16") == 0){
						dimensjon = 16;
						panel_2.setVisible(false);
						lagTomBrett(dimensjon, 4, 4);
					}
				}
			}
		});
	}

	private JPanel lagTomBrett(int dim, int hd, int br) {
		int topp, venstre;
		dimensjon = dim;
		vertikalAntall = hd;
		horisontalAntall = br;
		JTextField[][] brett;
		final int[][] ruter;
		JTextField ruten;
		final JPanel brettPanel = new JPanel();
		JPanel knappPanel = new JPanel();
		brett = new JTextField[dimensjon][dimensjon];
		ruter = new int[dimensjon][dimensjon];
		this.setPreferredSize(new Dimension(dimensjon * 50, dimensjon * 50 + 50));
		this.setTitle("Sudoku " + dimensjon +" x "+ dimensjon );
		getContentPane().setLayout(new BorderLayout());
		brettPanel.setLayout(new GridLayout(dimensjon,dimensjon));
		brettPanel.setAlignmentX(CENTER_ALIGNMENT);
		brettPanel.setAlignmentY(CENTER_ALIGNMENT);
		for(int i = 0; i < dimensjon; i++) {
			topp = (i % vertikalAntall == 0 && i != 0) ? 4 : 1;
			for(int j = 0; j < dimensjon; j++) {
				venstre = (j % horisontalAntall == 0 && j != 0) ? 4 : 1;
				ruten = new JTextField(i+""+j);
				ruten.setBorder(BorderFactory.createMatteBorder(topp,venstre,1,1, Color.GRAY));
				ruten.setHorizontalAlignment(SwingConstants.CENTER);
				ruten.setPreferredSize(new Dimension(50, 50));
				ruten.setText("");
				brett[i][j] = ruten;
				brettPanel.add(ruten);
			}
		}

		knappPanel.setLayout(new BoxLayout(knappPanel, BoxLayout.X_AXIS));
		JButton ok = new JButton("OK");
		knappPanel.add(ok);
		this.getContentPane().removeAll();
		this.getContentPane().add(knappPanel,BorderLayout.NORTH);
		this.getContentPane().add(brettPanel,BorderLayout.CENTER);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev){
				int teller1 = 0;
				int teller2 = 0;
				for (Component c : brettPanel.getComponents()) {
					if (c instanceof JTextField) {
						if(teller1 == dimensjon){
							teller1 = 0;
							teller2++;
						}
						if ( ((JTextField)c).getText().compareTo("") == 0) {
							((JTextField)c).setText("0");
						}
						ruter[teller2][teller1] = Integer.parseInt(((JTextField)c).getText());
						teller1++;
					}
				}
				setVisible(false);
				b.lesFraArray(dimensjon, vertikalAntall, horisontalAntall, ruter);
			}
		});
		return brettPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
