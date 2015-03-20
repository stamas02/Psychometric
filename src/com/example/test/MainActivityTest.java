package com.example.test;

import org.junit.Before;
import org.junit.Test;

import com.example.psychometric.MainActivity;
import com.example.psychometric.R;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.test.ActivityUnitTestCase;

public class MainActivityTest extends ActivityUnitTestCase<MainActivity> {

	
	public MainActivityTest() {
		
	       super(MainActivity.class);
	   }

	ActionBarActivity activity;

	@Before
	protected void setUp() throws Exception {	
		
		super.setUp();
		startActivity(new Intent(getInstrumentation().getTargetContext(), MainActivity.class), null, null);
	}

	@Test
	public void testActivityCreation() {
		// Verify the intent was started with correct result
		assertNotNull("Intent should have triggered after button press", getActivity());
	}
	
	
}
