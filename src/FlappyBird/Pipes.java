package FlappyBird;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static FlappyBird.Window.getWindowHeight;
import static FlappyBird.Window.getWindowWidth;

public class Pipes extends JPanel {
    private Image upPipe;
    private Image downPipe;

    private int pipeSpeed = 9;
    private static int passageCounter = 0;

    private final int PIPE_WIDTH = 70;
    public int pipeX;


    private Random random = new Random();

    private int skyHeight;
    private int floorHeight;
    private boolean passed;

    public static int getPassageCounter() {
        return passageCounter;
    }

    public static void setPassageCounter(int passageCounter) {
        Pipes.passageCounter = passageCounter;
    }

    public int getPipeSpeed() {
        return pipeSpeed;
    }

    public int bugSolver=0;

    public void setPipeSpeed(int pipeSpeed) {
        this.pipeSpeed = pipeSpeed-3;
    }

    public int getPipeX() {
        return pipeX;
    }

    public Pipes(int pipeX, int i, int WIDTH, int skyHeight, int pipeSpeed) {
        downPipe = Toolkit.getDefaultToolkit().getImage(".idea\\image1\\UP.png");
        upPipe = Toolkit.getDefaultToolkit().getImage(".idea\\image1\\daune.png");

        this.pipeX  += pipeX-70;
        this.pipeSpeed = pipeSpeed;

        new Thread(() -> {
            while (true) {


                if (this.pipeX < -this.PIPE_WIDTH) {
                    this.pipeX = getWindowWidth();


                    this.skyHeight = random.nextInt(250) + 50;
                    this.floorHeight = 350 - this.skyHeight;
                }

                if (this.pipeX == GameSans.getBirdX()) {
                    passageCounter++;


                }


                this.pipeX--;
                System.err.println(this.pipeX);



                 Utils.sleep(this.pipeSpeed);
                }
        }).start();
    }

    public void paint(Graphics g) {

        g.drawImage(downPipe, this.pipeX, getWindowHeight() - 137 - floorHeight, PIPE_WIDTH, floorHeight, this);
        g.drawImage(upPipe, this.pipeX, 0, PIPE_WIDTH, skyHeight, this);
    }

    public void resetPipes() {
        this.skyHeight = random.nextInt(250) + 50;
        this.floorHeight = 350 - skyHeight;
    }

    public Rectangle calculateLowerPipes() {
        return new Rectangle(this.pipeX, getWindowHeight() - 137 - floorHeight, PIPE_WIDTH, floorHeight);
    }

    public Rectangle calculateUpperPipes() {
        return new Rectangle(this.pipeX, 0, PIPE_WIDTH, skyHeight);
    }
}
