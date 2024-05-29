import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiciuFinanciar {

    private String nume;
    private String descriere;
    protected double cost;

    private String serviceType;

    public String getServiceType() {
        return serviceType;
    }

    public ServiciuFinanciar(String nume, String descriere, double cost, String serviceType) {
        this.nume = nume;
        this.descriere = descriere;
        this.cost = cost;
        this.serviceType = serviceType;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void aplica(Cont cont) throws InsufficientBalanceException {
        if (cont.getSold() >= this.cost) {
            cont.setSold(cont.getSold() - this.cost);

            cont.addTransaction(new Tranzactie(this, cont, this.cost, new Date(), "Debit", this.getNume()));
        } else {
            throw new InsufficientBalanceException("Insufficient balance to apply service: " + this.getNume());
        }
    }

    public double calculeazaCost(double suma) {
        String serviceType = this.getServiceType();

        if (serviceType.equals("fixed")) {
            return this.cost;
        } else if (serviceType.equals("percentage")) {
            return suma * this.cost / 100;
        } else {
            throw new IllegalArgumentException("Invalid service type: " + serviceType);
        }
    }

}

