import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {

    public static void kolmseitse(int[] a, int i, int[] b) {
        if (i == a.length) {
            System.out.println(Arrays.toString(b));
        }

        kolmseitse(a, i+1, b);
        //kolmseitse(a, i+1, b+a[i]);

    }
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}