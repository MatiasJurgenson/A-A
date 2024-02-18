import java.util.ArrayList;
import java.util.List;

public class yl4 {

    /**
     * Leiab kahe punkti vahelise kauguse kilomeetrites, kasutades valemit siit:
     * https://en.wikipedia.org/wiki/Haversine_formula
     */
    static double kaugus(double laius1, double pikkus1, double laius2, double pikkus2) {
        double laius = Math.pow(Math.sin(Math.toRadians(laius2 - laius1) / 2), 2);
        double h = laius + (1 - laius - Math.pow(Math.sin(Math.toRadians(laius1 + laius2) / 2), 2)) * Math.pow(Math.sin(Math.toRadians(pikkus2 - pikkus1) / 2), 2);
        return 2 * 6371.0088 * Math.asin(Math.sqrt(h));
    }

    public static List<Tipp> teeKülgenvus(Praktikum14.NimedegaKoordinaadid k) {
        List<Tipp> tipud = new ArrayList<>();
        for (String s : k.nimed) {
            tipud.add(new Tipp(s));
        }

        for (int i = 0; i < k.K.length; i++) {
            for (int j = 0; j < k.K.length; j++) {
                Tipp esimene = tipud.get(i);
                Tipp teine = tipud.get(j);
                double kaugus = kaugus(k.K[i][0], k.K[i][1], k.K[j][0], k.K[j][1]);
                esimene.kaared.add(new Kaar(esimene, teine, kaugus));
                teine.kaared.add(new Kaar(teine, esimene, kaugus));
            }
        }
        
        return tipud;
    }
}
