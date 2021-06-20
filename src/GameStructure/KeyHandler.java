package GameStructure;
import GameComponents.SquareColor;
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
        if (key.equals(37)) {
            //left arrow
            //World.getInstance().reqMoveLeft();
            World.getInstance().moveTetronimoLeft();
        }
        else if (key.equals(39)) {
            //right arrow
            //World.getInstance().reqMoveRight();
            World.getInstance().moveTetronimoRight();
        }
        else if (key.equals(38)) {
            //up arrow
            //World.getInstance().reqRotate();
            World.getInstance().rotateTetronimo();
        }
        else if (key.equals(40)) {
            //down arrow
            //World.getInstance().reqMoveDown();
            World.getInstance().moveTetronimoDown();
        } else if (key.equals(71)) {
            //g key
            World.getInstance().generateTetronimo(SquareColor.LIGHT_BLUE);
        }
    }

    public void keyReleased(KeyEvent e){
        Integer key = e.getKeyCode();
        if(dbg) System.out.println("DBG  key code releasedssdfdsd: " + key);
        if (key.equals(37)) {
            //left arrow?

            if(dbg) System.out.println("DBG  left released");
        }
        else if (key.equals(38)) {
            if(dbg) System.out.println("DBG  down released");
            //up arrow?
        }
        else if (key.equals(39)) {
            if(dbg) System.out.println("DBG  right released");
            //right arrow?
        }
        else if (key.equals(40)) {
            if(dbg) System.out.println("DBG up released");
            //down arrow?
        }
    }
}
