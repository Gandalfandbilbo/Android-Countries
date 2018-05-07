package com.example.shaishavvalera.countries;

import java.util.Collections;
import java.util.Stack;

/**
 * Created by Shaishav Valera on 08-Nov-17.
 */

public class Random
{
    private int start;
    private int end;
    private Stack<Integer> numbers = new Stack<>();
    public Random(int start, int end)
    {
        this.start = start;
        this.end = end;
    }
    private void loadNumbers()
    {
        for (int i=start;i<=end;i++)
        {
            numbers.push(i);
        }
        Collections.shuffle(numbers);
    }
    public int nextInt()
    {
        if (numbers.empty()) loadNumbers();
        return numbers.pop();
    }
}
