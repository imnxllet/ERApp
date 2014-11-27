package com.example.patientmanage;

import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import ER.App;

/**
 * The first activity when we launch the app as Nurses. It has a button to load
 * data inside "patient_records.txt".
 * 
 * @author Z.LU, L.CHEN, T.TRUONG, G.GUERRA.
 */
public class MainNurseActivity extends Activity {
	/**
	 * A database read from the "patient_records.txt".
	 */
	private App patientDataBase;
	private String username;

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
		setContentView(R.layout.activity_main_nurse);
		Intent intent = getIntent();
		username = (String) intent.getSerializableExtra("username");
		TextView name = (TextView) findViewById(R.id.user_name);
		name.setText(username + "!");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * loadFiles method OnClick method for button that will load the patients in
	 * "patient_records.txt".
	 * 
	 * @param view
	 * 
	 */
	public void loadFiles(View view) {
		Intent intent1 = new Intent(this, SearchActivity.class);
		try {
			patientDataBase = new App(this.getApplicationContext()
					.getFilesDir(), "patient_records.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * 
		 * 
		 * try { FileOutputStream fos = openFileOutput("PatientsSaved.txt",
		 * Context.MODE_PRIVATE); File saved = new
		 * File(this.getApplicationContext() .getFilesDir(),
		 * "PatientsSaved.txt"); File old = new
		 * File(this.getApplicationContext() .getFilesDir(),
		 * "patient_records.txt"); Scanner scanner = new Scanner(new
		 * FileInputStream(saved.getPath())); Scanner scanner1 = new Scanner(new
		 * FileInputStream(old.getPath())); FileWriter writer = new
		 * FileWriter(saved); if (!scanner.hasNext()){ while
		 * (scanner1.hasNext()){ writer.write(scanner1.nextLine() + "\n"); }
		 * writer.close(); patientDataBase = new
		 * App(this.getApplicationContext() .getFilesDir(),
		 * "PatientsSaved.txt"); }else { patientDataBase = new
		 * App(this.getApplicationContext() .getFilesDir(),
		 * "PatientsSaved.txt"); } } catch (Exception e) { e.printStackTrace();
		 * }
		 */
		intent1.putExtra("databasekey", patientDataBase);
		startActivity(intent1);

	}

}
