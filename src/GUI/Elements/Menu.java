package GUI.Elements;

import GUI.SchermataPrincipale;
import Utils.EsameUtils;
import GUI.FinestraAggiungiEsame;
import GUI.FinestraFiltraEsame;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Classe Menu

/**
 * Classe Menu

 * Classe che estende JMenuBar e crea il menu con i vari item
 * Metodi:
 * -Costruttore
 * Autore: Luca Mechetti 146743
 * Versione: 1.0
 */


public class Menu extends JMenuBar  {


    /**
     * SchermataPrincipale framePadre per avere accesso ai metodi della classe SchermataPrincipale
     */
    private SchermataPrincipale framePadre;

    //costruttore menu con i vari item

    /**
     * Costruttore della classe Menu
     * @param frame SchermataPrincipale
     */

    public Menu(SchermataPrincipale frame)  {
        this.framePadre = frame;
        JMenu Opzioni = new JMenu("Opzioni");
        JMenuItem Salva = new JMenuItem("Salva");
        JMenuItem Carica = new JMenuItem("Carica");
        Opzioni.add(Salva);
        Opzioni.add(Carica);
        JMenu Dati = new JMenu("Dati");
        JMenuItem Aggiungi = new JMenuItem("Aggiungi");
        JMenuItem Filtra = new JMenuItem("Filtra");
        JMenuItem Stampa = new JMenuItem("Stampa");
        Dati.add(Aggiungi);
        Dati.add(Filtra);
        Dati.add(Stampa);

        //aggiungo i listener agli item

        Salva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("Salva")) {
                    System.out.println("Cliccato Salva");
                    EsameUtils.salvaEsami(framePadre.getEsami());
                    framePadre.setModificato(false);
                }
            }
        });
        Carica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("Carica")) {
                    framePadre.setEsami(EsameUtils.caricaEsami(framePadre));
                    framePadre.aggiornaTabella();
                    framePadre.setModificato(false);
                    System.out.println("Cliccato Carica");
                }
            }
        });
        Aggiungi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("Aggiungi")) {
                    System.out.println("Cliccato Aggiungi");
                    FinestraAggiungiEsame finestra = new FinestraAggiungiEsame(framePadre);
                    framePadre.aggiornaTabella();
                }
            }
        });
        Filtra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("Filtra")) {
                    System.out.println("Cliccato Filtra");
                    FinestraFiltraEsame finestra = new FinestraFiltraEsame(framePadre);
                }
            }
        });
        Stampa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("Stampa")) {
                    System.out.println("Cliccato Stampa");
                    framePadre.getTabella().stampa();
                }
            }
        });

        //aggiungo i menu al menu bar

        this.add(Opzioni);
        this.add(Dati);
    }

}
