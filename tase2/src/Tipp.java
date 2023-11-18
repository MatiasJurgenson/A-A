class Tipp {
    int info;
    Tipp v;
    Tipp p;
    int abi; // abiväli

    Tipp(int info, Tipp v, Tipp p) {
        this.info = info;
        this.v = v;
        this.p = p;
        this.abi = abi;
    }

    Tipp(int info) {
        this.info = info;
    }
}