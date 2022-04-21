package snack_game;



import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeListener implements KeyListener {

    Snake playerSnack;

    public SnakeListener(Snake playerSnack) {
        this.playerSnack = playerSnack;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                if(playerSnack.getDirection() != KeyEvent.VK_LEFT)
                    playerSnack.setDirection(KeyEvent.VK_RIGHT);
                break;

            case KeyEvent.VK_LEFT:
                if(playerSnack.getDirection() != KeyEvent.VK_RIGHT)
                    playerSnack.setDirection(KeyEvent.VK_LEFT);
                break;

            case KeyEvent.VK_UP:
                if(playerSnack.getDirection() != KeyEvent.VK_DOWN)
                    playerSnack.setDirection(KeyEvent.VK_UP);
                break;

            case KeyEvent.VK_DOWN:
                if(playerSnack.getDirection() != KeyEvent.VK_UP)
                    playerSnack.setDirection(KeyEvent.VK_DOWN);
                break;
        }
        }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
