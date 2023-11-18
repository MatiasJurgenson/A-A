import ee.ut.dendroloj.Dendrologist;

import java.util.concurrent.ThreadLocalRandom;

public class yl2 {
    static void kuvaKahendpuu(Tipp juurTipp) {
        Dendrologist.drawBinaryTree(juurTipp, t -> t.info, t -> t.v, t -> t.p);
    }

    static Tipp juhuslikAvaldis(int n) {
        ThreadLocalRandom juhus = ThreadLocalRandom.current();
        if (n == 0) {
            return new Tipp(Integer.toString((juhus.nextBoolean() ? 1 : -1) * juhus.nextInt(1, 40)));
        }
        String tehe = switch (juhus.nextInt(3)) {
            case 0 -> "+";
            case 1 -> "-";
            case 2 -> "*";
            default -> throw new AssertionError("Võimatu juhuarv");
        };
        int vasakule = juhus.nextInt(n);
        return new Tipp(tehe, juhuslikAvaldis(vasakule), juhuslikAvaldis(n - 1 - vasakule));
    }

    static String sõnaesitus(Tipp juur) {
        // leht aer
        if (juur.v == null && juur.p == null) {
            return juur.info;
        }
        //sisetipp leht
        //String mall = "";
        String sõnastaV = sõnaesitus(juur.v);

        //if (sõnastaV.charAt(0) == '-' || Character.isDigit(sõnastaV.charAt(0))) mall += "%s";
        //else mall += "(%s)";

        //mall += " %s ";

        String sõnastaP = sõnaesitus(juur.p);

        //if (sõnastaP.charAt(0) == '-' || Character.isDigit(sõnastaP.charAt(0))) mall += "%s";
        //else mall = "(%s)";

        return String.format(("(%s) %s (%s)"), sõnastaV, juur.info, sõnastaP);
    }

    static int väärtusta(Tipp juur) {
        if (juur.v == null && juur.p == null) {
            return Integer.parseInt(juur.info);
        }
        int vasakult = väärtusta(juur.v);
        int paremalt = väärtusta(juur.p);
        switch (juur.info) {
            case "+" : return vasakult + paremalt;
            case "-" : return vasakult - paremalt;
            case "*" : return vasakult * paremalt;
        }
        return 0;

    }

    public static void main(String[] args) {
        Tipp tipp = juhuslikAvaldis(3);
        kuvaKahendpuu(tipp);
        //System.out.println(sõnaesitus(tipp));
        System.out.println(väärtusta(tipp));
    }

}
