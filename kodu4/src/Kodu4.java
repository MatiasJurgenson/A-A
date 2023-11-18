import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

public class Kodu4 {

    /**
     * Genereerib isikukoodi lähtudes reeglitest püstitatud <a href=https://et.wikipedia.org/wiki/Isikukood>siin.</a>
     * <br>
     * Numbrite tähendused:
     * <ul style="list-style-type:none">
     *      <li> 1 - sugu ja sünniaasta esimesed kaks numbrit, (1...8) </li>
     *      <li> 2-3 - sünniaasta 3. ja 4. numbrid, (00...99) </li>
     *      <li> 4-5 - sünnikuu, (01...12) </li>
     *      <li> 6-7 - sünnikuupäev (01...31) </li>
     *      <li> 8-10 - järjekorranumber samal päeval sündinute eristamiseks (000...999) </li>
     *      <li> 11 - kontrollnumber (0...9) </li>
     * </ul>
     *
     * @return Eesti isikukoodi reeglitele vastav isikukood
     */
    static long genereeriIsikukood() {
        ThreadLocalRandom juhus = ThreadLocalRandom.current();
        LocalDate sünnikuupäev = LocalDate.ofEpochDay(juhus.nextLong(-62091, 84006)); // Suvaline kuupäeva a 1800-2199
        long kood = ((sünnikuupäev.getYear() - 1700) / 100 * 2 - juhus.nextInt(2)) * (long) Math.pow(10, 9) +
                sünnikuupäev.getYear() % 100 * (long) Math.pow(10, 7) +
                sünnikuupäev.getMonthValue() * (long) Math.pow(10, 5) +
                sünnikuupäev.getDayOfMonth() * (long) Math.pow(10, 3) +
                juhus.nextInt(1000);
        int korrutisteSumma = 0;
        int[] iAstmeKaalud = {1, 2, 3, 4, 5, 6, 7, 8, 9, 1};
        for (int i = 0; i < 10; i++) {
            korrutisteSumma += (int) (kood / (long) Math.pow(10, i) % 10 * iAstmeKaalud[9 - i]);
        }
        int kontroll = korrutisteSumma % 11;
        if (kontroll == 10) {
            int[] iiAstmeKaalud = {3, 4, 5, 6, 7, 8, 9, 1, 2, 3};
            korrutisteSumma = 0;
            for (int i = 0; i < 10; i++) {
                korrutisteSumma += (int) (kood / (long) Math.pow(10, i) % 10 * iiAstmeKaalud[9 - i]);
            }
            kontroll = korrutisteSumma % 11;
            kontroll = kontroll < 10 ? kontroll : 0;
        }
        return kood * 10 + kontroll;
    }

    /**
     * Sorteerib isikukoodid sünniaja järgi:
     * <ul style="list-style-type:none">
     *     <li> a) järjestuse aluseks on sünniaeg, vanemad inimesed on eespool; </li>
     *     <li> b) kui sünniajad on võrdsed, määrab järjestuse isikukoodi järjekorranumber (kohad 8-10); </li>
     *     <li> c) kui ka järjekorranumber on võrdne, siis määrab järjestuse esimene number. </li>
     * </ul>
     *
     * @param isikukoodid sorteeritav isikukoodide massiiv
     */
    public static void sort(long[] isikukoodid) {
        //järjekord
        sortVäike(isikukoodid, 10, 3);
        //päev
        sortVäike(isikukoodid, 7, 2);
        //kuu
        sortVäike(isikukoodid, 5, 2);
        //aasta
        sortVäike(isikukoodid, 3, 2);
        //sajand

        //sortVäike(isikukoodid, 10, 5);
        //sortVäike(isikukoodid, 9, 1);
        //sortVäike(isikukoodid, 8, 2);
        //sortVäike(isikukoodid, 7, 3);
        //sortVäike(isikukoodid, 6, 4);
        //sortVäike(isikukoodid, 5, 4);
        //sortVäike(isikukoodid, 4, 3);
        //sortVäike(isikukoodid, 3, 1);
        //sortVäike(isikukoodid, 2, 1);


        LinkedList<Long>[] tabel = new LinkedList[9];


        for (long id : isikukoodid) {

            //võtame sajandi arvu
            int sajand = (int) (id / 10_000_000_000L);

            //kui on jääk lisame 1, et võtta arvesse ainult sajand ja mitte sugu
            if (sajand % 2 == 1) {
                sajand += 1;
            }

            if (tabel[sajand] == null) {
                tabel[sajand] = new LinkedList<>();
            }

            //isikukood lisatakase järjendisse vastavalt asukohale
            tabel[sajand].add(id);
        }
        int i = 0;

        //võtame kõik isikukoodid tabelist ja lisame massiivi tagasi
        for (LinkedList<Long> järjend : tabel) {
            if (järjend != null) {
                for (long id : järjend) {
                    isikukoodid[i] = id;
                    i++;
                }
            }
        }

        //vaatame isikukoodid läbi
        for (int j = 0; j < isikukoodid.length - 1; j++) {
            //võrreldavad isikukoodid
            long esimene = isikukoodid[j];
            long teine = isikukoodid[j+1];

            //õrreldavad sündinud samal ajal
            long jrk1 = (esimene / 10) % 1_000_000;
            long jrk2 = (teine / 10) % 1_000_000;

            //kui sünniajad on samad
            if (jrk1 == jrk2) {
                //kui esimene number isikukoodis on suurem tehakse vahetus
                if (esimene > teine) {
                    isikukoodid[j] = teine;
                    isikukoodid[j+1] = esimene;
                }
            }
        }
    }


    /**
     * sorteerib isikukoodid vastavalt etteantud parameeterkohtadele
     * @param isikukoodid massiiv isikukoodidest
     * @param mitmes esimene kümnendkoha asukoht mida tahame sorteerida
     * @param mitu mitu kümmendkohta jäägiks võtta
     */
    public static void sortVäike(long[] isikukoodid, long mitmes, long mitu) {
        mitmes = (long) Math.pow(10, (11-mitmes));
        mitu = (long) Math.pow(10, mitu);

        //tabel kuhu sorteerime
        LinkedList<Long>[] tabel = new LinkedList[(int) mitu];

        //sorteerime tabelisse
        for (long id : isikukoodid) {

            //võtame sorteeritava arvu
            //võtame kümnendkoha arvu ja sealt vastavalt jäägi mitu kümnendkohta tahame
            int asukoht = (int) ((id / mitmes) % mitu);

            //kui asukohta pole tabelis siis lisatakse
            if (tabel[asukoht] == null) {
                tabel[asukoht] = new LinkedList<>();
            }

            //isikukood lisatakase järjendisse vastavalt asukohale
            tabel[asukoht].add(id);
        }

        int i = 0;

        //võtame kõik isikukoodid tabelist ja lisame massiivi tagasi
        for (LinkedList<Long> järjend : tabel) {
            if (järjend != null) {
                for (long id : järjend) {
                    isikukoodid[i] = id;
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) {
        long[] isikukoodid = new long[10_000_000];
        for (int i = 0; i < 10_000_000; i++) {
            isikukoodid[i] = genereeriIsikukood();
        }

        long[] test = new long[]{20001010003L, 10001010013L};

        System.out.println(Arrays.toString(test));

        long start = System.currentTimeMillis();

        sort(test);

        long stop = System.currentTimeMillis();
        System.out.println("Aega kulus " + (stop - start) + " milliskundit");

        System.out.println(Arrays.toString(test));

        start = System.currentTimeMillis();

        sort(isikukoodid);

        stop = System.currentTimeMillis();
        System.out.println("Aega kulus " + (stop - start) + " milliskundit");
    }

}
