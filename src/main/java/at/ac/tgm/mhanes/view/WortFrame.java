package at.ac.tgm.mhanes.view;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Das Fenster, welches das Panel beinhaltet wird hier konfiguriert.
 * @author Matus Hanes
 * @version 29.09.2023
 *
 */
public class WortFrame extends JFrame {
	
	public WortFrame(String titel, WortPanel panel) {
		super(titel);
		super.add(panel);
		super.setPreferredSize(new Dimension(700, 600));
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.pack();
		super.setLocationRelativeTo(null); 
		super.setVisible(true);
	}
	
}
