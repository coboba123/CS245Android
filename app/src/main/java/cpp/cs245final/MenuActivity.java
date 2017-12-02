package cpp.cs245final;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final Button startGameButton = findViewById(R.id.startGameButton);
        final Button hiScoreButton = findViewById(R.id.highscoreButton);
        startGameButton.setOnClickListener(this);
        hiScoreButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){

        switch (view.getId()) {
            case R.id.startGameButton: // IF USER HITS NEW GAME BUTTON ON MAIN MENU SCREEN
                Toast.makeText(getBaseContext(),"Starting Game",Toast.LENGTH_LONG).show();
                final Intent startGame = new Intent(this,MainActivity.class);
                startActivity(startGame);
                break;

            case R.id.highscoreButton: // IF USER HITS HIGHSCORE BUTTON ON MAIN MENU SCREEN
                Toast.makeText(getBaseContext(),"Displaying hiscores",Toast.LENGTH_LONG).show();
                final Intent goToHighScore = new Intent(this,HighScoreActivity.class);
                startActivity(goToHighScore);
                break;

            default:
                break;
        }

    }
}
