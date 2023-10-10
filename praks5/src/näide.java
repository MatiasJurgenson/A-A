import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class näide {
    public static void main(String[] args) {
        Queue<String> jarjekord = new ArrayDeque<>(List.of("Eric", "John", "Mike"));
        System.out.println(jarjekord.isEmpty());
        // false
        jarjekord.add("Terry");
        String s = jarjekord.remove();
        System.out.println(s);
        // Eric
        System.out.println(jarjekord.remove());
        // John
        System.out.println(jarjekord.toString());
        // [Mike, Terry]
        jarjekord.remove();
        jarjekord.remove();
        System.out.println(jarjekord.isEmpty());
        // true
        System.out.println(jarjekord.toString());
        // []
    }
}
