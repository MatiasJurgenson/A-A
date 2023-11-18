public class C {
    public static boolean yl1(Tipp juur) {
        if (juur == null) return false;
        boolean leidubVasakul = yl1(juur.v);
        boolean leidubParemal = yl1(juur.p);

        int kõrgusVasakul = juur.v != null ? juur.v.abi : 0;
        int kõrgusParemal = juur.p != null ? juur.p.abi : 0;

        juur.abi = 1 + Math.max(kõrgusVasakul, kõrgusParemal);

        return  leidubVasakul || leidubParemal || (kõrgusVasakul-1 >= 3 && kõrgusParemal-1 >= 3);
    }

    public static int yl2 (Tipp juur) {
        if (juur == null) return 0;

        int leidub = 0;
        if (juur.p != null && juur.p.v == null) leidub = 1;

        return leidub + yl2(juur.v) + yl2(juur.p);
    }

    public static void main(String[] args) {
        Tipp tipp = Main.juhuslikPuu(10);
        System.out.println(yl1(tipp));
        System.out.println(yl2(tipp));
        Main.kuvaKahendpuu(tipp);
    }
}
