package at.ac.tgm.mhanes.persistenz;

import at.ac.tgm.mhanes.model.Rechtschreibtrainer;

/**
 * Strategy-Klasse von der alle weiteren Speicherformate für die Persistenz der Trainerdaten erben können.
 *
 * @author Matus Hanes
 * @version 02.10.2023
 */
public abstract class Speicherformat {

    protected Rechtschreibtrainer trainer;

    /**
     * Der Konstruktor zum Setzen des Trainers.
     * @param trainer der Rechtschreibtrainer
     */

    public Speicherformat(Rechtschreibtrainer trainer) {
        this.trainer = trainer;
    }

    /**
     * Zum Erstellen einer Datei mittels Dateipfad.
     * @param filepath der Dateipfad
     */
    public abstract void createFile(String filepath);

    /**
     * Zum Einlesen einer Datei in den Trainer mittels Dateipfad.
     * @param filepath der Dateipfad
     */
    public abstract void loadFile(String filepath);

    /**
     * Gibt den Trainer zurück.
     * @return der Rechtschreibtrainer
     */
    public Rechtschreibtrainer getTrainer() {
        return trainer;
    }

}
