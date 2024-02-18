import java.io.File;

public class yl8 {
    public static Praktikum12.NimedegaNaabrusmaatriks naabrusKitsendustega(File file, int x) throws Exception{
        Praktikum12.NimedegaNaabrusmaatriks a = Praktikum12.loeNaabrusmaatriks(file);

        for (int i = 0; i < a.M.length; i++) {
            for (int j = 0; j < a.M.length; j++) {
                if (a.M[i][j] > x) a.M[i][j] = -1;
            }
        }

        return a;
    }
}
