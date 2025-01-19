import processing.core.PApplet;
import java.util.ArrayList;
import java.util.Collections;


public class Main extends PApplet {
    int turns;
    String wordAsString, guessAsString;
    char letterChecker;
    ArrayList<String> words = new ArrayList<String>();
    ArrayList<Character> guess,theWord = new ArrayList<Character>();
    Boxes b;
    int bx, by, bw, bl;
    ArrayList<Boxes> backgroundBoxes;
    boolean gameOver = false;
    public void settings(){

        size(600,720);

    }

    public void setup(){
        bx = by = 0;
        bw = bl = 70;
        Collections.addAll(words, "angel", "apple", "abide", "bloom", "bread", "brave", "candy", "chair", "dance", "dream", "eagle", "flame", "fuzzy", "ghost", "grape", "house", "human", "ideal", "joker", "juicy", "koala", "lemon", "llama", "mango", "month", "night", "noble", "ocean", "olive", "peach", "polar", "queen", "quiet", "realm", "river", "sharp", "spice", "table", "trend", "union", "unity", "vault", "vivid", "water", "whale", "yacht", "zebra", "windy");
        turns = 0;
        wordAsString = words.get((int) (Math.random()* words.size() + 0.1));
        for (int i = 0; i < wordAsString.length(); i++) {
            char[] a = wordAsString.toCharArray();
            theWord.add(a[i]);
        }

        guess = new ArrayList<Character>();
        backgroundBoxes = new ArrayList<Boxes>();

        for (int i = 0; i < 30; i++) {
            if(i < 5) {
                bx = (bw + 16) * i + 85;
                by = 40;
                b = new Boxes(bx,by,bl,bw,color(255),' ');
                backgroundBoxes.add(b);
            } else if(i <10) {
                bx = (bw + 16) * (i - 5) + 85;
                by = 150;
                b = new Boxes(bx,by,bl,bw,color(255), ' ');
                backgroundBoxes.add(b);
            }  else if(i <15) {
                bx = (bw + 16) * (i - 10) + 85;
                by = 260;
                b = new Boxes(bx,by,bl,bw,color(255), ' ');
                backgroundBoxes.add(b);
            } else if(i <20) {
                bx = (bw + 16) * (i - 15) + 85;
                by = 370;
                b = new Boxes(bx,by,bl,bw,color(255), ' ');
                backgroundBoxes.add(b);
            } else if(i <25) {
                bx = (bw + 16) * (i - 20) + 85;
                by = 480;
                b = new Boxes(bx,by,bl,bw,color(255), ' ');
                backgroundBoxes.add(b);
            } else if(i <= 30) {
                bx = (bw + 16) * (i - 25) + 85;
                by = 590;
                b = new Boxes(bx,by,bl,bw,color(255), ' ');
                backgroundBoxes.add(b);
            }

        }
        textSize(45);



    }

    public void draw(){

        background(180);

        for (int i = 0; i < backgroundBoxes.size(); i++) {
            Boxes bgBoxes = backgroundBoxes.get(i);
            fill(bgBoxes.boxc);
            rect(bgBoxes.boxx, bgBoxes.boxy, bgBoxes.boxl, bgBoxes.boxw);

            fill(0);
            textAlign(CENTER, CENTER);
            int textx = bgBoxes.boxx + bgBoxes.boxl/2;
            int texty = bgBoxes.boxy + bgBoxes.boxw/2;
            text(Character.toUpperCase(bgBoxes.letters), textx, texty);
        }

        if (turns == 6) {
            gameOver = true;
        }
        if(gameOver) {
            fill (173, 216, 230);
            rect(0, 180, 600,360);
            fill(0);
            textAlign(CENTER, CENTER);
            text("GAME OVER!\nThe word was: " + wordAsString.toUpperCase(), 300,340);
        }

    }

    public void keyReleased() {
            letterChecker = Character.toLowerCase(key);
            if (Character.isLetter(letterChecker)) {
                if (guess.size() < 5) {
                    guess.add(letterChecker);

                    backgroundBoxes.get(guess.size() - 1 + turns * 5).letters = guess.get(guess.size() - 1);
                }
            } else if (keyCode == ENTER) {

                if (guess.size() == theWord.size()) {
                    turns += 1;
                    for (int i = 0; i < theWord.size(); i++) {
                        if (guess.get(i).equals(theWord.get(i))) {
                            backgroundBoxes.get(i + (turns - 1) * 5).boxc = color(0, 255, 0);
                        } else if(theWord.contains(guess.get(i))) {
                            boolean matched = false;
                            for (int j = 0; j < i; j++) {
                                if(guess.get(i).equals(theWord.get(j)) && guess.get(j).equals(theWord.get(j))) {
                                    matched = true;
                                }
                            }
                            if (matched == false) {
                                backgroundBoxes.get(i + (turns - 1) * 5).boxc = color(241, 194, 50);
                            } else {
                                backgroundBoxes.get(i + (turns - 1) * 5).boxc = color(100);
                            }
                        }else {
                            backgroundBoxes.get(i + (turns - 1) * 5).boxc = color(100);
                        }
                    }
                    if(guess.equals(theWord)) {
                        gameOver = true;
                    }
                    guess.clear();
                }
            } else if (keyCode == BACKSPACE && guess.size() > 0) {
                guess.remove(guess.size() - 1);
                backgroundBoxes.get(guess.size() + turns * 5).letters = ' ';
            }
    }

    public static void main(String[] args) {

        PApplet.main("Main");

    }

}