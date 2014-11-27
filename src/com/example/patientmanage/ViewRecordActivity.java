package com.example.patientmanage;

import java.io.IOException;
import ER.App;
import ER.Nurse;
import ER.Patient;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * A activity that for viewing all records that the patient has.
 * 
 * @author Z.LU, L.CHEN, T.TRUONG, G.GUERRA.
 * 
 */
public class ViewRecordActivity extends Activity {
	/**
	 * A nurse who handling a specific patient.
	 */
	private Nurse nurse;
	/**
	 * A patient database read from "PatientsSaved.txt".
	 */
	private App patientDataBase;

	/**
	 * Read "PatientsSaved.txt" and generate an App patientDataBase. Gets the
	 * nurse passing from the previous activity and set the patient of this
	 * nurse to a updated one from the patientdataBase if it exists. Finally
	 * display all records of the patient.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_record);

		Intent intent4 = getIntent();
		nurse = (Nurse) intent4.getSerializableExtra("nursekey");
		try {
			patientDataBase = new App(this.getApplicationContext()
					.getFilesDir(), "PatientsSaved.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Patient p : patientDataBase.getPatientList()) {
			if (nurse.getPatient().getName().equals(p.getName())) {
				nurse.setPatient(p);
			}
		}
		TextView patientRe = (TextView) findViewById(R.id.records);
		patientRe.setText(nurse.viewRecords());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_record, menu);
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
