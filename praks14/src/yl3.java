import ee.ut.dendroloj.Dendrologist;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class yl3 {
    public static void main(String[] args) throws Exception{
        Dendrologist.setUIScale(1.5);
        List<Tipp> tipud = Praktikum14.loeKülgnevusstruktuur(new File("puu1.tsv"));

        Praktikum14.kuvaGraaf(tipud);
    }

    public static void print(List<Tipp> tipud) {
        PriorityQueue<Tipp> q = new PriorityQueue<>();

        q.remove(tipud.get(0));
        tipud.get(0).x = 10;
        q.add(tipud.get(0));
    }

    public static List<Tipp> primm(List<Tipp> tipud) {
        List<Tipp> toes = new ArrayList<>();
        for (Tipp tipp : tipud) {
            toes.add(new Tipp(tipp.info));
        }

        PriorityQueue<Tipp> q = new PriorityQueue<>(Comparator.comparingInt(o -> o.x));
        Tipp alg = toes.get(0);
        alg.x = 0;

        q.add(alg);
        while (!q.isEmpty()) {
            Tipp preagune = q.remove();
            preagune.y = 2;

            for (Kaar kaar : preagune.kaared) {
                if (kaar.lõpp.y == 2) continue;
                if (kaar.lõpp.y == 1) {
                    if (kaar.kaal < kaar.lõpp.y) {
                        //Tipp lõpp = q.remove(kaar.lõpp);
                    }
                }
            }
        }

        return toes;
    }
}
