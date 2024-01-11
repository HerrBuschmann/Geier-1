import java.util.ArrayList;

public class LuansGeier extends HolsDerGeierSpieler{
    private ArrayList<Integer> winnable =new ArrayList<Integer>();
    private ArrayList<Integer> enemyCards =new ArrayList<Integer>();
    private ArrayList<Integer> myCards=new ArrayList<Integer>();

    public void reset() {
        winnable.clear();
        for (int i=10;i>-6;i--)
            winnable.add(i);
        enemyCards.clear();
        for (int i=15;i>0;i--)
            enemyCards.add(i);
        for (int i=15;i>0;i--)
            myCards.add(i);
    }

    public int gibKarte(int naechsteKarte) {

        int ret=-99;
        int letzteKarteGegner=letzterZug();
        if (letzteKarteGegner!=-99)
            enemyCards.remove(enemyCards.indexOf(letzteKarteGegner));
        // L�sche dieser Karte

        int bestVal = 0;
        winnable.remove(winnable.indexOf(naechsteKarte));

        for (int j = 0; j < myCards.size(); j++) {
            calcWin(enemyCards,myCards,winnable,ArrayList<Integer> = {j,}


        }
        calcWin(enemyCards,myCards,winnable,ArrayList)



        myCards.remove(myCards.indexOf(ret));
        return ret;
    }



    private int calcWin(ArrayList<Integer> enemyCards, ArrayList<Integer> myCards, ArrayList<Integer> winnable, ArrayList<Integer> values){
        int ret = 0;
        ArrayList<Integer> eCards = enemyCards, mCards = myCards, winn = winnable;
        eCards.remove(eCards.indexOf(values.get(2)));
        for (int i; myCards.size()<=i;i++ ){
            for(int j ; enemyCards.size()<=j;j++){
                for(int l ; winnable.size() <=l ; l++){
                    ret += calcWin()
                }
            }
        }
        return ret + calcWin();
    }
}
