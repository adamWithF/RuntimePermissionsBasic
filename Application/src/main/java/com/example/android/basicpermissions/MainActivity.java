/*
* Copyright 2015 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.basicpermissions;

// import android.util.log;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.provider.Settings;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.snackbar.Snackbar;

import com.example.android.basicpermissions.MyApplication;

/**
 * Launcher Activity that demonstrates the use of runtime permissions for Android M.
 * This Activity requests permissions to access the camera
 * ({@link android.Manifest.permission#CAMERA})
 * when the 'Show Camera Preview' button is clicked to start  {@link CameraPreviewActivity} once
 * the permission has been granted.
 * <p>
 * First, the status of the Camera permission is checked using {@link
 * ActivityCompat#checkSelfPermission(Context, String)}
 * If it has not been granted ({@link PackageManager#PERMISSION_GRANTED}), it is requested by
 * calling
 * {@link ActivityCompat#requestPermissions(Activity, String[], int)}. The result of the request is
 * returned to the
 * {@link androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback}, which starts
 * {@link
 * CameraPreviewActivity} if the permission has been granted.
 * <p>
 * Note that there is no need to check the API level, the support library
 * already takes care of this. Similar helper methods for permissions are also available in
 * ({@link ActivityCompat},
 * {@link androidx.core.content.ContextCompat} and {@link androidx.fragment.app.Fragment}).
 */
public class MainActivity extends AppCompatActivity
        implements ActivityCompat.OnRequestPermissionsResultCallback {

    private static final int PERMISSION_REQUEST_CAMERA = 0;

    private View mLayout;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // mLayout = findViewById(R.id.main_layout);
        text = findViewById(R.id.text);
        text.setText(this.getDeviceSuperInfo());



        // Register a listener for the 'Show Camera Preview' button.
        // findViewById(R.id.button_open_camera).setOnClickListener(new View.OnClickListener() {
        //     @Override
        //     public void onClick(View view) {
                // showCameraPreview();
            // }
        // });
    }

private String getDeviceSuperInfo() {
  String s = "Debug-infos:";

    try {
        s += "\n OS Version: "      + System.getProperty("os.version")      + "(" + android.os.Build.VERSION.INCREMENTAL + ")";
        s += "\n OS API Level: "    + android.os.Build.VERSION.SDK_INT;
        s += "\n Device: "          + android.os.Build.DEVICE;
        s += "\n Model (and Product): " + android.os.Build.MODEL            + " ("+ android.os.Build.PRODUCT + ")";

        s += "\n RELEASE: "         + android.os.Build.VERSION.RELEASE;
        s += "\n BRAND: "           + android.os.Build.BRAND;
        s += "\n DISPLAY: "         + android.os.Build.DISPLAY;
        s += "\n CPU_ABI: "         + android.os.Build.CPU_ABI;
        s += "\n CPU_ABI2: "        + android.os.Build.CPU_ABI2;
        s += "\n UNKNOWN: "         + android.os.Build.UNKNOWN;
        s += "\n HARDWARE: "        + android.os.Build.HARDWARE;
        s += "\n Build ID: "        + android.os.Build.ID;
        s += "\n MANUFACTURER: "    + android.os.Build.MANUFACTURER;
        s += "\n SERIAL: "          + android.os.Build.SERIAL;
        s += "\n USER: "            + android.os.Build.USER;
        s += "\n HOST: "            + android.os.Build.HOST;
        s += "\n ANDROID ID: "      + Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID);
        s += "\n LOGGING ID: "      + Settings.Secure.getString(getContentResolver(),Settings.Secure.LOGGING_ID);

        TelephonyManager tm = (TelephonyManager) MyApplication.getAppContext().getSystemService(Context.TELEPHONY_SERVICE);

        try {
          s += "\n IMEI: "            + tm.getImei();

        } catch (Exception e) {
          s += "\n IMEI: "            + e.getMessage();
        }

        try {
          s += "\n PHONE NUMBER: "    + tm.getLine1Number();

        } catch (Exception e) {
          s += "\n PHONE NUMBER: "    + e.getMessage();
        }

        try {
          s += "\n DEVICE ID: "       + tm.getDeviceId();

        } catch (Exception e) {
          s += "\n DEVICE ID: "       + e.getMessage();
        }

        return s;

    } catch (Exception e) {
      return e.getMessage();
        // Log.e(TAG, "Error getting Device INFO");
    }
}
  //end getDeviceSuperInfo

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        // BEGIN_INCLUDE(onRequestPermissionsResult)
        // if (requestCode == PERMISSION_REQUEST_CAMERA) {
        //     // Request for camera permission.
        //     if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        //         // Permission has been granted. Start camera preview Activity.
        //         Snackbar.make(mLayout, R.string.camera_permission_granted,
        //                 Snackbar.LENGTH_SHORT)
        //                 .show();
        //         startCamera();
        //     } else {
        //         // Permission request was denied.
        //         Snackbar.make(mLayout, R.string.camera_permission_denied,
        //                 Snackbar.LENGTH_SHORT)
        //                 .show();
        //     }
        // }
        // END_INCLUDE(onRequestPermissionsResult)
    }

    // private void showCameraPreview() {
    //     // BEGIN_INCLUDE(startCamera)
    //     // Check if the Camera permission has been granted
    //     if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
    //             == PackageManager.PERMISSION_GRANTED) {
    //         // Permission is already available, start camera preview
    //         Snackbar.make(mLayout,
    //                 R.string.camera_permission_available,
    //                 Snackbar.LENGTH_SHORT).show();
    //         startCamera();
    //     } else {
    //         // Permission is missing and must be requested.
    //         requestCameraPermission();
    //     }
    //     // END_INCLUDE(startCamera)
    // }

    // /**
    //  * Requests the {@link android.Manifest.permission#CAMERA} permission.
    //  * If an additional rationale should be displayed, the user has to launch the request from
    //  * a SnackBar that includes additional information.
    //  */
    // private void requestCameraPermission() {
    //     // Permission has not been granted and must be requested.
    //     if (ActivityCompat.shouldShowRequestPermissionRationale(this,
    //             Manifest.permission.CAMERA)) {
    //         // Provide an additional rationale to the user if the permission was not granted
    //         // and the user would benefit from additional context for the use of the permission.
    //         // Display a SnackBar with cda button to request the missing permission.
    //         Snackbar.make(mLayout, R.string.camera_access_required,
    //                 Snackbar.LENGTH_INDEFINITE).setAction(R.string.ok, new View.OnClickListener() {
    //             @Override
    //             public void onClick(View view) {
    //                 // Request the permission
    //                 ActivityCompat.requestPermissions(MainActivity.this,
    //                         new String[]{Manifest.permission.CAMERA},
    //                         PERMISSION_REQUEST_CAMERA);
    //             }
    //         }).show();

    //     } else {
    //         Snackbar.make(mLayout, R.string.camera_unavailable, Snackbar.LENGTH_SHORT).show();
    //         // Request the permission. The result will be received in onRequestPermissionResult().
    //         ActivityCompat.requestPermissions(this,
    //                 new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
    //     }
    // }

    // private void startCamera() {
    //     Intent intent = new Intent(this, CameraPreviewActivity.class);
    //     startActivity(intent);
    // }

}
