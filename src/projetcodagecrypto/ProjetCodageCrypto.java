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
public class ProjetCodageCrypto {

    
    
    
    public static void main(String[] args) {
        ArrayList<Character> test = new ArrayList<>();
        test.add('X');
        test.add('B');
        test.add('D');
        test.add('C');
        test.add('D');
        test.add('D');
        test.add('E');
        test.add('F');/*
        test.add('T');
        test.add('E');
        test.add('X');
        test.add('T');
        test.add('E');*/
        BWT b = new BWT(test);
        b.trieTableur();
        b.encodage();
        System.out.println("\n###############################################\n");
        b.decodage();
        
        MoveToFront mvt = new MoveToFront();
    }
    
    
    
  

}
