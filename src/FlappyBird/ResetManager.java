package FlappyBird;

public class ResetManager {

    private static GameSans game;








    public ResetManager(GameSans game) {


        this.game = game;

    }


    public static void resetGame() {

//        StartMenu startMenu = new StartMenu();

        game.resetVariables();

        game.resetPipes();

        game.repaint();

        game.resetPipes();




    }

}
