package com.bewareofraj.itec4550jokeoftheday;

import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import com.bewareofraj.itec4550jokeoftheday.util.RetrieveJokeTask;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	private TextView jokeTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		jokeTextView = (TextView) findViewById(R.id.jokeTextView);
		try {
			jokeTextView.setText(getRandomJoke());
		} catch (ExecutionException | InterruptedException | JSONException e) {
			e.printStackTrace();
		}
	}

	private String getRandomJoke() throws ExecutionException,
			InterruptedException, JSONException {
		String json = new RetrieveJokeTask()
				.execute("http://api.yomomma.info/").get();
		JSONObject obj = new JSONObject(json);
		return obj.getString("joke");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

}
