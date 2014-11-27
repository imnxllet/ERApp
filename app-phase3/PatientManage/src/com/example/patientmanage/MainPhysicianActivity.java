package com.example.patientmanage;

import java.io.IOException;

import ER.App;
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
 * It allows Physicians to lookup Patients' information, and it contains a
 * button that leads physicians to the screen of adding prescription.
 * 
 * @author Z.LU, L.CHEN, T.TRUONG, G.GUERRA.
 * 
 */
public class MainPhysicianActivity extends Activity {

	private App patientDataBase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_physician);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_physician, menu);
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
	 * OnClick method for a button that allows physicians to add prescription
	 * for a Patient.
	 * 
	 * @param view
	 */
	public void addPrescription(View view) {
		try {
			patientDataBase = new App(this.getApplicationContext()
					.getFilesDir(), "patient_records.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Intent intent2 = new Intent(this, AddPrescriptionActivity.class);
		EditText HCnumText = (EditText) findViewById(R.id.phyHCNum);
		Physician p = new Physician();
		p.setPatientdata(patientDataBase);
		try {
			String HCNum = HCnumText.getText().toString();
			p.searchPatient(HCNum);
			if (p.getPatient() == null) {
				TextView warn = (TextView) findViewById(R.id.phydisplay);
				warn.setText("No such patient!");
			} else {
				intent2.putExtra("physiciankey", p);
				startActivity(intent2);
			}
		} catch (Exception e) {
			TextView warn = (TextView) findViewById(R.id.phydisplay);
			warn.setText("No such patient!");
		}

	}

	/**
	 * OnClick method for a button that displays Patient information.
	 * 
	 * @param view
	 */
	public void displayPatient(View view) {
		try {
			patientDataBase = new App(this.getApplicationContext()
					.getFilesDir(), "patient_records.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		EditText HCnumText = (EditText) findViewById(R.id.phyHCNum);
		Physician p = new Physician();
		p.setPatientdata(patientDataBase);
		try {
			String HCNum = HCnumText.getText().toString();
			p.searchPatient(HCNum);
			if (p.getPatient() == null) {
				TextView warn = (TextView) findViewById(R.id.phydisplay);
				warn.setText("No such patient!");
			} else {
				TextView warn = (TextView) findViewById(R.id.phydisplay);
				warn.setText(p.viewPatient());
			}
		} catch (Exception e) {
			TextView warn = (TextView) findViewById(R.id.phydisplay);
			warn.setText("No such patient!");
		}
	}
}
