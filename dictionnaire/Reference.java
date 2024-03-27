package dictionnaire;
import java.util.ArrayList;

abstract public class Reference {
    abstract public String getMotInconnu();
    abstract public String getTraduction();
    abstract public String getType();
    abstract public String getDefinition();
    
    protected ArrayList<String> reference = new ArrayList<>();
    static ArrayList<ArrayList<String>> historique = new ArrayList<>();

    public ArrayList<String> rechercherHistorique(String motInconnu) {
        for (ArrayList<String> motRef: historique) {
            if (motRef.get(0).equalsIgnoreCase(motInconnu)) {
                return motRef;
            }
        } return this.reference;
    }

    public static ArrayList<ArrayList<String>> getHistorique() {
        return historique;
    }

    public void setHistorique(ArrayList<String> reference) {
        historique.add(reference);
    }
    
    public void setHistorique(String message) {
        ArrayList<String> ref = new ArrayList<>();
        ref.add(message);
        historique.add(ref);
    }



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

    public ArrayList<String> getReference() {
        return this.reference;
    }

    @Override
    public String toString() {
        if (this.reference.size() > 1) {
            this.concatDefinition(this.reference, true);
            return "Reference(mot="+this.getMotInconnu()+", traduction="+this.reference.get(1)+", type="+this.reference.get(2)+", definition="+this.reference.get(3)+")";
        } return this.getReference().get(0);
    }
}