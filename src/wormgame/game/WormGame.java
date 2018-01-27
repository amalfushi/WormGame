package wormgame.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import wormgame.domain.*;
import wormgame.Direction;
import wormgame.gui.Updatable;
import java.util.Random;

public class WormGame extends Timer implements ActionListener {

    private int width;
    private int height;
    private boolean continues;
    private Updatable updatable;
    private Worm worm;
    private Apple apple;
    private Random rand;

    public WormGame(int width, int height) {
        super(1000, null);

        this.width = width;
        this.height = height;
        this.continues = true;
        this.worm = null;
        this.apple = null;
        this.rand = new Random();
        setWorm(worm);
        setApple(apple);

        addActionListener(this);
        setInitialDelay(2000);

    }


    public boolean continues() {
        return continues;
    }

    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setWorm(Worm worm) {
        if (worm==null){
            this.worm = new Worm(width/2, height/2, Direction.DOWN);
        } else {
            this.worm = worm;
        }
    }
    
    public Worm getWorm() {
        return worm;
    }

    public void setApple(Apple apple){
        int x = rand.nextInt(width);
        int y = rand.nextInt(height);
        
        while (worm.headPiece().getX()==x && worm.headPiece().getY()==y){
            x = rand.nextInt(width);
            y = rand.nextInt(height);
        }
        
        if (apple == null){
            this.apple = new Apple(x,y);
        } else {
            this.apple = apple;
        }
    }

    public Apple getApple() {
        return apple;
    }
    
    public boolean hitEdge(){
        boolean helper = false;
        if(worm.headPiece().getX() == 0 && worm.getDirection() ==Direction.LEFT){
            helper = true;
        } else if (worm.headPiece().getX() == width-1 && worm.getDirection() == Direction.RIGHT){
            helper = true;
        } else if (worm.headPiece().getY() ==  0  && worm.getDirection() == Direction.UP){
            helper = true;
        } else if (worm.headPiece().getY() == height-1 && worm.getDirection() == Direction.DOWN){
            helper = true;
        }
        return helper;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!continues) {
            return;
        }
        
        if (hitEdge()){
            continues = false;
            return;
        }
        
        worm.move();
        
        if(worm.runsIntoItself()){
            continues = false;
            return;
        } else if(worm.runsInto(apple)){
            worm.grow();
            this.apple = null;
            setApple(apple);
        }
        
        updatable.update();
        super.setDelay(1000/worm.getLength());
    }
}
