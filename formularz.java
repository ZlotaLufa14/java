import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Podaj imie: ");
        String imie = sc.nextLine();

        System.out.print("Podaj nazwisko: ");
        String nazwisko = sc.nextLine();

        System.out.print("Podaj email: ");
        String email = sc.nextLine();

        System.out.print("Podaj haslo: ");
        String haslo = sc.nextLine();

        System.out.println("\n=== WYNIK ===\n");

        sprawdzPustosc(imie);
        sprawdzPustosc(nazwisko);
        sprawdzEmail(email);
        sprawdzHaslo(haslo);

    }

    public static void sprawdzPustosc(String element) {
        if(!element.isEmpty()) {
            System.out.println(element);
        } else {
            System.out.println("Pole jest puste");
        }
    }

    public static void sprawdzEmail(String element) {
        if(element.contains("@") && element.contains(".")) {
            System.out.println(element);
        } else {
            System.out.println("Email powinnien zawierać '.' oraz '@'.");
        }
    }

    public static void sprawdzHaslo(String element) {
        if(element.length() < 8 || !element.matches(".*[a-z].*") || !element.matches(".*[A-Z].*") || !element.matches(".*[a-zA-z0-9].*")) {
            System.out.println("Hasło powinno mieć więcej niż 8 znaków, zawierać małą, dużą literę oraz znak lub cyfrę");
        } else {
            System.out.println(element);
        }
    }
}
