/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategy;

import BoardGame.BoardGame;
import Strategy.Astar.Cells;
import Token.Player;

/**
 *
 * @author Natw
 */
public interface Strategy {
    

    /////////////////////// Variables de Mouvement /////////////////////////////
    public static final char UP = 'u';
    public static final char DOWN = 'd';
    public static final char LEFT = 'l';
    public static final char RIGHT = 'r';
    public static final char[] mouvement = {UP,DOWN,LEFT,RIGHT};
    
    ////////////////////////////// Méthodes ////////////////////////////////////
    
    public void action();
    public Player getPlayer();
    public void setPlayer(Player player);
}
