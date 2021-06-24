package GameComponents;

import java.util.LinkedList;

public class Tetromino {

    private SquareColor color;
    private long fallingTimer;
    private boolean isFalling;

    private boolean isTransLeftReq = false;
    private boolean isTransRightReq = false;
    private boolean isTransDownReq = false;
    private boolean isRotateReq = false;


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

            if(isFalling) {
                if(!moveDown()) {
                    World.getInstance().requestLockIn();
                    isFalling = false;
                }
            }
        }
        if(isFalling) {
            processMovements();
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

    public void requestRight() {
        isTransRightReq = true;
    }

    public void requestLeft() {
        isTransLeftReq = true;
    }

    public void requestDown() {
        isTransDownReq = true;
    }

    public void requestRotate() {
        isRotateReq = true;
    }

    private void processMovements() {
        if (isTransRightReq) {
            moveRight();
            isTransRightReq = false;
        }
        if (isTransLeftReq) {
            moveLeft();
            isTransLeftReq = false;
        }
        if (isTransDownReq) {
            moveDown();
            isTransDownReq = false;
        }
        if(isRotateReq) {
            rotate();
            isRotateReq = false;
        }
    }

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
    public void forceDown() {
        for(Square square : squares) {
            square.setY(square.getY() + 1);
        }
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
                    SquareSpace testSquareSpace = World.getInstance().getWorldMap()[square.getX() - 1][square.getY()];
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

    public void build(SquareColor color) {
        rotation = 0; // Do not remove, this resets the rotation
        switch (this.color) {
            case DEFAULT:
                break;
            case GREEN:
                squares.add(new Square(4, 1, color));
                squares.add(new Square(5, 0, color));
                squares.add(new Square(4, 0, color));
                squares.add(new Square(3, 1, color));
                break;
            case RED:
                squares.add(new Square(4, 1, color));
                squares.add(new Square(5, 1, color));
                squares.add(new Square(4, 0, color));
                squares.add(new Square(3, 0, color));
                break;
            case ORANGE:
                squares.add(new Square(5, 0, color));
                squares.add(new Square(3, 1, color));
                squares.add(new Square(4, 1, color));
                squares.add(new Square(5, 1, color));
                break;
            case DARK_BLUE:
                squares.add(new Square(3, 0, color));
                squares.add(new Square(3, 1, color));
                squares.add(new Square(4, 1, color));
                squares.add(new Square(5, 1, color));
                break;
            case PURPLE:
                squares.add(new Square(4, 1, color));
                squares.add(new Square(3, 2, color));
                squares.add(new Square(4, 2, color));
                squares.add(new Square(5, 2, color));
                break;
            case YELLOW:
                squares.add(new Square(4, 1, color));
                squares.add(new Square(5, 1, color));
                squares.add(new Square(4, 0, color));
                squares.add(new Square(5, 0, color));
                break;
            case LIGHT_BLUE:
                squares.add(new Square(3,1, color));
                squares.add(new Square(4,1, color));
                squares.add(new Square(5,1, color));
                squares.add(new Square(6,1, color));
                break;
        }
    }

    public void dropSquaresAboveLevel(int level) {
        if (!isFalling) {
            for (Square square: squares) {
                if (square.getY() < level) square.setY(square.getY() + 1);
            }
        }
    }

    public void rotate() {
        rotation++;
        rotation = rotation % 4; //normalizes values to 0-3

        boolean isRotationInBounds = true;

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
                if (rotation == 1 || rotation == 3) {
                    squares.clear();
                    squares.add(new Square(rightEdge - 1, bottomEdge - 0, color));
                    squares.add(new Square(rightEdge - 1, bottomEdge - 1, color));
                    squares.add(new Square(rightEdge - 0, bottomEdge - 0, color));
                    squares.add(new Square(rightEdge - 0, bottomEdge + 1, color));
                } else {
                    if (leftEdge > 0) {
                        squares.clear();
                        squares.add(new Square(rightEdge - 1, bottomEdge - 1, color));
                        squares.add(new Square(rightEdge - 2, bottomEdge - 1, color));
                        squares.add(new Square(rightEdge - 1, bottomEdge - 2, color));
                        squares.add(new Square(rightEdge - 0, bottomEdge - 2, color));
                    } else {
                        isRotationInBounds = false;
                    }
                }
                break;
            case RED:
                if(rotation == 1 || rotation == 3) {
                    squares.clear();
                    squares.add(new Square(rightEdge - 1, bottomEdge - 0, color));
                    squares.add(new Square(rightEdge - 1, bottomEdge - 1, color));
                    squares.add(new Square(rightEdge - 2, bottomEdge - 0, color));
                    squares.add(new Square(rightEdge - 2, bottomEdge + 1, color));

                } else {
                    if(rightEdge < 9) {
                        squares.clear();
                        squares.add(new Square(rightEdge + 0, bottomEdge - 1, color));
                        squares.add(new Square(rightEdge + 1, bottomEdge - 1, color));
                        squares.add(new Square(rightEdge + 0, bottomEdge - 2, color));
                        squares.add(new Square(rightEdge - 1, bottomEdge - 2, color));
                    } else {
                        isRotationInBounds = false;
                    }
                }
                break;
            case ORANGE:
                if (rotation == 1) {
                    squares.clear();
                    squares.add(new Square(rightEdge - 1, bottomEdge - 0, color));
                    squares.add(new Square(rightEdge - 1, bottomEdge + 1, color));
                    squares.add(new Square(rightEdge - 1, bottomEdge - 1, color));
                    squares.add(new Square(rightEdge - 2, bottomEdge - 1, color));
                } else if (rotation == 2) {
                    if(rightEdge < 9) {
                        squares.clear();
                        squares.add(new Square(rightEdge - 0, bottomEdge - 1, color));
                        squares.add(new Square(rightEdge - 1, bottomEdge - 1, color));
                        squares.add(new Square(rightEdge + 1, bottomEdge - 1, color));
                        squares.add(new Square(rightEdge - 1, bottomEdge - 0, color));
                    } else {
                        isRotationInBounds = false;
                    }

                } else if (rotation == 3) {
                    squares.clear();
                    squares.add(new Square(rightEdge - 1, bottomEdge - 1, color));
                    squares.add(new Square(rightEdge - 1, bottomEdge - 2, color));
                    squares.add(new Square(rightEdge - 1, bottomEdge - 0, color));
                    squares.add(new Square(rightEdge - 0, bottomEdge - 0, color));
                } else {
                    if (leftEdge > 0) {
                        squares.clear();
                        squares.add(new Square(rightEdge - 1, bottomEdge - 1, color));
                        squares.add(new Square(rightEdge + 0, bottomEdge - 1, color));
                        squares.add(new Square(rightEdge - 2, bottomEdge - 1, color));
                        squares.add(new Square(rightEdge - 0, bottomEdge - 2, color));
                    } else {
                        isRotationInBounds = false;
                    }
                }
                break;
            case PURPLE:
                if(rotation == 1) {
                    squares.clear();
                    squares.add(new Square(rightEdge - 1, bottomEdge - 1, SquareColor.PURPLE));
                    squares.add(new Square(rightEdge - 1, bottomEdge - 0, SquareColor.PURPLE));
                    squares.add(new Square(rightEdge - 1, bottomEdge + 1, SquareColor.PURPLE));
                    squares.add(new Square(rightEdge - 0, bottomEdge - 0, SquareColor.PURPLE));
                }
                else if(rotation == 2) {
                    if(leftEdge > 0) {
                        squares.clear();
                        squares.add(new Square(rightEdge - 1, bottomEdge - 1, SquareColor.PURPLE));
                        squares.add(new Square(rightEdge - 0, bottomEdge - 1, SquareColor.PURPLE));
                        squares.add(new Square(rightEdge - 2, bottomEdge - 1, SquareColor.PURPLE));
                        squares.add(new Square(rightEdge - 1, bottomEdge - 0, SquareColor.PURPLE));
                    }
                    else {
                        isRotationInBounds = false;
                    }
                }
                else if(rotation == 3) {
                    squares.clear();
                    squares.add(new Square(rightEdge - 1, bottomEdge - 1, SquareColor.PURPLE));
                    squares.add(new Square(rightEdge - 1, bottomEdge - 2, SquareColor.PURPLE));
                    squares.add(new Square(rightEdge - 1, bottomEdge - 0, SquareColor.PURPLE));
                    squares.add(new Square(rightEdge - 2, bottomEdge - 1, SquareColor.PURPLE));
                }
                else {
                    if(rightEdge < 9) {
                        squares.clear();
                        squares.add(new Square(rightEdge - 0, bottomEdge - 1, SquareColor.PURPLE));
                        squares.add(new Square(rightEdge + 1, bottomEdge - 1, SquareColor.PURPLE));
                        squares.add(new Square(rightEdge - 1, bottomEdge - 1, SquareColor.PURPLE));
                        squares.add(new Square(rightEdge - 0, bottomEdge - 2, SquareColor.PURPLE));
                    } else {
                        isRotationInBounds = false;
                    }
                }
                break;
            case YELLOW:
                break;
            case DARK_BLUE:
                if (rotation == 1) {
                    squares.clear();
                    squares.add(new Square(rightEdge - 1, bottomEdge - 0, color));
                    squares.add(new Square(rightEdge - 1, bottomEdge + 1, color));
                    squares.add(new Square(rightEdge - 1, bottomEdge - 1, color));
                    squares.add(new Square(rightEdge - 0, bottomEdge - 1, color));
                } else if (rotation == 2) {
                   if (leftEdge > 0) {
                       squares.clear();
                       squares.add(new Square(rightEdge - 1, bottomEdge - 1, color));
                       squares.add(new Square(rightEdge - 2, bottomEdge - 1, color));
                       squares.add(new Square(rightEdge - 0, bottomEdge - 1, color));
                       squares.add(new Square(rightEdge - 0, bottomEdge - 0, color));
                   } else {
                       isRotationInBounds = false;
                   }
                } else if (rotation == 3) {
                    squares.clear();
                    squares.add(new Square(rightEdge - 1, bottomEdge - 1, color));
                    squares.add(new Square(rightEdge - 1, bottomEdge - 2, color));
                    squares.add(new Square(rightEdge - 1, bottomEdge - 0, color));
                    squares.add(new Square(rightEdge - 2, bottomEdge - 0, color));
                } else {
                    if (rightEdge < 9) {
                        squares.clear();
                        squares.add(new Square(rightEdge - 0, bottomEdge - 1, color));
                        squares.add(new Square(rightEdge + 1, bottomEdge - 1, color));
                        squares.add(new Square(rightEdge - 1, bottomEdge - 1, color));
                        squares.add(new Square(rightEdge - 1, bottomEdge - 2, color));
                    } else {
                        isRotationInBounds = false;
                    }

                }
                break;
            case LIGHT_BLUE:
                if(rotation == 1 || rotation == 3) { //horizontal to vertical
                    squares.clear();
                    squares.add(new Square(rightEdge - 1,bottomEdge + 1, color));
                    squares.add(new Square(rightEdge - 1, bottomEdge, color));
                    squares.add(new Square(rightEdge - 1, bottomEdge - 1, color));
                    squares.add(new Square(rightEdge - 1,bottomEdge - 2, color));
                }

                else if(rotation == 0 || rotation == 2) { //vertical to horizontal
                    if(leftEdge > 1 && rightEdge < 9) {
                        squares.clear();
                        squares.add(new Square(rightEdge + 1, bottomEdge - 1, color));
                        squares.add(new Square(rightEdge + 0, bottomEdge - 1, color));
                        squares.add(new Square(rightEdge - 1, bottomEdge - 1, color));
                        squares.add(new Square(rightEdge - 2, bottomEdge - 1, color));
                    } else {
                        isRotationInBounds = false;
                    }
                }
                break;
        }
        /*

        SquareSpace[] testSquareSpaces = new SquareSpace[4];
        Iterator<Square> iter = squares.iterator();
        int cnt = 0;

        while(iter.hasNext()) {
            Square sq = iter.next();
            testSquareSpaces[cnt] = World.getInstance().getSquareSpace(sq.getX(), sq.getX());
            if(testSquareSpaces[cnt].getIsOccupied() && !isSquareSpaceOccupiedByTetronimo(testSquareSpaces[cnt])) {
                isRotationConflicted = false;
            }

            cnt++;
        }

         */


        if(!isRotationInBounds) {
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
