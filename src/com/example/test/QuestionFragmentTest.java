package com.example.test;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.example.psychometric.MainActivity;
import com.example.psychometric.R;
import com.example.psychometric.viewmodel.QuestionFragment;



import android.app.FragmentManager;
import  android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.test.ActivityUnitTestCase;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class QuestionFragmentTest extends ActivityUnitTestCase<MainActivity> {

	public QuestionFragmentTest() {
	       super(MainActivity.class);
	   }

	private ActionBarActivity mActivity; 
	private QuestionFragment mFragment;
	private View fragment_view;
	
    TextView tv_scenario_text;
    TextView tv_situation_text;
    EditText editText;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		startActivity(new Intent(getInstrumentation().getTargetContext(), MainActivity.class), null, null);
		mActivity = getActivity();	
		mActivity.getSupportFragmentManager().executePendingTransactions();
		getInstrumentation().waitForIdleSync();
		mFragment = (QuestionFragment)mActivity.getSupportFragmentManager().findFragmentByTag("QuestionFragment");
		mFragment.onCreateView(mActivity.getLayoutInflater()	, (ViewGroup)mActivity.findViewById(R.id.container), new Bundle());

		
		tv_scenario_text = (TextView)mFragment.getView().findViewById(R.id.tv_scenatio_text);
		tv_situation_text = (TextView)mFragment.getView().findViewById(R.id.tv_situation_text);
		editText = (EditText)mFragment.getView().findViewById(R.id.et_input_answer);
		//mFragment.
		View dsf = mFragment.getView();

		
	}
	


	@Test
	public void testInputFieldExist() {     
		//QuestionFragment wf = (QuestionFragment)startFragment(new QuestionFragment());
		assertNotNull("Input field doesn't exist with the id:'et_input_answer'", editText);
	}

	@Test
	public void testScenarioTextFieldExist() {       
		assertNotNull("Input field doesn't exist with the id:'et_input_answer'", tv_scenario_text);
	}
	
	@Test
	public void testSituationTextFieldExist() {       
		assertNotNull("Input field doesn't exist with the id:'et_input_answer'", editText);
	}
	
	
	@Test
	public void testSituationTextFieldContent() {    
		assertEquals(tv_situation_text.getText(),mActivity.getResources().getString(R.string.situation_text));
		//assertNull("Input field doesn't exist with the id:'et_input_answer'", fragment_view.findViewById(R.id.tv_situation_text));
	}
	
	@Test
	public void testScenarioTextFieldContent() {    
		assertEquals(tv_scenario_text.getText(),mActivity.getResources().getString(R.string.scenario_text));		
	}
	
	@Test
	public void testCorrectAnswer() {    
		editText.setText("a");
		editText.onEditorAction(EditorInfo.IME_ACTION_DONE);
		editText.setText("b");
		editText.onEditorAction(EditorInfo.IME_ACTION_DONE);
		
		mActivity.getSupportFragmentManager().executePendingTransactions();
		getInstrumentation().waitForIdleSync();
		Object copmleteFragment = mActivity.getSupportFragmentManager().findFragmentByTag("CopmleteFragment");
		assertNotNull("Input field doesn't exist with the id:'et_input_answer'", copmleteFragment);		
	}
	
	
	@Test
	public void testWrongAnswer() {    
		editText.setText("ads");
		editText.onEditorAction(EditorInfo.IME_ACTION_DONE);
		editText.setText("bds");
		editText.onEditorAction(EditorInfo.IME_ACTION_DONE);
		
		mActivity.getSupportFragmentManager().executePendingTransactions();
		getInstrumentation().waitForIdleSync();
		Object copmleteFragment = mActivity.getSupportFragmentManager().findFragmentByTag("CopmleteFragment");
		assertNull("Input field doesn't exist with the id:'et_input_answer'", copmleteFragment);		
	}
	
	@Test
	public void testSameAnswer() {    
		editText.setText("b");
		editText.onEditorAction(EditorInfo.IME_ACTION_DONE);
		editText.setText("b");
		editText.onEditorAction(EditorInfo.IME_ACTION_DONE);
		
		mActivity.getSupportFragmentManager().executePendingTransactions();
		getInstrumentation().waitForIdleSync();
		Object copmleteFragment = mActivity.getSupportFragmentManager().findFragmentByTag("CopmleteFragment");
		assertNull("Input field doesn't exist with the id:'et_input_answer'", copmleteFragment);		
	}
	
}
