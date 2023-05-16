package FlappyBird;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.atomic.AtomicBoolean;

import static FlappyBird.Window.getWindowHeight;
import static FlappyBird.Window.getWindowWidth;

public class GameSans extends JPanel implements KeyListener {
    private ResetManager resetManager;
    private int skyHeight;
    private static int birdHeight = 30;
    private static int birdWidth = 40;
    private static int birdY;
    private static int birdJumpHigh=-9;
    private boolean pressed = false;



    private static int birdX = getWindowWidth() / 2 - birdWidth / 2;
    private int gravitySpeed = 40;
    private int groundHeight = 100;
    private Image image;
    private Image imageBackground;

    private int gravity;
    private int pipeDistance = 223;



    private int bestScore = 0;

    private int pipeSpeed;

    private int passCaunt=0;

    private Pipes pipes;
    private Pipes pipes1;

    public static int getBirdX() {
        return birdX;
    }

    public GameSans() {

        this.skyHeight = skyHeight;
        image = Toolkit.getDefaultToolkit().createImage(".idea\\image1\\MyGif.gif");
        imageBackground = Toolkit.getDefaultToolkit().getImage(".idea\\image1\\backgraund.jpg");

        birdY = getHeight() / 2;
        gravity = 0;

        resetManager = new ResetManager(this);
        pipeSpeed = 9;

        this.pipes = new Pipes(getWindowWidth(), 0, getWidth(), this.skyHeight, pipeSpeed);
        this.pipes1 = new Pipes(getWindowWidth()+pipeDistance, 0, getWidth(), this.skyHeight, pipeSpeed);
        setFocusable(true);
        addKeyListener(this);



        new Thread(() -> {




            while (true) {





                Rectangle lowerPipes = this.pipes.calculateLowerPipes();
                Rectangle upperPipes = this.pipes.calculateUpperPipes();
                Rectangle lowerPipes1 = this.pipes1.calculateLowerPipes();
                Rectangle upperPipes1 = this.pipes1.calculateUpperPipes();

                gravity++;
                birdY += gravity;

                if (birdY > getHeight() - groundHeight - birdHeight)
                    resetManager.resetGame();

                if (birdY < 0)
                    resetManager.resetGame();

                if (Utils.checkCollision(lowerPipes, upperPipes, calculateBird()) ||
                        Utils.checkCollision(lowerPipes1, upperPipes1, calculateBird())) {
                    resetManager.resetGame();
                }

                Utils.sleep(gravitySpeed);

                repaint();
            }
        }).start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imageBackground, 0, 0, getWidth(), getHeight() - groundHeight, this);

        pipes.paint(g);
        pipes1.paint(g);

        g.setColor(Color.orange);
        g.drawImage(image, birdX, birdY, birdWidth, birdHeight, this);

        g.fillRect(0, getHeight() - groundHeight, getWidth(), groundHeight);

        Font font = new Font("Arial", Font.BOLD, 25);
        g.setFont(font);
        g.setColor(Color.white);
        if (bestScore < Pipes.getPassageCounter()) {
            bestScore = Pipes.getPassageCounter();


        }
        System.out.println(Pipes.getPassageCounter());

        String bestScoreText = "Best Score: " + bestScore;
        String scoreText = "Score: " + Pipes.getPassageCounter();
        if (Pipes.getPassageCounter()>=15&&Pipes.getPassageCounter()<16) {
            String stage2 = "STAGE 2";
            g.drawString(stage2, getWindowWidth()/2-20, getWindowHeight()/3);

        }
        String myStage = "dif: " + passCaunt;

        g.drawString(scoreText, 10, 30);
        g.drawString(bestScoreText, 10, 50);


    }


    public static Rectangle calculateBird () {
        return new Rectangle(birdX, birdY, birdWidth, birdHeight-1);

    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {


        if (!pressed && (e.getKeyCode() == KeyEvent.VK_SPACE||e.getKeyCode() == KeyEvent.VK_UP||e.getKeyCode() == KeyEvent.VK_W)) {
            gravity = birdJumpHigh;
            pressed = true;
        }

        if (Pipes.getPassageCounter()>15 ){
            pipes.setPipeSpeed(9);
            pipes1.setPipeSpeed(9);

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE||e.getKeyCode() == KeyEvent.VK_UP||e.getKeyCode() == KeyEvent.VK_W) {
            pressed = false;
        }



    }

    void resetVariables() {
        birdY = getHeight() / 2;
        gravity = 0;
        Pipes.setPassageCounter(0);
        birdJumpHigh=-9;
        pipes.setPipeSpeed(12);
        pipes1.setPipeSpeed(12);

    }


    void resetPipes() {
         pipes.pipeX=getWindowWidth();
         pipes1.pipeX=getWindowWidth()+pipeDistance;

    }

    public int getPassCaunt() {
        return passCaunt;
    }
}
