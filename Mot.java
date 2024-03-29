import java.util.ArrayList;

/**
 * Mot étend DictionnaryReference et gère l'historique des recherches effectuées.
 */
public class Mot extends DictionnaryReference {
    /*
     * TODO: Si le mot a deja ete chercher dans le dictionnaire => directement 
     * prendre la definition du mot dans l'historique, ce qui permet d'améliorer
     * la performance.
     */

    /** Historique des recherches effectuées. */
    static ArrayList<DictionnaryReference> historique = new ArrayList<>();

    /**
     * Constructeur de la classe Mot.
     * Ajoute l'historique de la recherche du mot inconnu au dictionnaire.
     * 
     * @param motInconnu Le mot inconnu à rechercher.
     * @param dictionnaire Le dictionnaire dans lequel effectuer la recherche.
     */
    Mot(String motInconnu, String dictionnaire) {
        super(motInconnu, dictionnaire);
        historique.add(new DictionnaryReference(motInconnu, dictionnaire));
    }

    /**
     * Renvoie l'historique des recherches effectuées.
     *
     * @return L'historique des recherches.
     */
    public ArrayList<DictionnaryReference> getHistorique() {
        return historique;
    }       
}
