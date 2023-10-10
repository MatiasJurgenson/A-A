/*****************************************************************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2023/2024 sügissemester
 *
 * Kodutöö. Ülesanne nr 2a
 * Teema: Rekursioon. Variantide läbivaatamine
 *
 * Autor: Matias Jürgenson
 *
 *****************************************************************************/
public class Kodu2A {

    /**
     * kontrollib, kas sellest järjendist on mingi elementide valik, milles on mitte rohkem kui pooled elemendid
     * ja annab kogukaaluks 10 kg
     * @param a sisestatav järjend
     * @return tõeväärtus, sellest kas leidub valik elemente nii, et selles pole rohkem kui pooled elemendid
     * ja annab kogukaaluks 10 kg
     */
    public static boolean valik(int[] a) {
        return valikAbi(a, 0, 0,0 );
    }

    /**
     * abimeetood valik meetodile
     * @param a sisestatud järjend
     * @param valik valitud elementide kogukaal
     * @param indeks praeguse järjendi elemendi indeks
     * @param elementideArv mitu elementi on valikus
     * @return tagastab tõeväärtuse
     */
    public static boolean valikAbi(int[] a, int valik, int indeks, int elementideArv) {
        //kui kaal on rohkem kui 10 kg, siis lõpetame praeguste valitud elemntide valiku, siis ei leidu
        if (valik > 10000) {
            return false;
        }
        //kui kaal on 10 kg ja on vähem kui pooled elemendid valikus siis leidub
        if (valik == 10000 && elementideArv <= (a.length / 2)) {
            return true;
        }
        //kui oleme lõppu jõudnud siis ei leidu
        if (indeks == a.length) {
            return false;
        }
        // teeme võrdluse kahel erineval olukorral
        // 1. kui lisame elemendi valikusse
        // 2. ei lisa elementi
        // kui leidub selline valik siis disjunktsioon tagastab tõeväärtuse "true"
        return (valikAbi(a, valik + a[indeks], indeks + 1, elementideArv + 1) || valikAbi(a, valik, indeks + 1, elementideArv));
    }

    public static void main(String[] args) {
        int[] a = {1, 1, 10000};
        int[] b = {};
        int[] c = {10000};
        int[] d = {5000, 5000,1,1};
        int[] e = {5001, 5001,5000,4000};


        //erinevate olukordade katsetamine
        System.out.println(valik(a));
        System.out.println(valik(b));
        System.out.println(valik(c));
        System.out.println(valik(d));
        System.out.println(valik(e));
    }

}
