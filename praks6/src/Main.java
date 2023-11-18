import java.util.*;

public class Main {

    public static void bitidRec(int[] tee, int i) {
        if (i == tee.length) System.out.println(Arrays.toString(tee));
        else {
            tee[i] = 0;
            bitidRec(tee, i+1);
            tee[i] = 1;
            bitidRec(tee, i+1);
        }
    }

    public static void bitidMag(int n) {
        int[] vektor = new int[n];
        Deque<Integer> magasin = new ArrayDeque<>();
        magasin.push(-1);
        magasin.push(1);
        magasin.push(0);

        int i = 0;
        while (!magasin.isEmpty()) {
            //System.out.println("--" + Arrays.toString(vektor));
            int number = magasin.pop();
            if (number == -1) {
                i--;
            } else {
                vektor[i] = number;
                if (i == vektor.length - 1) {
                    System.out.println(Arrays.toString(vektor));
                } else {
                    i++;
                    magasin.push(-1);
                    magasin.push(1);
                    magasin.push(0);
                }
            }
        }
    }

    public static void yl5(String[] järjend) {
        Queue<Queue<String>> järjekorrad = new ArrayDeque<>();
        for (int i = 0; i < 4; i++) {
            Queue<String> järjekord = new ArrayDeque<>();
            for (int j = i * 9; j < i * 9+ 9; j++) {
                järjekord.add(järjend[j]);
            }
            järjekorrad.add(järjekord);
        }
        for (int i = 0; i < 36; i++) {
            järjend[i] = järjekorrad.peek().poll();
            järjekorrad.add(järjekorrad.poll());
        }
    }

    

    public static void main(String[] args) {
        bitidRec(new int[]{1,2,3}, 0 );
        bitidMag(3);
    }
}



