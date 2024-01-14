import java.util.ArrayList;
import java.util.Random;

public class rand5 extends HolsDerGeierSpieler {
    private ArrayList<Integer> myCards=new ArrayList<Integer>();
    @Override
    public void reset() {
        for (int i=15;i>0;i--)
            myCards.add(i);
    }

    @Override
    public int gibKarte(int naechsteKarte) {
        int myCard = -99;
        if((naechsteKarte + 5) != 0 && myCards.contains((Integer) naechsteKarte + 5))
            myCard = naechsteKarte + 5;
        else
            myCard = myCards.get((new Random()).nextInt(myCards.size()));

        myCards.remove(myCards.indexOf(myCard));
        return myCard;
    }
}
