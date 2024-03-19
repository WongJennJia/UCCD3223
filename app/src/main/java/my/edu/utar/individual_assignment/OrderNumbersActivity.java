package my.edu.utar.individual_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrderNumbersActivity extends AppCompatActivity {

    private List<Integer> generatedNumbers;
    private List<Integer> userArrangement;
    private String chosenOrder;
    private TextView arrangementTextView; // Reference to TextView for user's arrangement

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_numbers); // Replace with your layout resource ID

        generatedNumbers = generateNumbers();
        userArrangement = new ArrayList<>();
        Button backToMenuButton = findViewById(R.id.back_to_menu_button);


        // (implementation omitted for brevity)
        chosenOrder = null; // Initially no order chosen

        // Set up UI elements to display generated numbers and order selection buttons
        TextView number1 = findViewById(R.id.number_1); // Replace with TextView ID for number 1
        TextView number2 = findViewById(R.id.number_2); // Replace with TextView ID for number 2
        TextView number3 = findViewById(R.id.number_3); // Replace with TextView ID for number 3
        TextView number4 = findViewById(R.id.number_4); // Replace with TextView ID for number 4
        Button ascendingButton = findViewById(R.id.ascending_button); // Replace with button ID
        Button descendingButton = findViewById(R.id.descending_button); // Replace with button ID
        Button checkButton = findViewById(R.id.check_button); // Replace with button ID

        // Set text for generated numbers
        number1.setText(String.valueOf(generatedNumbers.get(0)));
        number2.setText(String.valueOf(generatedNumbers.get(1)));
        number3.setText(String.valueOf(generatedNumbers.get(2)));
        number4.setText(String.valueOf(generatedNumbers.get(3)));

        //disable the checkButton first
        checkButton.setEnabled(false);

        // Implement click listeners for order selection buttons (ascending/descending)
        ascendingButton.setOnClickListener(v -> {
            setOrder("ascending");
            ascendingButton.setBackgroundColor(Color.GREEN);
            descendingButton.setBackgroundColor(Color.RED);
            checkButton.setEnabled(true);
        });
        descendingButton.setOnClickListener(v -> {
            setOrder("descending");
            descendingButton.setBackgroundColor(Color.GREEN);
            ascendingButton.setBackgroundColor(Color.RED);
            checkButton.setEnabled(true);
        });

        // Implement click listeners for each generated number

        arrangementTextView = findViewById(R.id.arrangement_text_view); // Replace with TextView ID
        number1.setOnClickListener(this::handleNumberClick);
        number2.setOnClickListener(this::handleNumberClick);
        number3.setOnClickListener(this::handleNumberClick);
        number4.setOnClickListener(this::handleNumberClick);

        // Implement click listeners for checkButton
        checkButton.setOnClickListener(this::checkArrangement);

        // Set click listener for back to menu button
        backToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch Menu Activity
                Intent menuIntent = new Intent(OrderNumbersActivity.this, MainActivity.class);
                startActivity(menuIntent);
            }
        });
    }
    private List<Integer> generateNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            numbers.add((int) (Math.random() * 1000)); // Generate random numbers between 1-999
        }
        return numbers;
    }

    private void setOrder(String order) {
        chosenOrder = order;
    }

    public void handleNumberClick(View view) {
        int number = Integer.parseInt(((TextView) view).getText().toString());
        if (userArrangement.size() < 4) { // Allow selection only if less than 4 chosen
            userArrangement.add(number);

            // Clear text and add a box with faded color to clicked TextView
            ((TextView) view).setText("");
            ((TextView) view).setBackgroundResource(R.drawable.selected_number_box); // Replace with your box drawable
            ((TextView) view).setTextColor(ContextCompat.getColor(this, R.color.faded_text_color)); // Replace with your faded color

            // Append number with a space to arrangement text view
            arrangementTextView.append(number + " ");

            if (userArrangement.size() == 4) {
                // Disable number selection after user has chosen all four numbers
                findViewById(R.id.number_1).setClickable(false); // Replace with IDs for all number TextViews
                findViewById(R.id.number_2).setClickable(false);
                findViewById(R.id.number_3).setClickable(false);
                findViewById(R.id.number_4).setClickable(false);
            }
        }
    }
    public void checkArrangement(View view) {
        List<Integer> sortedNumbers = new ArrayList<>(generatedNumbers);
        if (chosenOrder.equals("ascending")) {
            Collections.sort(sortedNumbers);
        } else {
            Collections.sort(sortedNumbers, Collections.reverseOrder());
        }

        boolean isCorrect = userArrangement.equals(sortedNumbers);

        // Display message indicating whether the user's arrangement is correct or not
        // Display toast message based on the result
        String message = isCorrect ? "Congratulations! Your arrangement is correct."
                : "Oops! The arrangement is not correct.";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}

