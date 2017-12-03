package cpp.cs245final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HighScoreActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        final Intent goToMainMenu = new Intent(this,MenuActivity.class);
        final Button mainMenuButton = findViewById(R.id.hiScoreBackButton);

        mainMenuButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getBaseContext(),"Returning to main menu.", Toast.LENGTH_LONG).show();
                    startActivity(goToMainMenu);
            }
            });
        }


}


