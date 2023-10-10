public class Main {

    public static int yl1(int n) {
        if  (n == 0) {
            return 1;
        }
        return 1 + yl1(n/2);
    }

    public static int yl2(int[] a, int n, int summa) {
        if (n >= a.length ) {
            if (summa <= 50) {
                System.out.println(summa);
                return 1;
            } else {
                return 0;
            }

        }

        if (summa > 50) {
            return 0;
        }

        return yl2(a, n + 1, summa) + yl2(a, n + 2, summa + a[n]);

    }
    public static void main(String[] args) {
        System.out.println(yl1(4));

        int[] a = {25, 25, 30, 20};

        System.out.println(yl2(a, 0, 0));
    }
}