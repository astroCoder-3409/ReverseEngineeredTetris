package GameStructure;

import GameComponents.World;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter{
    private boolean dbg = false;
    public KeyHandler(){

    }

    public void keyPressed(KeyEvent e) {
        Integer key = e.getKeyCode();
        if(dbg) System.out.println("DBG  key code pressed: " + key);

        if (key.equals(KeyEvent.VK_LEFT)) {
            World.getInstance().moveTetronimoLeft();
        }
        else if (key.equals(KeyEvent.VK_RIGHT)) {
            World.getInstance().moveTetronimoRight();
        }
        else if (key.equals(KeyEvent.VK_UP)) {
            World.getInstance().rotateTetronimo();
        }
        else if (key.equals(KeyEvent.VK_DOWN)) {
            World.getInstance().moveTetronimoDown();
        }
    }

    public void keyReleased(KeyEvent e){
    }
}
