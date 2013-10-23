package ysw.schoolsos;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class tip extends SlidingMenu {
	private WebView webview1;
	ConnectivityManager cManager;
	NetworkInfo mobile;
	NetworkInfo wifi;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tip);
		getSupportActionBar().setTitle(R.string.tip);
		webview1 = (WebView) findViewById(R.id.webview1);
		webview1.loadUrl("http://m.safe182.go.kr/mob/safe/safeInfoSchool.do");
		webview1.setWebViewClient(new WebViewClient());
		WebSettings set = webview1.getSettings();
		set.setJavaScriptEnabled(true);
		set.setBuiltInZoomControls(true);
		webview1.setInitialScale(100);

	}
}