public class C {
    public static void main(String[] args) {
        paarisPaaritu(3);
    }

    public static void paarisPaaritu(int i) {
        paarisPaarituAbi(i, "[");
    }

    public static void paarisPaarituAbi(int i, String s) {
        if (i >= 100) {
            System.out.println(s + i + "]");
            return;
        }
        if (i % 2 == 0) {
            paarisPaarituAbi(i+11, s + i + ", ");
            paarisPaarituAbi(i*3, s + i + ", ");
        } else {
            paarisPaarituAbi(i*2, s + i + ", ");
        }
    }
}
