import java.util.ArrayList;

public class RechercheMot extends DictionnaryReference {
    static ArrayList<ArrayList<String>> historique = new ArrayList<>();

    RechercheMot(String motInconnu, String dictionnaire) {
        super(motInconnu, dictionnaire);
        historique.add(super.getDictionnaryReference());
    }

    public ArrayList<ArrayList<String>> getHistorique() {
        return historique;
    }
}
