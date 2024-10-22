package Class;

import java.util.Vector;

import java.io.Serializable;

/**
 * Classe EsameComplesso
 *
 * Questa classe rappresenta un esame complesso.
 * Un esame complesso è caratterizzato da:
 * - Nome dello studente
 * - Cognome dello studente
 * - Nome dell'insegnamento
 * - Crediti dell'insegnamento
 * - Lode
 * - Voti
 * - Pesi
 * - Voto finale
 *
 * Questa classe è stata creata per permettere la creazione di esami complessi.
 *
 * La classe è stata implementata in modo da poter essere serializzata.
 *
 * Metodi:
 * - Costruttore
 * - Costruttore senza parametri
 * - Metodi Getter e Setter
 * - Metodi Reset
 * - Aggiorna il voto finale
 * - Metodo per aggiungere un voto
 * - Metodo per calcolare il voto finale
 * - Metodo toString
 *
 * Autore: Luca Mechetti 146743
 * Versione: 1.0
 */


public class EsameComplesso extends Esame{

    /**
     * Serial Version UID della classe EsameComplesso
     */

    private static final long serialVersionUID = 1L;

    //Attributi

    /**
     * Vector di interi che rappresenta i voti
     */
    private Vector<Integer> Voti;

    /**
     * Vector di interi che rappresenta i pesi
     */
    private Vector<Integer> Pesi;

    /**
     * Voto finale
     */
    private int VotoFinale;

    //Costruttore con parametri

    /**
     * Costruttore della classe EsameComplesso
     * @param Nome Nome dello studente
     * @param Cognome Cognome dello studente
     * @param NomeInsegnamento Nome dell'insegnamento
     * @param Crediti Crediti dell'esame
     * @param Lode  buleano che indica se l'esame è stato superato con lode
     * @param Voti  Vector di interi che rappresenta i voti
     * @param Pesi  Vector di interi che rappresenta i pesi
     */
    public EsameComplesso(String Nome, String Cognome, String NomeInsegnamento, int Crediti, boolean Lode, Vector<Integer> Voti, Vector<Integer> Pesi) {
        super(Nome, Cognome, NomeInsegnamento, Crediti, Lode);
        this.Voti = Voti;
        this.Pesi = Pesi;
        this.VotoFinale = CalcolaVotoFinale();
    }

    //Costruttore senza parametri

    /**
     * Costruttore senza parametri della classe EsameComplesso
     */

    public EsameComplesso() {
        super();
        this.Voti = new Vector<Integer>();
        this.Pesi = new Vector<Integer>();
        this.VotoFinale = 0;
    }

    //Metodi Getter e Setter

    /**
     * Metodo Getter per i voti
     * @return Voti
     */

    public Vector<Integer> getVoti() {
        return Voti;
    }

    /**
     * Metodo Setter per i voti
     * @param voti Vector di interi che rappresenta i voti
     */

    public void setVoti(Vector<Integer> voti) {
        Voti = voti;

    }

    /**
     * Metodo Getter per i pesi
     * @return Pesi
     */

    public Vector<Integer> getPesi() {
        return Pesi;
    }

    /**
     * Metodo Setter per i pesi
     * @param pesi Vector di interi che rappresenta i pesi
     */

    public void setPesi(Vector<Integer> pesi) {
        Pesi = pesi;


    }

    /**
     * Metodo Getter per il voto finale
     * @return VotoFinale
     */

    public int getVotoFinale() {
        return VotoFinale;
    }

    /**
     * Metodo Setter per il voto finale
     * @param votoFinale Voto finale
     */

    public void setVotoFinale(int votoFinale) {
        VotoFinale = votoFinale;
    }

    //Metodi Reset

    /**
     * Metodo per resettare i voti
     */

    public void ResetVoti(){
        Voti.clear();
    }

    /**
     * Metodo per resettare i pesi
     */

    public void ResetPesi(){
        Pesi.clear();
    }

    /**
     * Metodo per resettare il voto finale
     */

    public void ResetVotoFinale(){
        VotoFinale = 0;
    }

    //Metodo per aggiungere un voto

    /**
     * Metodo per aggiungere un voto
     * @param Voto Voto da aggiungere
     * @param Peso Peso del voto da aggiungere
     */

    public void AddVoto(int Voto, int Peso){
        Voti.add(Voto);
        Pesi.add(Peso);
        VotoFinale = CalcolaVotoFinale();
    }

    //Metodo per calcolare voto finale

    /**
     * Metodo per calcolare il voto finale
     * @return VotoFinale
     */

    public int CalcolaVotoFinale(){
        int VotoFinale = 0;
        int SommaPesi = 0;
        for(int i = 0; i < Voti.size(); i++){
            VotoFinale += Voti.get(i) * Pesi.get(i);
            SommaPesi += Pesi.get(i);
        }
        VotoFinale /= SommaPesi;
        return VotoFinale;
    }
    //Aggiorna il voto finale

    /**
     * Metodo per aggiornare il voto finale
     */

    public void AggiornaVotoFinale(){
        VotoFinale = CalcolaVotoFinale();
    }

    //Metodo toString
    /**
     * Metodo toString
     * @return String
     */

    @Override
    public String toString() {
        return "EsameComplesso{" +
                "Voti=" + Voti +
                ", Pesi=" + Pesi +
                ", Voto Finale=" + CalcolaVotoFinale() +
                "} " + super.toString();
    }
}
