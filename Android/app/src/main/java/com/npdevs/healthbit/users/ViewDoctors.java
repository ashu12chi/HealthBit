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
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple4;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ViewDoctors extends AppCompatActivity {

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

	private List<Tuple4<String, String, String, String>> msampleItem = new ArrayList<>();
	private List<String> docAddress = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_doctors);
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

		signUp = SignUp.load(CONTRACT_ADDRESS, web3j, credentials, GAS_PRICE, GAS_LIMIT);
		processor = Processor.load(PROCESSOR_ADDRESS, web3j, credentials, GAS_PRICE, GAS_LIMIT);

		int numberOfDoctor = -1;

		try {
			numberOfDoctor = signUp.doctorsCount().send().intValue();
			for (int i = 0; i < numberOfDoctor; i++) {
				String add = signUp.doctors_list(BigInteger.valueOf(i)).send();
				Tuple4<String, String, String, String> doctor = signUp.getDoctorDetails(add).send();
				docAddress.add(add);
				msampleItem.add(doctor);
			}

			recyclerView = findViewById(R.id.recycler_view);
			recyclerView.setHasFixedSize(true);
			RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ViewDoctors.this);
			recyclerView.setLayoutManager(layoutManager);
			RecyclerView.Adapter adapter = new MainAdapter(msampleItem);
			recyclerView.setAdapter(adapter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (numberOfDoctor == -1) {
			Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show();
		}
	}

	public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

		private List<Tuple4<String, String, String, String>> samples;

		MainAdapter(List<Tuple4<String, String, String, String>> samples) {
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
			Tuple4<String, String, String, String> doc = samples.get(position);
			viewHolder.textView.setText("Name: " + doc.getValue1());
			viewHolder.textView2.setText("Hospital: " + doc.getValue2());
			viewHolder.textView3.setText("Opens: " + doc.getValue3());
			viewHolder.textView4.setText("Closes: " + doc.getValue4());
			viewHolder.cardView.setOnClickListener(v -> {
				Toast.makeText(ViewDoctors.this, "clicked", Toast.LENGTH_SHORT).show();
				try {
					TransactionReceipt tr = processor.requestDoctor(docAddress.get(position), BigInteger.valueOf(60)).send();
					if (tr.isStatusOK())
						Toast.makeText(ViewDoctors.this, "success", Toast.LENGTH_LONG).show();
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(ViewDoctors.this, "failure", Toast.LENGTH_LONG).show();
				}
			});
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
