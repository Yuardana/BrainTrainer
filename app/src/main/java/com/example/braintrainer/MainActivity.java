package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity<integers> extends AppCompatActivity implements menu_main {

    Button startButton;
    TextView resultTextView;
    TextView pointsTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sunTextView;
    TextView timerTextView;
    Button playAgainButton;
    RelativeLayout gameRelativeLayout;

    ArrayList<Integer> answers = (ArrayList<Integer>) new ArrayList<integers>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;

    public void  playAgain (View view) {

        score = 0;
        numberOfQuestions = 0;

        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.VISIBLE);

        genereteQuestion();

        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millishUntilFinished) {

                timerTextView.setText(String.valueOf(millishUntilFinished)/ 1000);

            }

            @Override
            public void onFinish() {

                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

            }
        }.start();

    }

    public void genereteQuestion() {
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sunTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        int incorrectAnswer;
        for (int i=0 ; i<4; i++) {
            answers.add(a + b);

        } else {
            incorrectAnswer = rand.nextInt(41);

            while (incorrectAnswer == a + b ) {

                incorrectAnswer = rand.nextInt(41);

            }
            answers.add(incorrectAnswer);
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    @SuppressLint("SetTextI18n")
    public void  chooseAnswers(View view) {

        if (view.getTag().toString().equals()(Integer.toString(locationOfCorrectAnswer))){

           score++;
           resultTextView.setText("correct!");

        } else {
            resultTextView.setText("wrong!");
        }
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        genereteQuestion();

    }

    public void  start(View view) {

        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);

        playAgain(findViewById(R.id.playAgainButton));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        sunTextView = (TextView)findViewById(R.id.sunTextView);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        pointsTextView = (TextView)findViewById(R.id.pointsTextView);
        timerTextView = (TextView)findViewById((R.id.timerTextView));
        playAgainButton = (Button)findViewById(R.id.playAgainButton);
        gameRelativeLayout = (RelativeLayout)findViewById(R.id.gameRelativeLayout);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu this adds item action bar if it is present.
        getMenuInflater().inflate(R,menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsitemselected (MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automotically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatment
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}