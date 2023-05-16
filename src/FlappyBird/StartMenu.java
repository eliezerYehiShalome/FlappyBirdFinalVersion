package FlappyBird;

import javax.swing.*;
import java.awt.*;

public class StartMenu extends JButton {

     public StartMenu(){



     }

     public void paintComponent(Graphics graphics){

          int heiget=200;
          int width=200;
          super.paintComponent(graphics );
          this.setBackground(Color.PINK);
          graphics.fillRect(getWidth()/2,getHeight()/2,width,heiget);





     }




}
