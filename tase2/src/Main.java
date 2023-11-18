import ee.ut.dendroloj.Dendrologist;

import java.util.concurrent.ThreadLocalRandom;

public class Main {
    static Tipp juhuslikPuu(int n) {
        if (n == 0) return null;
        ThreadLocalRandom juhus = ThreadLocalRandom.current();
        //String juhuslikTäht = Character.toString(juhus.nextInt('A', 'Z' + 1));
        int juhuslikNum = juhus.nextInt(0, 5);
        int vasakule = juhus.nextInt(n);
        return new Tipp(juhuslikNum, juhuslikPuu(vasakule), juhuslikPuu(n - 1 - vasakule));
    }

    static void kuvaKahendpuu(Tipp juurTipp) {
        Dendrologist.drawBinaryTree(juurTipp, t -> t.info + " x=" + t.abi, t -> t.v, t -> t.p);
    }

    public static boolean yl1(Tipp juur) {
        if (juur == null) return false;
        boolean v1 = yl1(juur.v);
        boolean v2 = yl1(juur.p);

        int vasaktippe = (juur.v != null ? juur.v.abi : 0);
        int paremtippe = (juur.p != null ? juur.p.abi : 0);

        juur.abi = 1 + vasaktippe + paremtippe;

        return v1 || v2 || vasaktippe > paremtippe;
    }

    public static int yl2(Tipp juur) {
        if (juur == null) return 0;

        int v1 = yl2(juur.v);
        int v2 = yl2(juur.p);

        int leidub = 0;

        return 0;
    }
    public static void main(String[] args) {
        Tipp UwU = juhuslikPuu(10);
        kuvaKahendpuu(UwU);
        Tipp tipp = new Tipp(0, null, new Tipp(0));
        System.out.println(yl1(UwU));
        System.out.println(yl1(tipp));
    }
}