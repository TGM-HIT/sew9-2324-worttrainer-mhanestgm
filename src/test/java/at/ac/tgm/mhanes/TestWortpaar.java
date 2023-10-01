package at.ac.tgm.mhanes;

import at.ac.tgm.mhanes.model.WortPaar;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static at.ac.tgm.mhanes.model.WortPaar.checkURL;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Diese Klasse ist für das Testen von WortPaar zuständig.
 *
 * @author Matus Hanes
 * @version 29.09.2023
 */

public class TestWortpaar {

    private static WortPaar paar;

    @BeforeAll
    static void initPaar() {
        paar = new WortPaar("Hund", "https://www.pinclipart.com/picdir/middle/20-206356_wenn-hund-clipart.png");
    }
    @Test
    @DisplayName("Wort unter zwei Zeichen nicht erlaubt")
    void setWortUnterZweiZeichen() {
        assertThrows(IllegalArgumentException.class, () -> paar.setWort("H"));
    }

    @Test
    @DisplayName("Wort mit genau zwei Zeichen erlaubt")
    void setWortGleichZweiZeichen() {
        paar.setWort("Ja");
        assertEquals("Ja", paar.getWort());
    }

    @Test
    @DisplayName("URL mit falscher Scheme (kein http, https oder ftp) liefert Exception")
    void setURLOhneScheme() {
        assertThrows(IllegalArgumentException.class, () -> paar.setURL("abc://www.pinclipart.com/picdir/middle/20-206356_wenn-hund-clipart.png"));
    }

    /**
     * Die URL wird als ungültig gerwertet, es werden die Standards geprüft, aber nicht, ob es die URL wirklich gibt.
     */

    @Test
    @DisplayName("URL, die korrekt angegeben wurde übernehmen")
    void setKorrekteURL() {
        String url = "https://www.pinclipart.com/picdir/middle/20-206356_wenn-hund-clipart.png";
        assertTrue(checkURL(url));
    }

}
