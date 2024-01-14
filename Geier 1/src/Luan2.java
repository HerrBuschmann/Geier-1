import java.util.ArrayList;
import java.util.Random;

/**
 * Die Klasse Luan2 erweitert HolsDerGeierSpieler und implementiert die Spiellogik
 * für den Bot "Luan2" im Spiel "Hols der Geier".
 */
public class Luan2 extends HolsDerGeierSpieler {
    private ArrayList<Integer> winnable = new ArrayList<Integer>();
    private ArrayList<Integer> enemyCards = new ArrayList<Integer>();
    private ArrayList<Integer> myCards = new ArrayList<Integer>();
    private int[] lastCards = new int[]{0, 0, 0};
    private int[] playerPoints = new int[]{0, 0};
    private int stackedPoints = 0;

    /**
     * Setzt den Spielzustand zurück, initialisiert Karten und Punkte.
     */
    public void reset() {
        winnable.clear();
        // Initialisiere gewinnbare Karten von -5 bis 10
        // Die 0 wird in den verfahren ignoriert
        for (int i = 10; i > -6; i--)
            winnable.add(i);

        enemyCards.clear();
        // Initialisiere Karten des Gegners von 1 bis 15
        for (int i = 15; i > 0; i--)
            enemyCards.add(i);

        myCards.clear();
        // Initialisiere Spielerkarten von 1 bis 15
        for (int i = 15; i > 0; i--)
            myCards.add(i);

        // Setze andere Variablen zurück
        lastCards = new int[]{0, 0, 0};
        playerPoints = new int[]{0, 0};
        stackedPoints = 0;
    }

    /**
     * Entscheidet, welche Karte in der aktuellen Runde gespielt wird.
     *
     * @param naechsteKarte Die Punktekart, um welches gespielt wird.
     * @return Die gewählte Karte.
     */
    public int gibKarte(int naechsteKarte) {
        int ret = -99; // Standardrückgabewert

        // Protokolliere den letzten Zug des Gegners
        lastCards[1] = letzterZug();
        if (lastCards[1] != -99) {
            enemyCards.remove(enemyCards.indexOf(lastCards[1]));
        }
        winnable.remove(winnable.indexOf(naechsteKarte));

        // Handhabung von Punktstapeln bei gleichen Karten
        if (lastCards[0] == lastCards[1]) {
            stackedPoints = lastCards[2];
        } else {
            int[] points = this.addPoints(lastCards[0], lastCards[1], lastCards[2] + stackedPoints);
            playerPoints[points[0]] += points[1];
            this.stackedPoints = 0;
        }

        // Falls der Gegner wahrscheinlich seine höchste Karte spielt, spiele die gleiche/höhere Karte
        if (myCards.size() > 1 && naechsteKarte + stackedPoints >= winnable.get(0) && myCards.get(1) >= enemyCards.get(0)) {
            ret = highestSameCard();
        }
        // Falls der Bot Priorität hat und es die beste Punktekarte ist, spiele die besten Karte
        else if (naechsteKarte + stackedPoints >= winnable.get(0) && higherCards() && havePrio()) {
            ret = highestSameCard();
        }
        // Falls das Spielen der besten Karte notwendig ist, um nicht zu verlieren
        else if (!isGameWinnable(naechsteKarte + stackedPoints)) {
            ret = myCards.get(0);
        }
        // Falls der Gegner Priorität hat und es die beste Punktekarte ist, spiele die schlechteste Karte
        else if (naechsteKarte + stackedPoints >= winnable.get(0) && higherCards() && !havePrio()) {
            ret = myCards.get(myCards.size() - 1);
        }
        // Für negative Zahlen
        else if (naechsteKarte + stackedPoints < 0) {
            if ((naechsteKarte * -1) != 0 && myCards.contains((Integer) naechsteKarte * -1))
                ret = naechsteKarte * -1;
            else
                ret = myCards.get(myCards.size() - 1);
        }
        // Für andere Zahlen
        else {
            if ((naechsteKarte + 5) != 0 && myCards.contains((Integer) naechsteKarte + 5))
                ret = naechsteKarte + 5;
            else
                ret = myCards.get((new Random()).nextInt(myCards.size()));
        }

        // Aktualisiere lastCards, entferne die gespielte Karte aus der Hand des Spielers und gib die gewählte Karte zurück
        lastCards[0] = ret;
        lastCards[2] = naechsteKarte;
        myCards.remove(myCards.indexOf(ret));
        return ret;
    }

    /**
     * Prüft, ob die höchste Spielerkarten höhere Werte als die des Gegners haben.
     *
     * @return true, wenn Spieler höhere Karten hat, sonst false.
     */
    private boolean higherCards() {
        return (myCards.get(0) >= enemyCards.get(0));
    }

    /**
     * Prüft, ob der Spieler Priorität über dem Gegner hat.
     *
     * @return true, wenn Spieler Priorität hat, sonst false.
     */
    private boolean havePrio() {
        boolean ret;
        int cnt = 0;
        while (cnt < myCards.size() - 1 && myCards.get(cnt) == enemyCards.get(cnt)) {
            cnt++;
        }
        return (myCards.get(cnt) > enemyCards.get(cnt));
    }

    /**
     * Findet die kleinste Karte in der Hand des Spielers, die größer oder gleich der größten Karte des Gegners ist.
     *
     * @return Die gewählte Karte.
     */
    private int highestSameCard() {
        int ret = 0;
        for (int card : myCards) {
            if (card >= enemyCards.get(0))
                ret = card;
        }
        return ret;
    }

    /**
     * Prüft, ob das Spiel basierend auf Punkten noch gewonnen werden kann.
     *
     * @param point Der Punktstand für die aktuelle Runde.
     * @return true, wenn das Spiel noch nach Verlieren der runde gewonnen werden kann, sonst false.
     */
    private boolean isGameWinnable(int point) {
        return (playerPoints[1] + ((point < 0) ? point * -1 : point) < playerPoints[0] + pointsRemaining());
    }

    /**
     * Berechnet die verbleibenden Punkte im Spiel.
     *
     * @return Die verbleibenden Punkte im Spiel.
     */
    private int pointsRemaining() {
        int ret = 0;
        for (int i : winnable) {
            if (i < 0)
                ret -= i;
            else
                ret += i;
        }
        return ret;
    }

    /**
     * Berechnet die Punkte, die basierend auf den Kartenwerten und Punkten hinzugefügt werden.
     *
     * @param c1 Die eigene Karte.
     * @param c0 Die Karte des Gegners.
     * @param p  Die Punkte der aktuellen Runde.
     * @return Ein Array, das die Punkteverteilung (0 = Spieler, 1 = Punkte) enthält.
     */
    private int[] addPoints(int c1, int c0, int p) {
        int[] ret = new int[]{0, p};

        if (p < 0) {
            if (c0 < c1) {
                ret[0] = 1;
            }
        } else {
            if (c0 > c1) {
                ret[0] = 1;
            }
        }
        if (c1 == c0) ret[1] = 0;
        return ret;
    }
}