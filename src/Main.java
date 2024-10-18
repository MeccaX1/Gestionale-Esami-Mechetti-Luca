import GUI.SchermataPrincipale;

public class Main {
    public static void main(String[] args) {

        // se il primo argomento è "test", crea una nuova schermata principale con il parametro true per effettuare i test
        if (args.length > 0 && args[0].equals("test")) {
            SchermataPrincipale schermataPrincipale = new SchermataPrincipale(true);
            schermataPrincipale.setVisible(true);
        }else{
            SchermataPrincipale schermataPrincipale = new SchermataPrincipale();
            schermataPrincipale.setVisible(true);
        }
    }
}