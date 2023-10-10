import java.util.Arrays;

/*****************************************************************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2023/2024 sügissemester
 *
 * Kodutöö. Ülesanne nr 1
 * Teema: Järjendi summa, nii iteratiivselt kui ka rekursiivselt
 *
 * Autor: Matias Jürgenson
 *****************************************************************************/
public class Main {

    /***
     * loob vastavalt antud parameetritele juhuslikest arvudest koosneva massiivi
     * @param suurus massiivi sisestavate arvude arv
     * @param väiksim Minimaalne juhusliku arvu väärtus.
     * @param suurim Maksimaalne juhusliku arvu väärtus.
     * @return Juhuslikke arve sisaldav massiiv.
     */
    public static int[] juhuslik(int suurus, int väiksim, int suurim) {
        int[] massiiv = new int[suurus];
        for (int i = 0; i < suurus; i++)
            //valib suvalise arvu antud vahemikus
            massiiv[i] = (int) (väiksim + (suurim - väiksim + 1) * Math.random());
        return massiiv;
    }

    /**
     * vaatab kas massiiv on kahanevalt sorteeritud või mitte
     * @param massiiv sisestatud massiiv
     * @return tõeväärtus
     */
    public static boolean kasSorteeritud(int[] massiiv) {
        for (int i = 0; i < massiiv.length - 1; i++) {
            //kui eelnev arv on väiksem kui järgmine, siis pole tegu kahaneva massiiviga
            if (massiiv[i] < massiiv[i+1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * vaatab kas massiiv on kasvav või mitte
     * @param massiiv sisestatud massiiv
     * @return tõeväärtus
     */
    public static boolean kasKasvav(int[] massiiv) {
        for (int i = 0; i < massiiv.length - 1; i++) {
            //kui eelnev arv on suurem kui järgmine, siis pole tegu kasvava massiiviga
            if (massiiv[i] > massiiv[i+1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * pöörab massiivis olevad arvud ümber; esimene viimaseks, teine eelviimaseks, jne
     * @param massiiv massiiv mida hakkatakse ümber pöörama
     */
    public static void ümberpööramine(int[] massiiv) {
        int[] koopia = new int[massiiv.length];
        //massiivi ümberpööramine koopiasse
        for (int i = 0; i < massiiv.length; i++) {
            koopia[i] = massiiv[massiiv.length - 1 - i];
        }
        //massiivi asendamine koopiaga
        System.arraycopy(koopia, 0, massiiv, 0, massiiv.length);
    }

    //algne kood, mida muutsin, saadud siit: https://www.javatpoint.com/selection-sort-in-java
    /**
     * sorteerib massiivi valikmeetodi abil kahanevaks massiiviks
     * @param massiiv sorteeritav massiiv
     */
    public static void valikumeetod(int[] massiiv){
        //järjendid pikkusega 0, 1 või 2;
        if (massiiv.length < 3) {
            //kui massiivi pikkus on 0, 1 või kui 2 pikkusega on juba sorteeritud
            if (massiiv.length < 2 || massiiv[0] > massiiv[1]) {
                return;
            }
            //pöörab arvud ümber
            int arv = massiiv[0];
            massiiv[0] = massiiv[1];
            massiiv[1] = arv;
            return;

        }

        // ette antakse sorteeritud järjend
        if (kasSorteeritud(massiiv)) {
            return;
        }
        // mittekahanevalt sorteeritud järjend
        if (kasKasvav(massiiv)) {
            //pöörab massiivi ümber ja tagastab
            ümberpööramine(massiiv);
            return;
        }


        for (int i = 0; i < massiiv.length - 1; i++)
        {
            int indeks = i;
            //otsime suurimat numbrit
            for (int j = i + 1; j < massiiv.length; j++){
                //kui leitud number on suurem kui praegune number
                if (massiiv[j] > massiiv[indeks]){
                    indeks = j;
                }
            }
            //asendamine
            int suuremNumber = massiiv[indeks];
            massiiv[indeks] = massiiv[i];
            massiiv[i] = suuremNumber;
        }
    }

    //algne kood, mida muutsin, saadud siit: https://www.javatpoint.com/quick-sort
    /**
     * võetakse massiivi lõpust arv, mida kasutadakse, et massiiv ümber paikudata, nii et
     * paremal olevad arvud on temast suuremad ja temast vasakul olevad arvud temast väiksemad
     * @param a massiiv
     * @param algus massiivi algus
     * @param lõpp masiivi lõpp
     * @return arv, mis on väiksem kui temast vasakul olevad ja suurem kui temast paremal olevad arvud
     */
    public static int jaotus(int[] a, int algus, int lõpp) {
        //valime elemendi millega võrrelda teisi elemente
        int võrreldav = a[lõpp];
        int i = (algus - 1);

        //vaatame massiivi läbi
        for (int j = algus; j <= lõpp - 1; j++) {
            //kui praegune element on suurem võrreldavast
            if (a[j] > võrreldav)
            {
                //suurendame asendava koha arvu
                i++;

                //arvude vahetus
                int ajutine = a[i];
                a[i] = a[j];
                a[j] = ajutine;
            }
        }
        //arvude vahetus
        int t = a[i+1];
        a[i+1] = a[lõpp];
        a[lõpp] = t;
        //tagastab võrreldava arvu asukoha
        return (i + 1);
    }

    //algne kood, mida muutsin, saadud siit: https://www.javatpoint.com/quick-sort
    /**
     * sorteerib massiivi kiirsorteerimise teel kahanevaks massiiviks
     * @param massiiv sorteeritav massiiv
     * @param algus massiivi esimene element
     * @param lõpp massiivi viimane element
     */
    public static void kiirsorteerimine(int[] massiiv, int algus, int lõpp) {
        if (algus < lõpp) {
           //järjendid pikkusega 0, 1 või 2;
            if (massiiv.length < 3) {
                //kui massiivi pikkus on 0, 1 või kui 2 pikkusega on juba sorteeritud
                if (massiiv.length < 2 || massiiv[0] > massiiv[1]) {
                    return;
                }
                //pöörab arvud ümber
                int arv = massiiv[0];
                massiiv[0] = massiiv[1];
                massiiv[1] = arv;
                return;

            }

            // ette antakse sorteeritud järjend
            if (kasSorteeritud(massiiv)) {
                return;
            }
            // mittekahanevalt sorteeritud järjend
            if (kasKasvav(massiiv)) {
                //pöörab massiivi ümber ja tagastab
                ümberpööramine(massiiv);
                return;
            }

            //jaotame masiivi ja leiame koha, kus arvust paremal olevad arvud
            //on temast suuremad ja temast vasakul olevad arvud temast väiksemad
            int p = jaotus(massiiv, algus, lõpp);
            kiirsorteerimine(massiiv, algus, p - 1);
            kiirsorteerimine(massiiv, p + 1, lõpp);
        }
    }

    public static void main(String[] args) {
        //genereerime massiivi suurusega 50 000, väikseim võimalik element on 0 ja suurim 200 000
        int[] massiiv1 = juhuslik(50000, 0, 200000);
        int[] massiiv2 = new int[massiiv1.length];
        int[] massiiv3 = new int[massiiv1.length];
        System.arraycopy(massiiv1, 0, massiiv2, 0, massiiv1.length);
        System.arraycopy(massiiv1, 0, massiiv3, 0, massiiv1.length);

        int[] kasvav = {1,2,3,4,4,5,6,7,7,7,7,8,9,10};
        int[] üks = {1};
        int[] kaks = {1,2};

        System.out.println("valikmeetod");
        System.out.println("sorteeritud: " + kasSorteeritud(massiiv1));
        long start = System.currentTimeMillis();

        valikumeetod(massiiv1);

        long stop = System.currentTimeMillis();
        System.out.println("Aega kulus " + (stop - start) + " milliskundit");
        System.out.println("sorteeritud: " +  kasSorteeritud(massiiv1));

        System.out.println("\n-----\n");

        System.out.println("kiirsorteerimine");
        System.out.println("sorteeritud: " + kasSorteeritud(massiiv2));
        start = System.currentTimeMillis();

        kiirsorteerimine(massiiv2, 0, massiiv2.length-1);

        stop = System.currentTimeMillis();
        System.out.println("Aega kulus " + (stop - start) + " milliskundit");
        System.out.println("sorteeritud: " +  kasSorteeritud(massiiv2));

        System.out.println("\n-----\n");

        System.out.println("Java sisseehitatud sorteerimne");
        System.out.println("sorteeritud: " + kasSorteeritud(massiiv3));
        start = System.currentTimeMillis();

        Arrays.sort(massiiv3);

        stop = System.currentTimeMillis();
        ümberpööramine(massiiv3);
        System.out.println("Aega kulus " + (stop - start) + " milliskundit");
        System.out.println("sorteeritud: " +  kasSorteeritud(massiiv3));

        System.out.println("\n-----\n");

        System.out.println("kiir test");
        System.out.println("sorteeritud: " + kasSorteeritud(massiiv3));

        ümberpööramine(massiiv2);

        start = System.currentTimeMillis();

        kiirsorteerimine(massiiv2, 0, massiiv2.length-1);

        stop = System.currentTimeMillis();
        System.out.println("Aega kulus " + (stop - start) + " milliskundit");
        System.out.println("sorteeritud: " +  kasSorteeritud(massiiv3));

    }
}