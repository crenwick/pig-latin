package appsauceco.piglatin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

@SuppressLint("DefaultLocale")
public class DisplayMessageActivity extends Activity {
	private AdView adView;
	String stampMessage;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_message);

		Intent intent = getIntent();
		String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

		TextView textView = new TextView(this);
		String latin = pigLatin(message);
		textView = (TextView) findViewById(R.id.textView1);
		textView.setText(latin);
		stampMessage = latin;

		// adBanner
		adView = new AdView(this);
		adView.setAdUnitId("ca-app-pub-7649747947968832/7988359109");
		adView.setAdSize(AdSize.SMART_BANNER);
		LinearLayout layout = (LinearLayout) findViewById(R.id.adView);
		layout.addView(adView);
		AdRequest adRequest = new AdRequest.Builder().addTestDevice("6b0284de")
				.addTestDevice("c0808a004e5b92f").build();
		adView.loadAd(adRequest);

	}

	public void stamp(View ImageView) {
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("text/plain");
		i.putExtra(Intent.EXTRA_TEXT, stampMessage);
		startActivity(i);
	}

	/**
	 * Method to translate the phrase word by word
	 * 
	 * @param s
	 *            the sentence in English
	 * @return the pig latin verse
	 */
	private static String pigLatin(String s) {
		String latin = "";
		int i = 0;
		while (i < s.length()) {
			// take care of punctuation
			while (i < s.length() && !isLetter(s.charAt(i))) {
				latin = latin + s.charAt(i);
				i++;
			}

			// if there ant any words left, stop.
			if (i >= s.length())
				break;

			// otherwise we're at the beginning of the word
			int begin = i;
			while (i < s.length() && isLetter(s.charAt(i))) {
				i++;
			}

			// now we're at the end of a word, so translate it
			int end = i;
			latin = latin + pigWord(s.substring(begin, end));
		}
		return latin;
	}

	/**
	 * Method to test whether a character is a letter or not
	 * 
	 * @param c
	 *            the character to test
	 * @return True if it is a letter
	 */
	private static boolean isLetter(char c) {
		return ((c >= 'A' && c <= 'z') || (c >= 'a' && c <= 'z'));
	}

	/**
	 * method to translate one word into pig latin
	 * 
	 * @param word
	 *            the word in english
	 * @return the pig latin version
	 */
	private static String pigWord(String word) {
		int split = firstVowel(word);
		return word.substring(split) + word.substring(0, split) + "ay";
	}

	/**
	 * method to find the index of the first vowel in a word.
	 * 
	 * @param word
	 *            the word to search
	 * @return the index of the first vowel
	 */
	private static int firstVowel(String word) {
		word = word.toLowerCase();
		for (int i = 0; i < word.length(); i++)
			if (word.charAt(i) == 'a' || word.charAt(i) == 'e'
					|| word.charAt(i) == 'i' || word.charAt(i) == 'o'
					|| word.charAt(i) == 'u')
				return i;
		return 0;
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
		String url = "https://play.google.com/store/apps/developer?id=App+Sauce+Co.";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}

	@Override
	protected void onPause() {
		adView.pause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		adView.resume();
	}

	@Override
	protected void onDestroy() {
		adView.destroy();
		super.onDestroy();
	}

}