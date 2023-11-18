class Isik {
    public final int ID;
    public final String nimi;
    public final int palk;
    public final int vanus;

    public Isik(int ID, String nimi, int palk, int vanus) {
        this.ID = ID;
        this.nimi = nimi;
        this.palk = palk;
        this.vanus = vanus;
    }

    @Override
    public String toString() {
        return "Isik nr. " + ID + " " + nimi;
    }
}
