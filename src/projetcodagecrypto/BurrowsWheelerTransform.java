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
public class BurrowsWheelerTransform {
    private ArrayList<ArrayList<Integer>> tableurNonTrie, tableurTrie;
    private ArrayList<Integer> chaine,position;
    private int positionChaine;
    private int tailleMot;
    private String leCode = "";
    private int carac;
    private Ascii ascii;

    
    public BurrowsWheelerTransform(Ascii as)
    {
        ascii = as; 
        init(as);
        
    }
    
   
    
    /*
     * Trie le tableur pour chaque ligne/mot, et stocke dans position la position de départ de chaque ligne
     */
    private void trieTableur(String motACoder)
    {
        
        chaine = new ArrayList<Integer>(ascii.conversionASCII(motACoder));
        //Ajout du mot dans la premiere ligne du tableur
        tableurNonTrie.add(0,chaine);
        //on boucle pour toutes les autres lignes du tableur pour décaler à chaque fois un caractère abc --> cab --> bca
        for(int i=1 ; i < tailleMot ; i++)
        {
            tableurNonTrie.add(i, new ArrayList<Integer>(tableurNonTrie.get(i-1)));
            carac = tableurNonTrie.get(i).get(tailleMot-1);
            tableurNonTrie.get(i).remove(tailleMot-1);
            tableurNonTrie.get(i).add(0, carac);
        }
        /*
         * Permet de comparer deux mots ArrayList<Integer> sur chaque lettre
         * Si la première est différente : comparaison, sinon on passe à la 2nde, etc...
         */
        Comparator<ArrayList<Integer>>  myComparator = new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
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
        tableurTrie = new ArrayList<ArrayList<Integer>>(tableurNonTrie);
        
        // On trie le tableur avec l'override de compare
        Collections.sort(tableurTrie, myComparator);
        
        position.clear();
        
        //On compare notre tableur et le tableur trié pour connaitre la position initial des mots/lignes
        for(ArrayList<Integer> mot : tableurTrie)
            position.add(tableurNonTrie.indexOf(mot));
        
        //affichageTableur("Tableur non trié :", tableur);
       // affichageTableur("Tableur trié : ", tableurTrie);
        //System.out.println("Position :\n" + position);
        
        positionChaine = tableurTrie.indexOf(chaine);
        //En partant de 0 et pas de 1
        //System.out.println("Position de la chaine de départ \" " + chaine + " \" : " + positionChaine);
    }
    
    public void affichageTableur(String pretexte, ArrayList<ArrayList<Integer>> al)
    {   
        System.out.println(pretexte);
        System.out.println("[");
        for(ArrayList<Integer> mot : al)
            System.out.println("\tPosition : " + al.indexOf(mot) +"(" + tableurNonTrie.indexOf(mot) + ")\t" + mot);
        System.out.println("]");
    }
    
    public ArrayList<Integer> encodage(String motACoder)
    {
        tailleMot = motACoder.length();        
        trieTableur(motACoder);
        ArrayList<Integer> code = new ArrayList<Integer>();
        //Ajout de la position du mot de depart
        //code.add(String.valueOf(positionChaine).charAt(0));
        leCode = positionChaine +"";
        
        //Ajout de chaque dernier caractere de chaque ligne du tableur
        for(ArrayList<Integer> mot : tableurTrie)
        {
            leCode += mot.get(tailleMot-1);
            code.add(mot.get(tailleMot-1));
        }
        
        return code;
    }
    
    public ArrayList<Integer> decodage(ArrayList<Integer> codeADecoder)
    {
        ArrayList<Integer> codeSort = new ArrayList<Integer>();        
        ArrayList<Integer> chaineDecodee = new ArrayList<Integer>();
        //Trie du code
        codeSort = new ArrayList<>(codeADecoder);
        Collections.sort(codeSort);
        /*System.out.println("Code trié : " + codeSort);
        
        System.out.println("Position :  0  1  2  3  4 ");
        System.out.println("Code     : " + code);
        System.out.println("CodeSort : " + codeSort);*/
        int posi = positionChaine, nbOccu = 1, c = 0;
        
        for(int i=0 ; i<tailleMot ; i++)
        {
            //On regarde la lettre à la position posi dans codeSort
            c = codeSort.get(posi);

            //On ajoute cette lettre au début de la chaine décodé
            chaineDecodee.add(c);

            //On regarde l'occurence de cette lettre dans codeSort jusqu'à posSort mais pas au delà --> nbOccu
            nbOccu = Collections.frequency(codeSort.subList(0, posi+1), c);//System.out.println("Occu de " + c +" " + nbOccu);

            //On récupère la position dans code de cette lettre à la nbOccu ieme fois ( nbOccu = 2 --> donc la 2nde occurence)
            int debut = 0;
            
            for(int j=0 ; j < nbOccu ; j++)
            {
                posi = codeADecoder.subList(debut, tailleMot).indexOf(c) + debut;
                debut = posi + 1;
            }
        }
        
        return chaineDecodee;
    }
    
    /*
     * Permet d'intialiser les ArrayList et autres variables
     */
    public void init(Ascii as)
    {
         tableurNonTrie = new ArrayList<ArrayList<Integer>>(tailleMot); 
         position = new ArrayList<Integer>(tailleMot); 
         
         positionChaine = 0;
         carac=' ';
    }
    
    
    
    public int getPosition()
    {
       return positionChaine;
    }
}
