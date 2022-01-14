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
public class Depart extends Case{
    
    private int bonus;
    
    public Depart(int bonus, String nom, String description) {
        super(nom, description);
        this.bonus = -bonus;
    }
    
    

    @Override
    public void effectuerAction(Joueur j, int valeurDe) {
        mettreAJourLesDepensesEtGains(j, bonus);
    }

    @Override
    public void survolerCase(Joueur j) {
        mettreAJourLesDepensesEtGains(j, bonus);
    }

    @Override
    public String toString() {
        return getNom() + " - " + getDescription() + " - $" + bonus + " !";
    }
    
}
