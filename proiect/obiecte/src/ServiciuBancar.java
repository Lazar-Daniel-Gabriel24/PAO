import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ServiciuBancar {
    private static ServiciuBancar instance = null;

    private Utilizator utilizator = null;


    private ServiciuBancar() {
    }
    private HashMap<String, Utilizator> userDataMap = new HashMap<>();

    public static ServiciuBancar getInstance() {
        if (instance == null) {
            instance = new ServiciuBancar();
        }
        return instance;
    }

    private void afiseazaUtilizator() {
        if (this.utilizator != null) {
            System.out.println(this.utilizator);
            LocalDateTime localDateTime = LocalDateTime.now();
        } else {
            System.out.println("Niciun utilizator conectat!");
        }
    }
    private Utilizator findUtilizator(String numeUtilizator, String parola) {

        if (userDataMap.containsKey(numeUtilizator)) {
            Utilizator utilizator = userDataMap.get(numeUtilizator);


            if (utilizator.checkPassword(parola)) {
                return utilizator;
            } else {
                System.out.println("Parola introdusă este incorectă!");
                return null;
            }
        } else {
            System.out.println("Numele de utilizator nu a fost găsit!");
            return null;
        }
    }

    private void autentificare(String numeUtilizator, String parola) {
        this.utilizator = this.findUtilizator(numeUtilizator, parola);

        if (this.utilizator == null) {
            System.out.println("Numele de utilizator sau parola au fost introduse greșit, încercați din nou!");
        } else {
            System.out.println("Autentificare realizată cu succes!");
        }
    }


    private void deconectare() {
        this.utilizator = null;
        System.out.println("Deconectare reușită!");
    }

    private void transferBancar(Cont contSursa, Cont contDestinatie, double suma) throws InsufficientBalanceException {
        if (contSursa.getSold() >= suma) {
            contSursa.transfer(contDestinatie, suma);
            System.out.println("Transfer de " + suma + " RON efectuat cu succes de la contul " + contSursa.getNumarCont() + " către contul " + contDestinatie.getNumarCont());
        } else {
            throw new InsufficientBalanceException("Sold insuficient în contul sursă pentru transferul de " + suma + " RON");
        }
    }

    private void verificareSold(Cont cont) {
        System.out.println("Soldul curent al contului " + cont.getNumarCont() + " este: " + cont.getSold() + " RON");
    }

    private void schimbareParola(String parolaVeche, String parolaNoua) {
        this.utilizator.schimbaParola(parolaVeche, parolaNoua);
    }


    public void meniu() {
        Scanner scanner = new Scanner(System.in);
        int comanda;

        while (true) {
            System.out.println("Apăsați tastele:\n" +
                    "  1. Autentificare\n" +
                    "  2. Deconectare\n" +
                    "  3. Transfer bancar\n" +
                    "  4. Verificare sold\n" +
                    "  5. Schimbare parolă\n" +
                    "  6. Afișare raport financiar\n" +
                    "  7. Ieșire");

            System.out.print("Comandă: ");
            comanda = scanner.nextInt();

            switch (comanda) {
                case 1:
                    System.out.print("Introduceți numele de utilizator: ");
                    String numeUtilizator = scanner.next();
                    System.out.print("Introduceți parola: ");
                    String parola = scanner.next();
                    autentificare(numeUtilizator, parola);
                    break;
                case 2:
                    deconectare();
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:
                    System.out.println("La revedere!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Comandă invalidă!");
                    break;
            }
        }
    }
}
