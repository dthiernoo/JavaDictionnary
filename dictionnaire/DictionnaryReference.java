package dictionnaire;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * La classe DictionnaryReference fournit des fonctionnalités pour rechercher des mots dans un dictionnaire
 * et récupérer leurs références, y compris la traduction, le type et la définition.
 * Elle hérite de la classe Reference pour utiliser ses fonctionnalités de gestion d'historique.
 * 
 * @see Reference
*/
public class DictionnaryReference extends Reference {
    private String motInconnu;
    private String dictionnaire;

    /**
     * Constructeur de la classe DictionnaryReference.
     * Recherche le mot inconnu dans le dictionnaire et initialise ses références.
     * Si le mot est déjà recherché, récupère ses références depuis l'historique.
     * Cela permet de reduire le coût de l'algorithmique.
     * 
     * @param motInconnu Le mot inconnu à rechercher.
     * @param dictionnaire Le chemin vers le fichier CSV contenant le dictionnaire.
    */
    public DictionnaryReference(String motInconnu, String dictionnaire) {
        this.motInconnu = motInconnu;
        this.dictionnaire = dictionnaire;

        try {
            ArrayList<String> motRef = super.rechercherHistorique(motInconnu);
            if (motRef.size() == 4) {
                super.reference = motRef;
            } else {
                super.reference = setDictionnaryReference(dictionnaire);
                super.setHistorique(super.reference);
            }
        } catch (Exception e) {
            String message = "Aucune référence trouver pour " + this.motInconnu + "dans " + this.dictionnaire;
            super.reference.add(message); 
            super.setHistorique(message);
            System.out.println(e.getMessage());
        }
    }

    /**
     * Récupère la référence du mot dans le dictionnaire.
     * 
     * @param dictionnaire Le chemin vers le fichier CSV contenant le dictionnaire.
     * @return La référence du mot dans le dictionnaire.
     * @throws Exception Si une erreur survient lors de la recherche dans le dictionnaire.
    */
    public ArrayList<String> setDictionnaryReference(String dictionnaire) throws Exception {
        ArrayList<String> ref = new ArrayList<>();
        try {
            ref = trouverDictionnaryReference(motInconnu, dictionnaire);
            if (ref.getLast().equalsIgnoreCase("Type-recherche-prefixe")) {
                ref.removeLast(); throw new Exception("Erreur: Mot pas trouver. Peut etre vous vouliez dire:" + ref);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Erreur: Aucun dictionnaire trouver.");
        } catch (IOException e) {
            System.out.println("Erreur: Entrée/Sortie.");
        } return ref;
    }

    /**
     * Recherche la référence du mot dans le dictionnaire.
     * 
     * @param motInconnu Le mot inconnu à rechercher.
     * @param dictionnaire Le chemin vers le fichier CSV contenant le dictionnaire.
     * @return La référence du mot dans le dictionnaire.
     * @throws FileNotFoundException Si le fichier du dictionnaire n'est pas trouvé.
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de la lecture du dictionnaire.
    */
    public ArrayList<String> trouverDictionnaryReference(String motInconnu, String dictionnaire) throws FileNotFoundException, IOException{
        BufferedReader reader = new BufferedReader(new FileReader(dictionnaire));
        String line; ArrayList<String> reference = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            String[] ref = line.split(",");
            if (ref[0].equalsIgnoreCase(motInconnu)) {
                for (String str: ref) {
                    reference.add(str);
                } reader.close(); return reference;
            }
        }

        // Si l'on se trouve dans cette section, cela signifie qu'on a pas trouver de reference.
        ArrayList<String> occurences = recherchePrefixe(motInconnu, dictionnaire);
        if (occurences.size() > 0) {
            occurences.add("Type-recherche-prefixe");
            reader.close(); return occurences;
        }

        reader.close(); return reference;
    }

    /**
     * Recherche des mots similaires commençant par le même préfixe.
     * 
     * @param motInconnu Le mot inconnu pour lequel rechercher des mots similaires.
     * @param dictionnaire Le chemin vers le fichier CSV contenant le dictionnaire.
     * @return Une liste de mots similaires trouvés.
    */
    public ArrayList<String> recherchePrefixe(String motInconnu, String dictionnaire) {
        int compteur = motInconnu.length();

        for (int i = 1; i < compteur; i++) {
            String sub = motInconnu.substring(0, compteur - i);
            try {
                ArrayList<String> occurences = trouverOccurence(sub, dictionnaire);
                if (occurences.size() > 0) return occurences;
            } catch (IOException e) {}
        } 
        
        return new ArrayList<>();
    }

    /**
     * Recherche les mots commençant par le préfixe du mot inconnu.
     * 
     * @see testprefix.Main
     * @return Les mots similaires trouvés dans le dictionnaire.
    */
    public ArrayList<String> recherchePrefixe() {
        int compteur = this.motInconnu.length();

        for (int i = 1; i < compteur; i++) {
            String sub = this.motInconnu.substring(0, compteur - i);
            try {
                ArrayList<String> occurences = trouverOccurence(sub, this.dictionnaire);
                if (occurences.size() > 0) return occurences;
            } catch (IOException e) {}
        }

        return new ArrayList<>();
    }

    /**
     * Recherche tous les mots dont la sous-chaîne de longueur maximale depuis le début du mot inconnu, est présente dans le dictionnaire.
     * 
     * @param motInconnu Le préfixe pour lequel rechercher des occurrences.
     * @param dictionnaire Le chemin vers le fichier CSV contenant le dictionnaire.
     * @return Une liste de mots trouvés commençant par le préfixe donné.
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de la lecture du dictionnaire.
    */
    public ArrayList<String> trouverOccurence(String motInconnu, String dictionnaire) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(dictionnaire));
        String line; motInconnu = motInconnu.toLowerCase();
        ArrayList<String> mots = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            String[] ref = line.split(",");
            if (ref[0].toLowerCase().startsWith(motInconnu)) {
                mots.add(ref[0]);
            } 
        } reader.close();

        return mots;
    }

    @Override
    public String getMotInconnu() {
        return this.motInconnu;
    }

    @Override
    public String getTraduction() {
        if (super.reference.size() > 1) return super.reference.get(1);
        return "Aucune traduction trouver pour ce mot dans " + this.dictionnaire;
    }

    @Override
    public String getType() {
        if (super.reference.size() > 1) return super.reference.get(2);
        return "Aucun type trouver pour ce mot dans " + this.dictionnaire;
    }

    @Override
    public String getDefinition() {
        if (super.reference.size() > 1) {
            return super.concatDefinition(super.reference, true);
        } return "Aucune définition trouver pour ce mot dans " + this.dictionnaire;
    }
}
