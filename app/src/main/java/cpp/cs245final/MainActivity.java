package cpp.cs245final;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    public Button tryAgainButton;
    public Button newGameButton;
    public Button endGameButton ;
    public Intent intent;
    public String difficultyLevel;
    private Button[] buttonArray;
    public Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


        //ON GAME STARTUP MESSAGE
        Toast.makeText(getBaseContext(),"Creating " + difficultyLevel+"x"+difficultyLevel+ " grid",Toast.LENGTH_SHORT).show();

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
        buttonArray = new Button[]{b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20};

        //TESTING IF ALL BUTTONS SHOW UP
        for(int i = 0; i < 20; i ++){
            buttonArray[i].setOnClickListener(this);
            buttonArray[i].setVisibility(View.VISIBLE);
            buttonArray[i].setBackgroundResource(R.drawable.buttonshape);
            buttonArray[i].setTextColor(Color.parseColor("#00000000"));
        }

    }

    public void startGame(){
        int numOfTiles = Integer.parseInt(difficultyLevel);

        for (int i = 0; i < numOfTiles; i++) {
            buttonArray[i].setOnClickListener(this);
            buttonArray[i].setVisibility(View.VISIBLE);
            buttonArray[i].setBackgroundResource(R.drawable.buttonshape);
            buttonArray[i].setTextColor(Color.parseColor("#00000000"));
        }
    }



    public void musicPlayer(){
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.polymusic);
        ToggleButton toggle = findViewById(R.id.musicToggle);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    mediaPlayer.start();

                    Toast.makeText(getBaseContext(),"Music On",Toast.LENGTH_SHORT).show();
                } else {
                    mediaPlayer.pause();
                    Toast.makeText(getBaseContext(),"Music Off",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.tryAgainButton:
                Toast.makeText(getBaseContext(),"hi cody",Toast.LENGTH_LONG).show();
                revertTiles();
                break;

            case R.id.newGameButton:
                Toast.makeText(getBaseContext(),"New Game!",Toast.LENGTH_LONG).show();
                newGame();
                break;

            case R.id.endGameButton:
                Toast.makeText(getBaseContext(),"Game Terminated!",Toast.LENGTH_LONG).show();
                endGame();
                break;

            default:
                break;
        }

    }

    private void revertTiles() {
    }

    private void newGame() {
    }

    private void endGame() {
    }


}
