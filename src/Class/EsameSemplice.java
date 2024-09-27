package Class;

import java.io.Serializable;

public class EsameSemplice extends Esame{

    private static final long serialVersionUID = 1L;

    private int Voto;

    //Costruttore

    public EsameSemplice(String Nome, String Cognome, String NomeInsegnamento, int Crediti, boolean Lode, int Voto) {
        super(Nome, Cognome, NomeInsegnamento, Crediti, Lode);
        this.Voto = Voto;
    }

    //Costruttore senza parametri

    public EsameSemplice() {
        super();
        this.Voto = 0;
    }

    //Metodi Getter e Setter

    public int getVoto() {
        return Voto;
    }

    public void setVoto(int voto) {
        Voto = voto;
    }

    //Metodo toString

    @Override
    public String toString() {
        return "EsameSemplice{" +
                "Voto=" + Voto +
                "} " + super.toString();
    }

}
