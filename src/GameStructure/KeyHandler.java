package GameStructure;
import GameComponents.SquareColor;
import GameComponents.World;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyHandler extends KeyAdapter{

    public KeyHandler(){

    }
    public void keyPressed(KeyEvent e) {


        Integer key = e.getKeyCode();
        System.out.println("DBG  key code pressed: " + key);
        if (key.equals(37)) {
            //left arrow
            World.getInstance().moveTetronimoLeft();
        }
        else if (key.equals(39)) {
            //right arrow
            World.getInstance().moveTetronimoRight();
        }
        else if (key.equals(38)) {
            //up arrow
            World.getInstance().rotateTetronimo();
        }
        else if (key.equals(40)) {
            //down arrow
            World.getInstance().moveTetronimoDown();
        } else if (key.equals(71)) {
            //g key
            World.getInstance().generateTetronimo(SquareColor.LIGHT_BLUE);
        }
    }

    public void keyReleased(KeyEvent e){
        Integer key = e.getKeyCode();
        System.out.println("DBG  key code releasedssdfdsd: " + key);
        if (key.equals(37)) {
            //left arrow?

            System.out.println("DBG  left released");
        }
        else if (key.equals(38)) {
            System.out.println("DBG  down released");
            //up arrow?
        }
        else if (key.equals(39)) {
            System.out.println("DBG  right released");
            //right arrow?
        }
        else if (key.equals(40)) {
            System.out.println("DBG up released");
            //down arrow?
        }
    }
}
