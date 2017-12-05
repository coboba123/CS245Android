package cpp.cs245final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class HighScoreActivity extends AppCompatActivity{

    private final HighScoreEngine hiScoreRecords = new HighScoreEngine("0",0);

    //all text boxes for the score view
    public TextView score41, score42, score43, score61, score62, score63, score81, score82, score83,
            score101, score102, score103, score121, score122, score123, score141, score142, score143,
            score161, score162, score163, score181, score182, score183, score201, score202, score203;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        final Intent goToMainMenu = new Intent(this,MenuActivity.class);
        final Button mainMenuButton = findViewById(R.id.hiScoreBackButton);

        score41 = findViewById(R.id.score41);
        score42 = findViewById(R.id.score42);
        score43 = findViewById(R.id.score43);

        score61 = findViewById(R.id.score61);
        score62 = findViewById(R.id.score62);
        score62 = findViewById(R.id.score63);

        score81 = findViewById(R.id.score81);
        score82 = findViewById(R.id.score82);
        score82 = findViewById(R.id.score83);

        score101 = findViewById(R.id.score101);
        score102 = findViewById(R.id.score102);
        score102 = findViewById(R.id.score103);

        score121 = findViewById(R.id.score121);
        score122 = findViewById(R.id.score122);
        score122 = findViewById(R.id.score123);

        score141 = findViewById(R.id.score141);
        score142 = findViewById(R.id.score142);
        score142 = findViewById(R.id.score143);

        score161 = findViewById(R.id.score161);
        score162 = findViewById(R.id.score162);
        score162 = findViewById(R.id.score163);

        score181 = findViewById(R.id.score181);
        score182 = findViewById(R.id.score182);
        score182 = findViewById(R.id.score183);

        score201 = findViewById(R.id.score201);
        score202 = findViewById(R.id.score202);
        score202 = findViewById(R.id.score203);

        try
        {
            initializeScores();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }



        mainMenuButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getBaseContext(),"Returning to main menu.", Toast.LENGTH_LONG).show();
                    startActivity(goToMainMenu);
            }
            });
        }
        private void initializeScores() throws IOException
        {
            try {
                hiScoreRecords.loadScoreFromFile("4");
                hiScoreRecords.SorterAndRewrite();
                score41.setText(hiScoreRecords.getRecordNumber(0).getInitials() + " " + hiScoreRecords.getRecordNumber(0).getScore());
                score42.setText(hiScoreRecords.getRecordNumber(1).getInitials() + " " + hiScoreRecords.getRecordNumber(1).getScore());
                score43.setText(hiScoreRecords.getRecordNumber(2).getInitials() + " " + hiScoreRecords.getRecordNumber(2).getScore());
            }
            catch(Exception e)
            {
                    e.printStackTrace();
            }


            try {
                hiScoreRecords.loadScoreFromFile("6");
                hiScoreRecords.SorterAndRewrite();
                score61.setText(hiScoreRecords.getRecordNumber(0).getInitials() + "...." + hiScoreRecords.getRecordNumber(0).getScore());
                score62.setText(hiScoreRecords.getRecordNumber(1).getInitials() + "...." + hiScoreRecords.getRecordNumber(1).getScore());
                score63.setText(hiScoreRecords.getRecordNumber(2).getInitials() + "...." + hiScoreRecords.getRecordNumber(2).getScore());
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }


            try {
                hiScoreRecords.loadScoreFromFile("8");
                hiScoreRecords.SorterAndRewrite();
                score81.setText(hiScoreRecords.getRecordNumber(0).getInitials() + "...." + hiScoreRecords.getRecordNumber(0).getScore());
                score82.setText(hiScoreRecords.getRecordNumber(1).getInitials() + "...." + hiScoreRecords.getRecordNumber(1).getScore());
                score83.setText(hiScoreRecords.getRecordNumber(2).getInitials() + "...." + hiScoreRecords.getRecordNumber(2).getScore());
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }


            try {
                hiScoreRecords.loadScoreFromFile("10");
                hiScoreRecords.SorterAndRewrite();
                score101.setText(hiScoreRecords.getRecordNumber(0).getInitials() + "...." + hiScoreRecords.getRecordNumber(0).getScore());
                score102.setText(hiScoreRecords.getRecordNumber(1).getInitials() + "...." + hiScoreRecords.getRecordNumber(1).getScore());
                score103.setText(hiScoreRecords.getRecordNumber(2).getInitials() + "...." + hiScoreRecords.getRecordNumber(2).getScore());
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }


            try {
                hiScoreRecords.loadScoreFromFile("12");
                hiScoreRecords.SorterAndRewrite();
                score121.setText(hiScoreRecords.getRecordNumber(0).getInitials() + "...." + hiScoreRecords.getRecordNumber(0).getScore());
                score122.setText(hiScoreRecords.getRecordNumber(1).getInitials() + "...." + hiScoreRecords.getRecordNumber(1).getScore());
                score123.setText(hiScoreRecords.getRecordNumber(2).getInitials() + "...." + hiScoreRecords.getRecordNumber(2).getScore());
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }


            try {
                hiScoreRecords.loadScoreFromFile("14");
                hiScoreRecords.SorterAndRewrite();
                score141.setText(hiScoreRecords.getRecordNumber(0).getInitials() + "...." + hiScoreRecords.getRecordNumber(0).getScore());
                score142.setText(hiScoreRecords.getRecordNumber(1).getInitials() + "...." + hiScoreRecords.getRecordNumber(1).getScore());
                score143.setText(hiScoreRecords.getRecordNumber(2).getInitials() + "...." + hiScoreRecords.getRecordNumber(2).getScore());
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }


            try {
                hiScoreRecords.loadScoreFromFile("16");
                hiScoreRecords.SorterAndRewrite();
                score161.setText(hiScoreRecords.getRecordNumber(0).getInitials() + "...." + hiScoreRecords.getRecordNumber(0).getScore());
                score162.setText(hiScoreRecords.getRecordNumber(1).getInitials() + "...." + hiScoreRecords.getRecordNumber(1).getScore());
                score163.setText(hiScoreRecords.getRecordNumber(2).getInitials() + "...." + hiScoreRecords.getRecordNumber(2).getScore());
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }


            try {
                hiScoreRecords.loadScoreFromFile("18");
                hiScoreRecords.SorterAndRewrite();
                score181.setText(hiScoreRecords.getRecordNumber(0).getInitials() + "...." + hiScoreRecords.getRecordNumber(0).getScore());
                score182.setText(hiScoreRecords.getRecordNumber(1).getInitials() + "...." + hiScoreRecords.getRecordNumber(1).getScore());
                score183.setText(hiScoreRecords.getRecordNumber(2).getInitials() + "...." + hiScoreRecords.getRecordNumber(2).getScore());
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }


            try {
                hiScoreRecords.loadScoreFromFile("20");
                hiScoreRecords.SorterAndRewrite();
                score201.setText(hiScoreRecords.getRecordNumber(0).getInitials() + "...." + hiScoreRecords.getRecordNumber(0).getScore());
                score202.setText(hiScoreRecords.getRecordNumber(1).getInitials() + "...." + hiScoreRecords.getRecordNumber(1).getScore());
                score203.setText(hiScoreRecords.getRecordNumber(2).getInitials() + "...." + hiScoreRecords.getRecordNumber(2).getScore());
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }




}


