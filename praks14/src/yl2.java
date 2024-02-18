import ee.ut.dendroloj.Dendrologist;

import java.io.File;
import java.util.*;

public class yl2 {
    public static void main(String[] args) throws Exception{
        Dendrologist.setUIScale(1.5);
        List<Tipp> tipud = Praktikum14.loeKülgnevusstruktuur(new File("puu1.tsv"));
        System.out.println(leiaPikimAhel(tipud));
        Praktikum14.kuvaGraaf(tipud);
    }

    public static int leiaPikimAhel(List<Tipp> tipud) {
        Tipp A = suvalineLeht(tipud);
        if (A == null) return -1;

        Tipp B = laiutiX(A);
        Tipp C = laiutiY(B);


        return C.y;
    }

    public static Tipp suvalineLeht(List<Tipp> tipud) {
        for (Tipp tipp : tipud) {
            if (tipp.kaared.size() == 1) return tipp;
        }

        return null;
    }

    public static Tipp laiutiX(Tipp alg) {
        Queue<Tipp> q = new ArrayDeque();
        Tipp kaugeim = alg;

        q.add(alg);
        while (!q.isEmpty()) {
            Tipp praegune = q.poll();
            if (praegune.x > kaugeim.x) kaugeim = praegune;
            for (Kaar kaar : praegune.kaared) {
                if (kaar.lõpp.x > 0 || kaar.lõpp == alg) continue;
                kaar.lõpp.x = praegune.x + 1;
                q.add(kaar.lõpp);
            }
        }

        return kaugeim;
    }

    public static Tipp laiutiY(Tipp alg) {
        Queue<Tipp> q = new ArrayDeque();
        Tipp kaugeim = alg;

        q.add(alg);
        while (!q.isEmpty()) {
            Tipp praegune = q.poll();
            if (praegune.y > kaugeim.y) kaugeim = praegune;
            for (Kaar kaar : praegune.kaared) {
                if (kaar.lõpp.y > 0 || kaar.lõpp == alg) continue;
                kaar.lõpp.y = praegune.y + 1;
                q.add(kaar.lõpp);
            }
        }

        return kaugeim;
    }

}
