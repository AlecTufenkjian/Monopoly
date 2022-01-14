/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.info202.tp2;

/**
 *
 * @author Alec
 */
public class StationnementGratuit extends Case{

    public StationnementGratuit(String nom, String description) {
        super(nom, description);
    }
    
    

    @Override
    public void effectuerAction(Joueur j, int valeurDe) {       
    }

    @Override
    public void survolerCase(Joueur j) {
    }

    @Override
    public String toString() {
        return getNom() + " - " + getDescription() + "!";
    }
    
}
