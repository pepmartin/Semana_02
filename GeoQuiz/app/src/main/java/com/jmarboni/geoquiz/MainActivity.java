package com.jmarboni.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnYes, btnNo, btnPrevious, btnNext;
    TextView txtQuestion;
    int actualPosition;
    ArrayList<Question> questions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnYes = findViewById(R.id.btnYes);
        btnNo = findViewById(R.id.btnNo);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);

        txtQuestion = findViewById(R.id.txtView);
        actualPosition = 0;

        questions = new ArrayList<>();
        loadQuestion();

        //Show Text of Question Actual
        showActualQuestion();

        //txtQuestion.setText(questions.get(actualPosition).getName());

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyResponse(true);
            }
        });

        //As we're intro a Inner class(View.OnClickListener), Then call MainActivity
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyResponse(false);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualPosition += 1;
                if(actualPosition == questions.size()) {
                    actualPosition = 0;
                }
                showActualQuestion();
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualPosition -= 1;
                if(actualPosition == -1) {
                    actualPosition = questions.size()-1;
                }
                showActualQuestion();
                //txtQuestion.setText(questions.get(actualPosition).getName());
            }
        });
    }

    private void verifyResponse(boolean response) {
        Question actualQuestion = questions.get(actualPosition);
        if (response == actualQuestion.isResponse()) {
            Toast.makeText(MainActivity.this,
                    getString(R.string.answer_correct)
                    ,Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this,
                    getString(R.string.answer_incorrect)
                    ,Toast.LENGTH_SHORT).show();
        }
    }

    private void showActualQuestion() {
        txtQuestion.setText(questions.get(actualPosition).getName());
    }

    private void loadQuestion() {
        Question questionPeru =
                new Question(getString(R.string.peru_question),true);
        questions.add(questionPeru);

        Question questionChile =
                new Question(getString(R.string.chile_question),false);
        questions.add(questionChile);

        Question questionColombia =
                new Question(getString(R.string.colombia_question),true);
        questions.add(questionColombia);
    }
}
