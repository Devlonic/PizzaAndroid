package com.devlonic.pizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private void backToMainActivity(String size, String toppings, String addons, int price) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("size", size);
        intent.putExtra("toppings", toppings);
        intent.putExtra("addons", addons);
        intent.putExtra("price", price);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String size = intent.getStringExtra("size");
        String toppings = intent.getStringExtra("toppings");
        String addons = intent.getStringExtra("addons");
        int price = intent.getIntExtra("price", 0);

        ((TextView)findViewById(R.id.sizeResult)).setText(size);
        ((TextView)findViewById(R.id.toppingsResult)).setText(toppings);
        ((TextView)findViewById(R.id.addonsResult)).setText(addons);
        ((TextView)findViewById(R.id.priceResult)).setText(String.valueOf(price));

        Button finishButton = findViewById(R.id.finish_button);
        finishButton.setOnClickListener(v -> {
            backToMainActivity(null, null, null, 0);
        });

        Button backButton = findViewById(R.id.back_to_order_button);
        backButton.setOnClickListener(v -> {
            backToMainActivity(size, toppings, addons, price);
        });
    }
}