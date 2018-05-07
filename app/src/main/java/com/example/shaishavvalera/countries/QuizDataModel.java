package com.example.shaishavvalera.countries;

/**
 * Created by Shaishav Valera on 08-Nov-17.
 */

public class QuizDataModel
{
    public QuizDataModel(String question,String answer,String[] choices)
    {
        this.question = question;
        this.answer = answer;
        this.choices = choices;
    }
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getChoices(int num)
    {
        return choices[num];
    }

    public void setChoices(int i,String[] choices) {
        this.choices[i] = choices[i];
    }

    private String question;
    private String answer;
    private String[] choices = new String[4];
}
