package com.devlonic.pizza;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.devlonic.pizza.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;

    private void launchResultActivity(String size, String toppings, String addons, int price) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("size", size);
        intent.putExtra("toppings", toppings);
        intent.putExtra("addons", addons);
        intent.putExtra("price", price);
        startActivityForResult(intent, REQUEST_CODE);
    }

    private int calculatePrice(String size, String toppings, String addons) {
        return Integer.parseInt(size) * (toppings.split("\\s")).length * (addons.split("\\s")).length;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if(intent != null) {
            String size = intent.getStringExtra("size");
            String toppings = intent.getStringExtra("toppings");
            String addons = intent.getStringExtra("addons");

            ((EditText)findViewById(R.id.sizeInput)).setText(size);
            ((EditText)findViewById(R.id.toppingsInput)).setText(toppings);
            ((EditText)findViewById(R.id.addonsInput)).setText(addons);
        }

        Button orderButton = findViewById(R.id.order_button);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get values from inputs
                String size = ((EditText)findViewById(R.id.sizeInput)).getText().toString();
                String toppings = ((EditText)findViewById(R.id.toppingsInput)).getText().toString();
                String addons = ((EditText)findViewById(R.id.addonsInput)).getText().toString();
                int price = calculatePrice(size, toppings, addons);

                launchResultActivity(size, toppings, addons, price);
            }
        });
    }
}