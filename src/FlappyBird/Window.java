package FlappyBird;

import javax.swing.*;

public class Window extends JFrame {

        private static final int WINDOW_WIDTH =375;
    public static final int WINDOW_HEIGHT = 600;

    public static int getWindowHeight() {
        return WINDOW_HEIGHT;
    }
    public static int getWindowWidth() {
        return WINDOW_WIDTH;
    }

    public Window(){

        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        this.setTitle("Flappy bird");
        this.setLocationRelativeTo(null);
//        StartMenu startMenu=new StartMenu();
//        add(startMenu);

          GameSans beckgraund=new GameSans();
          add(beckgraund);



    }



}
