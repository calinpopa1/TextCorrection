import java.io.*;
import java.util.*;


public class Correcteur {

    //Cette méthode prend un texte a corriger et un dictionnaire en paramètre et corrige
    //le texte en utilisant le dictionnaire.
    public static void main(String[] args){
        if (args.length != 2) {
            System.out.println("Attention: 2 arguments sont attendus");
            System.exit(-1);
        }
        Dictionnaire temp1= new Dictionnaire();
        Set<String> ensemble =temp1.construireEnsemble(args[1]);
        Dictionnaire temp2 = new Dictionnaire();
        Map<String, Set<String>> listeAsso = temp2.construireListe(args[1]);
        Corriger temp3 = new Corriger();
        File nouveauFichier = temp3.remplacer(args[0],ensemble,listeAsso);
        dessiner(nouveauFichier);
        nouveauFichier.delete();
    }

    //Cette méthode affiche un texte corrigé.
    public static void dessiner(File fichierADessiner){
        try {
            FileReader fileReader = new FileReader(fichierADessiner);
            BufferedReader br = new BufferedReader(fileReader);
            String ligne;
            while((ligne = br.readLine()) != null) {
                System.out.println(ligne);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}