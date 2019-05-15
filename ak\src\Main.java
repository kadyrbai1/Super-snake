import processing.core.PApplet;

public class Main extends PApplet {
    private final static int TITLE_STATE = 0;
    private final static int SPEED_SEL_STATE = 1;
    private final static int GAME_STATE = 2;
    private final static int RESULTS_STATE = 3;
    private final static int PAUSE_STATE = 4;
    private static final int COLOR_IN_MENU = 0xffd564cf;

    private final int FONT_SIZE = 50;

    private int speed = 1;
    private int score = 0;
    private int maxResult;
    private int currentResult;

    private int currentState = TITLE_STATE;

    private Field field;
    private Apple apple;
    private Snake snake;

    public void settings() {
        fullScreen();
    }

    public void setup() {
        field = new Field(40, 20);
        snake = new Snake(field, 0, 0, 1, 0);
        apple = new Apple(field, snake);
    }

    public void draw() {
        switch (currentState) {
            case TITLE_STATE:
                drawTheTitle();
                break;
            case SPEED_SEL_STATE:
                drawTheSpeedSelection();
                break;
            case GAME_STATE:
                drawTheGame();
                break;
            case RESULTS_STATE:
                drawTheResult();
            case PAUSE_STATE:
                drawThePause();
        }
    }

    private void drawTheTitle() {
        background(0);

        fill(255, 0, 0);
        textSize(FONT_SIZE);
        textAlign(CENTER, CENTER);
        text("Super Snake", width / 2, height / 2);

        fill(COLOR_IN_MENU);
        textAlign(CENTER, CENTER);
        textSize(FONT_SIZE / 2);
        text("Created by KADYRBAI", width - 200, height - TOP + 50);

        textSize(FONT_SIZE / 2);
        textAlign(CENTER, CENTER);
        text("Press ENTER to continue", width / 2, height - TOP);
    }

    private void drawTheSpeedSelection() {
        background(0);

        fill(COLOR_IN_MENU);

        textSize(FONT_SIZE);
        textAlign(CENTER, CENTER);
        text(speed, width / 2, height / 2);

        textSize(FONT_SIZE);
        textAlign(CENTER, CENTER);
        text("Choose the Speed", width / 2, height - TOP * 6);

        textSize(FONT_SIZE / 2);
        textAlign(CENTER, CENTER);
        text("Press ENTER to continue", width / 2, height - TOP);
    }

    private void drawTheGame() {
        background(0);

        snake.move();

        if (snake.collides(apple)) {
            snake.grow();
            apple = new Apple(field, snake);

            score++;
            if (maxResult < score) {
                maxResult = score;
            }
        }

        if (snake.isDead()) {
            currentState = RESULTS_STATE;
        }

        fill(COLOR_IN_MENU);
        textSize(FONT_SIZE / 2);
        textAlign(CENTER, CENTER);
        text("Speed: " + speed, width / 2, height - TOP + 80);
        text("Score: " + score, width / 2, height - TOP + 30);
        textSize(FONT_SIZE);
        text("Best: " + maxResult, width - 200, height - TOP + 50);

        field.draw(this);
        snake.draw(this);
        apple.draw(this);

        delay(100 / speed);
    }

    private void drawTheResult() {
        background(0);

        fill(COLOR_IN_MENU);
        textSize(FONT_SIZE);
        textAlign(CENTER, CENTER);

        if (maxResult > currentResult) {
            text("Great job", width / 2, height / 2);
        } else {
            text("You can more", width / 2, height / 2);
        }

        fill(COLOR_IN_MENU);
        textSize(FONT_SIZE / 2);
        textAlign(CENTER, CENTER);
        text("Press ENTER to continue", width / 2, height - TOP);
    }

    private void drawThePause(){
        fill(255, 0, 0);
        textSize(FONT_SIZE);
        textAlign(CENTER, CENTER);
        text("PAUSE", width / 2, height / 2 - 50);
    }

    public void keyPressed() {
        switch (currentState) {
            case TITLE_STATE:
                keyPressedInTitle();
                break;
            case SPEED_SEL_STATE:
                keyPressedInSpeedSelection();
                break;
            case GAME_STATE:
                keyPressedInGame();
                break;
            case RESULTS_STATE:
                keyPressedInResult();
                break;
            case PAUSE_STATE:
                keyPressedInPause();
        }
    }

    private void keyPressedInPause() {
        if (keyCode == TAB){
            currentState = GAME_STATE;
        }
    }

    private void keyPressedInTitle() {
        if (keyCode == ENTER) {
            currentState = SPEED_SEL_STATE;
        }
    }

    private void keyPressedInSpeedSelection() {
        switch (keyCode) {
            case UP:
                if (speed < 10) {
                    speed++;
                }
                break;
            case DOWN:
                if (speed > 1) {
                    speed--;
                }
                break;
            case ENTER:
                currentState = GAME_STATE;
                break;
        }
    }

    private void keyPressedInGame() {
        switch (keyCode) {
            case UP:
                snake.turnUp();
                break;
            case DOWN:
                snake.turnDown();
                break;
            case RIGHT:
                snake.turnRight();
                break;
            case LEFT:
                snake.turnLeft();
                break;
            case TAB:
                currentState = PAUSE_STATE;
        }
    }

    private void keyPressedInResult() {
        if (keyCode == ENTER) {
            setup();
            currentState = SPEED_SEL_STATE;
            if (score > currentResult) {
                currentResult = score;
                score = 0;
            }
            score = 0;
        }
    }

    public static void main(String... args) {
        PApplet.main("Main");
    }
}
