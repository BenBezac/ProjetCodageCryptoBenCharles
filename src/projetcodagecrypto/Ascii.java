/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetcodagecrypto;

import java.util.ArrayList;

/**
 *
 * @author Benjamin
 */
public class Ascii {
    
    private ArrayList<Character> tableAscii;
    
    public Ascii()
    {
        tableAscii = new ArrayList<Character>();
    }
    
    public void initAscii()
    {
        for(char i=0 ; i<256 ; i++)
            tableAscii.add(i);
    }
     /*
     * Converti le mot de type String en type ArrayList de Int pour y stocker les valeurs ASCII des caractères du mot
     */
    public ArrayList<Integer> conversionASCII(String mot)
    {
        char[] tabchar = mot.toCharArray();
        ArrayList<Integer> chaine = new ArrayList<Integer>();
        
        for( int i=0 ; i < mot.length() ; i++)
        {
            chaine.add((int)tabchar[i]);
        }
        return chaine;
    }
    
    public String deconversionASCII(ArrayList<Integer> chaine)
    {
        String mot = "";
        
        for(int i : chaine)
        {
            mot += tableAscii.get(i) +"";
        }
        return mot;
    }
    public ArrayList<Character> getTableAscii(){return tableAscii;}
}
