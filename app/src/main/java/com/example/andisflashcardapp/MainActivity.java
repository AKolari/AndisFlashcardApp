package com.example.andisflashcardapp;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    boolean answers_Visible=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/* findViewById(R.id.FlashcardQuestion).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                findViewById(R.id.FlashcardQuestion).setVisibility(View.INVISIBLE);
                findViewById(R.id.FlashcardAnswer).setVisibility(View.VISIBLE);*/
/*
        findViewById(R.id.FlashcardQuestion).setOnClickListener((View v) -> {

            findViewById(R.id.FlashcardQuestion).setVisibility(View.INVISIBLE);
            findViewById(R.id.FlashcardAnswer).setVisibility(View.VISIBLE);
        });

        findViewById(R.id.FlashcardAnswer).setOnClickListener((View v) -> {

            findViewById(R.id.FlashcardQuestion).setVisibility(View.VISIBLE);
            findViewById(R.id.FlashcardAnswer).setVisibility(View.INVISIBLE);
        });
*/
        findViewById(R.id.FlashcardAnswer).setOnClickListener((View v) -> {

            //findViewById(R.id.FlashcardQuestion).setVisibility(View.INVISIBLE);
            findViewById(R.id.FlashcardAnswer).setBackground(getResources().getDrawable(R.drawable.answer_background_correct));
        });
        findViewById(R.id.FlashcardAnswer1).setOnClickListener((View v) -> {

            //findViewById(R.id.FlashcardQuestion).setVisibility(View.INVISIBLE);
            findViewById(R.id.FlashcardAnswer1).setBackground(getResources().getDrawable(R.drawable.answer_background_incorrect));
            findViewById(R.id.FlashcardAnswer).setBackground(getResources().getDrawable(R.drawable.answer_background_correct));
        });

        findViewById(R.id.FlashcardAnswer2).setOnClickListener((View v) -> {

            //findViewById(R.id.FlashcardQuestion).setVisibility(View.INVISIBLE);
            findViewById(R.id.FlashcardAnswer2).setBackground(getResources().getDrawable(R.drawable.answer_background_incorrect));
            findViewById(R.id.FlashcardAnswer).setBackground(getResources().getDrawable(R.drawable.answer_background_correct));
        });
        ((ImageView)findViewById(R.id.toggle_answers)).setImageResource(R.drawable.ic_iconmonstr_eye_5);


findViewById(R.id.toggle_answers).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if (answers_Visible) {
            findViewById(R.id.FlashcardAnswer).setVisibility(View.INVISIBLE);
            findViewById(R.id.FlashcardAnswer1).setVisibility(View.INVISIBLE);
            findViewById(R.id.FlashcardAnswer2).setVisibility(View.INVISIBLE);
            ((ImageView) findViewById(R.id.toggle_answers)).setImageDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.ic_iconmonstr_crossed_eye));
            answers_Visible=false;

        } else {
            findViewById(R.id.FlashcardAnswer).setVisibility(View.VISIBLE);
            findViewById(R.id.FlashcardAnswer1).setVisibility(View.VISIBLE);
            findViewById(R.id.FlashcardAnswer2).setVisibility(View.VISIBLE);
            ((ImageView) findViewById(R.id.toggle_answers)).setImageDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.ic_iconmonstr_eye_5));
            answers_Visible=true;
        }
    }
} );
            

























    }
}


/*
Notes:
view.invisible makes it invisible, but view.gone makes removes the view from the layout
AN intent is an object that has the intention to perform some action.
 Intent intent=
 new Intent(PlaylistActivity.this, SongActivity.class);
 intent.putExtra("some_key", "some_info_to_send");
 startActivity(intent)

 the .this is the context
 Allows you to go from playlist activity to song activity
 Should be placed in starting destination, so playlist activity

//in SongActivity
protected void onCreate(Bundle savedInstanceState)  {
    String data= getIntent(). getStringExtra("some_key);
    ...
    }   //INclude Null check

    Allows for the transfer of data between activities











 */