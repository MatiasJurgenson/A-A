import ee.ut.dendroloj.Dendrologist;
import ee.ut.dendroloj.GraphCanvas;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Praktikum12 {

    static class Tipp {
        final String info; // tipu info
        final List<Kaar> kaared = new ArrayList<>(); // sellest tipust väljuvate kaarte loetelu
        int x = 0; // abiväli täisarvu hoidmiseks
        int y = 0; // teine abiväli täisarvu hoidmiseks
        Tipp z = null; // abiväli tipu hoidmiseks

        public Tipp(String info) {
            this.info = info;
        }
    }

    static class Kaar {
        final Tipp alg; // kaare lähtetipp
        final Tipp lõpp; // kaare suubumistipp
        final int kaal; // kaare kaal (kui peaks vaja olema)

        public Kaar(Tipp alg, Tipp lõpp, int kaal) {
            this.alg = alg;
            this.lõpp = lõpp;
            this.kaal = kaal;
        }
    }

    public static void main(String[] args) throws IOException {
        List<Tipp> tipud = loeKülgnevusstruktuur(new File("õis_andmed.tsv"));
        Dendrologist.setUIScale(0.8);
        kuvaGraaf(tipud);
    }

    // Abimeetodid andmete lugemiseks

    static List<Tipp> loeKülgnevusstruktuur(File fail) throws IOException {
        List<String> read = Files.readAllLines(fail.toPath());
        Map<String, Tipp> tipud = new HashMap<>();
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

    static NimedegaNaabrusmaatriks loeNaabrusmaatriks(File fail) throws IOException {
        List<String> read = Files.readAllLines(fail.toPath());
        // Loeme esimeselt realt nimed
        String[] nimed = read.get(0).split("\t");
        // Loeme naabrusmaatriksi
        int n = read.size() - 1;
        int[][] M = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] väärtused = read.get(i + 1).split("\t");
            for (int j = 0; j < väärtused.length; j++)
                M[i][j] = Integer.parseInt(väärtused[j]);
        }
        return new NimedegaNaabrusmaatriks(M, nimed);
    }

    static class NimedegaNaabrusmaatriks {
        final int[][] M;
        final String[] nimed;

        NimedegaNaabrusmaatriks(int[][] M, String[] nimed) {
            this.M = M;
            this.nimed = nimed;
        }
    }

    // Abimeetodid graafide kuvamiseks dendroloj abil

    static void kuvaGraaf(List<Tipp> tipud) {
        GraphCanvas<Tipp> lõuend = new GraphCanvas<>();
        for (Tipp tipp : tipud) {
            lõuend.drawVertex(tipp, tipp.info);
            for (Kaar kaar : tipp.kaared)
                lõuend.drawDirectedEdge(kaar.alg, kaar.lõpp, Integer.toString(kaar.kaal));
        }
        Dendrologist.drawGraph(lõuend);
    }

    static void kuvaGraaf(int[][] M, String[] nimed) {
        // Märkus: See meetod eeldab ilma kontrollimata, et M on ruudukujuline.
        GraphCanvas<Integer> lõuend = new GraphCanvas<>();
        for (int i = 0; i < M.length; i++)
            lõuend.drawVertex(i, nimed[i]);
        for (int i = 0; i < M.length; i++)
            for (int j = i + 1; j < M.length; j++)
                if (M[i][j] == M[j][i]) {
                    // Naabrusmaatriks on sümmeetriline
                    if (M[i][j] > -1) lõuend.drawEdge(i, j, Integer.toString(M[i][j]));
                } else {
                    // Naabrusmaatriks ei ole sümmeetriline
                    if (M[i][j] > -1) lõuend.drawDirectedEdge(i, j, Integer.toString(M[i][j]));
                    if (M[j][i] > -1) lõuend.drawDirectedEdge(j, i, Integer.toString(M[j][i]));
                }
        Dendrologist.drawGraph(lõuend);
    }

}
