package Utils;

import Class.Esame;
import GUI.SchermataPrincipale;

import javax.swing.*;
import java.io.*;

import java.util.Vector;

// Classe che contiene i metodi per salvare e caricare gli esami su file

/**
 * Classe EsameUtils
 *
 * Classe che contiene i metodi per salvare e caricare gli esami su file
 *
 * Metodi:
 * -salvaEsami
 * -caricaEsami
 *
 * Autore: Luca Mechetti 146743
 * Versione: 1.0
 */

@SuppressWarnings("unchecked") // Ignora i warning di unchecked cast, non riesco a risolvere il problema in altro modo
public class EsameUtils {

    // Metodo per salvare gli esami su file

    /**
     * Metodo per salvare gli esami su file
     * @param esami Vector di esami da salvare
     */
    public static void salvaEsami(Vector<Esame> esami) {

        // Crea un nuovo JFileChooser per selezionare il file in cui salvare gli esami
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Scegli il file per salvare gli esami");
        int userSelection = fileChooser.showSaveDialog(null);
        // Se l'utente ha selezionato un file e ha cliccato su "Salva" nel JFileChooser salva gli esami nel file selezionato
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            // Se il file non ha estensione .dat la aggiunge
            if (!fileToSave.getName().endsWith(".dat")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".dat");
            }
            // Se il file esiste già chiede all'utente se vuole sovrascriverlo
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
                // Crea un nuovo FileOutputStream e ObjectOutputStream per scrivere gli esami sul file
                FileOutputStream fileOut = new FileOutputStream(fileToSave);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(esami);
                out.close();
                fileOut.close();
                System.out.println("Esami salvati correttamente");
            } catch (IOException i) {
                // Se si verifica un'eccezione stampa l'errore
                i.printStackTrace();
            }
        }
    }

    // Metodo per caricare gli esami da file

    /**
     * Metodo per caricare gli esami da file
     * @param framePadre SchermataPrincipale framePadre
     * @return Vector di esami
     */

    public static Vector<Esame> caricaEsami(SchermataPrincipale framePadre) {
        // Crea un nuovo JFileChooser per selezionare il file da cui caricare gli esami
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Scegli il file per caricare gli esami");
        int userSelection = fileChooser.showOpenDialog(null);
        // Se l'utente ha selezionato un file e ha cliccato su "Apri" nel JFileChooser carica gli esami dal file selezionato
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToLoad = fileChooser.getSelectedFile();
            // Se il file non ha estensione .dat mostra un messaggio di errore e ritorna gli esami attuali
            if (!fileToLoad.getName().endsWith(".dat")) {
                JOptionPane.showMessageDialog(null, "Seleziona un file con estensione .dat", "Errore", JOptionPane.ERROR_MESSAGE);
                return framePadre.getEsami();
            }
            Vector<Esame> esami = new Vector<>();
            try {
                // Crea un nuovo FileInputStream e ObjectInputStream per leggere gli esami dal file
                FileInputStream fileIn = new FileInputStream(fileToLoad);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                Object obj = in.readObject();
                // Se l'oggetto letto è un Vector di Esami lo assegna alla variabile esami, serve epr evitare ClassCastException
                if (obj instanceof Vector<?>) {
                    Vector<?> tempVector = (Vector<?>) obj;
                    boolean allEsami = true;
                    for (Object element : tempVector) {
                        if (!(element instanceof Esame)) {
                            allEsami = false;
                            break;
                        }
                    }
                    if (allEsami) {
                        esami = (Vector<Esame>) tempVector;
                    } else {
                        throw new ClassCastException("Vettore non contiene solo esami");
                    }
                } else {
                    throw new ClassCastException("Vettore non trovato su file");
                }
                in.close();
                fileIn.close();
                System.out.println("Esami caricati correttamente");
            } catch (IOException i) {
                // Se si verifica un'eccezione stampa l'errore
                i.printStackTrace();
                return framePadre.getEsami();
            } catch (ClassNotFoundException c) {
                // Se si verifica un'eccezione stampa l'errore
                System.out.println("Esami non trovati");
                c.printStackTrace();
                return framePadre.getEsami();
            }
            // Ritorna gli esami letti dal file
            return esami;
        }else {
            // Se l'utente ha cliccato su "Annulla" nel JFileChooser ritorna gli esami attuali
            return framePadre.getEsami();
        }
    }


}