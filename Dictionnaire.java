import java.io.*;
import java.util.*;


public class Dictionnaire {

    //Cette méthode construit un ensemble de tout les mots du dictionnaire.
    public Set<String> construireEnsemble(String dict){
         Set<String> ensemble = new HashSet<>();
        try {
            FileReader fr = new FileReader(dict);
            BufferedReader reader = new BufferedReader(fr);
            String s;
            while ((s = reader.readLine())!= null) {
                ensemble.add(s.toLowerCase());
            }
            reader.close();
        } catch (IOException ex) {
            System.out.println("Erreur à l’ouverture du fichier");
        }
        return ensemble;
    }

    //Cette méthode construit une liste associative contenant les corrections possibles.
    public Map<String,Set<String>> construireListe(String dict){
        Map<String, Set<String>> listeAssoc = new HashMap<>();
        try {
            FileReader fr = new FileReader(dict);
            BufferedReader reader = new BufferedReader(fr);
            String s;
            while ((s=reader.readLine())!= null) {
                String motPartiel = s;
                for(int i=0;i<s.length();i++){
                    motPartiel=motPartiel.substring(0,i)+motPartiel.substring(i+1);
                    motPartiel=motPartiel.toLowerCase();
                    if(listeAssoc.containsKey(motPartiel)) {
                        Set<String> temp =new HashSet<>();
                        temp.add(s.toLowerCase());
                        listeAssoc.get(motPartiel).addAll(temp);
                    }
                    else{
                        Set<String> temp =new HashSet<>();
                        temp.add(s.toLowerCase());
                        listeAssoc.put(motPartiel, temp);
                    }
                    motPartiel=s;
                }
            }
            reader.close();
        } catch (IOException ex) {
            System.out.println("Erreur à l’ouverture du fichier");
        }
        return listeAssoc;
    }
}