package cpp.cs245final;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;
import android.widget.ToggleButton;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private GameEngineV2 mGameEngine = null;
    static MediaPlayer mediaPlayer = null;
    public Button tryAgainButton;
    public Button newGameButton;
    public Button endGameButton;
    public Intent intent;
    public String difficultyLevel;
    private Button[] buttonArray;
    public Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20;
    private int guess1 = -1;
    private boolean guessed = false;

    private int guess2 = -1;

    public MediaPlayer mp;
    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences settings = getSharedPreferences("myPrefs", 0);
        musicPlayer();


        //Initializing menuButtons
        tryAgainButton = findViewById(R.id.tryAgainButton);
        newGameButton = findViewById(R.id.newGameButton);
        endGameButton = findViewById(R.id.endGameButton);
        tryAgainButton.setOnClickListener(this);
        newGameButton.setOnClickListener(this);
        endGameButton.setOnClickListener(this);

        //Receiving data from LevelSelectorActivity
        intent = getIntent();
        difficultyLevel = intent.getStringExtra("myKey");
        if (mGameEngine == null)
            mGameEngine = new GameEngineV2(Integer.parseInt(difficultyLevel));


        //ON GAME STARTUP MESSAGE
        Toast.makeText(getBaseContext(), "Creating " + difficultyLevel + "x" + difficultyLevel + " grid", Toast.LENGTH_SHORT).show();

        //initialize score text view
        score = findViewById(R.id.scoreNumber);

        //Initializing GameButtons
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        b8 = findViewById(R.id.button8);
        b9 = findViewById(R.id.button9);
        b10 = findViewById(R.id.button10);
        b11 = findViewById(R.id.button11);
        b12 = findViewById(R.id.button12);
        b13 = findViewById(R.id.button13);
        b14 = findViewById(R.id.button14);
        b15 = findViewById(R.id.button15);
        b16 = findViewById(R.id.button16);
        b17 = findViewById(R.id.button17);
        b18 = findViewById(R.id.button18);
        b19 = findViewById(R.id.button19);
        b20 = findViewById(R.id.button20);
        buttonArray = new Button[]{b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20};
        startGame();
        if (savedInstanceState != null) {
            mGameEngine = savedInstanceState.getParcelable("game_engine");
            guess1 = savedInstanceState.getInt("guess1");
            guess2 = savedInstanceState.getInt("guess2");
            int cursor = 0;
            for (int i = 0; i < 20; i++) {

                if (mGameEngine.isFlipped[i]) {
                    buttonArray[cursor].setBackgroundResource(R.drawable.blankcard);
                    buttonArray[cursor].setTextColor(Color.BLACK);
                    buttonArray[cursor].setText(mGameEngine.answers[i]);
                }

            }
        }


    }


    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences settings = getSharedPreferences("myPrefs", 0);
        SharedPreferences.Editor editor = settings.edit();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        int pos = savedInstanceState.getInt("possition");
        mediaPlayer.seekTo(pos); // RETURN SONG TO CURRENT POS
        super.onRestoreInstanceState(savedInstanceState);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Save the values you need from your textview into "outState"-object
        super.onSaveInstanceState(outState);
        outState.putInt("possition", mediaPlayer.getCurrentPosition()); // SAVE SONG AT CURRENT POS
        outState.putParcelable("game_engine", mGameEngine);
        outState.putInt("guess1", guess1);
        outState.putInt("guess2", guess2);
    }


    public void startGame() {
        int numOfTiles = Integer.parseInt(difficultyLevel);
        for (int i = 0; i < 20; i++) {
            buttonArray[i].setOnClickListener(this);
            buttonArray[i].setVisibility(View.INVISIBLE);
            buttonArray[i].setBackgroundResource(R.drawable.buttonshape);
            buttonArray[i].setTextColor(Color.parseColor("#00000000"));
        }
        for (int i = 0; i < numOfTiles; i++) {
            buttonArray[i].setVisibility(View.VISIBLE);
        }
    }


    public void musicPlayer() {
        if (mediaPlayer == null)
            mediaPlayer = MediaPlayer.create(this, R.raw.polymusic);
        ToggleButton toggle = findViewById(R.id.musicToggle);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mediaPlayer.start();
                    Toast.makeText(getBaseContext(), "Music On", Toast.LENGTH_SHORT).show();
                } else {
                    mediaPlayer.pause();
                    Toast.makeText(getBaseContext(), "Music Off", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        String temp = "";

        switch (view.getId()) {
            //sets background to the card back and erases text.
            //only if internally it wasn't guessed correctly
            case R.id.tryAgainButton:
                Toast.makeText(getBaseContext(), "hi cody", Toast.LENGTH_LONG).show();
                if (!mGameEngine.gameOver) {
                    guessed = false;
                    mGameEngine.revertTiles();
                    guess1 = -1;

                    guess2 = -1;

                    if (mGameEngine.isFlipped[0] == false) {
                        buttonArray[0].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[0].setText("");
                    }
                    if (mGameEngine.isFlipped[1] == false) {
                        buttonArray[1].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[1].setText("");
                    }
                    if (mGameEngine.isFlipped[2] == false) {
                        buttonArray[2].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[2].setText("");
                    }
                    if (mGameEngine.isFlipped[3] == false) {
                        buttonArray[3].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[3].setText("");
                    }
                    if (mGameEngine.isFlipped[4] == false) {
                        buttonArray[4].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[4].setText("");
                    }
                    if (mGameEngine.isFlipped[5] == false) {
                        buttonArray[5].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[5].setText("");
                    }
                    if (mGameEngine.isFlipped[6] == false) {
                        buttonArray[6].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[6].setText("");
                    }
                    if (mGameEngine.isFlipped[7] == false) {
                        buttonArray[7].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[7].setText("");
                    }
                    if (mGameEngine.isFlipped[8] == false) {
                        buttonArray[8].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[8].setText("");
                    }
                    if (mGameEngine.isFlipped[9] == false) {
                        buttonArray[9].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[9].setText("");
                    }
                    if (mGameEngine.isFlipped[10] == false) {
                        buttonArray[10].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[10].setText("");
                    }
                    if (mGameEngine.isFlipped[11] == false) {
                        buttonArray[11].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[11].setText("");
                    }
                    if (mGameEngine.isFlipped[12] == false) {
                        buttonArray[12].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[12].setText("");
                    }
                    if (mGameEngine.isFlipped[13] == false) {
                        buttonArray[13].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[13].setText("");
                    }
                    if (mGameEngine.isFlipped[14] == false) {
                        buttonArray[14].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[14].setText("");
                    }
                    if (mGameEngine.isFlipped[15] == false) {
                        buttonArray[15].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[15].setText("");
                    }
                    if (mGameEngine.isFlipped[16] == false) {
                        buttonArray[16].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[16].setText("");
                    }
                    if (mGameEngine.isFlipped[17] == false) {
                        buttonArray[17].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[17].setText("");
                    }
                    if (mGameEngine.isFlipped[18] == false) {
                        buttonArray[18].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[18].setText("");
                    }
                    if (mGameEngine.isFlipped[19] == false) {
                        buttonArray[19].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[19].setText("");
                    }
                }


                break;

            case R.id.newGameButton:
                Toast.makeText(getBaseContext(), "New Game!", Toast.LENGTH_LONG).show();

                final Intent i = new Intent(this, MenuActivity.class);
                startActivity(i);
                break;


            case R.id.endGameButton:
                Toast.makeText(getBaseContext(), "Game Terminated!", Toast.LENGTH_LONG).show();
                //endGame();
                mGameEngine.gameOver = true;
                int cursor = 0;
                for (int x = 0; x < 20; x++) {

                        buttonArray[cursor].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[cursor].setTextColor(Color.BLACK);
                        buttonArray[cursor].setText(mGameEngine.answers[x]);
                        //comment out the below for dev mode where you can see the answers and
                        //still get points
                        mGameEngine.isFlipped[x] = true;

                        cursor++;

                }


                break;


            //GAME BUTTON "Flip" function
            //stores the first two buttons as guesses, and compares them. If false, stops
            case R.id.button1:

                if (mGameEngine.isFlipped[0] == false) {
                    if (guess1 == -1) {
                        buttonArray[0].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[0].setTextColor(Color.BLACK);
                        buttonArray[0].setText(mGameEngine.answers[0]);
                        mGameEngine.turnFaceUp(0);
                        guess1 = 0;
                    } else if (guess2 == -1) {
                        buttonArray[0].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[0].setTextColor(Color.BLACK);
                        buttonArray[0].setText(mGameEngine.answers[0]);
                        mGameEngine.turnFaceUp(0);
                        guess2 = 0;

                    }
                }


                break;
            case R.id.button2:
//                buttonArray[1].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[1].setTextColor(Color.BLACK);
//                buttonArray[1].setText(mGameEngine.answers[0][1]);
//                mGameEngine.turnFaceUp(0, 1);


                if (mGameEngine.isFlipped[1] == false) {
                    if (guess1 == -1) {
                        buttonArray[1].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[1].setTextColor(Color.BLACK);
                        buttonArray[1].setText(mGameEngine.answers[1]);
                        mGameEngine.turnFaceUp(1);
                        guess1 = 1;

                    } else if (guess2 == -1) {
                        buttonArray[1].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[1].setTextColor(Color.BLACK);
                        buttonArray[1].setText(mGameEngine.answers[1]);
                        mGameEngine.turnFaceUp(1);
                        guess2 = 1;
                    }
                }
                break;
            case R.id.button3:
//                buttonArray[2].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[2].setTextColor(Color.BLACK);
//                buttonArray[2].setText(mGameEngine.answers[0][2]);
//                mGameEngine.turnFaceUp(0, 2);

                if (mGameEngine.isFlipped[2] == false) {
                    if (guess1 == -1) {
                        buttonArray[2].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[2].setTextColor(Color.BLACK);
                        buttonArray[2].setText(mGameEngine.answers[2]);
                        mGameEngine.turnFaceUp(2);
                        guess1 = 2;
                    } else if (guess2 == -1) {
                        buttonArray[2].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[2].setTextColor(Color.BLACK);
                        buttonArray[2].setText(mGameEngine.answers[2]);
                        mGameEngine.turnFaceUp(2);
                        guess2 = 2;
                    }
                }

                break;
            case R.id.button4:
//                buttonArray[3].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[3].setTextColor(Color.BLACK);
//                buttonArray[3].setText(mGameEngine.answers[0][3]);
//                mGameEngine.turnFaceUp(0, 3);
                if (mGameEngine.isFlipped[3] == false) {
                    if (guess1 == -1) {
                        buttonArray[3].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[3].setTextColor(Color.BLACK);
                        buttonArray[3].setText(mGameEngine.answers[3]);
                        mGameEngine.turnFaceUp( 3);
                        guess1 = 3;
                    } else if (guess2 == -1) {
                        buttonArray[3].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[3].setTextColor(Color.BLACK);
                        buttonArray[3].setText(mGameEngine.answers[3]);
                        mGameEngine.turnFaceUp( 3);
                        guess2 = 3;
                    }
                }

                break;
            case R.id.button5:
//                buttonArray[4].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[4].setTextColor(Color.BLACK);
//                buttonArray[4].setText(mGameEngine.answers[0][4]);
//                mGameEngine.turnFaceUp(0, 4);
                if (mGameEngine.isFlipped[4] == false) {
                    if (guess1 == -1) {
                        buttonArray[4].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[4].setTextColor(Color.BLACK);
                        buttonArray[4].setText(mGameEngine.answers[4]);
                        mGameEngine.turnFaceUp( 4);
                        guess1 = 4;
                    } else if (guess2 == -1) {
                        buttonArray[4].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[4].setTextColor(Color.BLACK);
                        buttonArray[4].setText(mGameEngine.answers[4]);
                        mGameEngine.turnFaceUp(4);
                        guess2 = 4;
                    }
                }

                break;
            case R.id.button6:
//                buttonArray[5].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[5].setTextColor(Color.BLACK);
//                buttonArray[5].setText(mGameEngine.answers[1][0]);
//                mGameEngine.turnFaceUp(1, 0);
                if (mGameEngine.isFlipped[5] == false) {
                    if (guess1 == -1) {
                        buttonArray[5].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[5].setTextColor(Color.BLACK);
                        buttonArray[5].setText(mGameEngine.answers[5]);
                        mGameEngine.turnFaceUp(5);
                        guess1 = 5;
                    } else if (guess2 == -1) {
                        buttonArray[5].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[5].setTextColor(Color.BLACK);
                        buttonArray[5].setText(mGameEngine.answers[5]);
                        mGameEngine.turnFaceUp(5);
                        guess2 = 5;
                    }
                }

                break;
            case R.id.button7:
//                buttonArray[6].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[6].setTextColor(Color.BLACK);
//                buttonArray[6].setText(mGameEngine.answers[1][1]);
//                mGameEngine.turnFaceUp(1, 1);
                if (mGameEngine.isFlipped[6] == false) {
                    if (guess1 == -1) {
                        buttonArray[6].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[6].setTextColor(Color.BLACK);
                        buttonArray[6].setText(mGameEngine.answers[6]);
                        mGameEngine.turnFaceUp(6);
                        guess1 = 6;
                    } else if (guess2 == -1) {
                        buttonArray[6].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[6].setTextColor(Color.BLACK);
                        buttonArray[6].setText(mGameEngine.answers[6]);
                        mGameEngine.turnFaceUp(6);
                        guess2 = 6;
                    }
                }

                break;
            case R.id.button8:
//                buttonArray[7].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[7].setTextColor(Color.BLACK);
//                buttonArray[7].setText(mGameEngine.answers[1][2]);
//                mGameEngine.turnFaceUp(1, 2);
                if (mGameEngine.isFlipped[7] == false) {
                    if (guess1 == -1) {
                        buttonArray[7].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[7].setTextColor(Color.BLACK);
                        buttonArray[7].setText(mGameEngine.answers[7]);
                        mGameEngine.turnFaceUp(7);
                        guess1 = 7;
                    } else if (guess2 == -1) {
                        buttonArray[7].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[7].setTextColor(Color.BLACK);
                        buttonArray[7].setText(mGameEngine.answers[7]);
                        mGameEngine.turnFaceUp(7);
                        guess2 = 7;
                    }
                }

                break;
            case R.id.button9:
//                buttonArray[8].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[8].setTextColor(Color.BLACK);
//                buttonArray[8].setText(mGameEngine.answers[1][3]);
//                mGameEngine.turnFaceUp(1, 3);
                if (mGameEngine.isFlipped[8] == false) {
                    if (guess1 == -1) {
                        buttonArray[8].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[8].setTextColor(Color.BLACK);
                        buttonArray[8].setText(mGameEngine.answers[8]);
                        mGameEngine.turnFaceUp(8);
                        guess1 = 8;
                    } else if (guess2 == -1) {
                        buttonArray[8].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[8].setTextColor(Color.BLACK);
                        buttonArray[8].setText(mGameEngine.answers[8]);
                        mGameEngine.turnFaceUp(8);
                        guess2 = 8;
                    }
                }

                break;
            case R.id.button10:
//                buttonArray[9].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[9].setTextColor(Color.BLACK);
//                buttonArray[9].setText(mGameEngine.answers[1][4]);
//                mGameEngine.turnFaceUp(1, 4);
                if (mGameEngine.isFlipped[9] == false) {
                    if (guess1 == -1) {
                        buttonArray[9].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[9].setTextColor(Color.BLACK);
                        buttonArray[9].setText(mGameEngine.answers[9]);
                        mGameEngine.turnFaceUp(9);
                        guess1 = 9;
                    } else if (guess2 == -1) {
                        buttonArray[9].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[9].setTextColor(Color.BLACK);
                        buttonArray[9].setText(mGameEngine.answers[9]);
                        mGameEngine.turnFaceUp(9);
                        guess2 = 9;
                    }
                }

                break;
            case R.id.button11:
//                buttonArray[10].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[10].setTextColor(Color.BLACK);
//                buttonArray[10].setText(mGameEngine.answers[2][0]);
//                mGameEngine.turnFaceUp(2, 0);
                if (mGameEngine.isFlipped[10] == false) {
                    if (guess1 == -1) {
                        buttonArray[10].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[10].setTextColor(Color.BLACK);
                        buttonArray[10].setText(mGameEngine.answers[10]);
                        mGameEngine.turnFaceUp(10);
                        guess1 = 10;
                    } else if (guess2 == -1) {
                        buttonArray[10].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[10].setTextColor(Color.BLACK);
                        buttonArray[10].setText(mGameEngine.answers[10]);
                        mGameEngine.turnFaceUp(10);
                        guess2 = 10;
                    }
                }

                break;
            case R.id.button12:
//                buttonArray[11].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[11].setTextColor(Color.BLACK);
//                buttonArray[11].setText(mGameEngine.answers[2][1]);
//                mGameEngine.turnFaceUp(2, 1);
                if (mGameEngine.isFlipped[11] == false) {
                    if (guess1 == -1) {
                        buttonArray[11].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[11].setTextColor(Color.BLACK);
                        buttonArray[11].setText(mGameEngine.answers[11]);
                        mGameEngine.turnFaceUp(11);
                        guess1 = 11;
                    } else if (guess2 == -1) {
                        buttonArray[11].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[11].setTextColor(Color.BLACK);
                        buttonArray[11].setText(mGameEngine.answers[11]);
                        mGameEngine.turnFaceUp(11);
                        guess2 = 11;
                    }
                }

                break;
            case R.id.button13:
//                buttonArray[12].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[12].setTextColor(Color.BLACK);
//                buttonArray[12].setText(mGameEngine.answers[2][2]);
//                mGameEngine.turnFaceUp(2, 2);
                if (mGameEngine.isFlipped[12] == false) {
                    if (guess1 == -1) {
                        buttonArray[12].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[12].setTextColor(Color.BLACK);
                        buttonArray[12].setText(mGameEngine.answers[12]);
                        mGameEngine.turnFaceUp(12);
                        guess1 = 12;
                    } else if (guess2 == -1) {
                        buttonArray[12].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[12].setTextColor(Color.BLACK);
                        buttonArray[12].setText(mGameEngine.answers[12]);
                        mGameEngine.turnFaceUp(12);
                        guess2 = 12;
                    }
                }

                break;
            case R.id.button14:
//                buttonArray[13].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[13].setTextColor(Color.BLACK);
//                buttonArray[13].setText(mGameEngine.answers[2][3]);
//                mGameEngine.turnFaceUp(2, 3);
                if (mGameEngine.isFlipped[13] == false) {
                    if (guess1 == -1) {
                        buttonArray[13].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[13].setTextColor(Color.BLACK);
                        buttonArray[13].setText(mGameEngine.answers[13]);
                        mGameEngine.turnFaceUp(13);
                        guess1 = 13;
                    } else if (guess2 == -1) {
                        buttonArray[13].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[13].setTextColor(Color.BLACK);
                        buttonArray[13].setText(mGameEngine.answers[13]);
                        mGameEngine.turnFaceUp(13);
                        guess2 = 13;
                    }
                }

                break;
            case R.id.button15:
//                buttonArray[14].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[14].setTextColor(Color.BLACK);
//                buttonArray[14].setText(mGameEngine.answers[2][4]);
//                mGameEngine.turnFaceUp(2, 4);
                if (mGameEngine.isFlipped[14] == false) {
                    if (guess1 == -1) {
                        buttonArray[14].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[14].setTextColor(Color.BLACK);
                        buttonArray[14].setText(mGameEngine.answers[14]);
                        mGameEngine.turnFaceUp(14);
                        guess1 =14;
                    } else if (guess2 == -1) {
                        buttonArray[14].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[14].setTextColor(Color.BLACK);
                        buttonArray[14].setText(mGameEngine.answers[14]);
                        mGameEngine.turnFaceUp(14);
                        guess2 = 14;
                    }
                }

                break;
            case R.id.button16:
//                buttonArray[15].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[15].setTextColor(Color.BLACK);
//                buttonArray[15].setText(mGameEngine.answers[3][0]);
//                mGameEngine.turnFaceUp(3, 0);

                if (mGameEngine.isFlipped[15] == false) {
                    if (guess1 == -1) {
                        buttonArray[15].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[15].setTextColor(Color.BLACK);
                        buttonArray[15].setText(mGameEngine.answers[15]);
                        mGameEngine.turnFaceUp(15);
                        guess1 = 15;
                    } else if (guess2 == -1) {
                        buttonArray[15].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[15].setTextColor(Color.BLACK);
                        buttonArray[15].setText(mGameEngine.answers[15]);
                        mGameEngine.turnFaceUp(15);
                        guess2 = 15;
                    }
                }

                break;
            case R.id.button17:
//                buttonArray[16].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[16].setTextColor(Color.BLACK);
//                buttonArray[16].setText(mGameEngine.answers[3][1]);
//                mGameEngine.turnFaceUp(3, 1);

                if (mGameEngine.isFlipped[16] == false) {
                    if (guess1 == -1) {
                        buttonArray[16].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[16].setTextColor(Color.BLACK);
                        buttonArray[16].setText(mGameEngine.answers[16]);
                        mGameEngine.turnFaceUp(16);
                        guess1 = 16;
                    } else if (guess2 == -1) {
                        buttonArray[16].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[16].setTextColor(Color.BLACK);
                        buttonArray[16].setText(mGameEngine.answers[16]);
                        mGameEngine.turnFaceUp(16);
                        guess2 = 16;
                    }
                }

                break;
            case R.id.button18:
//                buttonArray[17].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[17].setTextColor(Color.BLACK);
//                buttonArray[17].setText(mGameEngine.answers[3][2]);
//                mGameEngine.turnFaceUp(3, 2);
                if (mGameEngine.isFlipped[17] == false) {
                    if (guess1 == -1) {
                        buttonArray[17].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[17].setTextColor(Color.BLACK);
                        buttonArray[17].setText(mGameEngine.answers[17]);
                        mGameEngine.turnFaceUp(17);
                        guess1 = 17;
                    } else if (guess2 == -1) {
                        buttonArray[17].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[17].setTextColor(Color.BLACK);
                        buttonArray[17].setText(mGameEngine.answers[17]);
                        mGameEngine.turnFaceUp(17);
                        guess2 = 17;
                    }
                }

                break;
            case R.id.button19:
//                buttonArray[18].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[18].setTextColor(Color.BLACK);
//                buttonArray[18].setText(mGameEngine.answers[3][3]);
//                mGameEngine.turnFaceUp(3, 3);

                if (mGameEngine.isFlipped[18] == false) {
                    if (guess1 == -1) {
                        buttonArray[18].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[18].setTextColor(Color.BLACK);
                        buttonArray[18].setText(mGameEngine.answers[18]);
                        mGameEngine.turnFaceUp(18);
                        guess1 =18;

                    } else if (guess2 == -1) {
                        buttonArray[18].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[18].setTextColor(Color.BLACK);
                        buttonArray[18].setText(mGameEngine.answers[18]);
                        mGameEngine.turnFaceUp(18);
                        guess2 = 3;
                    }
                }

                break;
            case R.id.button20:
//                buttonArray[19].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[19].setTextColor(Color.BLACK);
//                buttonArray[19].setText(mGameEngine.answers[3][4]);
//                mGameEngine.turnFaceUp(3, 4);

                if (mGameEngine.isFlipped[19] == false) {
                    if (guess1 == -1) {
                        buttonArray[19].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[19].setTextColor(Color.BLACK);
                        buttonArray[19].setText(mGameEngine.answers[19]);
                        mGameEngine.turnFaceUp(19);
                        guess1 = 19;
                    } else if (guess2 == -1) {
                        buttonArray[19].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[19].setTextColor(Color.BLACK);
                        buttonArray[19].setText(mGameEngine.answers[19]);
                        mGameEngine.turnFaceUp(19);
                        guess2 = 19;
                    }
                }

                break;


            default:
                break;
        }
        //checks after a click if there are 2 guesses queued up. If there are, it compares them
        //If it is correct, guesses are reset to -1 so the user can continue guessing
        //the guess method also updates points.
        if (guess1 != -1 && guess2 != -1 && !guessed) {
            guessed = true;
            if (mGameEngine.guess(guess1, guess2)) {
                guess1 = -1;
                guess2 = -1;
                guessed = false;
            }
            score.setText(((Integer) mGameEngine.points).toString());
        }
    }


}
