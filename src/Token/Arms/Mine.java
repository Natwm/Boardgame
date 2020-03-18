package Token.Arms;

import Token.Arms.Weapon;
import BoardGame.BoardGame;
import Token.Player;
import static Token.Player.DOWN;
import static Token.Player.LEFT;
import static Token.Player.RIGHT;
import static Token.Player.UP;
import java.util.ArrayList;


/**
 * <h1>La classe Mine </h1>
 * <p>Mine est la classe qui correspond aux Mine ; La class Mine hérite de Weapon</p>
 * <p>La classe Mine correspond a toute les action que peux effectuer une Mine</p>
 * <p>Les attribut de Mine sont : </p>
 * <ul>
 *  <li> Les attibut hériter de Weapon </li>
 * </ul>
 *
 * @author  BAUDIN Arthur, MEZZA Nathan
 */
public class Mine extends Weapon {  
    //////////////////////////// Constructeur //////////////////////////////////
    /**
     * 
     * Constructeur de la classe Mine
     * 
     * @param posX La possition X de la mine
     * @param posY La possition Y de la mine
     * @param board Le plateau ou est situer la mine
     * @param owner Le propriétaire de la mine
     * 
     */
    public Mine(int posX, int posY, BoardGame board, Player owner) {
        super(posX, posY, board);
        this.player = owner;
        this.range = 2;
        this.damage = 50;
    }
    
    /**
     * 
     * Constructeur par défault de Mine
     * 
     */
    public Mine(){
        super(-1,-1,null);
        this.range = 2;
        this.damage = 50;
    }
    
    /**
     * Constructeur de Mine lorsque le seul argument est un joueur
     * @param player le propriétaire de la mine
     */
    public Mine(Player player){
        super(player);
    }
    
    /**
     * Compare la possition de la cible a celle de la possition du propriétaire de la mine
     * afin de la poser vers la cible
     * 
     * @param posX position future de la mine en X
     * @param posY position future de la mine en Y
     * @param target le joueur le plus proche du propriétaire de la mine
     * 
     * @return Retourne la direction vers la cible
     */
    public char compare(int posX, int posY,Player target){
        if(target != null){
            System.out.println("target  = "+target);
            System.out.println(posX + " || " + target.getPosX());
            if (posX < target.getPosX())
                return Player.DOWN;
            if( posX > target.getPosX())
                return Player.UP;
            if( posY < target.getPosY())
                return Player.RIGHT;
            if( posY > target.getPosY())
                return Player.LEFT;
        }
        return '\0';
    }
    
    /**
     * 
     * Strategie qui consiste a mettre une mine vers le joueur le plus proche
     * pour cela on compare la distance entre tous les joueurs et pose une mine
     * de manière optimale
     * 
     * @param posX position future de la mine en X
     * @param posY position future de la mine en Y
     * 
     * @return Retourne la direction vers la cible donné par la méthode compare()
     */
    public char strategieMine(int posX, int posY){
        ArrayList<Player> test = this.player.getBoard().getPlayers();

        int opti = 99999 ;
        Player target = null; 
        
        test.remove(0);
        
        for (Player token : test){
            int temp = Math.abs ( this.posX - token.getPosX()) + Math.abs( this.posY - token.getPosY() );
            if( temp != 0){
                if (temp <= opti){
                    opti = temp;
                    target = token;
                    System.out.println(target);
                }
            }
        } 
        return this.compare(posX,posY,target);
    }

    /**
     * La méthode permet de posé une Mine a un lieu précis a l'aide de la méthode StrategieMine()
     * 
     * @param posX position future de la mine en X
     * @param posY position future de la mine en Y
     * @param player Le propriétaire de la mine
     */
    public void use(int posX, int posY,Player player){
        char currentAction = this.strategieMine(posX,posY);
       
        switch(currentAction){
            case RIGHT:
                //methode de déplacement vers la droite;
                this.board.getBoardGamePrint()[posX][posY + 1] = 'm';
                this.board.getBoardGame()[posX][posY + 1] = new Mine(posX,posY + 1,this.board, player);
                break;

            case LEFT:
                //methode de déplacement vers la gauche;
                this.board.getBoardGamePrint()[posX][posY - 1] = 'm';
                this.board.getBoardGame()[posX][posY - 1] = new Mine(posX,posY - 1,this.board, player);
                break;

            case UP:
                //methode de déplacement vers le haut;
                this.board.getBoardGamePrint()[posX - 1][posY] = 'm';
                this.board.getBoardGame()[posX - 1][posY] = new Mine(posX - 1,posY,this.board, player);
                break;

            case DOWN:
                //methode de déplacement vers le bas;
                
                this.board.getBoardGamePrint()[posX + 1][posY] = 'm';
                
                this.board.getBoardGame()[posX + 1][posY] = new Mine(posX + 1,posY,this.board, player);
                break;
        }
        
    }
    
    public Player getOwner() {
        return this.player;
    }
}
