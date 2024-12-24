package com.example.abs_app;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CancellationSignal cancellationSignal = null;
    private BiometricPrompt.AuthenticationCallback authenticationCallback;

    Handler h = new Handler();
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        authenticationCallback = new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(MainActivity.this, "authentication error: "+errString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(MainActivity.this, "authntication succces", Toast.LENGTH_SHORT).show();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(MainActivity.this, onboard1.class);
                        startActivity(i);

                    }
                },2000);
            }
        };



        checkBiometriucSupport();




                BiometricPrompt biometricPrompt = new BiometricPrompt.Builder(getApplicationContext())
                        .setTitle("title of")
                        .setSubtitle("sub mnm")
                        .setDescription("uses FP")
                        .setNegativeButton("cancel", getMainExecutor(), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "cancelleddddddddddddddddddddddd", Toast.LENGTH_SHORT).show();
                            }
                        }).build();
                biometricPrompt.authenticate(
                        getCancellationSignal(),
                        getMainExecutor(),
                        authenticationCallback
                );



    }
    private CancellationSignal getCancellationSignal() {
        cancellationSignal = new CancellationSignal();
        cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() {
            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "canccelled by the user", Toast.LENGTH_SHORT).show();
            }
        });
        return cancellationSignal;
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private Boolean checkBiometriucSupport(){
        KeyguardManager keyguardManager= (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        if (!keyguardManager.isDeviceSecure()){
            Toast.makeText(this, "not enabeld", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "permission not enabled", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)){
            return true;
        }
        else
            return true;
    }
}