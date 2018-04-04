package com.example.icadi.lepetitcinema.Presentation;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.icadi.lepetitcinema.Domain.Seat;
import com.example.icadi.lepetitcinema.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SeatPickerActivity extends AppCompatActivity implements View.OnClickListener {

    public final static String SEATS = "SEATS";
    public final static String FILMTITLE = "FILMTITLE";
    public final static String AMOUNTOFTICKETS = "AMOUNTOFTICKETS";
    public final static String PRICE = "PRICE";
    public final static String FILM_IMAGE = "FILM_IMAGE";

//    public final static String CINEMAROOMNR = "CINEMAROOMNR";

    private LinkedHashMap<String, SeatImageViewBundle> seatImageViewBundleHashMap;
    private ArrayList<Seat> currentlySelectedSeats = new ArrayList<>();
    private TextView amountOfSeatsTextView;
    private Button buyTicketButton;

    private ImageButton childDecreaseButton;
    private ImageButton childIncreaseButton;

    private ImageButton normalDecreaseButton;
    private ImageButton normalIncreaseButton;

    private ImageButton elderDecreaseButton;
    private ImageButton elderIncreaseButton;

    private TextView childAmountTextView;
    private TextView childPriceTextView;

    private TextView normalAmountTextView;
    private TextView normalPriceTextView;

    private TextView elderAmountTextView;
    private TextView elderPriceTextView;

    private int childAmount;
    private double childPrice;

    private int normalAmount;
    private double normalPrice;

    private int elderAmount;
    private double elderPrice;

    private double totalPrice;
    private TextView totalPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_picker);

        amountOfSeatsTextView = (TextView) findViewById(R.id.seatPicker_textView_amountOfSeats);

        // Initialise seats
        seatImageViewBundleHashMap = new LinkedHashMap<>();
        initSeats();

        buyTicketButton = findViewById(R.id.seatPicker_button_buyTickets);
        buyTicketButton.setOnClickListener(this);

        childDecreaseButton = findViewById(R.id.seatPicker_button_childDecrease);
        childDecreaseButton.setOnClickListener(this);
        childIncreaseButton = findViewById(R.id.seatPicker_button_childIncrease);
        childIncreaseButton.setOnClickListener(this);

        normalDecreaseButton = findViewById(R.id.seatPicker_button_normalDecrease);
        normalDecreaseButton.setOnClickListener(this);
        normalIncreaseButton = findViewById(R.id.seatPicker_button_normalIncrease);
        normalIncreaseButton.setOnClickListener(this);

        elderDecreaseButton = findViewById(R.id.seatPicker_button_elderDecrease);
        elderDecreaseButton.setOnClickListener(this);
        elderIncreaseButton = findViewById(R.id.seatPicker_button_elderIncrease);
        elderIncreaseButton.setOnClickListener(this);

        childAmountTextView = findViewById(R.id.seatPicker_textView_childAmount);
        childPriceTextView = findViewById(R.id.seatPicker_textView_childPrice);

        normalAmountTextView = findViewById(R.id.seatPicker_textView_normalAmount);
        normalPriceTextView = findViewById(R.id.seatPicker_textView_normalPrice);

        elderAmountTextView = findViewById(R.id.seatPicker_textView_elderAmount);
        elderPriceTextView = findViewById(R.id.seatPicker_textView_elderPrice);

        totalPriceTextView = findViewById(R.id.seatPicker_textView_totalPriceAmount);
    }

    /**
     * Adds Seats and Imageviews to the HashMap
     *
     */
    public void initSeats() {

        for (int i = 0; i < 6; i++) {
            for (int ii = 0; ii < 9; ii++) {
                String seatId = ("r" + (i + 1) + "s" + (ii + 1));
                int resId = getResources().getIdentifier(seatId, "id", getPackageName());
                ImageView imageView = (ImageView) findViewById(resId);
                imageView.setOnClickListener(this);

                Seat seat = new Seat(seatId);
                SeatImageViewBundle seatImageViewBundle = new SeatImageViewBundle(seat, imageView);

                seatImageViewBundleHashMap.put(seatId , seatImageViewBundle);
            }
        }

        Log.i("SeatPicker", "initSeats: size of hashmap = " + seatImageViewBundleHashMap.size());
    }

    public boolean autoAssignSeats(int numOfTickets) {

        if (numOfTickets < seatImageViewBundleHashMap.size() + 1) {

            int numOfRemainingTickets = numOfTickets;

            for (String key : seatImageViewBundleHashMap.keySet()) {

                Seat seat = seatImageViewBundleHashMap.get(key).getSeat();
                ImageView imageView = seatImageViewBundleHashMap.get(key).getImageView();

                if (numOfRemainingTickets > 0 && !seat.isOccupied()) {
                    imageView.callOnClick();
                    numOfRemainingTickets--;

                } else if (numOfRemainingTickets <= 0) {
                    break;
                }

            }

            return true;

        } else {
            Log.i("SeatPickerActivity", " autoAssignSeats: ERROR: numOfSeats was bigger than hashmap size!");
            return false;

        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.seatPicker_button_buyTickets:


                final int currentAmountOfSeats = currentlySelectedSeats.size();
                final int currentAmountOfTickets = childAmount + normalAmount + elderAmount;

                if (currentAmountOfSeats == currentAmountOfTickets && currentAmountOfSeats != 0) {
                    Intent toPayment = new Intent(getApplicationContext(), PaymentSimulationActivity.class);
                    toPayment.putExtra(SEATS, currentlySelectedSeats);
                    toPayment.putExtra(FILMTITLE, getIntent().getStringExtra(FILMTITLE));
                    toPayment.putExtra(PRICE, totalPrice);
                    toPayment.putExtra(AMOUNTOFTICKETS, "" + currentlySelectedSeats.size());
                    toPayment.putExtra(FILM_IMAGE, getIntent().getStringExtra(FILM_IMAGE));

                    startActivity(toPayment);

                } else {
                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                    final AlertDialog.Builder autoAssignUnsuccesfulDialogBuilder = new AlertDialog.Builder(this);

                    if (currentAmountOfSeats == 0 || currentAmountOfTickets == 0) {
                        alertDialogBuilder.setTitle("You must reserve at least one seat and ticket!");

                        if (currentAmountOfSeats == 0 && currentAmountOfTickets == 0) {
                            alertDialogBuilder.setMessage("There is nothing selected");

                        } else if (currentAmountOfSeats == 0){
                            alertDialogBuilder.setMessage("There are  no seats selected");

                            alertDialogBuilder.setPositiveButton("Pick my seats for me", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();

                                    if (!autoAssignSeats(currentAmountOfTickets)) {
                                        autoAssignUnsuccesfulDialogBuilder.setTitle("There are not enough seats available");
                                        autoAssignUnsuccesfulDialogBuilder.setMessage("");
                                        autoAssignUnsuccesfulDialogBuilder.setCancelable(false);
                                        autoAssignUnsuccesfulDialogBuilder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });
                                        AlertDialog autoAssignUnsuccesfulDialog = autoAssignUnsuccesfulDialogBuilder.create();
                                        autoAssignUnsuccesfulDialog.show();
                                    }


                                }
                            });

                        } else {
                            alertDialogBuilder.setMessage("There are no tickets selected");
                        }


                    } else {
                        alertDialogBuilder.setTitle("Amount of tickets and seats are not equal!");
                        alertDialogBuilder.setMessage("There are currently " + currentAmountOfSeats +
                                " seats and " + currentAmountOfTickets + " tickets selected");
                    }


                    alertDialogBuilder.setCancelable(false);
                    alertDialogBuilder.setNeutralButton("Change selection", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();

                    alertDialog.show();
                }
                break;

            case R.id.seatPicker_button_childDecrease:
                if (childAmount > 0) {
                    childAmount--;
                }

                childAmountTextView.setText("" + childAmount);

                childPrice = childAmount * 5.00;
                childPriceTextView.setText("" + (childPrice) + " euro");
                break;

            case R.id.seatPicker_button_childIncrease:
                childAmount++;

                childAmountTextView.setText("" + childAmount);

                childPrice = childAmount * 5.00;
                childPriceTextView.setText("" + (childPrice) + " euro");
                break;

            case R.id.seatPicker_button_normalDecrease:
                if (normalAmount > 0) {
                    normalAmount--;
                }

                normalAmountTextView.setText("" + normalAmount);

                normalPrice = normalAmount * 10.00;
                normalPriceTextView.setText("" + (normalPrice) + " euro");
                break;

            case R.id.seatPicker_button_normalIncrease:
                normalAmount++;

                normalAmountTextView.setText("" + normalAmount);

                normalPrice = normalAmount * 10.00;
                normalPriceTextView.setText("" + (normalPrice) + " euro");
                break;

            case R.id.seatPicker_button_elderDecrease:
                if (elderAmount > 0) {
                    elderAmount--;
                }

                elderAmountTextView.setText("" + elderAmount);

                elderPrice = elderAmount * 8.00;
                elderPriceTextView.setText("" + (elderPrice) + " euro");
                break;

            case R.id.seatPicker_button_elderIncrease:
                elderAmount++;

                elderAmountTextView.setText("" + elderAmount);

                elderPrice = elderAmount * 8.00;
                elderPriceTextView.setText("" + (elderPrice) + " euro");
                break;


            default:
                ImageView imageView = findViewById(view.getId());
                if (imageView.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.cinemaseat_available).getConstantState())) {
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.cinemaseat_selected));
                    Seat seat = new Seat(getResources().getResourceEntryName(view.getId()));
                    currentlySelectedSeats.add(seat);
                    Log.i("SeatPickerActivity", "Added seat: " + seat.getNumber() + " to current selection, size is now: " + currentlySelectedSeats.size());

                } else if (imageView.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.cinemaseat_selected).getConstantState())) {
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.cinemaseat_available));
                    String seatNumber = getResources().getResourceEntryName(view.getId());

                    for (int i = 0; i < currentlySelectedSeats.size(); i++) {
                        Seat seat = currentlySelectedSeats.get(i);
                        if (seat.getNumber().equals(seatNumber)) {
                            currentlySelectedSeats.remove(seat);
                            Log.i("SeatPickerActivity", "Removed seat: " + seat.getNumber() + " from current selection, size is now: " + currentlySelectedSeats.size());
                        }
                    }

                } else if (imageView.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.cinemaseat_occupied).getConstantState())) {
                    Log.i("SeatPickerActivity", "The chosen seat is already occupied!");
                } else {
                    Log.i("SeatPickerActivity", "ERROR: Could not get drawable to check!");
                }

                amountOfSeatsTextView.setText("" + currentlySelectedSeats.size());



        }

        totalPrice = childPrice + normalPrice + elderPrice;
        totalPriceTextView.setText("" + totalPrice + " euro");

    }

    public ArrayList<Seat> getCurrentlySelectedSeats() {
        return currentlySelectedSeats;
    }

    public void setCurrentlySelectedSeats(ArrayList<Seat> currentlySelectedSeats) {
        this.currentlySelectedSeats = currentlySelectedSeats;
    }
}
