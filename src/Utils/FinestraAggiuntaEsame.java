package Utils;

import Class.Esame;
import Class.EsameComplesso;
import Class.EsameSemplice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class FinestraAggiuntaEsame extends JDialog {
    private JTextField nome;
    private JTextField cognome;
    private JTextField nomeInsegnamento;
    private JTextField crediti;
    private JCheckBox lode;
    private JCheckBox complesso;
    private JPanel pannelloVoti;
    private Vector<JTextField> voti;
    private Vector<JTextField> pesi;
    private boolean aggiungi;

    public FinestraAggiuntaEsame(Frame parent){
        super(parent, "Aggiungi Esame", true);
        setLayout(new GridLayout(0, 2));

        add(new JLabel("Nome:"));
        nome = new JTextField();
        add(nome);

        add(new JLabel("Cognome:"));
        cognome = new JTextField();
        add(cognome);

        add(new JLabel("Nome Insegnamento:"));
        nomeInsegnamento = new JTextField();
        add(nomeInsegnamento);

        add(new JLabel("Crediti:"));
        crediti = new JTextField();
        add(crediti);

        lode = new JCheckBox("Lode");
        add(lode);

        complesso = new JCheckBox("Esame Complesso");
        add(complesso);

        pannelloVoti= new JPanel(new GridLayout(0,2));
        voti = new Vector<JTextField>();
        pesi = new Vector<JTextField>();
        JButton aggiungiVotoButton = new JButton("Aggiungi Voto");
        aggiungiVotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField votoField = new JTextField();
                JTextField pesoField = new JTextField();
                voti.add(votoField);
                pesi.add(pesoField);
                pannelloVoti.add(new JLabel("Voto:"));
                pannelloVoti.add(votoField);
                pannelloVoti.add(new JLabel("Peso:"));
                pannelloVoti.add(pesoField);
                pannelloVoti.revalidate();
                pannelloVoti.repaint();
            }
        });
        add(aggiungiVotoButton);
        add(pannelloVoti);
        complesso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pannelloVoti.setVisible(complesso.isSelected());

            }
        });
        JButton conferma = new JButton("Conferma");
        conferma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //sezione finestre allarme

                //controllo lode selezionata solo se voto 30 e esame non complesso

                if (lode.isSelected() && Integer.parseInt(voti.getFirst().getText()) < 30){
                    JOptionPane.showMessageDialog(null, "Lode selezionabile solo con 30 come voto e esame semplice!", "Errore", JOptionPane.ERROR_MESSAGE);
                }

                //controllo voto maggiore o uguale 18 e minore o uguale trenta

                //controllo voto e peso non vuoti

                //controllo crediti maggiore di 0

                //controllo nome, cognome e nome insegnamento non vuoti

                //controllo se esame complesso, allora controllo se voti e pesi non vuoti

                //controllo se esame complesso, allora controllo se voti e pesi sono numeri

                //controllo se esame semplice, allora controllo se voto e peso non vuoti

                aggiungi = true;
                setVisible(false);
            }
        });
        add(conferma);
        JButton annulla = new JButton("Annulla");
        annulla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aggiungi = false;
                setVisible(false);
            }
        });
        add(annulla);

        //per dimensione corretta
        setSize(400, 300);
        setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    public Esame getEsame(){
        if (aggiungi){
            if (complesso.isSelected()){
                EsameComplesso esame = new EsameComplesso();
                esame.setNome(nome.getText());
                esame.setCognome(cognome.getText());
                esame.setNomeInsegnamento(nomeInsegnamento.getText());
                esame.setCrediti(Integer.parseInt(crediti.getText()));
                esame.setLode(lode.isSelected());
                Vector<Integer> votiEsame = new Vector<Integer>();
                Vector<Integer> pesiEsame = new Vector<Integer>();
                for (int i = 0; i < voti.size(); i++){
                    votiEsame.add(Integer.parseInt(voti.get(i).getText()));
                    pesiEsame.add(Integer.parseInt(pesi.get(i).getText()));
                }
                esame.setVoti(votiEsame);
                esame.setPesi(pesiEsame);
                return esame;
            } else {
                EsameSemplice esame = new EsameSemplice();
                esame.setNome(nome.getText());
                esame.setCognome(cognome.getText());
                esame.setNomeInsegnamento(nomeInsegnamento.getText());
                esame.setCrediti(Integer.parseInt(crediti.getText()));
                esame.setLode(lode.isSelected());
                esame.setVoto(Integer.parseInt(voti.getFirst().getText()));
                return esame;
            }
        } else {
            return null;
        }
    }

    public boolean isAggiungi() {
        return aggiungi;
    }

}
