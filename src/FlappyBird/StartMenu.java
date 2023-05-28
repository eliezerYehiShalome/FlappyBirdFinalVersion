package FlappyBird;

import javax.swing.*;
import java.awt.*;


public class StartMenu extends JFrame {


     private ImageIcon buttonIcon = new ImageIcon(".idea\\image1\\StartButon.png");
     private ImageIcon LOGO_ICON = new ImageIcon(".idea\\image1\\logo.jpeg");
     private ImageIcon logoAbout = new ImageIcon(".idea\\image1\\about.png");
     private ImageIcon Instructions = new ImageIcon(".idea\\image1\\Instructions.png");

     private ImageIcon set = new ImageIcon(".idea\\image1\\setings.png");


     private static String firstName;
     private static int selectedIndex;
     private static boolean mood;

     private String lastName;

     public static boolean getMood(){
          return mood;
     }


     private ImageIcon SignUpImg = new ImageIcon(".idea\\image1\\Sign up.png");

     public static String getFirstName() {
          return firstName;
     }
     public String getLastName() {
          return lastName;
     }

     private ResetManager resetManager;
     public StartMenu(){

          this.setSize(Window.getWindowWidth(),Window.WINDOW_HEIGHT);
          this.setDefaultCloseOperation(EXIT_ON_CLOSE);
          this.setResizable(false);
          this.setTitle("Flappy bird");
          this.setLocationRelativeTo(null);
          this.setLayout(null);
          this.setIconImage(LOGO_ICON.getImage());
          Background background = new Background(Window.getWindowWidth(),Window.WINDOW_HEIGHT);


          JButton start = new JButton(buttonIcon);
          start.setBounds(Window.getWindowWidth() / 2 -100,Window.WINDOW_HEIGHT /2 -150,200,100);
          start.setBorderPainted(true);

          JButton about = new JButton(logoAbout);
          about.setBounds(Window.getWindowWidth() / 2 -100,Window.WINDOW_HEIGHT / 2-33,40,40);
          about.setBorderPainted(true);

          JButton signUp =  new JButton( SignUpImg);
          signUp.setBounds(Window.getWindowWidth() / 2 -50,Window.WINDOW_HEIGHT / 2-33,150,40);
          signUp.setBorderPainted(true);


          JButton stings =  new JButton( set);
          stings.setBounds(Window.getWindowWidth() / 2 -100,Window.WINDOW_HEIGHT / 2+20,60,60);
          stings.setBorderPainted(true);

          JButton ins =  new JButton( Instructions);
          ins.setBounds(Window.getWindowWidth() / 2 -20 ,Window.WINDOW_HEIGHT / 2+20,120,60);
          ins.setBorderPainted(true);


          ins.addActionListener((event)->{
               JOptionPane.showMessageDialog(this,"על השחקן לנווט את הציפור, בין זוגות אנכיים של צינורות,\n כאשר בין זוג הצינורות רווח בגודל קבוע הממוקמם בגובה שונה\n בכל זוג צינורות. הציפור תמיד נעה מטה מלבד \nבעת לחיצה של השחקן על הכפתורים: רווח | חץ למעלה | W | \n שתגרום לציפור לבצע קפיצה מעלה לזמן קצר.\n כל מעבר מוצלח ברווח שבין זוג צינורות\n מזכה את השחקן בנקודה.");

          });





          start.addActionListener((event) -> {

               SwingUtilities.invokeLater(Window::new);

               this.setVisible(false);

          });


          stings.addActionListener((event) -> {
               String[] options = {"dark mode", "regular"};
               String op1="op1";
                this.selectedIndex = JOptionPane.showOptionDialog(this, "Choose an option:", "Stings",
                       JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

               if (this.selectedIndex != JOptionPane.CLOSED_OPTION) {
                    JOptionPane.showMessageDialog(this, "Selected option: " + options[selectedIndex]);
               }

               if (selectedIndex==1){
                    mood=false;
               }
               else
                    mood=true;


          });


          signUp.addActionListener((event) -> {

               this.firstName = JOptionPane.showInputDialog(this, "first name:","Sign up",1);
               this.lastName = JOptionPane.showInputDialog(this, "last name:","Sign up",1);


               if (firstName != null && !firstName.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "first name: " + firstName +"\nlast name:" +lastName);
                    signUp.setVisible(false);
               } else {
                    JOptionPane.showMessageDialog(this, "לא הוזנו נתונים");
               }

          });



          about.addActionListener((event) -> {

               JOptionPane.showMessageDialog(this, "Flappy bird by:\n  -Eliezer yhehi shalom\n  -Chaim ezra");
          });

          this.add(background);
          this.add(start);
          this.add(about);
          this.add(signUp);
          this.add(stings);
          this.add(ins);
          this.setVisible(true);
     }


}
