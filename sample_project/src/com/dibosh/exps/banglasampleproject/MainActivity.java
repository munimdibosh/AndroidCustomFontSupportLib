package com.dibosh.exps.banglasampleproject;

import com.dibosh.experiments.android.support.customfonthelper.AndroidCustomFontSupport;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class MainActivity extends Activity {

	WebView myBrowser;
	final String head = "<head><style>@font-face {font-family: 'myface';src: url('fonts/solaimanlipinormal.ttf');} body {font-family: 'myface'; } </style></head>";
    final String baseURL = "file:///android_asset/";
    final String bnFont= "solaimanlipinormal.ttf";
    final String titleFont= "Articulada.ttf";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//
		myBrowser =(WebView) this.findViewById(R.id.broswer);
		myBrowser.getSettings().setSupportZoom(true);
		myBrowser.getSettings().setBuiltInZoomControls(true);
		myBrowser.setWebViewClient(new WebViewClient());
		
		//WEB BROWSERS TAKE UTF-8 BY DEFAULT
		//SO IT SHOULD BE PROVIDED UNICODE BY DEFAULT.
		//IRRESPECTIVE OF PHONE MODEL OR ANDROID VERSION.
		//AS IN loadDataWithBaseURL YOU SPECIFY THE FORMAT AS UTF-8
		//SO JUST CONVERT YOUR PLAIN STRING TO UTF 
		String utfText_long= AndroidCustomFontSupport.getCorrectedBengaliFormatAsString(getString(R.string.bn_defn));
		//SHOW BANGLA IN WEBVIEW
		this.show(this.getStringInHTML(utfText_long));
		//SHOW BANGLA IN TEXTVIEW
		TextView tv=(TextView)findViewById(R.id.txtview);
		//FOR TEXTVIEW OR ANY OTHER PLAIN TEXT COMPONENT YOU NEED TO 
		//SEND THE METHOD YOUR SPECIFIED FONT(solaimanlipinormal.ttf)
		//.THE LIB TAKES CARE OF IF YOUR DEVICE SUPPORTS BANGLA OR NOT.
		tv.setText(AndroidCustomFontSupport.getCorrectedBengaliFormat(getString(R.string.bn_title),getFont(), 1.5f));
		//CUSTOM FONT SUPPORT
		TextView title=(TextView)findViewById(R.id.title);
		title.setText(AndroidCustomFontSupport.getStringRepresentationInCustomFont(this,getString(R.string.title),Typeface.createFromAsset(this.getAssets(), "fonts/"+this.titleFont),1.0f));
	}
	
	private Typeface getFont()
	{
		return Typeface.createFromAsset(this.getAssets(), "fonts/"+this.bnFont);
	}
	private String getStringInHTML(String text)
	{
		return "<html>" + head + "<body>" + text + "</body></html>";  
	}

	private void show(String htmlData)
	{
		myBrowser.loadDataWithBaseURL(baseURL, htmlData, "text/html", "UTF-8", null);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
