package GameStructure;

import GameComponents.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;
import javax.swing.JFrame;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 120;
    public static final int HEIGHT = WIDTH * 2;
    public static final float SCALE = 3.0f;
    public static final int SQUARE_WIDTH = (int) (12 * SCALE);
    public static final String NAME = "Tetris shit";
    private static final long serialVersionUID = 1L;
    private static final boolean gameDBG = false;

    private JFrame frame;
    public boolean running = false;
    public int tickCount = 0;

    Graphics g;
    BufferStrategy bs;

    Scanner keyboard;
    public Game() {
        World.getInstance().initializeWorldMap();
        setBackground(Color.BLACK);

        this.addKeyListener(new KeyHandler());

        setMinimumSize(new Dimension((int) (WIDTH*SCALE + 1), (int) (HEIGHT*SCALE + 1)));
        setMaximumSize(new Dimension((int) (WIDTH*SCALE + 1), (int) (HEIGHT*SCALE + 1)));
        setPreferredSize(new Dimension((int) (WIDTH*SCALE + 1), (int) (HEIGHT*SCALE + 1)));

        frame = new JFrame(NAME);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(this, BorderLayout.CENTER);
        frame.pack();

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public synchronized void start() {
        new Thread(this).start();
        running = true;
    }

    public synchronized void stop() {
        running = false;
    }


    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D / 64;

        int ticks = 0;
        int frames = 0;

        long lastTimer = System.currentTimeMillis();
        double delta = 0;

        boolean shouldRender;
        long now;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            shouldRender = true;

            while (delta >= 1) {
                ticks++;
                tick();
                delta -= 1;
                shouldRender = true;
            }

            try {
                Thread.sleep(2);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            if (shouldRender) {
                frames++;
                render();
            }

            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                if (gameDBG) System.out.println(ticks + " ticks, " + frames + " frames");
                frames = 0;
                ticks = 0;
            }
        }
    }

    public void tick() {
        tickCount++;
        World.getInstance().update();


        /*
        if(tickCount % 64 == 0) {
            World.getInstance().getWorldMap()[0][1].setColor(SquareColor.LIGHT_BLUE);
            World.getInstance().getWorldMap()[1][1].setColor(SquareColor.LIGHT_BLUE);
            World.getInstance().getWorldMap()[2][1].setColor(SquareColor.LIGHT_BLUE);
            World.getInstance().getWorldMap()[3][1].setColor(SquareColor.LIGHT_BLUE);
        }
        if(tickCount % (64 * 2) == 0) {
            World.getInstance().getWorldMap()[5][5].setColor(SquareColor.DARK_BLUE);
        }
         */
    }

    SquareSpace[][] squareSpaces;

    public void render() {

        g = getGraphics();
        squareSpaces = World.getInstance().getWorldMap();
        g.setColor(Color.darkGray);
        for(int x = 0; x < 10; x++) {
            for(int y = 0; y < 20; y++) {
                g.drawRect(x * SQUARE_WIDTH, y * SQUARE_WIDTH, SQUARE_WIDTH, SQUARE_WIDTH);
            }
        }
        for(int x = 0; x < 10; x++) {
            for(int y = 0; y < 20; y++) {
                setGraphicsColor(World.getInstance().getWorldMap()[x][y].getColor());
                g.fillRect(x * SQUARE_WIDTH+2, y * SQUARE_WIDTH+2, SQUARE_WIDTH-3 , SQUARE_WIDTH - 3);
            }
        }
        g.dispose();
    }
    private void setGraphicsColor(SquareColor color) {
        switch (color) {
            case DEFAULT:
                g.setColor(Color.BLACK);
                break;
            case GREEN:
                g.setColor(Color.GREEN);
                break;
            case RED:
                g.setColor(Color.RED);
                break;
            case ORANGE:
                g.setColor(Color.ORANGE);
                break;
            case PURPLE:
                g.setColor(Color.MAGENTA);
                break;
            case YELLOW:
                g.setColor(Color.YELLOW);
                break;
            case DARK_BLUE:
                g.setColor(Color.BLUE);
                break;
            case LIGHT_BLUE:
                float[] hsvVals = new float[3];
                Color.RGBtoHSB(51,239,255, hsvVals);
                g.setColor(Color.getHSBColor(hsvVals[0], hsvVals[1], hsvVals[2]));
                break;
        }
    }
}