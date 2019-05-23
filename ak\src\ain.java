import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {
    private final static int TITLE_STATE = 0;
    private final static int SPEED_SEL_STATE = 1;
    private final static int GAME_STATE = 2;
    private final static int RESULTS_STATE = 3;
    private final static int PAUSE_STATE = 4;
    private final static int MENU_STATE = 5;
    private final static int ABOUT_STATE = 6;
    private static final int COLOR_IN_MENU = 0xffd564cf;
    private static  PImage img;

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
        img = loadImage("snake1.png") ;
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
                break;
            case MENU_STATE:
                drawMenu();
                break;
            case ABOUT_STATE:
                drawAbout();

        }
    }

    private void drawAbout(){
        background(0);
        textSize(FONT_SIZE / 2);
        textAlign(CENTER, CENTER);
        text("Press Enter to go Menu Page", width / 2, height - TOP);

        textAlign(CENTER,CENTER);
        textSize(20f);
        text("The Snake design dates back to the arcade game Blockade,[3][4] developed\n and published by Gremlin in 1976." +
                "[5] It was cloned as Bigfoot Bonkers\n the same year. In 1977, Atari released two Blockade-inspired titles:\n " +
                "the arcade game Dominos and Atari VCS game\n Surround.[6] Surround was one of the nine Atari VCS\n " +
                "(later the Atari 2600) launch titles in the United States and was also sold by Sears under the name" +
                " Chase. That same year, a similar game\n was launched for the Bally Astrocade as Checkmate.[7]\n" +
                "\n" +
                "The first known personal computer version, titled Worm, \nwas programmed in 1978 by Peter Trefonas " +
                "of the US on the TRS-80,[3]\n and published by CLOAD magazine in the same year. This was followed shortly\n " +
                "afterwards with versions from the same author for\n the Commodore PET and Apple II. A microcomputer clone\n " +
                "of the Hustle arcade game, itself a clone\n of Blockade, was written by Peter Trefonas in 1979 and published\n " +
                "by CLOAD.[8] An authorized version of Hustle was published\n by Milton Bradley for the TI-99/4A in 1980.[9] " +
                "In 1982's Snake for the BBC Micro, by Dave Bresnen, the snake\n is controlled using the left and right arrow " +
                "keys relative to the direction it is heading in.\n The snake increases in speed as it gets longer, and there's " +
                "only one life; one mistake means starting from the beginning.\n" +
                "\n" +
                "Nibbler (1982) is a single-player arcade game where\n the snake fits tightly into a maze, and the gameplay " +
                "is faster than most snake designs. Another single-player version is \npart of the 1982 Tron arcade game, themed " +
                "with light cycles. It reinvigorated the\n snake concept, and many subsequent games borrowed the light cycle theme.\n" +
                "\n" +
                "Starting in 1991, Nibbles was included\n with MS-DOS for a period of time as a QBasic sample program. " +
                "In 1992 Rattler Race was released as part of the second\n Microsoft Entertainment Pack. It adds enemy " +
                "snakes to the familiar apple-eating gameplay.\n" +
                "\n" +
                "Slither.io (2016) is a massively multiplayer version of Snake.\n" +
                "\n" +
                "In 2017, Google released their version of the game as an easter egg, whenever the phrases \"snake\",\"play " +
                "snake\", \"snake game\" and \"snake video game\" are typed",width/2,height/2);



    }

    private void drawMenu(){
        background(0);


        textSize(FONT_SIZE / 2);
        textAlign(CENTER, CENTER);
        text("Press BACKSPACE to go Menu Page", width / 2, height - TOP);

        textSize(70f);
        text("BEST SCORE:",width/2-100,height/2 - 250);
        text(maxResult,width/2+200,height/2-250);

        text("ABOUT",width/2-100,height/2 - 50);



    }

    private void drawTheTitle() {

        background(0);

        fill(255, 0, 0);
        textSize(FONT_SIZE);
        textAlign(CENTER, CENTER);
        text("Super Snake", width / 2, height / 2 - 100);
        image(img,CENTER + 800,CENTER + 500);
        fill(COLOR_IN_MENU);
        textAlign(CENTER, CENTER);
        textSize(FONT_SIZE / 2);
        text("Created by KADYRBAI", width - 200, height - TOP + 50);

        textSize(FONT_SIZE / 2);
        textAlign(CENTER, CENTER);
        text("Press ENTER to continue", width / 2+200, height - TOP);


        textSize(FONT_SIZE / 2);
        textAlign(CENTER, CENTER);
        text("Press SHIFT to go Menu Page", width / 2-200, height - TOP);

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
        text("Press ENTER to continue", width / 2+200, height - TOP);

        textSize(FONT_SIZE / 2);
        textAlign(CENTER, CENTER);
        text("Press BACKSPACE to go back", width / 2 - 200, height - TOP);


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

        delay(300 / speed);
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
        text("Press ENTER to continue", width / 2+200, height - TOP);

        fill(COLOR_IN_MENU);
        textSize(FONT_SIZE / 2);
        textAlign(CENTER, CENTER);
        text("Press BACKSPACE to go Main Page", width / 2 - 200, height - TOP);


    }

    private void drawThePause(){
        fill(255, 0, 0);
        textSize(FONT_SIZE);
        textAlign(CENTER, CENTER);
        text("PAUSE", width / 2, height / 2 - 150);
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
                break;
            case MENU_STATE:
                keyPerssedMenu();
                break;
            case ABOUT_STATE:
                keyPressedAbout();
                break;
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
        }else if(keyCode == SHIFT){
            currentState = MENU_STATE;
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
            case BACKSPACE:
                currentState = TITLE_STATE;
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
        }else if(keyCode == BACKSPACE){
            currentState = TITLE_STATE;
        }
    }
    private void keyPerssedMenu(){
        if(keyCode == BACKSPACE){
            currentState = TITLE_STATE;
        }else if(keyCode == ENTER){
            currentState = ABOUT_STATE;
        }
    }
    private void keyPressedAbout(){
        if(keyCode == BACKSPACE){
            currentState = MENU_STATE;
        }else if(keyCode == ENTER){

        }
    }

    public static void main(String... args) {
        PApplet.main("Main");
    }
}
