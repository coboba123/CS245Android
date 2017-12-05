package cpp.cs245final;

/**
 * Created by Cody on 12/4/2017.
 */
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class HighScoreEngine extends AppCompatActivity implements View.OnClickListener {
    private int score;
    private final ArrayList<HighScoreEngine> highScoreRecords = new ArrayList<>();
    public EditText editText;
    public Intent intent;
    public String initials;
    public String difficulty;
    public TextView scoreNum;

    public String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Output";
    public HighScoreEngine()
    {
        initials = "";
        score = 0;
    }
    public HighScoreEngine(String initials, int score) {
        this.initials = initials;
        this.score = score;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score_input);
        final Button finishedButton = findViewById(R.id.finishedButton);
        finishedButton.setOnClickListener(this);
        editText = findViewById(R.id.nameInput);
        scoreNum = findViewById(R.id.scoreNumber);
        loadScoreFromFile(difficulty);

        intent = getIntent();
        score = intent.getIntExtra("highScore", 0);
        difficulty = intent.getStringExtra("difficulty");
        scoreNum.setText(((Integer) score).toString());
    }

    @Override
    public void onClick(View view) {
        //Need to write into file
        initials = editText.getText().toString();

        try
        {
            writing(initials);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        Toast.makeText(getBaseContext(), "Score Inputted!", Toast.LENGTH_LONG).show();
        final Intent startGame = new Intent(this, MenuActivity.class);
        startActivity(startGame);

    }


    public void loadScoreFromFile(String diff) {
        try {
            String path = "Scores" + diff + ".txt";
            Scanner scanner = new Scanner(new FileReader(path));
            String line;
            HighScoreEngine record;

            if (scanner.nextLine() != "") {
                while (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                    String[] results = line.split(" ");
                    String initals = results[0];
                    int score = Integer.parseInt(results[1]);
                    record = new HighScoreEngine(initials, score);
                    highScoreRecords.add(record);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*Method: SorterAndRewrite
    purpose: this method sorts the scores based on the value and places them into a new text file*/
    public void SorterAndRewrite() throws IOException {
        FileInputStream reader = new FileInputStream(path + "/Scores" + difficulty + ".txt");
        FileOutputStream writer = new FileOutputStream(path + "/Scores" + difficulty + ".txt");
        Collections.sort(highScoreRecords, new marksCompare());

        //Rewrites the new sorted scores in order based on Greatest to lowest
        for (int i = 0; i < highScoreRecords.size(); i++) {
            writer.write(Integer.parseInt(highScoreRecords.get(i).initials + " " + highScoreRecords.get(i).score));
        }
        reader.close();
        writer.close();
    }

    /*Method: writing
     purpose: this method is used to input initials and score into the textfile*/
    public void writing(String inputData) throws IOException {

        File file = new File(path + "/Scores" + difficulty + ".txt");
        if(!file.exists()){
            file.mkdir();
        }

        // File saved as 'Initals, Score'. IE " ABC 100"
        FileOutputStream fileInput = new FileOutputStream(file, true);
        fileInput.write(Integer.parseInt(inputData + " " + getScore()));
        fileInput.close();

    }

    /*Method: isHighScore
    purpose: this method is used to check if the score obtained is greated than one already on record in the textfile*/
    public boolean isHighScore(int hiScore) {
        for (int i = 0; i < getNumberOfRecords(); i++) {
            if (hiScore > highScoreRecords.get(i).score) {
                return true;
            }
        }
        // hiscore was lower
        return false;
    }

    class nameCompare implements Comparator<HighScoreEngine> {
        @Override
        public int compare(HighScoreEngine s1, HighScoreEngine s2) {
            return s1.initials.compareTo(s2.initials);
        }
    }

    class marksCompare implements Comparator<HighScoreEngine> {
        @Override
        public int compare(HighScoreEngine s1, HighScoreEngine s2) {
            return s2.score - s1.score;
        }
    }
    @Override
    public String toString(){
        return  initials + " " + score ;
    }

    public HighScoreEngine getRecordNumber(int i)
    {
        try {
            return highScoreRecords.get(i);
        }
        catch(Exception e)
        {
            HighScoreEngine temp = new HighScoreEngine();
            return temp;
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public int getNumberOfRecords() {
        return highScoreRecords.size();
    }
}
