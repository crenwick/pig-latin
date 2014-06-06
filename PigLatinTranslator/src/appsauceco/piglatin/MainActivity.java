package appsauceco.piglatin;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
//	private AdView adView;

	public final static String EXTRA_MESSAGE = "renwick.play.piglatin.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// adBanner
//		adView = new AdView(this);
//		adView.setAdUnitId("ADIDHERE");
//		adView.setAdSize(AdSize.SMART_BANNER);
//		LinearLayout layout = (LinearLayout) findViewById(R.id.adView);
//		layout.addView(adView);
//		AdRequest adRequest = new AdRequest.Builder().addTestDevice("")
//				.addTestDevice("").build();
//		adView.loadAd(adRequest);

	}

	public void sendMessage(View view) {
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String message = editText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.more_apps:
			appSauce(null);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void appSauce(View v) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse("market://search?q=pub:App+Sauce+Co."));
		startActivity(i);
	}

//	@Override
//	protected void onPause() {
//		adView.pause();
//		super.onPause();
//	}
//
//	@Override
//	protected void onResume() {
//		super.onResume();
//		adView.resume();
//	}
//
//	@Override
//	protected void onDestroy() {
//		adView.destroy();
//		super.onDestroy();
//	}

}
