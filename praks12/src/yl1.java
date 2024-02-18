import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class yl1 {
    public static int leiaKaugus(Tipp a, Tipp b) {
        if (a == b) return 0;
        if (a.kaared.size() == 0) return -1;

        int tee = 0;

        for (Kaar kaar : a.kaared) {
            int kaugus = leiaKaugus(kaar.lõpp, b);
            if (kaugus != -1) tee = kaugus + 1;
        }
        return tee;
    }

    public static Tipp otsiTipp(List<Tipp> graaf, String info) {
        for (Tipp tipp : graaf) {
            if (tipp.info.equals(info)) return tipp;
        }
        return null;
    }

    public static int leiaKaugusMitteRek(Tipp a, Tipp b) {
        a.x = 0;
        Queue<Tipp> järjekord = new ArrayDeque<>();
        järjekord.add(a);

        while (!järjekord.isEmpty()) {
            Tipp praegune = järjekord.remove();
            for (Kaar kaar : praegune.kaared) {
                Tipp tipp = kaar.lõpp;
                if (tipp == b) return praegune.x + 1;
                if (tipp.x == 0) {
                    tipp.x = praegune.x + 1;
                    järjekord.add(tipp);
                }
            }
        }
        return -1;
    }
}
