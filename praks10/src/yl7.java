public class yl7 {

    public static KOTipp otsiVäikseim(KOTipp juur) {
        while (juur.v != null) {
            juur = juur.v;
        }
        return juur;
    }

}
