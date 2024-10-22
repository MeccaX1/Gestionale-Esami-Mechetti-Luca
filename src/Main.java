import GUI.SchermataPrincipale;


/**
 * Classe Main
 * Classe principale del programma, contiene il metodo main
 * che crea una nuova schermata principale e la rende visibile
 * se il primo argomento è "test", crea una nuova schermata principale con il parametro true per effettuare i test
 */

public class Main {

    /**
     * Metodo main
     * @param args argomenti passati da riga di comando
     */

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("test")) {
            SchermataPrincipale schermataPrincipale = new SchermataPrincipale(true);
            schermataPrincipale.setVisible(true);
        }else{
            SchermataPrincipale schermataPrincipale = new SchermataPrincipale();
            schermataPrincipale.setVisible(true);
        }
    }
}