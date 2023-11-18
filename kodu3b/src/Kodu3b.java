import java.util.ArrayDeque;
import java.util.Deque;
/*****************************************************************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2023/2024 sügissemester
 *
 * Kodutöö. Ülesanne nr 3b
 * Teema: Magasin ja järjekord
 *
 * Autor: Matias Jürgenson
 *****************************************************************************/
public class Kodu3b {

    /**
     * leiab kõikvõimalikud kombinatsioonid arvu spordikoti ostmiseks
     * @param n spordikoti hind sentides
     * @return kombinatsioonide arv
     */
    public static long rahakott(int n) {

        //kui palju igat münti on
        int[] mündiKogused = {15, 15, 15, 10, 10};

        //magasin kuhu erinevad summad lisatakse
        Deque<Integer> magasin = new ArrayDeque<>();
        //algsumma
        magasin.push(0);

        //mitmendat münti vaadatakse (0 on 10 sendine, 1 20 sendine, jne)
        Deque<Integer> magasinAbi = new ArrayDeque<>();
        //algindeks
        magasinAbi.push(0);

        //variandite lugemiseks
        int variante = 0;


        while (!magasin.isEmpty()) {
            int summa = magasin.pop();
            int indeks = magasinAbi.pop();

            //kui müntidest saab seljakotti osta
            if (summa == n) {
                variante++;

            //kui summa on väiksem kui hind ja müntide indeks on väiksem kui erinevate müntide arv
            } else if (summa < n && indeks < 5) {
                //lisab 0 kuni 15 või 10, vastavalt mündi tüübile, ühte tüüpi münti summale juurde
                for (int i = 0; i < mündiKogused[indeks] + 1; i++) {
                    //kui on tegu 10 sendisega
                    if (indeks == 0) {
                        magasin.push(summa + i*10);
                        //lisab järgmise mündi indeksi
                        magasinAbi.push(1);
                    }
                    //kui on tegu 20 sendisega
                    else if (indeks == 1) {
                        magasin.push(summa + i*20);
                        magasinAbi.push(2);
                    }
                    //kui on tegu 50 sendisega
                    else if (indeks == 2) {
                        magasin.push(summa + i*50);
                        magasinAbi.push(3);
                    }
                    //kui on tegu 1 eurosega
                    else if (indeks == 3) {
                        magasin.push(summa + i*100);
                        magasinAbi.push(4);
                    }
                    //kui on tegu 2 eurosega
                    else {
                        magasin.push(summa + i*200);
                        magasinAbi.push(5);
                    }
                }
            }
        }

        //tagastab variantide arvu
        return variante;

    }

    public static void main(String[] args) {
        System.out.println(rahakott(2000));
        System.out.println(rahakott(2001));
        System.out.println(rahakott(5000));
        System.out.println(rahakott(4000));
    }
}