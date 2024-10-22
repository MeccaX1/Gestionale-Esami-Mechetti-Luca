package GUI;


import Class.EsameComplesso;



import javax.swing.*;
import java.awt.*;

// Classe che crea la finestra per mostrare i dettagli di un esame complesso

/**
 * Classe FinestraMostraEsameComplesso
 * Classe che crea la finestra per mostrare i dettagli di un esame complesso
 *
 * Metodi:
 * -Costruttore
 *
 * Autore: Luca Mechetti 146743
 * Versione: 1.0
 */

public class FinestraMostraEsameComplesso extends JDialog  {

    // Costruttore, crea la finestra per mostrare i dettagli di un esame complesso, prende in input la schermata principale e l'esame complesso

    /**
     * Costruttore FinestraMostraEsameComplesso
     * Crea la finestra per mostrare i dettagli di un esame complesso
     *
     * @param parent SchermataPrincipale
     * @param esameComplesso EsameComplesso
     */

    public FinestraMostraEsameComplesso(SchermataPrincipale parent, EsameComplesso esameComplesso) {
        super(parent, "Esame Complesso", true);
        setSize(600, 400);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Nome:"));
        panel.add(new JLabel(esameComplesso.getNome()));
        panel.add(new JLabel("Cognome:"));
        panel.add(new JLabel(esameComplesso.getCognome()));
        panel.add(new JLabel("Materia:"));
        panel.add(new JLabel(esameComplesso.getNomeInsegnamento()));
        panel.add(new JLabel("Crediti:"));
        panel.add(new JLabel(String.valueOf(esameComplesso.getCrediti())));
        panel.add(new JLabel("Voto Finale Pesato:"));
        panel.add(new JLabel(String.valueOf(esameComplesso.getVotoFinale())));

        for (int i = 0; i < esameComplesso.getVoti().size(); i++) {
            panel.add(new JLabel("Voto " + (i + 1) + ":"));
            panel.add(new JLabel(String.valueOf(esameComplesso.getVoti().get(i))));
            panel.add(new JLabel("Peso " + (i + 1) + ":"));
            panel.add(new JLabel(String.valueOf(esameComplesso.getPesi().get(i))));
        }

        add(panel, BorderLayout.CENTER);

        JButton closeButton = new JButton("Chiudi");
        closeButton.addActionListener(e -> setVisible(false));
        add(closeButton, BorderLayout.SOUTH);

        setLocationRelativeTo(parent);
        setVisible(true);
    }
}
