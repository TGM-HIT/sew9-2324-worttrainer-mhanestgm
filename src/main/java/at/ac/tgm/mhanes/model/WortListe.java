package at.ac.tgm.mhanes.model;

import java.util.ArrayList;

/**
 * Stellt eine Liste anhand eines ArrayList-Attributes mit WortPaaren dar.
 * Es können Paare (mit nur einer Referenz pro Liste) hinzugefügt und gelöscht werden.
 *
 * @author Matus Hanes
 * @version 29.09.2023
 */

public class WortListe  {

    private final ArrayList<WortPaar> woerter;

    /**
     * Startwert 0 für Länge der Liste mit den Paaren
     */
    public WortListe() {
        this.woerter = new ArrayList<>(10);
    }

    /**
     * Fügt ein Wortpaar zur Liste hinzu.
     */

    public boolean addPaar(WortPaar paar) throws IllegalArgumentException {
        if (paar == null) {
            throw new IllegalArgumentException("Das Wortpaar darf nicht null sein.");
        }
        return woerter.add(paar);
    }

    /**
     * Gibt den Wortpaar an einem bestimmten Index zurück.
     *
     * @param index Der Index
     * @return Das Wortpaar
     * @throws IllegalArgumentException Wenn Index kleiner 0 oder größer als Länge - 1
     */

    public WortPaar getPaar(int index) {
        return woerter.get(index);
    }

    /**
     * Löscht ein Wortpaar aus der Liste anhand einer Bezeichnung.
     * @param paar Das Wortpaar
     * @throws IllegalArgumentException Wenn Bezeichnung im Parameter null ist.
     */

    public boolean removePaar(WortPaar paar) throws IllegalArgumentException {

        if (paar == null) {
            throw new IllegalArgumentException("Das Wortpaar darf nicht null sein.");
        }

        return woerter.remove(paar);

    }

    /**
     * Gibt die Worteinträge jeweils in einer Zeile als String zurück.
     * @return Liste mit Einträgen
     */

    @Override
    public String toString() {

        StringBuilder ausgabe = new StringBuilder();
        int stelle = 1;
        for (WortPaar paar : woerter) {
            ausgabe.append("[").append(stelle).append("] ").append(paar.toString()).append("\n");
            stelle++;
        }

        return ausgabe.toString();
    }

    /**
     * Gibt die Länge der Liste zurück.
     * @return Anzahl Einträge
     */
    public int length() {
        return woerter.size();
    }

    /**
     * Cleart die Liste von allen Einträgen
     */

    public void clear() {
        woerter.clear();
    }
}
