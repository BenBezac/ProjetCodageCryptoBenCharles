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
        Ascii as = new Ascii();
        as.initAscii();
        String test = "Maxime";
        BurrowsWheelerTransform b = new BurrowsWheelerTransform(test, as);
        b.trieTableur();
        b.encodage();
        
        System.out.println("\n##################### MVT #####################\n");
        MoveToFront mvt = new MoveToFront(b.getCode(), b.getPosition());
        
        
        System.out.println("\n###############################################\n");
        b.decodage();
    }
    
    
  

}
