package Utils;

import Class.Esame;

import javax.swing.*;
import java.io.*;

import java.util.Vector;

public class EsameUtils {

    //Qui è dove scriverò i metodi per salvare e caricare gli esami su file
    public static void salvaEsami(Vector<Esame> esami, String path) {
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(esami);
            out.close();
            fileOut.close();
            System.out.println("Esami salvati correttamente");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }


    public static Vector<Esame> caricaEsami(String path) {
        Vector<Esame> esami = new Vector<Esame>();
        try {
            FileInputStream fileIn = new FileInputStream(path);
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


}