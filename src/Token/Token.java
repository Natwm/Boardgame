/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Token;

import BoardGame.BoardGame;

/**
 * <h1>La classe Token</h1>
 * <p>Token(pions) est la classe mere de tous les pions du plateau</p>
 * <p>La classe Token correspond a toute les action commune du personnage et des boites </p>
 * <p>Les attribut de Token sont : </p>
 * <ul>
 *  <li> La possition X d'un token, de type int (posX)</li>
 *  <li> La possition Y d'un token, de type int (posY) </li>
 *  <li> Le plateau de jeu, de type BoardGame (board) </li>
 * </ul>
 *
 * @author  BAUDIN Arthur, MEZZA Nathan
 */
public class Token {
    protected int posX;
    protected int posY;
    protected BoardGame board;
    
    
    //////////////////////////// Constructeur //////////////////////////////////
    public Token(int posX,int posY, BoardGame board){
        this.posX = posX;
        this.posY = posY;
        this.board = board;
    }
    
    public Token(){
        this(-1,-1,null);
    }
    
    /////////////////////// Variables de Mouvement /////////////////////////////
    public static final char UP = 'u';
    public static final char DOWN = 'd';
    public static final char LEFT = 'l';
    public static final char RIGHT = 'r';
    
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
    
    ////////////////////////////// ToString ////////////////////////////////////
    @Override 
    public String toString(){
        return "la classe est " +this.getClass() + " et est situer en "+posX + " "+ posY;
    }
    
    
    
}
