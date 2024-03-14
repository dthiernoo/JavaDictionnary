import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

abstract public class DictionnaryReference implements Reference {
    private String motInconnu;
    private String dictionnaire;
    private ArrayList<String> dictionnaryReference = new ArrayList<>();


    DictionnaryReference(String motInconnu, String dictionnaire) {
        this.motInconnu = motInconnu;
        this.dictionnaire = dictionnaire;

        try {
            this.dictionnaryReference = setDictionnaryReference(dictionnaire);
            
        } catch (Exception e) {
            this.dictionnaryReference.add("Aucune référence trouver pour " + this.motInconnu + "dans " + this.dictionnaire);
            System.out.println(e.getMessage());
        }
    }

    private ArrayList<String> setDictionnaryReference(String dictionnaire) throws Exception {
        ArrayList<String> ref = new ArrayList<>();
        try {
            ref = getReference(motInconnu, dictionnaire);
            if (ref.getLast().equalsIgnoreCase("Type-recherche-prefixe")) {
                ref.removeLast();
                throw new Exception("\u001B[31mErreur: Mot pas trouver. Peut etre vous vouliez dire:\u001B[0m " + ref);
            }
        } catch (FileNotFoundException e) {
            System.out.println("\u001B[31mErreur: Aucun dictionnaire trouver.\u001B[0m");
        } catch (IOException e) {
            System.out.println("\u001B[31mErreur: Entrée/Sortie.\u001B[0m");
        } return ref;
    }

    private ArrayList<String> getReference(String motInconnu, String dictionnaire) throws FileNotFoundException, IOException{
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

    private String concatDefinition(ArrayList<String> reference) {
        int count = 3;
        String concat = "";

        try {
            while (reference.get(count) != null) {
                concat += reference.get(count) + ",";
                reference.remove(count);
            } 
        } catch (IndexOutOfBoundsException e) {}
        
        concat = concat.substring(0, concat.length()-1);

        reference.add(concat); return concat;
    }

    private String concatDefinition(ArrayList<String> reference,  boolean updateRef) {
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

    /* Getters */
    public String getMotInconnu() {
        return this.motInconnu;
    }

    public String getTraduction() {
        if (this.dictionnaryReference.size() > 1) return this.dictionnaryReference.get(1);
        return "Aucune traduction trouver pour ce mot dans " + this.dictionnaire;
    }

    public String getType() {
        if (this.dictionnaryReference.size() > 1) return concatDefinition(this.dictionnaryReference, false);
        return "Aucun type trouver pour ce mot dans " + this.dictionnaire;
    }

    public String getDefinition() {
        if (this.dictionnaryReference.size() > 1) {
            concatDefinition(this.dictionnaryReference);
            return this.dictionnaryReference.get(3);
        } return "Aucune définition trouver pour ce mot dans " + this.dictionnaire;
    }

    public ArrayList<String> getDictionnaryReference() {
        return this.dictionnaryReference;
    }

    @Override
    public String toString() {
        if (this.dictionnaryReference.size() > 1) {
            concatDefinition(dictionnaryReference);
            return "Reference(mot="+this.getMotInconnu()+", traduction="+this.dictionnaryReference.get(1)+", type="+this.dictionnaryReference.get(2)+", definition="+this.dictionnaryReference.get(3)+")";
        } return this.getDictionnaryReference().get(0);
    }
}
