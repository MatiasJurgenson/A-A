import ee.ut.dendroloj.Dendrologist;

import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class yl1 {
    public static void main(String[] args) throws Exception{
        Dendrologist.setUIScale(1.5);
        List<Tipp> tipud = Praktikum14.loeKülgnevusstruktuur(new File("puu1.tsv"));
        loeKülgnevusSügavutiEesjärjestus(tipud);
        Praktikum14.kuvaGraaf(tipud);
    }

    public static void loeKülgnevusSügavutiEesjärjestus(List<Tipp> tipud) {
        Tipp esimene = tipud.get(0);
        Deque<Tipp> q = new ArrayDeque<>();
        q.add(esimene);

        while (!q.isEmpty()) {
            Tipp praegune = q.poll();
            praegune.x = 1; //töödeldud
            System.out.print(praegune.info);

            for (Kaar kaar : praegune.kaared) {
                if (kaar.lõpp.x == 1) continue;
                q.push(kaar.lõpp);
            }
        }

    }


}
