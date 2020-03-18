
package levels;

import TextReader.FilesReader;
import java.io.IOException;

/**
 * <h1>La classe Levels </h1>
 * <p>La classe Levels est la classe qui correspond a un niveau du jeu.</p>
 * <p>Les attribut de Levels sont : </p>
 * 
 * <ul>
 *  <li> Sont nom, de type String (name)</li>
 *  <li> sont tableau/board, de type char [] [] (level) </li>
 *  <li> Le plateau de jeu, de type BoardGame (board) </li>
 * </ul>
 * 
 * @author  BAUDIN Arthur, MEZZA Nathan
 */

public class Levels {
    private String name;
    private char [][] level;
    
    /**
     * Constructeur de la class Levels
     * 
     * @param name le nom du fichier a charger
     */
    public Levels(String name){
        this.name = name;
        try{
            this.load();
        }catch (Exception e){
            System.out.println("le fichier n'exite pas");
        }
        
    }
    
    /**
     * Permet de charger le niveau correspondant au non du niveau
     * 
     * @throws IOException Si le niveau n'existe pas
     */
    public void load () throws IOException{
        System.out.println(this.name);
        this.level = FilesReader.readMaze("src/levels/list/"+this.name);
    }
    
    /**
     * Set le nom du fichier 
     * 
     * @param name le nom du fichier
     */
    public void setName(String name){
        this.name = name ;
    }
    
    /**
     * Retourn le plateau de jeu
     * @return le plateau de jeu
     */
    public char [] [] getLevel(){
        return this.level;
    }
}
