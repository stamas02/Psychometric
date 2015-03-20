package com.example.psychometric.viewmodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.psychometric.R;

import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.KeyEvent;

public class QuestionFragment extends Fragment {

    TextView tv_scenario_text;
    TextView tv_situation_text;
    ArrayList<TextView> tv_answers = new ArrayList<TextView>();
    EditText editText;
    
    public View mRootView;

    ArrayList<String> answers = new ArrayList<String>();

    public QuestionFragment() {
    }

    @Override
    public View getView()
    {
    	return mRootView;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mRootView = rootView;


        tv_scenario_text = (TextView)rootView.findViewById(R.id.tv_scenatio_text);
        tv_situation_text = (TextView)rootView.findViewById(R.id.tv_situation_text);
        tv_answers.add((TextView) rootView.findViewById(R.id.tv_answer1_text));
        tv_answers.add((TextView)rootView.findViewById(R.id.tv_answer2_text));
        tv_answers.add((TextView)rootView.findViewById(R.id.tv_answer3_text));
        tv_answers.add((TextView)rootView.findViewById(R.id.tv_answer4_text));
        editText = (EditText) rootView.findViewById(R.id.et_input_answer);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    sendAnswer(v.getText().toString());
                    v.setText("");
                    handled = true;
                }
                return handled;
            }
        });
        initQuestion();
        return rootView;
    }


    public void sendAnswer(String incoming_answer)
    {
        ArrayList<String> acceptable_answers = new ArrayList<String>();
        acceptable_answers.add("a");
        acceptable_answers.add("b");
        acceptable_answers.add("c");
        acceptable_answers.add("d");

        if (!acceptable_answers.contains(incoming_answer.toLowerCase()))
            Toast.makeText(getActivity(), "Please write a correct letter", Toast.LENGTH_SHORT).show();
        else {
            if (!answers.contains(incoming_answer.toLowerCase())) {
                answers.add(incoming_answer.toLowerCase());
                markChosenAnswers(incoming_answer.toLowerCase());
            }
            else
                Toast.makeText(getActivity(), "You already chose this letter", Toast.LENGTH_SHORT).show();
        }
        if (answers.size() == 2)
            switchToComplete();
    }

    private void markChosenAnswers(String choosen)
    {
        if (choosen.equals("a"))
            tv_answers.get(0).setTextColor(Color.BLUE);
        if (choosen.equals("b"))
            tv_answers.get(1).setTextColor(Color.BLUE);
        if (choosen.equals("c"))
            tv_answers.get(2).setTextColor(Color.BLUE);
        if (choosen.equals("d"))
            tv_answers.get(3).setTextColor(Color.BLUE);
    }

    public void switchToComplete()
    {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
// only will trigger it if no physical keyboard is open
        inputMethodManager.hideSoftInputFromInputMethod(editText.getWindowToken(), 0);

        FragmentManager fragmentManager = getFragmentManager();
        CompleteFragment activeFragment = new CompleteFragment(answers);
        fragmentManager.beginTransaction()
                .replace(R.id.container, activeFragment,"CopmleteFragment")
                .addToBackStack("CopmleteFragment")
                .commit();
    }

    private void initQuestion()
    {
        tv_scenario_text.setText(R.string.scenario_text);
        tv_situation_text.setText(R.string.situation_text);
        List<String> answers = Arrays.asList(getResources().getStringArray(R.array.answer_texts));
        for (int i = 0; i < answers.size(); i++)
        {
            tv_answers.get(i).setText(answers.get(i));
        }
    }
}

