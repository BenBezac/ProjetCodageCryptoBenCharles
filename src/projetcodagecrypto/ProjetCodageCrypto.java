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
        String test = "TEXTE";
        ArrayList<Integer> codeBWT, decodeBWT, codeMTF, decodeMTF, codeHuff, decodeHuff;
        BurrowsWheelerTransform b = new BurrowsWheelerTransform(as);
        
        System.out.println("Texte à coder : " + test);
        
        System.out.println("\n##################### Encodage BWT #####################\n");
        codeBWT = new ArrayList<Integer>(b.encodage(test));
        System.out.println(as.deconversionASCII(codeBWT));
        
        System.out.println("\n##################### Encodage MVT #####################\n");
        MoveToFront mvt = new MoveToFront(b.getPosition(),as);
        codeMTF = new ArrayList<Integer>(mvt.compressionMTF(codeBWT));        
        System.out.println(codeMTF);
        
        //T es la charles
        codeHuff = new ArrayList<Integer>(codeMTF);
        decodeHuff = new ArrayList<Integer>(codeHuff);
        
        System.out.println("\n##################### Decodage MVT #####################\n");
        decodeMTF = new ArrayList<Integer>(mvt.decompressionMTF(decodeHuff));        
        System.out.println(as.deconversionASCII(decodeMTF));
        
        System.out.println("\n##################### Decodage BWT #####################\n");
        decodeBWT = new ArrayList<Integer>(b.decodage(decodeMTF));
        System.out.println(as.deconversionASCII(decodeBWT));
    }
    
    
  

}
