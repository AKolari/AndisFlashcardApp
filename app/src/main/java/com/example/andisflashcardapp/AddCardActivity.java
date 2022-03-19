package com.example.andisflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        findViewById(R.id.flashcard_add_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//ALTERNATE: findViewById(R.id.flashcard_add_cancel).setOnClickListener(v -> finish());









                findViewById(R.id.flashcard_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String question= ((EditText) findViewById(R.id.editQuestion)).getText().toString();
                String correct_Answer= ((EditText) findViewById(R.id.editAnswer)).getText().toString();
                String incorrect_Answer1= ((EditText) findViewById(R.id.editAnswer1)).getText().toString();
                String incorrect_Answer2= ((EditText) findViewById(R.id.editAnswer2)).getText().toString();


                Intent data =new Intent();
                data.putExtra("Question",question );
                data.putExtra("Answer",correct_Answer );
                data.putExtra("Incorrect1",incorrect_Answer1 );
                data.putExtra("Incorrect2",incorrect_Answer2 );
                setResult(RESULT_OK, data);
                finish();
            }
        });




































    }

}