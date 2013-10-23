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
public class BWT {
    
    private ArrayList<ArrayList<Character>> tableurNonTrie, tableurTrie;
    private ArrayList<Character> chaine;
    private String code;
    private ArrayList<Integer> position;
    private int positionChaine;
    private final int TAILLEMOT;
    private char carac;

    
    public BWT(ArrayList<Character> ch)
    {
        chaine = new ArrayList<>(ch);
        TAILLEMOT = chaine.size();
        init();
        String str = "toto";
        //Ajout du mot dans la premiere ligne du tableur
        tableurNonTrie.add(0,chaine);
        
        //on boucle pour toutes les autres lignes du tableur pour décaler à chaque fois un caractère abc --> cab --> bca
        for(int i=1 ; i < TAILLEMOT ; i++)
        {
            tableurNonTrie.add(i, new ArrayList<Character>(tableurNonTrie.get(i-1)));
            carac = (char) tableurNonTrie.get(i).get(TAILLEMOT-1);
            tableurNonTrie.get(i).remove(TAILLEMOT-1);
            tableurNonTrie.get(i).add(0, carac);
        }
    }
    
    /*
     * Trie le tableur pour chaque ligne/mot, et stocke dans position la position de départ de chaque ligne
     */
    public void trieTableur()
    {
        
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
        tableurTrie = new ArrayList<ArrayList<Character>>(tableurNonTrie);
        
        // On trie le tableur avec l'override de compare
        Collections.sort(tableurTrie, myComparator);
        
        position.clear();
        
        //On compare notre tableur et le tableur trié pour connaitre la position initial des mots/lignes
        for(ArrayList<Character> mot : tableurTrie)
            position.add(tableurNonTrie.indexOf(mot));
        
        //affichageTableur("Tableur non trié :", tableur);
        affichageTableur("Tableur trié : ", tableurTrie);
        //System.out.println("Position :\n" + position);
        
        positionChaine = tableurTrie.indexOf(chaine);
        //En partant de 0 et pas de 1
        System.out.println("Position de la chaine de départ \" " + chaine + " \" : " + positionChaine);
    }
    
    public void affichageTableur(String pretexte, ArrayList<ArrayList<Character>> al)
    {   
        System.out.println(pretexte);
        System.out.println("[");
        for(ArrayList<Character> mot : al)
            System.out.println("\tPosition : " + al.indexOf(mot) +"(" + tableurNonTrie.indexOf(mot) + ")\t" + mot);
        System.out.println("]");
    }
    
    public void encodage()
    {
        code = positionChaine +"";
        
        for(ArrayList<Character> mot : tableurTrie)
            code += mot.get(TAILLEMOT-1);
        System.out.println("Code : " + code);
    }
    
    /*
     * Permet d'intialiser les ArrayList et autres variables
     */
    public void init()
    {
         tableurNonTrie = new ArrayList<ArrayList<Character>>(TAILLEMOT); 
         position = new ArrayList<Integer>(TAILLEMOT); 
         code = new String();
         
         positionChaine = 0;
         carac=' ';
    }
}
