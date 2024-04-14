public class CerereImprumut {
    private Utilizator utilizator;
    private double suma;
    private String scop;

    public CerereImprumut(Utilizator utilizator, double suma, String scop) {
        this.utilizator = utilizator;
        this.suma = suma;
        this.scop = scop;
    }

    public Utilizator getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public String getScop() {
        return scop;
    }

    public void setScop(String scop) {
        this.scop = scop;
    }
}
