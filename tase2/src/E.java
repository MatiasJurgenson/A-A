public class E {
    public static boolean yl1 (Tipp juur) {
        if (juur == null) return false;
        boolean kasVasakul = yl1(juur.v);
        boolean kasParemal = yl1(juur.p);

        int tippeVasakul = juur.v != null ? juur.v.abi : 0;
        int tippeParemal = juur.p != null ? juur.p.abi : 0;

        juur.abi = 1 + tippeVasakul + tippeParemal;

        return kasVasakul || kasParemal || tippeParemal == 3;

    }

    public static int yl2(Tipp juur) {
        if (juur == null) return 0;

        int leidub = 0;
        if (juur.v != null && juur.p != null && juur.v.info * juur.p.info % 2 == 1) leidub = 1;

        return yl2(juur.v) + yl2(juur.p) + leidub;
    }
    public static void main(String[] args) {
        Tipp tipp = Main.juhuslikPuu(10);
        System.out.println(yl1(tipp));
        System.out.println(yl2(tipp));
        Main.kuvaKahendpuu(tipp);
    }
}
