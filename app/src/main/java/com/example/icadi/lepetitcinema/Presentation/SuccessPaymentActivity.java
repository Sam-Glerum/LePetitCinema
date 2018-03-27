package com.example.icadi.lepetitcinema.Presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.icadi.lepetitcinema.R;

public class SuccessPaymentActivity extends AppCompatActivity {

    private Button backToHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_payment);

        backToHomeButton = findViewById(R.id.backToHomeButton);
        backToHomeButton.setOnClickListener(new ButtonActionListenerHandler());
    }
}
