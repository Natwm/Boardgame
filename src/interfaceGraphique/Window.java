package interfaceGraphique;

import BoardGame.BoardGame;
import Token.Player;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class Window extends JFrame{
    
    private BoardGame board;
    
    private CardLayout cl = new CardLayout();
    private JPanel container = new JPanel();
    private JPanel container2 = new JPanel();
    private JPanel container3 = new JPanel();
    private JLabel jL = new JLabel("View Player 1");
    private ArrayList<JPanel> containerm = new ArrayList<>(); 
    public static Image im;
    private int cycle = 0;
    private JButton nextGamePanel;
    
    public Window(BoardGame board){
        
        this.board = board;
        
        this.setTitle("Bomberman");
        this.setBackground(Color.WHITE);
        this.setSize(800,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        JSplitPane contenu = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        JSplitPane souscontenu = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        
        container.setLayout(cl);
        container2.setLayout(new BorderLayout());
        
        ArrayList<Player> viewers = board.getPlayers();
        
        for ( int i = 0; i < viewers.size(); i++ ) {
            containerm.add(new Game(this.board, viewers.get(i)));
        }
        
        nextGamePanel = new JButton("Next");
        nextGamePanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if ( cycle == viewers.size() - 1) {
                    container = containerm.get(0);
                    jL.setText("View Player 1");
                    cycle = 0;
                }
                else {
                    cycle += 1;
                    container = containerm.get(cycle);
                    jL.setText("View Player "+(cycle+1));
                }
                System.out.println("next game panel");
        }
        });
        
        //Game jouer = new Game(this.board, board.getPlayer()); 
        //container.add(jouer);
        
        container = containerm.get(0);
        
        ModelePlayer modele = new ModelePlayer(board);
        JTable tableau = new JTable(modele);
        JScrollPane jspane = new JScrollPane();
        jspane.setViewportView(tableau);
        container2.add(jspane);
        container3.add(jL);
        container3.add(nextGamePanel);
        
        souscontenu.add(container2);
        souscontenu.add(container3);
        souscontenu.setDividerLocation(500);
        contenu.add(souscontenu);
        //contenu.add(container2);
        contenu.add(container);
        contenu.setDividerLocation(300);
        
        this.setContentPane(contenu);
        this.setVisible(true);    
    }
    
    public void setBoard(BoardGame board){
        
        this.board = board;
    }

    public void changePan(int indice){
        
        if(indice == 3){
            this.changeTail(this.board.getBoardGamePrint()[0].length, this.board.getBoardGamePrint().length);
            requestFocusInWindow();}
        
    }
    
    public void changeTail(int x, int y){
        
        this.setSize(x*60, y*60);
        requestFocusInWindow();
        
    }

}
