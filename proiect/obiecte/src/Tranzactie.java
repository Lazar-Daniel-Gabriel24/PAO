import java.util.Date;

public class Tranzactie {

    private Cont contSursa;
    private Cont contDestinatie;
    private double suma;
    private Date data;
    private String tipTranzactie;
    private String descriere;

    public Tranzactie(ServiciuFinanciar serviciuFinanciar, Cont contDestinatie, double suma, Date data, String tipTranzactie, String descriere) {
        this.contSursa = null;
        this.contDestinatie = contDestinatie;
        this.suma = suma;
        this.data = data;
        this.tipTranzactie = tipTranzactie;
        this.descriere = descriere;
    }

    public Cont getContSursa() {
        return contSursa;
    }

    public void setContSursa(Cont contSursa) {
        this.contSursa = contSursa;
    }

    public Cont getContDestinatie() {
        return contDestinatie;
    }

    public void setContDestinatie(Cont contDestinatie) {
        this.contDestinatie = contDestinatie;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTipTranzactie() {
        return tipTranzactie;
    }

    public void setTipTranzactie(String tipTranzactie) {
        this.tipTranzactie = tipTranzactie;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public void printDetails() {
        System.out.println("Tranzactie:");
        System.out.println("Cont sursa: " + contSursa.getNumarCont());
        System.out.println("Cont destinatie: " + contDestinatie.getNumarCont());
        System.out.println("Suma: " + suma);
        System.out.println("Data: " + data);
        System.out.println("Tip tranzactie: " + tipTranzactie);
        System.out.println("Descriere: " + descriere);
    }
}
