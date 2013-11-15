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
public class MoveToFront {
    
    private ArrayList<Integer> mot;
    private int position;
    private Ascii ascii;
    private ArrayList<Integer> tableau;
    
    public MoveToFront(ArrayList<Integer> m, int pos, Ascii as)
    {
        mot = new ArrayList<>(m);
        ascii = as;
        tableau = new ArrayList<>();
        for(int i=0 ; i<256 ; i++)
            tableau.add(i);
        position = pos;
    }
    
    public void compressionMTV()
    {
        for(int chiff : mot)
        {
            //si c'est la première lettre du tableau pas besoin de bouger sinon oui
            if(chiff != tableau.get(0))
            {
                tableau.remove(tableau.get(chiff));
                tableau.add(0, chiff);
            }
        }
    }
}
