package dictionnaire;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Mot étend DictionnaryReference et gère l'historique des recherches effectuées.
 */
public class Mot extends DictionnaryReference {
    
    /**
     * Constructeur de la classe Mot.
     * Ajoute l'historique de la recherche du mot inconnu au dictionnaire.
     * 
     * @param motInconnu Le mot inconnu à rechercher.
     * @param dictionnaire Le dictionnaire dans lequel effectuer la recherche.
     */
    Mot(String motInconnu, String dictionnaire) {
        super(motInconnu, dictionnaire);
    }

    public static void afficherMenuRechercher(Mot mot) {
        /* Format pour le mot trouver */
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        System.out.println("Mot: " + mot.getMotInconnu() + "\n");
        System.out.println("Traduction: " + mot.getTraduction() + "\n");
        System.out.println("Type: " + mot.getType() + "\n");
        System.out.println("Définition: " + mot.getDefinition());
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
    }

    public static void afficherMenuHistorique() {
        System.out.println("\nHistorique");
        for (ArrayList<String> mot: Mot.historique) {
            try {
                System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                System.out.println("Mot: " + mot.get(0) + "\n");
                System.out.println("Traduction: " + mot.get(1) + "\n");
                System.out.println("Type: " + mot.get(2) + "\n");
                System.out.println("Définition: " + mot.get(3));
                System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            }
        }
    }

    /**
     * Affiche le menu et demande à l'utilisateur de sélectionner une action.
     * @return Le numéro de l'action sélectionnée par l'utilisateur.
     */
    public static int afficheMenu() {
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
            else System.out.println("Erreur: Veuillez entrer un nombre entre 1 et 3.\n");
            try {
                System.out.print("Entrer un numero: ");
                number = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Erreur: Veuillez entrer un nombre valide.\n");
                scanner.nextLine(); count = 0;
            }
        } while (number > 3 || number < 1);

        return number;
    }
}
