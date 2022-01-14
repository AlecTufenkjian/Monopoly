/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.info202.tp2;

import java.util.Scanner;

/**
 *
 * @author Alec
 */
public abstract class Propriete extends Case{
    private int prixAchat;
    private int loyer;
    private Joueur proprietaire;
    

    public Propriete(int prixAchat, int loyer, String nom, String description) {
        super(nom, description);
        
        this.prixAchat = prixAchat;
        this.loyer = loyer;
        this.proprietaire = null;
    }
    
    //getters
    public int getPrixAchat() {
        return prixAchat;
    }
    public Joueur getProprietaire() {
        return proprietaire;
    }
    public int getLoyer() {
        return loyer;
    }
    
    //setters
    public void setProprietaire(Joueur proprietaire) {
        this.proprietaire = proprietaire;
    }
    public void setLoyer(int loyer) {
        this.loyer = loyer;
    }
    
    //Procedure d'achat
    public void acheter(Joueur j){
        if (getPrixAchat() <= j.getArgentEnPoche()){
            boolean estAchetee = confirmerAchat();
            if (estAchetee){               
                j.setArgentEnPoche(j.getArgentEnPoche() - getPrixAchat());
                setProprietaire(j);
                j.setNbPropriete(j.getNbPropriete() + 1);  
            }
        }else{
            System.out.println("Vous n'aviez pas assez d'argent pour acheter cette proprieté");
        }
    }
    public boolean confirmerAchat(){
        Scanner clavier = new Scanner (System.in);
        System.out.println(
                "Cette case appartient à personne.\n" + 
                "Le prix d'achat est de $" + prixAchat + ". Voulez-vous acheter? (Oui ou Non)");
        String reponse = clavier.nextLine().toLowerCase();
        
        boolean estErrone = true, estAchetee = false;
        while (estErrone)
            switch (reponse){
                case "oui": 
                    estErrone = false;
                    estAchetee = true;
                    break;
                case "non": 
                    estErrone = false;
                    break;
                default:
                    System.out.println("Erreur, vous ne pouvez entrez que Oui ou Non. Essayer encore une fois!");
                    reponse = clavier.nextLine().toLowerCase();
            } 
        System.out.println();
        return estAchetee;
    }
      
    
    
    
    
}
