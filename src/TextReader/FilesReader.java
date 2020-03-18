
package TextReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;




/**
 * <h1>La classe FilesReader </h1>
 * <p>La classe FilesReader est la classe qui permet de lire un ficher XSB ou SOK, afin de les charger dans un objet BoardGame afin d'avoir un nouveau board</p>
 * 
 * @author  BAUDIN Arthur, MEZZA Nathan
 */
public class FilesReader {

    public FilesReader(){
    
    }
    
    /**
     * Cette méthode lit un fichier et l'intègre dans une liste a deux dimensions. 
     * 
     * @param filename le chemin vers  le fichier
     * 
     * @return le tableau copier
     * 
     * @throws IOException Abscence de fichier
     */
    public static char [] [] readMaze(String filename) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(filename));
        char [][] board = FilesReader.mazeAnalyzerXSB(fileReader);   
        
        
        return board;
    }
    
    /**
     * Permet de lire un fichier avec l'extension .XSB
     * 
     * @param file le fichier transformer en BufferedReader
     * 
     * @return le contenue du BufferedReader dans une grille de type char [] []
     * 
     * @throws IOException si le fichier n'existe pas
     * 
     */
    private static char [][] mazeAnalyzerXSB (BufferedReader file) throws IOException{
        Object[] test = file.lines().toArray();
        char [][] board = new char [test.length][test[1].toString().length()];
        
        for (int i = 1 ; i < test.length ; i++){
            for (int j = 0; j< test[i].toString().length();j++){
                board[i-1][j] = test[i].toString().charAt(j);
            }
        }
        
        
        return board;
    }
    
    /**
     * Permet de lire un fichier avec l'extension .SOK
     * 
     * @param file le fichier transformer en BufferedReader
     * 
     * @return le contenue du BufferedReader dans une grille de type char [] []
     * 
     * @throws IOException si le fichier n'existe pas
     * 
     */
    private static char [][] mazeAnalyzerSOK (BufferedReader file) throws IOException{
        Object[] test = file.lines().toArray();
        int longueur,largeur;
        StringBuffer get1 = new StringBuffer() ;
        get1.append(test[0].toString().charAt(0));
        get1.append(test[0].toString().charAt(1));
        
        StringBuffer get2 = new StringBuffer();
        get2.append(test[0].toString().charAt(3));
        get2.append(test[0].toString().charAt(4));

        longueur = Integer.parseInt(get1.toString());
        largeur = Integer.parseInt(get2.toString());
        
        char [][] board = new char [largeur][longueur];
        
        for (int i = 1 ; i < test.length ; i++){
            for (int j = 0; j< test[i].toString().length();j++){
                board[i][j] = test[i].toString().charAt(j);
            }
        }
        
        
        return board;
    }
}
