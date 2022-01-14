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
public class Partie implements Serializable {
    private String[][] plateau;
    private Joueur[] joueurs;
    private Case[] cases;
    private int positionProchainJoueur; 

    //getters
    public String[][] getPlateau() {
        return plateau;
    }
    public Case[] getCases() {
        return cases;
    }
    public Joueur[] getJoueurs() {
        return joueurs;
    }
    public int getPositionProchainJoueur() {
        return positionProchainJoueur;
    }
    
    //setters
    public void setPlateau(String[][] plateau) {
        this.plateau = plateau;
    }
    public void setJoueurs(Joueur[] joueurs) {
        this.joueurs = joueurs;
    }   
    public void setJoueurParticulier(Joueur j, int indexJoueur){
        joueurs[indexJoueur] = j;
    }
    public void setCases(Case[] cases) {
        this.cases = cases;
    }
    public void setPositionProchainJoueur(int positionProchainJoueur) {
        this.positionProchainJoueur = positionProchainJoueur;
    }  
}
