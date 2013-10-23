/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetcodagecrypto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Benjamin
 */
public class ProjetCodageCrypto {

    private static ArrayList<ArrayList<Character>> tableur, tableurTemp;
    private static ArrayList<Integer> position;
    
    
    public static void main(String[] args) {
        ArrayList<Character> test = new ArrayList<>();
        test.add('t');
        test.add('e');
        test.add('x');
        test.add('t');
        test.add('e');
        BWT(test);
    }
    
    public static void BWT(ArrayList chaine)
    {
        init(chaine);
        
        char carac=' ';
        //Ajout du mot dans la premiere ligne du tableur
        tableur.add(0,chaine);
        
        //on boucle pour toutes les autres lignes du tableur pour décaler à chaque fois un caractère abc --> cab --> bca
        for(int i=1 ; i < chaine.size() ; i++)
        {
            tableur.add(i, new ArrayList<Character>(tableur.get(i-1)));
            carac = (char) tableur.get(i).get(chaine.size()-1);
            tableur.get(i).remove(chaine.size()-1);
            tableur.get(i).add(0, carac);
        }
        
        /*
         * Permet de comparer deux mots ArrayList<Character> sur chaque lettre
         * Si la première est différente : comparaison, sinon on passe à la 2nde, etc...
         */
        Comparator<ArrayList<Character>>  myComparator = new Comparator<ArrayList<Character>>() {
            @Override
            public int compare(ArrayList<Character> o1, ArrayList<Character> o2) {
                int col = -1;
                boolean pasSortie = true;
                while(pasSortie) //TantQue le caractere est identique on boucle --> texte et tetex on boucle pour te
                {
                    col++;
                    pasSortie = o1.get(col).equals(o2.get(col));
                }
                return o1.get(col).compareTo(o2.get(col));
            }  
        };
        tableurTemp = new ArrayList<ArrayList<Character>>(tableur);
        
        // On trie le tableur avec l'override de compare
        Collections.sort(tableurTemp, myComparator);
        
        position.clear();
        
        //On compare notre tableur et le tableur trié pour connaitre la position initial des mots/lignes
        for(ArrayList<Character> mot : tableurTemp)
            position.add(tableur.indexOf(mot));
        
        affichageTableur("Tableur trié : ", tableurTemp);
        affichageTableur("Tableur non trié :", tableur);
        System.out.println("Position :\n" + position);
    }
    
    public static void affichageTableur(String pretexte, ArrayList<ArrayList<Character>> al)
    {   
        System.out.println(pretexte);
        System.out.println("[");
        for(ArrayList<Character> mot : al)
            System.out.println("\t"+mot);
        System.out.println("]");
    }
    
    /*
     * Permet d'intialiser les ArrayList
     */
    public static void init(ArrayList chaine)
    {
         tableur = new ArrayList<ArrayList<Character>>(chaine.size()); 
         position = new ArrayList<Integer>(chaine.size()); 
    }
  

}
