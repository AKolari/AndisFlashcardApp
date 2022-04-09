package com.example.andisflashcardapp;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.IntentCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
   void defaultCards(){
       findViewById(R.id.FlashcardAnswer).setBackground(getResources().getDrawable(R.drawable.answer_background_neutral));
       findViewById(R.id.FlashcardAnswer1).setBackground(getResources().getDrawable(R.drawable.answer_background_neutral));
       findViewById(R.id.FlashcardAnswer2).setBackground(getResources().getDrawable(R.drawable.answer_background_neutral));

   }
     void startTimer() {
        time.cancel();
        time.start();
    }
   boolean answers_Visible=true;
   boolean options_visible=false;
   boolean answered=false;
FlashcardDatabase database;
List<Flashcard> cards;
    int cardIndex=0;
    CountDownTimer time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final Animation leftOutAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.left_out);
        final Animation rightInAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.right_in_bounce_back);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database=new FlashcardDatabase(getApplicationContext());

        cards=database.getAllCards();

 time= new CountDownTimer(16000, 1000) {
            public void onTick(long millisUntilFinished) {
                ((TextView) findViewById(R.id.timer)).setText("" + millisUntilFinished / 1000);
                findViewById(R.id.timer).setVisibility(View.VISIBLE);
                if(millisUntilFinished<10000){
                    ((TextView) findViewById(R.id.timer)).setTextColor(getResources().getColor(R.color.red));
                }
                if(answered==true){
                    findViewById(R.id.timer).setVisibility(View.INVISIBLE);
                }
            }

            public void onFinish() {
                findViewById(R.id.timer).setVisibility(View.INVISIBLE);
                if(answered==false){
                findViewById(R.id.FlashcardAnswer2).setBackground(getResources().getDrawable(R.drawable.answer_background_incorrect));
                findViewById(R.id.FlashcardAnswer).setBackground(getResources().getDrawable(R.drawable.answer_background_correct));
                findViewById(R.id.FlashcardAnswer1).setBackground(getResources().getDrawable(R.drawable.answer_background_incorrect));
            }}
        };
        startTimer();
findViewById(R.id.flashcard_delete).setVisibility(View.INVISIBLE);
        findViewById(R.id.flashcard_add).setVisibility(View.INVISIBLE);
        findViewById(R.id.flashcard_edit).setVisibility(View.INVISIBLE);
if(cards.size()!=0) {
    ((TextView) findViewById(R.id.FlashcardQuestion)).setText(cards.get(cardIndex).getQuestion());
    ((TextView) findViewById(R.id.FlashcardAnswer)).setText(cards.get(cardIndex).getAnswer());
    ((TextView) findViewById(R.id.FlashcardAnswer1)).setText(cards.get(cardIndex).getWrongAnswer1());
    ((TextView) findViewById(R.id.FlashcardAnswer2)).setText(cards.get(cardIndex).getWrongAnswer2());
}

        findViewById(R.id.FlashcardAnswer).setOnClickListener((View v) -> {
            answered=true;
            //findViewById(R.id.FlashcardQuestion).setVisibility(View.INVISIBLE);
            findViewById(R.id.FlashcardAnswer).setBackground(getResources().getDrawable(R.drawable.answer_background_correct));
            /*new ParticleSystem(MainActivity.this, 100, R.drawable.confetti, 3000)
                    .setSpeedRange(0.2f, 0.5f)
                    .oneShot(findViewById(R.id.FlashcardAnswer), 100);*/
            

        });
        findViewById(R.id.FlashcardAnswer1).setOnClickListener((View v) -> {
answered=true;
            //findViewById(R.id.FlashcardQuestion).setVisibility(View.INVISIBLE);
            findViewById(R.id.FlashcardAnswer1).setBackground(getResources().getDrawable(R.drawable.answer_background_incorrect));
            findViewById(R.id.FlashcardAnswer).setBackground(getResources().getDrawable(R.drawable.answer_background_correct));
        });

        findViewById(R.id.FlashcardAnswer2).setOnClickListener((View v) -> {
answered=true;
            //findViewById(R.id.FlashcardQuestion).setVisibility(View.INVISIBLE);
            findViewById(R.id.FlashcardAnswer2).setBackground(getResources().getDrawable(R.drawable.answer_background_incorrect));
            findViewById(R.id.FlashcardAnswer).setBackground(getResources().getDrawable(R.drawable.answer_background_correct));
        });
        ((ImageView)findViewById(R.id.toggle_answers)).setImageResource(R.drawable.ic_iconmonstr_eye_5);


findViewById(R.id.toggle_answers).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if (answers_Visible) {
            defaultCards();
            findViewById(R.id.FlashcardAnswer).setVisibility(View.INVISIBLE);
            findViewById(R.id.FlashcardAnswer1).setVisibility(View.INVISIBLE);
            findViewById(R.id.FlashcardAnswer2).setVisibility(View.INVISIBLE);
            ((ImageView) findViewById(R.id.toggle_answers)).setImageDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.ic_iconmonstr_crossed_eye));
            answers_Visible=false;

        } else {
            defaultCards();
            findViewById(R.id.FlashcardAnswer).setVisibility(View.VISIBLE);
            findViewById(R.id.FlashcardAnswer1).setVisibility(View.VISIBLE);
            findViewById(R.id.FlashcardAnswer2).setVisibility(View.VISIBLE);
            ((ImageView) findViewById(R.id.toggle_answers)).setImageDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.ic_iconmonstr_eye_5));
            answers_Visible=true;
        }
    }
} );

        findViewById(R.id.flashcard_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);

            }
        });
        findViewById(R.id.flashcard_edit).setOnClickListener((View v) -> {



            String question= ((TextView) findViewById(R.id.FlashcardQuestion)).getText().toString();
            String correct_Answer= ((TextView) findViewById(R.id.FlashcardAnswer)).getText().toString();
            String incorrect_Answer1= ((TextView) findViewById(R.id.FlashcardAnswer1)).getText().toString();
            String incorrect_Answer2= ((TextView) findViewById(R.id.FlashcardAnswer2)).getText().toString();

            Intent current =new Intent(MainActivity.this, AddCardActivity.class);
            current.putExtra("Question",question );
            current.putExtra("Answer",correct_Answer );
            current.putExtra("Incorrect1",incorrect_Answer1 );
            current.putExtra("Incorrect2",incorrect_Answer2 );

            MainActivity.this.startActivityForResult(current, 200);
        });


        findViewById(R.id.flashcard_delete).setOnClickListener((View v) -> {
   database.deleteCard(((TextView) findViewById(R.id.FlashcardQuestion)).getText().toString());
   cards=database.getAllCards();
   cardIndex--;
            if(cardIndex<=-1){
                cardIndex=cards.size()-1;
            }

            if(cards!=null && cards.size()>0){
                ((TextView)findViewById(R.id.FlashcardQuestion)).setText(cards.get(cardIndex).getQuestion());
                ((TextView)findViewById(R.id.FlashcardAnswer)).setText(cards.get(cardIndex).getAnswer());
                ((TextView)findViewById(R.id.FlashcardAnswer1)).setText(cards.get(cardIndex).getWrongAnswer1());
                ((TextView)findViewById(R.id.FlashcardAnswer2)).setText(cards.get(cardIndex).getWrongAnswer2());
            }else{
                ((TextView)findViewById(R.id.FlashcardQuestion)).setText("QUESTION");
                ((TextView)findViewById(R.id.FlashcardAnswer)).setText("ANSWER");
                ((TextView)findViewById(R.id.FlashcardAnswer1)).setText("WRONG");
                ((TextView)findViewById(R.id.FlashcardAnswer2)).setText("WRONG");

            }
            });







        findViewById(R.id.flashcard_next).setOnClickListener((View v) -> {
            answered=false;
            findViewById(R.id.FlashcardQuestion).startAnimation(leftOutAnim);
             startTimer();


        });




findViewById(R.id.flashcard_options).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if (options_visible) {
            defaultCards();
            findViewById(R.id.flashcard_edit).setVisibility(View.INVISIBLE);
            findViewById(R.id.flashcard_add).setVisibility(View.INVISIBLE);
            findViewById(R.id.flashcard_delete).setVisibility(View.INVISIBLE);
           options_visible=false;

        } else {
            defaultCards();
            findViewById(R.id.flashcard_edit).setVisibility(View.VISIBLE);
            findViewById(R.id.flashcard_add).setVisibility(View.VISIBLE);
            findViewById(R.id.flashcard_delete).setVisibility(View.VISIBLE);
           options_visible=true;
        }

    }
});


        leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // this method is called when the animation first starts
                findViewById(R.id.FlashcardAnswer).startAnimation(leftOutAnim);
                findViewById(R.id.FlashcardAnswer1).startAnimation(leftOutAnim);
                findViewById(R.id.FlashcardAnswer2).startAnimation(leftOutAnim);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // this method is called when the animation is finished playing
                defaultCards();
                cardIndex++;
                if(cardIndex==cards.size()){
                    cardIndex=0;
                }

                if(cards!=null && cards.size()>0){
                    ((TextView)findViewById(R.id.FlashcardQuestion)).setText(cards.get(cardIndex).getQuestion());
                    ((TextView)findViewById(R.id.FlashcardAnswer)).setText(cards.get(cardIndex).getAnswer());
                    ((TextView)findViewById(R.id.FlashcardAnswer1)).setText(cards.get(cardIndex).getWrongAnswer1());
                    ((TextView)findViewById(R.id.FlashcardAnswer2)).setText(cards.get(cardIndex).getWrongAnswer2());
                }
                findViewById(R.id.FlashcardQuestion).startAnimation(rightInAnim);
                findViewById(R.id.FlashcardAnswer). startAnimation(rightInAnim);
                findViewById(R.id.FlashcardAnswer1).startAnimation(rightInAnim);
                findViewById(R.id.FlashcardAnswer2).startAnimation(rightInAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // we don't need to worry about this method
            }
        });





    }









   // @SuppressLint("MissingSuperCall")   Errors tells me to call super. I don't know what that means, but it still works without it
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK) {
            String question = data.getExtras().getString("Question");
            String correct_Answer = data.getExtras().getString("Answer");
            String incorrect_Answer1 = data.getExtras().getString("Incorrect1");
            String incorrect_Answer2 = data.getExtras().getString("Incorrect2");
            ((TextView) findViewById(R.id.FlashcardQuestion)).setText(question);
            ((TextView) findViewById(R.id.FlashcardAnswer)).setText(correct_Answer);
            ((TextView) findViewById(R.id.FlashcardAnswer1)).setText(incorrect_Answer1);
            ((TextView) findViewById(R.id.FlashcardAnswer2)).setText(incorrect_Answer2);


            if (requestCode == 100) {

                database.insertCard(new Flashcard(question, correct_Answer, incorrect_Answer1, incorrect_Answer2));
                cards = database.getAllCards();
                cardIndex = -1;
            } else if (requestCode == 200) {
                cards.get(cardIndex).setQuestion(question);
                cards.get(cardIndex).setAnswer(correct_Answer);
                cards.get(cardIndex).setWrongAnswer1(incorrect_Answer1);
                cards.get(cardIndex).setWrongAnswer2(incorrect_Answer2);

                database.updateCard(cards.get(cardIndex));
            }

        }
        defaultCards();

    }
}




/*
Notes March 12th:
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
    }   //Include Null check

    Allows for the transfer of data between activities



Notes March 19th:







 */




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