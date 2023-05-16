package FlappyBird;

import java.awt.*;

public class Utils {


    public static void sleep (int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
    public static boolean checkCollision (Rectangle rect1, Rectangle rect2,Rectangle bird) {
        boolean collision = false;
        if (bird.intersects(rect2)||bird.intersects(rect1)) {
            collision = true;
        }
        return collision;
    }

}
