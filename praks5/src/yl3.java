import java.util.ArrayDeque;
import java.util.Deque;

public class yl3 {
    public static void swap(int[] arr, int i, int j) {

    }

    public static int partitsioon(int[] arr, int low, int high) {
        return 0;
    }

    public static void kiirMag(int[] arr) {
        Deque<Integer> magasin = new ArrayDeque<>();
        magasin.push(0);
        magasin.push(arr.length-1);

        while (!magasin.isEmpty()) {
            int high = magasin.pop();
            int low = magasin.pop();

            if (low < high) {
                int indeks = partitsioon(arr, low, high);
                magasin.push(low);
                magasin.push(indeks - 1);
            }
        }
    }
    public static void main(String[] args) {

    }
}
