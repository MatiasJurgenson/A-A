public class Main {

    public static int yl7(int n) {
        if (n == 0) {
            return 0;
        }
        return 1 + 2*yl7(n-1);
    }
    public static void main(String[] args) {
        System.out.println(yl7(3));
    }
}