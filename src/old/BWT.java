/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package old;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 *
 * @author Benjamin
 */
public class BWT {
    
    private ArrayList<ArrayList<Character>> tableurNonTrie, tableurTrie;
    private ArrayList<Character> chaine, chaineDecodee, code, codeSort;
    private ArrayList<Integer> position;
    private int positionChaine;
    private final int TAILLEMOT;
    private String leCode = "";
    private char carac;

    
    public BWT(ArrayList<Character> ch)
    {
        chaine = new ArrayList<>(ch);
        TAILLEMOT = chaine.size();
        init();
        String str = "toto";
        //Ajout du mot dans la premiere ligne du tableur
        tableurNonTrie.add(0,chaine);
        
    }
    
    /*
     * Trie le tableur pour chaque ligne/mot, et stocke dans position la position de départ de chaque ligne
     */
    public void trieTableur()
    {
        
        //on boucle pour toutes les autres lignes du tableur pour décaler à chaque fois un caractère abc --> cab --> bca
        for(int i=1 ; i < TAILLEMOT ; i++)
        {
            tableurNonTrie.add(i, new ArrayList<Character>(tableurNonTrie.get(i-1)));
            carac = (char) tableurNonTrie.get(i).get(TAILLEMOT-1);
            tableurNonTrie.get(i).remove(TAILLEMOT-1);
            tableurNonTrie.get(i).add(0, carac);
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
        //Ajout de la position du mot de depart
        //code.add(String.valueOf(positionChaine).charAt(0));
        leCode = positionChaine +"";
        
        //Ajout de chaque dernier caractere de chaque ligne du tableur
        for(ArrayList<Character> mot : tableurTrie)
        {
            leCode += mot.get(TAILLEMOT-1);
            code.add(mot.get(TAILLEMOT-1));
        }
        
        System.out.println("Mot encodée : " + leCode);
        System.out.println("Code : " + code);
        
    }
    
    public void decodage()
    {
        
        //Trie du code
        codeSort = new ArrayList<>(code);
        Collections.sort(codeSort);
        System.out.println("Code trié : " + codeSort);
        
        System.out.println("Position :  0  1  2  3  4 ");
        System.out.println("Code     : " + code);
        System.out.println("CodeSort : " + codeSort);
        chaineDecodee = new ArrayList<>(TAILLEMOT);
        int posi = positionChaine, dernierePos = 0, nbOccu = 1;
        char c = ' ';
        
        for(int i=0 ; i<TAILLEMOT ; i++)
        {
            //On regarde la lettre à la position posi dans codeSort
            c = codeSort.get(posi);

            //On ajoute cette lettre au début de la chaine décodé
            chaineDecodee.add(c);

            //On regarde l'occurence de cette lettre dans codeSort jusqu'à posSort mais pas au delà --> nbOccu
            nbOccu = Collections.frequency(codeSort.subList(0, posi+1), c);System.out.println("Occu de " + c +" " + nbOccu);

            //On récupère la position dans code de cette lettre à la nbOccu ieme fois ( nbOccu = 2 --> donc la 2nde occurence)
            int debut = 0;
            
            for(int j=0 ; j < nbOccu ; j++)
            {
                posi = code.subList(debut, TAILLEMOT).indexOf(c) + debut;
                debut = posi + 1;
            }
        }
        System.out.println("Chaine décodée : " + chaineDecodee);
        
    }
    
    /*
     * Permet d'intialiser les ArrayList et autres variables
     */
    public void init()
    {
         tableurNonTrie = new ArrayList<ArrayList<Character>>(TAILLEMOT); 
         position = new ArrayList<Integer>(TAILLEMOT); 
         code = new ArrayList<Character>(TAILLEMOT); 
         codeSort = new ArrayList<Character>(TAILLEMOT); 
         chaineDecodee = new ArrayList<Character>(TAILLEMOT); 
         
         positionChaine = 0;
         carac=' ';
    }
    
    public ArrayList<Character> getCode()
    {
       return code;
    }
    
    public int getPosition()
    {
       return positionChaine;
    }
}
