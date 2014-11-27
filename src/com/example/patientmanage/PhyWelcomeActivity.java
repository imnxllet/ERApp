package com.example.patientmanage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * The first activity when we launch the app as Nurses. It has a button to load
 * data inside "patient_records.txt".
 * 
 * @author Z.LU, L.CHEN, T.TRUONG, G.GUERRA.
 * 
 */
public class PhyWelcomeActivity extends Activity {

	private String username;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phy_welcome);
		Intent intent = getIntent();
		username = (String) intent.getSerializableExtra("username");
		TextView name = (TextView) findViewById(R.id.user_name);
		name.setText(username + "!");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.phy_welcome, menu);
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
	 * OnClick method for a button to load Patient information.
	 * 
	 * @param view
	 */
	public void load(View view) {
		Intent intent = new Intent(this, MainPhysicianActivity.class);
		startActivity(intent);
	}
}
