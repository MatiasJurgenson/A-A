import java.util.*;

public class yl4 {

    //sügavuti eesjärjestuses
    public static void LeiaTee(Tipp a) {
        Deque<Tipp> magasin = new ArrayDeque<>();
        List<Tipp> järjekord = new ArrayList<>();

        magasin.push(a);
        while (!magasin.isEmpty()) {
            Tipp praegune = magasin.pop();
            if(järjekord.contains(praegune)) continue;;
            System.out.println(praegune.info);
            for (Kaar kaar : praegune.kaared) {

            }
        }
    }

}
