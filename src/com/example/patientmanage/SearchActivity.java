package com.example.patientmanage;

import android.os.Bundle;
import android.app.Activity;

import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ER.App;
import ER.Nurse;

/**
 * This activity is for looking up patient.
 * 
 * @author Z.LU, L.CHEN, T.TRUONG, G.GUERRA.
 * 
 */
public class SearchActivity extends Activity {

	public static final String FILENAME = "patient_records.txt";
	private App patientDataBase;

	/**
	 * onCreate method This method sets up the activity and get the database
	 * generated from the previous activity that passing the intent in.
	 * 
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		// try {
		// patientDataBase = new App(this.getApplicationContext()
		// .getFilesDir(), FILENAME);
		// patientDataBase = new App(Context.x`, FILENAME);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		/*
		 * try {
		 * 
		 * FileOutputStream fos = openFileOutput("PatientsSaved.txt",
		 * Context.MODE_PRIVATE); Iterator<Patient> iter =
		 * patientDataBase.patientList.iterator(); while (iter.hasNext()){
		 * Patient p = iter.next(); String patientline = p.toString() + "\n";
		 * fos.write(patientline.getBytes()); fos.close(); } } catch (Exception
		 * e) { e.printStackTrace(); }
		 */

		// the below are testing codes
		// Patient p = patientDataBase.patientList.get(0);
		// TextView test = (TextView) findViewById(R.id.textView2);
		// test.setText("result is " + p.toString());
		Intent intent = getIntent();
		patientDataBase = (App) intent.getSerializableExtra("databasekey");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	/**
	 * searchPatient method This method handles the action of searching through
	 * available patients and returning the one which matches the requested
	 * health card number.
	 * 
	 * @param view
	 */
	public void searchPatient(View view) {
		Intent intent2 = new Intent(this, ViewPatientActivity.class);
		EditText HCnumText = (EditText) findViewById(R.id.editText1);

		Nurse nurse = new Nurse();
		nurse.setPatientdata(patientDataBase);
		try {
			String HCNum = HCnumText.getText().toString();
			nurse.searchPatient(HCNum);
			if (nurse.getPatient() == null) {
				TextView warn = (TextView) findViewById(R.id.warn);
				warn.setText("No such patient!");
			} else {
				intent2.putExtra("nursekey", nurse);
				startActivity(intent2);
			}
		} catch (Exception e) {
			TextView warn = (TextView) findViewById(R.id.warn);
			warn.setText("No such patient!");
		}

	}

	/**
	 * OnClick method for a button that leads Nurse to a screen that create a new Patient.
	 * @param view
	 */
	public void goToAdd(View view) {
		Intent intent = new Intent(this, CreateNewPatient.class);
		startActivity(intent);
	}

	/**
	 * OnClick method for a button that leads Nurse to a screen that displays all the Patients on the waitlist.
	 * @param view
	 */
	public void goToWaitlist(View view) {
		Intent intent = new Intent(this, PatientWaitlist.class);
		startActivity(intent);
	}

}
