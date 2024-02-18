import java.util.*;

public class yl3 {
    public static void kõigeRohkemAineid(Tipp a) {
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
            map.putIfAbsent(praegune.x, new ArrayList<>());
            map.get(praegune.x).add(praegune);
            if (map.get(max).size() < map.get(praegune.x).size()) {
                max = praegune.x;
            }
        }
    }
}
