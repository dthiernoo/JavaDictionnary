import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

abstract public class DictionnaryReference {
    private String motInconnu;
    private ArrayList<String> dictionnaryReference;

    DictionnaryReference(String motInconnu, String dictionnaire) {
        this.motInconnu = motInconnu;
        this.dictionnaryReference = setDictionnaryReference(dictionnaire);
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
/* Setters */
    private ArrayList<String> setDictionnaryReference(String dictionnaire) {
        ArrayList<String> ref = new ArrayList<>();
        try {
            ref = getReference(motInconnu, dictionnaire);
        } catch (FileNotFoundException e) {
            System.out.println("\\u001B[31mErreur: Aucun dictionnaire trouver.\\u001B[0m");
        } catch (IOException e) {
            System.out.println("\\u001B[31mErreur: Entrée/Sortie.\\u001B[0m");
        } return ref;
    }

/* Getters */
    public String getMotInconnu() {
        return this.motInconnu;
    }

    public ArrayList<String> getDictionnaryReference() {
        return this.dictionnaryReference;
    }

    public String getTraduction() {
        return this.dictionnaryReference.get(1);
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
        if (occurences.get(0) != null) {
            reader.close(); System.out.println(occurences);
        }

        reader.close(); return reference;
}
    
/* toString */
    @Override
    public String toString() {
        concatDefinition(dictionnaryReference);
        return "Reference(mot="+this.getMotInconnu()+", traduction="+this.dictionnaryReference.get(1)+", type="+this.dictionnaryReference.get(2)+", definition="+this.dictionnaryReference.get(3)+")";
    }
}
