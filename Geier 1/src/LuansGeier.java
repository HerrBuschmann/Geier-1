import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;

public class LuansGeier extends HolsDerGeierSpieler{
    private ArrayList<Integer> winnable =new ArrayList<Integer>();
    private ArrayList<Integer> enemyCards =new ArrayList<Integer>();
    private ArrayList<Integer> myCards=new ArrayList<Integer>();
    private int[] lastCards = new int[] {0,0,0};
    private int[] playerPoints = new int[] {0, 0};
    private HashMap<Integer,Integer> probabilities = new HashMap<>();
    public void reset() {
        winnable.clear();
        for (int i=10;i>-6;i--)
            winnable.add(i);
        enemyCards.clear();
        for (int i=15;i>0;i--)
            enemyCards.add(i);
        for (int i=15;i>0;i--)
            myCards.add(i);
        winnable.remove(winnable.indexOf(0));
    }

    public int gibKarte(int naechsteKarte) {
        int ret=-99;

         lastCards[1]=letzterZug();
        if (lastCards[1]!=-99) {
            enemyCards.remove(enemyCards.indexOf(lastCards[1]));
            int[] points =this.addPoints(lastCards[0],lastCards[1], lastCards[2]);
            playerPoints[points[0]] += points[1];
        }
        // L�sche dieser Karte

        for (Integer card : myCards) {
            probabilities.put(card,0);
        }

        int bestVal= 0, temp;
        System.out.println(enemyCards.get(2));
        for (int j = 0; j < myCards.size(); j++) {
            for(int i =0; i < enemyCards.size();i++ ) {
                temp = calcWin(enemyCards, myCards, winnable, naechsteKarte, myCards.get(j), enemyCards.get(i), playerPoints[0],playerPoints[1],0);
                System.out.println("Card: " + myCards.get(i) + " value: " + temp);
                if (temp > bestVal){
                    ret = myCards.get(j);
                    bestVal = temp;
                }
            }
        }

        winnable.remove(winnable.indexOf(naechsteKarte));


        lastCards[0] = ret;
        lastCards[2] = naechsteKarte;
        myCards.remove(myCards.indexOf(ret));
        return ret;
    }



    private int calcWin(ArrayList<Integer> enemyCards, ArrayList<Integer> myCards, ArrayList<Integer> winnable, int played , int mycard, int ecard, int pointme,int pointen,int cnt){
        int ret = 0;
        ArrayList<Integer> eCards = new ArrayList<>(enemyCards), mCards = new ArrayList<>(myCards), winn = new ArrayList<>(winnable);
        int[] points =this.addPoints(mycard,ecard, played);
        if(points[0]== 0)  pointme += points[1];
        else pointen += points[1];
        mCards.remove(mCards.indexOf(mycard));
        winn.remove(winn.indexOf(played));
        eCards.remove(eCards.indexOf(ecard));
        System.out.println(cnt);
        if (!mCards.isEmpty() && !eCards.isEmpty()){
            for (int i = 0; mCards.size() > i ; i++ ) {
                for (int j = 0; eCards.size() > j; j++) {
                    for (int l = 0; winn.size() > l; l++) {
                         ret += calcWin(eCards,mCards,winn,winn.get(l),mCards.get(i), eCards.get(j),pointme,pointen,cnt + 1);
                    }
                }
            }
        }
        else {
            if(pointme>pointen) ret = 1;
            else if (pointme<pointen) ret = -1;
            //System.out.println("!");
        }
        return ret;
    }


    private int[] addPoints(int c0, int c1, int p){
        int[] ret = new int[]{0,p};

        if(p<0){

            if (c0 < c1) {
                ret[0] = 1;
            }
        }else{
            if (c0 > c1){
                ret[0] = 1;
            }
        }
        if(c1==c0)ret[1] = 0;
        return ret;
    }
}
