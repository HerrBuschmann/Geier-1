import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {

    HolsDerGeier g = new HolsDerGeier();
    g.neueSpieler(new Klara(), new Luan2());

    for (int i= 0; i <= 20; i++){
        g.ganzesSpiel();
        g.naechstesSpiel();
    }
    }
}