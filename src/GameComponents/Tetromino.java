package GameComponents;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

public class Tetromino {

    private SquareColor color;
    private long fallingTimer;
    private boolean isFalling;
    private LinkedList<Square> squares;
    private int rotation = 0;
    public Tetromino(int x, int y, SquareColor color) {
        this.color = color;
        isFalling = true;
        squares = new LinkedList<Square>();
        fallingTimer = System.currentTimeMillis();
        build(color);
    }

    public boolean isFalling() {
        return isFalling;
    }

    public void update() {
        if(System.currentTimeMillis() - fallingTimer > World.getInstance().getFallTime()) {
            fallingTimer = System.currentTimeMillis();
            if(!moveDown()) {
                if(isFalling) World.getInstance().requestLockIn();
                isFalling = false;
            }

        }
    }

    boolean isSquareOccupiedByTetronimo(Square testSquare) {
        boolean isOccupied = false;
        for (Square square : squares){
            if(testSquare.getX() == square.getX() && testSquare.getY() == square.getY()) {
                isOccupied = true;
                break;
            }
        }
        return isOccupied;
    }


    boolean isSquareSpaceOccupiedByTetronimo(SquareSpace testSquare) {
        boolean isOccupied = false;
        for (Square square : squares){
            if(testSquare.getX() == square.getX() && testSquare.getY() == square.getY()) {
                isOccupied = true;
                break;
            }
        }
        return isOccupied;
    }

    public boolean getIsFalling() {return isFalling;}

    public boolean moveLeft() {
        /*boolean isMovePossible = true;
        int leftEdge = 9;
        for(Square square : squares) {
            if(square.getX() < leftEdge) {
                leftEdge = square.getX();
            }
        }
        if(leftEdge > 0) {
            for(Square square : squares) {
                if(World.getInstance().getWorldMap()[square.getX() - 1][square.getY()].getIsOccupied() && !isSquareOccupiedByTetronimo(square)) {
                    isMovePossible = false;
                }
            }

        } else {
            isMovePossible = false;
        }
        if(isMovePossible) {
            transformTetronimo(-1,0);
        }

         */
        return transformTetronimo(-1,0);
    }

    public boolean moveRight() {
        /*boolean isMovePossible = true;
        int rightEdge = 0;
        for(Square square : squares) {
            if(square.getX() > rightEdge) {
                rightEdge = square.getX();
            }
        }
        if(rightEdge < 9) {
            for(Square square : squares) {
                    if(World.getInstance().getWorldMap()[square.getX() + 1][square.getY()].getIsOccupied() && !isSquareOccupiedByTetronimo(square)) {
                    isMovePossible = false;
                }
            }

        } else {
            isMovePossible = false;
        }
        if(isMovePossible) {
            transformTetronimo(1,0);
        }
         */
        return transformTetronimo(1,0);
    }
    public boolean moveDown() {
        return transformTetronimo(0,1);
        /*boolean isMovePossible = true;
        int bottomEdge = 0;
        for(Square square : squares) {
            if(square.getY() > bottomEdge) {
                bottomEdge = square.getY();
            }
        }
        if(bottomEdge < 19) {
            for(Square square : squares) {
                SquareSpace testSquareSpace = World.getInstance().getWorldMap()[square.getX()][square.getY() + 1];
                if(testSquareSpace.getIsOccupied() && !isSquareSpaceOccupiedByTetronimo(testSquareSpace)) {
                    isMovePossible = false;
                }
            }

        } else {
            isMovePossible = false;
        }
        if(isMovePossible) {
            return transformTetronimo(0,1);
        }
        return isMovePossible;

         */
    }
    public void moveFloor() {

    }
    private boolean transformTetronimo(int xInc, int yInc) {
        boolean isMovePossible = true;
        System.out.println("transform tetronimo called");

        try {
            if(yInc > 0) {
                for(Square square : squares) {
                    SquareSpace testSquareSpace = World.getInstance().getWorldMap()[square.getX()][square.getY() + 1];
                    if(testSquareSpace.getIsOccupied() && !isSquareSpaceOccupiedByTetronimo(testSquareSpace)) {
                        isMovePossible = false;
                    }
                }
            }
            if(yInc < 0) {
                for(Square square : squares) {
                    SquareSpace testSquareSpace = World.getInstance().getWorldMap()[square.getX()][square.getY() - 1];
                    if(testSquareSpace.getIsOccupied() && !isSquareSpaceOccupiedByTetronimo(testSquareSpace)) {
                        isMovePossible = false;
                    }
                }
            }
            if(xInc > 0) {
                for(Square square : squares) {
                    SquareSpace testSquareSpace = World.getInstance().getWorldMap()[square.getX() + 1][square.getY()];
                    if(testSquareSpace.getIsOccupied() && !isSquareSpaceOccupiedByTetronimo(testSquareSpace)) {
                        isMovePossible = false;
                    }
                }
            }
            if(xInc < 0) {
                for(Square square : squares) {
                    SquareSpace testSquareSpace = World.getInstance().getWorldMap()[square.getX() - 1][square.getY() - 1];
                    if(testSquareSpace.getIsOccupied() && !isSquareSpaceOccupiedByTetronimo(testSquareSpace)) {
                        isMovePossible = false;
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException arrayException) {
            System.out.println("Attempted tetronimo translation out of bounds :/");
            isMovePossible = false;
        }

        if(isMovePossible) {
            System.out.println("SSS sucessful move");
            for(Square square : squares) {
                square.setX(square.getX() + xInc);
                square.setY(square.getY() + yInc);
            }
        }
        else {
            System.out.println("UUU unsucessful");
        }
        return isMovePossible;
    }



    public void removeSquare(Square square) {
        squares.remove(square);
    }

    //TODO finish Tetromino build()
    public void build(SquareColor color) {
        rotation = 0; // Do not remove, this resets the rotation
        switch (this.color) {
            case DEFAULT:
                break;
            case GREEN:
                break;
            case RED:
                break;
            case ORANGE:
                break;
            case PURPLE:
                squares.add(0, new Square(4, 1, color));
                squares.add(0, new Square(3, 2, color));
                squares.add(0, new Square(4, 2, color));
                squares.add(0, new Square(5, 2, color));
                break;
            case YELLOW:
                break;
            case DARK_BLUE:
                break;
            case LIGHT_BLUE:
                squares.add(0, new Square(3,1,color));
                squares.add(1, new Square(4,1,color));
                squares.add(2, new Square(5,1,color));
                squares.add(3, new Square(6,1,color));
                break;
        }
    }

    //TODO finish Tetromino rotate()
    public void rotate() {
        rotation++;
        rotation = rotation % 4; //normalizes values to 0-3

        boolean isRotationPossible = true;


        int prevX = squares.get(0).getX();


        int bottomEdge = 0;
        int rightEdge = 0;
        int leftEdge  = 9;
        int prevY = squares.get(0).getY();
        for(Square square : squares) {
            if(square.getX() > rightEdge) {
                rightEdge = square.getX();
            }
            if(square.getX() <  leftEdge) {
                leftEdge  = square.getX();
            }
            if(square.getY() > bottomEdge) {
                bottomEdge = square.getY();
            }
        }
        switch (color) {
            case DEFAULT:
                break;
            case GREEN:
                break;
            case RED:
                break;
            case ORANGE:
                break;
            case PURPLE:

                break;
            case YELLOW:
                break;
            case DARK_BLUE:
                break;
            case LIGHT_BLUE:
                if(rotation == 1 || rotation == 3) { //horizontal to vertical
                    squares.clear();
                    squares.add(0, new Square(rightEdge - 1,bottomEdge + 1, color));
                    squares.add(1, new Square(rightEdge - 1, bottomEdge, color));
                    squares.add(2, new Square(rightEdge - 1, bottomEdge - 1, color));
                    squares.add(3, new Square(rightEdge - 1,bottomEdge - 2, color));
                }

                if(rotation == 0 || rotation == 2) { //vertical to horizontal
                    if(leftEdge > 1 && rightEdge < 9) {
                        squares.clear();
                        squares.add(0, new Square(rightEdge + 1, bottomEdge - 1, color));
                        squares.add(1, new Square(rightEdge + 0, bottomEdge - 1, color));
                        squares.add(2, new Square(rightEdge - 1, bottomEdge - 1, color));
                        squares.add(3, new Square(rightEdge - 2, bottomEdge - 1, color));
                    } else {
                        isRotationPossible = false;
                    }
                }
                break;
        }
        if(!isRotationPossible) {
            rotation--; //this resets rotations counter
            System.out.println("Attempted tetronimo rotation out of bounds  :/");
        }
    }

    public LinkedList<Square> getSquareList() {
        return squares;
    }

    public SquareColor getColor() {
        return color;
    }

    public void setColor(SquareColor color) {
        this.color = color;
    }

    public void setSquares(LinkedList<Square> squares) {
        this.squares = squares;
    }

}
