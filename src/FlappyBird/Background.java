package FlappyBird;

import javax.swing.*;
import java.awt.*;

public class Background extends JPanel {

    private final ImageIcon IMAGE_ICON = new ImageIcon(".idea\\image1\\back1_img.png");


    private final int WIDTH;
    private final int HEIGHT;

    public Background(int width,int height){
        setBounds(0,0,width,height);
        this.WIDTH = width;
        this.HEIGHT = height;
    }
    public void paintComponent(Graphics g){
        new ImageIcon(IMAGE_ICON.getImage().getScaledInstance(WIDTH,HEIGHT,Image.SCALE_SMOOTH)).paintIcon(null,g,0,0);

    }
}