public class D {
    public static boolean yl1(Tipp juur) {
        if (juur == null) return false;
        if (juur.v == null && juur.p == null) {
            juur.abi = 1;
            return false;
        }

        boolean kasVasakul = yl1(juur.v);
        boolean kasParemal = yl1(juur.p);

        int lehtiVasakul = juur.v != null ? juur.v.abi : 0;
        int lehtiParemal = juur.p != null ? juur.p.abi : 0;

        juur.abi = lehtiVasakul + lehtiParemal;

        return kasParemal || kasVasakul || (lehtiVasakul + lehtiParemal == 4);
    }

    public static int yl2(Tipp juur) {
        if (juur == null) return 0;
        if (kasLeht(juur.v) && kasLeht(juur.p)) return 1;
        return yl2(juur.v) + yl2(juur.p);
    }

    public static boolean kasLeht(Tipp juur) {
        if (juur == null) return false;
        return juur.v == null && juur.p == null;
    }


    public static void main(String[] args) {
        Tipp tipp = Main.juhuslikPuu(10);
        System.out.println(yl1(tipp));
        System.out.println(yl2(tipp));
        Main.kuvaKahendpuu(tipp);
    }
}
