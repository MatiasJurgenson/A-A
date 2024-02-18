import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class yl7 {

    public static void main(String[] args) throws Exception{
        Praktikum12.NimedegaNaabrusmaatriks m = Praktikum12.loeNaabrusmaatriks(new File("linnade_kaugused.tsv"));
        int [][] kaugused = m.M;
        String[] nimed = m.nimed;

        Praktikum12.kuvaGraaf(kaugused, nimed);
        List<Tipp> tipud = teisendaKülg(kaugused, nimed);
        Praktikum12.kuvaGraaf(tipud);


    }

    public static List<Tipp> teisendaKülg(int[][] kaugused, String[] nimed) {
        List<Tipp> tipud = new ArrayList<>();

        for (String s : nimed) tipud.add(new Tipp(s));

        for (int i = 0; i < kaugused.length; i++) {
            for (int j = 0; j < kaugused.length; j++) {
                if (kaugused[i][j] == -1 || kaugused[i][j] == 0) continue;
                tipud.get(j).kaared.add(new Kaar(tipud.get(i), tipud.get(j), kaugused[i][j]));
            }
        }

        return tipud;
    }

    public static Praktikum12.NimedegaNaabrusmaatriks teisendaNaaber(List<Tipp> tipud) {
        String[] nimed = new String[tipud.size()];
        int[][] kaalud = new int[tipud.size()][tipud.size()];

        for (int i = 0; i < tipud.size(); i++) {
            nimed[i] = tipud.get(i).info;
            for (Kaar kaar : tipud.get(i).kaared) {
                kaalud[i][tipud.indexOf(kaar.lõpp)] = kaar.kaal;
            }
            for (int j = 0; j < kaalud[i].length; j++) {
                if (kaalud[i][j] == 0) kaalud[i][j] = -1;
            }
            kaalud[i][i] = 0;
        }


        return new Praktikum12.NimedegaNaabrusmaatriks(kaalud, nimed);
    }

}
