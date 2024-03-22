# Dictionnary Implemented in Java

import java.util.InputMismatchException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // int choixMenu = afficheMenu();


        Mot rechercheMot = new Mot("Alcarraza", "dictionnary.csv");
        Mot rechercheMot2 = new Mot("Alacarraza", "dictionnary.csv");
        Mot rechercheMot3 = new Mot("Abdominous", "dictionnary.csv");
        Mot rechercheMot4 = new Mot("Abdominous", "dictionnary.csv");

        // System.out.println(rechercheMot2.getDictionnaryReference());
        // System.out.println(rechercheMot2.getReference());
        System.out.println( rechercheMot.getHistorique() );
        System.out.println( rechercheMot2.getHistorique() );
        System.out.println( rechercheMot3.getHistorique() );
        // System.out.println(rechercheMot);
    }

    /**
     * Affiche le menu et demande à l'utilisateur de sélectionner une action.
     * @return Le numéro de l'action sélectionnée par l'utilisateur.
     */
    static int afficheMenu() {
        /* Affichage du menu sur la console */
        System.out.println("\n\nSelectionez votre action:\n");
        System.out.println("  1) Rechercher le mot");
        System.out.println("  2) Imprimer l'historique");
        System.out.println("  3) Traduire le mot\n");
        
        /* Validation de l'entrer saisie par l'utilisateur */
        Scanner scanner = new Scanner(System.in);
        int number = 0; int count = 0;

        do {
            if (count == 0) count++;
            else System.out.println("\u001B[31mErreur: Veuillez entrer un nombre entre 1 et 3.\u001B[0m\n");
            try {
                System.out.print("Entrer un numero: ");
                number = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\u001B[31mErreur: Veuillez entrer un nombre valide.\u001B[0m\n");
                scanner.nextLine(); count = 0;
            }
        } while (number > 3 || number < 1);

        scanner.close();
        return number;
    }
}