import ee.ut.dendroloj.Dendrologist;

import java.util.concurrent.ThreadLocalRandom;

public class yl1 {
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

    static int tippe(Tipp juur) {
        if (juur == null) return 0;
        return 1+ tippe(juur.v) + tippe(juur.p);
    }

    static void tippeX(Tipp juur) {
        if (juur == null) return;
        tippeX(juur.v);
        tippeX(juur.p);
        juur.x = 1;
        if (juur.v != null) juur.x += juur.v.x;
        if (juur.p != null) juur.x += juur.p.x;

    }

    static int kõrgused(Tipp juur) {
        if (juur == null) return 0;
        int kõrgus = 1 + Math.max(kõrgused(juur.v), kõrgused(juur.p));
        juur.x = kõrgus;
        return kõrgus;
    }

    static void info(Tipp juur) {
        if (juur == null) return;
        info(juur.v);
        System.out.print(juur.info);
        info(juur.p);
    }

    static void lehed(Tipp juur) {
        if (juur == null) return;
        if (juur.v == null && juur.p == null) {
            System.out.print(juur.info);
            return;
        }
        lehed(juur.v);
        lehed(juur.p);
    }

    public static void main(String[] args) {
        Tipp tipp = juhuslikPuu(10);

        tippeX(tipp);

        kuvaKahendpuu(tipp);
        //System.out.println(tippe(tipp));
        //System.out.println(kõrgus(tipp));
        //info(tipp);
        //lehed(tipp);

    }
}
