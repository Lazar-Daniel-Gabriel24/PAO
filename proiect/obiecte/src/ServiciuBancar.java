import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class ServiciuBancar {
    private static ServiciuBancar instance = null;
    private Utilizator utilizator = null;
    private HashMap<String, Utilizator> userDataMap = new HashMap<>();

    private ServiciuBancar() {
    }

    public static ServiciuBancar getInstance() {
        if (instance == null) {
            instance = new ServiciuBancar();
        }
        return instance;
    }

    public void inregistreazaUtilizator(Utilizator utilizator) {
        userDataMap.put(utilizator.getEmail(), utilizator);
    }

    private Utilizator findUtilizator(String email, String parola) {
        if (userDataMap.containsKey(email)) {
            Utilizator utilizator = userDataMap.get(email);
            if (utilizator.checkPassword(parola)) {
                return utilizator;
            } else {
                System.out.println("Parola introdusă este incorectă!");
                return null;
            }
        } else {
            System.out.println("Email-ul introdus nu a fost găsit!");
            return null;
        }
    }

    protected void autentificare(String email, String parola) {
        utilizator = findUtilizator(email, parola);
        if (utilizator != null) {
            System.out.println("Autentificare cu succes!");
        } else {
            System.out.println("Autentificare eșuată!");
        }
    }

    public void meniu() {
        if (utilizator == null) {
            System.out.println("Nu sunteți autentificat.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int optiune;

        do {
            System.out.println("Meniu:");
            System.out.println("1. Cere raport financiar");
            System.out.println("2. Cere împrumut");
            System.out.println("3. Autentificare cu dispozitiv");
            System.out.println("4. Adaugă card bancar");
            System.out.println("5. Schimbă parola");
            System.out.println("0. Deconectare");
            System.out.print("Alegeți o opțiune: ");
            optiune = scanner.nextInt();

            switch (optiune) {
                case 1:
                    cereRaportFinanciar();
                    break;
                case 2:
                    cereImprumut();
                    break;
                case 3:
                    autentificareCuDispozitiv();
                    break;
                case 4:
                    adaugaCardBancar();
                    break;
                case 5:
                    schimbaParola();
                    break;
                case 0:
                    utilizator = null;
                    System.out.println("Deconectare cu succes!");
                    break;
                default:
                    System.out.println("Opțiune invalidă!");
            }
        } while (optiune != 0);
    }

    private void adaugaCardBancar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceți numărul contului pentru care doriți să adăugați cardul: ");
        String numarCont = scanner.nextLine();

        Cont cont = utilizator.getContByNumber(numarCont);
        if (cont != null) {
            CardBancar cardBancar = utilizator.createCard(cont);
            utilizator.adaugaCardBancar(cardBancar);
            System.out.println("Cardul bancar a fost adăugat cu succes!");
        } else {
            System.out.println("Numărul de cont introdus nu există sau nu aparține acestui utilizator.");
        }
    }

    private void cereRaportFinanciar() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("Introduceți data de început (yyyy-MM-dd): ");
        String start = scanner.nextLine();
        System.out.println("Introduceți data de sfârșit (yyyy-MM-dd): ");
        String end = scanner.nextLine();

        try {
            Date startDate = dateFormat.parse(start);
            Date endDate = dateFormat.parse(end);

            utilizator.getRaportFinanciar(startDate, endDate);
        } catch (ParseException e) {
            System.out.println("Formatul datei este incorect.");
        }
    }

    private void cereImprumut() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceți suma împrumutului: ");
        double suma = scanner.nextDouble();
        System.out.println("Introduceți numărul de luni pentru împrumut: ");
        int luni = scanner.nextInt();

        utilizator.cereImprumut(suma, luni);
    }

    private void autentificareCuDispozitiv() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceți codul de autentificare: ");
        String cod = scanner.next();
        utilizator.autentificareCuDispozitiv(cod);
    }

    private void schimbaParola() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceți parola veche: ");
        String parolaVeche = scanner.nextLine();
        System.out.println("Introduceți parola nouă: ");
        String parolaNoua = scanner.nextLine();

        if (utilizator.schimbaParola(parolaVeche, parolaNoua)) {
            System.out.println("Parola a fost schimbată cu succes!");
        } else {
            System.out.println("Schimbarea parolei a eșuat!");
        }
    }
}
