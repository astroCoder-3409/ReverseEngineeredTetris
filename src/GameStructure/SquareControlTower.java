package GameStructure;

import GameComponents.Tetromino;

import java.util.LinkedList;

public class SquareControlTower {
    private static SquareControlTower mSquareControlTower = new SquareControlTower();
    LinkedList<Tetromino> tetrominoList;
    public SquareControlTower() {
        tetrominoList = new LinkedList<Tetromino>();
    }

    public void update() {
        for(Tetromino tetromino : tetrominoList) {

        }
    }

    public SquareControlTower getInstance() {
        return mSquareControlTower;
    }
}
