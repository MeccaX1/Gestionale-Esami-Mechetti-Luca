package GUI.Elements;

import GUI.SchermataPrincipale;
import Utils.EsameUtils;
import GUI.FinestraAggiungiEsame;
import GUI.FinestraFiltraEsame;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JMenuBar implements ActionListener {

    private SchermataPrincipale framePadre;

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
        Dati.add(Aggiungi);
        Dati.add(Filtra);

        Salva.addActionListener(this);
        Carica.addActionListener(this);
        Aggiungi.addActionListener(this);
        Filtra.addActionListener(this);

        this.add(Opzioni);
        this.add(Dati);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Salva")) {
            System.out.println("Cliccato Salva");
            EsameUtils.salvaEsami(framePadre.getEsami() );
        }
        if(e.getActionCommand().equals("Carica")) {
            framePadre.setEsami( EsameUtils.caricaEsami()) ;
            framePadre.aggiornaTabella();
            System.out.println("Cliccato Carica");
        }if (e.getActionCommand().equals("Aggiungi")) {
            System.out.println("Cliccato Aggiungi");
            //FinestraAggiuntaEsame finestra = new FinestraAggiuntaEsame(framePadre);
            FinestraAggiungiEsame finestra = new FinestraAggiungiEsame(framePadre);
            framePadre.aggiornaTabella();
//            if (finestra.isAggiungi()){
//                framePadre.getEsami().add(finestra.getEsame());
//                framePadre.aggiornaTabella();
//            }
        }if (e.getActionCommand().equals("Filtra")) {
            System.out.println("Cliccato Filtra");
            FinestraFiltraEsame finestra = new FinestraFiltraEsame(framePadre);
        }
    }

}
