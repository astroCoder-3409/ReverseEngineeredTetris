package GameComponents;

public class Square {
    private int x, y;
    private SquareColor squareColor;
    public Square(int x, int y, SquareColor color) {
        this.x = x;
        this.y = y;
        this.squareColor = color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
