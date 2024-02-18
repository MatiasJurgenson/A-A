/*****************************************************************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2023/2024 sügissemester
 *
 * Kodutöö. Ülesanne nr 8
 * Teema: Toespuud
 *
 * muudetud kaare klass, saadut praktikum14.java-st
 *
 *****************************************************************************/
public class Kaar {
    final int alg; // kaare lähtetipp, asendatud tippu klassi asemel numbriga
    final int lõpp; // kaare suubumistipp, asendatud tippu klassi asemel numbriga
    final double kaal; // kaare kaal (kui peaks vaja olema)

    public Kaar(int alg, int lõpp, double kaal) {
        this.alg = alg;
        this.lõpp = lõpp;
        this.kaal = kaal;
    }
}
