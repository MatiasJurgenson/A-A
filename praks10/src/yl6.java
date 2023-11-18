public class yl6 {

    static void paiguta(KOTipp juur, int i, int[] massiiv) {
        if (juur == null) return;
        int vasakulTippe = tippe(juur.v);
        juur.väärtus = massiiv[i + vasakulTippe];

        paiguta(juur.v, i, massiiv);
        paiguta(juur.p, i+1+vasakulTippe, massiiv);
    }

    public static void märgenda(KOTipp juur) {
        if (juur == null) return;
        märgenda(juur.v);
        märgenda(juur.p);
        if (juur.v != null) juur.x = juur.v.x + 1 + tippe(juur.v.p);

    }

    public static int tippe(KOTipp juur) {
        if (juur == null) return 0;
        return 1 + tippe(juur.v) + tippe(juur.p);
    }
}
