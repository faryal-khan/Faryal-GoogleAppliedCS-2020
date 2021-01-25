package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int userRollScore, computerRollScore;
    private int userTotal, computerTotal;
    private static final int one = 1;
    private static final int two = 2;
    private static final int three = 3;
    private static final int four = 4;
    private static final int five = 5;
    private static final int six= 6;

    private boolean gotOne = false;
   // private boolean userTurn;
 //   private boolean computerTurn;

    ImageView imageView;
    TextView textView1;
    Button rollButton;
    Button holdButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.imageView2);
        textView1 = (TextView)findViewById(R.id.textView);
        rollButton = (Button)findViewById(R.id.button);
        holdButton = (Button)findViewById(R.id.button2);

    }

    public void diceRoller(View view){


        int randNumber = (int)((Math.random()*6) + 1);
        switch(randNumber) {
            case one:
            //Drawable diceImage = getResources().getDrawable(R.drawable.dice1);
             imageView.setImageResource(R.drawable.dice1);
             userRollScore = 0;
             String score = "Your score: " + userTotal + " Computer score: " + computerTotal ;
             textView1.setText(score);
             computerTurn();
             break;

            case two:
                userRollScore+= 2;
                imageView.setImageResource(R.drawable.dice2);
                String score2 = "Your score: " + userTotal + " Computer score: " + computerTotal + " Your turn score: " + userRollScore;
                textView1.setText(score2);
                break;

            case three:
                userRollScore += 3;
                imageView.setImageResource(R.drawable.dice3);
                String score3 = "Your score: " + userTotal + " Computer score: " + computerTotal + " Your turn score: " + userRollScore;
                textView1.setText(score3);
                break;


            case four:
                userRollScore += 4;
                imageView.setImageResource(R.drawable.dice4);
                String score4 = "Your score: " + userTotal + " Computer score: " + computerTotal + " Your turn score: " + userRollScore;
                textView1.setText(score4);
                break;

            case five:
                userRollScore += 5;
                imageView.setImageResource(R.drawable.dice5);
                String score5 = "Your score: " + userTotal + " Computer score: " + computerTotal + " Your turn score: " + userRollScore;
                textView1.setText(score5);
                break;

            case six:
                userRollScore += 6;
                imageView.setImageResource(R.drawable.dice6);
                String score6 = "Your score: " + userTotal + " Computer score: " + computerTotal + " Your turn score: " + userRollScore;
                textView1.setText(score6);
                break;


        }

    }

    public void computerRoller(){
        int randNumber = (int)((Math.random()*6) + 1);
        switch(randNumber) {
            case one:
                imageView.setImageResource(R.drawable.dice1);
                computerTotal += computerRollScore;
                computerRollScore = 0;
                String score = "Your score: " + userTotal + " Computer score: " + computerTotal;
                textView1.setText(score);
                gotOne = true;

                break;

            case two:
                gotOne = false;
                computerRollScore+= 2;
                imageView.setImageResource(R.drawable.dice2);
                String score2 = "Your score: " + userTotal + " Computer score: " + computerTotal + " Computer turn score: " + userRollScore;
                textView1.setText(score2);
                break;

            case three:
                gotOne = false;
                computerRollScore += 3;
                imageView.setImageResource(R.drawable.dice3);
                String score3 = "Your score: " + userTotal + " Computer score: " + computerTotal + " Computer turn score: " + userRollScore;
                textView1.setText(score3);
                break;


            case four:
                gotOne = false;
                computerRollScore += 4;
                imageView.setImageResource(R.drawable.dice4);
                String score4 = "Your score: " + userTotal + " Computer score: " + computerTotal + " Computer turn score: " + userRollScore;
                textView1.setText(score4);
                break;

            case five:
                gotOne = false;
                computerRollScore += 5;
                imageView.setImageResource(R.drawable.dice5);
                String score5 = "Your score: " + userTotal + " Computer score: " + computerTotal + " Computer turn score: " + userRollScore;
                textView1.setText(score5);
                break;

            case six:
                gotOne = false;
                computerRollScore += 6;
                imageView.setImageResource(R.drawable.dice6);
                String score6 = "Your score: " + userTotal + " Computer score: " + computerTotal + " Computer turn score: " + userRollScore;
                textView1.setText(score6);
                break;


        }
    }



    public void resetGame(View view){
        userRollScore = 0;
        computerRollScore = 0;
        userTotal = 0;
        computerTotal = 0;

        textView1.setText("Your score: 0 Computer Score: 0");


    }

    public void holdTurn(View view){
        userTotal += userRollScore;
        userRollScore = 0;
        String holdGame = "Your score: " + userTotal + " Computer score: " + computerTotal;
        textView1.setText(holdGame);
        computerTurn();
    }

    public void computerTurn(){
        rollButton.setEnabled(false);
        holdButton.setEnabled(false);

        while(!gotOne){
            computerRoller();
        }

        if (gotOne == true){
            rollButton.setEnabled(true);
            holdButton.setEnabled(true);
        }

    }
}
