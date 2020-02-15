package com.npdevs.healthbit.users;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.npdevs.healthbit.R;

public class OptionsPage extends AppCompatActivity {

	private String PRIVATE_KEY;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options_page);

		PRIVATE_KEY = getIntent().getStringExtra("PRIVATE_KEY");
		Button buttonViewDoctors = findViewById(R.id.btnViewDoc);
		Button buttonEditDetails = findViewById(R.id.btnEditDetails);
		Button buttonViewPres = findViewById(R.id.btnViewPres);
		Button buttonViewCompanies = findViewById(R.id.btnViewInsur);
		Button buttonViewPharmacies = findViewById(R.id.btnViewPharma);
		Button buttonViewBlood = findViewById(R.id.buttonViewBlood);

		buttonViewDoctors.setOnClickListener(v -> {
			Intent intent = new Intent(this, ViewDoctors.class);
			intent.putExtra("PRIVATE_KEY", PRIVATE_KEY);
			startActivity(intent);
		});

		buttonEditDetails.setOnClickListener(v -> {
			Intent intent = new Intent(this, EditDetails.class);
			intent.putExtra("PRIVATE_KEY", PRIVATE_KEY);
			startActivity(intent);
		});

		buttonViewPres.setOnClickListener(v -> {
			Intent intent = new Intent(this, ViewPrescriptions.class);
			intent.putExtra("PRIVATE_KEY", PRIVATE_KEY);
			startActivity(intent);
		});

		buttonViewCompanies.setOnClickListener(v -> {
			Intent intent = new Intent(this, ViewCompanies.class);
			intent.putExtra("PRIVATE_KEY", PRIVATE_KEY);
			startActivity(intent);
		});

		buttonViewPharmacies.setOnClickListener(v -> {
			Intent intent = new Intent(this, ViewPharmacies.class);
			intent.putExtra("PRIVATE_KEY", PRIVATE_KEY);
			startActivity(intent);
		});

		buttonViewBlood.setOnClickListener(v -> {
			Intent intent = new Intent(this, ViewBloodRequests.class);
			intent.putExtra("PRIVATE_KEY", PRIVATE_KEY);
			startActivity(intent);
		});
	}
}
