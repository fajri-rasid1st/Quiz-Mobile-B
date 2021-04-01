package com.example.quizmobileb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

@SuppressLint("QueryPermissionsNeeded")
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbarHome = findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbarHome);

        Button btnProfile = findViewById(R.id.btn_profile);
        btnProfile.setOnClickListener(this);

        Button btnMap = findViewById(R.id.btn_map);
        btnMap.setOnClickListener(this);

        Button btnMessage = findViewById(R.id.btn_message);
        btnMessage.setOnClickListener(this);

        Button btnExit = findViewById(R.id.btn_exit);
        btnExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btn_profile) {
            moveToProfile();
        } else if (id == R.id.btn_map) {
            showMap();
        } else if (id == R.id.btn_message) {
            dialPhoneNumber();
        } else if (id == R.id.btn_exit) {
            showExitAlertDialog();
        }
    }

    // move to profile activity
    private void moveToProfile() {
        Intent profileIntent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(profileIntent);
    }

    // to show map
    private void showMap() {
        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        mapIntent.setData(Uri.parse("geo:-5.054178,119.542379?z=20"));
        if (mapIntent.resolveActivity(getPackageManager()) != null) startActivity(mapIntent);
    }

    // to dial phone number
    private void dialPhoneNumber() {
        Intent intentPhone = new Intent(Intent.ACTION_DIAL);
        intentPhone.setData(Uri.parse("tel:" + "+6282290380510"));
        if (intentPhone.resolveActivity(getPackageManager()) != null) startActivity(intentPhone);
    }

    // when back button on device is pressed
    @Override
    public void onBackPressed() {
        showExitAlertDialog();
    }

    // to show alert dialog
    private void showExitAlertDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Konfirmasi Keluar");
        alert.setMessage("Yakin ki mau keluar?");
        alert.setPositiveButton("IYE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                System.exit(0);
            }
        });
        alert.setNegativeButton("NDAJI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        alert.create();
        alert.show();
    }
}