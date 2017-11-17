

/*
 * manhal al-khayyat
 * mmalkhay
 * 
 */

import java.io.File;
import javax.swing.*;

public class Oblig3 {
	public static void main(String[] args){

		if (args.length == 0) {
			/*
			 * JFileChooser starter programet med � velge en fil. s� kaller p� les fra fil metode i brett. s� lager den losninger
			 */
			final JFrame frame = new JFrame("JFileChooser Demo");
			final JFileChooser fc = new JFileChooser();
			fc.setMultiSelectionEnabled(false);
			fc.setCurrentDirectory(new File("C:\\tmp"));

			int retVal = fc.showOpenDialog(frame);
			if (retVal == JFileChooser.APPROVE_OPTION) {
				File selectedfile;
				selectedfile = fc.getSelectedFile();
				Brett b = new Brett();

				b.lesFraFil(selectedfile);
				b.lagLosninger();
			}
		} else if (args.length == 1) {
			Brett b = new Brett();
			File fil = new File(args[0]);
			b.lesFraFil(fil);
			b.lagLosninger();
		} else {
			String tilFil = args[1];
			Brett b = new Brett(tilFil);
			File fil = new File(args[0]);
			b.lesFraFil(fil);
			b.lagLosninger();
		}
		System.out.println("hadet bra");
	}
}
