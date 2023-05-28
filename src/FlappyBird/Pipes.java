package FlappyBird;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static FlappyBird.Window.getWindowHeight;
import static FlappyBird.Window.getWindowWidth;

public class Pipes extends JPanel {
    private final Image UP_PIPE;
    private final Image DOWN_PIPE;
    private final Image UP_PIPE_DARK;
    private final Image DOWN_PIPE_DARK;
    private int pipeSpeed = 9;
    private static int passageCounter = 0;
    int tillStartToCount = 0;

    private final int PIPE_WIDTH = 70;
    public int pipeX;


    private final Random  random = new Random();

    private int skyHeight;
    private int floorHeight;
    private boolean passed;

    public static int getPassageCounter() {
        return passageCounter;
    }

    public static void setPassageCounter(int passageCounter) {
        Pipes.passageCounter = passageCounter;
    }

    public int setPipeSpeed(int pipeSpeed) {
        this.pipeSpeed = pipeSpeed-3;
        return pipeSpeed;
    }
    private final boolean HAS_PASSED_PIPE = false;



    public Pipes(int pipeX, int i, int WIDTH, int skyHeight, int pipeSpeed) {
        DOWN_PIPE = Toolkit.getDefaultToolkit().getImage(".idea\\image1\\up.png");
        UP_PIPE = Toolkit.getDefaultToolkit().getImage(".idea\\image1\\daune.png");
        DOWN_PIPE_DARK = Toolkit.getDefaultToolkit().getImage(".idea\\image1\\dardPipe1.png");
        UP_PIPE_DARK = Toolkit.getDefaultToolkit().getImage(".idea\\image1\\dardPipe.png");



        this.pipeX  += pipeX-PIPE_WIDTH;
        this.pipeSpeed = pipeSpeed;

        new Thread(() -> {
            while (true) {


                if (this.pipeX < -this.PIPE_WIDTH) {
                    this.pipeX = getWindowWidth();


                    this.skyHeight = random.nextInt(250) + 50;
                    this.floorHeight = 350 - this.skyHeight;
                }


                if (this.pipeX == getWindowWidth()/2) {
                    tillStartToCount++;
                    if (tillStartToCount >= 2) {
                        passageCounter++;
                    }

                }


                this.pipeX--;
                repaint();



                Utils.sleep(pipeSpeed);
                }


        }).start();


    }

    public void paint(Graphics g) {
        if (!StartMenu.getMood()) {
            g.drawImage(DOWN_PIPE, this.pipeX, getWindowHeight() - 137 - floorHeight, PIPE_WIDTH, floorHeight, this);
            g.drawImage(UP_PIPE, this.pipeX, 0, PIPE_WIDTH, skyHeight, this);
        }
        else {
            g.drawImage(DOWN_PIPE_DARK, this.pipeX, getWindowHeight() - 137 - floorHeight, PIPE_WIDTH, floorHeight, this);
            g.drawImage(UP_PIPE_DARK, this.pipeX, 0, PIPE_WIDTH, skyHeight, this);
        }
    }

    public Rectangle calculateLowerPipes() {
        return new Rectangle(this.pipeX, getWindowHeight() - 137 - floorHeight, PIPE_WIDTH, floorHeight);
    }

    public Rectangle calculateUpperPipes() {
        return new Rectangle(this.pipeX, 0, PIPE_WIDTH, skyHeight);
    }
}
