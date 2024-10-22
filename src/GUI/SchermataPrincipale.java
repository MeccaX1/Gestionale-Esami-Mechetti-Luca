package GUI;

import GUI.Elements.Menu;
import GUI.Elements.Tabella;
import Utils.AutoSalvataggio;
import Utils.EsameUtils;
import Class.*;

import javax.management.StandardMBean;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;


/**
 * Classe SchermataPrincipale
 *
 * Classe che rappresenta la schermata principale dell'applicazione, estende la classe GuiBase.
 *
 * Metodi:
 * -Costruttore
 *
 * Autore: Luca Mechetti 146743
 * Versione: 1.0
 */

public class SchermataPrincipale extends GuiBase {

    // Attributi della classe SchermataPrincipale

    /**
     * Vector esami, vettore di esami
     */
    private Vector<Esame> esami = new Vector<Esame>();
    /**
     * Tabella tabella, tabella degli esami
     */
    private Tabella tabella;
    /**
     * boolean modificato, booleano per sapere se la tabella è stata modificata
     */
    private boolean modificato;
    /**
     * boolean filtrato, booleano per sapere se la tabella è stata filtrata
     */
    private boolean filtrato;
    /**
     * AutoSalvataggio autosave, autosave per salvare i dati
     */
    private final AutoSalvataggio autosave;
    /**
     * boolean filtraggio, booleano per sapere se la tabella è stata filtrata
     */
    private boolean filtraggio = false;

    // Costruttore, crea la schermata principale, inizializza il menu, la tabella e il thread per l'autosave.
    // Inoltre, aggiunge un listener per la chiusura della finestra, che chiede all'utente se vuole salvare i dati prima di uscire.
    // Aggiunto un costruttore con codice che crea un vettore di esami e li aggiunge alla tabella per effettuare i test

    /**
     * Costruttore della classe SchermataPrincipale senza parametri
     *
     */

    public SchermataPrincipale(){
        super(); // chiama il costruttore della superclasse GuiBase
        autosave = new AutoSalvataggio(this); // crea un nuovo autosave
        Thread  thread = new Thread(autosave); // crea un nuovo thread per l'autosave
        thread.start(); // avvia il thread

        // crea un nuovo menu
        Menu menu = new Menu(this);

        // creo tabella
        tabella = new Tabella(this);

        // aggiungo il menu alla finestra
        this.setJMenuBar(menu);

        // creo uno scrollPane per la tabella
        JScrollPane scrollPane = new JScrollPane(tabella);

        // aggiungo lo scrollPane alla finestra
        this.add(scrollPane, BorderLayout.CENTER);

        // aggiorno la tabella
        this.aggiornaTabella();

        // aggiungo un listener per la chiusura della finestra che chiede all'utente se vuole salvare i dati prima di uscire
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (modificato){
                    int dialogResult = JOptionPane.showConfirmDialog (null, "Vuoi salvare i dati prima di uscire?","Attenzione",JOptionPane.YES_NO_OPTION);
                    if(dialogResult == JOptionPane.YES_OPTION){
                        EsameUtils.salvaEsami(esami);
                    }
                }
            }
        });

    }

    // Costruttore con parametro booleano per effettuare i test

    /**
     * Costruttore della classe SchermataPrincipale con parametro booleano
     *
     * @param test booleano per effettuare i test
     */

    public SchermataPrincipale(boolean test){
        super();
        autosave = new AutoSalvataggio(this);
        Thread  thread = new Thread(autosave);
        thread.start();
        Menu menu = new Menu(this);

        if (test) {

            Vector<Integer> voti = new Vector<>();
            voti.add(28);
            voti.add(30);
            voti.add(25);

            Vector<Integer> pesi = new Vector<>();
            pesi.add(33);
            pesi.add(33);
            pesi.add(34);
            for (int i = 0; i < 50; i++) {
                this.esami.add(new EsameSemplice("String Nome" + i, "String Cognome", "String NomeInsegnamento", 10, false, 30));
            }
            this.esami.add(new EsameComplesso("String Nome", "String Cognome", "String NomeInsegnamento", 10, false, voti, pesi));
            this.esami.add(new EsameComplesso("String Nome2", "String Cognome", "String NomeInsegnamento", 11, false, voti, pesi));
        }
        tabella = new Tabella(this);
        this.setJMenuBar(menu);


        JScrollPane scrollPane = new JScrollPane(tabella);
        this.add(scrollPane, BorderLayout.CENTER);

        this.aggiornaTabella();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (modificato){
                    int dialogResult = JOptionPane.showConfirmDialog (null, "Vuoi salvare i dati prima di uscire?","Attenzione",JOptionPane.YES_NO_OPTION);
                    if(dialogResult == JOptionPane.YES_OPTION){
                        EsameUtils.salvaEsami(esami);
                    }
                }
            }
        });

    }


    //Metodi get e set per gli attributi della classe

    /**
     * Metodo get per l'attributo tabella
     * @return tabella Tabella
     */

    public Tabella getTabella() {
        return tabella;
    }

    /**
     * Metodo set per l'attributo tabella
     * @param tabella Tabella
     */

    public void setTabella(Tabella tabella) {
        this.tabella = tabella;
    }

    /**
     * Metodo get per l'attributo modificato
     * @return modificato boolean
     */

    public boolean isModificato() {
        return modificato;
    }

    /**
     * Metodo set per l'attributo modificato
     * @param modificato boolean
     */

    public void setModificato(boolean modificato) {
        this.modificato = modificato;
    }

    /**
     * Metodo get per l'attributo filtrato
     * @return filtrato boolean
     */

    public boolean isFiltrato() {
        return filtrato;
    }

    /**
     * Metodo set per l'attributo filtrato
     * @param filtrato boolean
     */

    public void setFiltrato(boolean filtrato) {
        this.filtrato = filtrato;
    }

    /**
     * Metodo get per l'attributo autosave
     * @return autosave AutoSalvataggio
     */

    public AutoSalvataggio getAutosave() {
        return autosave;
    }

    /**
     * Metodo get per l'attributo esami
     * @return esami Vector di esami
     */

    public Vector<Esame> getEsami() {
        return esami;
    }

    /**
     * Metodo set per l'attributo esami
     * @param esami Vector di esami
     */


    public void setEsami(Vector<Esame> esami) {
        this.esami = esami;
    }

    /**
     * Metodo get per l'attributo filtraggio
     * @return filtraggio boolean
     */

    public boolean isFiltraggio() {
        return filtraggio;
    }

    /**
     * Metodo set per l'attributo filtraggio
     * @param filtraggio boolean
     */

    public void setFiltraggio(boolean filtraggio) {
        this.filtraggio = filtraggio;
    }

    //Metodo per aggiornare la tabella, prende i dati dal vettore di esami e li aggiunge alla tabella

    /**
     * Metodo per aggiornare la tabella logicamente e graficamente con i dati presenti nel vettore di esami
     */

    public void aggiornaTabella(){
        DefaultTableModel model = (DefaultTableModel) tabella.getModel();
        model.setRowCount(0);
        for (Esame esame : esami){
            if (esame instanceof EsameSemplice){

                EsameSemplice esameSemplice = (EsameSemplice) esame;
                System.out.println("Aggiorna"+esameSemplice.getNome()+ esameSemplice.getCognome());
                if (esameSemplice.isLode()){
                    model.addRow(new Object[]{esameSemplice.getNome(), esameSemplice.getCognome(), esameSemplice.getNomeInsegnamento(), esameSemplice.getCrediti(), "Si", esameSemplice.getVoto(), "Semplice" });

                }else {
                    model.addRow(new Object[]{esameSemplice.getNome(), esameSemplice.getCognome(), esameSemplice.getNomeInsegnamento(), esameSemplice.getCrediti(), "No", esameSemplice.getVoto(), "Semplice" });
                }
           }
            else if (esame instanceof EsameComplesso){
               EsameComplesso esameComplesso = (EsameComplesso) esame;
               if (esameComplesso.isLode()) {
                   model.addRow(new Object[]{esameComplesso.getNome(), esameComplesso.getCognome(), esameComplesso.getNomeInsegnamento(), esameComplesso.getCrediti(), "Si", esameComplesso.getVotoFinale(), "Complesso"});
               }else {
                   model.addRow(new Object[]{esameComplesso.getNome(), esameComplesso.getCognome(), esameComplesso.getNomeInsegnamento(), esameComplesso.getCrediti(), "No", esameComplesso.getVotoFinale(), "Complesso"});
               }
            }
        }
        // aggiorno graficamente e logicamente la tabella
        //revalidate() e repaint() sono metodi che permettono di aggiornare la tabella, revalidate() ricalcola la dimensione e i componenti della tabella, repaint() ridisegna la tabella
        tabella.revalidate();
        tabella.repaint();
    }

}
