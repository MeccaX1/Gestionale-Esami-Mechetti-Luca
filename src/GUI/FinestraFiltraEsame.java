package GUI;

import Class.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Vector;
import java.text.DecimalFormat;

public class FinestraFiltraEsame extends JDialog {
    private JTextField nomeField;
    private JTextField insegnamentoField;
    private JCheckBox containsCheckBox = new JCheckBox("Corrispondenza Esatta");
    private JLabel mediaPesata;

    private Vector<Esame> esamiNonFiltrati;
    private Vector<Esame> esamiFiltrati;

    public FinestraFiltraEsame(SchermataPrincipale parent) {
        super(parent, "Filtra Esame", false); // false = non blocca la schermata principale, non modale (puoi cliccare sulla schermata principale)
        esamiNonFiltrati = parent.getEsami();
        this.setSize(300, 200);
        setLayout(new GridLayout(6, 2));

        nomeField = new JTextField();
        insegnamentoField = new JTextField();

        add(new JLabel("Nome e Cognome Studente:"));
        add(nomeField);
        add(new JLabel("Nome Insegnamento:"));
        add(insegnamentoField);
        add(new JLabel("Opzione Filtro :"));
        add(containsCheckBox);
        mediaPesata = new JLabel("");


        JButton filtraButton = new JButton("Filtra");
        filtraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.setFiltraggio(true);
                esamiFiltrati = new Vector<>();
                for (Esame esame : esamiNonFiltrati) {
                    if (!containsCheckBox.isSelected()) {
                        String nomecognome = esame.getNome() + esame.getCognome();
                        if (nomecognome.toLowerCase().replaceAll("\\s+", "").contains(nomeField.getText().toLowerCase().replaceAll("\\s+", "")) && esame.getNomeInsegnamento().toLowerCase().replaceAll("\\s+", "").contains(insegnamentoField.getText().toLowerCase().replaceAll("\\s+", ""))) {
                            esamiFiltrati.add(esame);
                        }
                    }else {

                        String nomecognome = esame.getNome() + esame.getCognome();
                        if (nomeField.getText().toLowerCase().replaceAll("\\s+", "").isEmpty() && !(insegnamentoField.getText().toLowerCase().replaceAll("\\s+", "").isEmpty())  ){

                            if (Objects.equals(esame.getNomeInsegnamento().toLowerCase().replaceAll("\\s+", ""), insegnamentoField.getText().toLowerCase().replaceAll("\\s+", "")) ) {
                                esamiFiltrati.add(esame);
                            }
                        }else if((insegnamentoField.getText().toLowerCase().replaceAll("\\s+", "").isEmpty()) && !(nomeField.getText().toLowerCase().replaceAll("\\s+", "").isEmpty()) ){

                            if (Objects.equals(esame.getNomeInsegnamento(), nomeField.getText().toLowerCase().replaceAll("\\s+", ""))) {

                                esamiFiltrati.add(esame);

                            }
                        }else if(!(nomeField.getText().toLowerCase().replaceAll("\\s+", "").isEmpty()) && !(insegnamentoField.getText().toLowerCase().replaceAll("\\s+", "").isEmpty()) ){

                            if (Objects.equals(nomecognome, nomeField.getText().toLowerCase().replaceAll("\\s+", ""))&&  Objects.equals( esame.getNomeInsegnamento().toLowerCase().replaceAll("\\s+", ""), insegnamentoField.getText().toLowerCase().replaceAll("\\s+", "")) ) {
                                esamiFiltrati.add(esame);


                            }
                        }else {
                            System.out.println("Errore");
                        }

                    }
                }
                if(esamiFiltrati.size() == 0){
                    JOptionPane.showMessageDialog(parent, "Nessun esame trovato", "Errore", JOptionPane.ERROR_MESSAGE);
                }else {
                    parent.setEsami(esamiFiltrati);
                    parent.aggiornaTabella();
                    double sommaVotiPesi = 0;
                    double sommaPesi = 0;
                    double media = 0;
                    for (Esame esame : esamiFiltrati) {
                        if (esame instanceof EsameSemplice) {
                            EsameSemplice esameSemplice = (EsameSemplice) esame;
                            sommaVotiPesi += esameSemplice.getVoto()*esameSemplice.getCrediti();
                            sommaPesi += esameSemplice.getCrediti();
                        } else if (esame instanceof EsameComplesso) {
                            EsameComplesso esameComplesso = (EsameComplesso) esame;
                            sommaVotiPesi += esameComplesso.getVotoFinale()*esameComplesso.getCrediti();
                            sommaPesi += esameComplesso.getCrediti();
                        }
                    }
                    media = ((sommaVotiPesi/sommaPesi));
                    DecimalFormat formato = new DecimalFormat("#.00");
                    String mediaFormattata = formato.format(media);
                    mediaPesata.setText(mediaFormattata);

                }
            }
        });

        JButton annullaButton = new JButton("Annulla");
        annullaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.setEsami(esamiNonFiltrati);
                parent.aggiornaTabella();
                parent.setFiltraggio(false);
                setVisible(false);
            }
        });

        add(new JLabel("Media voti filtrati:"));
        add(mediaPesata);

        JButton plotButton = new JButton("Genera grafico");
        plotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FinestraGrafico(parent);
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                parent.setEsami(esamiNonFiltrati);
                parent.aggiornaTabella();
                parent.setFiltraggio(false);
                setVisible(false);
            }
        });


        add(filtraButton);
        add(annullaButton);
        add(plotButton);


        setLocationRelativeTo(parent);
        setVisible(true);
    }


}