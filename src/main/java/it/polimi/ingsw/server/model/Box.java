package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.piece.Block;
import it.polimi.ingsw.server.model.piece.Dome;
import it.polimi.ingsw.server.model.piece.Piece;

import java.util.Stack;

public class Box {

    //per adesso basta il costruttore di default
    //da vedere ancora il concetto di torre come aggregato di pezzi
    final private int row;
    final private int column;
    private Worker occupier;
    //private Piece upperPiece;
    //private Piece lowerPiece;
    private Stack<Piece> tower;
    private int towerSize;

    public Box (int row, int column){
        this.occupier = null;
        //this.upperPiece = new Piece(0);
        this.tower = new Stack<Piece>();
        tower.add(new Block(0));
        this.row = row;
        this.column = column;
    }

    public int getRow() { return row; }

    public Stack<Piece> getTower() {
        return tower;
    }

    public int getColumn() {
        return column;
    }

    public int getTowerSize() {
        return towerSize;
    }

    public void setTowerSize(int towerSize){
        this.towerSize = towerSize;
    }

    public void increaseLevel () {
        int height = tower.get(tower.size() - 1).getLevel() + 1;
        if (height < 4) {
            tower.add(new Block(height));
            setTowerSize(height);
        }
        else if (height == 4) {
            tower.add(new Dome(height));
            setTowerSize(height);
        }
        else { System.out.println("This tower is complete."); }
    }

    public void decreaseLevel() {
        int height = tower.get(tower.size() - 1).getLevel();
        if (height > 0) {
            tower.remove(tower.size() - 1);
            setTowerSize(height - 1);
        }
        else {System.out.println("This tower doesn't have floors.");}
    }


    public Worker getOccupier() {
        return occupier;
    }

    public void setOccupier(Worker occupier) {
        this.occupier = occupier;
    }

    public String toString() {
        if ((getOccupier() == null) && (tower.size()==1)) {
            return "-- -";
        }
        else if ((getOccupier() != null) && (tower.size()==1)) {
            return getOccupier().getColour()+getOccupier().getWorkerTag()+" -";
        }
        else if ((getOccupier() == null) && (tower.size()>1)) {
            return "-- "+tower.get(tower.size()-1).getLevel();
        }
        else if (getOccupier() != null && tower.size()>1) {
            return getOccupier().getColour()+getOccupier().getWorkerTag()+" "+tower.get(tower.size()-1).getLevel();
        }
        else return " ";
    }

}