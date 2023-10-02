package at.ac.tgm.mhanes.persistenz;

import at.ac.tgm.mhanes.model.Rechtschreibtrainer;
import com.google.gson.Gson;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Wird für das Erstellen sowie Einlesen (persistierende Abspeichern) von JSON-Dateien in den Rechtschreibtrainer angewandt.
 *
 * @author Matus Hanes
 * @version 02.10.2023
 */
public class JSON extends Speicherformat {

    /**
     * Setzt den Trainer der Superklasse.
     * @param trainer der Rechtschreibtrainer
     */
    public JSON(Rechtschreibtrainer trainer) {
        super(trainer);
    }

    /**
     * Erstellt eine JSON-Datei, indem der Rechtschreibtrainer als Ausgangspunkt verwendet wird.
     * @param filepath der Dateipfad
     * @throws IllegalArgumentException Wenn der Pfad nicht in .json endet, bzw. keine "JSON-Datei" angegeben wurde.
     */
    public void createFile(String filepath) throws IllegalArgumentException {
        if (!filepath.endsWith(".json")) {
            throw new IllegalArgumentException("Der Dateipfad muss in .json enden.");
        }
        try (BufferedWriter out = new BufferedWriter(new FileWriter(filepath))) {
            JSONObject obj = new JSONObject(trainer);
            out.write(obj.toString());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Liest eine JSON-Datei in den Rechtschreibtrainer aus.
     * @param filepath der Dateipfad
     * @throws IllegalArgumentException Wenn der Pfad nicht in .json endet, bzw. keine "JSON-Datei" angegeben wurde.
     */
    public void loadFile(String filepath) throws IllegalArgumentException {
        if (!filepath.endsWith(".json")) {
            throw new IllegalArgumentException("Der Dateipfad muss in .json enden.");
        }
        try (BufferedReader in = new BufferedReader(new FileReader(filepath))) {
            Gson gson = new Gson();
            trainer = gson.fromJson(in, Rechtschreibtrainer.class);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
