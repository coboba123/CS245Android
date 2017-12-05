package cpp.cs245final;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;


/**
 * Created by matth on 12/2/2017.
 * redid the game engine without the 2d arrays to be able to parse them
 */

public class GameEngineV2 implements Parcelable {
    boolean isFlipped[];
    boolean isCorrect[];
    String answers[];
    int points = 0;
    private int difficulty = 0;
    private int amountAns[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private String possibleAns[] = {"cat", "dog", "mouse", "bunny", "ferret", "parrot", "lion", "monkey", "rhino", "bear"};
    private Random rand = new Random((System.currentTimeMillis()));
    private int totalAnswers = 0;
    boolean gameOver = false;

    //The game engine accepts the x and y for its height and width depending
    //on what difficulty the user selects. Can eventually remove x and y parameters
    //and create array based on difficulty alone.
    public GameEngineV2(int dif) {
        isFlipped = new boolean[20];
        isCorrect = new boolean[20];
        answers = new String[20];
        difficulty = dif;

        //this ensures that the random number will only be equal to a number between 0 and the
        //max number of pairs there can be for this difficulty.
        int num = rand.nextInt(difficulty / 2 - 1);
        for (int i = 0; i < 20; i++) {

            isFlipped[i] = false;
            isCorrect[i] = false;
            //randomly decides what word to try and place
            num = rand.nextInt((difficulty / 2));
            //if that word has already been used, try a different random word
            //This is highly inefficient so if anyone has a better solution please change.
            while (amountAns[num] >= 2 && totalAnswers < difficulty) {
                num = rand.nextInt((difficulty / 2));
            }
            if (amountAns[num] < 2 && totalAnswers < difficulty) {
                //gets the word from the bank at the index given by num.
                answers[i] = possibleAns[num];
                //tells that this word has been used an additional time.
                amountAns[num]++;

            }
            totalAnswers++;

        }
    }

    //turns a card faceup internally
    public void turnFaceUp(int x) {
        isFlipped[x] = true;
    }

    //takes in two x,y coordinates and checks if the strings are equal
    //If they are correct it tells the system they are both correct
    public boolean guess(int x, int y) {
        if (answers[x].equals(answers[y])) {
            isCorrect[x] = true;
            isCorrect[y] = true;
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
        for (int i = 0; i < 20; i++) {

            if (isCorrect[i] == false) {
                isFlipped[i] = false;
            }

        }
    }

    //everything after this is parcel methods which are supposed to let orientation change
    //when bundles in save instance state.

    //Recreate object from parcel
    protected GameEngineV2(Parcel in) {
        points = in.readInt();
        in.readBooleanArray(isCorrect);
        in.readBooleanArray(isFlipped);
        in.readStringArray(answers);
        difficulty = in.readInt();
        in.readStringArray(possibleAns);
        totalAnswers = in.readInt();
        gameOver = in.readByte() != 0x00;

    }


    //to be honest don't really know what this is, android just made me make it.
    public static final Creator<GameEngineV2> CREATOR = new Creator<GameEngineV2>() {
        @Override
        public GameEngineV2 createFromParcel(Parcel in) {
            return new GameEngineV2(in);
        }

        @Override
        public GameEngineV2[] newArray(int size) {
            return new GameEngineV2[size];
        }
    };

    //TBH don't know how to use this but it shouldn't effect anything.
    @Override
    public int describeContents() {
        return 0;
    }

    //save object in parcel
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(points);
        parcel.writeBooleanArray(isCorrect);
        parcel.writeBooleanArray(isFlipped);
        parcel.writeStringArray(answers);
        parcel.writeInt(difficulty);
        parcel.writeStringArray(possibleAns);
        parcel.writeInt(totalAnswers);
        parcel.writeByte((byte) (gameOver ? 0x01 : 0x00));


    }
}

