import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

public class yl5 {
    public static void pikimAhel (List<Tipp> graaf) {
        for (Tipp tipp : graaf) {
            for (Kaar kaar : tipp.kaared) {
                kaar.lõpp.x += 1;
            }
        }

        Deque<Tipp> magasin = new ArrayDeque<>();
        for (Tipp tipp : graaf) {
            if (tipp.x == 0) {
                magasin.push(tipp);
            }
        }

        while (!magasin.isEmpty()) {
            Tipp praegune = magasin.pop();
            for (Kaar kaar : praegune.kaared) {
                Tipp lõpp = kaar.lõpp;
                lõpp.x--;
                int uuskaugus = praegune.x + 1;
                if (uuskaugus > lõpp.x) {

                }
            }
        }
    }
}
