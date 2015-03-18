package com.example.tams.psychometric.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.tams.psychometric.MainActivity;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.Button;

public class MainActivityTest extends ActivityUnitTestCase<MainActivity> {

	private MainActivity activity; 
	private Intent intent;
	
	public MainActivityTest() {
		super(MainActivity.class);
	}
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		intent = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);
	startActivity(intent, null, null);
	activity = getActivity();
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testOnClick() {
		// Verify the intent was started with correct result
		assertNotNull("Intent should have triggered after button press", intent);
	}

}
