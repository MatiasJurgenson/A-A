import ee.ut.dendroloj.Dendrologist;

public class yl5 {
    static void kuvaKahendotsimispuu(KOTipp juurTipp) {
        Dendrologist.drawBinaryTree(juurTipp, t -> Integer.toString(t.väärtus), t -> t.v, t -> t.p);
    }

    static KOTipp gene(int h) {
        if (h == 0) return  null;
        if (h == 1) return new KOTipp(0);
        KOTipp juur = new KOTipp(0);
        juur.v = gene(h - (Math.random() > 0.5 ? 1 : 2));
        juur.p = gene(h - (Math.random() > 0.5 ? 1 : 2));

        return juur;
    }

    public static void main(String[] args) {
        KOTipp juur = gene(5);
        kuvaKahendotsimispuu(juur);
        juur = Main.paremPööre(juur);
        kuvaKahendotsimispuu(juur);

    }
}
