package Class;

import java.io.Serializable;


public abstract class Esame implements Serializable {

    private static final long serialVersionUID = 1L;

    private String Nome, Cognome, NomeInsegnamento;
    private int Crediti;
    private boolean Lode;

    //Costruttore

    public Esame(String Nome, String Cognome, String NomeInsegnamento, int Crediti, boolean Lode) {
        this.Nome = Nome;
        this.Cognome = Cognome;
        this.NomeInsegnamento = NomeInsegnamento;
        this.Crediti = Crediti;
        this.Lode = Lode;
    }

    //Costruttore senza parametri

    public Esame() {
        this.Nome = "";
        this.Cognome = "";
        this.NomeInsegnamento = "";
        this.Crediti = 0;
        this.Lode = false;
    }

    //Metodi Getter e Setter


    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCognome() {
        return Cognome;
    }

    public void setCognome(String cognome) {
        Cognome = cognome;
    }

    public String getNomeInsegnamento() {
        return NomeInsegnamento;
    }

    public void setNomeInsegnamento(String nomeInsegnamento) {
        NomeInsegnamento = nomeInsegnamento;
    }

    public int getCrediti() {
        return Crediti;
    }

    public void setCrediti(int crediti) {
        Crediti = crediti;
    }

    public boolean isLode() {
        return Lode;
    }

    public void setLode(boolean lode) {
        Lode = lode;
    }

    //Metodo Reset

    public void Reset() {
        this.Nome = "";
        this.Cognome = "";
        this.NomeInsegnamento = "";
        this.Crediti = 0;
    }

    //Reset per ogni parametro

    public void ResetNome() {
        this.Nome = "";
    }

    public void ResetCognome() {
        this.Cognome = "";
    }

    public void ResetNomeInsegnamento() {
        this.NomeInsegnamento = "";
    }

    public void ResetCrediti() {
        this.Crediti = 0;
    }

    public void ResetLode() {
        this.Lode = false;
    }

    //Metodo astratto toString

    @Override
    public String toString() {
        return "Esame{" +
                "Nome='" + Nome + '\'' +
                ", Cognome='" + Cognome + '\'' +
                ", NomeInsegnamento='" + NomeInsegnamento + '\'' +
                ", Crediti=" + Crediti +
                ", Lode=" + Lode +
                '}';
    }
}
