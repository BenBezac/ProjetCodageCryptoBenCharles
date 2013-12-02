/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetcodagecrypto;

import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author Benjamin
 */
public class ProjetCodageCrypto {

    
    
    
    public static void main(String[] args) throws IOException {
        Ascii as = new Ascii();
        as.initAscii();
        String test = "Coucou les amis";
        ArrayList<Integer> codeBWT, decodeBWT, codeMTF, decodeMTF, decodeHuff;
        BurrowsWheelerTransform b = new BurrowsWheelerTransform(as);
        
        System.out.println("Texte à coder : " + test);
        
        System.out.println("\n##################### Encodage BWT #####################\n");
        codeBWT = new ArrayList<Integer>(b.encodage(test));
        System.out.println(as.deconversionASCII(codeBWT));
        
        System.out.println("\n##################### Encodage MVT #####################\n");
        MoveToFront mvt = new MoveToFront(b.getPosition(),as);
        codeMTF = new ArrayList<Integer>(mvt.compressionMTF(codeBWT));        
        System.out.println(codeMTF);
        
        System.out.println("\n##################### Codage Huffman #####################\n");
        Huffman hf = new Huffman(codeMTF);
        String codeHuff = hf.coder();
        System.out.println(codeHuff);
        
        System.out.println("\n##################### Decodage Huffman #####################\n");
        decodeHuff = new ArrayList<Integer>(hf.decoder(codeHuff));
        hf.afficherDecoder(decodeHuff);
        
        System.out.println("\n##################### Decodage MVT #####################\n");
        decodeMTF = new ArrayList<Integer>(mvt.decompressionMTF(decodeHuff));        
        System.out.println(as.deconversionASCII(decodeMTF));
        
        System.out.println("\n##################### Decodage BWT #####################\n");
        decodeBWT = new ArrayList<Integer>(b.decodage(decodeMTF));
        System.out.println(as.deconversionASCII(decodeBWT));
    }
}
