package com.example.icadi.lepetitcinema.Presentation;

import android.view.View;

import com.example.icadi.lepetitcinema.R;

public class ButtonActionListenerHandeler implements View.OnClickListener{


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.payTicketButton:
                System.out.println("|------------------------|");
                System.out.println("|PayTicketButton clicked!|");
                System.out.println("|------------------------|");
                break;

        }
    }
}
