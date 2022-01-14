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
public class ServicePublic extends Propriete{

    public ServicePublic(int prixAchat, int loyer, String nom, String description) {
        super(prixAchat, loyer, nom, description);
    }

    @Override
    public void effectuerAction(Joueur j, int valeurDe) {
        if (getProprietaire() == null){
            acheter(j);
        }else if(!getProprietaire().equals(j)){
            System.out.println("Cette case appartient a " + getProprietaire().getNom() + "\nVous devriez lui payer $" + 10 * valeurDe);
            
            mettreAJourLesDepensesEtGains(j, -10 * valeurDe);
            mettreAJourLesDepensesEtGains(getProprietaire(), 10 * valeurDe);
        }
    }

    @Override
    public void survolerCase(Joueur j) {
    }
    
    
    //NOTE IMPORTANT: Je n'ai pas redefini cette methode dans la classe Propriete car
    //ca serait inutile de redefinir cette methode dans Propriete comme les textes des proprietes sont differents
    @Override
    public String toString() {
        if (getProprietaire() == null){
            return getNom() + " - Prix d'achat: $" + getPrixAchat() + " - Loyer: 10 fois la valeur du de"; 
        }else{
            return getNom() + " - achetée par " + getProprietaire().getNom() + " - Loyer: 10 fois la valeur du de";
        }  
    }
    
    
    
}
