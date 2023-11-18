import java.util.LinkedList;

public class Kimbumeetod {
    public LinkedList<Isik>[] isikud;

    public Kimbumeetod(int n) {
        isikud = new LinkedList[n];
    }

    public int hash(int id) {
        double t = (Math.sqrt(5) - 1) / 2;
        return (int) (isikud.length + (id*t) - (int)((id*t)));
    }

    public void lisa(Isik isik) {
        int h = hash(isik.ID);
        if (isikud[h] == null) {
            isikud[h] = new LinkedList<Isik>();
        }
        isikud[h].addLast(isik);
    }

    public Isik otsi(int id) {
        int h = hash(id);
        if (isikud[h] == null) {
            return null;
        }
        for (Isik isik : isikud[h]) {
            if (isik.ID == id) {
                return isik;
            }
        }
        return null;
    }

    public Isik eemalda(int id) {
        int h = hash(id);
        if (isikud[h] == null) {
            return null;
        }
        for (Isik isik : isikud[h]) {
            if (isik.ID == id) {
                isikud[h].remove(isik);
                return isik;
            }
        }
        return null;
    }
}
