package my.edu.utar.individual_assignment;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class CompareNumbersActivity extends AppCompatActivity {
    //private TextView instructionText;
    private TextView number1Text;
    private TextView number2Text;
    private int correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_numbers); // Replace with your actual layout resource

        // Find UI elements by their IDs
        number1Text = findViewById(R.id.number1_text);
        number2Text = findViewById(R.id.number2_text);
        Button nextButton = findViewById(R.id.next_button);
        Button backToMenuButton = findViewById(R.id.back_to_menu_button);
        // Generate random numbers
        generateNumbers();

        // Set click listeners for number TextViews
        number1Text.setOnClickListener(v -> checkAnswer(Integer.parseInt(number1Text.getText().toString())));

        number2Text.setOnClickListener(v -> checkAnswer(Integer.parseInt(number2Text.getText().toString())));

        // Set click listener for next button
        nextButton.setOnClickListener(v -> generateNumbers());

        // Set click listener for back to menu button
        backToMenuButton.setOnClickListener(v -> {
            // Launch Menu Activity
            Intent menuIntent = new Intent(CompareNumbersActivity.this, MainActivity.class);
            startActivity(menuIntent);
        });
    }

    private void generateNumbers() {
        // Generate random numbers between 1 and 999 (inclusive)
        int num1 = (int) (Math.random() * 1000);
        int num2 = (int) (Math.random() * 1000);

        // Determine correct answer
        correctAnswer = Math.max(num1, num2);

        // Display numbers
        number1Text.setText(String.valueOf(num1));
        number2Text.setText(String.valueOf(num2));

        // Reset text color
        number1Text.setTextColor(Color.BLACK);
        number2Text.setTextColor(Color.BLACK);
    }

    private void checkAnswer(int chosenNumber) {
        if (chosenNumber == correctAnswer) {
            // Correct answer
            // Change text color of chosen number to green
            TextView clickedTextView = (TextView) findViewById(chosenNumber == Integer.parseInt(number1Text.getText().toString()) ? R.id.number1_text : R.id.number2_text);
            clickedTextView.setTextColor(Color.GREEN);
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            // Incorrect answer
            Toast.makeText(this, "Try Again!, This one is smaller", Toast.LENGTH_SHORT).show();
            // Change text color of chosen number to red
            TextView clickedTextView = (TextView) findViewById(chosenNumber == Integer.parseInt(number1Text.getText().toString()) ? R.id.number1_text : R.id.number2_text);
            clickedTextView.setTextColor(Color.RED);
        }
    }
}
