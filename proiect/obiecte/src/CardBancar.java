import java.util.Date;

public class CardBancar {

    private Cont cont;
    private String numarCard;
    private Date dataExpirare;
    private int codSecuritate;


    public CardBancar(Cont cont, String numarCard, Date dataExpirare, int codSecuritate) {
        this.cont = cont;
        this.numarCard = numarCard;
        this.dataExpirare = dataExpirare;
        this.codSecuritate = codSecuritate;
    }

    public Cont getCont() {
        return cont;
    }

    public void setCont(Cont cont) {
        this.cont = cont;
    }

    public String getNumarCard() {
        return numarCard;
    }

    public void setNumarCard(String numarCard) {
        this.numarCard = numarCard;
    }

    public Date getDataExpirare() {
        return dataExpirare;
    }

    public void setDataExpirare(Date dataExpirare) {
        this.dataExpirare = dataExpirare;
    }

    public int getCodSecuritate() {
        return codSecuritate;
    }

    public void setCodSecuritate(int codSecuritate) {
        this.codSecuritate = codSecuritate;
    }


    public double verificaSold() {
        return cont.getSold();
    }

    public void efectuaPlata(double suma) throws InsufficientBalanceException {
        if (verificaSold() >= suma) {
            cont.transfer(null, suma);
            System.out.println("Plata de " + suma + " a fost efectuata cu succes.");
        } else {
            throw new InsufficientBalanceException("Sold insuficient pentru plata de " + suma);
        }
    }
}
