package com.npdevs.healthbit.users;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.npdevs.healthbit.R;
import com.npdevs.healthbit.contracts.SignUp;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;

public class Register extends AppCompatActivity {

	final BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);
	final BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
	final private String CONTRACT_ADDRESS = "0x12309af7c3d47e4edbeace57a9c7b0841d054867";
	private Button buttonRegister, buttonCancel;
	private EditText eName, eKey, eCountry, eCity, eDistt, eDOB, eState, eLocality, eHouse, eAadhaar, eBlood;
	private String sName, sKey, sCountry, sCity, sDistt, sDOB, sState, sLocality, sHouse, sAadhaar, sBlood;
	// for processor: 0xfdd29fd0e1aa61d9c0f17032634cda77e4e801e9
	private Web3j web3j;
	private Credentials credentials;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		buttonRegister = findViewById(R.id.button7);
		buttonCancel = findViewById(R.id.buttonCancel);
		eName = findViewById(R.id.editText);
		eKey = findViewById(R.id.editText2);
		eDOB = findViewById(R.id.editText3);
		eCountry = findViewById(R.id.editText4);
		eCity = findViewById(R.id.editText5);
		eState = findViewById(R.id.editText6);
		eDistt = findViewById(R.id.editText7);
		eLocality = findViewById(R.id.editText8);
		eHouse = findViewById(R.id.editText9);
		eAadhaar = findViewById(R.id.editText10);
		eBlood = findViewById(R.id.editText11);
		web3j = Web3j.build(new HttpService(getString(R.string.Ganache)));

		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			Web3ClientVersion clientVersion = web3j.web3ClientVersion().send();
			if (!clientVersion.hasError()) {
				Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(getApplicationContext(), clientVersion.getError().getMessage(), Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
		}

		buttonRegister.setOnClickListener(v -> {
			sName = eName.getText().toString();
			sKey = eKey.getText().toString();
			sDOB = eDOB.getText().toString();
			sCountry = eCountry.getText().toString();
			sCity = eCity.getText().toString();
			sState = eState.getText().toString();
			sDistt = eDistt.getText().toString();
			sLocality = eLocality.getText().toString();
			sHouse = eHouse.getText().toString();
			sAadhaar = eAadhaar.getText().toString();
			sBlood = eBlood.getText().toString();

			credentials = Credentials.create(sKey);

			SignUp signUp = null;
			TransactionReceipt tr;
			try {
				signUp = SignUp.load(CONTRACT_ADDRESS, web3j, credentials, GAS_PRICE, GAS_LIMIT);
				Toast.makeText(this, "Contract Loaded", Toast.LENGTH_LONG).show();
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
			}
			assert signUp != null;
			try {
				tr = signUp.signupUser(sName, sDOB, sCountry, sCity, sState, sDistt, sLocality, sHouse, sAadhaar, sBlood).send();
				Toast.makeText(this, "Registration Successful: " + tr.isStatusOK() + "\nPlease update records using Edit Details option", Toast.LENGTH_LONG).show();
				if (tr.isStatusOK())
					finish();
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
			}
		});

		buttonCancel.setOnClickListener(v -> finish());
	}
}
