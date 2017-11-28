import javax.swing.*;
import java.awt.*;

class Firkant extends JPanel {
	Firkant() {
		setPreferredSize(new Dimension(300, 300));
	}
	int i = 0;
	public void paintComponent(Graphics g) {
		// super.paintComponent(g);

		g.setColor(Color.blue);
		g.fillRect(50+i, 50+i, 100+i, 100);
		g.setColor(Color.white);
		g.drawRect(50+i, 50+i, 100+i, 100);
	}
	public void tegnNy ( ){ i+=10; repaint(); }
}

class FirkantDemo extends JFrame {
	FirkantDemo() {
		setTitle("En firkant");
		Container lerret = getContentPane();
		Firkant firkant = new Firkant();
		lerret.add(firkant);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocation(10,50);
		setVisible(true);
		for(int i=0; i <= 10; i++){
		   firkant.tegnNy();
                   try {Thread.sleep(2000);} catch(Exception e) { }
                 } 
	}
	public static void main(String[] args) {
		new FirkantDemo();
	}
}
