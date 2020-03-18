package Strategy.Astar;

import java.util.ArrayList;

/**
 * <h1>La classe Cells</h1>
 * <p>Cells est la classe qui correspond aux zone du plateau et leurs caractéristiques</p>
 * <p>Les attribut de Cells sont : </p>
 * <ul>
 *  <li> La valeur  f: f (int) </li>
 *  <li> La valeur g: g (int) </li>
 *  <li> La distance heuristique de la Cells: heuristique (int) </li>
 *  <li> La possition X d'un token, de type int (posX) </li>
 *  <li> La possition Y d'un token, de type int (posY) </li>
 *  <li> La Cell précédent celle-ci : previous (Cells) </li>
 *  <li> La liste des voisins de cette Cells : neighbours (ArrayList Cells) </li>
 *  <li> La valeur boolean qui indique si la Cells est un mur ou non : isWall(boolean) </li>
 *  <li> La valeur boolean qui indique si la Cells est un Player ou non : isPlayer(boolean) </li>
 * </ul>
 *
 * @author  BAUDIN Arthur, MEZZA Nathan
 */
public class Cells {
    private int f,g,heuristique = 0;
    private int posX, posY = 0;
    private Cells previous;
    private ArrayList <Cells> neighbours ;
    private boolean isWall ;
    private boolean isPlayer;

    
    //////////////////////////// Constructeur //////////////////////////////////

    public Cells (int f, int g, int heuristique, int posX, int posY, Cells previous, ArrayList<Cells> neighbours, boolean isWall,boolean isPlayer) {
        this.f = f;
        this.g = g;
        this.heuristique = heuristique;
        this.posX = posX;
        this.posY = posY;
        this.previous = previous;
        this.neighbours = neighbours;
        this.isWall = isWall;
        this.isPlayer = isPlayer;
    }
    
    ////////////////////////////// Méthode /////////////////////////////////////
    
    /////////////////////////////// Getter /////////////////////////////////////

    public int getF(){
        return this.f;
    }
    
    public int getG (){
        return this.g ;
    }
    
    public int getHeuristique(){
        return this.heuristique ;
    }
    
    public int getPosX(){
        return this.posX ;
    }
    
    public int getPosY(){
        return this.posY ;
    }
    
    public Cells getPrevious (){
        return this.previous ;
    }
    
    public ArrayList<Cells> getNeighbours(){
        return this.neighbours ;
    }
    
    public boolean getIsWall(){
        return this.isWall;
    }  

    public boolean getIsPlayer(){
        return this.isPlayer;
    }  
    
    /////////////////////////////// Setter /////////////////////////////////////
    
    public void setF(){
        this.f = this.g + this.heuristique;
    }
    
    public void setG (int G){
        this.g = G ;
    }
    
    public void setHeuristique(Cells current, Cells end){
        this.heuristique = Math.abs ( current.getPosX() - end.getPosX()) + Math.abs( current.getPosY() - end.getPosY() ) ;
    }
    
    public void setPosX( int posX){
        this.posX = posX ;
    }
    
    public void setPosY(int posY){
        this.posY = posY ;
    }
    
    public void setPrevious (Cells previous){
        this.previous = previous ;
    }
    
    public void setNeighbours( ArrayList < Cells > cellsList ){
        ArrayList<Cells> neighbour = new ArrayList () ;
        
        try {
        
            for ( Cells cell : cellsList){
                if (this.posX == cell.getPosX() + 1 && this.posY == cell.getPosY() && !cell.getIsWall()){
                    neighbour.add(cell);

                }
                if ( this.posX == cell.getPosX() - 1 && this.posY == cell.getPosY()  && !cell.getIsWall() ){
                    neighbour.add(cell);

                }
                if ( this.posY  == cell.getPosY() + 1 && this.posX == cell.getPosX() && !cell.getIsWall()  ){
                    neighbour.add(cell);
                }
                if ( this.posY  == cell.getPosY() - 1 && this.posX == cell.getPosX() && !cell.getIsWall()  ){
                    neighbour.add(cell);
                }
            this.neighbours = neighbour;
            
            }
        
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println(" Vous etes sortie du plateau");
        }
        
    }
    
    public void setIsWall(boolean status){
        this.isWall = status ;
    }
      
    public void setIsPlayer(boolean status){
        this.isPlayer = status ;
    }
    
    ////////////////////////////// toString ////////////////////////////////////
    @Override
    public String toString (){
        return ("la possition de la céllule " +this.posX +" "+ this.posY + " c'est un mur :"+ this.isWall);
    }

}
