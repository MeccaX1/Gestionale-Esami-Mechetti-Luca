package Utils;

import Class.Esame;
import GUI.SchermataPrincipale;

import java.util.Vector;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class AutoSalvataggio extends Thread {

    private final long interval = 5 * 60 * 1000; // 5 minuti
    private SchermataPrincipale parent;
    private boolean runnig;

    public AutoSalvataggio(SchermataPrincipale parent) {
        System.out.println("AutoSalvataggio creato");
        this.parent = parent;
        runnig = true;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(interval); // aspetta 5 minuti
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }

        while (this.runnig) {
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
        this.runnig = false;
    }

    public void Start() {
        this.runnig = true;
    }

}




