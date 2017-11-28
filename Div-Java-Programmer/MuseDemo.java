import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MuseDemo extends JFrame {
	MuseDemo() {
		setTitle("Musedemo");
		Muse mus = new Muse();
		mus.addMouseMotionListener(mus);
		getContentPane().add(mus, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	public static void main(String[] args) {
		new MuseDemo();
	}
}

class Muse extends JPanel implements MouseMotionListener {
	int [] x = new int[1000],
	          y= new int[1000];
	int ant = 0;

	Muse() { setPreferredSize(new Dimension(500, 500));
	}
       // De to metodene i MouseMotionListener
	public void mouseDragged (MouseEvent e) {
		 if(ant ==x.length) ant = x.length-1;
		 x[ant] = e.getX();
		 y[ant] = e.getY();
		  ant++;
		 repaint();  // her ber vi om at vهr paint() skal kalles 
	}	
	public void mouseMoved (MouseEvent e) { };

	public void paintComponent (Graphics g) {
	// Her tegner vi
		for (int i=1; i<ant; i++)
		    g.drawLine(x[i-1],y[i-1],x[i],y[i]);
	}
}

