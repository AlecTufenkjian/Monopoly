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
public class Terrain extends Propriete {

    public Terrain(int prixAchat, int loyer, String nom, String description) {
        super(prixAchat, loyer, nom, description);
    }

    
    
    
       

    @Override
    public void effectuerAction(Joueur j, int valeurDe) {
        
        if (getProprietaire() == null){
            acheter(j);       
        }else if(!getProprietaire().equals(j)){
            System.out.println("Cette case appartient a " + getProprietaire().getNom() + "\nVous devriez lui payer $" + getLoyerAjuste());
            
            mettreAJourLesDepensesEtGains(j, -getLoyerAjuste());
            mettreAJourLesDepensesEtGains(getProprietaire(), getLoyerAjuste());
        }
            
    }

    @Override
    public void survolerCase(Joueur j) {
    }
    
    @Override
    public String toString() {
        if (getProprietaire() == null){
            return getNom() + " - Prix d'achat: $" + getPrixAchat() + " - Loyer: $" + getLoyerAjuste(); 
        }else{
            return getNom() + " - achetée par " + getProprietaire().getNom() + " - Loyer: $" + getLoyerAjuste();
        }  
    }

    public int getLoyerAjuste() {  
        if (getProprietaire() == null || getProprietaire().getNbPropriete() <= 1){
            return getLoyer();
        }else{
            return getLoyer() * 2;
        }  
    }


    
}
