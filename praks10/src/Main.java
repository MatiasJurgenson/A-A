import ee.ut.dendroloj.Dendrologist;

public class Main {
    static void kuvaKahendotsimispuu(KOTipp juurTipp) {
        Dendrologist.drawBinaryTree(juurTipp, t -> Integer.toString(t.väärtus), t -> t.v, t -> t.p);
    }

    public static KOTipp paremPööre(KOTipp x) {
        KOTipp y;

        y = x.v;
        x.v = y.p;
        y.p = x;

        return y;

    }
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}