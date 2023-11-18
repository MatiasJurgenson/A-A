import ee.ut.dendroloj.Dendrologist;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

public class yl3 {

    static void kuvaKahendpuu(Tipp juurTipp) {
        Dendrologist.drawBinaryTree(juurTipp, t -> t.info + " x=" + t.x, t -> t.v, t -> t.p);
    }

    static Tipp juhuslikPuu(int n) {
        if (n == 0) return null;
        ThreadLocalRandom juhus = ThreadLocalRandom.current();
        String juhuslikTäht = Character.toString(juhus.nextInt('A', 'Z' + 1));
        int vasakule = juhus.nextInt(n);
        return new Tipp(juhuslikTäht, juhuslikPuu(vasakule), juhuslikPuu(n - 1 - vasakule));
    }

    static void väljasta(Tipp juur, int sügavus) {

    }

    static void väljastaIlus(Tipp juur, int sügavus) {
        Queue<Tipp> praegune = new ArrayDeque<>();
        Queue<Boolean> vasak = new ArrayDeque<>();

        praegune.add(juur);

        Tipp reavahetus = juur.v;

        while (!praegune.isEmpty()) {
            Tipp t = praegune.poll();
            boolean vasakule = vasak.poll();

            if (t == reavahetus) {
                System.out.println();
                if (t.v != null) reavahetus = t.v;
                else if (t.p != null) reavahetus = t.p;
            }

            int vasakul = yl1.tippe(t.v) + (vasakule ? 1 : 0);;
            int paremal = yl1.tippe(t.p) + (vasakule ? 0 : 1);
            System.out.print("-".repeat(vasakul) + t.info + "-".repeat(paremal));
            if (t.v != null) praegune.add(t.v);
            if (t.p != null) praegune.add(t.p);
        }

    }

    public static void main(String[] args) {
        Tipp tipp = juhuslikPuu(10);
    }
}
