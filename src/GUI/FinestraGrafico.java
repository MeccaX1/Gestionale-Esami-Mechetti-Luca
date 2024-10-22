package GUI;

import org.knowm.xchart.*;

import Class.*;


import javax.swing.*;
import java.util.Vector;

// Classe che crea un grafico a barre con i voti degli esami

/**
 * Classe FinestraGrafico
 * La classe FinestraGrafico crea un grafico a barre con i voti degli esami.
 * Prende in input la schermata principale e crea un grafico a barre con i voti degli esami.
 *
 * Metodi:
 * - Costruttore
 *
 * Autore: Luca Mechetti 146743
 * Versione: 1.0
 */

public class FinestraGrafico {

    // Attributi della classe FinestraGrafico

    /**
     * Vector esamiFiltrati, vettore di esami filtrati
     */
    private Vector<Esame> esamiFiltrati;

    // Costruttore della classe FinestraGrafico, prende in input la schermata principale e crea un grafico a barre

    /**
     * Costruttore della classe FinestraGrafico
     * @param parent SchermataPrincipale
     */

    public FinestraGrafico(SchermataPrincipale parent ) {

        // Prendo gli esami filtrati dalla schermata principale
        esamiFiltrati = parent.getEsami();

        Vector<Integer> voti = new Vector<>();

        for (Esame esame : esamiFiltrati) {
            if(esame instanceof EsameComplesso){
                EsameComplesso esameComplesso = (EsameComplesso) esame;
                voti.add(esameComplesso.getVotoFinale());
            }else if(esame instanceof EsameSemplice){
                EsameSemplice esameSemplice = (EsameSemplice) esame;
                voti.add(esameSemplice.getVoto());
            }
        }


        // Creo un istogramma con i voti degli esami
        Histogram istogramma = new Histogram( voti, 13);

        // Creo un grafico a barre
        CategoryChart grafico = new CategoryChartBuilder().width(800).height(600).title("Voti Esami").xAxisTitle("Voti").yAxisTitle("Frequenza").build();

        //genero l'asse x con i voti da 18 a 30
        Vector<Number> xAxisData = new Vector<>();
        for (int i = 18; i <= 30; i++) {
            xAxisData.add(i);
        }

        //azzero l'asse y
        Vector<Number> yAxisData = new Vector<>();
        for (int i = 18; i <= 30; i++) {
            yAxisData.add(0);
        }

        // aggiungo i voti al grafico
        for (int voto : voti) {
            if (voto >= 18 && voto <= 30) {
                int i = xAxisData.indexOf(voto);
                yAxisData.set(i, yAxisData.get(i).intValue() + 1);
            }
        }
        grafico.addSeries("Voti", xAxisData, yAxisData);

        // Imposto lo stile del grafico, non voglio decimali e voglio che l'asse x vada da 18 a 30
        grafico.getStyler().setDecimalPattern("0");
        grafico.getStyler().setXAxisMin(18.0);
        grafico.getStyler().setXAxisMax(30.0);
        grafico.getStyler().setXAxisTickMarkSpacingHint(1);



        //mi dava errore se non mettevo il thread, creava conflitti con il thread principale

        new Thread(() -> {
            JFrame chartFrame = new SwingWrapper<>(grafico).displayChart();
            chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }).start();


    }


}
