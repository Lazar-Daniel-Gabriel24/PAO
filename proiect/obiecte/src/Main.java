import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
public class Main {

    public static void main(String[] args) {
        Utilizator utilizator1 = new Utilizator("John", "Doe", "123456789", "Str. Exemplu 1", "john@example.com", "parola1");
        Utilizator utilizator2 = new Utilizator("Alice", "Smith", "987654321", "Str. Test 2", "alice@example.com", "parola2");

        Cont cont1 = new Cont(utilizator1, "RO123456", 1000.0, "Cont curent", "Cont curent pentru tranzacții zilnice", 0.0, "contStandard");
        Cont cont2 = new Cont(utilizator2, "RO654321", 500.0, "Cont economii", "Cont pentru economii", 0.0, "contEconomii");

        utilizator1.getConturi().add(cont1);
        utilizator2.getConturi().add(cont2);

        List<Utilizator> utilizatori = new ArrayList<>();
        utilizatori.add(utilizator1);
        utilizatori.add(utilizator2);

        Map<Utilizator, String> dispozitiveAutentificare = new HashMap<>();
        dispozitiveAutentificare.put(utilizator1, "codAcces1");
        dispozitiveAutentificare.put(utilizator2, "codAcces2");

        for (Utilizator utilizator : utilizatori) {
            System.out.println("Utilizator: " + utilizator.getNume() + " " + utilizator.getPrenume());
            for (Cont cont : utilizator.getConturi()) {
                System.out.println("  Cont: " + cont.getNumarCont() + " - Sold: " + cont.getSold());
            }
        }

        String codAcces = "codAcces1";
        if (dispozitiveAutentificare.containsKey(utilizator1)) {
            String codUtilizator = dispozitiveAutentificare.get(utilizator1);
            if (codAcces.equals(codUtilizator)) {
                System.out.println("Autentificare reușită pentru " + utilizator1.getNume());
            } else {
                System.out.println("Autentificare eșuată pentru " + utilizator1.getNume());
            }
        }

        ServiciuFinanciar serviciu1 = new ServiciuFinanciar("Serviciu 1", "Descriere 1", 10.0, "fixed");
        ServiciuFinanciar serviciu2 = new ServiciuFinanciar("Serviciu 2", "Descriere 2", 5.0, "percentage");

        List<Cont> conturi = new ArrayList<>();
        conturi.add(cont1);
        conturi.add(cont2);

        try {
            serviciu1.aplica(cont1);
            serviciu2.aplica(cont2);
            double cost1 = serviciu1.calculeazaCost(100.0);
            double cost2 = serviciu2.calculeazaCost(200.0);
            System.out.println("Cost serviciu 1 pentru suma 100: " + cost1);

            System.out.println("Cost serviciu 2 pentru suma 200: " + cost2);

            genereazaRaportFinanciar(utilizatori, conturi);

            CerereImprumut cerere1 = new CerereImprumut(utilizator1, 500.0, "Scop imprumut 1");
            CerereImprumut cerere2 = new CerereImprumut(utilizator2, 1000.0, "Scop imprumut 2");

            System.out.println("\n**Simulare procesare cereri de împrumut (limitată fără bază de date):**");
            if (cerere1.getUtilizator().getConturi().stream().findFirst().isPresent()) {
                Cont contSursa = cerere1.getUtilizator().getConturi().stream().findFirst().get();
                if (contSursa.getSold() >= cerere1.getSuma()) {
                    System.out.println("Împrumut aprobat pentru " + cerere1.getUtilizator().getNume());
                    contSursa.setSold(contSursa.getSold() - cerere1.getSuma());
                } else {
                    System.out.println("Împrumut respins pentru " + cerere1.getUtilizator().getNume() + ": Fonduri insuficiente");
                }
            } else {
                System.out.println("Eroare: Utilizatorul " + cerere1.getUtilizator().getNume() + " nu are conturi asociate.");
            }

            if (cerere2.getUtilizator().getConturi().stream().findFirst().isPresent()) {
                Cont contSursa = cerere2.getUtilizator().getConturi().stream().findFirst().get();
                if (contSursa.getSold() >= cerere2.getSuma()) {
                    System.out.println("Împrumut aprobat pentru " + cerere2.getUtilizator().getNume());
                    contSursa.setSold(contSursa.getSold() - cerere2.getSuma());
                } else {
                    System.out.println("Împrumut respins pentru " + cerere2.getUtilizator().getNume() + ": Fonduri insuficiente");
                }
            } else {
                System.out.println("Eroare: Utilizatorul " + cerere2.getUtilizator().getNume() + " nu are conturi asociate.");
            }

            gestioneazaCarduriTranzactii(utilizatori, conturi);

        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void genereazaRaportFinanciar(List<Utilizator> utilizatori, List<Cont> conturi) {
        System.out.println("\n**Raport Financiar**");
        System.out.println(" conturile utilizatorilor:");
        for (Cont cont : conturi) {
            System.out.println("  - " + cont.getNumarCont() + " - Titular: " + cont.getNume() + " - Sold: " + cont.getSold());
        }
    }

    private static void gestioneazaCarduriTranzactii(List<Utilizator> utilizatori, List<Cont> conturi) {

        System.out.println("\n**Simulare efectuare tranzacții:**");
        try {
            Optional<Cont> contSursaOptional = conturi.stream()
                    .filter(c -> c.getNume().equals("John"))
                    .findFirst();

            Optional<Cont> contDestinatieOptional = conturi.stream()
                    .filter(c -> c.getNume().equals("Alice"))
                    .findFirst();

            if (contSursaOptional.isPresent() && contDestinatieOptional.isPresent()) {
                Cont contSursa = contSursaOptional.get();
                Cont contDestinatie = contDestinatieOptional.get();
                efectueazaTranzactie(contSursa, contDestinatie, 100.0);
                for (Cont cont : conturi) {
                    System.out.println("Cont: " + cont.getNumarCont() + " - Sold: " + cont.getSold());
                }
            } else {
                System.out.println("Eroare: Contul sursă sau cel de destinație nu a fost găsit.");
            }
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void efectueazaTranzactie(Cont contSursa, Cont contDestinatie, double suma) throws InsufficientBalanceException {
        if (contSursa.getSold() >= suma) {
            contSursa.setSold(contSursa.getSold() - suma);
            contDestinatie.setSold(contDestinatie.getSold() + suma);
            System.out.println("Tranzacție efectuată cu succes: " + suma + " RON transferați de la " + contSursa.getNumarCont() + " la " + contDestinatie.getNumarCont());
        } else {
            throw new InsufficientBalanceException("Fonduri insuficiente în contul sursă");
        }
    }
}
