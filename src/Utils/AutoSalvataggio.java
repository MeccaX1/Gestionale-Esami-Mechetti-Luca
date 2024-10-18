package Utils;

import GUI.SchermataPrincipale;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class AutoSalvataggio extends Thread {

    private final long interval = 5 * 60 * 1000; // 5 minuti
    private SchermataPrincipale parent;
    private boolean running;

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

    public void Stop() {
        this.running = false;
    }

    public void Start() {
        this.running = true;
    }

}




