package at.ac.tgm.mhanes;

import at.ac.tgm.mhanes.model.WortListe;
import at.ac.tgm.mhanes.model.WortPaar;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Dient als Testklasse für die WortListe.
 * Es wird das Hinzufügen und Löschen von Paaren als auch getPaar mittels Index getestet.
 *
 * @author Matus Hanes
 * @version 01.10.2023
 */

public class TestWortliste {

    private static WortListe liste;

    @BeforeAll
    static void initListe() {
        liste = new WortListe();
    }

    @Test
    @DisplayName("Gültiges Wortpaar zu Liste hinzufügen")
    void addWortpaarZurListe() {
        WortPaar p = new WortPaar("Hund", "https://www.pinclipart.com/picdir/middle/20-206356_wenn-hund-clipart.png");
        assertTrue(liste.addPaar(p));
    }

    @Test
    @DisplayName("null sollte nicht zu Wortliste hinzugefügt werden")
    void addNullZurListe() {
        WortPaar p = null;
        assertThrows(IllegalArgumentException.class, () -> liste.addPaar(p));
    }

    @Test
    @DisplayName("Lösche Paar je nach Reihenfolge")
    void loeschePaar() {
        WortPaar p = new WortPaar("Hund", "https://www.pinclipart.com/picdir/middle/20-206356_wenn-hund-clipart.png");
        liste.addPaar(p);
        assertTrue(liste.removePaar(p));
    }

    @Test
    @DisplayName("Wortpaar mit genau zwei Buchstaben in Ordnung")
    void addPaarMitGenauZweiBuchstaben() {
        WortPaar p = new WortPaar("Hu", "https://www.pinclipart.com/picdir/middle/20-206356_wenn-hund-clipart.png");
        assertTrue(liste.addPaar(p));
    }

    @Test
    @DisplayName("Beim Clearen hat die Liste eine Länge von 0")
    void clearListe() {
        liste.clear();
        assertEquals(0, liste.length());
    }

    @Test
    @DisplayName("getPaar mit negativem Index")
    void getPaarMitNegativemIndex() {
        liste.clear();
        WortPaar p = new WortPaar("Hund", "https://www.pinclipart.com/picdir/middle/20-206356_wenn-hund-clipart.png");
        liste.addPaar(p);
        assertThrows(IndexOutOfBoundsException.class, () -> liste.getPaar(-1));
    }

    @Test
    @DisplayName("getPaar mit Index gleich 0")
    void getPaarMitIndex0() {
        liste.clear();
        WortPaar hund = new WortPaar("Hund", "https://www.pinclipart.com/picdir/middle/20-206356_wenn-hund-clipart.png");
        WortPaar katze = new WortPaar("Katze", "https://www.pinclipart.com/picdir/middle/20-206356_wenn-hund-clipart.png");
        liste.addPaar(hund);
        liste.addPaar(katze);
        assertEquals(liste.getPaar(0), hund);
    }

    @Test
    @DisplayName("getPaar mit positivem Index (gleich 1)")
    void getPaarMitPositivemIndex() {
        liste.clear();
        WortPaar hund = new WortPaar("Hund", "https://www.pinclipart.com/picdir/middle/20-206356_wenn-hund-clipart.png");
        WortPaar katze = new WortPaar("Katze", "https://www.pinclipart.com/picdir/middle/20-206356_wenn-hund-clipart.png");
        liste.addPaar(hund);
        liste.addPaar(katze);
        assertEquals(liste.getPaar(1), katze);
    }

}
