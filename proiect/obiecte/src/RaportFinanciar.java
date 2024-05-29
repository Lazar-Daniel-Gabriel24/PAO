import java.util.Date;
import java.util.List;

public class RaportFinanciar {
    private Cont cont;
    private Date dataInceput;
    private Date dataSfarsit;
    private String continut;
    private String tipRaport;

    public RaportFinanciar(Cont cont, Date dataInceput, Date dataSfarsit, String continut, String tipRaport) {
        this.cont = cont;
        this.dataInceput = dataInceput;
        this.dataSfarsit = dataSfarsit;
        this.continut = continut;
        this.tipRaport = tipRaport;
    }

    public Cont getCont() {
        return cont;
    }

    public void setCont(Cont cont) {
        this.cont = cont;
    }

    public Date getDataInceput() {
        return dataInceput;
    }

    public void setDataInceput(Date dataInceput) {
        this.dataInceput = dataInceput;
    }

    public Date getDataSfarsit() {
        return dataSfarsit;
    }

    public void setDataSfarsit(Date dataSfarsit) {
        this.dataSfarsit = dataSfarsit;
    }

    public String getContinut() {
        return continut;
    }

    public void setContinut(String continut) {
        this.continut = continut;
    }

    public String getTipRaport() {
        return tipRaport;
    }

    public void setTipRaport(String tipRaport) {
        this.tipRaport = tipRaport;
    }

    public String genereazaRaport() {
        switch (tipRaport) {
            case "sold cont":
                return genereazaRaportSoldCont();
            case "istoric tranzactii":
                return genereazaRaportIstoricTranzactii();
            default:
                return "Tip raport invalid";
        }
    }

    private String genereazaRaportSoldCont() {
        double sold = cont.getSold();
        String solduFormatat = String.format("%.2f", sold);
        return "Sold cont: " + solduFormatat;
    }

    private String genereazaRaportIstoricTranzactii() {
        List<Tranzactie> tranzactii = cont.getTranzactii();
        StringBuilder raport = new StringBuilder();
        for (Tranzactie tranzactie : tranzactii) {
            raport.append(tranzactie.toString()).append("\n");
        }
        return raport.toString();
    }
}