package at.ac.tgm.mhanes;

import at.ac.tgm.mhanes.model.Rechtschreibtrainer;
import at.ac.tgm.mhanes.model.WortListe;
import at.ac.tgm.mhanes.model.WortPaar;
import at.ac.tgm.mhanes.persistenz.JSON;
import at.ac.tgm.mhanes.persistenz.Speicherformat;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Klasse zum Testen der JSON-Funktionalität.
 *
 * @author Matus Hanes
 * @version 18.10.2023
 */

public class TestJSON {

    @Test
    void PruefeObAttributeBeimLadenBleiben() throws IOException {

        WortListe liste = new WortListe();
        Rechtschreibtrainer trainer = new Rechtschreibtrainer(liste);
        Speicherformat datei = new JSON(trainer);

        if (new File("test.json").exists()) {
            datei.loadFile("test.json");
            liste = datei.getTrainer().getListe();
            assertEquals(liste.getPaar(0).getWort(), "Hund");
            assertEquals(liste.getPaar(1).getWort(), "Katze");
        } else {
            liste.addPaar(new WortPaar("Hund", "https://www.pinclipart.com/picdir/middle/20-206356_wenn-hund-clipart.png"));
            liste.addPaar(new WortPaar("Katze", "https://media.os.fressnapf.com/cms/2020/05/Ratgeber-Katze-Gesundheit-KatzeWiese_1200x527.jpg"));
            datei.createFile("test.json");
        }

    }

}
