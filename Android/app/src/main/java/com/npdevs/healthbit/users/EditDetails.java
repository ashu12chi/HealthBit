package com.npdevs.healthbit.users;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.npdevs.healthbit.R;
import com.npdevs.healthbit.contracts.SignUp;
import com.npdevs.healthbit.filehandling.FileHandler;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple4;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;

public class EditDetails extends AppCompatActivity {

	final BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);
	final BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
	final private String CONTRACT_ADDRESS = "0x12309af7c3d47e4edbeace57a9c7b0841d054867";
	private String PRIVATE_KEY;
	private SignUp signUp;
	private Web3j web3j;
	private String imageUri;
	private String hash = "QmSCgvcaRaKqfDKfoFRwumuzHbGNYbTjyZcAuvKb8FBXmy";
	private Credentials credentials;
	private Button buttonRegister, buttonCancel, btnChoose, btnUpload, btnFetch;
	private EditText eCountry, eCity, eDistt, eState, eLocality, eHouse, textNSP;
	private String sCountry, sCity, sDistt, sState, sLocality, sHouse;
	// for processor: 0xfdd29fd0e1aa61d9c0f17032634cda77e4e801e9

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_details);

		PRIVATE_KEY = getIntent().getStringExtra("PRIVATE_KEY");

		buttonRegister = findViewById(R.id.editEdit);
		buttonCancel = findViewById(R.id.buttonCancel);
		btnUpload = findViewById(R.id.btnUpload);
		btnChoose = findViewById(R.id.btnChoose);
		btnFetch = findViewById(R.id.btnFetch);
		textNSP = findViewById(R.id.textNSP);

		eCountry = findViewById(R.id.editCountry);
		eCity = findViewById(R.id.editCity);
		eState = findViewById(R.id.editState);
		eDistt = findViewById(R.id.editDistt);
		eLocality = findViewById(R.id.editLocal);
		eHouse = findViewById(R.id.editHouse);

		web3j = Web3j.build(new HttpService(getString(R.string.Ganache)));
		credentials = Credentials.create(PRIVATE_KEY);
		signUp = SignUp.load(CONTRACT_ADDRESS, web3j, credentials, GAS_PRICE, GAS_LIMIT);
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
			StrictMode.setVmPolicy(builder.build());
			Web3ClientVersion clientVersion = web3j.web3ClientVersion().send();
			if (!clientVersion.hasError()) {
				Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(getApplicationContext(), clientVersion.getError().getMessage(), Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
		}

		try {
			Tuple4<String, String, String, String> user = signUp.getUserDetails(credentials.getAddress()).send();
			eCountry.setText(user.getValue1());
			eState.setText(user.getValue2());
			eDistt.setText(user.getValue3());
			eCity.setText(user.getValue4());
			eHouse.setText("dhgfdh");
			eLocality.setText("SHGDHSG");
			hash = signUp.getUserHash(credentials.getAddress()).send();
			textNSP.setText(hash);
		} catch (Exception e) {
			e.printStackTrace();
		}

		buttonRegister.setOnClickListener(v -> {
			sCountry = eCountry.getText().toString();
			sCity = eCity.getText().toString();
			sState = eState.getText().toString();
			sDistt = eDistt.getText().toString();
			sLocality = eLocality.getText().toString();
			sHouse = eHouse.getText().toString();

			try {
				TransactionReceipt tr = signUp.editUserDetails(sLocality, sDistt, sState, sCountry, sCity, sHouse, hash).send();
				Toast.makeText(this, "SUCCESS: " + tr.isStatusOK(), Toast.LENGTH_LONG).show();
				if (tr.isStatusOK())
					finish();
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
			}
		});

		btnChoose.setOnClickListener(v -> {
			try {
				openFileChooser();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		btnUpload.setOnClickListener(v -> {
			try {
				FileHandler fileHandler = new FileHandler();
				hash = fileHandler.addFile(imageUri);
				Toast.makeText(this, imageUri, Toast.LENGTH_LONG).show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		btnFetch.setOnClickListener(v -> {
			try {
				FileHandler fileHandler = new FileHandler();
				byte[] file = fileHandler.getFile(hash);
				String fileName = hash + ".txt";

				File picFile = new File("/storage/emulated/0/" + fileName);

				OutputStream os = new FileOutputStream(picFile);
				os.write(file);
				System.out.println("Successfully byte inserted");
				os.close();
				textNSP.setText(new String(file));
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setDataAndType(Uri.fromFile(picFile), "*/*");
//				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private void openFileChooser() {
		Intent intent = new Intent();
		intent.setType("*/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(intent, 1);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
			imageUri = getRealPathFromURI(this, data.getData());
			Toast.makeText(EditDetails.this, imageUri, Toast.LENGTH_LONG).show();
			textNSP.setText(imageUri);
		}
	}

	private String getRealPathFromURI(Context context, Uri contentUri) {
		Cursor cursor = null;
		try {
			String[] proj = {MediaStore.Images.Media.DATA};
			cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
			int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			return cursor.getString(column_index);
		} catch (Exception e) {
			Log.e("NSP", "getRealPathFromURI Exception : " + e.toString());
			return "";
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
	}
}
