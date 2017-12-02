package cpp.cs245final;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button tryAgainButton = findViewById(R.id.tryAgainButton);
        final Button newGameButton = findViewById(R.id.newGameButton);
        final Button endGameButton = findViewById(R.id.endGameButton);

        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.polymusic);

        tryAgainButton.setOnClickListener(this);
        newGameButton.setOnClickListener(this);
        endGameButton.setOnClickListener(this);

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
                break;

            case R.id.newGameButton:
                Toast.makeText(getBaseContext(),"New Game!",Toast.LENGTH_LONG).show();
                break;

            case R.id.endGameButton:
                Toast.makeText(getBaseContext(),"Game Terminated!",Toast.LENGTH_LONG).show();
                break;

            default:
                break;
        }

    }




}
