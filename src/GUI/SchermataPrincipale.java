package GUI;

import GUI.Elements.Menu;
import GUI.Elements.Tabella;
import Utils.EsameUtils;
import Class.*;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.Vector;

public class SchermataPrincipale extends GuiBase {

    private Vector<Esame> esami = new Vector<Esame>();

    public SchermataPrincipale(){
        super();
        Menu menu = new Menu(this);
        this.esami.add(new EsameSemplice("Man","Swagg","20",20,false,20));
        Tabella Tabella = new Tabella();
        this.setJMenuBar(menu);
        this.add(Tabella);
    }

    public Vector<Esame> getEsami() {
        return esami;
    }

    public void setEsami(Vector<Esame> esami) {
        this.esami = esami;
    }

}
