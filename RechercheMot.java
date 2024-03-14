import java.util.ArrayList;

/**
 * RechercheMot étend DictionnaryReference et gère l'historique des recherches effectuées.
 */
public class RechercheMot extends DictionnaryReference {
    
    /** Historique des recherches effectuées. */
    static ArrayList<ArrayList<String>> historique = new ArrayList<>();

    /**
     * Constructeur de la classe RechercheMot.
     * Ajoute l'historique de la recherche du mot inconnu au dictionnaire.
     * 
     * @param motInconnu Le mot inconnu à rechercher.
     * @param dictionnaire Le dictionnaire dans lequel effectuer la recherche.
     */
    RechercheMot(String motInconnu, String dictionnaire) {
        super(motInconnu, dictionnaire);
        historique.add(super.getDictionnaryReference());
    }

    /**
     * Renvoie l'historique des recherches effectuées.
     *
     * @return L'historique des recherches.
     */
    public ArrayList<ArrayList<String>> getHistorique() {
        return historique;
    }
}
