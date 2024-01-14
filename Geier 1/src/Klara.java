import java.util.ArrayList;
/**
 *
 * @author Klara Schramm
 * @version 10.01.2024
 */
public class Klara extends HolsDerGeierSpieler {


    private ArrayList<Integer> nochNichtGespielt = new ArrayList<Integer>();


    //public IntelligenterGeier() {}

    public void reset() {

        for (int i = 15; i > 0; i--)
            nochNichtGespielt.add(i);
    }

    //* Strategie nochzuGewinnen: 3 Zahlenbereiche: 1. Zahlen von -5 (14) bis -1, 2. Zahlen von 1-5 und Zahlen von 6-10*//
    //*Strategie nochnichtGespielt: 3 Zahlenbereiche: 1. Zahlen von 1-5 2. 5-9 3.10-13 bei 10 get last 9=15
    public int gibKarte(int naechsteKarte) {
        int ret = -99;

        //* 1. Zahlenbereich*//
        //Größe von noch nicht Gespielt kontrolliert letzten Array slot und get diese//
        if (naechsteKarte == 10) {
            ret = nochNichtGespielt.get(nochNichtGespielt.size() - 1);
        } else if (naechsteKarte == 9) {
            ret = 15;
        } else if (naechsteKarte >= -4) {
            int a, b;

            if (naechsteKarte >= 6) {
                a = 3;
                b = 11;
            } else if (naechsteKarte >= 1) {
                a = 6;
                b = 1;
            } else {
                a = 4;
                b = 7;
            }
            do {
                ret = (int) (Math.random() * 100) % a + b;
                //Kontrolle ob Karte existiert//
            } while (!nochNichtGespielt.contains(ret));

        } else if (naechsteKarte == -5) {
            ret = 14;
        }
        nochNichtGespielt.remove(nochNichtGespielt.indexOf(ret));
        return ret;
    }
}