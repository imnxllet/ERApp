package com.example.patientmanage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import ER.App;
import ER.Nurse;
import ER.Patient;
import ER.Record;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Activity that's for creating and updating records.
 * 
 * @author Z.LU, L.CHEN, T.TRUONG, G.GUERRA.
 * 
 */
public class CreateRecordActivity extends Activity {
	/**
	 * A nurse handling a specific patient.
	 */
	private Nurse nurse;
	/**
	 * A patient database populated from the file "PatientsSaved.txt".
	 */
	private App patientDataBase;

	/**
	 * OnCreate method that get the intent from previous activity. It reads the
	 * "PatientsSaved.txt" and populate a database. Create "PatientsSaved.txt"
	 * if it does not exist. Uses the patient from previous nurse if
	 * "PatientsSaved.txt" is empty. Otherwise, sets the patient of this nurse
	 * with the updated info getting from the database.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_record);
		Intent intent5 = getIntent();
		nurse = (Nurse) intent5.getSerializableExtra("nursekey");
		File file = new File(this.getApplicationContext().getFilesDir(),
				"PatientsSaved.txt");

		if (file.exists()) {
			try {
				patientDataBase = new App(this.getApplicationContext()
						.getFilesDir(), "PatientsSaved.txt");
				if (!patientDataBase.getPatientList().isEmpty()) {
					for (Patient p : patientDataBase.getPatientList()) {
						if (nurse.getPatient().getName().equals(p.getName())) {
							nurse.setPatient(p);
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_record, menu);
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

	/**
	 * OnClick method for a button for creating a new record for the patient
	 * that the nurse handling.
	 * 
	 * @param view
	 */
	public void createrecord(View view) {
		EditText time = (EditText) findViewById(R.id.arrivalTime);
		String at0 = time.getText().toString();
		nurse.getPatient().getRecords().put(at0, new Record(at0));

		TextView prompt = (TextView) findViewById(R.id.prompt);
		prompt.setText("This record is added now.");
		prompt.setTextSize(10);

	}

	/**
	 * OnClick method for a button for updating a record for the patient that
	 * the nurse handling.
	 * 
	 * @param view
	 */
	public void updaterecord(View view) {

		EditText at = (EditText) findViewById(R.id.at);
		EditText temp = (EditText) findViewById(R.id.temp);
		EditText timetemp = (EditText) findViewById(R.id.timetemp);
		EditText hr = (EditText) findViewById(R.id.hr);
		EditText timehr = (EditText) findViewById(R.id.timehr);
		EditText bp = (EditText) findViewById(R.id.bp);
		EditText timebp = (EditText) findViewById(R.id.timebp);
		EditText seenByDoc = (EditText) findViewById(R.id.seenbydoc);

		String at1 = at.getText().toString().trim();
		String temp1 = temp.getText().toString().trim();
		String timetemp1 = timetemp.getText().toString().trim();
		String hr1 = hr.getText().toString().trim();
		String timehr1 = timehr.getText().toString().trim();
		String bp1 = bp.getText().toString().trim();
		String timebp1 = timebp.getText().toString().trim();
		String seenByDoc1 = seenByDoc.getText().toString().trim();
		if (nurse.getPatient().getRecords().containsKey(at1)) {
			if (!temp1.equals("") && !timetemp1.equals("")) {
				nurse.getPatient().getRecords().get(at1).getTemperature()
						.put(timetemp1, temp1);
			}
			if (!hr1.equals("") && !timehr1.equals("")) {
				nurse.getPatient().getRecords().get(at1).getHeartRate()
						.put(timehr1, hr1);
			}
			if (!bp1.equals("") && !timebp1.equals("")) {
				nurse.getPatient().getRecords().get(at1).getBloodPressure()
						.put(timebp1, bp1);
			}
			if (!seenByDoc1.equals("")) {
				nurse.getPatient().getRecords().get(at1)
						.setSeenByDoctor(seenByDoc1);
			}
			TextView prompt = (TextView) findViewById(R.id.prompt);
			prompt.setText("This record is updated now.");
			prompt.setTextSize(10);
		} else {
			TextView prompt = (TextView) findViewById(R.id.prompt);
			prompt.setText("Record does not exist.");
			prompt.setTextSize(10);
		}

	}

	/**
	 * OnClick method for a button for saving the updated information of the
	 * patient that the nurse handling on to "PatientsSaved.txt".
	 * 
	 * @param view
	 */
	public void saveAll(View view) {
		try {
			File saved = new File(this.getApplicationContext().getFilesDir(),
					"PatientsSaved.txt");
			FileWriter writer = new FileWriter(saved, true);
			BufferedWriter bufferWritter = new BufferedWriter(writer);
			bufferWritter.write(nurse.getPatient().toString() + "\n");
			bufferWritter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		TextView prompt = (TextView) findViewById(R.id.prompt);
		prompt.setText("All changes have been saved!");
		prompt.setTextSize(10);
	}

}
