package GUI;


import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


// Classe base per le finestre, estende JFrame e implementa ActionListener e WindowListener, in modo da poter gestire gli eventi.
// Inoltre, imposta il titolo della finestra, la dimensione e la chiusura dell'applicazione quando si chiude la finestra.

/**
 * Classe GuiBase
 *
 * Classe base per le finestre, estende JFrame e implementa ActionListener e WindowListener, in modo da poter gestire gli eventi.
 * Inoltre, imposta il titolo della finestra, la dimensione e la chiusura dell'applicazione quando si chiude la finestra.
 *
 * Metodi:
 * -Costruttore
 *
 * Autore: Luca Mechetti 146743
 * Versione: 1.0
 */

public class GuiBase extends JFrame implements ActionListener, WindowListener {

    // Costruttore della classe GuiBase

    /**
     * Costruttore della classe GuiBase
     *
     * Costruttore della classe GuiBase, imposta il titolo della finestra, la dimensione e la chiusura dell'applicazione quando si chiude la finestra.
     */

    public GuiBase(){
        super("Gestionale Esami");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosing(WindowEvent e) {}
    @Override
    public void windowClosed(WindowEvent e) {}
    @Override
    public void windowIconified(WindowEvent e) {}
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeactivated(WindowEvent e) {}
    @Override
    public void actionPerformed(ActionEvent e) {}
}
