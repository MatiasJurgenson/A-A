import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) {
        Deque<Integer> mag = new ArrayDeque<>();
        System.out.println(mag.isEmpty());
        // true
        mag.push(3);
        mag.push(4);
        mag.push(5);
        System.out.println(mag.isEmpty());
        // false
        System.out.println(mag.toString());
        // [5, 4, 3]
        mag.push(6);
        System.out.println(mag.toString());
        // [6, 5, 4, 3]
        int e = (int) mag.pop();
        System.out.println(Integer.toString(e));
        // 6
        System.out.println(mag.toString());
        // [5, 4, 3]
        mag.pop();
        System.out.println(mag.toString());
        // [4, 3]
    }
}