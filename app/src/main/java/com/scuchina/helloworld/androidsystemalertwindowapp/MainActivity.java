package com.scuchina.helloworld.androidsystemalertwindowapp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_OVERLAY_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.i("TAG", "onActivityResult called");
        if (requestCode == REQUEST_OVERLAY_CODE) {
            if (Settings.canDrawOverlays(this)) {
                showNextToast();
            }
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.bt_permission) {
            if (checkDrawOverlayPermission()) {
                showNextToast();
            };
        }
    }

    private void showNextToast() {
        Toast.makeText(this, R.string.next, Toast.LENGTH_SHORT).show();
    }

    private boolean checkDrawOverlayPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, REQUEST_OVERLAY_CODE);
                return false;
            }
        }
        return true;
    }
}
