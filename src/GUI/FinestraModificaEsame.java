package GUI;

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
    private boolean complesso = false;
    private boolean errore;


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
            complesso=true;
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
            complesso=false;
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
                if (complesso){
                    for (int i = 0; i < voti.size(); i++){
                        if (voti.get(i).getText().isEmpty() || pesi.get(i).getText().isEmpty()){
                            JOptionPane.showMessageDialog(parent, "Voti e pesi non possono essere vuoti!", "Errore", JOptionPane.ERROR_MESSAGE);
                            errore=true;
                        }
                    }
                }  if (!complesso){
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
                if (complesso){
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
                if (!complesso){
                    try {
                        Integer.parseInt(voti.getFirst().getText());
                    } catch (NumberFormatException ex){
                        errore=true;
                        JOptionPane.showMessageDialog(parent, "Voto deve essere un numero!", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if(complesso){
                    for (int i = 0; i < pesi.size(); i++){
                        if (pesi.get(i).getText().isEmpty()){
                            errore=true;
                            JOptionPane.showMessageDialog(parent, "Pesi non possono essere vuoti!", "Errore", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                if (complesso){
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
                    esameComplesso.setVotoFinale(esameComplesso.CalcolaVotoFinale());
                } else if (esame instanceof EsameSemplice) {
                    EsameSemplice esameSemplice = (EsameSemplice) esame;
                    esameSemplice.setVoto(Integer.parseInt(voti.get(0).getText()));
                }

                modificato = true;
                setVisible(false);
            }
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
