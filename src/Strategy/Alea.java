package Strategy;

import Strategy.Strategy;
import Token.Player;

/**
 * <h1>La classe Alea</h1>
 * <p>Alea est la classe qui correspond a une des strategis disponible au joueur </p>
 * <p>La classe Alea correspond aux actions qu'effectura le joueur qui possède cette strategie, elle implemente <i>Strategy</i></p>
 * <p>Les attributs de Alea sont : </p>
 * <ul>
 *  <li> Le mouvement que le joueur a effectué précédement afin de ne pas fairel'inverse au prochain tours : previusMove (char)</li>
 *  <li> Une référece sur le joueur qui possède cette strategie : player (Player) </li>
 * </ul>
 *
 * @author  BAUDIN Arthur, MEZZA Nathan
 */
public class Alea implements Strategy{
    private char previusMove = '\0';
    private Player player;

    /**
    *
    * Constructeur de la class Alea.
    * @param player Le joueur qui utilise la strategie 
    * 
    */
    public Alea(Player player) {
        this.player = player;
    }
    
    public Alea(){
        this.player = null;
    }


    
    @Override
    public void action(){
        int actionAlea = (int)(Math.random() * 3);

        switch(actionAlea){
            case 0:
                System.out.println("move");
                this.move();
                break;

            case 1 :// le joueur attend
                System.out.println("wait");
                break;

            case 2:
                this.useWeapon();
                System.out.println("armz");
                break;

        }
    }

    /**
    *
    * Méthode de déplacement de la strategie Aléatoire
    *
    */
    public void move() {
        int moveAlea = (int)(Math.random() * 4);
        
        //if(Strategy.mouvement[moveAlea] == this.previusMove){
        if(!this.reverseMove(Strategy.mouvement[moveAlea])){
            this.move();
        }
        
        this.previusMove = Strategy.mouvement[moveAlea];
        char move = Strategy.mouvement[moveAlea];

        //System.out.println(move);
      
        this.player.inputMove(move);
        this.player.getBoard().printBoard();
        System.out.println("\n\n\n\n");
    }

    /**
    *
    * Méthode qui permet d'utiliser une des armes que possède le joueur de manière aléatoire
    *
    */
    public void useWeapon() {
        int aleaWeapon = (int) Math.random() * this.player.getPlayerWeapon().size();
        this.player.setCurrentWeapon(this.player.getPlayerWeapon().get(aleaWeapon)); // le joueur s'équipe de l'arme choisit aléatoirement
        this.player.useCurrentWeapon();// utilise l'arme
    }

    /**
    *
    * Cette méthode vérifie si le joueur n'effectue pas un mouvement
    * contraire
    * @param newMove le mouvement qui vas être executer 
    * @return si le mouvement n'est pas contradictoire
    * 
    */
    public boolean reverseMove(char newMove){
        return !((this.previusMove == Strategy.UP && newMove == Strategy.DOWN)
        || (this.previusMove == Strategy.DOWN && newMove == Strategy.UP)
        || (this.previusMove == Strategy.LEFT && newMove == Strategy.RIGHT)
        || (this.previusMove == Strategy.RIGHT && newMove == Strategy.LEFT));
    }

    /////////////////////////////// Getter /////////////////////////////////////

    /**
     * 
     * Méthode qui permet de récuperer le mouvement effectuer précedement
     * @return Le mouvement précedent 
     * 
     */
    public char getPreviusMove() {
        return previusMove;
    }
    
    @Override
    public Player getPlayer() {
        return this.player;
    }

    /////////////////////////////// Setter /////////////////////////////////////
    /**
     * 
     * Permet de set la valeur du mouvement précedemment
     * @param previusMove le mouvement précedement
     * 
     */
    public void setPreviusMove(char previusMove) {
        this.previusMove = previusMove;
    }
    
    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    ////////////////////////////// ToString ////////////////////////////////////
    @Override
    public String toString(){
        return "Votre strategy est Aléatoire";
    }
}
