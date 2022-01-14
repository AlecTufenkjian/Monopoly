package ca.qc.bdeb.info202.tp2;

import java.io.*;
import java.util.Scanner;
/**
 *
 * @author Alec - 1955227
 */
public class Tp2 {

    /**
     * @param args the command line arguments
     */
    
    public static final String FICHIERPARTIE_BIN = "sauvegarde.bin";
    
    public static void main(String[] args) {

        Partie partie = new Partie();
        De de = new De();

        int choixMenu = preparerLeJeu();
        switch (choixMenu){
                case 1: 
                    partie = chargerLaPartiePrecedente();
                    break;
                case 2:
                    creerUneNouvellePartie(partie);                   
                    break;
                case 3: 
                    quitter(); 
                    break;  
            }
        
        
         demarrerLeJeu(partie, de);
    }
    
    
    public static int preparerLeJeu(){
        Scanner clavier = new Scanner (System.in);       
        System.out.println("============== Jeu de Monopoly ==============");
        
        afficherMenuInitial();       
        String reponse = clavier.nextLine();
        int choixMenu = validerEntreeChiffre(reponse);
        
        System.out.println();
        return choixMenu;
    }
    public static void afficherMenuInitial(){
        System.out.print(
                "\t1. Charger une partie\n" +
                "\t2. Commencer une partie\n" +
                "\t3. Quitter\n\n" +
                "Quelle action voulez-vous effectuer : ");
    }
    
    
    public static Partie chargerLaPartiePrecedente() {
        Partie partie = null;
        try (FileInputStream fichier = new FileInputStream(FICHIERPARTIE_BIN);
            ObjectInputStream ois = new ObjectInputStream(fichier)) {
            partie = (Partie) ois.readObject();
        }  catch (ClassNotFoundException e) {
            System.out.println("Erreur classe introuvable");
            System.exit(0);           
        }catch (java.io.IOException e) {
            System.out.println("Il semble que vous n'aviez sauvegarde aucune partie.\n Entrez l'option 2 la prochaine fois");
            System.exit(0);
        }
        return partie;
    }
    
    //Methodes qui preparent le debut d'une nouvelle partie
    public static void creerUneNouvellePartie(Partie partie){
        chargerEtValiderLePlateauDeJeu(partie);
        creerLesJoueurs(partie);
        creerLesCases(partie);
    }
    public static void creerLesCases(Partie partie){
        String plateau[][] = partie.getPlateau(); 
        Case cases[] = new Case[10];

        for (int i = 1; i <= cases.length; i++){
            switch (plateau[i][0]) {
                case "D":
                    cases[i - 1] = new Depart(Integer.parseInt(plateau[i][4]), plateau[i][1], plateau[i][2]);
                    break;
                case "T":
                    cases[i - 1] = new Terrain(Integer.parseInt(plateau[i][3]), Integer.parseInt(plateau[i][4]), plateau[i][1], plateau[i][2]);
                    break;
                case "Tx":
                    cases[i - 1] = new Taxe(Integer.parseInt(plateau[i][4]), plateau[i][1], plateau[i][2]);
                    break;
                case "P":
                    cases[i - 1] = new StationnementGratuit(plateau[i][1], plateau[i][2]);
                    break;
                case "SP":
                    cases[i - 1] = new ServicePublic(Integer.parseInt(plateau[i][3]), Integer.parseInt(plateau[i][4]), plateau[i][1], plateau[i][2]);
                    break;
            }     
        }
        partie.setCases(cases);
    }
    public static int recupererNombreJoueurs() {
        Scanner clavier = new Scanner (System.in);
        System.out.print("************************** Commencer une Partie **************************\n"
                        + "Pour pouvoir jouer à ce jeu, il vous faut au moins 2 participants.\n"
                        + "Mais ne vous inquiétez pas, il reste toujours une place pour un troisième!\n"
                        + "Voulez-vous ajouter un joueur? (Oui ou Non)\n"
                        + "Réponse: ");
        String reponse = clavier.nextLine();
        
        int nbJoueur = 2;
        boolean estErrone = true;
        while (estErrone)
            switch (reponse.toLowerCase()){
                case "oui": 
                    estErrone = false;
                    nbJoueur = 3;
                    break;
                case "non": 
                    estErrone = false;
                    break;
                default:
                    System.out.println("Erreur, vous ne pouvez entrez que Oui ou Non. Essayer encore une fois!");
                    reponse = clavier.nextLine();
            } 
        System.out.println();
        return nbJoueur;
    }
    public static void recupererNomsDesJoueurs(Joueur []joueurs){
        Scanner clavier = new Scanner(System.in);
        String nom;
        System.out.println("************ Saisie des Joueurs ************");
        for (int i = 0; i < joueurs.length; i++){
            System.out.print("Surnom du joueur " + (i + 1) + " : ");
            nom = clavier.nextLine();
            joueurs[i].setNom(nom);
        }
        System.out.println();
    }
    public static void creerLesJoueurs(Partie partie) {  
        int nbJoueur = recupererNombreJoueurs();
        Joueur joueurs[] = new Joueur[nbJoueur];
        for (int i = 0; i < joueurs.length; i++){
            joueurs[i] = new Joueur();
        }
        recupererNomsDesJoueurs(joueurs);
        partie.setJoueurs(joueurs);
    }
    
    
    //Methodes qui avancent la partie en communiquant avec les autres classes
    public static void demarrerLeJeu(Partie partie, De de) {
        boolean estFini = false;
        int choixMenu;
        while (!estFini){
            afficherInfoJoueur(partie.getJoueurs(), partie.getCases());
            afficherPlateau(partie.getCases());
            choixMenu = afficherMenuDuTour(partie);
            switch(choixMenu){
                case 1: 
                    jouer(partie, de.lancer());
                    break;
                case 2:
                    sauvegarder(partie);
                    estFini = true;
                    break;
                case 3:
                    mettreFinALaPartie(partie.getJoueurs());
                    estFini = true;
                    break;
            }
            
        }
        
    }  
    public static void afficherPlateau(Case cases[]){
        System.out.println("********************** Plateau **********************");
        for (int i = 0; i < cases.length; i++){
            System.out.println(i + ": " + cases[i].toString());
        }
        System.out.println();

    }
    public static void afficherInfoJoueur(Joueur joueurs[], Case cases[]){
        System.out.println("*************** Les Joueurs ***************");
        
        for (int i = 0; i < joueurs.length; i++){
            System.out.println(joueurs[i].toString(cases));
        }
        System.out.println();
    }
    public static int afficherMenuDuTour(Partie partie){
        Scanner clavier = new Scanner(System.in);
        String prochainJoueur = partie.getJoueurs()[partie.getPositionProchainJoueur()].getNom();
        System.out.print(
                "************************ Menu ************************\n" +
                "C'est au tour de " + prochainJoueur + "\n" +
                "1) Lancer le dé\n" +
                "2) Sauvegarder et quitter\n" +
                "3) Mettre fin à la partie et quitter\n" +
                "Faites votre choix: ");
        
        String reponse = clavier.nextLine();
        int choixMenu = validerEntreeChiffre(reponse);
        System.out.println();
        return choixMenu;
    }
    public static void jouer(Partie partie, int valeurDe) {
        System.out.println("Vous avez obtenu " + valeurDe);
        
        Case []cases = partie.getCases();
        Joueur []joueurs = partie.getJoueurs(); 
        
        int indexJoueur = partie.getPositionProchainJoueur();
        Joueur j = joueurs[indexJoueur];        
        int positionJoueur = j.getPosition();
        
        for (int i = 1; i < valeurDe; i++){
            positionJoueur++;
            if (positionJoueur >= 10){
                positionJoueur = positionJoueur - 10;
            }
            cases[positionJoueur].survolerCase(j);           
        }
        positionJoueur++;
        if (positionJoueur >= 10){
            positionJoueur = positionJoueur - 10;
        }
        cases[positionJoueur].effectuerAction(j, valeurDe);
        
        int indexProchainJoueur = indexJoueur + 1;
        if (indexProchainJoueur > joueurs.length - 1){
            indexProchainJoueur = indexProchainJoueur - joueurs.length;
        }
        
        j.setPosition(positionJoueur);
        partie.setPositionProchainJoueur(indexProchainJoueur);        
        partie.setJoueurParticulier(j, indexJoueur);
        
        if (j.getArgentEnPoche() < 0){
            mettreFinALaPartie(joueurs);
        }
    }
    private static void sauvegarder(Partie partie){
        try (FileOutputStream fos = new FileOutputStream(FICHIERPARTIE_BIN);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(partie);
        } catch (IOException e) {
            System.out.println("Erreur d'entrÃ©es-sorties");
        }
    }
    public static void mettreFinALaPartie(Joueur joueurs[]){
        int valeurNetteMax = joueurs[0].getValeurNette();
        int index = 0;
        for (int i = 1; i < joueurs.length; i++) {       
            if (valeurNetteMax < joueurs[i].getValeurNette()){           
                    valeurNetteMax = joueurs[i].getValeurNette();
                    index = i;
            }
        }
        System.out.println(joueurs[index].getNom() + " a gagné avec une valeur nette de $" + joueurs[index].getValeurNette());
        for (int i = 0; i < joueurs.length; i++){
            if (i != index){
                System.out.println(joueurs[i].getNom() + " a perdu avec une valeur nette de $" + joueurs[i].getValeurNette());
            }
        }
        System.exit(0);
    }

    
    //Methodes de chargement et de validation
    public static void chargerEtValiderLePlateauDeJeu(Partie partie) {   
        String plateau[][] = new String[11][5];
        System.out.print("Chargement du plateau de jeu...");
        
        String[] typeDeCase = {"D", "T", "Tx", "P", "SP"};
        int[] nombreDeCase = {0, 0, 0, 0, 0};
        
        int nbRangee = 0;
        boolean estValide;
        
        try (BufferedReader FichierLecture = new BufferedReader(new FileReader("plateau.csv"));){          
            String ligne = FichierLecture.readLine();
            System.out.print("ok\nValidation du plateau de jeu...");                  
            while (ligne != null){
                estValide = validerLongueurDeLaLigne(ligne);
                if (estValide){
                    plateau[nbRangee] = ligne.split(",");
                    nbRangee++;
                }else{
                    System.out.println("\nLe plateau enregistré dans le fichier n'est pas valide.\nAu moins une case a un surplus ou une manque d'information.  Corrigez-là s'il vous plait!");
                    System.exit(0);
                }
                for (int i = 0; i < typeDeCase.length; i++){
                    if (ligne.split(",")[0].equals(typeDeCase[i])){
                        nombreDeCase[i]++;
                        break;
                    }                   
                }
                ligne = FichierLecture.readLine();
            }
            FichierLecture.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nErreur, le fichier plateau.csv n'existe pas.");
            System.exit(0);
        } catch (IOException ex) {
            System.out.println("\nErreur d'acces au fichier.");
            System.exit(0);
        }
        validerContenuDuPlateau(plateau, nombreDeCase);
        partie.setPlateau(plateau);
        
        System.out.println("ok\n");      
    }
    public static void validerContenuDuPlateau (String [][]plateau, int[]nombreDeCase){
        boolean commenceParUneCaseDepart = true;
        boolean contientUneDeChaqueCase = true;
        boolean contientUneSeuleCaseDepart = true;
        if (!plateau[1][0].equals("D")){
            commenceParUneCaseDepart = false;
            System.out.print("\n-Le plateau ne commence pas avec une case depart.");
        }           
        if (nombreDeCase[0] != 1){
           contientUneSeuleCaseDepart = false;
           System.out.print("\n-Le plateau ne contient pas qu'une seule case de depart.");
        }                
        for (int i = 0; i < nombreDeCase.length; i++){
            if (nombreDeCase[i] < 1){
                contientUneDeChaqueCase = false;
                System.out.print("\n-Le plateau ne contient pas une case de chaque type.");
                break;
            }
        }
        if (!commenceParUneCaseDepart || !contientUneDeChaqueCase || !contientUneSeuleCaseDepart){
            System.out.println();
            System.exit(0);
        }
    }
    public static boolean validerLongueurDeLaLigne(String ligne) {
        boolean estValide = true;
        if (ligne.split(",").length != 5){
            estValide = false;
        }
        return estValide;
    }
    public static int validerEntreeChiffre(String reponse){
        Scanner clavier = new Scanner (System.in);
        int choixMenu = 0;
        if (reponse.matches("[0-9]+")){
            choixMenu = Integer.parseInt(reponse);
        }
        while (choixMenu != 1 && choixMenu != 2 && choixMenu != 3){
            System.out.println("Erreur, vous ne pouvez entrez que 1, 2 ou 3. Essayer encore une fois!");               
            reponse = clavier.nextLine();
            if (reponse.matches("[0-9]+")){
                choixMenu = Integer.parseInt(reponse);
            }
        }
        return choixMenu;  
    }
    
    
    public static void quitter() {
        System.out.println("*********************************************\nAu revoir!");
        System.exit(0);
    }
}