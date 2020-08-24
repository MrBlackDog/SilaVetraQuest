package com.example.silavetra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class StartActivity extends AppCompatActivity {

    String SecretCode;
    String ID;

    private final int REQUEST_CODE = 100;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        CheckPermission();

        EditText editTextID = findViewById(R.id.editIdText);
        EditText editTextCode = findViewById(R.id.editCodeText);

        Button SingInButton = findViewById(R.id.button);
        SingInButton.setClickable(false);
        SingInButton.setOnClickListener(view -> {
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
            Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(StartActivity.this, QuestActivity.class);
            startActivity(intent);
            Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        });

        editTextID.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                if (editTextID.getText().toString().equals(""))
                {
                    ID = "";
                    //  SingInButton.setText("XD");
                    SingInButton.setClickable(false);
                }
                else
                {
                    ID = editTextID.getText().toString();
                    // SingInButton.setText("XD2");
                    SingInButton.setClickable(true);
                }
            }
        });

        editTextCode.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                if (editTextCode.getText().toString().equals(""))
                {
                    SecretCode = "";
                    //  SingInButton.setText("XD");
                    SingInButton.setClickable(false);
                }
                else
                {
                    SecretCode = editTextCode.getText().toString();
                    // SingInButton.setText("XD2");
                    SingInButton.setClickable(true);
                }
            }

        });
    }

    private void CheckPermission() {
        if ( (ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(
                        this, Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(
                        this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED)) {
            // You can use the API that requires the permission.
            // performAction(...);
        } //else if (shouldShowRequestPermissionRationale(...)) {
        // In an educational UI, explain to the user why your app requires this
        // permission for a specific feature to behave as expected. In this UI,
        // include a "cancel" or "no thanks" button that allows the user to
        // continue using your app without granting the permission.
        // showInContextUI(...);}
        else {
            // You can directly ask for the permission.
            requestPermissions(
                    new String[] { Manifest.permission.CAMERA ,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION },
                    REQUEST_CODE);
        }
    }

    /**
     * lifecycle handler
     */
    //отрабатывает,когда мы возвращаемся из другого активити
    @Override
    protected void onRestart() {
        super.onRestart();
      //  loadText();
    }

    @Override
    protected void onResume() {
        super.onResume();
      //  loadText();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
      //  saveText();
    }
}
