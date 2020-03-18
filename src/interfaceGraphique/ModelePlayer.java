/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGraphique;

import BoardGame.BoardGame;
import Token.Player;
import java.awt.List;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 21602916
 */
public class ModelePlayer extends AbstractTableModel{
    
    private final ArrayList<Player> players;
    private final String[] entetes = {"Player", "Energie", "CurrentWeapon"};
    
    public ModelePlayer(BoardGame b) {
        super();
        players = b.getPlayers();
    }
    
    @Override
    public int getRowCount() {
        return players.size();
    }

    @Override
    public int getColumnCount() {
        return entetes.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        switch(columnIndex) {
            case 0:
                return "Player "+(rowIndex+1);
            case 1:
                return players.get(rowIndex).getCurrentEnergie();
            case 2:
                return players.get(rowIndex).getCurrentWeapon();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int i) {
        return entetes[i];
    }
    
    
}
