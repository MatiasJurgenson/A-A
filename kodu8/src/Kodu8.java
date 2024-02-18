/*****************************************************************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2023/2024 sügissemester
 *
 * Kodutöö. Ülesanne nr 8
 * Teema: toespuud
 *
 * Autor: Matias Jürgenson
 *
 *****************************************************************************/
import java.util.*;

public class Kodu8 {

    /**
     * Leiab minimaalse kaugusega toesepuu Kruskali algoritmiga.
     *
     * @param nimed Asukohtade nimed
     * @param K     Asukohtade koordinaadid tabelina, kus iga rida on kujul [laiuskraad, pikkuskraad]
     * @return Minimaalse toesepuu kaarte loend, kujul [[i1, j2], [i2, j2], ...], kus i ja j on asukohtade indeksid alates 0-ist
     */
    public static int[][] toesKruskal(String[] nimed, double[][] K) {

        //loome tippude järjendi
        List<Tipp> tipud = new ArrayList<>();
        for (int i = 0; i < nimed.length; i++) {
            tipud.add(new Tipp(i));
        }

        //lisame tippudele esindajad, et saaksime kasutada Galler-Fischeri klassipuid
        for (Tipp tipp : tipud) {
            tipp.z = tipp;
        }

        //loome kaarte järjendi ja lisame kaared
        List<Kaar> kaared = new ArrayList<>();
        for (int i = 0; i < K.length; i++) {
            for (int j = i + 1; j < K.length; j++) {

                //leiame kauguse kahe punkti vahel ja lisame kaarte hulka
                double kaugus = kaugus(K[i][0], K[i][1], K[j][0], K[j][1]);
                kaared.add(new Kaar(i, j, kaugus));
            }
        }

        //sorteerime kaared
        kaared.sort(new Comparator<Kaar>() {
            @Override public int compare(Kaar kaar1, Kaar kaar2)
            {
                double vahe = kaar1.kaal - kaar2.kaal;
                //kui kaal1 on väiksem
                if (vahe < 0 ) return -1;
                //kui kaal1 on suurem
                else if (vahe > 0 ) return 1;
                //võrdsed
                else return 0;
            }
        });

        //loome tulemuste kaarte massiivi
        int[][] tulemus = new int[tipud.size() - 1][2];
        int k = 0;

        //hakkame kaari lisama
        for (Kaar kaar : kaared) {

            //kaare alg- ja lõpptipp
            Tipp alg = tipud.get(kaar.alg);
            Tipp lõpp = tipud.get(kaar.lõpp);

            //leiame tipude juurtipud
            Tipp algVanem = leiaJuurtipp(alg);
            Tipp lõppVanem = leiaJuurtipp(lõpp);

            //kui ei kuulu samasse puusse, ehk ei teki tsüklit
            if (algVanem != lõppVanem) {

                //muudame algtippu esindajat
                algVanem.z = lõppVanem;

                //lisame kaare
                tulemus[k++] = new int[]{kaar.alg, kaar.lõpp};
            }
        }

        //tagastame minimaalse toespuu
        return tulemus;
    }

    /**
     * Leiab tipu juurtippu Galler-Fischeri klassipuu
     * @param tipp mille vanemat otsime
     * @return tippu juurtippu Galler-Fischeri klassipuus
     */
    public static Tipp leiaJuurtipp(Tipp tipp) {
        //kui tipp on juurtipp
        if (tipp.z == tipp) return tipp;

        //leiame juurippu rekursiivselt
        Tipp juurtipp = leiaJuurtipp(tipp.z);

        //viime tipu üles kui ta esindaja pole juurtipp
        if (tipp.z != juurtipp) {
            tipp.z = juurtipp;
        }

        //tagastame juurtippu
        return juurtipp;

    }

    /**
     * Leiab lähendi rändkaupmehe probleemile.
     *
     * @param nimed Asukohtade nimed
     * @param K     Asukohtade koordinaadid tabelina, kus iga rida on kujul [laiuskraad, pikkuskraad]
     * @return Rändkaupmehe lähend kui asukohtade läbimise järjestus arvude 0...n-1 permutatsioonina, kus n on tippude arv
     */
    public static int[] rändkaupmees(String[] nimed, double[][] K) {

        //loome tippude järjendi
        List<Tipp> tipud = new ArrayList<>();
        for (int i = 0; i < nimed.length; i++) {
            tipud.add(new Tipp(i));
        }

        //leiame toespuu ja loome selle graafi
        int[][] toespuu = toesKruskal(nimed, K);
        for (int[] kaar : toespuu) {

            //võtame tippude indeksid
            int alg = kaar[0];
            int lõpp = kaar[1];

            //leiame vastavad tipud
            Tipp x = tipud.get(alg);
            Tipp y = tipud.get(lõpp);

            //lisame tippudele kaared
            x.kaared.add(new Kaar(alg, lõpp, kaar.length));
            y.kaared.add(new Kaar(lõpp, alg, kaar.length));
        }

        //võtame tippu millega hakkame graafi läbima ja lisame magasini
        Tipp esimene = tipud.get(0);
        Deque<Tipp> q = new ArrayDeque<>();
        q.add(esimene);

        //järjend kuhu lisame läbitud tippude järjekorra
        List<Integer> läbimine = new ArrayList<>();

        //kuni pole töötlemata tippe
        while (!q.isEmpty()) {
            //võtame tippu
            Tipp praegune = q.pop();

            //töötleme ja lisame järjendisse
            praegune.x = 1;
            läbimine.add(praegune.info);

            //vaata tipu kaared läbi
            for (Kaar kaar : praegune.kaared) {

                //leiame järgmise tipu, mis pole töödeltud ja lisame magasini
                Tipp lõpp = tipud.get(kaar.lõpp);
                if (lõpp.x == 1) continue;
                q.push(lõpp);
            }
        }

        //muudame järjendi massiiviks
        int[] tulemus = new int[läbimine.size()];
        for (int i = 0; i < läbimine.size(); i++) {
            tulemus[i] = läbimine.get(i);
        }

        //tagastame teekonna
        return tulemus;
    }

    /**
     * Leiab kahe punkti vahelise kauguse, kasutades valemit siit:
     * https://en.wikipedia.org/wiki/Haversine_formula
     *
     * @param lai1 Esimese punkti laiuskraad
     * @param pik1 Esimese punkti pikkuskraad
     * @param lai2 Teise punkti laiuskraad
     * @param pik2 Teise punkti pikkuskraad
     * @return Punktide vaheline kaugus kilomeetrites
     */
    public static double kaugus(double lai1, double pik1, double lai2, double pik2) {
        double dLaius = Math.pow(Math.sin(Math.toRadians(lai2 - lai1) / 2), 2);
        return 2 * 6371.0088 * Math.asin(Math.sqrt(dLaius +
                (1 - dLaius - Math.pow(Math.sin(Math.toRadians(lai1 + lai2) / 2), 2)) *
                        Math.pow(Math.sin(Math.toRadians(pik2 - pik1) / 2), 2)));
    }

    /*public static void main(String[] args) throws Exception{
        double[][] kordinaadid = new double[][]{{58.000093581041504, 26.910056744311554}, {58.04849981859941, 27.285501621034705}};
        Praktikum14.NimedegaKoordinaadid K = Praktikum14.loeKoordinaadid(new File("omniva.csv"));
        //toesKruskal(K.nimed, K.K);
        System.out.println(Arrays.deepToString(toesKruskal(K.nimed, kordinaadid)));
    }*/

}