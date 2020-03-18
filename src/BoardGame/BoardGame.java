package BoardGame;

import Strategy.Alea;
import Strategy.Astar.RushStrategy;
import Strategy.Strategy;
import Token.Arms.Mine;
import Token.Arms.Bomb;
import Token.Player;
import Token.Wall;
import Token.Token;
import java.util.ArrayList;
import levels.Levels;
import levels.Levels_list;

/**
 * <h1>La classe BoardGame</h1>
 * <p>BoardGame est la classe qui correspond aux plateau de chaque joueurs</p>
 * <p>La classe BoardGame correspond a toute les action qui concerne le plateau</p>
 * <p>Les attribut de BoardGame sont : </p>
 * <ul>
 *  <li> La liste des Joueurs sur le terrain : players (ArrayList (Player))</li>
 *  <li> Le plateau de jeu : boardGame(Token[][])  </li>
 *  <li> La liste des bombes sur le terrain : bombes (ArrayList(Bomb)) </li>
 *  <li> La liste des mines sur le terrain : mines (ArrayList(Mines)) </li>
 * </ul>
 *
 * @author  BAUDIN Arthur, MEZZA Nathan
 */

public class BoardGame {

    private Player player;
    private ArrayList<Player> players;
    private char [][] boardGamePrint;
    private Token[][] boardGame;

    private ArrayList<Bomb> bombes = new ArrayList();
    private ArrayList<Mine> mines = new ArrayList();

    
    private final Levels_list levels_list;
    private ArrayList<Levels> levels = new ArrayList();
    private int lvlNumber = 0;

    //////////////////////////// Constructeur //////////////////////////////////
    /**
    *
    * Constructeur de la class BoardGame.
    * @param board le plateau de char qui vas être convertie 
    * 
    */
    public BoardGame(char[][] board){
        this.boardGamePrint = board;
        this.setBoardGame();
        this.setPlayer();
        this.setBombes();
        this.setMines();
        this.levels_list = null;
    }

    /**
    *
    * Constructeur par défault de la class BoardGame
    *
    */
    public BoardGame(Levels_list levels_list){
        this.levels_list = levels_list;
        this.levels = levels_list.getLevels();       
        this.boardGamePrint = levels.get(lvlNumber).getLevel();
        
        try{
            this.setPlayer() ;
            this.setBoardGame();
            this.setBombes();
        }
        catch(Exception e){
        
        }
    }

    /////////////////////////////// Getter /////////////////////////////////////
    /**
    *
    * Getter du player (test)
    * @return le joueur du plateau
    *
    */
    public Player getPlayer() {
        return player;
    }

    /**
    *
    * Getter des players situés sur le plateau
    * @return les joueurs du plateau
    *
    */
    public ArrayList <Player> getPlayers() {
        return players;
    }


    /**
    *
    *Getter du plateau de char passer en argument au constructeur
    *@return le plateau de char
    *
    */
    public char[][] getBoardGamePrint() {
        return boardGamePrint;
    }


    /**
    *
    *Getter du plateau de Token
    *@return le plateau de Token
    *
    */
    public Token[][] getBoardGame() {
        return boardGame;
    }

    /**
    *
    *Getter de la liste des bombes situé sur le plateau
    *@return la liste des bombes sur le plateau
    *
    */
    public ArrayList<Bomb> getBombes() {
        return bombes;
    }

    /**
    *
    *Getter de la liste des mines situé sur le plateau
    *@return la liste des mines sur le plateau
    *
    */
    public ArrayList<Mine> getMines() {
        return mines;
    }

    /////////////////////////////// Setter /////////////////////////////////////

    /**
     *
     * Permet de set les joueurs sur le plateau de jeu. Pour cela il parcourt le plateau boardGamePrint
     * qui correspond a notre plateau de mais fais de char et a chaque lieux ou le caractère 'p' est présent
     * place un Token player sur le nouveau terrain.
     *
     */
    public void setPlayer() {
        ArrayList <Player> players = new ArrayList();
        for (int i = 0 ; i < this.boardGamePrint.length; i++){

            for (int j = 0; j < this.boardGamePrint[1].length; j++){

                if(this.boardGamePrint[i][j] == 'p'){
                    players.add(new Player(i,j,this,null));
                }
            }
        }
       // this.player = players.get(0);
        this.setPlayers(players);
        this.setEquipements();
        /*RushStrategy rush = new RushStrategy(this.player);
        this.player.setStrategy(rush);
        this.player.addWeapon(new Mine(-1,-1,this,this.player));
        */
        
    }

    /**
     * 
     * (Provisoire) permet de préparer les joueur du platerau en leur donnant une arme et une strategie
     * 
     */
    public void setEquipements(){
        ArrayList<Strategy> test = new ArrayList();
        test.add(new RushStrategy());
        test.add(new Alea());
        int i = 0;
        for (Player player : this.players){
            player.addWeapon(new Mine(-1,-1,this,player));
            player.setStrategy(test.get(i));
            player.getStrategy().setPlayer(player);
            
            
            i++;
        }
    }

    /**
     *
     * Cette méthode permet de set le plateau de jeu selon la liste boardGamePrint et place a chaque zone
     * le Token correspondant au caractère lue.
     *
     */
    public void setBoardGame() {
        Token[][] board = new Token[this.boardGamePrint.length][this.boardGamePrint[1].length]; // crée un plateau de Token correspondant a la taille du plateau
                                                                                                //passé en argument dans le constructeur
        for (int i = 0 ; i < this.boardGamePrint.length; i++){

            for (int j = 0; j < this.boardGamePrint[1].length; j++){
                char current = this.boardGamePrint [i][j];

                switch(current) { // current correspond au caractère a l'emplacement (i,j) sur le plateau de char
                    case ' ' :
                        board[i][j] = null;
                        break ;

                    case 'p' :
                        board[i][j] = new Player(i,j,this,null);
                        break ;

                    case 'b' :
                        board[i][j] = new Bomb(i,j,this);
                        break ;

                    case 'm' :
                        board[i][j] = new Mine(i,j,this,null);
                        break ;

                    case '#' :
                        board[i][j] = new Wall(i,j,this);
                        break ;

                }
            }
        }
        this.boardGame = board;
    }



    /**
     *
     * Permet d'ajouter les mines qui ce trouve sur le plateau de jeu. Pour cela il parcourt le plateau boardGamePrint
     * qui correspond a notre plateau de mais fais de char et a chaque lieux ou le caractère 'b' est présent
     * ajoute un Token Bomb a la liste.
     *
     */
    public void setBombes() {
        for (int i = 0 ; i < this.boardGamePrint.length; i++){

            for (int j = 0; j < this.boardGamePrint[1].length; j++){

                if(this.boardGamePrint[i][j] == 'b'){
                    this.bombes.add(new Bomb(i,j,this));
                }

            }
        }
    }


    /**
     *
     * Permet d'ajouter les mines qui ce trouve sur le plateau de jeu. Pour cela il parcourt le plateau boardGamePrint
     * qui correspond a notre plateau de mais fais de char et a chaque lieux ou le caractère 'm' est présent
     * ajoute un Token Mine a la liste.
     *
     */
    public void setMines() {
        for (int i = 0 ; i < this.boardGamePrint.length; i++){

            for (int j = 0; j < this.boardGamePrint[1].length; j++){

                if(this.boardGamePrint[i][j] == 'm'){
                    this.mines.add(new Mine(i,j,this,null));
                }
            }
        }
    }

    /**
     * 
     * Permet de set le plateau de caractère afin de l'afficher en console plus tard
     * @param boardGamePrint Le plateau de char qui correspond au plateau de jeu
     * 
     */
    public void setBoardGamePrint(char[][] boardGamePrint) {
        this.boardGamePrint = boardGamePrint;
    }

    
    /**
     * 
     * Permet de set la liste des joueur présent sur le plateau 
     * @param players la liste des joueur sur le plateau
     *
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
        if (players.size() !=0)
            this.player = players.get(0);
    }

    /**
     *
     * Affiche le plateau sur l'inviter de commande
     *
     */
    public void printBoard () {
        for( int i = 0; i < this.boardGamePrint.length; i++) {
                System.out.println(" ");
            for( int j = 0; j < this.boardGamePrint[i].length; j++) {
                System.out.print(" "+this.boardGamePrint[i][j]);
            }
        }
    }
    
    public static char[][] randomBoard(int width, int height, int nbP, int nbW, int nbM, int nbB) {
        char[][] bc = new char[width][height];
        int nbPlayerToSet = nbP;
        int nbMinesToSet = nbM;
        int nbBombsToSet = nbB;
        int nbWallToSet = nbW;
        for (int j = 0; j < height; j ++) {
            for (int i = 0; i < width; i++) {  
                if ( i == 0  || j == height - 1 || j == 0 || i == width - 1 )
                    bc[i][j] = '#';
                else if ( nbPlayerToSet !=0 ){
                    if (Math.random() > 0.8) {
                        bc[i][j] = 'p';
                        nbPlayerToSet -= 1;
                    }
                }
                else if ( nbWallToSet !=0 ){
                    if (Math.random() > 0.8) {
                        bc[i][j] = '*';
                        nbWallToSet -= 1;
                    }
                }
                else if ( nbMinesToSet !=0 ){
                    if (Math.random() > 0.8) {
                        bc[i][j] = 'm';
                        nbMinesToSet -= 1;
                    }
                }
                else if ( nbBombsToSet !=0 ){
                    if (Math.random() > 0.8) {
                        bc[i][j] = 'b';
                        nbBombsToSet -= 1;
                    }
                }
                else
                    bc[i][j] = ' ';                
            }
        }
             
        return bc;
    }
        
}
