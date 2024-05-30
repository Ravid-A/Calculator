package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText num1;
    private EditText num2;
    private Spinner operators;
    private TextView answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        operators = findViewById(R.id.operators);
        answer = findViewById(R.id.mathAnswer);

        Button calculate = findViewById(R.id.calculate);
        calculate.setOnClickListener(this::OnClick);
    }

    private void OnClick(View v)
    {
        int ans = 0;
        int number1 = Integer.parseInt(num1.getText().toString());
        int number2 = Integer.parseInt(num2.getText().toString());
        int operatorId = (int) operators.getSelectedItemId();
        String operator = "";

        try
        {
            switch (operatorId)
            {
                case 0:
                    ans = number1 + number2;
                    operator = "+";
                    break;
                case 1:
                    ans = number1 - number2;
                    operator = "-";
                    break;
                case 2:
                    ans = number1 * number2;
                    operator = "*";
                    break;
                case 3:
                    ans = number1 / number2;
                    operator = "/";
                    break;
                case 4:
                    ans = (int) Math.pow(number1, number2);
                    operator = "^";
                    break;
            }
        }
        catch (Exception e)
        {
            answer.setText(R.string.invalid_input);
            return;
        }

        answer.setText(String.format("%,d %s %,d = %,d", number1, operator, number2, ans));

        num1.setText("");
        num2.setText("");
        operators.setSelection(0);

    }
}