package GameStructure;

public class Main {

    Game gameInstance;

    public Main() {
        gameInstance = new Game();
        gameInstance.start();
    }

    public static void main(String Args[]) {
        Main main = new Main();
    }
}
