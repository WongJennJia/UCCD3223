package my.edu.utar.individual_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Replace with your actual layout resource

        // Find buttons by their IDs (defined in your layout file)
        Button compareButton = findViewById(R.id.compare_button);
        Button orderButton = findViewById(R.id.order_button);
        Button composeButton = findViewById(R.id.compose_button);

        // Set click listeners for buttons
        compareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch Compare Numbers Activity
                Intent compareIntent = new Intent(MainActivity.this, CompareNumbersActivity.class);
                startActivity(compareIntent);
            }
        });

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch Order Numbers Activity
                Intent orderIntent = new Intent(MainActivity.this, OrderNumbersActivity.class);
                startActivity(orderIntent);
            }
        });

        composeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch Compose Numbers Activity
                Intent composeIntent = new Intent(MainActivity.this, ComposeNumbersActivity.class);
                startActivity(composeIntent);
            }
        });
    }
}
