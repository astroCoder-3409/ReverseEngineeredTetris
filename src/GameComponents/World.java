package GameComponents;

import java.util.Iterator;
import java.util.LinkedList;

public class World {
    private SquareSpace[][] worldMap;

    private LinkedList<Tetromino> tetrominoList;
    private LinkedList<Square> squareList;
    private static World mWorld = new World();
    public World() {
        worldMap = new SquareSpace[10][20];
        tetrominoList = new LinkedList<Tetromino>();
        squareList = new LinkedList<Square>();

    }

    public void initializeWorldMap() {
        for(int x = 0; x < 10; x++) {
            for(int y = 0; y < 20; y++) {
                worldMap[x][y] = new SquareSpace(x, y);
            }
        }
        //tetrominoList.add(new Tetromino(4,4,SquareColor.PURPLE));
        generateTetronimo(SquareColor.PURPLE);
    }

    public void moveTetronimoLeft() {
        LinkedList<Tetromino> tempTetronimoList = (LinkedList<Tetromino>) tetrominoList.clone();

        Iterator<Tetromino> iter = tempTetronimoList.iterator();
        while (iter.hasNext()) {
            Tetromino tetromino = iter.next();
            if(tetromino.isFalling()) {
                tetromino.moveLeft();
            }
        }

        tetrominoList = (LinkedList<Tetromino>) tetrominoList.clone();
        tempTetronimoList.clear();


        /*
        for(Tetromino tetromino : tetrominoList) {
            if(tetromino.isFalling()) {
                tetromino.moveLeft();
            }
        }
         */
    }

    public void moveTetronimoRight() {

        LinkedList<Tetromino> tempTetronimoList = (LinkedList<Tetromino>) tetrominoList.clone();

        Iterator<Tetromino> iter = tempTetronimoList.iterator();
        while (iter.hasNext()) {
            Tetromino tetromino = iter.next();
            if(tetromino.isFalling()) {
                tetromino.moveRight();
            }
        }

/*
        for(Tetromino tetromino : tetrominoList) {
            if(tetromino.isFalling()) {
                tetromino.moveRight();
            }
        }

 */

    }
    public void moveTetronimoDown() {

        LinkedList<Tetromino> tempTetronimoList = (LinkedList<Tetromino>) tetrominoList.clone();

        Iterator<Tetromino> iter = tempTetronimoList.iterator();
        while (iter.hasNext()) {
            Tetromino tetromino = iter.next();
            if(tetromino.isFalling()) {
                tetromino.moveDown();
            }
        }

/*
        for(Tetromino tetromino : tetrominoList) {
            if(tetromino.isFalling()) {
                tetromino.moveDown();
            }
        }

 */
    }

    public void moveAllTetronimosDown() {

        LinkedList<Tetromino> tempTetronimoList = (LinkedList<Tetromino>) tetrominoList.clone();

        Iterator<Tetromino> iter = tempTetronimoList.iterator();
        while (iter.hasNext()) {
            Tetromino tetromino = iter.next();
            tetromino.moveDown();
        }

/*
        for(Tetromino tetromino : tetrominoList) {
            if(tetromino.isFalling()) {
                tetromino.moveDown();
            }
        }

 */
    }
    public void moveTetronimoFloor() {

    }
    public void rotateTetronimo() {

        LinkedList<Tetromino> tempTetronimoList = (LinkedList<Tetromino>) tetrominoList.clone();

        Iterator<Tetromino> iter = tempTetronimoList.iterator();
        while (iter.hasNext()) {
            Tetromino tetromino = iter.next();
            if(tetromino.isFalling()) {
                tetromino.rotate();
            }
        }

        /*
        for(Tetromino tetromino : tetrominoList) {
            if(tetromino.isFalling()) {
                tetromino.rotate();
            }
        }

         */
    }

    public static World getInstance() {
        return mWorld;
    }

    public SquareSpace[][] getWorldMap() {
        return worldMap;
    }


    public LinkedList<Tetromino> getTetrominoList() {
        return tetrominoList;
    }

    public LinkedList<Square> getSquareList() {
        return squareList;
    }

    public SquareSpace getSquareSpace(int x, int y) {
        return worldMap[x][y];
    }

    public long getFallTime() {
        return 900;
    }

    public void update() {
        LinkedList<Tetromino> tempTetronimoList = (LinkedList<Tetromino>) tetrominoList.clone();
        //iterates through each column on x axis
        for(int x = 0; x < 10; x++) {
            //iterates through each square space
            for(int y = 0; y < 20; y++) {
                //for(Tetromino tetromino : tetrominoList) {

                Iterator<Tetromino> iter = tempTetronimoList.iterator();
                while (iter.hasNext()) {
                    Tetromino tetromino = iter.next();
                    tetromino.update();
                    squareList = tetromino.getSquareList();
                    for(Square square : squareList) {
                        if((square.getX() == x && square.getY() == y)) {
                            worldMap[x][y].setIsOccupied(true);
                            worldMap[x][y].setColor(tetromino.getColor());
                            worldMap[x][y].beenModified = true;
                        }
                    }
                }
                //}
                if(!worldMap[x][y].beenModified && worldMap[x][y].getIsOccupied()) {
                    worldMap[x][y].setIsOccupied(false);
                    worldMap[x][y].setColor(SquareColor.DEFAULT);
                }
                worldMap[x][y].beenModified = false;
            }
        }
    }
    public void generateTetronimo(SquareColor color) {
        tetrominoList.add(new Tetromino(0, 0, color));
    }

    public void lockIn() {
        generateTetronimo(SquareColor.LIGHT_BLUE);
        for (int y = 0; y < 20; y++) {
            boolean isLineClearable = true;
            for(int x = 0; x < 10; x++) {
                if(!worldMap[x][y].getIsOccupied()) isLineClearable = false;
            }
            if(isLineClearable) {
                for(int x = 0; x < 10; x++) {
                    for(Tetromino tetromino : tetrominoList) {
                        squareList = tetromino.getSquareList();

                        Iterator<Square> iter = squareList.iterator();

                        while (iter.hasNext()) {
                            Square square = iter.next();

                            if (square.getY() == y){
                                iter.remove();
                            }
                        }
                    }
                }
                moveAllTetronimosDown();
            }
        }
    }
}
