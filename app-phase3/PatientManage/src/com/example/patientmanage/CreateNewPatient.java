package com.example.patientmanage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This activities allows Nurses to create new Patients.
 * 
 * @author Z.LU, L.CHEN, T.TRUONG, G.GUERRA.
 * 
 */
public class CreateNewPatient extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_new_patient);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_new_patient, menu);
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
	 * OnClick method for a button that allows Nurse to create a new Patient.
	 * 
	 * @param view
	 */
	public void addPatient(View view) {
		EditText newp_name = (EditText) findViewById(R.id.newp_name);
		EditText newp_db = (EditText) findViewById(R.id.newp_bd);
		EditText newp_hc = (EditText) findViewById(R.id.newp_hc);

		String p_name = newp_name.getText().toString().trim();
		String p_db = newp_db.getText().toString().trim();
		String p_hc = newp_hc.getText().toString().trim();

		try {
			File saved = new File(this.getApplicationContext().getFilesDir(),
					"patient_records.txt");
			FileWriter writer = new FileWriter(saved, true);
			BufferedWriter bufferWritter = new BufferedWriter(writer);
			bufferWritter.write("\n" + p_hc + "," + p_name + "," + p_db);
			bufferWritter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		TextView prompt = (TextView) findViewById(R.id.patient_added);
		prompt.setText("Patient added!\nTo see the new patient, please go back to the loading screen and load again.");
		prompt.setTextSize(10);
	}

}
