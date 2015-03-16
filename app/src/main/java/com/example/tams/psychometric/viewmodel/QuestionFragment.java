package com.example.tams.psychometric.viewmodel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tams.psychometric.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class QuestionFragment extends Fragment {

    TextView tv_scenario_text;
    TextView tv_situation_text;
    ArrayList<TextView> tv_answers = new ArrayList<TextView>();


    public QuestionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        tv_scenario_text = (TextView)rootView.findViewById(R.id.tv_scenatio_text);
        tv_situation_text = (TextView)rootView.findViewById(R.id.tv_situation_text);
        tv_answers.add((TextView)rootView.findViewById(R.id.tv_answer1_text));
        tv_answers.add((TextView)rootView.findViewById(R.id.tv_answer2_text));
        tv_answers.add((TextView)rootView.findViewById(R.id.tv_answer3_text));
        tv_answers.add((TextView)rootView.findViewById(R.id.tv_answer4_text));
        initQuestion();
        return rootView;
    }


    private void initQuestion()
    {
        tv_scenario_text.setText(R.string.scenario_text);
        tv_situation_text.setText(R.string.situation_text);
        //for ()
    }
}
