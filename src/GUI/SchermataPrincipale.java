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
        this.esami.add(new EsameSemplice("Man","Swagg","20",20,false,20));
        tabella = new Tabella();
        this.setJMenuBar(menu);


        JScrollPane scrollPane = new JScrollPane(tabella);
        this.add(scrollPane, BorderLayout.CENTER);

        EsameSemplice esame = (EsameSemplice) esami.get(0);
        tabella.addRow(new Object[]{esame.getNome(), esame.getCognome(), esame.getNomeInsegnamento(), esame.getCrediti(), esame.isLode(), esame.getVoto()});
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
