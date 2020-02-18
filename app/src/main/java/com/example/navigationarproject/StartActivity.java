package com.example.navigationarproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

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
                  //  SingInButton.setText("XD");
                    SingInButton.setClickable(false);
                }
                else
                {
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
                if (editTextID.getText().toString().equals(""))
                {
                    //  SingInButton.setText("XD");
                    SingInButton.setClickable(false);
                }
                else
                {
                    // SingInButton.setText("XD2");
                    SingInButton.setClickable(true);
                }
            }
        });
    }
}
