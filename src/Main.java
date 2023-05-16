//import javax.swing.*;
//import java.awt.*;
//
//public class Main {
//    public static void main(String[] args) {
//        System.out.println("Hello world!");
//    }
//            package Class2;
//
//import javax.swing.*;
//import java.awt.*;
//
//    public class sqere extends JButton {
//
//        private Color color;
//        private int tool;
//        private int previousTool;
//
//
//
//
//
//        public sqere() {
//
//            this.color = color;
//            this.tool = tool;
//
//
//        }
//
//        public sqere(Color color, int tool) {
//            this.color = color;
//            this.tool = tool;
//            this.previousTool = tool;
//
//
//        }
//
//        public void paintComponent(Graphics graphics) {
//            super.paintComponent(graphics);
//            this.setBackground(this.color);
//            if (this.tool != 0) {
//                if (this.tool == 1)
//                    graphics.setColor(Color.BLUE);
//                if (this.tool == 2)
//                    graphics.setColor(Color.red);
//
//
//                graphics.fillOval(8, 8, this.getHeight() - 10, this.getWidth() - 10);
//            }
//        }
//
//        public void dissapier() {
//
//            this.previousTool=this.tool;
//            this.tool = 0;
//            repaint();
//        }
//
//        public void showw(){
//            if (this.previousTool==1) {
//                this.tool = 1;
//                repaint();
//            }
//            if (this.previousTool==2) {
//                this.tool = 2 ;
//                repaint();
//            }
//
//
//        }
//
//    }
//}