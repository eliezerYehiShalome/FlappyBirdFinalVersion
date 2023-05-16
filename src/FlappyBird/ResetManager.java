package FlappyBird;

public class ResetManager {

    private GameSans game;

    public ResetManager(GameSans game) {
        this.game = game;
    }


    public void resetGame() {
        game.resetVariables();

        game.resetPipes();

        game.repaint();

        game.resetPipes();


    }

}
