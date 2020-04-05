package com.example.seventh_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class BaiduSearch extends AppCompatActivity {

    private EditText search_text;
    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu_search);
        web = findViewById(R.id.webview);
        web.setWebViewClient(new WebViewClient());
        MaterialButton uri_search_button = findViewById(R.id.uri_search_button);
        search_text = findViewById(R.id.search_text);
        search_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                for(int i = s.length(); i > 0; i --){
                    if(s.subSequence(i-1, i).toString().equals("\n"))
                        s.replace(i-1, i, "");
                }
            }
        });

        uri_search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSearch(search_text.getText().toString());
            }
        });
        search_text.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event != null && keyCode == KeyEvent.KEYCODE_ENTER){
                    doSearch(search_text.getText().toString());
                }
                return false;
            }
        });

    }

    private void doSearch(String word) {
        web.loadUrl("https://www.baidu.com/s?wd=" + word);
        search_text.setText("");
    }
}
