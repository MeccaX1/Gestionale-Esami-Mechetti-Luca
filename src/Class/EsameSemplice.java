package Class;

import java.io.Serializable;

/**
 * Classe EsameSemplice
 *
 * Questa classe rappresenta un esame semplice.
 * Un esame semplice è caratterizzato da:
 * - Nome dello studente
 * - Cognome dello studente
 * - Nome dell'insegnamento
 * - Crediti dell'insegnamento
 * - Lode
 * - Voto
 *
 * Questa classe è stata creata per permettere la creazione di esami semplici.
 *
 * La classe è stata implementata in modo da poter essere serializzata.
 *
 * Metodi:
 * - Costruttore
 * - Costruttore senza parametri
 * - Metodi Getter e Setter
 * - Metodo toString
 *
 * Autore: Luca Mechetti 146743
 * Versione: 1.0
 */

public class EsameSemplice extends Esame{

    /**
     * Serial Version UID della classe EsameSemplice
     */
    private static final long serialVersionUID = 1L;

    //Attributi

    /**
     * Voto dell'esame
     */
    private int Voto;

    //Costruttore

    /**
     * Costruttore della classe EsameSemplice
     * @param Nome Nome dello studente
     * @param Cognome Cognome dello studente
     * @param NomeInsegnamento Nome dell'insegnamento
     * @param Crediti Crediti dell'insegnamento
     * @param Lode buleano che indica se l'esame è stato superato con lode
     * @param Voto Voto dell'esame
     */

    public EsameSemplice(String Nome, String Cognome, String NomeInsegnamento, int Crediti, boolean Lode, int Voto) {
        super(Nome, Cognome, NomeInsegnamento, Crediti, Lode);
        this.Voto = Voto;
    }

    //Costruttore senza parametri

    /**
     * Costruttore senza parametri della classe EsameSemplice
     */

    public EsameSemplice() {
        super();
        this.Voto = 0;
    }

    //Metodi Getter e Setter

    /**
     * Metodo Getter del Voto
     * @return Voto
     */

    public int getVoto() {
        return Voto;
    }

    /**
     * Metodo Setter del Voto
     * @param voto Voto dell'esame
     */

    public void setVoto(int voto) {
        Voto = voto;
    }

    //Metodo toString

    /**
     * Metodo toString della classe EsameSemplice
     * @return String
     */

    @Override
    public String toString() {
        return "EsameSemplice{" +
                "Voto=" + Voto +
                "} " + super.toString();
    }

}
