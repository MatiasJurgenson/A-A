import ee.ut.dendroloj.Dendrologist;
/*****************************************************************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2023/2024 sügissemester
 *
 * Kodutöö. Ülesanne nr 5
 * Teema: AVL-puud
 *
 * Autor: Matias Jürgenson
 *
 *****************************************************************************/

public class Kodu5 {

    /**
     * meetod puu kuvamiseks
     * @param juurTipp puu mida tahetakse kuvada
     */
    static void kuvaKahendotsimispuu(KOTipp juurTipp) {
        Dendrologist.drawBinaryTree(juurTipp, t -> Integer.toString(t.väärtus), t -> t.v, t -> t.p);
    }

    /**
     * Lisab antud väärtuse antud puusse ja tasakaalustab selle
     * @param juur Puu juurtipp, kuhu tahetakse väärtus lisada
     * @param väärtus lisatav väärtus
     * @return puu juur, kus on lisatud väärtus
     */
    public static KOTipp lisaKirje(KOTipp juur, int väärtus) {
        //kui antud tipp on tühi loob uue puu koos antud väärtusega
        if (juur == null) return new KOTipp(väärtus);

        //kui tippu väärtus suurem kui lisatav
        if (juur.väärtus > väärtus) {
            //otsib väiksemat väärtust
            juur.v = lisaKirje(juur.v, väärtus);
        } else {
            //muidu otsib suuremat väärtust
            juur.p = lisaKirje(juur.p, väärtus);
        }

        //tagastab tasakaalustatud juure
        return tasakaalusta(juur);
    }

    /**
     * Eemaldab antud väärtuse antud puus ja tasakaalustab selle
     * @param juur Puu juurtipp, kust tahetakse väärtus eemaldada
     * @param väärtus eemaldatav väärtus
     * @return puu juur, kus on eemaldatud väärtus
     */
    public static KOTipp eemaldaKirje(KOTipp juur, int väärtus) {
        //kui tipp on null siis eemaldatavat väärtust pole
        if (juur == null) return null;

        //kui väärtus leitud
        if (juur.väärtus == väärtus) {
            //kui tegu on lehttipuga, muudatb tipu null-iks
            if (juur.v == null && juur.p == null) return null;

            //kui mõlemad alampuud on olemas
            if (juur.v != null && juur.p != null) {

                KOTipp järgmine = juur.p;

                //otsime tipu parempoolse tipu vasakpoolsemat väärtust
                while (järgmine.v != null) järgmine = järgmine.v;

                //paneme eemaldava tippu väärtuseks leitud väärtuse
                juur.väärtus = järgmine.väärtus;

                //eemaldame tipu kust väärtuse võtsime
                juur.p = eemaldaVasakpoolseim(juur.p);

                //tasakaalustame puu ja tagastame
                return tasakaalusta(juur);
            }
            //kui ainult üks alampuu on olemas
            if (juur.v != null) return juur.v;
            if (juur.p != null) return juur.p;

        //kui tippu väärtus suurem kui eemaldatav
        } else if (juur.väärtus > väärtus) {
            //otsib väiksemat väärtust
            juur.v = eemaldaKirje(juur.v, väärtus);
        } else {
            //muidu otsib suuremat väärtust
            juur.p = eemaldaKirje(juur.p, väärtus);
        }

        //tagastab tasakaalustatud juure
        return tasakaalusta(juur);

    }

    /**
     * Eemaldab kõige vasakpoolseima väärtuse antud tipust
     * @param juur puu juur, kust hakatakse eemaldtavat väärtust otsima
     * @return puu juur, kus on vasakpoolseim väärtus eemaldatud
     */
    public static KOTipp eemaldaVasakpoolseim(KOTipp juur) {
        //kui leidub veel vasakpoolseimat tippu
        if (juur.v != null) juur.v = eemaldaVasakpoolseim(juur.v);
        //kui vasakpoolsel tipul on parem asendatakse tipp parema alampuuga
        else if (juur.p != null) return juur.p;
        //muidu pannakse null
        else return null;
        return juur;
    }

    /**
     * Lisatakse antud puud üheks AVL puuks
     * @param avl1 esimene puu
     * @param avl2 teine puu
     * @return uus AVL puu, mis on loodud antud puudest
     */
    public static KOTipp liidaAVLpuud(KOTipp avl1, KOTipp avl2) {
        //leiab esimese puu tippude arvu
        int avl1Tippe = tippe(avl1);

        //loob massiivi, kuhu lisatakse puude väärtused
        int[] väärtused = new int[avl1Tippe + tippe(avl2)];

        //lisatakse puu väärtused massiivi
        paiguta(avl1, 0, väärtused);
        paiguta(avl2, avl1Tippe, väärtused);


        KOTipp y = null;
        //lisatakse väärtused uute puusse
        for (int väärtus : väärtused) {
            y = lisaKirje(y, väärtus);
        }

        return y;
    }

    /**
     * lisab antud puu väärtused antud massiivi
     * @param juur puu juur, mille elemente lisatakse
     * @param i indeks, millest hakkatakse lisama
     * @param massiiv massiiv kuhu lisatakse
     */
    public static void paiguta(KOTipp juur, int i, int[] massiiv) {

        if (juur == null) return;

        //leiab palju tippe asub vasakus alampuus
        int vasakulTippe = tippe(juur.v);

        //lisab tipu väärtuse massiivi, olenevalt indeksist ja palju tippe asub vasakul
        massiiv[i + vasakulTippe] = juur.väärtus;

        //lisame vasaku alampuu väärtused
        paiguta(juur.v, i, massiiv);
        //lisame parema alampuu väärtused
        paiguta(juur.p, i+1+vasakulTippe, massiiv);
    }

    /**
     * leiab mitu tippu asub puus
     * @param juur puu juur, mille tippe hakatakse lugema
     * @return tippude arv
     */
    public static int tippe(KOTipp juur) {
        //kui null siis tippu pole
        if (juur == null) return 0;

        //praegune tipp + vasaku alampuu tippud + parema alampuu tipud
        return 1 + tippe(juur.v) + tippe(juur.p);
    }

    /**
     * sooritab puu tipul parempöörde
     * @param x tipp millele sooritadakse pööret
     * @return pööratud puu
     */
    public static KOTipp paremPööre(KOTipp x) {
        KOTipp y;

        //muudab uuele puule antud puu vasaku alampuu
        y = x.v;

        //muudab antud puule vasaku alampuule uue puu parema alampuule
        x.v = y.p;

        //muudab uue puu paremema alampuu vanaks puuks
        y.p = x;

        return y;
    }

    /**
     * sooritab puu tipul vasakpöörde
     * @param x tipp millele sooritadakse pööret
     * @return pööratud puu
     */
    public static KOTipp vasakPööre(KOTipp x) {
        KOTipp y;

        //muudab uuele puule antud puu parema alampuu
        y = x.p;

        //muudab eelmisele puule parema alampuule uue puu vasaku alampuule
        x.p = y.v;

        //muudab uue puu vasaku alampuu vanaks puuks
        y.v = x;

        return y;
    }

    /**
     * annab puu tippudele kõrguste info
     * @param juur puu juur kuhu infot hakatakse lisama
     * @return kõrgus, mis lisatakse tippu
     */
    static int kõrgused(KOTipp juur) {
        //kui tippu pole on kõrgus 0
        if (juur == null) return 0;

        //paegune tipp + suurim alampuu kõrgus
        int kõrgus = 1 + Math.max(kõrgused(juur.v), kõrgused(juur.p));

        //kõrgus lisatakse tippu
        juur.x = kõrgus;
        return kõrgus;
    }

    /**
     * tasakaalustab antud puu
     * @param juur puu juur, mida hakatakse tasakaalustama
     * @return tasakaalustatud puu
     */
    public static KOTipp tasakaalusta(KOTipp juur) {

        //kui tippu pole, siis tasakaalustatud
        if (juur == null) return null;

        //tasakaalustab parema ja vasaku alampuu
        juur.v = tasakaalusta(juur.v);
        juur.p = tasakaalusta(juur.p);

        //leiab tippude kõrgused
        kõrgused(juur);

        //leiab alapuude kõrgused
        int vasakKõrgus = (juur.v != null ? juur.v.x : 0);
        int paremKõrgus = (juur.p != null ? juur.p.x : 0);

        //kui kõrguste vahe on −1, 0 või +1, siis tasakaalus
        if (Math.abs(vasakKõrgus - paremKõrgus) < 2) return juur;

        //Kui tipu vasaku ja parema alampuu kõrguste vahe on −2
        if (vasakKõrgus - paremKõrgus == -2) {

            //leiab parema alampuu kõrgused
            vasakKõrgus = (juur.p.v != null ? juur.p.v.x : 0);
            paremKõrgus = (juur.p.p != null ? juur.p.p.x : 0);

            //juurtipu vasaku ja parema alampuu kõrguste vahe +1 tehakse parempööre paremas alampuus
            if (vasakKõrgus - paremKõrgus > 0) {
                juur.p = paremPööre(juur.p);
            }

            //sooritadakse vasakpööre
            return vasakPööre(juur);

        //Kui tipu vasaku ja parema alampuu kõrguste vahe on +2
        } else {

            //leiab vasaku alampuu kõrgused
            vasakKõrgus = (juur.v.v != null ? juur.v.v.x : 0);
            paremKõrgus = (juur.v.p != null ? juur.v.p.x : 0);

            //vasaku ja parema alampuu kõrguste vahe −1 tehakse vasakpööre vasakus alampuus
            if (vasakKõrgus - paremKõrgus < 0) {
                juur.v = vasakPööre(juur.v);
            }

            //sooritadakse parempööre
            return paremPööre(juur);
        }
    }

    public static void main(String[] args) {
        KOTipp y = null;
        y = lisaKirje(y, 1);
        kuvaKahendotsimispuu(y);
        y = lisaKirje(y, 2);
        kuvaKahendotsimispuu(y);
        y = lisaKirje(y, 3);
        kuvaKahendotsimispuu(y);
        //y = eemaldaKirje(y, 2);
        //kuvaKahendotsimispuu(y);
    }

}