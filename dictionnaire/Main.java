package dictionnaire;
import java.util.Scanner;

/**
 * La classe Main contient le point d'entrée du programme et gère l'interaction
 * avec l'utilisateur via un menu dans la console.
*/
class Main {

    /**
     * Méthode principale qui lance le programme et gère le menu principal.
     * @param args Les arguments de la ligne de commande (non utilisés dans cette application).
    */
    public static void main(String[] args) {

        do {
            // Affichage du menu principal et récupération du choix de l'utilisateur
            int choixMenu = Mot.afficheMenu();
            Scanner scanner = new Scanner(System.in);
        
            if (choixMenu == 1) {
                // Recherche d'un mot dans le dictionnaire
                System.out.print("Entrer le mot à chercher: "); String inconnu = scanner.nextLine();
                Mot mot = new Mot(inconnu, "dictionnary.csv");
                Mot.afficherMenuRechercher(mot);

            } else if (choixMenu == 2) {
                // Affichage de l'historique des recherches
                Mot.afficherMenuHistorique();

            } else if (choixMenu == 3) {
                // Traduction d'un mot de l'anglais au français
                System.out.print("Entrer le mot à traduire: "); String inconnu = scanner.nextLine();
                Mot mot = new Mot(inconnu, "dictionnary.csv");
                System.out.println("\nTraduction: " + mot.getTraduction());
            }
    
        } while (true);   
    }
}