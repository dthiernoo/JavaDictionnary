import java.util.ArrayList;

public interface Historique {
    public ArrayList<String> rechercherHistorique(String motInconnu);
    public ArrayList<ArrayList<String>> getHistorique();
    public void setHistorique(ArrayList<String> reference);
    public void setHistorique(String message);
}