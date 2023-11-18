public class A {
    public static void main(String[] args) {
        tänavad(6);
    }

    public static void tänavad(int n) {
        tänavadAbi(n, "K", 0);
    }

    public static void tänavadAbi(int n, String s, int i) {
        if (s.length() == n) {
            System.out.println(s);
            return;
        }
        if (i < 3) {
            tänavadAbi(n, s+"E", i+1);
        } else {
            i=0;
        }
        tänavadAbi(n, s+"K", i);
    }
}
