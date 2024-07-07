package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private String currentInput = "";
    private double operand1 = Double.NaN;
    private double operand2;
    private char currentOperator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);

        setNumberButtonListeners();
        setOperatorButtonListeners();
    }

    private void setNumberButtonListeners() {
        int[] numberButtonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9
        };

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                currentInput += button.getText().toString();
                resultTextView.setText(currentInput);
            }
        };

        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setOperatorButtonListeners() {
        int[] operatorButtonIds = {
                R.id.buttonAdd, R.id.buttonSubtract, R.id.buttonMultiply, R.id.buttonDivide, R.id.buttonEquals, R.id.buttonClear
        };

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                char operator = button.getText().charAt(0);

                switch (operator) {
                    case 'C':
                        operand1 = Double.NaN;
                        currentInput = "";
                        resultTextView.setText("");
                        break;
                    case '=':
                        if (!Double.isNaN(operand1)) {
                            operand2 = Double.parseDouble(currentInput);
                            performOperation();
                            currentInput = String.valueOf(operand1);
                            resultTextView.setText(currentInput);
                        }
                        break;
                    default:
                        if (!Double.isNaN(operand1)) {
                            operand2 = Double.parseDouble(currentInput);
                            performOperation();
                            currentOperator = operator;
                            currentInput = "";
                            resultTextView.setText(String.valueOf(operand1));
                        } else {
                            operand1 = Double.parseDouble(currentInput);
                            currentOperator = operator;
                            currentInput = "";
                        }
                        break;
                }
            }
        };

        for (int id : operatorButtonIds) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void performOperation() {
        switch (currentOperator) {
            case '+':
                operand1 = operand1 + operand2;
                break;
            case '-':
                operand1 = operand1 - operand2;
                break;
            case '*':
                operand1 = operand1 * operand2;
                break;
            case '/':
                if (operand2 == 0) {
                    resultTextView.setText("Error");
                    operand1 = Double.NaN;
                } else {
                    operand1 = operand1 / operand2;
                }
                break;
        }
    }
}
