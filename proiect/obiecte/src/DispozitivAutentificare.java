public class DispozitivAutentificare {

    private Utilizator utilizator;
    private String codAcces;

    public DispozitivAutentificare(Utilizator utilizator, String codAcces) {
        this.utilizator = utilizator;
        this.codAcces = codAcces;
    }

    public Utilizator getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
    }

    public String getCodAcces() {
        return codAcces;
    }

    public void setCodAcces(String codAcces) {
        this.codAcces = codAcces;
    }

    public boolean autentificare(String codAccesIntrodus) {
        return codAccesIntrodus.equals(codAcces);
    }

    public void schimbaCodAcces(String codAccesNou) {
        this.codAcces = codAccesNou;
    }
}
