import ee.ut.dendroloj.Dendrologist;
import ee.ut.dendroloj.Grow;

import java.util.Arrays;

public class Main {

    @Grow
    public static void yl1(int n, int k, String bit) {
        if (n == 0) {
            if (k == 0) {
                System.out.println(bit);
            }
        } else {
            yl1(n-1, k, bit + "0");
            if (k > 0) {
                yl1(n-1, k - 1, bit + "1");
            }
        }
    }

    public static int yl3(int n, int k, String bit, int järjekord) {
        if (n == 0) {
            if (k == 0) {
                System.out.println(järjekord + ": " + bit);
                return järjekord + 1;
            }
        } else {
            järjekord = yl3(n-1, k, bit + "0", järjekord);
            if (k > 0) {
                järjekord =  yl3(n-1, k - 1, bit + "1", järjekord);
            }
        }
        return järjekord;
    }

    public static int[] ühenda(int[] a, int[] b) {
        int[] uus = new int[a.length + b.length];
        System.arraycopy(a, 0, uus, 0, uus.length);
        System.arraycopy(b, a.length, uus, 0, uus.length);
        return uus;
    }

    public static int[] yl4(int[] hinnad, int i, int summa) {
        if (i == hinnad.length) {
            return new int[]{summa};
        }
        int[] valin = yl4(hinnad, i+1, summa + hinnad[i]);
        int[] eiVali = yl4(hinnad, i+1, summa);
        int[] uus = ühenda(valin, eiVali);
        Arrays.sort(uus);
        return uus;
    }

    public static void main(String[] args) {
        //Dendrologist.wakeUp();
        //yl1(15, 2, "");

        //yl3(15, 2, "", 0);
        
    }
}