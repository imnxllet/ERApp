package com.example.patientmanage;

import java.io.IOException;
import java.util.ArrayList;

import ER.App;
import ER.Nurse;
import ER.Patient;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * It displays Patients information for those who are on the waitlist.
 * 
 * @author Z.LU, L.CHEN, T.TRUONG, G.GUERRA.
 * 
 */
public class PatientWaitlist extends Activity {

	private App patientDataBase;
	private Nurse nurse;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_waitlist);
		try {
			patientDataBase = new App(this.getApplicationContext()
					.getFilesDir(), "PatientsSaved.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		nurse = new Nurse();
		TextView waitlist = (TextView) findViewById(R.id.waitlist);
		ArrayList<Patient> list = nurse.removeThoseVisited(patientDataBase
				.getPatientList());
		// test
		// String test = "";
		// for (Patient p : list){
		// test = test + p.toString();
		// }
		// waitlist.setText(test);
		if (list.isEmpty()) {
			waitlist.setText("All patients with records have been seen!");
		} else {
			waitlist.setText(nurse.waitListToString(list));
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.patient_waitlist, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
