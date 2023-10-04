package at.ac.tgm.mhanes;

import at.ac.tgm.mhanes.model.Rechtschreibtrainer;
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
 * @version 02.10.2023
 */

public class TestRechtschreibtrainer {

    private static WortListe liste;
    private static Rechtschreibtrainer trainer;

    @BeforeAll
    static void initListeTrainer() {
        liste = new WortListe();
        trainer = new Rechtschreibtrainer(liste);
    }

    @Test
    @DisplayName("Zufällig Element aus Liste holen")
    void getRandomPaarAusListe() {
        WortPaar p1 = new WortPaar("Hund", "https://www.pinclipart.com/picdir/middle/20-206356_wenn-hund-clipart.png");
        WortPaar p2 = new WortPaar("Katze", "https://www.pinclipart.com/picdir/middle/20-206356_wenn-hund-clipart.png");

        liste.addPaar(p1); liste.addPaar(p2);

        int anzP1 = 0;
        int anzP2 = 0;

        for (int i = 0; i < 1000; i++) {
            if (trainer.getRandomPaar() == p1) {
                anzP1++;
                continue;
            }
            anzP2++;
        }
        assertFalse(anzP1 == 0 || anzP2 == 0);
        System.out.println("P1: " + anzP1 + "; P2: " + anzP2);
        liste.clear();
    }

    @Test
    @DisplayName("Aktuelles Paar stimmt mit dem zufällig ausgewählten überein")
    void aktuellesPaarStimmtUeberein() {
        WortPaar p1 = new WortPaar("Hund", "https://www.pinclipart.com/picdir/middle/20-206356_wenn-hund-clipart.png");
        WortPaar p2 = new WortPaar("Katze", "https://www.pinclipart.com/picdir/middle/20-206356_wenn-hund-clipart.png");

        liste.addPaar(p1); liste.addPaar(p2);
        assertEquals(trainer.getRandomPaar(), trainer.getAktuellesPaar());
        liste.clear();
    }

    @Test
    @DisplayName("Prüfen ob Wort in Paar mit kapitalisiert übergebenem nicht übereinstimmt")
    void checkenObWortFalseKapitalisiertGrossKlein() {
        WortPaar p1 = new WortPaar("Hund", "https://www.pinclipart.com/picdir/middle/20-206356_wenn-hund-clipart.png");

        liste.addPaar(p1);

        // Aktuelles Paar auswählen
        trainer.getRandomPaar();

        // Prüfen ob aktuelles Paar Wort beeinhaltet
        assertFalse(trainer.check("HUND"));
        liste.clear();
    }

    @Test
    @DisplayName("Prüfen ob Wort in Paar mit korrekt kapitalisiertem übereinstimmt")
    void checkenObWortTrueNichtKapitalisiertGrossKlein() {
        WortPaar p1 = new WortPaar("Hund", "https://www.pinclipart.com/picdir/middle/20-206356_wenn-hund-clipart.png");

        liste.addPaar(p1);

        // Aktuelles Paar auswählen
        trainer.getRandomPaar();

        // Prüfen ob aktuelles Paar Wort beeinhaltet
        assertTrue(trainer.check("Hund"));
        liste.clear();
    }

    @Test
    @DisplayName("Prüfen ob Wort in Paar mit anders kapitalisiertem String übereinstimmt")
    void checkenObWortTrueIgnoreCase() {
        WortPaar p1 = new WortPaar("Hund", "https://www.pinclipart.com/picdir/middle/20-206356_wenn-hund-clipart.png");

        liste.addPaar(p1);

        // Aktuelles Paar auswählen
        trainer.getRandomPaar();

        // Prüfen ob aktuelles Paar Wort beeinhaltet
        assertTrue(trainer.checkIgnoreCase("HUND"));
        liste.clear();
    }

    @Test
    @DisplayName("Prüfen ob Wort in Paar mit anders kapitalisiertem aber FALSCHEM String nicht übereinstimmt")
    void checkenObWortFalseIgnoreCase() {
        WortPaar p1 = new WortPaar("Hund", "https://www.pinclipart.com/picdir/middle/20-206356_wenn-hund-clipart.png");

        liste.addPaar(p1);

        // Aktuelles Paar auswählen
        trainer.getRandomPaar();

        // Prüfen ob aktuelles Paar Wort beeinhaltet
        assertFalse(trainer.checkIgnoreCase("KATZE"));
        liste.clear();
    }

    @Test
    @DisplayName("Prüfen ob das aktuelle Paar wenn null bei einem Check false liefert")
    void checkObAktuellesPaarGleichNullFalseGrossKlein() {
        trainer = new Rechtschreibtrainer(liste);
        WortPaar p1 = new WortPaar("Hund", "https://www.pinclipart.com/picdir/middle/20-206356_wenn-hund-clipart.png");

        liste.addPaar(p1);

        // Prüfen ob aktuelles Paar Wort beeinhaltet (aber dadurch, dass aktuellesPaar null = false)
        assertFalse(trainer.check("Hund"));
        liste.clear();
    }

    @Test
    @DisplayName("Prüfen ob das aktuelle Paar bei null unabhängig von Groß-Kleinschreibung false liefert")
    void checkObAktuellesPaarGleichNullFalseIgnoreCase() {
        trainer = new Rechtschreibtrainer(liste);
        WortPaar p1 = new WortPaar("Hund", "https://www.pinclipart.com/picdir/middle/20-206356_wenn-hund-clipart.png");

        liste.addPaar(p1);

        // Prüfen ob aktuelles Paar Wort beeinhaltet (aber dadurch, dass aktuellesPaar null = false)
        assertFalse(trainer.checkIgnoreCase("Hund"));
        liste.clear();
    }

    @Test
    @DisplayName("reset setzt counter-Werte auf 0")
    void anzahlDerRichtigFalschenAntwortenAuf0() {

        trainer.resetAnzCheckFalse();
        trainer.resetAnzCheckTrue();
        
        trainer = new Rechtschreibtrainer(liste);
        WortPaar p1 = new WortPaar("Hund", "https://www.pinclipart.com/picdir/middle/20-206356_wenn-hund-clipart.png");

        liste.addPaar(p1);

        trainer.getRandomPaar();

        // 1 True, 1 False
        trainer.check("Hund");
        trainer.check("HUND");

        assertTrue(trainer.getAnzCheckFalse() == 1 && trainer.getAnzCheckTrue() == 1);

        trainer.resetAnzCheckFalse();
        trainer.resetAnzCheckTrue();

        assertTrue(trainer.getAnzCheckFalse() == 0 && trainer.getAnzCheckTrue() == 0);
    }

}
