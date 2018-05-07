package com.example.shaishavvalera.countries;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Shaishav Valera on 08-Nov-17.
 */

public class QuizHistory extends AppCompatActivity
{
    private RealmResults<QuizModel> qm;
    private ArrayList<QuizModel> quizModels;
    ListView lv;
    Realm realm;
    public ArrayAdapter<String> list;
    private List<String> liststring = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_history);
        lv = (ListView) findViewById(R.id.quizresults);
        Realm.init(this);
        realm = Realm.getDefaultInstance();
        quizModels = new ArrayList(realm.where(QuizModel.class).findAll());
        if(quizModels.isEmpty())
        {
            liststring.add("No Quiz Taken yet");
            list = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,liststring);
            lv.setAdapter(list);
        }
        liststring = addtoString(quizModels);
        list = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,liststring);
        lv.setAdapter(list);
    }
    public List<String> addtoString(ArrayList<QuizModel> qm)
    {
        List<String> tempstring = new ArrayList<>();
        for(int i=0;i<qm.size();i++)
        {
            int score = qm.get(i).getScore();
            String type = qm.get(i).getType();
            String date = qm.get(i).getDate();
            tempstring.add("Your Score: "+score+" Quiz Difficulty: "+type+" Date: "+date);
        }
        return tempstring;
    }
}
