package com.example.patientmanage;

import java.io.File;
import java.io.FileInputStream;

import java.util.Scanner;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * The first activity when we launch the app. It has a button to load data
 * inside "patient_records.txt".
 * 
 * @author Z.LU, L.CHEN, T.TRUONG, G.GUERRA.
 */
public class MainActivity extends Activity {
	/**
	 * onCreate Method. If an instance is saved, restores it. Else, a new
	 * instance is created.
	 * 
	 * @param savedInstanceState
	 *            .
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * OnClick method for a button that will allows a Nurse to login.
	 * @param view
	 */
	public void loginNurse(View view) {
		Intent intent = new Intent(this, MainNurseActivity.class);
		boolean here = false;
		EditText user = (EditText) findViewById(R.id.username);
		EditText password = (EditText) findViewById(R.id.password);

		String user1 = user.getText().toString().trim();
		String password1 = password.getText().toString().trim();

		File file = new File(this.getApplicationContext().getFilesDir(),
				"passwords.txt");
		try {
			Scanner scanner = new Scanner(new FileInputStream(file.getPath()));
			while (scanner.hasNext()) {
				String[] line = scanner.nextLine().split(",");
				if (user1.equals(line[0]) && password1.equals(line[1])) {
					if ("nurse".equals(line[2])) {
						here = true;
						intent.putExtra("username", user1);
						startActivity(intent);
					}
				}
			}
		} catch (Exception e) {

		}
		if (here == false) {
			TextView invalid = (TextView) findViewById(R.id.invalid);
			invalid.setText("Invalid username/password, please enter again.");
		}
	}

	/**
	 * OnClick method for a button that will allows a Pysician to login.
	 * @param view
	 */
	public void loginPhysician(View view) {
		Intent intent = new Intent(this, PhyWelcomeActivity.class);
		boolean here = false;
		EditText user = (EditText) findViewById(R.id.username);
		EditText password = (EditText) findViewById(R.id.password);

		String user1 = user.getText().toString().trim();
		String password1 = password.getText().toString().trim();

		File file = new File(this.getApplicationContext().getFilesDir(),
				"passwords.txt");
		try {
			Scanner scanner = new Scanner(new FileInputStream(file.getPath()));
			while (scanner.hasNext()) {
				String[] line = scanner.nextLine().split(",");
				if (user1.equals(line[0]) && password1.equals(line[1])) {
					if ("physician".equals(line[2])) {
						here = true;
						intent.putExtra("username", user1);
						startActivity(intent);
					}
				}
			}
		} catch (Exception e) {

		}
		if (here == false) {
			TextView invalid = (TextView) findViewById(R.id.invalid);
			invalid.setText("Invalid username/password, please enter again.");
		}
	}
}