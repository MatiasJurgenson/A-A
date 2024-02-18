import java.io.File;
import java.util.Arrays;
import java.util.List;

public class yl9 {

    public static void main(String[] args) throws Exception{
        Praktikum12.NimedegaNaabrusmaatriks m = Praktikum12.loeNaabrusmaatriks(new File("täht.tsv"));
        Praktikum12.kuvaGraaf(m.M, m.nimed);

        int[][] kaugused = floydWarshall(m.M);
        Praktikum12.kuvaGraaf(kaugused, m.nimed);

        for (int[] massiiv : kaugused) {
            System.out.println(Arrays.toString(massiiv));
        }
    }
    public static int[][] floydWarshall(int[][] kaugused) {
        int[][] koopia = new int[kaugused.length][kaugused.length];
        for (int i = 0; i < kaugused.length; i++) {
            System.arraycopy(kaugused[i], 0, koopia[i], 0, kaugused.length);
        }

        //keskmine tipp
        for (int k = 0; k < kaugused.length; k++) {
            //esimene tipp
            for (int i = 0; i < kaugused.length; i++) {
                //teine tipp
                for (int j = 0; j < kaugused.length; j++) {
                    if (koopia[i][k] == -1 || koopia[k][j] == -1) continue;
                    if (koopia[i][k] + koopia[k][j] < koopia[i][j] || koopia[i][j] == -1)
                        koopia[i][j] = koopia[i][k] + koopia[k][j];
                }
            }
        }
        
        return koopia;
    }

    public static void prindi (int[][] kaugused, String[] nimed, String esimene, String teine) {
        
    }
}
