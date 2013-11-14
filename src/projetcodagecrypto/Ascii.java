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
        tableAscii = new ArrayList<>();
    }
    
    public void initAscii()
    {
        for(char i=0 ; i<256 ; i++)
            tableAscii.add(i);
    }
    
    public ArrayList<Character> getTableAscii(){return tableAscii;}
}
