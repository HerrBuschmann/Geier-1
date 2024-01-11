public class Main {
    public static void main(String[] args) throws Exception {

        HolsDerGeier g = new HolsDerGeier();
        g.neueSpieler(new IntelligentererGeier(), new KillerBot());
        g.newGame();
    }
}