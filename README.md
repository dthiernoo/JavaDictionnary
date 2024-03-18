# Dictionnary Implemented in Java

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

    /**
     * Constructeur de la classe Mot.
     * Ajoute l'historique de la recherche du mot inconnu au dictionnaire.
     * 
     * @param motInconnu Le mot inconnu à rechercher.
     * @param dictionnaire Le dictionnaire dans lequel effectuer la recherche.
     */
    Mot(String motInconnu, String dictionnaire) {
        chercherMotDansHistorique(motInconnu, dictionnaire);
        super(motInconnu, dictionnaire);
        historique.add(new DictionnaryReference(motInconnu, dictionnaire));
    }

    private DictionnaryReference chercherMotDansHistorique(String motInconnu, String dictionnaire) {
        int indice = estPresentDansHistorique(motInconnu);
        if (indice != -1) {
            return historique.get(indice);
        } return new DictionnaryReference(motInconnu, dictionnaire);
    }

    private int estPresentDansHistorique(String motInconnu) {
        for (int i = 0; i < historique.size(); i++) {
            if (historique.get(i).getMotInconnu().equalsIgnoreCase(motInconnu)) {
                return i;
            } 
        } return -1;

        // for (DictionnaryReference ref: historique) {
        //     if (ref.getMotInconnu().equalsIgnoreCase(motInconnu)) {
        //         return true;
        //     }
        // } return false;
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
