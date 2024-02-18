import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/*****************************************************************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2023/2024 sügissemester
 *
 * Kodutöö. Ülesanne nr 6b
 * Teema: Kahendkuhjad
 *
 * Autor: Matias Jürgenson
 *
 *****************************************************************************/

public class Kodu6b {
    /**
     * meetod leidmaks kõikvõimalikud kahendkuhjad, kus on n elementi
     * @param n mitu elementi kuhi sisaldab
     * @return kõikvõimalikud variandid massiivina ja sorteeritult kasvavas järjestuses
     */
    public static int[][] kuhjad(int n) {

        //järjekord erinevate kuhjade variantide hoidmiseks
        Queue<int[]> kuhjad = new ArrayDeque<>();

        //leiame mitu kuhja saame luua
        int kuhjasid = variantideArv(n);

        //massiiv kuhu leitud kuhjad lisatakse
        int[][] variandid = new int[kuhjasid][n];

        //kui huhjas on 1 element tagastab selle kohe
        if (n==1) {
            variandid[0][0] = 1;
            return variandid;
        }

        //leiame, mis saab olla tipu miinimum arv
        int[] miinumum = new int[n];
        miinimumArv(miinumum, 0);


        //loome esimese kuhja, sisestame juurtipu ja lisame järjekorda
        int[] algkuhi = new int[n];
        algkuhi[0] = n;
        kuhjad.add(algkuhi);

        //arv millega lisame kuhjade variante massiivi
        int j = 0;

        //senikaua kuni järjekord pole tühi loome erinevaid variante
        while (!kuhjad.isEmpty()) {
            int[] praeguneKuhi = kuhjad.remove();

            //leiame koha kuhu seda asetada
            int koht = leiaEsimene(praeguneKuhi, 0);

            //vaatame kõikvõimalikud lisatavad arvud läbi, välja arvatud n
            for (int i = miinumum[koht]; i < n; i++) {

                //kui arvu ei leidu
                if (!kasLeidub(praeguneKuhi,i)) {
                    //kui ülemus on suurem kui lisatav arv
                    if (i < praeguneKuhi[(koht-1) / 2]) {
                        //teeme koopia ja lisame arvu

                        int[] uusKuhi = new int[n];
                        System.arraycopy(praeguneKuhi, 0, uusKuhi, 0, koht);
                        //int[] uusKuhi = Arrays.copyOf(praeguneKuhi, n);
                        uusKuhi[koht] = i;

                        //kui kuhi sai täis lisame selle varaintidesse ja suurendame järgmise variandi asukohta
                        if (leiaEsimene(uusKuhi, 0) == -1) variandid[j++] = uusKuhi;
                        //muidu lisame järjekorda töötluseks
                        else kuhjad.add(uusKuhi);
                    }
                }
            }
        }

        //kui järjekord on läbi töödeltud tagastame kõik variandid
        return variandid;
    }

    /**
     * leiab miinumum arvud, mis kuhja tippud saavad olla
     * @param a kuhi massiivi kujul
     * @param i tipp mida vaatame
     */
    public static void miinimumArv(int[] a, int i) {
        //leiame alluva indeksi
        int vasakAlluv = (2 * i + 1);
        int paremAlluv = (2 * i + 2);

        //mitu alluvat
        int vasakul = 0;
        int paremal = 0;

        //kui vasak alluv on olemas
        if (vasakAlluv < a.length) {
            //teeme vasakul pool läbi ja lisame alluvate arvu
            miinimumArv(a, vasakAlluv);
            vasakul = a[vasakAlluv];
        }

        //kui parem alluv olemas
        if (paremAlluv < a.length) {
            //teeme vasakul pool läbi ja lisame alluvate arvu
            miinimumArv(a, paremAlluv);
            paremal = a[paremAlluv];
        }

        //lisame miinum arvu mis tipp saab olla
        a[i] = vasakul + paremal + 1;
    }

    /**
     * leiab mitu erinevat kuhja saab leida, mis sisaldavad n elementi
     * algoritm saaadud siit: https://oeis.org/A056971
     * @param i elementide arv kuhjas, mille variante leiame
     * @return variantide arv täisarvuna
     */
    public static int variantideArv(int i) {
        //loome massiivi kuhu lisame eelnevad variandid
        int[] a = new int[i + 1];

        //esimesed kaks varianti
        a[0] = 1;
        a[1] = 1;

        //leiame kõik ülejäänud variandid
        for (int n = 2; n < i + 1; n++) {
            //h = Floor[Log[2, n + 1]] - 1;
            int h = (int) Math.floor(Math.log(n+1) / Math.log(2)) - 1;
            //b = 2^h - 1;
            int aste = (int) Math.pow(2, h);
            int b = aste - 1;
            //r = n - 1 - 2*b;
            int r = n - 1 - 2 * b;
            //r1 = r - Floor[r/2^h]*(r - 2^h);
            int r1 = r - (r / aste) * (r - aste);
            //r2 = r - r1;
            int r2 = r - r1;
            //Binomial[n - 1, b + r1]*a[b + r1]*a[b + r2]];
            a[n] = binoom(n - 1, b + r1)*a[b + r1]*a[b + r2];
        }

        //tagastame variantide arvu
        return a[i];
    }

    /**
     * meetod binoom kombinatsioonide arvutamiseks
     * algoritm saaadud siit: https://rosettacode.org/wiki/Evaluate_binomial_coefficients
     * @param n mitu korda midagi tehakse
     * @param k mitu korda midagi juhtub
     * @return kombinatsioonide arv
     */
    private static int binoom(int n, int k) {
        long arv = 1;

        //korrutame n! / (n - k)! -ga
        for (int i = n; i >= n-k+1; i--) {
            arv *= i;
        }

        //jagame k!
        for (int i = 1; i <= k; i++) {
            arv /= i;
        }

        //tagastame kombinatsioonide arvu
        return (int) arv;
    }

    /**
     * meetod leidmaks, kas arv leidub massiivis
     * @param a massiiv, kust aru otsime
     * @param b arv mida otsime
     * @return tõeväärtuse, sellest kas arv leidub või mitte
     */
    public static boolean kasLeidub(int[] a, int b) {
        //vaatame massiivi läbi
        for (int arv : a) {
            //kui arv leidub tagastame tõese väärtuse
            if (arv == b) return true;
        }
        //muidu
        return false;
    }

    /**
     * meetod leidmaks arvu esimese leitud koha indeksi, antud massiivis, tagastamiseks
     * @param a massiiv, kust indeksi otsitakse
     * @param b arv, mille indeksit otsitakse
     * @return indeks, kus arv asub
     */
    public static int leiaEsimene(int[] a, int b) {
        //vaatame iga massiivi elemendi läbi
        for (int i = 0; i < a.length; i++) {
            //kui leidub tagastame indeksi
            if (a[i] == b) return i;
        }
        //muidu
        return -1;
    }

    public static void main(String[] args) {

        System.out.println(variantideArv(1));
        System.out.println(Arrays.deepToString(kuhjad(1)));
        System.out.println(Arrays.deepToString(kuhjad(5)));


        long start = System.currentTimeMillis();

        kuhjad(13);

        long stop = System.currentTimeMillis();
        System.out.println("Aega kulus " + (stop - start) + " milliskundit");
        start = System.currentTimeMillis();

        kuhjad(14);

        stop = System.currentTimeMillis();
        System.out.println("Aega kulus " + (stop - start) + " milliskundit");


    }
}
