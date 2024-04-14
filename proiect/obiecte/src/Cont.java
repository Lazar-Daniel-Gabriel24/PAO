import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cont extends ServiciuFinanciar {

    private Utilizator utilizator;
    private String numarCont;
    private double sold;

    public Cont(Utilizator utilizator, String numarCont, double sold, String nume, String descriere, double cost, String serviceType) {
        super(nume, descriere, cost, serviceType);
        this.utilizator = utilizator;
        this.numarCont = numarCont;
        this.sold = sold;
    }


    public Utilizator getUtilizator() {
        return utilizator;
    }

    public String getNumarCont() {
        return numarCont;
    }

    public double getSold() {
        return sold;
    }
    public void setSold(double sold) {
        if (sold < 0) {
            try {
                throw new InsufficientBalanceException("Insufficient balance"); // Throw the exception here
            } catch (InsufficientBalanceException e) {
                throw new RuntimeException(e);
            }
        }
        this.sold = sold;
    }


    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
    }

    public void setNumarCont(String numarCont) {
        this.numarCont = numarCont;
    }
    public CardBancar createCard() {
        String cardNumber = generateUniqueCardNumber();
        Date expirationDate = calculateExpirationDate();
        int securityCode = generateSecurityCode();

        CardBancar card = new CardBancar(this, cardNumber, expirationDate, securityCode);

        return card;
    }

    private String generateUniqueCardNumber() {
        return "1234567890123456";
    }

    private Date calculateExpirationDate() {
        return new Date();
    }
    public void addTransaction(Tranzactie tranzactie) {
        istoricTranzactii.add(tranzactie);
    }
    private int generateSecurityCode() {
        return 123;
    }
    public List<Tranzactie> getTranzactii() {
        return istoricTranzactii;
    }
    private List<Tranzactie> istoricTranzactii = new ArrayList<>();

    public void transfer(Cont contDestinatie, double suma) throws InsufficientBalanceException {
        if (sold >= suma) {
            sold -= suma;
            contDestinatie.sold += suma;

            Date currentDate = new Date();
            Tranzactie tranzactieDebit = new Tranzactie(this, contDestinatie, suma, currentDate, "Debit", "Transfer to account " + contDestinatie.getNumarCont());
            Tranzactie tranzactieCredit = new Tranzactie(contDestinatie, this, suma, currentDate, "Credit", "Transfer from account " + this.getNumarCont());

            istoricTranzactii.add(tranzactieDebit);
            contDestinatie.istoricTranzactii.add(tranzactieCredit);

        } else {
            throw new InsufficientBalanceException("Sold insufficient for transfer");
        }
    }

    public List<Tranzactie> getIstoricTranzactii() {
        return istoricTranzactii;
    }
}
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
