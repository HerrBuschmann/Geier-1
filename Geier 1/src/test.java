import java.util.ArrayList;

public class test {

    public static int test1(int[] i){
        i[0]++;
        return i[0];
    }

    ArrayList<Integer> lol = new ArrayList<>();

    public int getkarte(int gelegt){
        int ret = 99;

        if (gelegt == 15) {
            ret = lol.getLast();
        }


        return ret;
    }

}
