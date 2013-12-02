/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projetcodagecrypto;

import java.util.ArrayList;

/**
 *
 * @author cmolin
 */
public class Arbre implements Comparable<Arbre> {
    
    private int caractere; //Caractère
    
    private int poids; //Poids de l'arbre
    
    private Arbre fGauche, fDroit; //Fils gauche et fils droit
    
    /**
     * Constructeur d'un arbre avec un caractère et un poids
     * @param caractere 
     */
    public Arbre(int caractere, int poids) 
    {
        this.caractere = caractere;
        this.poids = poids;
        this.fGauche = null;
        this.fDroit = null;
    }
    
    /**
     * Constructeur d'un arbre avec deux arbres comme fils
     * @param fGauche
     * @param fDroit 
     */
    public Arbre(Arbre fGauche, Arbre fDroit)
    {
        this.poids = fGauche.getPoids() + fDroit.getPoids();
        this.fGauche = fGauche;
        this.fDroit = fDroit;
    }
    
    public int getPoids()
    {
        return this.poids;
    }
    
    public int getCaractere() 
    {
        return this.caractere;
    }
    
    /**
     * Affiche les feuilles de l'arbre
     */
    public void displayTree() 
    {
        if (this.isLeaf()) {
            System.out.print("(" + this.caractere + ":" + this.poids + ")");
        }
        else {
            System.out.print("(");
            this.fGauche.displayTree();
            this.fDroit.displayTree();
            System.out.print(")");
        }
    }
    
    /**
     * Retourne le codage binaire de l'arbre
     * @return 
     */
    public ArrayList<ArrayList> codageTabBinary ()
    {
        ArrayList<ArrayList> tabBinary = new ArrayList<ArrayList>();
        this.buildCodageTabBinary("", tabBinary);
        return tabBinary;
    }
    
    /**
     * Méthode récursive pour calculer le code binaire
     * @param codage
     * @param tabBinary 
     */
    private void buildCodageTabBinary (String codage, ArrayList<ArrayList> tabBinary)
    {
        if (this.isLeaf()) {
            ArrayList code = new ArrayList();
            code.add(this.caractere);
            code.add(codage);
            tabBinary.add(code);
        }
        else {
            this.fGauche.buildCodageTabBinary(codage + "0", tabBinary);
            this.fDroit.buildCodageTabBinary(codage + "1", tabBinary);
        }
    }
    
    /**
     * Test si l'arbre est une feuille
     * @return 
     */
    public boolean isLeaf() 
    {
        return (this.fDroit == null && this.fGauche == null);
    }

    /**
     * Méthode de comparaison sur le poids de l'arbre
     * @param otherArbre
     * @return 
     */
    @Override
    public int compareTo(Arbre otherArbre) {
        return (this.poids < otherArbre.getPoids()) ? -1: (this.poids > otherArbre.getPoids()) ? 1:0;
    }
    
}
