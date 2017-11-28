
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class cardlayout extends JFrame implements MouseListener {


	public static CardLayout card = new CardLayout();

	
	public static JPanel container = new JPanel();
	public static JPanel panel1 = new JPanel();
	public static JPanel panel2 = new JPanel();
	public static JPanel panel3 = new JPanel();
	public static JPanel panel4 = new JPanel();

	private static JButton bt[] = new JButton[4];
	
	
//this is a simple text for itch button in these panels 
	//and it's will help you to know in which panel you're really are
	private String[] text = {"PANEL 1 ","PANEL 2",
			"PANEL 3 ","PANEL 4"};

	// this is the constructor
	public cardlayout() {
//setting the null layout for all panels 
		//and we set the cardlayout option in the layout of container
		container.setLayout(card);
		panel1.setLayout(null);
		panel2.setLayout(null);
		panel3.setLayout(null);
		panel4.setLayout(null);
		
	
//here we're initiating the 4 buttons
		for (int i = 0; i < bt.length; i++) {
			
		bt[i]=new JButton(""+text[i]) ;
		bt[i].setBounds(100, 150, 100, 40);
		bt[i].addMouseListener(this) ;
		// i did use the mouselistener , you can use the action listener too 
		}

	
		// I ADDED THE BUTTON "panel[i]" to all panels except the 
	//container panel
		panel1.add(bt[0]);

		panel2.add(bt[1]);

		panel3.add(bt[2]);
		
		panel4.add(bt[3]);
		
// we give to itch panel a color 
		panel1.setBackground(Color.red);
		panel2.setBackground(Color.blue);
		panel3.setBackground(Color.DARK_GRAY);
		panel4.setBackground(Color.green);

//	 setLayout(null) ;// you must not set the layout to the frame , remember that ;) 
		
		
		setSize(500, 500); // this is the frame size and down is location and 
		//the close option
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		
		//we add these panels to the container
		//and we give to itch one of its
		//a number
		//container.add(panel1, "2");//first add
		container.add(panel1, "2");//first add
		container.add(panel2, "3");
		container.add(panel3, "4");
		container.add(panel4, "5");
//that will show you the first added panel ("2") that you add it to 
		//this container("1")
		card.show(container, "1");

	}
// the main function 
	public static void main(String[] args) {

		cardlayout c = new cardlayout();

		c.add(container); // here we just add this container to the frame
		c.setVisible(true);

	}
// we need here just the mouseclicked method
	@Override
	public void mouseClicked(MouseEvent e) {

	
		for (int i = 0; i < bt.length; i++) {//here we loop throw these buttons
			
		if (e.getSource() == bt[0]) {// panel 1
			card.show(container, "" + 3); //take you to the next panel
		
			
		}
		if (e.getSource() == bt[1]) {// panel 2
			card.show(container, "" + 4);//take you to the next panel
		
			
		}
		if (e.getSource() == bt[2]) {//panel 3
			card.show(container, "" + 5);//take you to the next panel
		
			
		}
		if (e.getSource() == bt[3]) {//panel 4 
			card.show(container, "" + 2);// will bring u back to panel 1 
		
			
		}

	}
	
	}
	

	
	
	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
