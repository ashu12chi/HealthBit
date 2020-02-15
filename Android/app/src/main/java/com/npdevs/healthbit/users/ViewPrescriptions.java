package com.npdevs.healthbit.users;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.npdevs.healthbit.R;
import com.npdevs.healthbit.contracts.Processor;
import com.npdevs.healthbit.contracts.SignUp;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple4;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ViewPrescriptions extends AppCompatActivity {

	final BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);
	final BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
	final private String CONTRACT_ADDRESS = "0x12309af7c3d47e4edbeace57a9c7b0841d054867";
	final private String PROCESSOR_ADDRESS = "0xfdd29fd0e1aa61d9c0f17032634cda77e4e801e9";
	private String PRIVATE_KEY;
	private Web3j web3j;
	private Credentials credentials;
	private SignUp signUp;
	private Processor processor;
	private RecyclerView recyclerView;
	private String myAddress;

	private List<Tuple4<String, String, String, BigInteger>> msampleItem = new ArrayList<Tuple4<String, String, String, BigInteger>>();
	private List<String> presData = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_prescription);
		PRIVATE_KEY = getIntent().getStringExtra("PRIVATE_KEY");

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
		credentials = Credentials.create(PRIVATE_KEY);
		myAddress = credentials.getAddress();

		signUp = SignUp.load(CONTRACT_ADDRESS, web3j, credentials, GAS_PRICE, GAS_LIMIT);
		processor = Processor.load(PROCESSOR_ADDRESS, web3j, credentials, GAS_PRICE, GAS_LIMIT);

		int numberOfPres = -1;

		try {
			numberOfPres = processor.presCount().send().intValue();
			Toast.makeText(this, numberOfPres + "", Toast.LENGTH_LONG).show();
			for (int i = 0; i < numberOfPres; i++) {
				Tuple4<String, String, String, BigInteger> pres = processor.getPrescription(BigInteger.valueOf(i)).send();
				if (pres.getValue2().equals(myAddress)) {
					msampleItem.add(pres);
					Log.e("NSP", "DONE");
					presData.add(processor.getMedicine(BigInteger.valueOf(i)).send());
				}
			}
			recyclerView = findViewById(R.id.recycler_view);
			recyclerView.setHasFixedSize(true);
			RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ViewPrescriptions.this);
			recyclerView.setLayoutManager(layoutManager);
			RecyclerView.Adapter adapter = new MainAdapter(msampleItem);
			recyclerView.setAdapter(adapter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (numberOfPres == -1) {
			Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show();
		}
	}

	public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

		private List<Tuple4<String, String, String, BigInteger>> samples;

		MainAdapter(List<Tuple4<String, String, String, BigInteger>> samples) {
			this.samples = samples;
			Log.e("NSP Recycler", samples.size() + "");
		}

		@NonNull
		@Override
		public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
			View view = LayoutInflater
					.from(viewGroup.getContext())
					.inflate(R.layout.item_doctor, viewGroup, false);

			return new ViewHolder(view);
		}

		@Override
		public void onBindViewHolder(@NonNull MainAdapter.ViewHolder viewHolder, int position) {
			Tuple4<String, String, String, BigInteger> doc = samples.get(position);
			try {
				viewHolder.textView.setText("Status: " + doc.getValue4());
				viewHolder.textView2.setText("Doctor: " + signUp.getDoctorDetails(doc.getValue3()).send().getValue1());
				viewHolder.textView3.setText(presData.get(position));
			} catch (Exception e) {
				e.printStackTrace();
			}
//			viewHolder.cardView.setClickable(false);
		}

		@Override
		public int getItemCount() {
			return samples.size();
		}

		class ViewHolder extends RecyclerView.ViewHolder {

			private TextView textView, textView2, textView3, textView4;
			private CardView cardView;

			ViewHolder(View view) {
				super(view);
				textView = view.findViewById(R.id.nameView);
				textView2 = view.findViewById(R.id.nameView2);
				textView3 = view.findViewById(R.id.nameView3);
				textView4 = view.findViewById(R.id.nameView4);
				cardView = view.findViewById(R.id.nameCard);
			}
		}
	}
}
