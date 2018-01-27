package wormgame.domain;

import java.util.List;
import java.util.ArrayList;
import wormgame.Direction;

public class Worm {
    
    private int x;
    private int y;
    private Direction direction;
    private List<Piece> pieces;
    private Boolean growth;
    

    public Worm(int originalX, int originalY, Direction originalDirection) {
        this.x = originalX;
        this.y = originalY;
        this.direction = originalDirection;
        this.pieces = new ArrayList<Piece>();
        pieces.add(new Piece(x,y));
        growth = false;
    }

    public Direction getDirection(){
        return direction;
    }
    
    public void setDirection(Direction dir) {
        this.direction = dir;
    }
    
    public int getLength(){
        return getPieces().size();
    }
    
    public List<Piece> getPieces(){
        return pieces;
    }
        
    public void move(){
        //continues growing until worm is 3x
        if(pieces.size()<3){
            growth = true;
        }
        //removes tail if growth is disabled
        if(growth==false){
            pieces.remove(0);
        }
        
        switch (direction) {
            case DOWN:
                y++;
                break;
            case LEFT:
                x--;
                break;
            case UP:
                y--;
                break;
            case RIGHT:
                x++;
                break;
            default:
                break;
        }
        pieces.add(new Piece(x,y));
        
        //disables growth above 3x OR if it's been turned back on (apple)
        if(pieces.size() >= 3){
            growth = false;
        }
    }
    
    //stupid as fuck
    public void grow(){
        growth = true;
    }
    
    public boolean runsInto(Piece piece){
        boolean helper = false;
        for(Piece otherPiece : pieces){
            if (piece.getX() == otherPiece.getX() &&
                    piece.getY() == otherPiece.getY()){
                helper = true;
            }
        }
        return helper;
    }
    
    public Piece headPiece(){
        return pieces.get(pieces.size()-1);
    }
    
    public boolean runsIntoItself(){
        boolean helper = false;
        for(Piece otherPiece : pieces){
            if (otherPiece.equals(headPiece())){
                continue;
            }
            if(headPiece().getX() ==  otherPiece.getX()
                    && headPiece().getY() == otherPiece.getY()){
                helper = true;
            }
        }
        return helper;
    }
}
