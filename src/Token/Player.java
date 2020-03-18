package Token;

import java.util.ArrayList;
import BoardGame.BoardGame;
import Strategy.Strategy;
import Token.Arms.Mine;
import Token.Arms.Weapon;
import Token.Token;

/**
 * <h1>La classe Player</h1>
 * <p>Player est la classe qui correspond aux joueurs ; La class Player hérite de Token</p>
 * <p>La classe Player correspond a toute les action qui concerne le joueur</p>
 * <p>Les attribut de Player sont : </p>
 * <ul>
 *  <li> L'armes que possède le joueur actuellement : currentWeapon (Weapon) </li>
 *  <li> La liste des armes que possède le joueur : playerWeapon (ArrayList (Weapon)) </li>
 *  <li> Un index afin de changer d'arme lors des tests : currentIndex(int)  </li>
 *  <li> Son Energie : currentsEnergie (int) </li>
 *  <li> La strategie propre a chaque joueur : strategy (Strategy) </li>
 * </ul>
 *
 * @author  BAUDIN Arthur, MEZZA Nathan
 */
public class Player extends Token{
    
    private Weapon currentWeapon;
    private ArrayList<Weapon> playerWeapon = new ArrayList();
    private int currentIndex = 0;
    private int currentEnergie ;
    private Strategy strategy;
    
    
    //////////////////////////// Constructeur //////////////////////////////////
    public Player (int posX,int posY, BoardGame board,Strategy strategy){
        super(posX,posY,board);
        this.currentEnergie = 100;
        this.strategy = strategy;
    }
    
    ////////////////////////// Methodes Armes //////////////////////////////////
    /**
     * Effectue les actions correspondant à la strategie du joueur
     */
    public void action(){
        this.strategy.action();
    }
    
    /**
     * 
     * Méthode qui permet de changer d'arme a l'aide de l'index courant du joueur.
     * pour cela nous ajoutons 1 a l'index tous en vérifiant si currentIndex est suppérieur ou non a la taille de la liste
     * si cela est vrai alors on remet l'index a 0 .
     * 
     */
    public void switchWeaponUp() {
        if (currentIndex + 1 >= playerWeapon.size()) { // on vérifie si l'index n'est pas suppérieur a la taille de la liste.
            currentIndex = 0;

        } else { // si c'est faux alors on rajoute  1 a l'index
            currentIndex++;
        }

        this.currentWeapon = playerWeapon.get(currentIndex++); // puis on change l'arme courante
    }

    /**
     * 
     * Méthode qui permet de changer d'arme a l'aide de l'index courant du joueur.
     * pour cela nous retirons 1 a l'index tous en vérifiant si currentIndex est inférieur ou non a la taille de la liste
     * si cela est vrai alors on remet l'index a 0 .
     * 
     */
    public void switchWeaponDown() {
        if (currentIndex <= 0) {// on vérifie si l'index n'est pas inférieur a la taille de la liste.
            currentIndex = this.playerWeapon.size() - 1;

        } else {// si c'est faux alors on retire  1 a l'index
            currentIndex--;
        }

        this.currentWeapon = playerWeapon.get(currentIndex);// puis on change l'arme courante

    }
    
    /**
     * 
     * Cette fonction appèle la méthode use de l'arme courante du joueur
     * 
     */
    public void useCurrentWeapon(){
        this.currentWeapon.use(this.posX,this.posY,this);
    }
    
    /////////////////////// Methodes de Deplacement ////////////////////////////
    /**
     * Récupère un input précis puis appèle la méthode de déplacement
     * correspondante a la direction donnée.
     *
     * @param str La direction choisit
     *
     */
    public void inputMove(char str) {

        switch (str) {
            case RIGHT:
                //methode de déplacement vers la droite;
                this.right();
                break;

            case LEFT:
                //methode de déplacement vers la gauche;
                this.left();
                break;

            case UP:
                //methode de déplacement vers le haut;
                this.up();
                break;

            case DOWN:
                //methode de déplacement vers le bas;
                this.down();
                break;
        }
        this.board.setBoardGame();
    }

    /**
     *
     * Effectue un déplacement du personnage vers l'avant.
     *
     *
     */
    private void up() {

        if (posibilityMove(this.posX - 1, this.posY)) {
            
            if(thereIsAMine(this.posX - 1, this.posY)){
                
                this.getDamage(((Mine)this.board.getBoardGame()[this.posX - 1][this.posY]).getDamage());
            }
            
            this.board.getBoardGamePrint()[this.posX - 1][this.posY] = this.board.getBoardGamePrint()[this.posX][this.posY];
            this.board.getBoardGamePrint()[this.posX][this.posY] = ' ';
            
            this.board.getBoardGame()[this.posX - 1][this.posY] = this.board.getBoardGame()[this.posX][this.posY];
            this.board.getBoardGame()[this.posX][this.posY] = null;
            
            this.posX = this.posX - 1;
        }

    }

    /**
     *
     * Effectue un déplacement du personnage vers le bas. en vérifiant si le
     * personnage peux ce déplacer et si oui ou non il déplace une boite
     *
     */
    private void down() {
        if (posibilityMove(this.posX + 1, this.posY)) {
            
            if(thereIsAMine(this.posX + 1, this.posY)){
                
                this.getDamage(((Mine)this.board.getBoardGame()[this.posX + 1][this.posY]).getDamage());
            }

            this.board.getBoardGamePrint()[this.posX + 1][this.posY] = this.board.getBoardGamePrint()[posX][posY];
            this.board.getBoardGamePrint()[this.posX][this.posY] = ' ';
            
            this.board.getBoardGame()[this.posX + 1][this.posY] = this.board.getBoardGame()[this.posX][this.posY];
            this.board.getBoardGame()[this.posX][this.posY] = null;
            
            this.posX = this.posX + 1;

        }
    }

    /**
     *
     * Effectue un déplacement du personnage vers la gauche. en vérifiant si le
     * personnage peux ce déplacer et si oui ou non il déplace une boite
     *
     */
    private void left() {
        if (posibilityMove(this.posX, this.posY - 1)) {
            
            if(thereIsAMine(this.posX, this.posY - 1)){
                
                this.getDamage(((Mine)this.board.getBoardGame()[this.posX][this.posY - 1]).getDamage());
            }
            this.board.getBoardGamePrint()[this.posX][this.posY - 1] = this.board.getBoardGamePrint()[this.posX][this.posY];
            this.board.getBoardGamePrint()[this.posX][this.posY] = ' ';
            
            this.board.getBoardGame()[this.posX][this.posY - 1] = this.board.getBoardGame()[this.posX][this.posY];
            this.board.getBoardGame()[this.posX][this.posY] = null;
            
            this.posY = this.posY - 1;
            
        }
    }

    /**
     * Effectue un déplacement du personnage vers la gauche. en vérifiant si le
     * personnage peux ce déplacer et si oui ou non il déplace une boite
     *
     */
    private void right() {
        if (posibilityMove(this.posX, this.posY + 1)) {
            
            if(thereIsAMine(this.posX, this.posY + 1)){
                
                this.getDamage(((Mine)this.board.getBoardGame()[this.posX][this.posY + 1]).getDamage());
            }
           
            this.board.getBoardGamePrint()[this.posX][this.posY + 1] = this.board.getBoardGamePrint()[posX][posY];
            this.board.getBoardGamePrint()[this.posX][this.posY] = ' ';
            
            this.board.getBoardGame()[this.posX][this.posY + 1] = this.board.getBoardGame()[this.posX][this.posY];
            this.board.getBoardGame()[this.posX][this.posY] = null;
            
            this.posY = posY + 1;

            
        }

    }

    /**
     * 
     * Vérifie si le mouvement est possible. Vérifie si le Token a la possition X Y n'est pas un mur, un mur cassable, un joueur ou une bombe
     * 
     * @param posX La future possition X du joueur 
     * @param posY La future possition Y du joueur 
     * 
     * @return Si nous pouvons accéder a cette case sans risque
     * 
     */
    private boolean posibilityMove(int posX, int posY) {
        return this.board.getBoardGamePrint()[posX][posY] != '#' && this.board.getBoardGamePrint()[posX][posY] != '*'&& this.board.getBoardGamePrint()[posX][posY] != 'p' && this.board.getBoardGamePrint()[posX][posY] != 'b' ;
    }
    
    /**
     * 
     * Vérifie si la prochaine position du joueur sera une line ou non
     * 
     * @param posX La future possition X du joueur 
     * @param posY La future possition Y du joueur 
     * 
     * @return Si la zone vers la quelle le joueur ce dirige est une mine ou pas
     */
    private boolean thereIsAMine(int posX, int posY){
        return this.board.getBoardGamePrint()[posX][posY] == 'm';
    }
    
    /*
    private boolean ifOwnerMine(Mine mine){
        return ((Mine)this.board.getBoardGame()[this.posX][posY + 1]).getOwner() == this;
    }*/
    
    /**
     * 
     * S'acctive lorsque le jouer recevera des degats, il perdera alors de l'énergie;
     * 
     * @param damage Les degats reçus par le joueur
     * 
     */
    private void getDamage(int damage){
        this.setCurrentEnergie(this.currentEnergie - damage);
    }
    
    /////////////////////////////// Getter /////////////////////////////////////
    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    public ArrayList<Weapon> getPlayerWeapon() {
        return playerWeapon;
    }
    
    public int getCurrentIndex() {
        return currentIndex;
    }
    
    public int getCurrentEnergie(){
        return this.currentEnergie;
    }

    public Strategy getStrategy() {
        return strategy;
    }
    
    /////////////////////////////// Setter /////////////////////////////////////
    public void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public void setPlayerWeapon(ArrayList<Weapon> playerWeapon) {
        this.playerWeapon = playerWeapon;
        this.currentWeapon = playerWeapon.get(0);
    }
    
    public void setCurrentEnergie(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void addWeapon(Weapon weapon){
        this.playerWeapon.add(weapon);
    }
    
    @Override
    public String toString(){
        return "le Joueur est situé en "+posX + " "+ posY + "  Strategie " + this.strategy;
        
    }
    
}
