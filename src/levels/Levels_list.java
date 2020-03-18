package levels;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * <h1>La classe Levels_list </h1>
 * <p>La classe Levels_list est la classe qui correspond aux niveaux du jeu.</p>
 * <p>L'attribut de Levels_list est : </p>
 * 
 * <ul>
 *  <li>La liste des niveau du jeu, de type ArrayList Levels  (level)</li>
 * </ul>
 * 
 * @author  BAUDIN Arthur, MEZZA Nathan
 */

public class Levels_list {

    private ArrayList<Levels> level = new ArrayList();
    private int nbLevel = 0;
    
    /**
     * Constructeur de la Class, elle permet de charger la liste des niveau et de les intégrer dans une liste. Permet de charger la liste des niveau disponible.
     * 
     * @throws IOException Si les fichier des niveaux existe
     */
    public  Levels_list() throws IOException{
        
        //Levels test = new Levels("test.xsb");
        
        Levels level0 = new Levels("level0.sok");
               
        //Levels level1 = new Levels("level1.xsb");
        
     
        //this.level.add(test);
        //this.nbLevel+=1;
        this.level.add(level0);
        this.nbLevel+=1;
        //this.level.add(level1);
        //this.nbLevel+=1;
    }
    
    /**
     * Retourne la liste des niveaux
     * @return la liste des niveaux
     */
    public ArrayList<Levels> getLevels (){
        return this.level;
    }
    
    /**
     * Retourne le numéro du niveaux
     * @return le numéro du niveaux
     */
    public int getNbLevels (){
        return this.nbLevel;
    }
    
    
    
    
    
    
}
