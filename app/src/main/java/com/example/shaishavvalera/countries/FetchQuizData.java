package com.example.shaishavvalera.countries;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Shaishav Valera on 08-Nov-17.
 */

public class FetchQuizData extends AsyncTask<String,Void,ArrayList<QuizDataModel>>
{
    private ArrayList<QuizDataModel> question = new ArrayList<>();
    protected ArrayList<QuizDataModel> doInBackground(String...urls)
    {
        String difficulty = urls[0].toLowerCase();
        String url = "https://opentdb.com/api.php?amount=10&category=22&difficulty="+difficulty+"&type=multiple";
        try
        {
            URL link = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) link.openConnection();
            try
            {
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine())!=null)
                {
                    sb.append(line).append("\n");
                }
                br.close();
                JSONObject all = new JSONObject(sb.toString());
                JSONArray allresults = all.getJSONArray("results");
                for (int i = 0;i<allresults.length();i++)
                {
                    JSONObject result = allresults.getJSONObject(i);
                    String question = result.getString("question");
                    String CorrectAnswers = result.getString("correct_answer");
                    JSONArray incorrect = result.getJSONArray("incorrect_answers");
                    String[] mChoices = new String[4];
                    for(int j = 0;j<3;j++)
                    {
                        mChoices[j] = incorrect.getString(j);
                    }
                    mChoices[3] = result.getString("correct_answer");
                    QuizDataModel qm = new QuizDataModel(question,CorrectAnswers,mChoices);
                    this.question.add(qm);
                }
                return question;
            }
            finally
            {
                urlConnection.disconnect();
            }
        }
        catch (Exception e)
        {
            Log.e("Error:",""+e);
            return null;
        }
    }
}
