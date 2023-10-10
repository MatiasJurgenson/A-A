import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class yl2 {
    public static int fiboMag(int n) {
        Deque<Integer> magasin = new ArrayDeque<>();
        magasin.push(n);

        int summa = 0;
        while (!magasin.isEmpty()) {
            int praegune = magasin.pop();
            if (praegune < 2) summa += praegune;
            else {
                magasin.push(praegune-1);
                magasin.push(praegune-2);
            }
        }
        return summa;
    }

    public static int fiboJär(int n) {
        Queue<Integer> järjekord = new ArrayDeque<>();
        järjekord.add(n);

        int summa = 0;
        while (!järjekord.isEmpty()) {
            int praegune = järjekord.remove();
            if (praegune < 2) summa += praegune;
            else {
                järjekord.add(praegune-1);
                järjekord.add(praegune-2);
            }
        }
        return summa;
    }
    public static void main(String[] args) {
        System.out.println(fiboJär(6));
        System.out.println(fiboMag(6));
    }
}
