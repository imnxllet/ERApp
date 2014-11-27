package com.example.patientmanage;

import ER.Nurse;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

/**
 * Activity that is for viewing Patient's Records.
 * 
 * @author Z.LU, L.CHEN, T.TRUONG, G.GUERRA.
 */

public class ViewPatientActivity extends Activity {
	private Nurse nurse;

	/**
	 * Gets the intent from previews activity, and displays the Patient's
	 * information.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_patient);

		Intent intent2 = getIntent();
		nurse = (Nurse) intent2.getSerializableExtra("nursekey");
		TextView patientInfo = (TextView) findViewById(R.id.textView1);
		patientInfo.setText(nurse.viewPatient());
		patientInfo.setTextSize(17);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_patient, menu);
		return true;
	}

	/**
	 * This is a onClick method for a button that start an activity to display
	 * the Patient's Records.
	 * 
	 * @param view
	 */
	public void viewRecords(View view) {
		Intent intent3 = new Intent(this, ViewRecordActivity.class);
		intent3.putExtra("nursekey", nurse);

		startActivity(intent3);
	}

	/**
	 * This is a onClick method for a button that start an activity to create or
	 * update a new Record for the Patient.
	 * 
	 * @param view
	 */
	public void createrecord(View view) {
		Intent intent4 = new Intent(this, CreateRecordActivity.class);
		intent4.putExtra("nursekey", nurse);

		startActivity(intent4);
	}
}