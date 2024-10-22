package GUI.Elements;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Class.*;
import GUI.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

//Classe Tabella

/**
 * Classe Tabella
 * Questa classe estende JTable e rappresenta la tabella degli esami.
 * La tabella è caratterizzata da:
 * - Nome
 * - Cognome
 * - Nome Insegnamento
 * - Crediti
 * - Lode
 * - Voto
 * - Tipo Esame
 * Metodi:
 * - Costruttore
 * - Metodo addRow
 * - Metodo stampa
 * Autore: Luca Mechetti 146743
 * Versione: 1.0
 */

public class Tabella extends JTable {

    //vettore di esami

    /**
     * Vector esami Vettore di esami per la tabella
     */
    private Vector<Esame> esami;

    //serve per sapere quale riga è stata selezionata con il tasto destro

    /**
     * ultimaRigaSelezionata Riga selezionata con il tasto destro
     */
    private int ultimaRigaSelezionata = -1;

    //Costruttore della classe Tabella

    /**
     * Costruttore della classe Tabella
     * @param parent SchermataPrincipale
     */

    public Tabella(SchermataPrincipale parent) {

        DefaultTableModel model = new DefaultTableModel(){
            //rende tabella non-editable, classe anonima
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        //genero le varie colonne della tabella

        this.esami = new Vector<Esame>();
        esami = parent.getEsami();
        model.addColumn("Nome");
        model.addColumn("Cognome");
        model.addColumn("Nome Insegnamento");
        model.addColumn("Crediti");
        model.addColumn("Lode");
        model.addColumn("Voto");
        model.addColumn("Tipo Esame");

        //aggiungo il popup menu con le opzioni di modifica ed eliminazione, apribile con tasto destro

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem modificaItem = new JMenuItem("Modifica");
        JMenuItem eliminaItem = new JMenuItem("Elimina");
        popupMenu.add(modificaItem);
        popupMenu.add(eliminaItem);

        //setto il modello alla tabella, questo serve per fare in modo che la tabella sia dalle colonne con i nomi che volgiamo noi come intestazione e per impostare qualsiasi altra impostazione grafica

        this.setModel(model);

        //aggiungo un listener per gestire i click sulla tabella, doppio tasto per la visualizzazione di un esame complesso e tasto destro per il menu contestuale

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2 ) {
                    int row = rowAtPoint(e.getPoint());
                    if (row >= 0) {
                        Esame esame = esami.get(row);
                            //se la riga è diversa collassiamo la precedente e ne apriamo una nuova
                            if (esame instanceof EsameComplesso) {
                                FinestraMostraEsameComplesso Finestra = new FinestraMostraEsameComplesso(parent, (EsameComplesso) parent.getEsami().get(row));
                            }

                    }
                }else if (SwingUtilities.isRightMouseButton(e)){
                    int row = rowAtPoint(e.getPoint());
                    if (row >= 0) {
                        ultimaRigaSelezionata =row;
                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            }
        });

        //aggiungo i listener per le azioni di modifica ed eliminazione

        modificaItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Modifica");
                Esame esame = parent.getEsami().get(ultimaRigaSelezionata);
                FinestraModificaEsame finestra = new FinestraModificaEsame(parent, esame);
                parent.aggiornaTabella();
            }
        });

        eliminaItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Elimina");
                int risposta = JOptionPane.showConfirmDialog(
                        null,
                        "Sei sicuro di voler eliminare questo esame?",
                        "Conferma Eliminazione",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (risposta == JOptionPane.YES_OPTION) {
                    System.out.println("Elimina");
                    parent.getEsami().remove(ultimaRigaSelezionata);
                    parent.aggiornaTabella();
                    parent.setModificato(true);
                }
            }
        });
    }

    //metodo che aggiunge una riga alla tabella

    /**
     * Metodo addRow
     * Aggiunge una riga alla tabella
     * @param row Object[] riga da aggiungere
     */

    public void addRow(Object[] row) {

        //prendo il modello della tabella e aggiungo la riga, casting necessario per poter usare i metodi del modello
        //this.getModel() ritorna un TableModel, ma noi vogliamo un DefaultTableModel per poter usare il metodo addRow
        DefaultTableModel model = (DefaultTableModel) this.getModel();
        model.addRow(row);
    }

    //metodo per stampare la tabella, gestisce anche eventuali errori, messaggi di conferma e di annullamento. Metodo predefinito di Java
    /**
     * Metodo stampa
     * Stampa la tabella
     */
    public void stampa(){
        try {
            boolean completato = print(JTable.PrintMode.FIT_WIDTH, null, null);
            if (completato) {
                JOptionPane.showMessageDialog(null, "Stampa completata con successo", "Stampa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Stampa annullata", "Stampa", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Errore durante la stampa: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }

    }
}
