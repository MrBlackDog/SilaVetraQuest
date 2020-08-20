package com.example.silavetra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StartActivity extends AppCompatActivity {

    //layout variables
    TextView textChoose;
    RadioGroup radioGroup;
    FloatingActionButton fab;
    //tribe settings
    String tribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);
        textChoose = findViewById(R.id.textChoose);
        fab = findViewById(R.id.floatingActionButton);
        radioGroup = findViewById(R.id.triberadioGroup);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case -1:
                    Toast.makeText(getApplicationContext(), "Ничего не выбрано",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.radioButtonСyberpunk:
                    /*Toast.makeText(getApplicationContext(), "Сyberpunk переключатель",
                            Toast.LENGTH_SHORT).show();*/
                    tribe = getResources().getString(R.string.cyberpunk);
                    break;
                case R.id.radioButtonEuphoria:
                    /*Toast.makeText(getApplicationContext(), "Euphoria переключатель",
                            Toast.LENGTH_SHORT).show();*/
                    tribe = getResources().getString(R.string.euphoria);
                    break;
                case R.id.radioButtonMasson:
                    /*Toast.makeText(getApplicationContext(), "Masson переключатель",
                            Toast.LENGTH_SHORT).show();*/
                    tribe = getResources().getString(R.string.masson);
                break;
                default:
                    break;
            }
        });
        fab.setOnClickListener(v -> {
            if(!tribe.equals(""))
            {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("Tribe",tribe);
                intent.putExtra("StageAct",1);
                startActivity(intent);
            }
        });
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
