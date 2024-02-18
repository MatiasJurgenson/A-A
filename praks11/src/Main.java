import ee.ut.dendroloj.Dendrologist;

public class Main {
    static void kuvaKahendpuu(Tipp juurTipp) {
        Dendrologist.drawBinaryTree(juurTipp, t -> t.info + " x=" + t.abi, t -> t.v, t -> t.p);
    }
}