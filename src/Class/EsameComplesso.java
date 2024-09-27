package Class;

import java.util.Vector;

import java.io.Serializable;



public class EsameComplesso extends Esame{

    private static final long serialVersionUID = 1L;

    private Vector<Integer> Voti;
    private Vector<Integer> Pesi;
    private int VotoFinale;

    //Costruttore con parametri
    public EsameComplesso(String Nome, String Cognome, String NomeInsegnamento, int Crediti, boolean Lode, Vector<Integer> Voti, Vector<Integer> Pesi) {
        super(Nome, Cognome, NomeInsegnamento, Crediti, Lode);
        this.Voti = Voti;
        this.Pesi = Pesi;
        this.VotoFinale = CalcolaVotoFinale();
    }

    //Costruttore senza parametri

    public EsameComplesso() {
        super();
        this.Voti = new Vector<Integer>();
        this.Pesi = new Vector<Integer>();
        this.VotoFinale = 0;
    }

    //Metodi Getter e Setter

    public Vector<Integer> getVoti() {
        return Voti;
    }

    public void setVoti(Vector<Integer> voti) {
        Voti = voti;
        VotoFinale = CalcolaVotoFinale();
    }

    public Vector<Integer> getPesi() {
        return Pesi;
    }

    public void setPesi(Vector<Integer> pesi) {
        Pesi = pesi;
        VotoFinale = CalcolaVotoFinale();

    }

    public int getVotoFinale() {
        return VotoFinale;
    }

    public void setVotoFinale(int votoFinale) {
        VotoFinale = votoFinale;
    }

    //Metodi Reset

    public void ResetVoti(){
        Voti.clear();
    }

    public void ResetPesi(){
        Pesi.clear();
    }

    public void ResetVotoFinale(){
        VotoFinale = 0;
    }

    //Metodo per aggiungere un voto

    public void AddVoto(int Voto, int Peso){
        Voti.add(Voto);
        Pesi.add(Peso);
        VotoFinale = CalcolaVotoFinale();
    }

    //Metodo per calcolare voto finale

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
    public void AggiornaVotoFinale(){
        VotoFinale = CalcolaVotoFinale();
    }

    //Metodo toString

    @Override
    public String toString() {
        return "EsameComplesso{" +
                "Voti=" + Voti +
                ", Pesi=" + Pesi +
                ", Voto Finale=" + CalcolaVotoFinale() +
                "} " + super.toString();
    }
}
