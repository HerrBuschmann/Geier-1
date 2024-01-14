import java.util.ArrayList;

/**
 * @author Kakan Chaudhary
 * @version 13.01.2024
 */
public class Kakan extends HolsDerGeierSpieler {

    public Kakan() {
        reset();
    }

    // Alle Variablen werden zugewiesen
    ArrayList<Integer> meineKarten;
    ArrayList<Integer> kartenGegner;
    ArrayList<Integer> punkteKarten;
    ArrayList<Integer> karten;

    boolean stechen = false;

    int zug = 0;
    int meineKarte = 0;
    int letztenKSpieler; // die LetztenKarten der beiden Spieler
    int letzteKarte = 0;
    int meinePunkte = 0;
    int gegnerPunkte = 0;
    int gewonnen = 0;
    int gegnerGewonnen = 0;
    int runden = 0;
    int hoechsteKarte = 0;
    int letztePunkteKarte = 1;

    // die Punkte werden werden jeweils für gegner und mich zusammengezählt und verteilt zusatzlich
    // kann ich somit zählen wir viele Züge gespielt wurden
    private int PunkteZaehler() {
        if (meineKarte > letzterZug()) {
            meinePunkte += letztePunkteKarte;
            letztePunkteKarte = 0;
            zug++;
        } else if (meineKarte < letzterZug()) {
            gegnerPunkte += letztePunkteKarte;
            letztePunkteKarte = 0;
            zug++;
        }
        return meineKarte;
    }
    // wer mehr Punkte am Ende hat, hat das spiel gewonnen.
    private void gewonneneSpiele() {
        if (zug >= 15) {
            if (meinePunkte > gegnerPunkte) {
                gewonnen++;
            } else if (meinePunkte < gegnerPunkte) {
                gegnerGewonnen++;
            }
        }
    }
    //Ich gebe alle Werte ein die initialisiert werden müssen um
    //das Spiel zu starten bzw. neu zu beginnen.
    @Override
    public void reset() {
        zug = 0;
        meinePunkte = 0;
        gegnerPunkte = 0;
        gewonnen = 0;
        gegnerGewonnen = 0;
        runden = 0;

        // jetzt müssen die Array initialisiert werden
        meineKarten = new ArrayList<Integer>();
        kartenGegner = new ArrayList<Integer>();
        karten = new ArrayList<Integer>();

        for (int i = 1; i <= 15; i++) {
            meineKarten.add(i);
            kartenGegner.add(i);
        }
        // array für Punktekarten befüllen

        for (int i = -5; i <= 10; i++) {
            if (i != 0) {
                karten.add(i);
            }
        }
    }

    // wenn ich eine Karte als meineKarte int spiele, dass diese aus meinenKarten
    // rausgenommen wird
    private void kartenentfernen() {
        meineKarten.remove(meineKarten.indexOf(meineKarte));
        if (runden > 1) {
            kartenGegner.remove(kartenGegner.indexOf(letzterZug()));
        }
    }
    //eine Methode um meine höchste noch vorhandene Karte zu ermitteln
    public int hoechsteKarte() {
        return meineKarten.get(meineKarten.size() - 1);
    }
    //eine Methode um meine niedrigste noch vorhandene Karte zu ermitteln
    public int niedrigsteKarte() {
        return meineKarten.get(0);

    }
    //eine Methode um Karten aus meinenKarten random auszuwählen und zu spielen
    private int randomS() {
        int anzahlk = meineKarten.size() - 1;
        int index = (int) (Math.random() * anzahlk);
        return meineKarten.get(index);
    }

    @Override
    public int gibKarte(int naechsteKarte) {
        /*
         * letztePunkteKarte += naechsteKarte; // kartenentfernen(); runden++;
         * meineKarte = kartenStechen(letztePunkteKarte); meineKarten.remove((Object)
         * (meineKarten.indexOf(meineKarte))); meineKarte = letzteKarte;
         */
//so sollten die Karten gespielt werden wenn kein Stechen stattfindet und diese noch vorhanden sinde
        if (letzteKarte == letzterZug()){
            letzteKarte = kartenStechen(naechsteKarte);
            meineKarten.remove(meineKarten.indexOf(letzteKarte));
            return letzteKarte;
        }
        else if (gewonnen >= gegnerGewonnen) {


            switch (naechsteKarte) {
                case -5:
                    naechsteKarte = 10;
                    break;
                case -4:
                    naechsteKarte = 11;
                    break;
                case -3:
                    naechsteKarte = 7;
                    break;
                case -2:
                    naechsteKarte = 5;
                    break;
                case -1:
                    naechsteKarte = 1;
                    break;
                case 1:
                    naechsteKarte = 2;
                    break;
                case 2:
                    naechsteKarte = 4;
                    break;
                case 3:
                    naechsteKarte = 9;
                    break;
                case 4:
                    naechsteKarte = 12;
                    break;
                case 5:
                    naechsteKarte = 13;
                    break;
                case 6:
                    naechsteKarte = 14;
                    break;
                case 7:
                    naechsteKarte = 3;
                    break;
                case 8:
                    naechsteKarte = 8;
                    break;
                case 9:
                    naechsteKarte = 15;
                    break;
                case 10:
                    naechsteKarte = 6;
                    break;
            }
            meineKarten.remove(meineKarten.indexOf(naechsteKarte));
            letzteKarte = naechsteKarte;
            return naechsteKarte;
        }else {
            switch (naechsteKarte) {
                case -5:
                    naechsteKarte = 12;
                    break;
                case -4:
                    naechsteKarte = 11;
                    break;
                case -3:
                    naechsteKarte = 8;
                    break;
                case -2:
                    naechsteKarte = 6;
                    break;
                case -1:
                    naechsteKarte = 4;
                    break;
                case 1:
                    naechsteKarte = 3;
                    break;
                case 2:
                    naechsteKarte = 5;
                    break;
                case 3:
                    naechsteKarte = 7;
                    break;
                case 4:
                    naechsteKarte = 9;
                    break;
                case 5:
                    naechsteKarte = 10;
                    break;
                case 6:
                    naechsteKarte = 13;
                    break;
                case 7:
                    naechsteKarte = 14;
                    break;
                case 8:
                    naechsteKarte = 15;
                    break;
                case 9:
                    naechsteKarte = 2;
                    break;
                case 10:
                    naechsteKarte = 1;
                    break;
            }
            meineKarten.remove(meineKarten.indexOf(naechsteKarte));
            letzteKarte = naechsteKarte;
            return naechsteKarte;
        }

    }



    //Im Fall das um eine Karte gestochen wir und die Punktekarten gemeinsam über
    // 17 kommen, möchte ich meine noch höchste verblibene Karte spielen
    //geben die erste PunkteKarte und die folgende Karte gemeinsam unter 4 Punkte,
    // reicht es meine noch verbliebene niedrigste Karte zu spielen
    // was dazwischen liegt ist mir egal, deshalb random
    private int kartenStechen(int naechsteKarte) {
        if (letzteKarte == letzterZug()) {
            if (letztePunkteKarte >= 17) {
                return hoechsteKarte();
            }
            if (letztePunkteKarte < 4) {
                return niedrigsteKarte();
            }
            if (letztePunkteKarte > 4 || letztePunkteKarte < 17)
                ;
            {
                return randomS();
            }
        }
        return naechsteKarte;
    }

}