import java.util.*;

public class yl2 {
    public static List<Tipp> LeiaKaugeimad(Tipp a) {
        Queue<Tipp> tippudeJärjekord = new ArrayDeque<>();
        a.x = 0;
        tippudeJärjekord.add(a);

        //kauhusele vatsav map
        Map<Integer, List<Tipp>> map = new HashMap<>();

        int max = 0;
        while (!tippudeJärjekord.isEmpty()) {
            Tipp praegune = tippudeJärjekord.remove();
            for (Kaar kaar : praegune.kaared) {
                Tipp tipp = kaar.lõpp;
                if (tipp.x == 0) {
                    tipp.x = praegune.x + 1;
                    tippudeJärjekord.add(tipp);
                }
            }
            max = Math.max(max, praegune.x);
            map.putIfAbsent(praegune.x, new ArrayList<>());
            map.get(praegune.x).add(praegune);
        }

        List<Tipp> tagastus = new ArrayList<>();

        tippudeJärjekord.add(a);
        while (!tippudeJärjekord.isEmpty()) {
            Tipp praegune = tippudeJärjekord.remove();
            for (Kaar kaar : praegune.kaared) {
                tippudeJärjekord.add(kaar.lõpp);
            }
            if (praegune.x == max && !tagastus.contains(praegune)) {
                tagastus.add(praegune);
            }
        }
        return tagastus;
    }

    public static void prindiTee(List<Tipp> kaugused) {
        for (Tipp tipp: kaugused) {
            StringBuilder sb = new StringBuilder();
            Tipp abi = tipp;
            while (abi != null) {

            }
        }
    }
}
