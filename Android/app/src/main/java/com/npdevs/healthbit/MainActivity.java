package com.npdevs.healthbit;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.npdevs.healthbit.contracts.SignUp;
import com.npdevs.healthbit.users.OptionsPage;
import com.npdevs.healthbit.users.Register;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {    // Login activity

	private EditText textKey;
	final BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);
	final BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
	final private String CONTRACT_ADDRESS = "0x12309af7c3d47e4edbeace57a9c7b0841d054867";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btnLogin = findViewById(R.id.btnLogin);
		Button btnSignUp = findViewById(R.id.btnSignUp);
		textKey = findViewById(R.id.textKey);

		Web3j web3j = Web3j.build(new HttpService(getString(R.string.Ganache)));
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			Web3ClientVersion clientVersion = web3j.web3ClientVersion().send();
			if (!clientVersion.hasError()) {
				Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
			} else {
				System.out.println(clientVersion.getError().getMessage());
				EditText et=findViewById(R.id.editText);
				et.setText(clientVersion.getError().getMessage());
				Toast.makeText(getApplicationContext(), clientVersion.getError().getMessage(), Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			EditText et=findViewById(R.id.editText);
			et.setText(e.getMessage());
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
		}

		btnLogin.setOnClickListener(v -> {
			String pKey = String.valueOf(textKey.getText());
			Credentials credentials = Credentials.create(pKey);
			SignUp signUp = SignUp.load(CONTRACT_ADDRESS, web3j, credentials, GAS_PRICE, GAS_LIMIT);
			try {
				// TODO login check
				Toast.makeText(this, "Login success", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(this, OptionsPage.class);
				intent.putExtra("PRIVATE_KEY", pKey);
				startActivity(intent);
				finish();
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(this, "Login failed " + e.getMessage(), Toast.LENGTH_LONG).show();
			}
		});

		btnSignUp.setOnClickListener(v -> {
			Intent intent = new Intent(this, Register.class);
			startActivity(intent);
		});
	}
}
