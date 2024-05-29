import java.util.Scanner;

public static void main(String[] args) {
    ServiciuBancar serviciuBancar = ServiciuBancar.getInstance();
    Scanner scanner = new Scanner(System.in);

    // Crearea unui utilizator predefinit
    Utilizator utilizator = new Utilizator("John", "Doe", "1234567890123", "Strada Exemplu nr. 1", "john.doe@example.com", "parola");

    // Adăugarea unui cont bancar predefinit pentru utilizator
    Cont cont = new Cont(utilizator, "1234567890", 1000.0, "Nume cont", "Descriere cont", 0.0, "Type cont");
    utilizator.adaugaContSortat(cont);

    // Crearea dispozitivului de autentificare
    DispozitivAutentificare dispozitiv = new DispozitivAutentificare(utilizator, "1234"); // Cod de acces predefinit
    utilizator.adaugaDispozitivAutentificare(dispozitiv);

    // Înregistrarea utilizatorului
    serviciuBancar.inregistreazaUtilizator(utilizator);

    // Autentificare utilizator (poți ignora această parte dacă nu dorești să faci autentificarea manual)
    serviciuBancar.autentificare("john.doe@example.com", "parola");

    // Afișare meniu principal
    serviciuBancar.meniu();
}

