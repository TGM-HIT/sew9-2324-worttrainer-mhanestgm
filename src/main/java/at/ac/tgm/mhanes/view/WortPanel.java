package at.ac.tgm.mhanes.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import at.ac.tgm.mhanes.controller.WortController;

import java.awt.GridLayout;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Color;

/** 
 * Diese Klasse wird vom Frame verwendet und beinhaltet alle notwendigen Komponenten.
 * Es gibt ein Eingabefeld, ein Bild, sowie ein Display der Anzahl der richtigen und der eingetippten Wörter.
 * Ebenso besteht die Möglichkeit mit dem Button den Stand zurückzusetzen und ein Wort hinzuzufügen.
 * Man kann auch den Worttrainer speichern / einen eigenen laden.
 * 
 * @author Matus Hanes
 * @version 03.10.2023
 *
 */
public class WortPanel extends JPanel {

	private final JTextField feld;
	private final JLabel lImage;
	private Image image;
	private ImageIcon icon;
	private final JPanel center1;
	private final JLabel anzRichtig;
	private final JLabel anzWoerter;

	public WortPanel(WortController wc) throws MalformedURLException {
		super.setLayout(new BorderLayout());
		
		Font f = new Font("Helvetica", Font.ITALIC, 20);
		Font f2 = new Font("Helvetica", Font.BOLD, 20);

		JPanel grid1 = new JPanel(new GridLayout(2, 1, 10, 10));
		JLabel frage = new JLabel("Welches Wort wird unten dargestellt (Eingabe zum Überprüfen?)");
		frage.setFont(f);
		
		feld = new JTextField(5);
		feld.setFont(f2);
		feld.setActionCommand("enter");
		feld.addActionListener(wc);
		
		grid1.add(frage);
		grid1.add(feld);
		super.add(grid1, BorderLayout.PAGE_START);
		
        icon = new ImageIcon("/speicher/placeholder.png");
        
        image = icon.getImage(); // umwandeln in ein Image-Objekt
        image = image.getScaledInstance(250 * icon.getIconWidth() / icon.getIconHeight(), 250, Image.SCALE_SMOOTH); // Proportionales Skalieren

		lImage = new JLabel(new ImageIcon(image)); // anzeigen in einem JLabel
    	
    	super.add(lImage, BorderLayout.CENTER);

		JPanel grid2 = new JPanel(new GridLayout(2, 3, 5, 5));
		JLabel richtigeText = new JLabel("Richtige Wörter: ");

    	richtigeText.setFont(f);
    	
    	anzRichtig = new JLabel("0");
    	anzRichtig.setFont(f);
    	
        center1 = new JPanel(new FlowLayout());
    	center1.add(anzRichtig);
    	center1.setOpaque(true);

		JButton reset = new JButton("Zurücksetzen");
    	reset.setActionCommand("reset");
    	reset.setFont(f2);
    	reset.addActionListener(wc);


		JLabel woerterText = new JLabel("Anzahl Wörter:");
    	woerterText.setFont(f);
    	
    	anzWoerter = new JLabel("1");
    	anzWoerter.setFont(f);
		JPanel center2 = new JPanel(new FlowLayout());
    	center2.add(anzWoerter);

		JButton hinzufuegen = new JButton("Wort hinzufügen");
    	hinzufuegen.setActionCommand("add");
    	hinzufuegen.setFont(f2);
    	hinzufuegen.addActionListener(wc);
    	
    	
    	grid2.add(richtigeText);
    	grid2.add(center1);
    	grid2.add(reset);
    	grid2.add(woerterText);
    	grid2.add(center2);
    	grid2.add(hinzufuegen);

		JPanel grid3 = new JPanel(new GridLayout(1, 2, 10, 10));

		JButton laden = new JButton("Laden");
    	laden.setFont(f2);
    	laden.setActionCommand("load");
    	laden.addActionListener(wc);

		JButton speichern = new JButton("Speichern");
    	speichern.setFont(f2);
    	speichern.setActionCommand("save");
    	speichern.addActionListener(wc);
    	
    	grid3.add(laden);
    	grid3.add(speichern);

		JLabel link = new JLabel("https://github.com/TGM-HIT/sew9-2324-worttrainer-mhanestgm");
    	link.setFont(f);
		JPanel gridPanel = new JPanel(new GridLayout(3, 1));
    
    	gridPanel.add(grid2);
    	gridPanel.add(grid3);
    	gridPanel.add(link);
    	super.add(gridPanel, BorderLayout.PAGE_END);
    	
	}
     		
     /**
      * Gibt den Text aus dem Feld zurück.
      * @return Eingabe
      */
     public String getText() {
    	 return this.feld.getText();
     }
     
     /**
      * Wird vom Controller aufgerufen, wenn das Wort richtig war und setzt die Zahlen auf ihre entsprechenden Werte.
      * Feld mit Anzahl der richtigen Wörter wird GRÜN markiert.
      * 
      * @param anzRichtig Anzahl der richtigen Wörter
      * @param anzWoerter Anzahl gesamten Wörter in der Liste
      */
     public void zahlenSetzenRichtig(int anzRichtig, int anzWoerter) {
    	 this.anzRichtig.setText("" + anzRichtig);
    	 this.anzWoerter.setText("" + anzWoerter);
    	 this.center1.setBackground(Color.GREEN);
    	 
     }
     
     /**
      * Wird vom Controller aufgerufen, wenn das Wort richtig war und setzt die Zahlen auf ihre entsprechenden Werte.
      * Feld mit Anzahl der ricHhtigen Wörter wird ROT markiert.
      * 
      * @param anzRichtig Anzahl der richtigen Wörter
      * @param anzWoerter Anzahl gesamten Wörter in der Liste
      */
     public void zahlenSetzenFalsch(int anzRichtig, int anzWoerter) {
    	 this.zahlenSetzenRichtig(anzRichtig, anzWoerter);
    	 this.center1.setBackground(Color.RED);
     }
     
     /**
      * Setzt die Anzahl der richtigen Wörter in GUI auf 0.
      */
     public void reset() {
    	 this.anzRichtig.setText("0");
     }
     
     /**
      * Setzt die Farbe wieder auf weiß zurück
      */
     public void weissFaerben() {
    	 this.center1.setBackground(new Color(0.925f, 0.925f, 0.925f));
     }
     
     /**
      * Setzt den Wert im Textfeld auf einen leeren String
      */
     public void feldLeeren() {
    	 this.feld.setText("");
     }
     
     /**
      * Setzen des neuen Icons
      * @param url Die URL als stringt
      * @throws MalformedURLException Wenn die URL nicht gefunden wird.
      */
     public void setIcon(String url) throws MalformedURLException {
    	 icon = new ImageIcon(new URL(url));
    	 image = icon.getImage();
    	 image = image.getScaledInstance(250 * icon.getIconWidth() / icon.getIconHeight(), 250, Image.SCALE_SMOOTH); // Proportionales Skalieren
    	 icon.setImage(image);
         lImage.setIcon(icon); // anzeigen in einem JLabel
     }
}
