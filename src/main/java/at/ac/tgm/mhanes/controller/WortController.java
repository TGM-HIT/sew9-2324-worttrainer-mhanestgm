package at.ac.tgm.mhanes.controller;

import at.ac.tgm.mhanes.model.Rechtschreibtrainer;
import at.ac.tgm.mhanes.model.WortListe;
import at.ac.tgm.mhanes.model.WortPaar;
import at.ac.tgm.mhanes.view.WortFrame;
import at.ac.tgm.mhanes.view.WortPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

import javax.swing.JOptionPane;


/**
 * Controller des Rechtschreibtrainers, zusammen mit dem Frame, Panel und Model wird hier eine Struktur gebildet.
 * Man kann ein Wort zum Trainer hinzufügen, die Wortliste speichern und eine andere laden und Spielstand zurücksetzen.
 * @author Matus Hanes
 * @version 02.10.2023
 *
 */
public class WortController implements ActionListener {

	private WortPanel panel;
	private final Rechtschreibtrainer trainer;
	
	WortController() {
		
		try {
			panel = new WortPanel(this);
		} catch (MalformedURLException ex) {
			JOptionPane.showMessageDialog(null, "URL nicht gefunden!", "Fehler", JOptionPane.ERROR_MESSAGE);
		}

		new WortFrame("Wort-Trainer", panel);
		trainer = new Rechtschreibtrainer(new WortListe());
		//streamer = new WortSerial(trainer);
		//liste.addPaar("Hund", "https://www.pinclipart.com/picdir/middle/20-206356_wenn-hund-clipart.png");
		trainer.getRandomPaar();
	}
	
	public static void main(String[] args) {
		new WortController();
	}
	
	/**
	 * Checkt welcher Button geklickt wurde / ob der Benutzer enter gedrückt hat.
	 * reset bedeutet "Zurücksetzen"
	 * add "bedeutet Wort hinzufügen"
	 * load bedeutet "Laden"
	 * save bedeuter "Speichern"
	 * 
	 * @param e Das Event zum Auffangen.
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {

		String filepfad;

		switch (e.getActionCommand()) {
		
			case "reset":

				int ergebnis = JOptionPane.showConfirmDialog(null, "Bist du dir sicher, dass du den Spielstand zurücksetzen willst?",
						"Überprüfung", JOptionPane.YES_NO_OPTION);
				
				if (ergebnis == JOptionPane.YES_OPTION) {
					panel.reset();
					trainer.resetAnzCheckTrue();
				}
				
				break;
				
			case "add":
				
				boolean ungueltig = true;
				boolean geschlossen = false;
				String wort, url;
					
				do {
			
						wort = JOptionPane.showInputDialog("Bitte geben Sie ein Wort ein.");
						
						if (wort != null) {

							for (int i = 0; i < wort.length(); i++) {
								if (Character.isLetter(wort.charAt(i))) {
									if (i == wort.length() - 1) {
										ungueltig = false;
									}
								} else if (!Character.isLetter(wort.charAt(i))) {
									JOptionPane.showMessageDialog(null, "Ohne Sonderzeichen!", "Fehler", JOptionPane.ERROR_MESSAGE);
									break;
								} 
							}
						} else {
							System.err.println("Fenster geschlossen.");
							geschlossen = true;
						}
	
				} while (ungueltig && !geschlossen);				
				
				ungueltig = true;

				if (!geschlossen) {
					do {
						url = JOptionPane.showInputDialog("Bitte geben Sie eine dazugehörige URL ein.");
						if (url == null) {
							System.err.println("Fenster geschlossen.");
							geschlossen = true;
						} else if (WortPaar.checkURL(url)) {
							ungueltig = false;
						} else {
							JOptionPane.showMessageDialog(null, "Ohne Sonderzeichen!", "Fehler", JOptionPane.ERROR_MESSAGE);
						}
						
					} while (ungueltig && !geschlossen);
					
					if (url != null) {
						trainer.getListe().addPaar(new WortPaar(wort, url));
					}
				}
				
				break;
			case "load":
					filepfad = JOptionPane.showInputDialog("Bitte Pfad zum Laden einer Datei angeben. (Serialisiert!)");

                    //try {
                        if (filepfad != null) {
                            //streamer.laden(filepfad);
                            ungueltig = false;
                        } else {
                            System.err.println("Fenster geschlossen.");
                            geschlossen = true;
                        }
                    /*} catch (ClassNotFoundException ex1) {
                        ex1.printStackTrace();
                    } catch (IOException ex2) {
                        JOptionPane.showMessageDialog(null, "Ungültiger Filepfad!", "Fehler", JOptionPane.ERROR_MESSAGE);
                    }*/

                    if (filepfad != null) {

                        //trainer = streamer.getTrainer();

                        try {
                            panel.setIcon(trainer.getRandomPaar().getURL());
                        } catch (MalformedURLException ex) {
                            JOptionPane.showMessageDialog(null, "URL nicht gefunden!", "Fehler", JOptionPane.ERROR_MESSAGE);
                        }
                    }
				
				break;
				
			case "save":

				ungueltig = true;
				geschlossen = false;

				do {
					filepfad = JOptionPane.showInputDialog("Bitte Pfad zum Speichern der Datei angeben. (Serialisiert!)");
						//try {
							if (filepfad != null) {
								//streamer.speichern(filepfad);
								ungueltig = false;
							} else {
								System.err.println("Fenster geschlossen.");
								geschlossen = true;
							}
						/*} catch (IOException ex) {
							JOptionPane.showMessageDialog(null, "Ungültiger Filepfad!", "Fehler", JOptionPane.ERROR_MESSAGE);
							ex.printStackTrace();
						}*/
				} while (ungueltig && !geschlossen);

				break;
				
			case "enter":
				
				if (trainer.checkIgnoreCase(panel.getText())) {
					for (int i = 0; i < panel.getText().length(); i++) {
						
						if (i == panel.getText().length() - 1) {

							panel.zahlenSetzenRichtig(trainer.getAnzCheckTrue(), trainer.getListe().length());
							JOptionPane.showMessageDialog(null, "Deine Antwort ist richtig.", "Gut gemacht!", JOptionPane.INFORMATION_MESSAGE);
							
							try {
								panel.setIcon(trainer.getRandomPaar().getURL());
							} catch (MalformedURLException ex) {
								JOptionPane.showMessageDialog(null, "URL nicht gefunden!", "Fehler", JOptionPane.ERROR_MESSAGE);
								System.err.println(ex.getMessage());
							}
                        } else {
							JOptionPane.showMessageDialog(null, "Ohne Sonderzeichen!", "Fehler", JOptionPane.ERROR_MESSAGE);
						}
					}

					
				} else {
					panel.zahlenSetzenFalsch(trainer.getAnzCheckTrue(), trainer.getListe().length());
					JOptionPane.showMessageDialog(null, "Diese Antwort ist falsch.", "Nicht so ganz", JOptionPane.ERROR_MESSAGE);
				}
				
				panel.weissFaerben();
				panel.feldLeeren();
				break;
				
		}
	}
}

