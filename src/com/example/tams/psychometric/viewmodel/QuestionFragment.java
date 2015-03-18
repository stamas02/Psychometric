package com.example.tams.psychometric.viewmodel;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tams.psychometric.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class QuestionFragment extends Fragment {

    TextView tv_scenario_text;
    TextView tv_situation_text;
    ArrayList<TextView> tv_answers = new ArrayList<TextView>();
    EditText editText;

    public QuestionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);



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
        acceptable_answers.add("A");
        acceptable_answers.add("B");
        acceptable_answers.add("C");
        acceptable_answers.add("D");

        if (!acceptable_answers.contains(incoming_answer))
            Toast.makeText(getActivity(), "Please write a correct latter", Toast.LENGTH_SHORT).show();
        else
            switchToComplete();
    }

    public void switchToComplete()
    {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
// only will trigger it if no physical keyboard is open
        inputMethodManager.hideSoftInputFromInputMethod(editText.getWindowToken(), 0);

        FragmentManager fragmentManager = getFragmentManager();
        CompleteFragment activeFragment = new CompleteFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.container, activeFragment,"Complete")
                .addToBackStack("Groups")
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