package com.example.quiz_app;

import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView questionTextView;
    TextView totalQuestionTextView;
    Button ansA,ansB,ansC,ansD;
    Button submit;

    int score=0;
    int totalQuestions=QuestionAnswer.question.length;
    int currentQuestionIndex=0;
    String selectedAnswer="";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionTextView=findViewById(R.id.total_question);
        questionTextView=findViewById(R.id.question);
        ansA=findViewById(R.id.ans_a);
        ansB=findViewById(R.id.ans_b);
        ansC=findViewById(R.id.ans_c);
        ansD=findViewById(R.id.ans_d);
        submit=findViewById(R.id.submit);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submit.setOnClickListener(this);

        totalQuestionTextView.setText("Total Question: "+totalQuestions);

        loadNewQuestion();
    }

    private void loadNewQuestion(){
        if(currentQuestionIndex==totalQuestions){
            finishQuiz();
            return;
        }
        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);

        selectedAnswer="";
    }

    private void finishQuiz(){
        String passStatus;
        if(score>=totalQuestions*0.6){
            passStatus="Qualified";
        }else{
            passStatus="Not Qualified";
        }
        new AlertDialog.Builder(this).setTitle(passStatus).setMessage("Your score is: "+score+" out of "+totalQuestions)
                .setPositiveButton("Restart",((dialog,i)->restartQuiz()))
                .setCancelable(false)
                .show();
    }
    private void restartQuiz(){
        score=0;
        currentQuestionIndex=0;
        loadNewQuestion();
    }
    @Override
    public void onClick(View view){
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton=(Button) view;

        if(clickedButton.getId()==R.id.submit){
            if(!selectedAnswer.isEmpty()){
                if(selectedAnswer.equals(QuestionAnswer.answers[currentQuestionIndex])){
                    score++;
                }else{
                    clickedButton.setBackgroundColor(Color.RED);
                }
                currentQuestionIndex++;
                loadNewQuestion();
            }else{
                System.out.println("Didn't select an option");
            }
        }else{
            selectedAnswer=clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.YELLOW);
        }
    }

}



