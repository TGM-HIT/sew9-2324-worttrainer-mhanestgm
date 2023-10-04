package at.ac.tgm.mhanes.model;
import java.util.Random;

/**
 * Der Rechtschreibtrainer besitzt eine Liste, ein aktuelles Paar und eine Statistik über die erfolgreichen Checks.
 * Man kann mithilfe der getRandomPaar-Methode ein Objekt zufällig aus der Liste zurückgeben und ein neues aktuelles Paar festlegen.
 * Die check-Methoden übernehmen ein Wort und schauen, ob dieses mit dem aktuellen Paar übereinstimmt.
 *
 * @author Matus Hanes
 * @version 03.10.2023
 */

public class Rechtschreibtrainer {

    private final WortListe liste;
    private WortPaar aktuellesPaar;
    private int anzCheckTrue;
    private int anzCheckFalse;

    /**
     * Legt den Startwert für die Anzahl der erfolgreichen Checks fest (anzCheckTrue auf 0 setzen).
     * Außerdem wird ein Wert für die Liste zugewiesen, der später nicht mehr verändert werden kann, falls dieser jedoch null ist wird eine Exception geworfen.
     * @param liste Der Wert zum Übernehmen
     */
    public Rechtschreibtrainer(WortListe liste) {
        if (liste == null) {
            throw new IllegalArgumentException("Es darf kein null für die WortListe übergeben werden.");
        }
        this.liste = liste;
        anzCheckTrue = 0;
        anzCheckFalse = 0;
    }

    /**
     * Wählt einen zufälliges Paar aus der Liste aus (setzt also einen neuen Wert für das Attribut) und gibt diesen dann anschließend zurück
     * @return Neuer Paar
     */

    public WortPaar getRandomPaar() {
        Random r = new Random();
        int index = r.nextInt(liste.length());
        this.aktuellesPaar = liste.getPaar(index);
        return aktuellesPaar;
    }

    /**
     * Gibt das aktuelle Wortpaar im Attribut zurück.
     * @return paar
     */

    public WortPaar getAktuellesPaar() {
        return aktuellesPaar;
    }

    /**
     * Vergleicht das Wort im Parameter mit dem Wort im aktuellen Paar, gibt true zurück wenn sie übereinstimmen, sonst false.
     * @return Ergebnis des Checks
     */

    public boolean check(String wort) {
        if (aktuellesPaar != null && aktuellesPaar.getWort().equals(wort)) {
            anzCheckTrue++;
            return true;
        }
        anzCheckFalse++;
        return false;
    }

    /**
     * Vergleicht das Wort im Parameter mit dem Wort im aktuellen Paar, gibt true zurück wenn sie übereinstimmen, sonst false.
     * Gegenüber der check-Methode wird hier nur der Inhalt ohne Berücksichtigung auf Groß- und Kleinschreibung verglichen.
     * @return Ergebnis des Checks, ignorieren der Schreibweise
     */

    public boolean checkIgnoreCase(String wort) {
        if (aktuellesPaar != null && aktuellesPaar.getWort().equalsIgnoreCase(wort)) {
            anzCheckTrue++;
            return true;
        }
        anzCheckFalse++;
        return false;
    }

    /**
     * Setzt die Anzahl der richtigen Checks auf 0
     */
    public void resetAnzCheckTrue() {
    	this.anzCheckTrue = 0;
    }

    /**
     * Setzt die Anzahl der falschen Checks auf 0
     */
    public void resetAnzCheckFalse() {
        this.anzCheckFalse = 0;
    }

    /**
     * Gibt die Anzahl der Male wo bei den Checks das Wort im Parameter mit dem Wort im aktuellen Paar übereingestimmt hat zurück.
     * @return Anzahl der Checks wo TRUE zurückkam.
     */

    public int getAnzCheckTrue() {
        return anzCheckTrue;
    }

    /**
     * Gibt die Anzahl der Male wo bei den Checks das Wort im Parameter mit dem Wort im aktuellen Paar NICHT übereingestimmt hat zurück.
     * @return Anzahl der Checks wo FALSE zurückkam.
     */

    public int getAnzCheckFalse() {
        return anzCheckFalse;
    }

    /**
     * Gibt die Liste zurück
     * @return Liste im Trainer
     */
    public WortListe getListe() {
        return liste;
    }

    
}
