package com.example.exn082;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_CODE = 1;
    private Intent si;
    private Random rnd;
    private double a,b,c;
    private EditText et1,et2,et3;
    private TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rnd = new Random();

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        tv2 = findViewById(R.id.tv2);

        si = new Intent(this,AnswerActivity.class);
    }

    public void rndbtn(View view) {
        tv2.setText("");
        a = rnd.nextInt(20)-10;
        if (a == 0) a++;
        et1.setText(a + "");

        b = rnd.nextInt(20)-10;
        et2.setText(b + "");

        c = rnd.nextInt(20)-10;
        et3.setText(c + "");
    }

    public void ansbtn(View view) {
        if (!et1.getText().toString().equals("") && !et2.getText().toString().equals("") && !et3.getText().toString().equals(""))
        {
            if ((!et1.getText().toString().contains(String.valueOf('-')) || (et1.getText().toString().indexOf('-') == 0) && et1.getText().toString().indexOf('-') != et1.getText().toString().length() - 1) && (!et1.getText().toString().contains(String.valueOf('.')) || (et1.getText().toString().indexOf('.') != 0) && et1.getText().toString().indexOf('.') != et1.getText().toString().length() - 1) && (!et2.getText().toString().contains(String.valueOf('-')) || (et2.getText().toString().indexOf('-') == 0) && et2.getText().toString().indexOf('-') != et2.getText().toString().length() - 1) && (!et2.getText().toString().contains(String.valueOf('.')) || (et2.getText().toString().indexOf('.') != 0) && et2.getText().toString().indexOf('.') != et2.getText().toString().length() - 1) && (!et3.getText().toString().contains(String.valueOf('-')) || (et3.getText().toString().indexOf('-') == 0) && et3.getText().toString().indexOf('-') != et3.getText().toString().length() - 1) && (!et3.getText().toString().contains(String.valueOf('.')) || (et3.getText().toString().indexOf('.') != 0) && et3.getText().toString().indexOf('.') != et3.getText().toString().length() - 1)) {
                if (Double.parseDouble(et1.getText().toString()) != 0)
                {
                    tv2.setTextColor(Color.BLACK);
                    tv2.setText("");

                    a = Double.parseDouble(et1.getText().toString());
                    b = Double.parseDouble(et2.getText().toString());
                    c = Double.parseDouble(et3.getText().toString());

                    si.putExtra("a",a);
                    si.putExtra("b",b);
                    si.putExtra("c",c);
                    startActivityForResult(si,REQUEST_CODE);
                }
                else
                {
                    tv2.setTextColor(Color.RED);
                    tv2.setText("a - cannot be 0!");
                }
            }
            else
            {
                tv2.setTextColor(Color.RED);
                tv2.setText("Invalid number!");
            }
        }
        else
        {
            tv2.setTextColor(Color.RED);
            tv2.setText("You must enter all the coefficients!");
        }
    }

    @Override
    protected void onActivityResult(int source, int result, @Nullable Intent data_back)
    {
        super.onActivityResult(source,result,data_back);
        if (source == REQUEST_CODE)
        {
            if (result == Activity.RESULT_OK)
            {
                if (data_back != null)
                {
                    switch (data_back.getIntExtra("numAns",0))
                    {
                        case 0:
                            tv2.setText("No answer"); break;
                        case 1:
                            tv2.setText("x = " + data_back.getDoubleExtra("x",0)); break;
                        case 2:
                            tv2.setText("x1 = " + data_back.getDoubleExtra("x1",0) + "\n" + "x2 = " + data_back.getDoubleExtra("x2",0)); break;
                    }
                }
            }
        }
    }
}