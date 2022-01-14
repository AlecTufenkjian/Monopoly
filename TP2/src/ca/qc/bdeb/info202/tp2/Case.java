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
public abstract class Case implements Serializable{
    private String nom;
    private String description;
    
    public Case(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }
    
    
    
    
   public abstract void effectuerAction(Joueur j, int valeurDe);
   public abstract void survolerCase(Joueur j);
    


    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }
    
    public void mettreAJourLesDepensesEtGains(Joueur j, int montant){
        j.setArgentEnPoche(j.getArgentEnPoche() + montant);
        j.setValeurNette(j.getValeurNette() + montant);
    }

    @Override
   public abstract String toString();
    
    
    
   
}
