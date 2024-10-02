package GUI.Elements;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Tabella  extends JTable {

    public Tabella() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nome");
        model.addColumn("Cognome");
        model.addColumn("Nome Insegnamento");
        model.addColumn("Crediti");
        model.addColumn("Lode");
        model.addColumn("Voto");
        this.setModel(model);
    }

    public void addRow(Object[] row) {
        DefaultTableModel model = (DefaultTableModel) this.getModel();
        model.addRow(row);
    }
}
