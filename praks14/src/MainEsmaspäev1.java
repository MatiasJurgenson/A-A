import ee.ut.dendroloj.Dendrologist;
import ee.ut.dendroloj.GraphCanvas;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

/**
 * Algoritmid ja andmestruktuurid, Praktikum XV, graafi II: minimaalne toespuu.
 * Praktikumijuhendaja: Kristo Väljako
 */
public class MainEsmaspäev1 {

    static class Tipp {
        final String info; // tipu info
        final List<Kaar> kaared = new ArrayList<>(); // sellest tipust väljuvate kaarte loetelu
        double x = 0; // abiväli täisarvu hoidmiseks
        int y = 0; // teine abiväli täisarvu hoidmiseks
        Tipp z = null; // abiväli tipu hoidmiseks

        public Tipp(String info) {
            this.info = info;
        }
    }

    static class Kaar {
        final Tipp alg; // kaare lähtetipp
        final Tipp lõpp; // kaare suubumistipp
        final double kaal; // kaare kaal (kui peaks vaja olema)

        public Kaar(Tipp alg, Tipp lõpp, double kaal) {
            this.alg = alg;
            this.lõpp = lõpp;
            this.kaal = kaal;
        }
    }

    public static void main(String[] args) throws IOException {
        NimedegaKoordinaadid omniva = loeKoordinaadid(new File("omniva.csv"));

        for (int i = 0; i < omniva.nimed.length; i++) {
            System.out.println(omniva.nimed[i] + ", vastavad koordinaadid: " + Arrays.toString(omniva.K[i]));
        }

        List<Tipp> omnivaGraaf = looGraaf(omniva);

        kuvaGraaf(omnivaGraaf, omniva.K);

        List<Tipp> omnivaToespuu = Prim(omnivaGraaf);

        kuvaGraaf(omnivaToespuu, omniva.K);

        System.out.println("Leitud toespuu kogukaal: " + servadeKogukaal(omnivaToespuu));
    }

    /**
     * Leiab kahe punkti vahelise kauguse kilomeetrites, kasutades valemit siit:
     * https://en.wikipedia.org/wiki/Haversine_formula
     */
    static double kaugus(double lai1, double pik1, double lai2, double pik2) {
        double dLaius = Math.pow(Math.sin(Math.toRadians(lai2 - lai1) / 2), 2);
        return 2 * 6371.0088 * Math.asin(Math.sqrt(dLaius +
                (1 - dLaius - Math.pow(Math.sin(Math.toRadians(lai1 + lai2) / 2), 2)) *
                        Math.pow(Math.sin(Math.toRadians(pik2 - pik1) / 2), 2)));
    }



    public static List<Tipp> looGraaf(NimedegaKoordinaadid koordinaadid) {
        List<Tipp> graaf = new ArrayList<>();
        for (String nimi : koordinaadid.nimed) {
            Tipp tipp = new Tipp(nimi);
            graaf.add(tipp);
        }

        for (int i = 0; i < koordinaadid.nimed.length; i++) {
            for (int j = i + 1; j < koordinaadid.nimed.length; j++) {
                Tipp tipp1 = graaf.get(i);
                Tipp tipp2 = graaf.get(j);
                double kaugus = kaugus(koordinaadid.K[i][0],koordinaadid.K[i][1],koordinaadid.K[j][0],koordinaadid.K[j][1]);
                Kaar kaar1 = new Kaar(tipp1,tipp2,kaugus);
                tipp1.kaared.add(kaar1);
                Kaar kaar2 = new Kaar(tipp2,tipp1,kaugus);
                tipp2.kaared.add(kaar2);
            }
        }
        return graaf;
    }

    public static double servadeKogukaal(List<Tipp> graaf) {
        puhastaAbiväljad(graaf);
        return servadeKogukaal(graaf,graaf.get(0));
    }

    public static double servadeKogukaal(List<Tipp> graaf, Tipp algtipp) {
        double kaal = 0;
        //Abiväli y peab meeles, kas oleme tipus juba käinud.
        algtipp.y = 1; //algtipus on käidud
        for (Kaar kaar : algtipp.kaared)
            if (kaar.lõpp.y == 0)
                kaal += kaar.kaal + servadeKogukaal(graaf,kaar.lõpp);

        return kaal;
    }

    public static void puhastaAbiväljad(List<Tipp> graaf) {
        for (Tipp tipp : graaf) {
            tipp.x = 0;
            tipp.y = 0;
            tipp.z = null;
        }
    }

    /* Primi algoritm: ------------------------------------------------------------------------------------------------
    -------------------------------------------------------------------------------------------------------------------
     */

    public static Tipp leiaTipp(List<Tipp> graaf, String nimi) {
        for (Tipp tipp : graaf)
            if (tipp.info.equals(nimi))
                return tipp;
        return null;
    }

    public static List<Tipp> Prim(List<Tipp> graaf) {
        puhastaAbiväljad(graaf);
        Tipp algustipp = graaf.get(0);
        List<Tipp> toespuu = new ArrayList<>();
        for (Tipp tipp : graaf)
            toespuu.add(new Tipp(tipp.info));

        //Loome eelistusjärjekorda, mis võrdleb tipu abivälja .x (sinna hakkame salvestama lühimat teed, mida mööda oleme
        // jõudnud antud tipuni parajasti vaatluse all olevast tipust)
        PriorityQueue<Tipp> Q = new PriorityQueue<>(Comparator.comparing(tipp -> tipp.x));

        /* Ülejäänud abiväljade tähendused:
        y - töö käik: y = 0 - pole antud tipuni jõudnud; y=2 - antud tipp on töödeldud; y=1 - antud tippu on kohatud, kuid teda pole veel töödeldud
        z - eellane, selles tähenduses, et tipust .x on praegu käesolevasse tippu lühim kaar
         */

        algustipp.x = 0;
        algustipp.z = null;
        Q.add(algustipp);
        algustipp.y = 1;

        while (!Q.isEmpty()) {
            Tipp vaadeldav = Q.remove();

            if (vaadeldav != algustipp) {
                Tipp vaadeldavToeses = leiaTipp(toespuu,vaadeldav.info);
                Tipp eellane = leiaTipp(toespuu,vaadeldav.z.info);
                Kaar kaar1 = new Kaar(vaadeldavToeses,eellane,vaadeldav.x);
                vaadeldavToeses.kaared.add(kaar1);
                Kaar kaar2 = new Kaar(eellane,vaadeldavToeses,vaadeldav.x);
                eellane.kaared.add(kaar2);
            }

            vaadeldav.y = 2;

            for (Kaar kaar : vaadeldav.kaared) {
                Tipp lõpptipp = kaar.lõpp;
                if (lõpptipp.y == 2)
                    continue;
                if (lõpptipp.y == 0) {
                    lõpptipp.x = kaar.kaal;
                    lõpptipp.z = vaadeldav;
                    Q.add(lõpptipp);
                    lõpptipp.y = 1;
                } else if (lõpptipp.y == 1) {
                    if (kaar.kaal < lõpptipp.x) {
                        Q.remove(lõpptipp);
                        lõpptipp.x = kaar.kaal;
                        lõpptipp.z = vaadeldav;
                        Q.add(lõpptipp);
                    }
                }
            }
        }


        return toespuu;
    }

    /*
    Mallis etteantud meetodid:-----------------------------------------------------------------------------------------
    -------------------------------------------------------------------------------------------------------------------
    -------------------------------------------------------------------------------------------------------------------
     */
    // Abimeetodid andmete lugemiseks failist:

    /**
     * Loeb TSV failist graafi külgnevusstruktuurina.
     * Tagastab külgnevusstruktuuri tippude järjendina.
     */
    static List<Tipp> loeKülgnevusstruktuur(File fail) throws IOException {
        List<String> read = Files.readAllLines(fail.toPath());
        Map<String, Tipp> tipud = new LinkedHashMap<>();
        for (String rida : read) {
            String[] väärtused = rida.split("\t");
            Tipp tipp = new Tipp(väärtused[0]);
            tipud.put(tipp.info, tipp);
        }
        for (String rida : read) {
            String[] väärtused = rida.split("\t");
            Tipp tipp = tipud.get(väärtused[0]);
            for (int i = 2; i < väärtused.length; i++)
                tipp.kaared.add(new Kaar(tipp, tipud.get(väärtused[i]), 1));
        }
        return new ArrayList<>(tipud.values());
    }

    /**
     * Loeb CSV failist tippude nimed ja koordinaadid.
     * Tagastab koordinaatide massiivi kujul [[laiuskraad_1, pikkuskraad_1], ..., [laiuskraad_n, pikkuskraad_n]]
     * ja nimede massiivi kujul [nimi_1, ..., nimi_n].
     * <p>
     * Mõeldud kasutamiseks failiga omniva.csv.
     */
    static NimedegaKoordinaadid loeKoordinaadid(File fail) throws IOException {
        List<String> read = Files.readAllLines(fail.toPath());
        String[] nimed = new String[read.size()];
        double[][] K = new double[read.size()][];
        for (int i = 0; i < read.size(); i++) {
            String[] väärtused = read.get(i).split(",");
            nimed[i] = väärtused[0];
            K[i] = new double[]{Double.parseDouble(väärtused[1]), Double.parseDouble(väärtused[2])};
        }
        return new NimedegaKoordinaadid(K, nimed);
    }

    static class NimedegaKoordinaadid {
        public final double[][] K; // koordinaatide massiiv (iga element on kujul [pikkuskraad, laiuskraad]).
        public final String[] nimed;

        public NimedegaKoordinaadid(double[][] K, String[] nimed) {
            this.K = K;
            this.nimed = nimed;
        }
    }

    // Abimeetodid graafide kuvamiseks dendroloj abil:

    /**
     * Kuvab külgnevusstruktuurina esitatud graafi.
     */
    static void kuvaGraaf(List<Tipp> tipud) {
        DecimalFormat df = new DecimalFormat("#.#", new DecimalFormatSymbols(Locale.ROOT));
        GraphCanvas<Tipp> lõuend = new GraphCanvas<>();
        for (Tipp tipp : tipud) {
            lõuend.drawVertex(tipp, tipp.info);
            for (Kaar kaar : tipp.kaared)
                lõuend.drawDirectedEdge(kaar.alg, kaar.lõpp, df.format(kaar.kaal));
        }
        Dendrologist.drawGraph(lõuend);
    }

    /**
     * Kuvab külgnevusstruktuurina esitatud graafi, kus tippude asukohad on määratud koordinaatide massiivis K olevate koordinaatidega.
     * <p>
     * NB: See meetod eeldab, et koordinaatide massiivis K ja tipude järjendis on tipud samas järjestuses!
     */
    static void kuvaGraaf(List<Tipp> tipud, double[][] K) {
        DecimalFormat df = new DecimalFormat("#.#", new DecimalFormatSymbols(Locale.ROOT));
        GraphCanvas<Tipp> lõuend = new GraphCanvas<>();
        for (int i = 0; i < tipud.size(); i++) {
            // Pikkuskraadi cos(60°) = 0.5-ga korrutamine vähendab oluliselt moonutust (vt https://et.wikipedia.org/wiki/Ruutlabaprojektsioon#Valemid)
            lõuend.drawFixedVertex(tipud.get(i), tipud.get(i).info.split(" ")[0], K[i][1] * 0.5, K[i][0]);
        }
        for (Tipp tipp : tipud) {
            for (Kaar kaar : tipp.kaared)
                lõuend.drawDirectedEdge(kaar.alg, kaar.lõpp, df.format(kaar.kaal));
        }
        Dendrologist.drawGraph(lõuend);
    }

}