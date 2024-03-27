import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        do {
            int choixMenu = Mot.afficheMenu();
            Scanner scanner = new Scanner(System.in);
        
            if (choixMenu == 1) {
                System.out.print("Entrer le mot à chercher: "); String inconnu = scanner.nextLine();
                Mot mot = new Mot(inconnu, "dictionnary.csv");
                Mot.afficherMenuRechercher(mot);
            } else if (choixMenu == 2) {
                Mot.afficherMenuHistorique();
            } else if (choixMenu == 3) {
                System.out.print("Entrer le mot à traduire: "); String inconnu = scanner.nextLine();
                Mot mot = new Mot(inconnu, "dictionnary.csv");
                System.out.println("\nTraduction: " + mot.getTraduction());
            }
    
        } while (true);   
    }
}