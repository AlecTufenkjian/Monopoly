/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.info202.tp2;

import java.io.Serializable;

/**
 *
 * @author Alec
 */
public class Joueur implements Serializable {
    private String nom;
    private int argentEnPoche = 400;
    private int position = 0;
    private int valeurNette = argentEnPoche;
    private int nbPropriete = 0;


    public int getArgentEnPoche() {
        return argentEnPoche;
    }

    public int getPosition() {
        return position;
    }

    public String getNom() {
        return nom;
    }

    public int getValeurNette() {
        return valeurNette;
    }

    public int getNbPropriete() {
        return nbPropriete;
    }
    
    
    
    
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void setArgentEnPoche(int argentEnPoche) {
        this.argentEnPoche = argentEnPoche;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setValeurNette(int valeurNette) {
        this.valeurNette = valeurNette;
    }

    public void setNbPropriete(int nbPropriete) {
        this.nbPropriete = nbPropriete;
    }
    
    
    
    

    public String toString(Case cases[]) {
        
        return nom + " est sur la case " + cases[position].getNom() + " et possède $" + argentEnPoche + "(Valeur Nette: $" + valeurNette + ")";
    }
  
}
