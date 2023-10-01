package at.ac.tgm.mhanes.model;

import org.apache.commons.validator.routines.UrlValidator;

/**
 * Der WortPaar besitzt eine Wort und eine dazugehörige URL.
 * Eine Instanz/en dieser Klasse wird/en für den Worttrainer und die WortListe verwendet.
 *
 * @author Matus Hanes
 * @version 29.09.2023
 */

public class WortPaar {

    private String wort;
    private String url;

    /**
     * Legt die Werte für die Attribute fest.
     * @param wort String für das Wort
     * @param url String für die URL
     */

    public WortPaar(String wort, String url) {
        this.setWort(wort);
        this.setURL(url);
    }

    /**
     * Schaut, ob die URL die im Parameter übergeben wurde gültig ist und liefert ein relativ akkurates Ergebnis mit true oder false.
     * @param url Die URL zum Checken
     * @return Ob es sich um eine URL handelt oder nicht.
     */

    public static boolean checkURL(String url) {
        String[] schemes = {"http","https", "ftp"}; // DEFAULT schemes = "http", "https", "ftp"
        UrlValidator urlValidator = new UrlValidator(schemes);
        return urlValidator.isValid(url);
    }

    /**
     * Setzt einen Wert für das Wort im Eintrag.
     *
     * @param wort Der Wert zum Übernehmen
     * @throws IllegalArgumentException Wenn der Parameterwert null oder der Aktualparameter zu kurz (unter 2 Buchstaben) ist.
     */
    public void setWort(String wort) throws IllegalArgumentException {

        if (wort == null) {
            throw new IllegalArgumentException("Für das Wort muss ein Wert angegeben werden!");
        }

        if (wort.length() >= 2) {
            this.wort = wort;
        } else {
            throw new IllegalArgumentException("Wort muss mindestens 2 Buchstaben haben.");
        }

    }

    /**
     * Setzt einen Wert für die URL im Worteintrag.
     *
     * @param url Der Wert zum Übernehmen
     * @throws IllegalArgumentException Wenn der Wert im Parameter null oder weniger als 10 bzw. mehr als 2000 Zeichen hat.
     */

    public void setURL(String url) throws IllegalArgumentException {

        if (checkURL(url)) {
            this.url = url;
        } else {
            throw new IllegalArgumentException("Es wurde keine gültige URL übergeben.");
        }

    }

    /**
     * Gibt das Wort zurück.
     * @return wort
     */

    public String getWort() {
        return wort;
    }

    /**
     * Gibt die URL zurück.
     * @return url
     */
    public String getURL() {
        return url;
    }

    /**
     * Gibt das Wort und die URL des Worteintrages getrennt mit einem Strichpunkt zurück.
     * @return wort;url
     */

    @Override
    public String toString() {
        return this.wort + ";" + this.url;
    }
}
