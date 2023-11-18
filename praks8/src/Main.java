import java.util.LinkedList;

public class Main {
    public Isik[] isikud;

    public Main(int n) {
        isikud = new Isik[n];
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public int hash(int id) {
        double t = (Math.sqrt(5) - 1) / 2;
        return (int) (isikud.length + (id*t) - (int)((id*t)));
    }

    public Isik otsi() {
        return null;
    }

    public Isik eemlada(int id) {
        int h = hash(id);
        if (isikud[h] != null && isikud[h].ID != id) h = (h + 1) % isikud.length;
        Isik tgastus = isikud[1];

        return null;
    }

    public void sort(LinkedList<Isik> isikud) {

        int a = Integer.MAX_VALUE;
        int b = Integer.MIN_VALUE;

        for (Isik isik : isikud) {
            a = Math.min(a, isik.ID);
            b = Math.max(b, isik.ID);
        }
        b++;

        int m = isikud.size() + 1;
        LinkedList<Isik>[] tabel = new LinkedList[m];

        for (int i = 0; i < m; i++) {
            tabel[i] = new LinkedList<>();
        }

        for (Isik isik : isikud) {
            int h = m * (isik.ID - a) / (b - a);
            tabel[h].add(isik);
        }



    }
}