package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText firstNumberEditText, secondNumberEditText;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements from the layout
        firstNumberEditText = findViewById(R.id.num1EditText);
        secondNumberEditText = findViewById(R.id.num2EditText);
        resultTextView = findViewById(R.id.resultTextView);

        // Set click listeners for arithmetic operation buttons
        setupOperationButton(R.id.addButton, '+');
        setupOperationButton(R.id.subtractButton, '-');
        setupOperationButton(R.id.multiplyButton, '*');
        setupOperationButton(R.id.divideButton, '/');
        setupSquareRootButton(R.id.sqrtButton);
    }

    private void setupOperationButton(int buttonId, final char operator) {
        Button operationButton = findViewById(buttonId);
        operationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCalculation(operator);
            }
        });
    }

    private void setupSquareRootButton(int buttonId) {
        Button sqrtButton = findViewById(buttonId);
        sqrtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateSquareRoot();
            }
        });
    }

    private void performCalculation(char operator) {
        // Get the values entered in the input fields
        String firstNumStr = firstNumberEditText.getText().toString();
        String secondNumStr = secondNumberEditText.getText().toString();

        // Check if either input field is empty
        if (firstNumStr.isEmpty() || secondNumStr.isEmpty()) {
            showToast("Please enter both numbers");
            return; // Exit the method to prevent calculations with empty inputs
        }

        // Convert the input values to numeric format
        double firstNum = Double.parseDouble(firstNumStr);
        double secondNum = Double.parseDouble(secondNumStr);
        double result = 0;

        // Perform the selected calculation based on the operator
        switch (operator) {
            case '+':
                result = firstNum + secondNum;
                break;
            case '-':
                result = firstNum - secondNum;
                break;
            case '*':
                result = firstNum * secondNum;
                break;
            case '/':
                if (secondNum != 0) {
                    result = firstNum / secondNum;
                } else {
                    showToast("Cannot divide by zero");
                    return; // Exit the method if division by zero is attempted
                }
                break;
        }

        // Format and display the calculation result
        DecimalFormat df = new DecimalFormat("#.##");
        resultTextView.setText("Result: " + df.format(result));
    }

    private void calculateSquareRoot() {
        String firstNumStr = firstNumberEditText.getText().toString();
        // Check if the input field is empty
        if (firstNumStr.isEmpty()) {
            showToast("Please enter a number");
            return; // Exit the method to prevent calculations with empty inputs
        }

        double num = Double.parseDouble(firstNumStr);
        double sqrtResult = Math.sqrt(num);
        DecimalFormat df = new DecimalFormat("#.##");
        resultTextView.setText("Square Root: " + df.format(sqrtResult));
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
