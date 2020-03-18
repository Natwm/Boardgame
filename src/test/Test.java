/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import BoardGame.*;
import interfaceGraphique.Window;
import java.io.IOException;
import levels.Levels_list;

/**
 *
 * @author Natw
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        Levels_list levels = new Levels_list();
        BoardGame boardGame = new BoardGame(levels);
        boardGame.printBoard();
        
        

        Window window = new Window(boardGame);
        //Game game = new Game(boardGame,window);
        
        //Player player = boardGame.getPlayer();
       // System.out.println(player.getStrategy());
        
        /*for (int i = 0; i < 5; i++){
            for (Player test : boardGame.getPlayers()){
               // System.out.println(test);
                test.action();
            }
        }*/
        

       
        /*//boardGame.setPlayer();
        Player playerTest = boardGame.getPlayer();
        
        
        
        ArrayList<Weapon> armes = new ArrayList();
        armes.add(new Mine(boardGame));
        armes.add(new Bomb(boardGame));
        playerTest.setPlayerWeapon(armes);        
        
        
        playerTest.useCurrentWeapon();*/

                
    }
    
}
