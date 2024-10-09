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

public class Tabella  extends JTable {

    private Vector<Esame> esami;
    private int LastselectedRow = -1;

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
        model.addColumn("Tipo Esame");
        this.setModel(model);

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem modificaItem = new JMenuItem("Modifica");
        JMenuItem eliminaItem = new JMenuItem("Elimina");
        popupMenu.add(modificaItem);
        popupMenu.add(eliminaItem);

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
                        LastselectedRow=row;
                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            }
        });

        modificaItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Modifica");
                Esame esame = parent.getEsami().get(LastselectedRow);
                FinestraModificaEsame finestra = new FinestraModificaEsame(parent, esame);
                parent.aggiornaTabella();
            }
        });
    }

    public void addRow(Object[] row) {
        DefaultTableModel model = (DefaultTableModel) this.getModel();
        model.addRow(row);
    }
}
