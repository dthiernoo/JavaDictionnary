package dictionnaire;
import java.util.ArrayList;

/**
 * La classe abstraite Reference représente une référence à un mot dans le dictionnaire.
 * Elle fournit des méthodes abstraites pour récupérer différentes informations sur le mot.
*/
abstract public class Reference {
   
    
    protected ArrayList<String> reference = new ArrayList<>();
    private static ArrayList<ArrayList<String>> historique = new ArrayList<>();

    /**
     * Recherche dans l'historique la référence du mot donné.
     * 
     * @param motInconnu Le mot pour lequel on recherche une référence dans l'historique.
     * @return La référence du mot si trouvé dans l'historique, sinon la référence actuelle (null).
    */
    public ArrayList<String> rechercherHistorique(String motInconnu) {
        for (ArrayList<String> motRef: historique) {
            if (motRef.get(0).equalsIgnoreCase(motInconnu)) {
                return motRef;
            }
        } return this.reference;
    }

    

    /**
     * Concatène les éléments de la référence pour former une définition.
     * 
     * @param reference La référence contenant les éléments à concaténer.
     * @param updateRef Indique s'il faut mettre à jour la référence après concaténation.
     * @return La définition du mot.
    */
    protected String concatDefinition(ArrayList<String> reference,  boolean updateRef) {
        int count = 3;
        String concat = "";

        try {
            while (reference.get(count) != null) {
                concat += reference.get(count) + ",";
                reference.remove(count);
            } 
        } catch (IndexOutOfBoundsException e) {}
        
        concat = concat.substring(0, concat.length()-1);

        if (updateRef) reference.add(concat); 
        
        return concat;
    }

    /**
     * Récupère la référence du mot.
     * 
     * @return La référence du mot.
    */
    public ArrayList<String> getReference() {
        return this.reference;
    }

    /**
     * Récupère l'historique des recherches de mots.
     * 
     * @return L'historique des recherches de mots.
    */
    public static ArrayList<ArrayList<String>> getHistorique() {
        return historique;
    }

     /**
     * Récupère le mot inconnu pour lequel cette référence est recherchée.
     * 
     * @return Le mot inconnu recherché.
    */
    abstract public String getMotInconnu();

    /**
     * Récupère la traduction du mot recherché.
     * 
     * @return La traduction du mot recherché.
    */
    abstract public String getTraduction();

    /**
     * Récupère le type du mot recherché (nom, verbe, etc.).
     * 
     * @return Le type du mot recherché.
    */
    abstract public String getType();

    /**
     * Récupère la définition du mot recherché.
     * 
     * @return La définition du mot recherché.
    */
    abstract public String getDefinition();

    /**
     * Ajoute une référence à l'historique des recherches de mots.
     * 
     * @param reference La référence à ajouter à l'historique.
    */
    protected void setHistorique(ArrayList<String> reference) {
        historique.add(reference);
    }
    
    /**
     * Ajoute un message d'erreur à l'historique des recherches de mots.
     * Utilisée dans le cas où l'utilisateur n'entre pas un mot valide.
     * 
     * @param message Le message d'erreur à ajouter à l'historique.
    */
    protected void setHistorique(String message) {
        ArrayList<String> ref = new ArrayList<>();
        ref.add(message);
        historique.add(ref);
    }

    /**
     * Retourne une représentation en chaîne de caractères de la référence.
     * 
     * @return La représentation en chaîne de caractères de la référence.
    */
    @Override
    public String toString() {
        if (this.reference.size() > 1) {
            this.concatDefinition(this.reference, true);
            return "Reference(mot="+this.getMotInconnu()+", traduction="+this.reference.get(1)+", type="+this.reference.get(2)+", definition="+this.reference.get(3)+")";
        } return this.getReference().get(0);
    }
}