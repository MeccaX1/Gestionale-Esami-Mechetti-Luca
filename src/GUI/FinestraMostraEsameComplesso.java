package GUI;


import Class.EsameComplesso;



import javax.swing.*;
import java.awt.*;

public class FinestraMostraEsameComplesso extends JDialog  {


    public FinestraMostraEsameComplesso(SchermataPrincipale parent, EsameComplesso esameComplesso) {
        super(parent, "Esame Complesso", true);
        setSize(600, 400);

        JPanel panel = new JPanel(new GridLayout(0, 2));

        panel.add(new JLabel("Esame di "+ esameComplesso.getNome() + " " + esameComplesso.getCognome()));
        panel.add(new JLabel("Materia "+ esameComplesso.getNomeInsegnamento()));

        panel.add(new JLabel("Crediti "+ esameComplesso.getCrediti()));

        panel.add(new JLabel("Voto Finale Pesato "+ esameComplesso.getVotoFinale()));

        for (int i = 0; i < esameComplesso.getVoti().size(); i++) {
            panel.add(new JLabel("Voto " + (i + 1) + ": " + esameComplesso.getVoti().get(i)));

            panel.add(new JLabel("Peso " + (i + 1) + ": " + esameComplesso.getPesi().get(i)));

        }

        add(panel);

        setLocationRelativeTo(parent);
        setVisible(true);


    }
}
