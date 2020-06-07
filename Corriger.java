import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Corriger {

    //Cette méthode remplace tout les mots fautifs du texte a corriger par le mot suivi de ses suggestions de correction.
    public File remplacer(String fichierTxt, Set<String> ensemble, Map<String,Set<String>> listeAssoc){
        File temp=new File("temp.txt");
        Pattern patternMot = Pattern.compile("\\w+",Pattern.UNICODE_CHARACTER_CLASS);
        Pattern patternSeparateur = Pattern.compile("[^\\w]+",Pattern.UNICODE_CHARACTER_CLASS);
        try {
            FileWriter fw = new FileWriter(temp);
            BufferedWriter writer = new BufferedWriter(fw);
            FileReader fileReader = new FileReader(fichierTxt);
            Scanner s = new Scanner(fileReader);
            s.useDelimiter("\\b");
            while (s.hasNext(patternMot)) {
                String mot = s.next(patternMot);
                String separateur = s.next(patternSeparateur);
                if(ensemble.contains(mot.toLowerCase())==true) {
                    writer.append(mot);
                }
                if(ensemble.contains(mot.toLowerCase())==false){
                    writer.append("["+mot+" => "+remplacerMot(ensemble, listeAssoc,mot)+"]");
                }
                writer.append(separateur);
            }
            writer.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }

    //Cette methode remplace un mot fautif par le mot et ses suggestions de correction.
    public String remplacerMot(Set<String> ensemble, Map<String,Set<String>> listeAssoc, String mot){
        Set<String> suggestions= new HashSet<>();
        if(listeAssoc.containsKey(mot.toLowerCase()))
            suggestions.addAll(listeAssoc.get(mot.toLowerCase()));
        String motPartiel=mot;
        for(int i=0;i<mot.length();i++){
            motPartiel=motPartiel.substring(0,i)+motPartiel.substring(i+1);
            motPartiel=motPartiel.toLowerCase();
            if(listeAssoc.containsKey(motPartiel))
                suggestions.addAll(listeAssoc.get(motPartiel));
            if(ensemble.contains(motPartiel))
                suggestions.add(motPartiel);
            motPartiel=mot;
        }
        if(suggestions.size()==0)
            suggestions.add("(?)");
        return suggestions.toString().replace("[", "").replace("]", "");
    }




}

