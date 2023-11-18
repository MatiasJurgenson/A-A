import ee.ut.dendroloj.Dendrologist;

public class test {

    static void kuvaKahendpuu(Tipp juurTipp) {
        Dendrologist.drawBinaryTree(juurTipp, t -> t.info + t.x, t -> t.v, t -> t.p);
    }

    static Tipp vasakpööre(Tipp x) {
        Tipp y = new Tipp("y");

        y.p = x.p.p;
        y.v = x;
        y.v.p = x.p.v;


        return  y;
    }
    public static void main(String[] args) {
        Tipp x = new Tipp("x", new Tipp("P1"), new Tipp("y", new Tipp("P2"), new Tipp("P3")));
        kuvaKahendpuu(x);
        x = vasakpööre(x);
        kuvaKahendpuu(x);




    }
}
