package GUI;

import GUI.Elements.Menu;
import GUI.Elements.Tabella;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public class SchermataPrincipale extends GuiBase {

    public SchermataPrincipale(){
        super();
        Menu menu = new Menu();
        Tabella Tabella = new Tabella();
        this.setJMenuBar(menu);
        this.add(Tabella);
    }


}
