import java.util.ArrayList;
import java.util.List;

public class Tipp {
        final String info; // tipu info
        final List<Kaar> kaared = new ArrayList<>(); // sellest tipust väljuvate kaarte loetelu
        int x = 0; // abiväli täisarvu hoidmiseks
        int y = 0; // teine abiväli täisarvu hoidmiseks
        Tipp z = null; // abiväli tipu hoidmiseks

        public Tipp(String info) {
            this.info = info;
        }

}
