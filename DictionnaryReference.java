import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

abstract public class DictionnaryReference extends Reference {
    private String motInconnu;
    private String dictionnaire;



    DictionnaryReference(String motInconnu, String dictionnaire) {
        this.motInconnu = motInconnu;
        this.dictionnaire = dictionnaire;

        try {
            /* Si le mot a deja ete chercher, chercher la reference du
             * mot dans l'historique sinon rechercher la reference dans
             * le dictionnaire.
             */
            ArrayList<String> motRef = super.rechercherHistorique(motInconnu);
            if (motRef.size() == 4) {
                super.reference = motRef;
            } else {
                super.reference = setDictionnaryReference(dictionnaire);
                super.setHistorique(reference);
            }
        } catch (Exception e) {
            String message = "Aucune référence trouver pour " + this.motInconnu + "dans " + this.dictionnaire;
            super.reference.add(message); super.setHistorique(message);
            System.out.println(e.getMessage());
        }
    }
    

    DictionnaryReference(String motInconnu, String dictionnaire, ArrayList<String> dictionnaryReference) {
        this.motInconnu = motInconnu;
        this.dictionnaire = dictionnaire;
        super.reference = dictionnaryReference;
    }

    private ArrayList<String> setDictionnaryReference(String dictionnaire) throws Exception {
        ArrayList<String> ref = new ArrayList<>();
        try {
            ref = trouverDictionnaryReference(motInconnu, dictionnaire);
            if (ref.getLast().equalsIgnoreCase("Type-recherche-prefixe")) {
                ref.removeLast(); throw new Exception("\u001B[31mErreur: Mot pas trouver. Peut etre vous vouliez dire:\u001B[0m " + ref);
            }
        } catch (FileNotFoundException e) {
            System.out.println("\u001B[31mErreur: Aucun dictionnaire trouver.\u001B[0m");
        } catch (IOException e) {
            System.out.println("\u001B[31mErreur: Entrée/Sortie.\u001B[0m");
        } return ref;
    }

    private ArrayList<String> trouverDictionnaryReference(String motInconnu, String dictionnaire) throws FileNotFoundException, IOException{
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

        /* Si on est dans cette section ca veut dire que on a pas trouver de reference */
        ArrayList<String> occurences = recherchePrefixe(motInconnu, dictionnaire);
        if (occurences.size() > 0) { /* .get(0) != null */
            occurences.add("Type-recherche-prefixe");
            reader.close(); 
            // System.out.println(occurences); 
            return occurences;
        }

        reader.close(); return reference;
    }

    ArrayList<String> recherchePrefixe(String motInconnu, String dictionnaire) {
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

    ArrayList<String> trouverOccurence(String motInconnu, String dictionnaire) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(dictionnaire));
        String line; motInconnu = motInconnu.toLowerCase();
        ArrayList<String> mots = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            String[] ref = line.split(",");
            if (ref[0].toLowerCase().startsWith(motInconnu)) {
                mots.add(ref[0]);
            } 
        } reader.close();

        return mots; /* Return array null or array words potential */
    }


    /* Getters */
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
        if (super.reference.size() > 1) return super.concatDefinition(super.reference, false);
        return "Aucun type trouver pour ce mot dans " + this.dictionnaire;
    }

    @Override
    public String getDefinition() {
        if (super.reference.size() > 1) {
            super.concatDefinition(super.reference);
            return super.reference.get(3);
        } return "Aucune définition trouver pour ce mot dans " + this.dictionnaire;
    }
}
