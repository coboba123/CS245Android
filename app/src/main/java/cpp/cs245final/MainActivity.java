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
    private GameEngine mGameEngine = null;
    static MediaPlayer mediaPlayer = null;
    public Button tryAgainButton;
    public Button newGameButton;
    public Button endGameButton;
    public Intent intent;
    public String difficultyLevel;
    private Button[] buttonArray;
    public Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20;
    private int guess1x = -1;
    private int guess1y = -1;
    private int guess2x = -1;
    private int guess2y = -1;
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
            mGameEngine = new GameEngine(4, 5, Integer.parseInt(difficultyLevel));


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
        //mediaPlayer.pause();
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
                    mGameEngine.revertTiles();
                    guess1x = -1;
                    guess1y = -1;
                    guess2x = -1;
                    guess2y = -1;
                    if (mGameEngine.isFlipped[0][0] == false) {
                        buttonArray[0].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[0].setText("");
                    }
                    if (mGameEngine.isFlipped[0][1] == false) {
                        buttonArray[1].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[1].setText("");
                    }
                    if (mGameEngine.isFlipped[0][2] == false) {
                        buttonArray[2].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[2].setText("");
                    }
                    if (mGameEngine.isFlipped[0][3] == false) {
                        buttonArray[3].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[3].setText("");
                    }
                    if (mGameEngine.isFlipped[0][4] == false) {
                        buttonArray[4].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[4].setText("");
                    }
                    if (mGameEngine.isFlipped[1][0] == false) {
                        buttonArray[5].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[5].setText("");
                    }
                    if (mGameEngine.isFlipped[1][1] == false) {
                        buttonArray[6].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[6].setText("");
                    }
                    if (mGameEngine.isFlipped[1][2] == false) {
                        buttonArray[7].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[7].setText("");
                    }
                    if (mGameEngine.isFlipped[1][3] == false) {
                        buttonArray[8].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[8].setText("");
                    }
                    if (mGameEngine.isFlipped[1][4] == false) {
                        buttonArray[9].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[9].setText("");
                    }
                    if (mGameEngine.isFlipped[2][0] == false) {
                        buttonArray[10].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[10].setText("");
                    }
                    if (mGameEngine.isFlipped[2][1] == false) {
                        buttonArray[11].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[11].setText("");
                    }
                    if (mGameEngine.isFlipped[2][2] == false) {
                        buttonArray[12].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[12].setText("");
                    }
                    if (mGameEngine.isFlipped[2][3] == false) {
                        buttonArray[13].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[13].setText("");
                    }
                    if (mGameEngine.isFlipped[2][4] == false) {
                        buttonArray[14].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[14].setText("");
                    }
                    if (mGameEngine.isFlipped[3][0] == false) {
                        buttonArray[15].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[15].setText("");
                    }
                    if (mGameEngine.isFlipped[3][1] == false) {
                        buttonArray[16].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[16].setText("");
                    }
                    if (mGameEngine.isFlipped[3][2] == false) {
                        buttonArray[17].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[17].setText("");
                    }
                    if (mGameEngine.isFlipped[3][3] == false) {
                        buttonArray[18].setBackgroundResource(R.drawable.buttonshape);
                        buttonArray[18].setText("");
                    }
                    if (mGameEngine.isFlipped[3][4] == false) {
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
                for (int x = 0; x < 4; x++) {
                    for (int y = 0; y < 5; y++) {
                        buttonArray[cursor].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[cursor].setTextColor(Color.BLACK);
                        buttonArray[cursor].setText(mGameEngine.answers[x][y]);
                        //comment out the below for dev mode where you can see the answers and
                        //still get points
                        mGameEngine.isFlipped[x][y] = true;

                        cursor++;
                    }
                }


                break;


            //GAME BUTTON "Flip" function
            //stores the first two buttons as guesses, and compares them. If false, stops
            case R.id.button1:

                if (mGameEngine.isFlipped[0][0] == false) {
                    if (guess1x == -1) {
                        buttonArray[0].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[0].setTextColor(Color.BLACK);
                        buttonArray[0].setText(mGameEngine.answers[0][0]);
                        mGameEngine.turnFaceUp(0, 0);
                        guess1x = 0;
                        guess1y = 0;
                    } else if (guess2x == -1) {
                        buttonArray[0].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[0].setTextColor(Color.BLACK);
                        buttonArray[0].setText(mGameEngine.answers[0][0]);
                        mGameEngine.turnFaceUp(0, 0);
                        guess2x = 0;
                        guess2y = 0;
                    }
                }


                break;
            case R.id.button2:
//                buttonArray[1].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[1].setTextColor(Color.BLACK);
//                buttonArray[1].setText(mGameEngine.answers[0][1]);
//                mGameEngine.turnFaceUp(0, 1);


                if (mGameEngine.isFlipped[0][1] == false) {
                    if (guess1x == -1) {
                        buttonArray[1].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[1].setTextColor(Color.BLACK);
                        buttonArray[1].setText(mGameEngine.answers[0][1]);
                        mGameEngine.turnFaceUp(0, 1);
                        guess1x = 0;
                        guess1y = 1;
                    } else if (guess2x == -1) {
                        buttonArray[1].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[1].setTextColor(Color.BLACK);
                        buttonArray[1].setText(mGameEngine.answers[0][1]);
                        mGameEngine.turnFaceUp(0, 1);
                        guess2x = 0;
                        guess2y = 1;
                    }
                }
                break;
            case R.id.button3:
//                buttonArray[2].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[2].setTextColor(Color.BLACK);
//                buttonArray[2].setText(mGameEngine.answers[0][2]);
//                mGameEngine.turnFaceUp(0, 2);

                if (mGameEngine.isFlipped[0][2] == false) {
                    if (guess1x == -1) {
                        buttonArray[2].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[2].setTextColor(Color.BLACK);
                        buttonArray[2].setText(mGameEngine.answers[0][2]);
                        mGameEngine.turnFaceUp(0, 2);
                        guess1x = 0;
                        guess1y = 2;
                    } else if (guess2x == -1) {
                        buttonArray[2].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[2].setTextColor(Color.BLACK);
                        buttonArray[2].setText(mGameEngine.answers[0][2]);
                        mGameEngine.turnFaceUp(0, 2);
                        guess2x = 0;
                        guess2y = 2;
                    }
                }

                break;
            case R.id.button4:
//                buttonArray[3].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[3].setTextColor(Color.BLACK);
//                buttonArray[3].setText(mGameEngine.answers[0][3]);
//                mGameEngine.turnFaceUp(0, 3);
                if (mGameEngine.isFlipped[0][3] == false) {
                    if (guess1x == -1) {
                        buttonArray[3].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[3].setTextColor(Color.BLACK);
                        buttonArray[3].setText(mGameEngine.answers[0][3]);
                        mGameEngine.turnFaceUp(0, 3);
                        guess1x = 0;
                        guess1y = 3;
                    } else if (guess2x == -1) {
                        buttonArray[3].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[3].setTextColor(Color.BLACK);
                        buttonArray[3].setText(mGameEngine.answers[0][3]);
                        mGameEngine.turnFaceUp(0, 3);
                        guess2x = 0;
                        guess2y = 3;
                    }
                }

                break;
            case R.id.button5:
//                buttonArray[4].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[4].setTextColor(Color.BLACK);
//                buttonArray[4].setText(mGameEngine.answers[0][4]);
//                mGameEngine.turnFaceUp(0, 4);
                if (mGameEngine.isFlipped[0][4] == false) {
                    if (guess1x == -1) {
                        buttonArray[4].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[4].setTextColor(Color.BLACK);
                        buttonArray[4].setText(mGameEngine.answers[0][4]);
                        mGameEngine.turnFaceUp(0, 4);
                        guess1x = 0;
                        guess1y = 4;
                    } else if (guess2x == -1) {
                        buttonArray[4].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[4].setTextColor(Color.BLACK);
                        buttonArray[4].setText(mGameEngine.answers[0][4]);
                        mGameEngine.turnFaceUp(0, 4);
                        guess2x = 0;
                        guess2y = 4;
                    }
                }

                break;
            case R.id.button6:
//                buttonArray[5].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[5].setTextColor(Color.BLACK);
//                buttonArray[5].setText(mGameEngine.answers[1][0]);
//                mGameEngine.turnFaceUp(1, 0);
                if (mGameEngine.isFlipped[1][0] == false) {
                    if (guess1x == -1) {
                        buttonArray[5].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[5].setTextColor(Color.BLACK);
                        buttonArray[5].setText(mGameEngine.answers[1][0]);
                        mGameEngine.turnFaceUp(1, 0);
                        guess1x = 1;
                        guess1y = 0;
                    } else if (guess2x == -1) {
                        buttonArray[5].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[5].setTextColor(Color.BLACK);
                        buttonArray[5].setText(mGameEngine.answers[1][0]);
                        mGameEngine.turnFaceUp(1, 0);
                        guess2x = 1;
                        guess2y = 0;
                    }
                }

                break;
            case R.id.button7:
//                buttonArray[6].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[6].setTextColor(Color.BLACK);
//                buttonArray[6].setText(mGameEngine.answers[1][1]);
//                mGameEngine.turnFaceUp(1, 1);
                if (mGameEngine.isFlipped[1][1] == false) {
                    if (guess1x == -1) {
                        buttonArray[6].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[6].setTextColor(Color.BLACK);
                        buttonArray[6].setText(mGameEngine.answers[1][1]);
                        mGameEngine.turnFaceUp(1, 1);
                        guess1x = 1;
                        guess1y = 1;
                    } else if (guess2x == -1) {
                        buttonArray[6].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[6].setTextColor(Color.BLACK);
                        buttonArray[6].setText(mGameEngine.answers[1][1]);
                        mGameEngine.turnFaceUp(1, 1);
                        guess2x = 1;
                        guess2y = 1;
                    }
                }

                break;
            case R.id.button8:
//                buttonArray[7].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[7].setTextColor(Color.BLACK);
//                buttonArray[7].setText(mGameEngine.answers[1][2]);
//                mGameEngine.turnFaceUp(1, 2);
                if (mGameEngine.isFlipped[1][2] == false) {
                    if (guess1x == -1) {
                        buttonArray[7].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[7].setTextColor(Color.BLACK);
                        buttonArray[7].setText(mGameEngine.answers[1][2]);
                        mGameEngine.turnFaceUp(1, 2);
                        guess1x = 1;
                        guess1y = 2;
                    } else if (guess2x == -1) {
                        buttonArray[7].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[7].setTextColor(Color.BLACK);
                        buttonArray[7].setText(mGameEngine.answers[1][2]);
                        mGameEngine.turnFaceUp(1, 2);
                        guess2x = 1;
                        guess2y = 2;
                    }
                }

                break;
            case R.id.button9:
//                buttonArray[8].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[8].setTextColor(Color.BLACK);
//                buttonArray[8].setText(mGameEngine.answers[1][3]);
//                mGameEngine.turnFaceUp(1, 3);
                if (mGameEngine.isFlipped[1][3] == false) {
                    if (guess1x == -1) {
                        buttonArray[8].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[8].setTextColor(Color.BLACK);
                        buttonArray[8].setText(mGameEngine.answers[1][3]);
                        mGameEngine.turnFaceUp(1, 3);
                        guess1x = 1;
                        guess1y = 3;
                    } else if (guess2x == -1) {
                        buttonArray[8].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[8].setTextColor(Color.BLACK);
                        buttonArray[8].setText(mGameEngine.answers[1][3]);
                        mGameEngine.turnFaceUp(1, 3);
                        guess2x = 1;
                        guess2y = 3;
                    }
                }

                break;
            case R.id.button10:
//                buttonArray[9].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[9].setTextColor(Color.BLACK);
//                buttonArray[9].setText(mGameEngine.answers[1][4]);
//                mGameEngine.turnFaceUp(1, 4);
                if (mGameEngine.isFlipped[1][4] == false) {
                    if (guess1x == -1) {
                        buttonArray[9].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[9].setTextColor(Color.BLACK);
                        buttonArray[9].setText(mGameEngine.answers[1][4]);
                        mGameEngine.turnFaceUp(1, 4);
                        guess1x = 1;
                        guess1y = 4;
                    } else if (guess2x == -1) {
                        buttonArray[9].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[9].setTextColor(Color.BLACK);
                        buttonArray[9].setText(mGameEngine.answers[1][4]);
                        mGameEngine.turnFaceUp(1, 4);
                        guess2x = 1;
                        guess2y = 4;
                    }
                }

                break;
            case R.id.button11:
//                buttonArray[10].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[10].setTextColor(Color.BLACK);
//                buttonArray[10].setText(mGameEngine.answers[2][0]);
//                mGameEngine.turnFaceUp(2, 0);
                if (mGameEngine.isFlipped[2][0] == false) {
                    if (guess1x == -1) {
                        buttonArray[10].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[10].setTextColor(Color.BLACK);
                        buttonArray[10].setText(mGameEngine.answers[2][0]);
                        mGameEngine.turnFaceUp(2, 0);
                        guess1x = 2;
                        guess1y = 0;
                    } else if (guess2x == -1) {
                        buttonArray[10].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[10].setTextColor(Color.BLACK);
                        buttonArray[10].setText(mGameEngine.answers[2][0]);
                        mGameEngine.turnFaceUp(2, 0);
                        guess2x = 2;
                        guess2y = 0;
                    }
                }

                break;
            case R.id.button12:
//                buttonArray[11].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[11].setTextColor(Color.BLACK);
//                buttonArray[11].setText(mGameEngine.answers[2][1]);
//                mGameEngine.turnFaceUp(2, 1);
                if (mGameEngine.isFlipped[2][1] == false) {
                    if (guess1x == -1) {
                        buttonArray[11].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[11].setTextColor(Color.BLACK);
                        buttonArray[11].setText(mGameEngine.answers[2][1]);
                        mGameEngine.turnFaceUp(2, 1);
                        guess1x = 2;
                        guess1y = 1;
                    } else if (guess2x == -1) {
                        buttonArray[11].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[11].setTextColor(Color.BLACK);
                        buttonArray[11].setText(mGameEngine.answers[2][1]);
                        mGameEngine.turnFaceUp(2, 1);
                        guess2x = 2;
                        guess2y = 1;
                    }
                }

                break;
            case R.id.button13:
//                buttonArray[12].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[12].setTextColor(Color.BLACK);
//                buttonArray[12].setText(mGameEngine.answers[2][2]);
//                mGameEngine.turnFaceUp(2, 2);
                if (mGameEngine.isFlipped[2][2] == false) {
                    if (guess1x == -1) {
                        buttonArray[12].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[12].setTextColor(Color.BLACK);
                        buttonArray[12].setText(mGameEngine.answers[2][2]);
                        mGameEngine.turnFaceUp(2, 2);
                        guess1x = 2;
                        guess1y = 2;
                    } else if (guess2x == -1) {
                        buttonArray[12].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[12].setTextColor(Color.BLACK);
                        buttonArray[12].setText(mGameEngine.answers[2][2]);
                        mGameEngine.turnFaceUp(2, 2);
                        guess2x = 2;
                        guess2y = 2;
                    }
                }

                break;
            case R.id.button14:
//                buttonArray[13].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[13].setTextColor(Color.BLACK);
//                buttonArray[13].setText(mGameEngine.answers[2][3]);
//                mGameEngine.turnFaceUp(2, 3);
                if (mGameEngine.isFlipped[2][3] == false) {
                    if (guess1x == -1) {
                        buttonArray[13].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[13].setTextColor(Color.BLACK);
                        buttonArray[13].setText(mGameEngine.answers[2][3]);
                        mGameEngine.turnFaceUp(2, 3);
                        guess1x = 2;
                        guess1y = 3;
                    } else if (guess2x == -1) {
                        buttonArray[13].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[13].setTextColor(Color.BLACK);
                        buttonArray[13].setText(mGameEngine.answers[2][3]);
                        mGameEngine.turnFaceUp(2, 3);
                        guess2x = 2;
                        guess2y = 3;
                    }
                }

                break;
            case R.id.button15:
//                buttonArray[14].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[14].setTextColor(Color.BLACK);
//                buttonArray[14].setText(mGameEngine.answers[2][4]);
//                mGameEngine.turnFaceUp(2, 4);
                if (mGameEngine.isFlipped[2][4] == false) {
                    if (guess1x == -1) {
                        buttonArray[14].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[14].setTextColor(Color.BLACK);
                        buttonArray[14].setText(mGameEngine.answers[2][4]);
                        mGameEngine.turnFaceUp(2, 4);
                        guess1x = 2;
                        guess1y = 4;
                    } else if (guess2x == -1) {
                        buttonArray[14].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[14].setTextColor(Color.BLACK);
                        buttonArray[14].setText(mGameEngine.answers[2][4]);
                        mGameEngine.turnFaceUp(2, 4);
                        guess2x = 2;
                        guess2y = 4;
                    }
                }

                break;
            case R.id.button16:
//                buttonArray[15].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[15].setTextColor(Color.BLACK);
//                buttonArray[15].setText(mGameEngine.answers[3][0]);
//                mGameEngine.turnFaceUp(3, 0);

                if (mGameEngine.isFlipped[3][0] == false) {
                    if (guess1x == -1) {
                        buttonArray[15].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[15].setTextColor(Color.BLACK);
                        buttonArray[15].setText(mGameEngine.answers[3][0]);
                        mGameEngine.turnFaceUp(3, 0);
                        guess1x = 3;
                        guess1y = 0;
                    } else if (guess2x == -1) {
                        buttonArray[15].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[15].setTextColor(Color.BLACK);
                        buttonArray[15].setText(mGameEngine.answers[3][0]);
                        mGameEngine.turnFaceUp(3, 0);
                        guess2x = 3;
                        guess2y = 0;
                    }
                }

                break;
            case R.id.button17:
//                buttonArray[16].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[16].setTextColor(Color.BLACK);
//                buttonArray[16].setText(mGameEngine.answers[3][1]);
//                mGameEngine.turnFaceUp(3, 1);

                if (mGameEngine.isFlipped[3][1] == false) {
                    if (guess1x == -1) {
                        buttonArray[16].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[16].setTextColor(Color.BLACK);
                        buttonArray[16].setText(mGameEngine.answers[3][1]);
                        mGameEngine.turnFaceUp(3, 1);
                        guess1x = 3;
                        guess1y = 1;
                    } else if (guess2x == -1) {
                        buttonArray[16].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[16].setTextColor(Color.BLACK);
                        buttonArray[16].setText(mGameEngine.answers[3][1]);
                        mGameEngine.turnFaceUp(3, 1);
                        guess2x = 3;
                        guess2y = 1;
                    }
                }

                break;
            case R.id.button18:
//                buttonArray[17].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[17].setTextColor(Color.BLACK);
//                buttonArray[17].setText(mGameEngine.answers[3][2]);
//                mGameEngine.turnFaceUp(3, 2);
                if (mGameEngine.isFlipped[3][2] == false) {
                    if (guess1x == -1) {
                        buttonArray[17].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[17].setTextColor(Color.BLACK);
                        buttonArray[17].setText(mGameEngine.answers[3][2]);
                        mGameEngine.turnFaceUp(3, 2);
                        guess1x = 3;
                        guess1y = 2;
                    } else if (guess2x == -1) {
                        buttonArray[17].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[17].setTextColor(Color.BLACK);
                        buttonArray[17].setText(mGameEngine.answers[3][2]);
                        mGameEngine.turnFaceUp(3, 2);
                        guess2x = 3;
                        guess2y = 2;
                    }
                }

                break;
            case R.id.button19:
//                buttonArray[18].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[18].setTextColor(Color.BLACK);
//                buttonArray[18].setText(mGameEngine.answers[3][3]);
//                mGameEngine.turnFaceUp(3, 3);

                if (mGameEngine.isFlipped[3][3] == false) {
                    if (guess1x == -1) {
                        buttonArray[18].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[18].setTextColor(Color.BLACK);
                        buttonArray[18].setText(mGameEngine.answers[3][3]);
                        mGameEngine.turnFaceUp(3, 3);
                        guess1x = 3;
                        guess1y = 3;
                    } else if (guess2x == -1) {
                        buttonArray[18].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[18].setTextColor(Color.BLACK);
                        buttonArray[18].setText(mGameEngine.answers[3][3]);
                        mGameEngine.turnFaceUp(3, 3);
                        guess2x = 3;
                        guess2y = 3;
                    }
                }

                break;
            case R.id.button20:
//                buttonArray[19].setBackgroundResource(R.drawable.blankcard);
//                buttonArray[19].setTextColor(Color.BLACK);
//                buttonArray[19].setText(mGameEngine.answers[3][4]);
//                mGameEngine.turnFaceUp(3, 4);

                if (mGameEngine.isFlipped[3][4] == false) {
                    if (guess1x == -1) {
                        buttonArray[19].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[19].setTextColor(Color.BLACK);
                        buttonArray[19].setText(mGameEngine.answers[3][4]);
                        mGameEngine.turnFaceUp(3, 4);
                        guess1x = 3;
                        guess1y = 4;
                    } else if (guess2x == -1) {
                        buttonArray[19].setBackgroundResource(R.drawable.blankcard);
                        buttonArray[19].setTextColor(Color.BLACK);
                        buttonArray[19].setText(mGameEngine.answers[3][4]);
                        mGameEngine.turnFaceUp(3, 4);
                        guess2x = 3;
                        guess2y = 4;
                    }
                }

                break;


            default:
                break;
        }
        //checks after a click if there are 2 guesses queued up. If there are, it compares them
        //If it is correct, guesses are reset to -1 so the user can continue guessing
        //the guess method also updates points.
        if (guess1x != -1 && guess2x != -1) {
            if (mGameEngine.guess(guess1x, guess1y, guess2x, guess2y)) {
                guess1x = -1;
                guess1y = -1;
                guess2x = -1;
                guess2y = -1;
            }
            score.setText(((Integer) mGameEngine.points).toString());
        }
    }


}
