/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projetcodagecrypto;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author cmolin
 */
public class ReadFile {
    
    private File fichier;
    
    private int bits;
    
    private int offSet;
    
    private DataOutputStream ecrire;
    private DataInputStream lire;
    
    public ReadFile(String name) throws FileNotFoundException
    {
        this.fichier = new File(name);
        this.bits = 0;
        this.offSet = 0;
        this.ecrire = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(this.fichier)));
        this.lire = new DataInputStream(new BufferedInputStream(new FileInputStream(this.fichier)));
    }
    
    public void write(String chaine) throws IOException
    {   
        for (int i = 0; i<chaine.length(); i++)
        {
            this.writeBit(Character.getNumericValue(chaine.charAt(i)));
        }
        
        if (this.bits != 0) {
            this.ecrire.write(this.bits);
        }
        
        this.ecrire.close();
    }
    
    private void writeBit(int bit) throws IOException
    {
        if (bit == 0) 
            this.bits <<= 1;
        else
            this.bits = this.bits << 1|1;
        
        this.offSet++;
        
        if (this.offSet == 8) {
            this.ecrire.write(this.bits);
            this.bits = 0;
            this.offSet = 0;
        }
    }
    
    public void read() throws FileNotFoundException, IOException
    {
        bits = this.lire.read();
       
       int mask=0x80;
       
       for (int i =0; i<8; i++)
        if ((bits & mask)==0) {
             mask=mask>>1;
             System.out.print(0);
         }
         else {
           mask=mask>>1;
           System.out.print(1);
         }
    }
}
