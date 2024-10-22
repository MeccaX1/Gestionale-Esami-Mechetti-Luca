package Class;

import java.io.Serializable;

/**
 * Classe astratta Esame
 * Questa classe astratta rappresenta un esame.
 * Un esame è caratterizzato da:
 * - Nome dello studente
 * - Cognome dello studente
 * - Nome dell'insegnamento
 * - Crediti dell'insegnamento
 * - Lode
 * Questa classe astratta è stata creata per permettere la creazione di esami di diversi tipi.
 * La classe è stata implementata in modo da poter essere serializzata.
 * Metodi:
 * - Costruttore
 * - Costruttore senza parametri
 * - Metodi Getter e Setter
 * - Metodo Reset
 * - Reset per ogni parametro
 * - Metodo astratto toString
 * Autore: Luca Mechetti 146743
 * Versione: 1.0
 */


public abstract class Esame implements Serializable {

    //Attributi
    /** Versione del seriale */
    private static final long serialVersionUID = 1L;


    /** Nome dello studente,
     * Cognome dello studente,
     * Nome dell'insegnamento,
     */
    private String Nome,Cognome, NomeInsegnamento;

    /** Crediti dell'esame */
    private int Crediti;

    /** Variabile buleana che definisce se l'esame è passato con Lode */
    private boolean Lode;

    //Costruttore

    /**
     * Costruttore della classe Esame
     * @param Nome Nome dello studente
     * @param Cognome Cognome dello studente
     * @param NomeInsegnamento Nome dell'insegnamento
     * @param Crediti Crediti dell'esmae
     * @param Lode Lode
     */
    public Esame(String Nome, String Cognome, String NomeInsegnamento, int Crediti, boolean Lode) {
        this.Nome = Nome;
        this.Cognome = Cognome;
        this.NomeInsegnamento = NomeInsegnamento;
        this.Crediti = Crediti;
        this.Lode = Lode;
    }

    //Costruttore senza parametri
    /**
     * Costruttore senza parametri della classe Esame
     */
    public Esame() {
        this.Nome = "";
        this.Cognome = "";
        this.NomeInsegnamento = "";
        this.Crediti = 0;
        this.Lode = false;
    }

    //Metodi Getter e Setter
    /**
     * Metodo Getter per il nome dello studente
     * @return Nome
     */

    public String getNome() {
        return Nome;
    }

    /**
     * Metodo Setter per il nome dello studente
     * @param nome nome da settare
     */

    public void setNome(String nome) {
        Nome = nome;
    }

    /**
     * Metodo Getter per il cognome dello studente
     * @return Cognome
     */

    public String getCognome() {
        return Cognome;
    }

    /**
     * Metodo Setter per il cognome dello studente
     * @param cognome cognome da settare
     */

    public void setCognome(String cognome) {
        Cognome = cognome;
    }

    /**
     * Metodo Getter per il nome dell'insegnamento
     * @return NomeInsegnamento
     */

    public String getNomeInsegnamento() {
        return NomeInsegnamento;
    }

    /**
     * Metodo Setter per il nome dell'insegnamento
     * @param nomeInsegnamento nome dell'insegnamento da settare
     */

    public void setNomeInsegnamento(String nomeInsegnamento) {
        NomeInsegnamento = nomeInsegnamento;
    }

    /**
     * Metodo Getter per i crediti dell'insegnamento
     * @return Crediti
     */

    public int getCrediti() {
        return Crediti;
    }

    /**
     * Metodo Setter per i crediti dell'insegnamento
     * @param crediti crediti da settare
     */

    public void setCrediti(int crediti) {
        Crediti = crediti;
    }

    /**
     * Metodo Getter per la lode
     * @return Lode
     */

    public boolean isLode() {
        return Lode;
    }

    /**
     * Metodo Setter per la lode
     * @param lode lode da settare
     */

    public void setLode(boolean lode) {
        Lode = lode;
    }

    //Metodo Reset

    /**
     * Metodo Reset
     * Resetta tutti i parametri dell'esame
     */

    public void Reset() {
        this.Nome = "";
        this.Cognome = "";
        this.NomeInsegnamento = "";
        this.Crediti = 0;
    }

    //Reset per ogni parametro

    /**
     * Metodo ResetNome
     * Resetta il nome dello studente
     */

    public void ResetNome() {
        this.Nome = "";
    }

    /**
     * Metodo ResetCognome
     * Resetta il cognome dello studente
     */

    public void ResetCognome() {
        this.Cognome = "";
    }

    /**
     * Metodo ResetNomeInsegnamento
     * Resetta il nome dell'insegnamento
     */

    public void ResetNomeInsegnamento() {
        this.NomeInsegnamento = "";
    }

    /**
     * Metodo ResetCrediti
     * Resetta i crediti dell'insegnamento
     */

    public void ResetCrediti() {
        this.Crediti = 0;
    }

    /**
     * Metodo ResetLode
     * Resetta la lode
     */

    public void ResetLode() {
        this.Lode = false;
    }

    //Metodo astratto toString

    /**
     * Metodo astratto toString
     * @return String
     */

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
