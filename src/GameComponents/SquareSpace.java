package GameComponents;

public class SquareSpace {
    private boolean isOccupied;
    private int x, y;
    private SquareColor currentColor;
    public boolean beenModified = false;
    public SquareSpace(int x, int y) {
        this.x = x;
        this.y = y;
        isOccupied = false;
        currentColor = SquareColor.DEFAULT;
    }
    public boolean getIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public SquareColor getColor() {
        return currentColor;
    }

    public void setColor(SquareColor color) {
        currentColor = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
