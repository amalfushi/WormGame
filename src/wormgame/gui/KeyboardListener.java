package wormgame.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import wormgame.Direction;
import wormgame.domain.Worm;

public class KeyboardListener implements KeyListener{
    
    private Worm worm;
    
    public KeyboardListener(Worm worm) {
        this.worm = worm;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            case KeyEvent.VK_UP:
                worm.setDirection(Direction.UP);
                break;
            case KeyEvent.VK_LEFT:
                worm.setDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_DOWN:
                worm.setDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_RIGHT:
                worm.setDirection(Direction.RIGHT);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
    
    
}
