package Strategy.Astar;

import Strategy.Strategy;
import java.util.ArrayList;

/**
 * <h1>La classe <a href = "https://en.wikipedia.org/wiki/A*_search_algorithm">AStar</a> </h1>
 * <p>La classe AStar est la classe qui correspond a l'algorithme pour touver le chemin le plus optimiser vers une cible.</p>
 * 
 * 
 * @author  BAUDIN Arthur, MEZZA Nathan
 */

public class Astar {

    private ArrayList<Cells> path;
    private ArrayList<Character> moveToTarget; 
    private Cells start, end ;
    
    //////////////////////////// Constructeur //////////////////////////////////
    
    public Astar ( ArrayList<Cells> path, Cells start, Cells end){
        this.path = path ;
        this.start = start ;
        this.end = end ;
    }
    
    public Astar () {
        this(null,null,null);
    }
    
    ////////////////////////////// Methode /////////////////////////////////////
    
    /**
     * Méthode correspondante a l'algorithme A* 
     */
    public void pathFinding (){
       // System.out.println(" \n Start = " + start +" ||||||| end = " + end + "\n");
        ArrayList<Cells> openSet = new ArrayList();
        ArrayList<Cells> closeSet = new ArrayList () ;
        
        openSet.add(this.start); // met dans openSet la première Cells  
        
        while(!openSet.isEmpty()){
            
           // Cherche la Cells la plus proche de la cible dans OpenSet 
           // A l'aide de leurs F, et G
            
            int win = 0 ;
            for (int i = 0 ; i<openSet.size();i++ ){
                
                if (openSet.get(i).getF() < openSet.get(win).getF()){
                    win = i ;
                }
                if (openSet.get(i).getF() == openSet.get(win).getF()){
                    if(openSet.get(i).getG() > openSet.get(win).getG()){
                        win = i ;
                    }
                }
            }  
            
            Cells current = openSet.get(win);

            // Si la Cells choisit correspond a la cible alors on arretè l'algo.
            if (current == this.end){
                    this.toGoal(current);
                    break;
            }

            openSet.remove(current); // On a parcourut cette célule, on la retire de openSet
            closeSet.add(current); // et on la met dans la closeSet

            ArrayList <Cells> neighbours = current.getNeighbours();

           for(int i = 0 ; i < neighbours.size() ; i++){ // on parcout la liste des voisin de la Cells courante
                
                if (!closeSet.contains(neighbours.get(i))){ // On regarde si ce voisin est dans closeSet

                    int tempG = current.getG() +1 ;
                    //System.out.println("openSet  " + openSet);
                    if(!openSet.contains(neighbours.get(i))){ // si il n'est pas dans closeSet, on regarde si il est dans openSet
                        openSet.add(neighbours.get(i));
                    }
                    
                    neighbours.get(i).setG(tempG);                                  // on set la valeur G du voisin 
                    neighbours.get(i).setHeuristique(neighbours.get(i), this.end);  // on set sont Heuristique
                    neighbours.get(i).setF();                                       // on set sa valeur F
                    neighbours.get(i).setPrevious(current);                         // puis on lui ajoute la Cells qui le précédait
                }
            }
        }
    }
    
    /**
     * Cette méthode parcourt tous les précédant de end afin de retrouver le chemin a emprunter 
     * @param end La Cible du joueur qui utilise cette classe
     */
    private void toGoal(Cells end){
        ArrayList<Cells> pathToGoal = new ArrayList() ;
        Cells temp = end;
        pathToGoal.add(temp);

        while (temp.getPrevious()!= null){
            pathToGoal.add(temp.getPrevious());
            temp = temp.getPrevious();
        }
        this.setPath(pathToGoal);
        this.directionToTarget();
    }
    
    /**
     * Cette méthode permet de retranscrire le chemin de Cells en une liste de charactère a suivre
     */
    public void directionToTarget(){
        ArrayList<Character> path = new ArrayList ();
        for (int i = 0; i < this.path.size()-1; i++){
            path.add(moveToNextStep(this.path.get(i),this.path.get(i+1)));
        }
        path.remove(path.size() - 1);
        this.moveToTarget = path;
    }
    
    /**
     * 
     * Cette méthode permet de trouver la direction a prendre pour ce rendre de la Cell firstStep a nextStep
     * 
     * @param firstStep La position de la Cells actuel
     * @param nextStep La possition de la prochaine Cells
     * 
     * @return le char correspondant a la direction a prendre
     */
    public char moveToNextStep(Cells firstStep, Cells nextStep){
        int valPosX = firstStep.getPosX() - nextStep.getPosX();
        int valPosY = firstStep.getPosY() - nextStep.getPosY();
        
            switch(valPosX){
                case -1 :
                    return Strategy.DOWN;
                    
                case 1 :
                    return Strategy.UP;
                    
                default :
                    switch(valPosY){
                        
                        case -1 :
                            return Strategy.RIGHT;
                    
                        case 1 :
                            return Strategy.LEFT;
                        
                        default :
                            return '\0';
                    }
                    
            }
    }
    
    /////////////////////////////// Getter /////////////////////////////////////
    
    public ArrayList<Cells> getPath () {
        return this.path ;
    }

    public ArrayList<Character> getMoveToTarget() {
        return moveToTarget;
    }
    
    
    
    /////////////////////////////// Setter /////////////////////////////////////
       
    public void setPath (ArrayList<Cells> path){
        ArrayList<Cells> pathOrdo = new ArrayList() ;
        for (int i = path.size()-1 ; i >= 0 ; i--){
            pathOrdo.add(path.get(i));  
        }
        this.path = pathOrdo;
    }
    
    public void setStart (Cells start){
        this.start = start ;
    }
    
    public void setEnd (Cells end){
        this.end = end ;
    }

    ////////////////////////////// ToString ////////////////////////////////////
    
    @Override
    public String toString (){
        return ("le chemin le plus optimisé est : " +this.path );
    }
}
