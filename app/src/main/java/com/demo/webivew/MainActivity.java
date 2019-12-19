package com.demo.webivew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView mWebview;

    @Override
    protected void onResume() {
        super.onResume();
        mWebview.loadUrl("https://www.baidu.com");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebview = findViewById(R.id.webview);


        mWebview.getSettings().setJavaScriptEnabled(true);//允许与js 交互
        mWebview.getSettings().setDefaultTextEncodingName("utf-8");//支持中文
        mWebview.getSettings().setDomStorageEnabled(true);//允许缓存、开启DOM（双重重定向白屏问题）
        mWebview.setWebViewClient(new WebViewClient() {
            //覆盖shouldOverrideUrlLoading 方法
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url == null) return false;
                try {
                    if (url.startsWith("http:") || url.startsWith("https:")) {

                        return true;
                    } else {//如果不是http和https就用系统浏览器打开
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    }
                } catch (Exception e) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                    return false;
                }
            }
        });
        mWebview.setWebChromeClient(new WebChromeClient());

    }

}
