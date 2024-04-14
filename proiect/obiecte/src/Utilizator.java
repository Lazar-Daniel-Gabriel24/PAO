import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Utilizator {

    private String nume;
    private String prenume;
    private String CNP;
    private String adresa;
    private String email;
    private String parola;
    private List<Cont> conturi = new ArrayList<>();
    private Map<Utilizator, DispozitivAutentificare> dispozitiveAutentificareMap = new TreeMap<>();

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

    public String getAdresa() {
        return adresa;
    }

    public String getEmail() {
        return email;
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
    }
    public void schimbaParola(String parolaVeche, String parolaNoua) {
        // Verify the old password before allowing the change
        if (checkPassword(parolaVeche)) {
            this.parola = parolaNoua;
            System.out.println("Parola a fost schimbată cu succes!");
        } else {
            System.out.println("Parola veche introdusă este incorectă!");
        }
    }
    public void adaugaDispozitivAutentificare(DispozitivAutentificare dispozitiv) {
        dispozitiveAutentificareMap.put(this, dispozitiv);
    }

    public DispozitivAutentificare getDispozitivAutentificare() {
        return dispozitiveAutentificareMap.get(this);
    }
}