public class B {
    public static boolean yl1(Tipp juur) {
        if (juur == null) return false;
        boolean leidubVasakul = yl1(juur.v);
        boolean leidubParemal = yl1(juur.p);

        int tippeVasakul = juur.v != null ? juur.v.abi : 0;
        int tippeParemal = juur.p != null ? juur.p.abi : 0;

        juur.abi = 1 + tippeVasakul + tippeParemal;

        return leidubVasakul || leidubParemal || tippeVasakul > tippeParemal;
    }

    public static int yl2(Tipp juur) {
        if (juur == null) return 0;

        int vasakKordaja = (juur.v == null ? 1 : juur.v.info);
        int paremKordaja = (juur.p == null ? 1 : juur.p.info);

        int leidub = 0;
        if (vasakKordaja * paremKordaja == juur.info) {
            leidub = 1;
        }

        return yl2(juur.v) + yl2(juur.p) + leidub;

    }


    public static void main(String[] args) {
        Tipp tipp = Main.juhuslikPuu(10);
        Main.kuvaKahendpuu(tipp);
        System.out.println(yl1(tipp));
        System.out.println(yl2(tipp));
    }

}
