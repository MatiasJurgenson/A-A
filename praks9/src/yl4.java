import ee.ut.dendroloj.Dendrologist;

import java.util.concurrent.ThreadLocalRandom;

public class yl4 {
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

    static void peegelda(Tipp juur) {
        if (juur == null) return;
        peegelda(juur.v);
        Tipp uusParem = juur.v;
        peegelda(juur.p);
        Tipp uusVasak = juur.p;
        juur.v = uusVasak;
        juur.p = uusParem;
    }

    static Tipp kopeeriLehtedeta(Tipp juur) {
        if (juur == null) return null;
        if (juur.v == null && juur.p == null) return null;
        Tipp uus = new Tipp(juur.info);
        juur.v = kopeeriLehtedeta(juur.v);
        juur.p = kopeeriLehtedeta(juur.p);
        return uus;
    }

    static int võrdsedAlampuud(Tipp juur) {
        if (juur == null) return 0;
        if (juur.v == null && juur.p == null) return 1;

        int vasakul = yl1.tippe(juur.v);
        int paremal = yl1.tippe(juur.p);

        int v = võrdsedAlampuud(juur.v);
        int p = võrdsedAlampuud(juur.p);
        return v + p + (vasakul == paremal ? 1 : 0);
    }

    public static void main(String[] args) {

    }
}
