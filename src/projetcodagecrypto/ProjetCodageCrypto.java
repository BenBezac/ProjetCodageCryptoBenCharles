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

    
    
    
    public static void main(String[] args) {
        ArrayList<Character> test = new ArrayList<>();
        test.add('T');
        test.add('E');
        test.add('X');
        test.add('T');
        test.add('E');
        BWT b = new BWT(test);
        b.trieTableur();
        b.encodage();
    }
    
    
    
  

}
