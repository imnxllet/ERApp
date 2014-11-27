package com.example.patientmanage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import ER.Physician;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Phycisians are allowed to update prescriptions for Patients.
 * 
 * @author Z.LU, L.CHEN, T.TRUONG, G.GUERRA.
 * 
 */
public class AddPrescriptionActivity extends Activity {

	private Physician physician;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_prescription);

		Intent intent2 = getIntent();
		physician = (Physician) intent2.getSerializableExtra("physiciankey");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_prescription, menu);
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
	 * OnClick method for a button that allows physician to add a new
	 * prescription.
	 * 
	 * @param view
	 */
	public void add(View view) {
		EditText time = (EditText) findViewById(R.id.addTime);
		EditText pres = (EditText) findViewById(R.id.prescription);
		String time1 = time.getText().toString().trim();
		String pres1 = pres.getText().toString().trim();

		physician.getPatient().getPrescription().put(time1, pres1);
		try {
			File saved = new File(this.getApplicationContext().getFilesDir(),
					"patient_records.txt");
			FileWriter writer = new FileWriter(saved, true);
			BufferedWriter bufferWritter = new BufferedWriter(writer);
			bufferWritter.write("\n" + physician.getPatient().toString());
			bufferWritter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		TextView prompt = (TextView) findViewById(R.id.saved_prescription);
		prompt.setText("Added new prescription.");
		prompt.setTextSize(10);

	}
}
