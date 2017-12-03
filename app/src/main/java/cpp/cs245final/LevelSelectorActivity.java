package cpp.cs245final;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LevelSelectorActivity extends AppCompatActivity {

    private Spinner spinner;
    private String[] tileNumber = new String[] {"4","6","8","10","12","14","16","18","20"};
    private List<String> tilesList;
    private Button startGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selector);
        spinner = findViewById(R.id.spinner);
        tilesList = new ArrayList<>(Arrays.asList(tileNumber));
        final Intent goToMainActivity = new Intent(this,MainActivity.class);
        ArrayAdapter<String> adp= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tilesList);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adp);

        startGameButton = findViewById(R.id.LevelSelectorStartGameButton);
        startGameButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String text = spinner.getSelectedItem().toString();
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("myKey",text);
                startActivity(i);

            }
        });

    }


}