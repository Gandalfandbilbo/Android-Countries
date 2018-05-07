package com.example.shaishavvalera.countries;

import io.realm.RealmObject;

/**
 * Created by Shaishav Valera on 07-Nov-17.
 */

public class QuizModel extends RealmObject {

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public int getScore() {
        return score;
    }

    private String date;
    private String type;
    private int score;

    public QuizModel()
    {

    }
    public QuizModel(String type,String date,int score)
    {
        this.date = date;
        this.score = score;
        this.type = type;
    }
}
