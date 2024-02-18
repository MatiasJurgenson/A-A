/*****************************************************************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2023/2024 sügissemester
 *
 * Kodutöö. Ülesanne nr 8
 * Teema: Toespuud
 *
 * muudetud tippu klass, saadut praktikum14.java-st
 *
 *****************************************************************************/
import java.util.ArrayList;
import java.util.List;

public class Tipp {
    final int info; // tipu info, sõne on asentaud numbriga
    final List<Kaar> kaared = new ArrayList<>(); // sellest tipust väljuvate kaarte loetelu
    int x = 0; // abiväli täisarvu hoidmiseks
    int y = 0; // teine abiväli täisarvu hoidmiseks
    Tipp z = null; // abiväli tipu hoidmiseks

    public Tipp(int info) {
        this.info = info;
    }
}
