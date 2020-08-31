package com.github.kaism.watchlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddStockActivity extends AppCompatActivity {
	// bundle key constants
	public static final String SYMBOL = "com.github.kaism.watchlist.SYMBOL";
	public static final String LOW_PRICE = "com.github.kaism.watchlist.LOW_PRICE";
	public static final String HIGH_PRICE = "com.github.kaism.watchlist.HIGH_PRICE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_stock);

		setOnClickListeners();
	}

	private void setOnClickListeners() {
		// handle save stock
		findViewById(R.id.save_stock).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String symbol = getString((EditText) findViewById(R.id.symbol)).toUpperCase();
				int lowPrice = stringToPrice(getString((EditText) findViewById(R.id.lowPrice)));
				int highPrice = stringToPrice(getString((EditText) findViewById(R.id.highPrice)));

				// return data to main activity
				Intent replyIntent = new Intent();
				replyIntent.putExtra(SYMBOL, symbol);
				replyIntent.putExtra(LOW_PRICE, lowPrice);
				replyIntent.putExtra(HIGH_PRICE, highPrice);
				setResult(RESULT_OK, replyIntent);

				finish();
			}
		});
	}

	private String getString(EditText editText) {
		return editText.getText().toString();
	}

	private int stringToPrice(String string) {
		if (string != null){
			String[] arr = string.split("\\.");
			if (arr.length == 1) {
				return Integer.parseInt(arr[0].concat("00"));
			} else if (arr.length == 2) {
				return Integer.parseInt(arr[0].concat(arr[1]));
			}
		}
		return 0;
	}
}