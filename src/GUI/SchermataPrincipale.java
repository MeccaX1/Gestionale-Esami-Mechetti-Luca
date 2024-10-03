package GUI;

import GUI.Elements.Menu;
import GUI.Elements.Tabella;
import Utils.EsameUtils;
import Class.*;

import javax.management.StandardMBean;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.Vector;

public class SchermataPrincipale extends GuiBase {

    private Vector<Esame> esami = new Vector<Esame>();
    private Tabella tabella;

    public SchermataPrincipale(){
        super();
        Menu menu = new Menu(this);
        Vector<Integer> voti = new Vector<>();
        voti.add(28);
        voti.add(30);
        voti.add(25);

        Vector<Integer> pesi = new Vector<>();
        pesi.add(33);
        pesi.add(33);
        pesi.add(34);
        this.esami.add(new EsameComplesso("String Nome", "String Cognome", "String NomeInsegnamento", 10, false,voti , pesi));
        this.esami.add(new EsameComplesso("String Nome", "String Cognome", "String NomeInsegnamento", 10, false,voti , pesi));
        tabella = new Tabella(this);
        this.setJMenuBar(menu);


        JScrollPane scrollPane = new JScrollPane(tabella);
        this.add(scrollPane, BorderLayout.CENTER);

        EsameComplesso esame = (EsameComplesso) esami.get(0);
        tabella.addRow(new Object[]{esame.getNome(), esame.getCognome(), esame.getNomeInsegnamento(), esame.getCrediti(), esame.isLode(), esame.getVotoFinale()});
        tabella.addRow(new Object[]{esame.getNome(), esame.getCognome(), esame.getNomeInsegnamento(), esame.getCrediti(), esame.isLode(), esame.getVotoFinale()});
    }

    public Vector<Esame> getEsami() {
        return esami;
    }

    public void setEsami(Vector<Esame> esami) {
        this.esami = esami;
    }

    public void aggiornaTabella(){
        DefaultTableModel model = (DefaultTableModel) tabella.getModel();
        model.setRowCount(0);
        for (Esame esame : esami){
            if (esame instanceof EsameSemplice){

                EsameSemplice esameSemplice = (EsameSemplice) esame;
                System.out.println("Aggiorna"+esameSemplice.getNome()+ esameSemplice.getCognome());
                model.addRow(new Object[]{esameSemplice.getNome(), esameSemplice.getCognome(), esameSemplice.getNomeInsegnamento(), esameSemplice.getCrediti(), esameSemplice.isLode(), esameSemplice.getVoto()});
           }
//            else if (esame instanceof EsameComplesso){
//                EsameComplesso esameComplesso = (EsameComplesso) esame;
//                Tabella.addRow(new Object[]{esameComplesso.getNome(), esameComplesso.getCognome(), esameComplesso.getNomeInsegnamento(), esameComplesso.getCrediti(), esameComplesso.isLode(), esameComplesso.getVoto()});
//            }
        }

        tabella.revalidate();
        tabella.repaint();
    }

}
