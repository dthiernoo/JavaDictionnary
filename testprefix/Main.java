package testprefix;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import dictionnaire.DictionnaryReference;

/**
 * La classe Main est responsable de la lecture des mots à partir d'un fichier, de la recherche de mots similaires
 * dans un dictionnaire et de l'écriture du nombre d'occurrences de mots similaires dans un autre fichier.
*/
public class Main {
    /**
     * La méthode principale de l'application.
     * Elle lit chaque ligne du fichier d'entrée, recherche des mots similaires dans le dictionnaire et écrit le
     * nombre d'occurrences de mots similaires dans le fichier de sortie.
     * 
     * @param args Les arguments de la ligne de commande (non utilisés dans cette application).
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de la lecture ou de l'écriture des fichiers.
    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./testprefix/testPrefix.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("./testprefix/testPrefixResult.txt"));
        String line; 
        
        try {
            while ((line = reader.readLine()) != null) {
                // Crée une référence vers le dictionnaire et recherche des mots similaires pour chaque ligne lue
                DictionnaryReference dictionnaryReference = new DictionnaryReference(line, "dictionnary.csv");
                int nombreOccurences = dictionnaryReference.recherchePrefixe().size();

                // Écrit le nombre d'occurrences de mots similaires dans le fichier de sortie
                writer.write(Integer.toString(nombreOccurences));
                writer.newLine();
            }
            // Ferme les flux de lecture et d'écriture une fois le traitement terminé
            reader.close();
            writer.close();
        } catch (IOException e) {}
    }
}
