package Utils;

import GUI.SchermataPrincipale;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

// Classe AutoSalvataggio

/**
 * Classe AutoSalvataggio
 *
 * Classe che estende la classe Thread e implementa un thread che salva i dati ogni 5 minuti.
 *
 * Metodi:
 * -Costruttore
 * -run
 * -Stop
 * -Start
 *
 * Autore: Luca Mechetti 146743
 * Versione: 1.0
 */


public class AutoSalvataggio extends Thread {

    // Attributi della classe AutoSalvataggio
    /**
     * long interval, intervallo di tempo per il salvataggio
     */
    private final long interval = 5 * 60 * 1000; // 5 minuti
    /**
     * SchermataPrincipale parent, schermata principale
     */
    private SchermataPrincipale parent;
    /**
     * boolean running, flag per sapere se il thread è in esecuzione
     */
    private boolean running;

    // Costruttore della classe AutoSalvataggio

    /**
     * Costruttore della classe AutoSalvataggio
     * @param parent SchermataPrincipale
     */

    public AutoSalvataggio(SchermataPrincipale parent) {
        // costruttore che prende in input la schermata principale
        this.parent = parent;
        // setta il flag running a true
        running = true;
    }

    // metodo run che salva i dati ogni 5 minuti
    // se il flag running è false, il thread si ferma
    // se il flag filtraggio è true, il thread si ferma, per evitare di salvare i dati mentre si sta filtrando
    // se il salvataggio va a buon fine, stampa un messaggio di conferma
    // se il salvataggio non va a buon fine, stampa un messaggio di errore
    // infine, aspetta 5 minuti
    // se il thread viene interrotto, stampa un messaggio di errore
    // Override del metodo run della classe Thread

    /**
     * Metodo run per avviare il thread
     */
    @Override
    public void run() {

        try {
            Thread.sleep(interval); // aspetta 5 minuti
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }

        while (this.running && !parent.isFiltraggio()) {
            try {
                try {
                    System.out.println("AutoSalvataggio salvataggio esami");
                    FileOutputStream fileOut = new FileOutputStream("Autosalvataggio.dat");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(parent.getEsami());
                    out.close();
                    fileOut.close();
                    System.out.println("Esami salvati correttamente");
                } catch (IOException i) {
                    i.printStackTrace();
                }

                Thread.sleep(interval); // aspetta 5 minuti

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }

    }

    // metodo per fermare il thread

    /**
     * Metodo Stop per fermare il thread
     */

    public void Stop() {
        this.running = false;
    }

    // metodo per avviare il thread

    /**
     * Metodo Start per avviare il thread
     */

    public void Start() {
        this.running = true;
    }

}




