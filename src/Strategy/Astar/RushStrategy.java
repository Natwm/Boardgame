package Strategy.Astar;

import Strategy.Strategy;
import Token.Arms.Weapon;
import Token.Player;
import java.util.ArrayList;

/**
 * <h1>La classe RushStrategy</h1>
 * <p>RushStrategy est la classe qui correspond a une des strategis disponible au joueur </p>
 * <p>La classe RushStrategy correspond aux actions qu'effectura le joueur qui possède cette strategie, elle implemente <i>Strategy</i></p>
 * <p>Les attributs de RushStrategy sont : </p>
 * <ul>
 *  <li> Une liste de Cells qui permet de reperer le joueur et sa cible : arrayOfCells (ArrayOfCells)</li>
 *  <li> Le chemin vers la cible du joueur : path (Astar) </li>
 *  <li> Le joueur qui utilise la strategie : user (Player) </li>
 * </ul>
 *
 * @author  BAUDIN Arthur, MEZZA Nathan
 */

public class RushStrategy implements Strategy {
    private ArrayOfCells arrayOfCells;
    private Astar path;
    private Player user;

    
    /**
     * 
     * Constructeur de la classe RushStrategy
     * @param player une référence sur le joueur qui utilise la strategie
     *
     */
    public RushStrategy(Player player) {
        this.user = player;
        this.arrayOfCells = new ArrayOfCells(player);
        Cells start = this.arrayOfCells.getStart();
        this.path = new Astar(null, start,this.playerAttention());
    }
    
    /**
     * Constructeur par default de RushStrategy
     */
    public RushStrategy() {

    }

    ////////////////////////////// Méthodes ////////////////////////////////////
    /**
     * 
     * Permet de mettre a jour le chemin le plus cours vers le joueur le plus proche, change de sible si jamais un autre joueur est plus proche
     * 
     */
    private void update(){
        //System.out.println(this.user);
        this.arrayOfCells.update(this.user);
        Cells start = this.arrayOfCells.getStart();
        //System.out.println(" Le nouveau départ :"+start);
        this.path.setStart(start);
        this.path.setEnd(this.playerAttention());
    }
    
    ////////////////////////// Méthode Strategy ////////////////////////////////
    /**
     * La méthode qui effectue les action lié a la strategie
     */ 
    @Override
    public void action(){
        //System.out.println(this.path);
        this.update();
        this.move();
        this.useWeapon();
    }
    
    /**
     *
     * La méthode de mouvement type de RushStrategy
     * 
     */
    public void move() {
        this.path.pathFinding();
        //System.out.println(this.path.getMoveToTarget());
        this.user.inputMove(this.path.getMoveToTarget().get(0));
        this.update();
        this.user.getBoard().printBoard();
    }


    /**
     * 
     * La méthode d'utilisation des armes type de RushStrategy
     * 
     */
    public void useWeapon() {
        for(Weapon weapon : this.user.getPlayerWeapon()){
            this.user.setCurrentWeapon(weapon);
            if ( !weapon.impactedToken().isEmpty())
                this.user.useCurrentWeapon();
            //System.out.println(weapon.impactedToken());
        }
        
    }

    /**
     * 
     * Retourne le joueur le plus proche de l'utilisateur 
     * @return le joueur le plus proche afin d'effectuer Astar avec pour destination ce personnage
     * 
     */
    public Cells playerAttention() {
        ArrayList<Cells> playerList = new ArrayList(this.arrayOfCells.getArrayOfPlayer());        
        
        playerList.remove(this.arrayOfCells.whereIsPlayer(user));
        
        Cells target = null;
        int closerPlayer = (int) Float.POSITIVE_INFINITY;
        for (Cells token : playerList) {
            int temp = Math.abs(this.user.getPosX() - token.getPosX()) + Math.abs(this.user.getPosX() - token.getPosY());
            if (temp != 0) {
                if (temp <= closerPlayer) {
                    closerPlayer = temp;
                    target = token;

                }
            }

        }
        return target;
    }
    
    @Override
    public Player getPlayer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void setPlayer(Player player) {
        this.user = player;
        this.arrayOfCells = new ArrayOfCells(player);
        Cells start = this.arrayOfCells.getStart();
        this.path = new Astar(null, start,this.playerAttention());
    }
    ////////////////////////////// ToString ////////////////////////////////////
    @Override 
    public String toString(){
        return "Votre strategy est RushStrategy";
    }
}
