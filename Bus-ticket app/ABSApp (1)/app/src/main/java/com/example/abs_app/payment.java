package com.example.abs_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.webkit.DownloadListener;
import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
public class payment extends AppCompatActivity {

    private WebView webView;
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);

        // Set up WebView clients
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // Add any custom handling for page finished
              //  Toast.makeText(payment.this, "finisheddd", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(payment.this, MapsActivity.class));
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                // Add any custom handling for progress changed
            }
        });

        // Set up DownloadListener for WebView
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                request.setMimeType(mimetype);
                String cookies = android.webkit.CookieManager.getInstance().getCookie(url);
                request.addRequestHeader("cookie", cookies);
                request.addRequestHeader("User-Agent", userAgent);
                request.setDescription("Downloading file...");
                request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimetype));
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(url, contentDisposition, mimetype));
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);
                Toast.makeText(getApplicationContext(), "Downloading File...", Toast.LENGTH_SHORT).show();
            }
        });

        // Load Chapa payment URL
        String chapaPaymentUrl = "https://checkout.chapa.co/checkout/web/payment/PL-QluvIDiFk1Qq"; // Replace with actual URL
        webView.loadUrl(chapaPaymentUrl);

        // Set up timer to periodically check payment status
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Add code to check payment status
            }
        }, 0, 300); // Check every 300 milliseconds
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cancel timer when activity is destroyed
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}