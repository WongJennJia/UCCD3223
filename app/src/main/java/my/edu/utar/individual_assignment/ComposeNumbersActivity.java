package my.edu.utar.individual_assignment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Random;


public class ComposeNumbersActivity extends AppCompatActivity {

    private TextView targetNumberTextView;
    private TextView number1TextView;
    private TextView number2TextView;
    private TextView number3TextView;
    private Button checkButton;
    private int targetNumber;
    private int[] otherNumbers = new int[3]; // Store generated numbers for answer checking
    private int clickedNumber = 0; // Variable to store user-selected number

    public static int generateTargetNumber() {
        Random random = new Random();
        return random.nextInt(990) + 10; // Generate a number between 10 and 999 (inclusive)
    }

    public static int[] generateOtherNumbers(int targetNumber) {
        int[] otherNumbers = new int[3];
        Random random = new Random();
        for (int i = 0; i < otherNumbers.length; i++) {
            otherNumbers[i] = random.nextInt(targetNumber - 1) + 1; // Generate numbers less than targetNumber
        }
        // Randomly select two indices (excluding the chosen index) to ensure any two numbers can add up to the target
        int index1 = random.nextInt(otherNumbers.length);
        int index2;
        do {
            index2 = random.nextInt(otherNumbers.length);
        } while (index1 == index2); // Ensure indices are different

        // Make one of the chosen indices add up to the target with another random number
        otherNumbers[index1] = targetNumber - otherNumbers[index2];

        return otherNumbers;
    }
    public void generateProblem() {
        targetNumber = generateTargetNumber();
        otherNumbers = generateOtherNumbers(targetNumber);
        String[] otherNumberString = new String[3];

        // Convert integer targetNumber to String
        String targetNumberString = String.valueOf(targetNumber);
        targetNumberTextView.setText(targetNumberString);

        // Loop through otherNumberString array to set TextViews
        for (int i = 0; i < otherNumbers.length; i++) {
            if (otherNumbers[i] != 0) {
                switch (i) {
                    case 0:
                        otherNumberString[i] = String.valueOf(otherNumbers[i]);
                        number1TextView.setText(otherNumberString[i]);
                        break;
                    case 1:
                        otherNumberString[i] = String.valueOf(otherNumbers[i]);
                        number2TextView.setText(otherNumberString[i]);
                        break;
                    case 2:
                        otherNumberString[i] = String.valueOf(otherNumbers[i]);
                        number3TextView.setText(otherNumberString[i]);
                        break;
                }
            }
        }



        int defaultBoxColor = ContextCompat.getColor(this, R.color.default_box_color);
        number1TextView.setBackgroundColor(defaultBoxColor);
        number1TextView.setEnabled(true);
        number2TextView.setBackgroundColor(defaultBoxColor);
        number2TextView.setEnabled(true);
        number3TextView.setBackgroundColor(defaultBoxColor);
        number3TextView.setEnabled(true);
        clickedNumber = 0;
        checkButton.setEnabled(true);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_numbers);

        targetNumberTextView = findViewById(R.id.target_number_text);
        number1TextView = findViewById(R.id.number1_text);
        number2TextView = findViewById(R.id.number2_text);
        number3TextView = findViewById(R.id.number3_text);
        checkButton = findViewById(R.id.check_button);
        Button nextButton = findViewById(R.id.next_button);
        Button backToMenuButton = findViewById(R.id.back_to_menu_button);

        generateProblem();

        number1TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedNumber = Integer.parseInt(((TextView) v).getText().toString());
                number1TextView.setBackgroundColor(Color.GRAY);
                number1TextView.setEnabled(false);
                clickedNumber += selectedNumber; // Update clickedNumber
            }
        });

        // Similar click listeners for number2TextView and number3TextView
        number2TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedNumber = Integer.parseInt(((TextView) v).getText().toString());
                number2TextView.setBackgroundColor(Color.GRAY);
                number2TextView.setEnabled(false);
                clickedNumber += selectedNumber; // Update clickedNumber
            }
        });

        number3TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedNumber = Integer.parseInt(((TextView) v).getText().toString());
                number3TextView.setBackgroundColor(Color.GRAY);
                number3TextView.setEnabled(false);
                clickedNumber += selectedNumber; // Update clickedNumber
            }
        });
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickedNumber == 0) {
                    Toast.makeText(ComposeNumbersActivity.this, "Please select a number!", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean isCorrect = false;
                //for (int otherNumber : otherNumbers) {
                if (clickedNumber == targetNumber) {
                        isCorrect = true;
                        //break;
                    }
                //}
                // Display toast message based on isCorrect
                if (isCorrect) {
                    Toast.makeText(ComposeNumbersActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                    number1TextView.setEnabled(false);
                    number2TextView.setEnabled(false);
                    number3TextView.setEnabled(false);
                    checkButton.setEnabled(false);
                } else {
                    Toast.makeText(ComposeNumbersActivity.this, "Try again!", Toast.LENGTH_SHORT).show();
                    number1TextView.setBackgroundColor(Color.WHITE);
                    number1TextView.setEnabled(true);
                    number2TextView.setBackgroundColor(Color.WHITE);
                    number2TextView.setEnabled(true);
                    number3TextView.setBackgroundColor(Color.WHITE);
                    number3TextView.setEnabled(true);
                    clickedNumber = 0;
                }
            }
        });

        // Set click listener for next button
        nextButton.setOnClickListener(v -> generateProblem());

        // Set click listener for back to menu button
        backToMenuButton.setOnClickListener(v -> {
            // Launch Menu Activity
            Intent menuIntent = new Intent(ComposeNumbersActivity.this, MainActivity.class);
            startActivity(menuIntent);
        });
    }








}
