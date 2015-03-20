package com.example.psychometric.viewmodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.psychometric.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class CompleteFragment extends Fragment {


    ArrayList<String> answers = new  ArrayList<String>();
    ArrayList<TextView> tv_answer_results = new ArrayList<TextView>();


    public CompleteFragment( ArrayList<String> answers) {
        this.answers = answers;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_complete, container, false);
        tv_answer_results.add((TextView)rootView.findViewById(R.id.tv_answer1_result));
        tv_answer_results.add((TextView)rootView.findViewById(R.id.tv_answer2_result));
        publishResult();
        return rootView;
    }


    public void publishResult()
    {
        List<String> answers_results = Arrays.asList(getResources().getStringArray(R.array.answer_result_texts));
        for (int i = 0; i < tv_answer_results.size(); i++)
        {
            tv_answer_results.get(i).setText(answers_results.get(getAnswerNumber(answers.get(i))));
        }
    }


    public int getAnswerNumber(String answer)
    {
        if (answer.equals("a"))
            return 0;
        if (answer.equals("b"))
            return 1;
        if (answer.equals("c"))
            return 2;
        if (answer.equals("d"))
            return 3;
        return -1;
    }




}
