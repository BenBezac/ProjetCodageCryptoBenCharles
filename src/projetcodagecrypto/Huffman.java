package projetcodagecrypto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Huffman {
    
    /* Chaine à compresser */
    private ArrayList<Integer> encoding;
    
    /* Tableau contenant les fréquences d'apparition */
    private ArrayList<ArrayList<Integer>> tabFrequence;
    
    /* Liste d'arbre */
    private ArrayList<Arbre> lstArbre;
    
    /* Tableau de codage */
    private ArrayList<ArrayList> tabBinary;
    
    /* Constructeur avec chaine à compresser comme paramètre */
    public Huffman (ArrayList<Integer> encoding)
    {
        this.encoding = new ArrayList<>(encoding);
        this.tabFrequence = new ArrayList<>();
        this.lstArbre = new ArrayList<>();
    }
    
    public String coder()
    {
        this.completeTabFrequence();
        this.sortTabFrequence();
        this.initTree();
        this.buildTree();
        this.tabBinary = this.lstArbre.get(0).codageTabBinary();
        String chaineCoder = "";
        
        for (int code : this.encoding) {
            chaineCoder += this.codage(code);
        }
        
        return chaineCoder;
    }
    
    private String codage(int code)
    {
        ArrayList<Byte> bytes = new ArrayList<Byte>();
        for (ArrayList tab : this.tabBinary) {
            if (tab.get(0).equals(code)) {
                return tab.get(1).toString();
            }
        }
        return "";
    }
    
    public ArrayList<Arbre> getLstArbre()
    {
        return this.lstArbre;
    }
    
    /* Complete le tableau de fréquence */
    private void completeTabFrequence ()
    {
        for (final int code : this.encoding) {
            boolean write = false;
            for (ArrayList<Integer> tabFreq : this.tabFrequence) {
                if (tabFreq.get(0) == code) {
                   tabFreq.set(1, tabFreq.get(1) + 1);
                   write = true;
                }
            }
            if (!write) {
                ArrayList<Integer> tabFreq = new ArrayList<Integer>() {{ add(code); add(1); }};
                this.tabFrequence.add(tabFreq);
            }
        }
    }
    
    /**
     * Trie le tableau contenant les fréquences
     */
    public void sortTabFrequence ()
    {
        Collections.sort(this.tabFrequence, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> al1, ArrayList<Integer> al2) {
               return al1.get(1).compareTo(al2.get(1));
            }
        });
    }
    
    /* Initialise les arbres à partir du tableau de fréquence */
    public void initTree ()
    {
        for (ArrayList<Integer> tabFreq : this.tabFrequence) {
            Arbre arb = new Arbre(tabFreq.get(0), tabFreq.get(1));
            this.lstArbre.add(arb);
        }
    }
    
    public void buildTree()
    {
        if (this.lstArbre.size() != 1) {
            Arbre a = this.minLstArbre();
            this.lstArbre.remove(a);
            Arbre b = this.minLstArbre();
            this.lstArbre.remove(b);
            Arbre i = new Arbre(a,b);
            this.lstArbre.add(i);
            
            this.buildTree();
        }
    }
    
    public Arbre minLstArbre()
    {
        return Collections.min(this.lstArbre, new Comparator<Arbre>() {
            @Override
            public int compare(Arbre a1, Arbre a2) {
                return a1.compareTo(a2);
            }
        });
    }
    
    public ArrayList<Integer> decoder(String chaine)
    {
        ArrayList<Integer> decoding = new ArrayList<Integer>();
        String buffer = "";
        
        for (int i = 0; i<chaine.length(); i++)
        {
            buffer += chaine.charAt(i);
            
            for (ArrayList tab : this.tabBinary)
            {
                if (tab.get(1).equals(buffer)) {
                    decoding.add(tab.get(0).hashCode());
                    buffer = "";
                }
            }
        }
        
        return decoding;
    }
    
    public void afficherDecoder(ArrayList<Integer> decoding)
    {
        System.out.print("[");
        for (Integer tab : decoding)
        {
            System.out.print(tab + " ");
        }
        System.out.print("]");
    }
}
