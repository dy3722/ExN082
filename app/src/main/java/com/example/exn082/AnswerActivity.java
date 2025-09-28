package com.example.exn082;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AnswerActivity extends AppCompatActivity {
    private final int RESULT_OK = Activity.RESULT_OK;
    private Intent gi;
    private double a,b,c,inSqr,x,x1,x2;
    private ImageView iv;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        iv = findViewById(R.id.iv);
        tv = findViewById(R.id.tv);

        gi = getIntent();

        a = gi.getDoubleExtra("a",1);
        b = gi.getDoubleExtra("b",0);
        c = gi.getDoubleExtra("c",0);

        inSqr = b*b-4*a*c;

        if (inSqr < 0 && a > 0) // smile no answer
        {
            iv.setImageResource(R.drawable.smile_no_ans);
            tv.setText("No answer");
            gi.putExtra("no","No answer");
        }
        else if (inSqr == 0 && a > 0) // smile one answer
        {
            iv.setImageResource(R.drawable.smile_one_ans);
            x = -b/(2*a);
            tv.setText("x = " + x);
        }
        else if (inSqr > 0 && a > 0) // smile two answer
        {
            iv.setImageResource(R.drawable.smile_two_ans);
            x1 = (-b + Math.sqrt(inSqr)) / (2*a);
            x2 = (-b - Math.sqrt(inSqr)) / (2*a);
            tv.setText("x1 = " + x1 + "\n" + "x2 = " + x2);
        }
        else if (inSqr < 0 && a < 0) // cry no answer
        {
            iv.setImageResource(R.drawable.cry_no_ans);
            tv.setText("No answer");
            gi.putExtra("no","No answer");
        }
        else if (inSqr == 0 && a < 0) // cry one answer
        {
            iv.setImageResource(R.drawable.cry_one_ans);
            x = -b/(2*a);
            tv.setText("x = " + x);
        }
        else // cry two answer
        {
            iv.setImageResource(R.drawable.cry_two_ans);
            x1 = (-b - Math.sqrt(inSqr)) / (2*a);
            x2 = (-b + Math.sqrt(inSqr)) / (2*a);
            tv.setText("x1 = " + x1 + "\n" + "x2 = " + x2);
        }
    }

    public void Back(View view) {
        if (inSqr < 0)
        {
            gi.putExtra("numAns",0);
        }
        else if (inSqr == 0)
        {
            gi.putExtra("numAns",1);
            gi.putExtra("x",x);
        }
        else
        {
            gi.putExtra("numAns",2);
            gi.putExtra("x1",x1);
            gi.putExtra("x2",x2);
        }

        setResult(RESULT_OK,gi);
        finish();
    }
}