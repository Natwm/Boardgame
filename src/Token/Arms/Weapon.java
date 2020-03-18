/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Token.Arms;

import java.util.ArrayList;
import BoardGame.BoardGame;
import Token.Player;
import Token.Token;


/**
 * <h1>La classe Weapon </h1>
 * <p>Weapon est la classe qui correspond aux Mine ; La class Weapon hérite de Weapon</p>
 * <p>La classe Weapon correspond a toute les action que peux effectuer une Arme</p>
 * <p>Les attribut de Weapon sont : </p>
 * <ul>
 *  <li> La distance que peut atteindre une Armes : range (int) </li>
 *  <li> Les dommages que peux infliger une Armes : damage (int) </li>
 *  <li> Le propriétaire de l'armes : player (Player) </li>
 * </ul>
 *
 * @author  BAUDIN Arthur, MEZZA Nathan
 */
public abstract class Weapon extends Token{
    protected int range;
    protected int damage;
    protected Player player;
    
    //////////////////////////// Constructeur //////////////////////////////////
    /**
     * Constructeur de la classe Weapon
     * @param posX La possition X de l'arme
     * @param posY La possition Y de l'arme
     * @param board le plateau qui contient l'arme
     */
    public Weapon (int posX,int posY, BoardGame board){
        super(posX,posY,board);
    }
    
    /**
     * Constructeur par défault de la classe Weapon
     * @param player le propriétaire de l'arme
     */
    public Weapon ( Player player){
        this.player = player;
    }
    
    ///////////////////////////////// Méthodes /////////////////////////////////
    /**
     * Cette méthode Ajoute dans une liste tous les Tokens qui serons infecter par l'utilisation de l'arme
     * du joueur, selon la range(distance) de l'arme les zone seront plus ou moins grande 
     * @return La liste des joueur impacter pas l'utilisation d'une arme
     */
    public ArrayList<Token> impactedToken(){
        ArrayList<Token> impacted = new ArrayList();        
        int posX = this.player.getPosX();
        int posY = this.player.getPosY();
                
                
        for (int i = 1 ; i < this.range + 1; i++){
            //System.out.println(" # " + i + "\n\n\n" );            
            try{
            if(isItPossible(posX - i, posY) && this.player.getBoard().getBoardGame()[posX - i][posY] instanceof Player ){System.out.println("posX = " + (posX-i) + " || posY =  " + posY);
                impacted.add(this.player.getBoard().getBoardGame()[posX - i][posY]);
            }
            
            
            if(isItPossible(posX + i, posY) && this.player.getBoard().getBoardGame()[posX + i][posY] instanceof Player){System.out.println("posX = " + (posX+i) + " || posY =  " + posY);
                impacted.add(this.player.getBoard().getBoardGame()[posX + i][posY]);
            }
            
            
            if(isItPossible(posX, posY - i) && this.player.getBoard().getBoardGame()[posX][posY - i] instanceof Player){System.out.println("posX = " + posX + " || posY =  " + (posY-i));
                impacted.add(this.player.getBoard().getBoardGame()[posX][posY - i]);
            }
            
            
            if(isItPossible(posX, posY + i) && this.player.getBoard().getBoardGame()[posX][posY + i] instanceof Player){System.out.println("posX = " + posX + " || posY =  " + (posY+i));
                impacted.add(this.player.getBoard().getBoardGame()[posX][posY + i]);
            }
            
            //les diagonales
            if(isItPossible(posX - 1, posY + 1) && this.player.getBoard().getBoardGame()[posX - 1][posY - 1] instanceof Player){System.out.println("posX = " + (posX-1) + " || posY =  " + (posY-1));
                impacted.add(this.player.getBoard().getBoardGame()[posX][posY + i]);
            }
            
            if(isItPossible(posX - 1, posY + 1) && this.player.getBoard().getBoardGame()[posX - 1][posY + 1] instanceof Player){System.out.println("posX = " + (posX-1) + " || posY =  " + (posY+1));
                impacted.add(this.player.getBoard().getBoardGame()[posX][posY + i]);
            }
            
            if(isItPossible(posX + 1, posY + 1) && this.player.getBoard().getBoardGame()[posX + 1][posY + 1] instanceof Player){System.out.println("posX = " + (posX+1) + " || posY =  " + (posY+1));
                impacted.add(this.player.getBoard().getBoardGame()[posX][posY + i]);
            }
            
            if(isItPossible(posX + 1, posY - 1) && this.player.getBoard().getBoardGame()[posX + 1][posY - 1] instanceof Player){System.out.println("posX = " + (posX+1) + " || posY =  " + (posY+1));
                impacted.add(this.player.getBoard().getBoardGame()[posX][posY + i]);
            }
            }catch(ArrayIndexOutOfBoundsException e){
                System.out.println("sortie du plateau");
            }
        }
        System.out.println("les zones impacter sont :" + impacted);
        
        return impacted;
        
    }
    
    public abstract void use(int posX, int posY,Player player);
    
    
    
    /**
     * retourne un boolean, qui correspond a si il est possible de déposer une mine a la zone indiquer en argument de la méthode
     * @param posX La futur possition X
     * @param posY La futur possition Y
     * @return un boolean
     */
    private boolean isItPossible (int posX, int posY){
        return !(posX < 0 || posX > this.player.getBoard().getBoardGamePrint()[this.player.getPosX()].length ) 
                && !(posY < 0 || posY > this.player.getBoard().getBoardGamePrint()[this.player.getPosY()].length ) ;
    }
    
    /////////////////////////////// Getter /////////////////////////////////////
    public int getPosX() {
        return posX;
    }
    
    public int getPosY() {
        return posY;
    }

    public BoardGame getBoard() {
        return board;
    }

    public int getRange() {
        return range;
    }

    public int getDamage() {
        return damage;
    }

    public Player getPlayer() {
        return player;
    }

        /////////////////////////////// Setter /////////////////////////////////////
    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setBoard(BoardGame board) {
        this.board = board;
    }

    public void setRange(int range) {
        this.range = range;
    } 
    
    public void setDamage(int damage) {
        this.damage = damage;
    }  

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    
    ////////////////////////////// To String ///////////////////////////////////
    @Override
    public String toString(){
        return " " +this.getClass();
    }
}
