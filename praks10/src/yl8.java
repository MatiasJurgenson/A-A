public class yl8 {
    public static KOTipp otsi(KOTipp juur, int otsitav) {
        if (juur == null) return null;
        if (juur.väärtus == otsitav) return juur;

        if (juur.väärtus > otsitav) return otsi(juur.v, otsitav);
        else return otsi(juur.p, otsitav);
    }

    public static KOTipp lisa(KOTipp juur, int arv) {
        if (juur == null) return new KOTipp(arv);

        if (juur.väärtus > arv) {
            juur.v = lisa(juur.v, arv);
        } else {
            juur.p = lisa(juur.p, arv);
        }

        return juur;
    }

    public static KOTipp eemalda(KOTipp juur, int arv) {
        if (juur == null) return null;
        if (juur.väärtus == arv) {
            if (juur.v == null && juur.p == null) return null;
            if (juur.v != null && juur.p != null) {
                KOTipp järgmine = juur.p;
                while (järgmine.v != null) järgmine = järgmine.v;
                juur.väärtus = järgmine.väärtus;
                juur.p = eemalda(juur.p, juur.väärtus);
                return juur;
            }
            if (juur.v != null) return juur.v;
            if (juur.p != null) return juur.p;

        } else if (arv < juur.väärtus) {
            juur.v = eemalda(juur.v, arv);
        } else {
            juur.p = eemalda(juur.p, arv);
        }
        return juur;
    }
}
