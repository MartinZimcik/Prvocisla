import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        Scanner sc = new Scanner(System.in,"UTF-8");

        System.out.println("Zadej cestu k souboru excel:");

        userInterface.printPrimesFromFile(sc.nextLine());
    }
}
