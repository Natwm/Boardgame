package interfaceGraphique;

import BoardGame.BoardGame;
import Token.Player;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game extends JPanel implements ActionListener{
    

    private int height ;
    private BoardGame board;
    JFrame endScreen;
    //private Window window;
    private Player viewer;
    
    private final JButton suivant = new JButton("Suivant");
    
    public Game(BoardGame board, Player viewer){
        this.height = 60;
        this.board = board;
        this.viewer = viewer;
        
        this.setLayout(null);
        this.setVisible(true);
        this.add(suivant);
        
        suivant.addActionListener(this);
        suivant.setBackground(Color.LIGHT_GRAY);
        suivant.setBounds(3, 3, 150, 30);
        
    }
    
    public void paintComponent(Graphics g){

        Load newImage = new Load();  
        

        for (int x = 0; x < board.getBoardGamePrint()[0].length ; x++){

            for (int y = 0 ; y < board.getBoardGamePrint().length; y++){
                
                char zone = this.board.getBoardGamePrint() [y] [x];
                

                switch (zone){
                    
                    case ' ':
                        g.drawImage(newImage.getGround(), x * height, y * height, height, height, this);
                        break;
                        
                    case '#':
                        g.drawImage(newImage.getWall(), x * height, y * height,height,height, this);
                        break;
                        
                    case '*':
                        g.drawImage(newImage.getDestrucWall(), x * height, y * height,height,height, this);
                        break;
                        
                    case 'b':
                        g.drawImage(newImage.getGround(), x * height, y * height, height, height, this);
                        g.drawImage(newImage.getBomb(), x * height, y * height,height,height, this);
                        break;
                        
                    case 'p':
                        g.drawImage(newImage.getGround(), x * height, y * height, height, height, this);
                        g.drawImage(newImage.getPlayer(), x * height, y * height,height,height, this);
                        break; 
                        
                    case 'm':
                        if ( board.getMines().get(0).getOwner() == viewer ){
                            g.drawImage(newImage.getGround(), x * height, y * height, height, height, this);
                            g.drawImage(newImage.getMine(), x * height, y * height,height,height, this);
                        }
                        else
                            g.drawImage(newImage.getGround(), x * height, y * height, height, height, this);
                        break;     
                        
                    default:
                        g.drawImage(newImage.getGround(), x * height, y * height, height, height, this);
                        break;
                }
            }
        }
        
        
    }
 
   
    /**
     * permet de mettre a jour la vue
     * @param board la plateau de jeu
     */    
    public void refresh(BoardGame board){
        
        this.board = board;
        this.repaint();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Player test : board.getPlayers()){
                test.action();
        }
        refresh(board);
    }
   
}