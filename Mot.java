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
 
}
