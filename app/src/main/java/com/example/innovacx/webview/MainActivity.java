package com.example.innovacx.webview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity  {

    JavaScriptInterface JSInterface;
    StringGetter stringGetter;
    String key;
    EditText urlField;
    ImageButton goButton;
    WebView webView;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


      key="customers_mysql.php";


        urlField = (EditText)findViewById(R.id.url_field);
        goButton = (ImageButton)findViewById(R.id.go_btn);
         url = "file:///android_asset/Index.html";
        webView =(WebView)findViewById(R.id.webView);
        webView.setWebViewClient(new MyBrowser());


        JSInterface = new JavaScriptInterface(this);
        stringGetter = new StringGetter(this);
       // webView.addJavascriptInterface(JSInterface, "JSInterface");

        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);
        webView.addJavascriptInterface(new StringGetter(this), "AndroidFunction");

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* String url = urlField.getText().toString();*/
               /* webView.getSettings().setLoadsImagesAutomatically(true);

                webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);*/
               /* webView.loadUrl(url);
*/
            }
        });



    }

    public class StringGetter {
        Context jContext;

        StringGetter(Context context){
            jContext = context;
        }
        @JavascriptInterface
        public String getString() {
            return key;

        }
    }



    private class MyBrowser extends WebViewClient {


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }




    }



    public class JavaScriptInterface{
        Context jContext;

            JavaScriptInterface(Context cont){
                jContext = cont;
            }

        @JavascriptInterface
        public void AlertDisplay(){
            Toast.makeText(MainActivity.this ,"Javascript interfacing success",Toast.LENGTH_LONG).show();
        }


    }
}
