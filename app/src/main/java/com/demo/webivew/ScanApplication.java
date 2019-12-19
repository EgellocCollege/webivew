package com.demo.webivew;

import android.app.Application;
import android.os.Build;
import android.webkit.WebView;

public class ScanApplication extends Application {
    @Override
    public void onCreate()
    {
        super.onCreate();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
        {
            try
            {
                WebView.setDataDirectorySuffix(Application.getProcessName());
            }
            catch (Exception ignored)
            {

            }
        }

    }
}
