class Fibo {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        /*System.out.println(fibo(2));
        System.out.println(fibo(3));
        System.out.println(fibo(4));
        System.out.println(fibo(5));
        System.out.println(fibo(6));
        System.out.println(fibo(7));*/

        System.out.println(fibo(41));
        //a) 41 - 1046 ms

        long stop = System.currentTimeMillis();
        System.out.println("Aega kulus " + (stop - start) + " milliskundit");

        start = System.currentTimeMillis();

        /*System.out.println(fiboTava(2));
        System.out.println(fiboTava(3));
        System.out.println(fiboTava(4));
        System.out.println(fiboTava(5));
        System.out.println(fiboTava(6));
        System.out.println(fiboTava(7));*/

        System.out.println(fiboTava(41));
        //b) 0 ms

        stop = System.currentTimeMillis();
        System.out.println("Aega kulus " + (stop - start) + " milliskundit");

        start = System.currentTimeMillis();

        System.out.println(fiboTava(1000000));
        //c) asi läheb katki :(

        stop = System.currentTimeMillis();
        System.out.println("Aega kulus " + (stop - start) + " milliskundit");
    }
    public static int fibo(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fibo(n - 1) + fibo(n - 2);
    }

    public static long fiboTava (long n) {
        if (n < 2) {
            return n;
        }
        long a = 0;
        long b = 1;
        long c = 1;

        for (long i = 0; i < n - 1; i++) {

            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
}