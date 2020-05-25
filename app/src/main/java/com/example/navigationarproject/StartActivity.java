package com.example.navigationarproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class StartActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    String SecretCode;
    String ID;

    EditText editTextID;
    EditText editTextCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_start);

        editTextID = findViewById(R.id.editIdText);
        editTextCode = findViewById(R.id.editCodeText);

        Button SingInButton = findViewById(R.id.button);

        if (savedInstanceState != null) {
            // Restore value of members from saved state
            editTextID.setText( savedInstanceState.getString("ID"));
            editTextCode.setText( savedInstanceState.getString("Code"));
        }

        SingInButton.setOnClickListener(view -> {
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);

            Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });
        SingInButton.setClickable(false);

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
                ID = editTextID.getText().toString();

                if ( editTextID.getText().toString().equals(""))
                {
                    ID = "";
                  //  SingInButton.setText("XD");
                    SingInButton.setClickable(false);
                }
                else if(!editTextCode.getText().toString().equals(""))
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
                SecretCode = editTextCode.getText().toString();
                if (editTextCode.getText().toString().equals(""))
                {
                    SecretCode = "";
                    //  SingInButton.setText("XD");
                    SingInButton.setClickable(false);
                }
                else if(!editTextID.getText().toString().equals(""))
                {
                    // SingInButton.setText("XD2");
                    SingInButton.setClickable(true);
                }
            }

        });

       // if(sharedPreferences.contains("ID") && sharedPreferences.contains("Code"))
      //  {
           // loadText();
       // }

    }

    /**
     * временно не нужно
     */
    void saveText() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ID", editTextID.getText().toString());
        editor.putString("Code", editTextCode.getText().toString());
        editor.apply();
        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
    }
    /**
     * временно не нужно
     */
    void loadText() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        if(sharedPreferences.contains("ID") && sharedPreferences.contains("Code"))
        {
            ID = sharedPreferences.getString("ID", "NET");
            SecretCode = sharedPreferences.getString("Code", "NEMA");
            editTextID.setText(ID);
            editTextCode.setText(SecretCode);
            Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * временно не нужно
     */
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        SecretCode = savedInstanceState.getString("Code");
        ID = savedInstanceState.getString("ID");

        Log.d(" Id restored, ID= ",ID);
        Log.d("Code restored, Code= ",SecretCode);
        editTextID.setText(savedInstanceState.getString("ID"));
        editTextCode.setText(savedInstanceState.getString("Code"));
        Log.e("Id= ",editTextID.getText().toString());
        Log.e("Code= ",editTextCode.getText().toString());
    }

    //сохраняем тут свои данные,если приложение не было закрыто полностью,а
    //только свернуто
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Code", SecretCode);
        outState.putString("ID", ID);
        Log.d("Id saved, ID= ",ID);
        Log.d("Code saved, Code= ",SecretCode);
        super.onSaveInstanceState(outState);
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
