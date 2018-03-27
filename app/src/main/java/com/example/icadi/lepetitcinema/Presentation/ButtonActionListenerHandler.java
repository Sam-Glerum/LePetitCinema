package com.example.icadi.lepetitcinema.Presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.icadi.lepetitcinema.R;

public class ButtonActionListenerHandler extends AppCompatActivity implements View.OnClickListener{

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.payTicketButton:
                Intent toSuccessPayment = new Intent(view.getContext(), SuccessPaymentActivity.class);
                startActivity(toSuccessPayment);
                break;

            case R.id.backToHomeButton:
                Intent toHomeScreen = new Intent(view.getContext(), MainActivity.class);
                startActivity(toHomeScreen);
                break;

        }
    }
}
