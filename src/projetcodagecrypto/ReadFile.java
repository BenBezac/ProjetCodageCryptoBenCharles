/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projetcodagecrypto;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
    
    public String readFile(String nameFile)
    {
        String chaine = "";
        
        try {
            InputStream ips = new FileInputStream(nameFile);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            while ((ligne = br.readLine()) != null) {
                chaine += ligne + "\n";
            }
            br.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        
        return chaine;
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
