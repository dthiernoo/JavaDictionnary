package dictionnaire;
import java.util.ArrayList;

public class Historique {
    public static ArrayList<Mot> historique = new ArrayList<>();

    Historique(Mot mot) {
        historique.add(mot);
    }

}