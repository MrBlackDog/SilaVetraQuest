package com.example.silavetra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class QuestActivity extends AppCompatActivity {

    int StageAct;
    String tribe;
    String answer;
    String Rigthanswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);
        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            StageAct = arguments.getInt("StageAct");
            tribe = arguments.getString("Tribe");
        }
        TextView textView = findViewById(R.id.textViewQuestion);
        switch (StageAct) {
            case 1: {
                textView.setText(getResources().getText(R.string.Question1));
                Rigthanswer = getResources().getText(R.string.Answer1).toString();
                break;
            }
            case 2: {
                textView.setText(getResources().getText(R.string.Question2));
                Rigthanswer = getResources().getText(R.string.Answer2).toString();
                break;
            }
            case 3:
            {
                textView.setText("Победа!");
            }

        }

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            {
                if (answer.contentEquals(Rigthanswer)||answer.contentEquals(getResources().getString(R.string.Answer11))) {
                    StageAct++;
                    // Log.d("ArTack","Quest " + StageAct);
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("StageAct", StageAct);
                    intent.putExtra("Tribe", tribe);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Неверный ответ " + answer,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        EditText editText = findViewById(R.id.editTextTextMultiLine);
        editText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                answer = editText.getText().toString();
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

            }
        });

    }
}
