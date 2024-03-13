import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RechercheMot {
    private String motIconnu;
    private ArrayList<String> dictionnaryReference;

    RechercheMot(String motInconnu, String dictionnaire) {
        this.motIconnu = motInconnu;

        try {
            this.dictionnaryReference = getReference(motInconnu, dictionnaire);
        } catch (FileNotFoundException e) {
            System.out.println("\\u001B[31mErreur: Aucun dictionnaire trouver.\\u001B[0m");
        } catch (IOException e) {
            System.out.println("\\u001B[31mErreur: Entrée/Sortie.\\u001B[0m");
        }
    }

    public ArrayList<String> getReference(String motInconnu, String dictionnaire) throws FileNotFoundException, IOException{
            BufferedReader reader = new BufferedReader(new FileReader(dictionnaire));
            String line; ArrayList<String> reference = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                String[] ref = line.split(",");
                if (ref[0].equalsIgnoreCase(motInconnu)) {
                    for (String str: ref) {
                        reference.add(str);
                    } reader.close(); return reference;
                }
            }
            
            reader.close(); return reference;
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

    public String getmotIconnu() {
        return this.motIconnu;
    }

    public ArrayList<String> getDictionnaryReference() {
        return this.dictionnaryReference;
    }

    @Override
    public String toString() {
        concatDefinition(dictionnaryReference);
        return "Reference(mot="+this.motIconnu+", traduction="+this.dictionnaryReference.get(1)+", type="+this.dictionnaryReference.get(2)+", definition="+this.dictionnaryReference.get(3)+")";
    }
}
