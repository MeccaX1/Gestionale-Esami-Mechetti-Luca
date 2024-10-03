package GUI.Elements;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Class.*;
import GUI.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class Tabella  extends JTable {

    private Vector<Esame> esami;
    private int ExpandedRow = -1;

    public Tabella(SchermataPrincipale parent) {

        DefaultTableModel model = new DefaultTableModel(){
            //Override isCellEditable to make the cells non-editable, classe anonima
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.esami = new Vector<Esame>();
        this.esami = parent.getEsami();
        model.addColumn("Nome");
        model.addColumn("Cognome");
        model.addColumn("Nome Insegnamento");
        model.addColumn("Crediti");
        model.addColumn("Lode");
        model.addColumn("Voto");
        this.setModel(model);
//        this.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                int row = rowAtPoint(e.getPoint());
//                if (row >= 0) {
//                    Esame esame = esami.get(row);
//                    if (row != ExpandedRow){
//                        //se la riga è diversa collassiamo la precedente e ne apriamo una nuova
//                        if (esame instanceof EsameComplesso) {
//
//                            EsameComplesso esameComplesso = (EsameComplesso) esame;
//                            if (ExpandedRow != -1) {
//                                for (int i = 0; i < esameComplesso.getVoti().size(); i++) {
//                                    model.removeRow( ExpandedRow+ 1);
//                                }
//                            }
//
//                            for (int i =0; i < esameComplesso.getVoti().size(); i++) {
//                                model.insertRow(row + 1, new Object[]{"", "", "", "Parziale n " + (i + 1), "Peso "+ esameComplesso.getPesi().get(i), "voto " + esameComplesso.getVoti().get(i)});
//
//                            }
//                            ExpandedRow= row;
//                        }
//                    }else {
//                        if (esame instanceof EsameComplesso) {
//
//                            EsameComplesso esameComplesso = (EsameComplesso) esame;
//
//                            for (int i = 0; i < esameComplesso.getVoti().size(); i++) {
//                                model.removeRow(row + 1);
//                            }
//                            ExpandedRow= -1;
//                        }
//                    }
//
//                }
//            }
//        });
    }

    public void addRow(Object[] row) {
        DefaultTableModel model = (DefaultTableModel) this.getModel();
        model.addRow(row);
    }
}
