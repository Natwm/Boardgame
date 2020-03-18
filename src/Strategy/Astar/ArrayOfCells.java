/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategy.Astar;

import BoardGame.BoardGame;
import Token.Player;
import java.util.ArrayList;

/**
 *
 * @author Natw
 */
public class ArrayOfCells {

    private ArrayList<Cells> arrayOfCells;
    private ArrayList<Cells> arrayOfPlayer;
    private Cells start;
    private Cells end;

    //////////////////////////// Constructeur //////////////////////////////////
    /**
     * Constructeur de la class ArrayOfCells
     * @param user Le joueur correspondant au start pour la class Astar 
     */
    public ArrayOfCells(Player user) {
        this.setArrayOfCells(user.getBoard());
        this.start = this.whereIsPlayer(user);   
    }

    ////////////////////////////// Methode /////////////////////////////////////
    /**
     * Met a jour la liste des Cells
     * @param player le joueur correspondant au start pour la class Astar
     */
    public void update(Player player){
        this.setArrayOfCells(player.getBoard());
        this.start = this.whereIsPlayer(player);
    }
    
    /**
     * Permet de retrouver un joueur précis dans la liste des cells 
     * @param player le joueur que l'on cherche
     * @return La Cells correspondante au joueur rechercher
     */
    public Cells whereIsPlayer(Player player){
        for(Cells cell : this.arrayOfCells){
            if (cell.getPosX() == player.getPosX() && cell.getPosY() == player.getPosY()  )
                return cell;
        }
        return null;
    }
    
    /**
     * 
     * Permet de set la variable arrayOfPlayer de la class , afin de trouver un
     * joueur plus rapidement avec la méthode whoIsIt()
     * 
     */
    public void whoIsPlayers(){
        ArrayList<Cells> arrayOfPlayer = new ArrayList();
        for(Cells cell : this.arrayOfCells){
            if (cell.getIsPlayer())
                arrayOfPlayer.add(cell);
        }
        this.arrayOfPlayer = arrayOfPlayer;
    }
    
    /////////////////////////////// Getter /////////////////////////////////////
    public ArrayList<Cells> getArrayOfCells() {
        return arrayOfCells;
    }

    public ArrayList<Cells> getArrayOfPlayer() {
        return arrayOfPlayer;
    }

    public Cells getStart() {
        return start;
    }
    
    /////////////////////////////// Setter /////////////////////////////////////
    /**
     * Permet de concevoir la liste des Cells depuis un objet BoardGame
     * a chaque fois qu'il rencontre un élément dans le tablea ajoute une Cells correspondant
     * a ses caractéristiques
     * 
     * @param boardGame Le plateau de jeux actuel
     * 
     */
    public void setArrayOfCells(BoardGame boardGame) {
        ArrayList<Cells> cells = new ArrayList();
        for (int i = 0; i < boardGame.getBoardGamePrint().length; i++) {
            for (int j = 0; j < boardGame.getBoardGamePrint()[i].length; j++) {
                if (boardGame.getBoardGamePrint()[i][j] == '#' || boardGame.getBoardGamePrint()[i][j] == '*') {
                    cells.add(new Cells(0, 0, 0, i, j, null, null, true,false));
                } else if  (boardGame.getBoardGamePrint()[i][j] == 'p'){
                    cells.add(new Cells(0, 0, 0, i, j, null, null, false,true));
                }else{
                    cells.add(new Cells(0, 0, 0, i, j, null, null, false,false));
                }
            }
        }
        this.setNeighbourgOfCell(cells);
        this.arrayOfCells = cells;
        this.whoIsPlayers();  
    }
    
    /**
     * Permet de set la liste des Cells correspondants au joueur 
     * @param arrayOfPlayer la liste des Cells correspondants au joueurs
     */
    public void setBoardOfCells(ArrayList<Cells> arrayOfPlayer) {
        this.arrayOfPlayer = arrayOfPlayer;
    }
    
    /**
     * Per met de set les voisins de chaque cells présent dans la liste.
     * @param cells la liste des cell qui correspond au plateau de jeu
     */
    private void setNeighbourgOfCell(ArrayList<Cells> cells){
        for(Cells cell : cells){ //int i = 0 ; i < cells.size(); i++ ){
            if(!cell.getIsWall()){
                cell.setNeighbours(cells);
            }
        }
    }
/////////////////////////////// ToString /////////////////////////////////////
    
    @Override
    public String toString(){
        return "La liste des Cells :" + this.arrayOfCells;
    }
    
}
