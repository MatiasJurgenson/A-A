import java.util.*;

/*****************************************************************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2023/2024 sügissemester
 *
 * Kodutöö. Ülesanne nr 7a
 * Teema: Kaugusalgoritmid
 *
 * Autor: Ülli Õpilane
 *
 *****************************************************************************/
public class Kodu7a {

    /**
     * Leiab kõik linnad, mis asuvad k tankimise kaugusel, kui auto suudab läbida x kilomeetrit enne tankimist.
     * Linnad leitakse antud linnade massiivist ja sellele vastama
     * @param lähtelinn linn, kus alustadakse
     * @param x mitu kilomeetrit auto saab sõita enne tankimist
     * @param k mitu tankimist tehakse
     * @param linnad kõikide linnade nimede sõnemassiiv
     * @param M linnade naabrusmaatriks, mis näitab kui kaugel mingi linn teisest on
     * @return kõik linnad, kuhu jõuab k tankimsega, kui auto suudab läbida x kilomeetrit enne tankimist
     */
    public static String[] jõuame(String lähtelinn, int x, int k, String[] linnad, int[][] M) {

        //teeme kõik linnad tippudeks
        List<Tipp> tipud = new ArrayList<>();
        for (String s : linnad) tipud.add(new Tipp(s));



        //otsime lähtelinna tippu
        Tipp linnaTipp = null;
        for (Tipp tipp : tipud) {
            //kui lähtelinn lisame
            if (tipp.info.equals(lähtelinn)) linnaTipp = tipp;
        }

        //läbime maatriksi, et leida leida linnade kaugused, mis on väiksemad kui x
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                //kui teed pole või on linn ise
                if (M[i][j] == -1 || M[i][j] == 0) continue;

                //kui kaugus suurem kui x, siis teed ei leidu
                if (M[i][j] > x) M[i][j] = -1;
                //kui väiksem lisame tee (kaare) tippu
                else tipud.get(i).kaared.add(new Kaar(tipud.get(i), tipud.get(j), M[i][j]));
            }
        }

        //järjend kuhu paneme kõik linnad, mis asuvad k kaugsel
        List<String> kLinnad = new ArrayList<>();

        //järjekord, millega hakkame graafi läbima
        Queue<Tipp> tippudeJärjekord = new ArrayDeque<>();
        linnaTipp.x = 0;
        tippudeJärjekord.add(linnaTipp);

        //seni kaua kuni leidub tippe järjekorras
        while (!tippudeJärjekord.isEmpty()) {
            //võtame tippu
            Tipp praegune = tippudeJärjekord.remove();

            //läbime tippu kaared
            for (Kaar kaar : praegune.kaared) {

                //võtame kaare lõpptippu ehk praeguse tippu naabertippu
                Tipp tipp = kaar.lõpp;

                //kui seda pole läbitud
                if (tipp.x == 0) {

                    //lisame kui kaugel see asub lähtelinnast ja lisame järjekorda
                    tipp.x = praegune.x + 1;
                    tippudeJärjekord.add(tipp);
                }

            }

            //kui vaadeldav tipp on k kaugesel ja ei ole Lähtelinn lisame väljastatavate linnade hulka
            if (praegune.x == k && !praegune.info.equals(lähtelinn)) kLinnad.add(praegune.info);
        }

        //teeme järjendi ümber massiiviks
        String[] tagastus = new String[kLinnad.size()];
        for (int i = 0; i < kLinnad.size(); i++) {
            tagastus[i] = kLinnad.get(i);
        }


        return tagastus;
    }

    public static void main(String[] args) throws Exception{
        /*
        Praktikum12.NimedegaNaabrusmaatriks m = Praktikum12.loeNaabrusmaatriks(new File("linnade_kaugused.tsv"));
        int [][] kaugused = m.M;
        String[] nimed = m.nimed;

        System.out.println(Arrays.toString(jõuame("Põltsamaa", 64, 2, nimed, kaugused)));
        System.out.println(Arrays.toString(jõuame("Karksi-Nuia", 26, 2, nimed, kaugused)));
         */
    }
}