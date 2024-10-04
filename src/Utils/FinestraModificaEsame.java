package Utils;

import Class.Esame;
import Class.EsameComplesso;
import Class.EsameSemplice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class FinestraModificaEsame extends JDialog {
    private JTextField nome;
    private JTextField cognome;
    private JTextField nomeInsegnamento;
    private JTextField crediti;
    private JCheckBox lode;
    private Vector<JTextField> voti;
    private Vector<JTextField> pesi;
    private boolean modificato = false;

    public FinestraModificaEsame(Frame parent, Esame esame) {
        super(parent, "Modifica Esame", true);
        setSize(400, 300);
        setLayout(new GridLayout(0, 2));

        nome = new JTextField(esame.getNome());
        cognome = new JTextField(esame.getCognome());
        nomeInsegnamento = new JTextField(esame.getNomeInsegnamento());
        crediti = new JTextField(String.valueOf(esame.getCrediti()));
        lode = new JCheckBox("Lode", esame.isLode());

        add(new JLabel("Nome:"));
        add(nome);
        add(new JLabel("Cognome:"));
        add(cognome);
        add(new JLabel("Materia:"));
        add(nomeInsegnamento);
        add(new JLabel("Crediti:"));
        add(crediti);
        add(new JLabel("Lode:"));
        add(lode);

        voti = new Vector<>();
        pesi = new Vector<>();

        if (esame instanceof EsameComplesso) {
            EsameComplesso esameComplesso = (EsameComplesso) esame;
            for (int i = 0; i < esameComplesso.getVoti().size(); i++) {
                JTextField votoField = new JTextField(String.valueOf(esameComplesso.getVoti().get(i)));
                JTextField pesoField = new JTextField(String.valueOf(esameComplesso.getPesi().get(i)));
                voti.add(votoField);
                pesi.add(pesoField);
                add(new JLabel("Voto " + (i + 1) + ":"));
                add(votoField);
                add(new JLabel("Peso " + (i + 1) + ":"));
                add(pesoField);
            }
        } else if (esame instanceof EsameSemplice) {
            EsameSemplice esameSemplice = (EsameSemplice) esame;
            JTextField votoField = new JTextField(String.valueOf(esameSemplice.getVoto()));
            voti.add(votoField);
            add(new JLabel("Voto:"));
            add(votoField);
        }

        JButton conferma = new JButton("Conferma");
        conferma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                esame.setNome(nome.getText());
                esame.setCognome(cognome.getText());
                esame.setNomeInsegnamento(nomeInsegnamento.getText());
                esame.setCrediti(Integer.parseInt(crediti.getText()));
                esame.setLode(lode.isSelected());

                if (esame instanceof EsameComplesso) {
                    EsameComplesso esameComplesso = (EsameComplesso) esame;
                    Vector<Integer> votiEsame = new Vector<>();
                    Vector<Integer> pesiEsame = new Vector<>();
                    for (int i = 0; i < voti.size(); i++) {
                        votiEsame.add(Integer.parseInt(voti.get(i).getText()));
                        pesiEsame.add(Integer.parseInt(pesi.get(i).getText()));
                    }
                    esameComplesso.setVoti(votiEsame);
                    esameComplesso.setPesi(pesiEsame);
                } else if (esame instanceof EsameSemplice) {
                    EsameSemplice esameSemplice = (EsameSemplice) esame;
                    esameSemplice.setVoto(Integer.parseInt(voti.get(0).getText()));
                }

                modificato = true;
                setVisible(false);
            }
        });

        JButton annulla = new JButton("Annulla");
        annulla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificato = false;
                setVisible(false);
            }
        });

        add(conferma);
        add(annulla);

        setLocationRelativeTo(parent);
        setVisible(true);
    }

    public boolean isModificato() {
        return modificato;
    }
}
