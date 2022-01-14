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
public class Taxe extends Case{
    
    private int taxe;

    public Taxe(int taxe, String nom, String description) {
        super(nom, description);
        this.taxe = taxe;
    }

    @Override
    public void effectuerAction(Joueur j, int valeurDe) {
        mettreAJourLesDepensesEtGains(j, -taxe);
    }

    @Override
    public void survolerCase(Joueur j) {
        mettreAJourLesDepensesEtGains(j, -taxe/10);
    }

    @Override
    public String toString() {
        return getNom() + " - " + getDescription() + " - Taxe: $" + taxe + " ou $" + taxe/10 + " en passant";
    }
    
}
