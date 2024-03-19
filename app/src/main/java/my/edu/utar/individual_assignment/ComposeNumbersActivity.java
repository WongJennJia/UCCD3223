package my.edu.utar.individual_assignment;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

        // Convert integer targetNumber to String
        String targetNumberString = String.valueOf(targetNumber);
        targetNumberTextView.setText(targetNumberString);

        // Convert otherNumber to String and assign to textview
        //String otherNumberString1 = String.valueOf(otherNumbers[0]);
        String[] otherNumberString = new String[3];

        //otherNumberString[1] = String.valueOf(otherNumbers[1]);
        //number1TextView.setText(otherNumberString1);
        //number2TextView.setText(otherNumberString[1]);

        /*
        // Loop through otherNumberString array to set TextViews
        for (int i = 0; i < otherNumbers.length; i++) {
            if (otherNumbers[i] != 0) {
                otherNumberString[i] = String.valueOf(otherNumbers[i]);
                number1TextView.setText(otherNumbers[i]);
            }
        }
         */
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
                    // Add more cases if needed
                }
            }
        }
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


/*
        number1TextView.setClickable(true);
        number2TextView.setClickable(true);
        number3TextView.setClickable(true);

*/

        //generateTargetNumber();
        generateProblem();

        number1TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedNumber = Integer.parseInt(((TextView) v).getText().toString());
                clickedNumber += selectedNumber; // Update clickedNumber
            }
        });

        // Similar click listeners for number2TextView and number3TextView
        number2TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedNumber = Integer.parseInt(((TextView) v).getText().toString());
                clickedNumber += selectedNumber; // Update clickedNumber
            }
        });

        number3TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedNumber = Integer.parseInt(((TextView) v).getText().toString());
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

                if (isCorrect) {
                    Toast.makeText(ComposeNumbersActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ComposeNumbersActivity.this, "Try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
                // Display toast message based on isCorrect

    }

    //generateTargetNumber()







}
