package Utils;

import GUI.SchermataPrincipale;

import Class.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Vector;

public class FinestraFiltraEsame extends JDialog {
    private JTextField nomeField;
    private JTextField insegnamentoField;
    private JCheckBox containsCheckBox = new JCheckBox("Corrispondenza Esatta");

    private Vector<Esame> esamiNonFiltrati;
    private Vector<Esame> esamiFiltrati;

    public FinestraFiltraEsame(SchermataPrincipale parent) {
        super(parent, "Filtra Esame", true);
        esamiNonFiltrati = parent.getEsami();
        setSize(300, 200);
        setLayout(new GridLayout(4, 2));

        nomeField = new JTextField();
        insegnamentoField = new JTextField();

        add(new JLabel("Nome e Cognome Studente:"));
        add(nomeField);
        add(new JLabel("Nome Insegnamento:"));
        add(insegnamentoField);
        add(new JLabel("Opzione Filtro :"));
        add(containsCheckBox);


        JButton filtraButton = new JButton("Filtra");
        filtraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                }
            }
        });

        JButton annullaButton = new JButton("Annulla");
        annullaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.setEsami(esamiNonFiltrati);
                parent.aggiornaTabella();
                setVisible(false);
            }
        });

        add(filtraButton);
        add(annullaButton);

        setLocationRelativeTo(parent);
        setVisible(true);
    }


}