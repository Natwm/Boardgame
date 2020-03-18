/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Token.Arms;

import Token.Arms.Weapon;
import test.*;
import BoardGame.BoardGame;
import Token.Player;


/**
 *
 * @author Natw
 */
public class Bomb extends Weapon{
    
    
    //////////////////////////// Constructeur //////////////////////////////////
    public Bomb(int posX, int posY, BoardGame board) {
        super(posX, posY, board);
        this.range = 3;
        this.damage = 75;
    }
    
    public Bomb(){
        super(-1,-1,null);
        this.range = 3;
        this.damage = 75;
    }
    
    public Bomb(Player player){
        super(player);
    }
    
    public void use(int posX, int posY, Player player) {
    }
    
}
