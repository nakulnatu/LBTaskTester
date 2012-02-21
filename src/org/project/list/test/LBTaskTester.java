package org.project.list.test;

import android.location.Location;
import android.location.LocationManager;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;
import org.project.list.Current_Location;
/**
Class to Test application LBList

Solves CS185c Section 3 Project LBTask

@author Nakul Natu(007224360) and Tarun Ramaswamy(007475208)
*/

public class LBTaskTester extends
		android.test.ActivityInstrumentationTestCase2<Current_Location> 
{
	private Current_Location testActivity;
	Button tempButton;
	ViewFlipper vf;

	public LBTaskTester() 
	{
		super("org.project.list", Current_Location.class);
	}

	public static void waiting(int n) 
	{
		// Function to wait for n seconds
		long t0, t1;

		t0 = System.currentTimeMillis();

		do 
		{
			t1 = System.currentTimeMillis();
		} 
		while ((t1 - t0) < (n * 1000));
	}

	@Override
	protected void setUp() throws Exception 
	{
		super.setUp();
		// For performing key events we have to make touch invalid
		setActivityInitialTouchMode(false);
		// Get the activity
		testActivity = getActivity();
		// Set view flipper and button
		tempButton = (Button) testActivity
				.findViewById(org.project.list.R.id.BButton_MainList);
		vf = (ViewFlipper) testActivity
				.findViewById(org.project.list.R.id.ViewFlipper01);
	}

	public void testPackageUI() 
	{
		/** In this package we do UI testing. If the buttons are
		 working properly and we can navigate properly or not
		 */
		waiting(4);
		// Test 1 We are on current list. Go to main list
		testActivity.runOnUiThread(new Runnable() 
		{
			public void run() 
			{	// Get the fovus on button
				tempButton.requestFocus();
			} // end of run() method definition
		} // end of anonymous Runnable object instantiation
		);
		// Press the button All Tasks
		this.sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);
		// If displayed child is main list test passed
		assertEquals(2, vf.getDisplayedChild());
		
		// Test 2 We are on main list. Go to current list
		waiting(4);
		tempButton = (Button) testActivity
				.findViewById(org.project.list.R.id.Button_Home);
		testActivity.runOnUiThread(new Runnable() 
		{
			public void run() 
			{
				tempButton.requestFocus();
			} // end of run() method definition
		} // end of anonymous Runnable object instantiation
		);
		// Press Home button
		this.sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);
		// If displayed child is current list test is passed
		assertEquals(0, vf.getDisplayedChild());

		// Test 3 We are on current list. Go to Add Item
		waiting(4);
		tempButton = (Button) testActivity
				.findViewById(org.project.list.R.id.Button_AddTask);
		testActivity.runOnUiThread(new Runnable() 
		{
			public void run() 
			{
				tempButton.requestFocus();
			} // end of run() method definition
		} // end of anonymous Runnable object instantiation
		);
		// Press Add Task Button
		this.sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);
		// If displayed child is Add Item test is passed
		assertEquals(3, vf.getDisplayedChild());

		// Test 4 We are on Add Item. Go to main list
		waiting(4);
		tempButton = (Button) testActivity
				.findViewById(org.project.list.R.id.Button_Discard);
		testActivity.runOnUiThread(new Runnable() 
		{
			public void run() 
			{
				tempButton.requestFocus();
			} // end of run() method definition
		} // end of anonymous Runnable object instantiation
		);
		// Press Discard Button
		this.sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);
		// If displayed child is Main List test is passed
		assertEquals(2, vf.getDisplayedChild());

		// Test 5 We are on Main List. Go to Add Item
		waiting(4);
		tempButton = (Button) testActivity
				.findViewById(org.project.list.R.id.Button_Add);
		testActivity.runOnUiThread(new Runnable() 
		{
			public void run() 
			{
				tempButton.requestFocus();
			} // end of run() method definition
		} // end of anonymous Runnable object instantiation
		);
		// Press Add Button
		this.sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);
		// If displayed child is Add Item test is passed
		assertEquals(3, vf.getDisplayedChild());
	}

	public void testPackageLocation() 
	{
		/** Here we test adding a task in the application 
		  as well as main function that is location based searching
		 */
		// Test 6 Add Item
		waiting(4);
		tempButton = (Button) testActivity
				.findViewById(org.project.list.R.id.Button_AddTask);
		testActivity.runOnUiThread(new Runnable() 
		{
			public void run() 
			{
				tempButton.requestFocus();
			} // end of run() method definition
		} // end of anonymous Runnable object instantiation
		);
		// Go to add item
		this.sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);
		// Set the data to add
		waiting(5);
		final TextView tempText1 = (TextView) testActivity
				.findViewById(org.project.list.R.id.Text_Name);
		final TextView tempText2 = (TextView) testActivity
				.findViewById(org.project.list.R.id.Text_Location);

		final TextView tempText3 = (TextView) testActivity
				.findViewById(org.project.list.R.id.Text_Description);

		final TextView tempText4 = (TextView) testActivity
				.findViewById(org.project.list.R.id.Text_Date);

		final TextView tempText5 = (TextView) testActivity
				.findViewById(org.project.list.R.id.Text_Time);
		testActivity.runOnUiThread(new Runnable() 
		{
			public void run() 
			{
				tempText1.setText("TestHomeDepot");
				tempText2.setText("Home Depot");
				tempText3.setText("Anti bed bug spray");
				tempText4.setText("12/8/2010");
				tempText5.setText("12:45");
			} // end of run() method definition
		} // end of anonymous Runnable object instantiation
		);

		waiting(2);
		// Save the data
		tempButton = (Button) testActivity
				.findViewById(org.project.list.R.id.Button_Save);
		testActivity.runOnUiThread(new Runnable() 
		{
			public void run() 
			{
				tempButton.requestFocus();
			} // end of run() method definition
		} // end of anonymous Runnable object instantiation
		);
		this.sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);
		waiting(5);
		// If data is added then test is passed
		assertEquals(true, Current_Location.items.contains("TestHomeDepot"));
		
		// Test 7 Location Based Search
		final Location loc = new Location(LocationManager.GPS_PROVIDER);
		// Set new location
		loc.setLatitude(37.311341);
		loc.setLongitude(-121.851564);
		
		tempButton = (Button) testActivity
				.findViewById(org.project.list.R.id.Button_Home);
		testActivity.runOnUiThread(new Runnable() 
		{
			public void run() 
			{
				// Call method to search data location wise 
				testActivity.onLocationChanged(loc);
				tempButton.requestFocus();
			} // end of run() method definition
		} // end of anonymous Runnable object instantiation
		);
		// Go to Current List
		this.sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);
		waiting(5);
		waiting(60);
		// If the task is present in the list then test is passed
		assertEquals(true, Current_Location.title.contains("TestHomeDepot"));

	}

	public void testPackagePersistence() 
	{
		/** Here we test the database persistence of the application
		  after restarting it.
		 */
		int size = 0;
		waiting(4);
		// Test 8 Check the number of tasks
		tempButton = (Button) testActivity
				.findViewById(org.project.list.R.id.BButton_MainList);
		testActivity.runOnUiThread(new Runnable() 
		{
			public void run() 
			{
				tempButton.requestFocus();
			} // end of run() method definition
		} // end of anonymous Runnable object instantiation
		);
		// Go to current list 
		this.sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);
		waiting(4);
		// Check the number of tasks present
		size = Current_Location.items.size();
		// Stop the activity
		testActivity.finish();
		// Start the activity
		testActivity = this.getActivity();
		waiting(4);
		testActivity.runOnUiThread(new Runnable() 
		{
			public void run() 
			{
				tempButton.requestFocus();
			} // end of run() method definition
		} // end of anonymous Runnable object instantiation
		);
		// Go to current list
		this.sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);
		waiting(4);
		/** If the number of tasks before and after restarting application 
		are same then test passed */
		assertEquals(size, Current_Location.items.size());
	}

}
