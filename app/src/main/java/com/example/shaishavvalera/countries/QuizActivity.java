package com.example.shaishavvalera.countries;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import io.realm.Realm;

/**
 * Created by Shaishav Valera on 07-Nov-17.
 */

public class QuizActivity extends AppCompatActivity
{
    Button choice1,choice2,choice3,choice4,easy,medium,hard,again;
    TextView score,question,difficulty;
    private String quiztype;
    private String todaydate;
    private String mAnswer;
    private int mScore = 0;
    private ArrayList<QuizDataModel> questions = new ArrayList<>();
    Realm realm;
    public int counter = 0;
    ContentLoadingProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);
        easy = (Button)findViewById(R.id.easy);
        medium = (Button) findViewById(R.id.medium);
        hard = (Button) findViewById(R.id.hard);
        score = (TextView)findViewById(R.id.score);
        question = (TextView) findViewById(R.id.question);
        difficulty = (TextView) findViewById(R.id.choose);
        choice1 = (Button) findViewById(R.id.choice1);
        choice2 = (Button) findViewById(R.id.choice2);
        choice3 = (Button) findViewById(R.id.choice3);
        choice4 = (Button) findViewById(R.id.choice4);
        again = (Button) findViewById(R.id.again);
        progressBar = (ContentLoadingProgressBar) findViewById(R.id.progress);
        score.setText("Score: 0");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        todaydate = sdf.format(new Date());
        Log.e("date:",todaydate);
    }
    private class callData extends FetchQuizData
    {
        @Override
        protected void onPreExecute()
        {
            progressBar.show();
        }
        @Override
        protected void onPostExecute(ArrayList<QuizDataModel> result)
        {
            questions = result;
            progressBar.hide();
            updateQuestion(counter);
        }
    }
    public void enterData(final String type, final int quizscore, final String date1)
    {
        Realm.init(this);
        realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction()
        {
            @Override
            public void execute(Realm realm)
            {
                QuizModel newentry = new QuizModel(type,date1,quizscore);
                realm.copyToRealm(newentry);
            }
        });
    }
    public void easy(View v)
    {
        quiztype = "Easy";
        new callData().execute(quiztype);
        easy.setVisibility(v.INVISIBLE);
        medium.setVisibility(v.INVISIBLE);
        hard.setVisibility(v.INVISIBLE);
        difficulty.setVisibility(v.INVISIBLE);
        score.setVisibility(v.VISIBLE);
        question.setVisibility(v.VISIBLE);
        choice1.setVisibility(v.VISIBLE);
        choice2.setVisibility(v.VISIBLE);
        choice3.setVisibility(v.VISIBLE);
        choice4.setVisibility(v.VISIBLE);
    }
    public void medium(View v)
    {
        quiztype = "Medium";
        new callData().execute(quiztype);
        easy.setVisibility(v.INVISIBLE);
        medium.setVisibility(v.INVISIBLE);
        hard.setVisibility(v.INVISIBLE);
        difficulty.setVisibility(v.INVISIBLE);
        question.setVisibility(v.VISIBLE);
        choice1.setVisibility(v.VISIBLE);
        choice2.setVisibility(v.VISIBLE);
        choice3.setVisibility(v.VISIBLE);
        choice4.setVisibility(v.VISIBLE);
        score.setVisibility(v.VISIBLE);
    }
    public void hard(View v)
    {
        quiztype = "Hard";
        new callData().execute(quiztype);
        easy.setVisibility(v.INVISIBLE);
        medium.setVisibility(v.INVISIBLE);
        hard.setVisibility(v.INVISIBLE);
        difficulty.setVisibility(v.INVISIBLE);
        question.setVisibility(v.VISIBLE);
        choice1.setVisibility(v.VISIBLE);
        choice2.setVisibility(v.VISIBLE);
        choice3.setVisibility(v.VISIBLE);
        choice4.setVisibility(v.VISIBLE);
        score.setVisibility(v.VISIBLE);
    }
    public void choice1(View v)
    {
        counter++;
        if (counter == 10)
        {
            if(choice1.getText()==mAnswer)
            {
                mScore++;
            }
            question.setVisibility(v.INVISIBLE);
            choice1.setVisibility(v.INVISIBLE);
            choice2.setVisibility(v.INVISIBLE);
            choice3.setVisibility(v.INVISIBLE);
            choice4.setVisibility(v.INVISIBLE);
            score.setVisibility(v.INVISIBLE);
            difficulty.setText("You Finished the Quiz\nYour Score is: "+mScore);
            difficulty.setVisibility(v.VISIBLE);
            again.setVisibility(v.VISIBLE);
            enterData(quiztype,mScore,todaydate);
        }
        else
        {

            if (choice1.getText() == mAnswer)
            {
                mScore++;
                score.setText("Score: " + mScore);
                updateQuestion(counter);
            }
            else
            {
                updateQuestion(counter);
            }
        }
    }
    public void choice2(View v)
    {
        counter++;
        if (counter == 10)
        {
            if(choice2.getText()==mAnswer)
            {
                mScore++;
            }
            question.setVisibility(v.INVISIBLE);
            choice1.setVisibility(v.INVISIBLE);
            choice2.setVisibility(v.INVISIBLE);
            choice3.setVisibility(v.INVISIBLE);
            choice4.setVisibility(v.INVISIBLE);
            score.setVisibility(v.INVISIBLE);
            difficulty.setText("You Finished the Quiz\nYour Score is: "+mScore);
            difficulty.setVisibility(v.VISIBLE);
            again.setVisibility(v.VISIBLE);
            enterData(quiztype,mScore,todaydate);
        }
        else
        {

            if (choice2.getText() == mAnswer)
            {
                mScore++;
                score.setText("Score: " + mScore);
                updateQuestion(counter);
            }
            else
            {
                updateQuestion(counter);
            }
        }
    }
    public void choice3(View v)
    {
        counter++;
        if (counter == 10)
        {
            if(choice3.getText()==mAnswer)
            {
                mScore++;
            }
            question.setVisibility(v.INVISIBLE);
            choice1.setVisibility(v.INVISIBLE);
            choice2.setVisibility(v.INVISIBLE);
            choice3.setVisibility(v.INVISIBLE);
            choice4.setVisibility(v.INVISIBLE);
            score.setVisibility(v.INVISIBLE);
            difficulty.setText("You Finished the Quiz\nYour Score is: "+mScore);
            difficulty.setVisibility(v.VISIBLE);
            again.setVisibility(v.VISIBLE);
            enterData(quiztype,mScore,todaydate);
        }
        else
        {

            if (choice3.getText() == mAnswer)
            {
                mScore++;
                score.setText("Score: " + mScore);
                updateQuestion(counter);
            }
            else
            {
                updateQuestion(counter);
            }
        }
    }
    public void choice4(View v)
    {
        counter++;
        if (counter == 10)
        {
            if(choice4.getText()==mAnswer)
            {
                mScore++;
            }
            question.setVisibility(v.INVISIBLE);
            choice1.setVisibility(v.INVISIBLE);
            choice2.setVisibility(v.INVISIBLE);
            choice3.setVisibility(v.INVISIBLE);
            choice4.setVisibility(v.INVISIBLE);
            score.setVisibility(v.INVISIBLE);
            difficulty.setText("You Finished the Quiz\nYour Score is: "+mScore);
            difficulty.setVisibility(v.VISIBLE);
            again.setVisibility(v.VISIBLE);
            enterData(quiztype,mScore,todaydate);
        }
        else
        {

            if (choice4.getText() == mAnswer)
            {
                mScore++;
                score.setText("Score: " + mScore);
                updateQuestion(counter);
            }
            else
            {
                updateQuestion(counter);
            }
        }
    }
    private void updateQuestion(int num)
    {
        int [] num1 = new int[4];
        Random rand = new Random(0,3);
        for(int i = 0;i<4;i++)
        {
            num1[i]=rand.nextInt();
        }
        question.setText(questions.get(num).getQuestion());
        choice1.setText(questions.get(num).getChoices(num1[0]));
        choice2.setText(questions.get(num).getChoices(num1[1]));
        choice3.setText(questions.get(num).getChoices(num1[2]));
        choice4.setText(questions.get(num).getChoices(num1[3]));
        mAnswer = questions.get(num).getAnswer();
    }
    public void again(View v)
    {
        again.setVisibility(v.INVISIBLE);
        easy.setVisibility(v.VISIBLE);
        medium.setVisibility(v.VISIBLE);
        hard.setVisibility(v.VISIBLE);
        difficulty.setText("Choose Quiz Difficulty");
        score.setText("Score: 0");
        question.setText("");
        choice1.setText("");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
        counter=0;
        mScore=0;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.quiz_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.quizhistory:
                Intent i = new Intent(QuizActivity.this,QuizHistory.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
