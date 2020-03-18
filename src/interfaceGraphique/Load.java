package interfaceGraphique;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Load {
    public static Image wall;
    public static Image ground;
    public static Image destrucWall;
    public static Image bomb;
    public static Image player;
    public static Image mine;
    
    public Load(){
        try{
            this.wall = ImageIO.read(new File("src/interfaceGraphique/images/Wall.jpg"));
            this.ground = ImageIO.read(new File("src/interfaceGraphique/images/Ground.jpg"));
            this.destrucWall = ImageIO.read(new File("src/interfaceGraphique/images/DestrucWall.jpg"));
            this.bomb = ImageIO.read(new File("src/interfaceGraphique/images/Bomb2.png"));
            this.player = ImageIO.read(new File("src/interfaceGraphique/images/Player.png"));
            this.mine = ImageIO.read(new File("src/interfaceGraphique/images/Mine.png"));
            
        } catch (IOException ex) {
            System.out.println("fichier introuvable");
        }
    }
    
    public Image getWall(){
        return this.wall;
    }
    
    public Image getGround(){
        return this.ground;
    }
    
    public Image getDestrucWall(){
        return this.destrucWall;
    }
    
    public Image getBomb(){
        return this.bomb;
    }
    
    public Image getPlayer(){
        return this.player;
    }
    
    public Image getMine(){
        return this.mine;
    }
}
