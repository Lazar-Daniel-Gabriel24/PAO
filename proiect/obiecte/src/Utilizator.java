import java.text.SimpleDateFormat;
import java.util.*;

public class Utilizator {
    private String nume;
    private String prenume;
    private String CNP;
    private String adresa;
    private String email;
    private String parola;
    private List<Cont> conturi = new ArrayList<>();
    private List<CardBancar> carduri = new ArrayList<>();
    private DispozitivAutentificare dispozitivAutentificare;

    public Utilizator(String nume, String prenume, String CNP, String adresa, String email, String parola) {
        this.nume = nume;
        this.prenume = prenume;
        this.CNP = CNP;
        this.adresa = adresa;
        this.email = email;
        this.parola = parola;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getCNP() {
        return CNP;
    }

    public String getParola() {
        return parola;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getEmail() {
        return email;
    }

    public Cont getContByNumber(String numarCont) {
        for (Cont cont : conturi) {
            if (cont.getNumarCont().equals(numarCont)) {
                return cont;
            }
        }
        return null;
    }

    public boolean checkPassword(String parolaIntrodusa) {
        return parola.equals(parolaIntrodusa);
    }

    public void updateDetaliiPersonale(String numeNou, String prenumeNou, String adresaNoua, String emailNou) {
        this.nume = numeNou;
        this.prenume = prenumeNou;
        this.adresa = adresaNoua;
        this.email = emailNou;
    }

    public List<Cont> getConturi() {
        return conturi;
    }

    public List<CardBancar> getCarduri() {
        return carduri;
    }

    public void adaugaContSortat(Cont cont) {
        for (Cont existingCont : conturi) {
            if (existingCont.getNumarCont().equals(cont.getNumarCont())) {
                return;
            }
        }
        conturi.add(cont);
        conturi.sort((c1, c2) -> c1.getNumarCont().compareTo(c2.getNumarCont()));
    }

    public void adaugaCardBancar(CardBancar card) {
        Cont contAsociat = card.getCont();
        contAsociat.setUtilizator(this);
        adaugaContSortat(contAsociat);
        carduri.add(card);
    }

    public boolean schimbaParola(String parolaVeche, String parolaNoua) {
        if (checkPassword(parolaVeche)) {
            this.parola = parolaNoua;
            System.out.println("Parola a fost schimbată cu succes!");
            return true;
        } else {
            System.out.println("Parola veche introdusă este incorectă!");
            return false;
        }
    }

    public void adaugaDispozitivAutentificare(DispozitivAutentificare dispozitiv) {
        this.dispozitivAutentificare = dispozitiv;
    }

    public DispozitivAutentificare getDispozitivAutentificare() {
        return dispozitivAutentificare;
    }

    public void getRaportFinanciar(Date startDate, Date endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Raport financiar de la " + sdf.format(startDate) + " până la " + sdf.format(endDate));
        // Implementați logica de generare a raportului financiar
    }

    public void cereImprumut(double suma, int luni) {
        System.out.println("Solicitat un împrumut de " + suma + " RON pentru " + luni + " luni.");
        // Implementați logica de cerere a împrumutului
    }

    public void autentificareCuDispozitiv(String cod) {
        if (dispozitivAutentificare != null && dispozitivAutentificare.autentificare(cod)) {
            System.out.println("Autentificare cu dispozitiv realizată cu succes.");
        } else {
            System.out.println("Codul de autentificare este incorect.");
        }
    }


    private String generateUniqueCardNumber() {
        // Generarea unui număr de card bancar unic
        StringBuilder cardNumberBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            int digit = random.nextInt(10);
            cardNumberBuilder.append(digit);
        }
        return cardNumberBuilder.toString();
    }

    private Date calculateExpirationDate() {
        // Calcularea datei de expirare a cardului (de exemplu, 3 ani în viitor)
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 3); // Adăugăm 3 ani la data curentă

        return calendar.getTime();
    }

    private int generateSecurityCode() {
        // Generarea unui cod de securitate pentru card (de exemplu, un număr aleatoriu de 3 cifre)
        Random random = new Random();
        return 100 + random.nextInt(900); // Număr între 100 și 999
    }

    public CardBancar createCard(Cont cont) {
        // Crearea unui obiect de tip CardBancar cu ajutorul metodelor de generare
        String cardNumber = generateUniqueCardNumber();
        Date expirationDate = calculateExpirationDate();
        int securityCode = generateSecurityCode();

        CardBancar card = new CardBancar(cont, cardNumber, expirationDate, securityCode);

        return card;
    }
}
