package GUI;

import Class.Esame;
import Class.EsameComplesso;
import Class.EsameSemplice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

// Classe per la creazione della finestra di aggiunta di un esame

/**
 * Classe FinestraAggiungiEsame
 * Classe per la creazione della finestra di aggiunta di un esame
 * La classe estende la classe JDialog e implementa l'interfaccia ActionListener
 *
 * Metodi:
 * - Costruttore
 * - Metodo schermataDialogoAggiunta
 *
 * Autore: Luca Mechetti 146743
 * Versione: 1.0
 */

public class FinestraAggiungiEsame extends JDialog {

    // Attributi della classe FinestraAggiungiEsame

    /**
     * JTextField nome, campo di testo per l'inserimento del nome dello studente
     */
    private JTextField nome;
    /**
     * JTextField cognome, campo di testo per l'inserimento del cognome dello studente
     */
    private JTextField cognome;
    /**
     * JTextField nomeInsegnamento, campo di testo per l'inserimento del nome dell'insegnamento
     */
    private JTextField nomeInsegnamento;
    /**
     * JTextField crediti, campo di testo per l'inserimento dei crediti dell'esame
     */
    private JTextField crediti;
    /**
     * JCheckBox lode, checkbox per selezionare la lode
     */
    private JCheckBox lode;
    /**
     * Vector di JTextField voti, vettore di campi di testo per l'inserimento dei voti degli esami complessi
     */
    private Vector<JTextField> voti;
    /**
     * Vector di JTextField pesi, vettore di campi di testo per l'inserimento dei pesi degli esami complessi
     */
    private Vector<JTextField> pesi;
    /**
     * boolean modificato, booleano per controllare se l'esame è stato modificato
     */
    private boolean modificato = false;
    /**
     * boolean complesso, booleano per controllare se l'esame è complesso
     */
    private boolean complesso = false;
    /**
     * boolean errore, booleano per controllare se ci sono errori
     */
    private boolean errore;
    /**
     * int numeroVoti, numero di esami da aggiungere
     */
    private int numeroVoti;
    /**
     * Esame esame, esame da aggiungere
     */
    private Esame esame;

    // Costruttore della finestra di aggiunta di un esame, che prende in input la schermata principale per poter accedere tramite riferimento ai suoi metodi e attributi

    /**
     * Costruttore della finestra di aggiunta di un esame
     * @param parent SchermataPrincipale, schermata principale del programma
     */

    public FinestraAggiungiEsame(SchermataPrincipale parent) {
        // costruttore della superclasse JDialog
        super(parent, "Aggiungi Esame", true);

        // chiamata al metodo schermataDialogoAggiunta per selezionare il tipo di esame da aggiungere, se gli esami sono più di uno si sta cercando di aggiungere un esame complesso
        numeroVoti = schermataDialogoAggiunta(parent);
        if (numeroVoti == 1) {
            esame = new EsameSemplice();
        } else {
            esame = new EsameComplesso();
        }

        // impostazione delle dimensioni e del layout della finestra

        setSize(400, 300);
        setLayout(new GridLayout(0, 2));

        // creazione dei campi di testo per l'inserimento dei dati dell'esame

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

        // creazione dei campi di testo per l'inserimento dei voti e dei pesi degli esami complessi

        voti = new Vector<>();
        pesi = new Vector<>();

        // se l'esame è complesso, vengono creati i campi di testo per l'inserimento dei voti e dei pesi
        // altrimenti viene creato un solo campo di testo per l'inserimento del voto

        if (esame instanceof EsameComplesso) {
            System.out.println("Esame complesso2");
            complesso=true;
            for (int i = 0; i < numeroVoti; i++) {
                JTextField votoField = new JTextField();
                JTextField pesoField = new JTextField();
                voti.add(votoField);
                pesi.add(pesoField);
                add(new JLabel("Voto " + (i + 1) + ":"));
                add(votoField);
                add(new JLabel("Peso " + (i + 1) + ":"));
                add(pesoField);
            }
        } else if (esame instanceof EsameSemplice) {
            complesso=false;
            JTextField votoField = new JTextField();
            voti.add(votoField);
            add(new JLabel("Voto:"));
            add(votoField);
        }

        // creazione dei bottoni per confermare o annullare l'aggiunta dell'esame

        JButton conferma = new JButton("Conferma");
        conferma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //varabile per controllare se ci sono errori
                errore=false;

                //sezione finestre allarme

                // controllo che tutti i campi non siano vuoti
                if (nome.getText().isEmpty() || cognome.getText().isEmpty() || nomeInsegnamento.getText().isEmpty() || crediti.getText().isEmpty() || voti.isEmpty()){
                    errore=true;
                    JOptionPane.showMessageDialog(parent, "Tutti i campi devono essere compilati!", "Errore", JOptionPane.ERROR_MESSAGE);
                }

                //controllo lode solo con 30
                if (lode.isSelected() && Integer.parseInt(voti.get(0).getText()) < 30){
                    errore=true;
                    JOptionPane.showMessageDialog(parent, "Lode selezionabile solo con 30 come voto!", "Errore", JOptionPane.ERROR_MESSAGE);
                }

                //contollo lode solo con 30 in ogni voto per esame complesso
                if (lode.isSelected() && complesso){
                    for (int i = 0; i < voti.size(); i++){
                        if (Integer.parseInt(voti.get(i).getText()) < 30){
                            errore=true;
                            JOptionPane.showMessageDialog(parent, "Lode selezionabile solo con 30 come voto!", "Errore", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                    }
                }

                //controllo che nome, cognome e materia non contengano numeri
                if (nome.getText().matches(".*\\d.*") || cognome.getText().matches(".*\\d.*") || nomeInsegnamento.getText().matches(".*\\d.*")){
                    errore=true;
                    JOptionPane.showMessageDialog(parent, "Nome, cognome e materia non possono contenere numeri!", "Errore", JOptionPane.ERROR_MESSAGE);
                }

                //controllo che il voto sia compreso tra 18 e 30
                if( Integer.parseInt(voti.get(0).getText()) < 18 || Integer.parseInt(voti.get(0).getText()) > 30){
                    errore=true;
                    JOptionPane.showMessageDialog(parent, "Voto deve essere compreso tra 18 e 30!", "Errore", JOptionPane.ERROR_MESSAGE);
                }

                //controllo che i voti e i pesi non siano vuoti
                if (complesso){
                    for (int i = 0; i < voti.size(); i++){
                        if (voti.get(i).getText().isEmpty() || pesi.get(i).getText().isEmpty()){
                            JOptionPane.showMessageDialog(parent, "Voti e pesi non possono essere vuoti!", "Errore", JOptionPane.ERROR_MESSAGE);
                            errore=true;
                        }
                    }
                }  if (!complesso){
                    if (voti.get(0).getText().isEmpty()){
                        JOptionPane.showMessageDialog(parent, "Voto non può essere vuoto!", "Errore", JOptionPane.ERROR_MESSAGE);
                        errore=true;
                    }
                }

                //controllo che i crediti siano maggiori di 0
                if (Integer.parseInt(crediti.getText()) <= 0){
                    errore=true;
                    JOptionPane.showMessageDialog(parent, "Crediti devono essere maggiori di 0!", "Errore", JOptionPane.ERROR_MESSAGE);

                }

                //controllo che i pesi siano numeri
                if (nome.getText().isEmpty() || cognome.getText().isEmpty() || nomeInsegnamento.getText().isEmpty()){
                    errore=true;
                    JOptionPane.showMessageDialog(parent, "Nome, cognome e nome insegnamento non possono essere vuoti!", "Errore", JOptionPane.ERROR_MESSAGE);

                }

                //controllo che i pesi e i voti siano numeri
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

                //controllo che il voto sia un numero
                if (!complesso){
                    try {
                        Integer.parseInt(voti.get(0).getText());
                    } catch (NumberFormatException ex){
                        errore=true;
                        JOptionPane.showMessageDialog(parent, "Voto deve essere un numero!", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }

                //controllo che i pesi siano numeri
                if(complesso){
                    for (int i = 0; i < pesi.size(); i++){
                        if (pesi.get(i).getText().isEmpty()){
                            errore=true;
                            JOptionPane.showMessageDialog(parent, "Pesi non possono essere vuoti!", "Errore", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }

                //controllo che la somma dei pesi sia 100
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

                //controllo che non ci sia errore, se non c'è aggiungo l'esame
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
                    parent.getEsami().add(esame);
                    parent.setModificato(true);
                    setVisible(false);
                }
            }
        });

        JButton annulla = new JButton("Annulla");
        annulla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
            }

        });

        add(conferma);
        add(annulla);

        setLocationRelativeTo(parent);
        setVisible(true);
    }

    // metodo per selezionare il tipo di esame da aggiungere

    /**
     * Metodo per selezionare il tipo di esame da aggiungere
     * @param parent SchermataPrincipale, schermata principale del programma
     * @return int, numero di esami da aggiungere
     */
    private final int schermataDialogoAggiunta(Frame parent) {
        String[] options = {"Esame Semplice", "Esame Complesso"};
        int choice = JOptionPane.showOptionDialog(this, "Seleziona il tipo di esame da aggiungere:", "Seleziona Tipo di Esame",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == 1) {
            String input = JOptionPane.showInputDialog(this, "Inserisci numero esami:", "Esame complesso", JOptionPane.QUESTION_MESSAGE);
            if (input != null) {
                try {
                    return Integer.parseInt(input);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Input non valido. Per favore inserisci un numero intero.", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        return 1;
    }
}
