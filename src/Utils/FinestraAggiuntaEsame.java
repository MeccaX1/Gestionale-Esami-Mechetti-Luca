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
    private boolean errore;


    public FinestraAggiuntaEsame(Frame parent){
        super(parent, "Aggiungi Esame", true);
        setSize(400, 300);
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
                if (complesso.isSelected()){
                    pannelloVoti.add(new JLabel("Voto:"));
                    JTextField votoField = new JTextField();
                    voti.add(votoField);
                    pannelloVoti.add(votoField);
                    pannelloVoti.add(new JLabel("Peso:"));
                    JTextField pesoField = new JTextField();
                    pesi.add(pesoField);
                    pannelloVoti.add(pesoField);
                    pannelloVoti.revalidate();
                    pannelloVoti.repaint();
                } else {
                    if (voti.size() > 0){
                        JOptionPane.showMessageDialog(parent, "Esame semplice, non è possibile aggiungere più di un voto!", "Errore", JOptionPane.ERROR_MESSAGE);
                    } else {
                        pannelloVoti.add(new JLabel("Voto:"));
                        JTextField votoField = new JTextField();
                        voti.add(votoField);
                        pannelloVoti.add(votoField);
                        pannelloVoti.revalidate();
                        pannelloVoti.repaint();
                    }
                }
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
                errore=false;
                    //sezione finestre allarme
                    // controllo che tutti i campi non siano vuoti
                    if (nome.getText().isEmpty() || cognome.getText().isEmpty() || nomeInsegnamento.getText().isEmpty() || crediti.getText().isEmpty() || voti.isEmpty()){
                        errore=true;
                        JOptionPane.showMessageDialog(parent, "Tutti i campi devono essere compilati!", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                    if (lode.isSelected() && Integer.parseInt(voti.getFirst().getText()) < 30){
                        errore=true;
                        JOptionPane.showMessageDialog(parent, "Lode selezionabile solo con 30 come voto e esame semplice!", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                    if( Integer.parseInt(voti.getFirst().getText()) < 18 || Integer.parseInt(voti.getFirst().getText()) > 30){
                        errore=true;
                        JOptionPane.showMessageDialog(parent, "Voto deve essere compreso tra 18 e 30!", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                    if (complesso.isSelected()){
                        for (int i = 0; i < voti.size(); i++){
                            if (voti.get(i).getText().isEmpty() || pesi.get(i).getText().isEmpty()){
                                JOptionPane.showMessageDialog(parent, "Voti e pesi non possono essere vuoti!", "Errore", JOptionPane.ERROR_MESSAGE);
                                errore=true;
                            }
                        }
                    }  if (!complesso.isSelected()){
                        if (voti.getFirst().getText().isEmpty()){
                            JOptionPane.showMessageDialog(parent, "Voto non può essere vuoto!", "Errore", JOptionPane.ERROR_MESSAGE);
                            errore=true;
                        }
                    }
                    if (Integer.parseInt(crediti.getText()) <= 0){
                        errore=true;
                        JOptionPane.showMessageDialog(parent, "Crediti devono essere maggiori di 0!", "Errore", JOptionPane.ERROR_MESSAGE);

                    }
                    if (nome.getText().isEmpty() || cognome.getText().isEmpty() || nomeInsegnamento.getText().isEmpty()){
                        errore=true;
                        JOptionPane.showMessageDialog(parent, "Nome, cognome e nome insegnamento non possono essere vuoti!", "Errore", JOptionPane.ERROR_MESSAGE);

                    }
                    if (complesso.isSelected()){
                        for (int i = 0; i < voti.size(); i++){
                            try {
                                Integer.parseInt(voti.get(i).getText());
                                Integer.parseInt(pesi.get(i).getText());
                            } catch (NumberFormatException ex){
                                errore=true;
                                JOptionPane.showMessageDialog(parent, "Voti e pesi devono essere numeri!", "Errore", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    if (!complesso.isSelected()){
                        try {
                            Integer.parseInt(voti.getFirst().getText());
                        } catch (NumberFormatException ex){
                            errore=true;
                            JOptionPane.showMessageDialog(parent, "Voto deve essere un numero!", "Errore", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    if(complesso.isSelected()){
                        for (int i = 0; i < pesi.size(); i++){
                            if (pesi.get(i).getText().isEmpty()){
                                errore=true;
                                JOptionPane.showMessageDialog(parent, "Pesi non possono essere vuoti!", "Errore", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    if (complesso.isSelected()){
                        int sommaPesi = 0;
                        for (int i = 0; i < pesi.size(); i++){
                            sommaPesi += Integer.parseInt(pesi.get(i).getText());
                        }
                        if (sommaPesi != 100){
                            errore=true;
                            JOptionPane.showMessageDialog(parent, "La somma dei pesi deve essere 100!", "Errore", JOptionPane.ERROR_MESSAGE);

                        }
                   }
                    if (!errore){
                    aggiungi = true;
                    setVisible(false);
                }
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
                esame.AggiornaVotoFinale();
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
