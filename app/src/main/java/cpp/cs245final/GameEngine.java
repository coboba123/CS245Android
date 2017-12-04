package cpp.cs245final;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Bundle;

import java.util.Random;


/**
 * Created by matth on 12/2/2017.
 */

public class GameEngine implements Parcelable {
    boolean isFlipped[][];
    boolean isCorrect[][];
    String answers[][];
    int points = 0;
    private int difficulty = 0;
    private int amountAns[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private String possibleAns[] = {"cat", "dog", "mouse", "bunny", "ferret", "parrot", "lion", "monkey", "rhino", "bear"};
    private Random rand = new Random((System.currentTimeMillis()));
    private int width;
    private int height;
    private int totalAnswers = 0;
    boolean gameOver = false;

    //The game engine accepts the x and y for its height and width depending
    //on what difficulty the user selects. Can eventually remove x and y parameters
    //and create array based on difficulty alone.
    public GameEngine(int x, int y, int dif) {
        width = x;
        height = y;
        isFlipped = new boolean[x][y];
        isCorrect = new boolean[x][y];
        answers = new String[x][y];
        difficulty = dif;

        //this ensures that the random number will only be equal to a number between 0 and the
        //max number of pairs there can be for this difficulty.
        int num = rand.nextInt(difficulty / 2 - 1);
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                isFlipped[i][j] = false;
                isCorrect[i][j] = false;
                //randomly decides what word to try and place
                num = rand.nextInt((difficulty / 2));
                //if that word has already been used, try a different random word
                //This is highly inefficient so if anyone has a better solution please change.
                while (amountAns[num] >= 2 && totalAnswers < difficulty) {
                    num = rand.nextInt((difficulty / 2));
                }
                if (amountAns[num] < 2 && totalAnswers < difficulty) {
                    //gets the word from the bank at the index given by num.
                    answers[i][j] = possibleAns[num];
                    //tells that this word has been used an additional time.
                    amountAns[num]++;

                }
                totalAnswers++;
            }
        }
    }

    //turns a card faceup internally
    public void turnFaceUp(int x, int y) {
        isFlipped[x][y] = true;
    }

    //takes in two x,y coordinates and checks if the strings are equal
    //If they are correct it tells the system they are both correct
    public boolean guess(int x1, int y1, int x2, int y2) {
        if (answers[x1][y1].equals(answers[x2][y2])) {
            isCorrect[x1][y1] = true;
            isCorrect[x2][y2] = true;
            points += 2;
            return true;
        } else {
            if (points > 0) {
                points--;
            }
            return false;
        }
    }

    //turns everything that is not correct facedown.
    public void revertTiles() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (isCorrect[i][j] == false) {
                    isFlipped[i][j] = false;
                }
            }
        }
    }

    //everything after this is parcel methods which are supposed to let orientation change
    //when bundles in save instance state.
    protected GameEngine(Parcel in) {
        points = in.readInt();
    }


    //to be honest don't really know what this is, android just made me make it.
    public static final Creator<GameEngine> CREATOR = new Creator<GameEngine>() {
        @Override
        public GameEngine createFromParcel(Parcel in) {
            return new GameEngine(in);
        }

        @Override
        public GameEngine[] newArray(int size) {
            return new GameEngine[size];
        }
    };

    //Edit later to save on flip.
    @Override
    public int describeContents() {
        return 0;
    }

    //edit later to save on slip
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(points);
    }
}

