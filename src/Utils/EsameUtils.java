package Utils;

import Class.Esame;

import javax.swing.*;
import java.io.*;

import java.util.Vector;

public class EsameUtils {

    //Qui è dove scriverò i metodi per salvare e caricare gli esami su file
    public static void salvaEsami(Vector<Esame> esami) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Scegli il file per salvare gli esami");
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getName().endsWith(".dat")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".dat");
            }
            if (fileToSave.exists()) {
                int response = JOptionPane.showConfirmDialog(null,
                        "Il file esiste già. Vuoi sovrascriverlo?",
                        "Conferma Sovrascrittura",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (response != JOptionPane.YES_OPTION) {
                    return;
                }
            }
            try {
                FileOutputStream fileOut = new FileOutputStream(fileToSave);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(esami);
                out.close();
                fileOut.close();
                System.out.println("Esami salvati correttamente");
            } catch (IOException i) {
                i.printStackTrace();
            }
        }
    }

    public static Vector<Esame> caricaEsami() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Scegli il file per caricare gli esami");
        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToLoad = fileChooser.getSelectedFile();
            if (!fileToLoad.getName().endsWith(".dat")) {
                JOptionPane.showMessageDialog(null, "Seleziona un file con estensione .dat", "Errore", JOptionPane.ERROR_MESSAGE);
                Vector<Esame> esami = new Vector<>();
                return esami;
            }
            Vector<Esame> esami = new Vector<>();
            try {
                FileInputStream fileIn = new FileInputStream(fileToLoad);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                esami = (Vector<Esame>) in.readObject();
                in.close();
                fileIn.close();
                System.out.println("Esami caricati correttamente");
            } catch (IOException i) {
                i.printStackTrace();
                return null;
            } catch (ClassNotFoundException c) {
                System.out.println("Esami non trovati");
                c.printStackTrace();
                return null;
            }
            return esami;
        }
        Vector<Esame> esami = new Vector<>();
        return esami;
    }


}