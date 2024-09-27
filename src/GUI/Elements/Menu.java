package GUI.Elements;

import GUI.SchermataPrincipale;
import Utils.EsameUtils;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Menu extends JMenuBar implements ActionListener {

    private SchermataPrincipale framePadre;

    public Menu(SchermataPrincipale frame)  {
        this.framePadre = frame;
        JMenu Opzioni = new JMenu("Opzioni");
        JMenuItem Salva = new JMenuItem("Salva");
        JMenuItem Carica = new JMenuItem("Carica");
        Opzioni.add(Salva);
        Opzioni.add(Carica);
        Salva.addActionListener(this);
        Carica.addActionListener(this);
        this.add(Opzioni);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Salva")) {
            System.out.println("Cliccato Salva");
            EsameUtils.salvaEsami(framePadre.getEsami(), "prova.dat");
        }
        if(e.getActionCommand().equals("Carica")) {
            System.out.println("Cliccato Carica");
        }
    }
}
