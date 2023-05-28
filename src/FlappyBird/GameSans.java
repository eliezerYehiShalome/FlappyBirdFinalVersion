package FlappyBird;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static FlappyBird.Window.getWindowHeight;
import static FlappyBird.Window.getWindowWidth;

public class GameSans extends JPanel implements KeyListener {
    private final ResetManager RESET_MANAGER;
    private int skyHeight;

    //BIRD=================================================
    private static final int BIRD_HEIGHT = 30;
    private static final int BIRD_WIDTH = 40;
    private static int birdY=300;
    private static int birdJumpHigh = -9;
    private int gravity;
    private static final int BIRD_X = getWindowWidth() / 2 - BIRD_WIDTH / 2;
    private int GRAVITY_SPEED = 40;
    private boolean pressed = false;
    private static final int LEVEL_2_DEMAND =10;

    //IMAGE=================================================
    private final Image IMAGE = Toolkit.getDefaultToolkit().createImage(".idea\\image1\\MyGif.gif");
    private final Image IMAGE_BACKGROUNDER_DAY_ = Toolkit.getDefaultToolkit().getImage(".idea\\image1\\back1.jpg");
    private final Image IMAGE_BACKGROUNDER_NIGHT_ = Toolkit.getDefaultToolkit().getImage(".idea/image1/dark.png");

    //PIPE==================================================
    private final int PIPE_DISTANSE = 223;
    private boolean cha;
    private final int GRAUNE_HIGET = 100;
    private static final int PIPE_SPEED=9;

    private final Pipes PIPES ;
    private final Pipes PIPES_1;

    //TEXT=============================================
    private static String bestScoreText;

    private int bestScore = 0;


    public GameSans() {


        this.birdY = getHeight() / 2;
        gravity = 0;

        RESET_MANAGER = new ResetManager(this);

        JButton restButton =  new JButton("RESTART");
        restButton.setBounds(Window.getWindowWidth() / 2 -50,Window.WINDOW_HEIGHT / 2-33,150,40);
        restButton.setBorderPainted(true);



        this.PIPES  = new Pipes(getWindowWidth(), 0, getWidth(), this.skyHeight, PIPE_SPEED);
        this.PIPES_1 = new Pipes(getWindowWidth() + PIPE_DISTANSE, 0, getWidth(), this.skyHeight, PIPE_SPEED);
        setFocusable(true);
        addKeyListener(this);



        new Thread(() -> {
            while (true) {


                Rectangle lowerPipes = this.PIPES .calculateLowerPipes();
                Rectangle lowerPipes1 = this.PIPES_1.calculateLowerPipes();

                Rectangle upperPipes = this.PIPES .calculateUpperPipes();
                Rectangle upperPipes1 = this.PIPES_1.calculateUpperPipes();

                 cha=(birdY > getHeight() - GRAUNE_HIGET - BIRD_HEIGHT
                        ||birdY < 0||(Utils.checkCollision(lowerPipes, upperPipes, calculateBird())
                        || Utils.checkCollision(lowerPipes1, upperPipes1, calculateBird())));
                gravity++;
                birdY += gravity;
                repaint();

                if(cha) {


                    if (Pipes.getPassageCounter() > LEVEL_2_DEMAND) {
                        PIPES.setPipeSpeed(PIPE_SPEED);
                        PIPES_1.setPipeSpeed(PIPE_SPEED);

                    }
                        //restButton.addActionListener((event) -> {
                        ResetManager.resetGame();
                 //   });

                   // this.add(restButton);

                }
                Utils.sleep(GRAVITY_SPEED);
            }

        }).start();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color magenta;





        if (!StartMenu.getMood()) {
            g.drawImage(IMAGE_BACKGROUNDER_DAY_, 0, 0, getWidth(), getHeight() - GRAUNE_HIGET+50, this);
            magenta = new Color(0xCFC698);
            System.out.println(getHeight() - GRAUNE_HIGET);

        }
        else {
            g.drawImage(IMAGE_BACKGROUNDER_NIGHT_, 0, 0, getWidth(), getHeight() - GRAUNE_HIGET, this);
            magenta = new Color(0x757056);
            System.out.println(getHeight() - GRAUNE_HIGET);
        }
        g.setColor(magenta);

        g.fillRect(0, getHeight() - GRAUNE_HIGET, getWidth(), GRAUNE_HIGET);

        this.PIPES.paint(g);
        this.PIPES_1.paint(g);




        g.drawImage(IMAGE, BIRD_X, birdY, BIRD_WIDTH, BIRD_HEIGHT, this);

        Font font = new Font("Arial", Font.BOLD, 25);
        g.setFont(font);
        g.setColor(Color.white);


        if (bestScore < Pipes.getPassageCounter()) {
            bestScore = Pipes.getPassageCounter();
        }

        if (Pipes.getPassageCounter() == LEVEL_2_DEMAND) {
            String stage2 = "STAGE 2";
            g.drawString(stage2, getWindowWidth() / 3, getWindowHeight() / 3);
        }
        if (StartMenu.getFirstName()!=null)
           this.bestScoreText =StartMenu.getFirstName()+ "'s Best Score: " + bestScore;
        else
            this.bestScoreText =" Best Score: " + bestScore;


        g.drawString(this.bestScoreText, 10, 30);

        Font fontScore = new Font("ben", Font.BOLD, 70);
        g.setFont(fontScore);
        String scoreText = ""+ Pipes.getPassageCounter();
        g.drawString(scoreText, this.BIRD_X, 100);

    }


    public static Rectangle calculateBird() {
        return new Rectangle(BIRD_X, birdY, BIRD_WIDTH, BIRD_HEIGHT - 1);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!pressed && (e.getKeyCode() == KeyEvent.VK_SPACE
                || e.getKeyCode() == KeyEvent.VK_UP
                || e.getKeyCode() == KeyEvent.VK_W)) {
            gravity = birdJumpHigh;
            pressed = true;
        }


    }






    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE
                || e.getKeyCode() == KeyEvent.VK_UP
                || e.getKeyCode() == KeyEvent.VK_W) {
            pressed = false;
        }
    }

    void resetVariables() {
        birdY = getHeight() / 2;
        gravity = 0;
        Pipes.setPassageCounter(0);
        birdJumpHigh = -9;
        PIPES .setPipeSpeed(12);
        PIPES_1.setPipeSpeed(12);
    }

    void resetPipes() {
        PIPES .pipeX = getWindowWidth();
        PIPES_1.pipeX = getWindowWidth() + PIPE_DISTANSE;
    }

}
