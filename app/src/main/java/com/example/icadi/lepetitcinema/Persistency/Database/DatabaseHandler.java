package com.example.icadi.lepetitcinema.Persistency.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.icadi.lepetitcinema.Domain.Ticket;

import java.util.ArrayList;

/**
 * Created by icadi on 4-4-18.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // Tag used for logging
    private static final String TAG = DatabaseHandler.class.getSimpleName();

    // Constants
    private static final String FILMTITLE = "filmtitle";
    private static final String SEATS = "seats";
    private static final String CINEMAROOM = "cinemaRoom";
    private static final String PRICE = "price";
    private static final String QRCODEDATA = "qrCodeData";

    private static final int DATABASE_VERSION = 1;

    public DatabaseHandler(Context context) {
        super(context, "LePetitCinemaTicket", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i(TAG, "onCreate called");
        // Create table Ticket
        sqLiteDatabase.execSQL("CREATE TABLE `Ticket` ( " +
                "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                "`filmTitle` TEXT, " +
                "`seats` TEXT, " +
                "`cinemaRoom` TEXT, " +
                "`qrCodeData` TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.i(TAG, "onUpgrade upgrading database");
        // Check if the database upgrade has a new version, if so, drop the old and create the new database
        if (newVersion == oldVersion + 1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Ticket");
            // Create a newdatabase
            this.onCreate(sqLiteDatabase);
        }
    }

    public ArrayList<Ticket> getAllTickets() {
        // Initialize ArrayList to store retrieved Tickets
        ArrayList<Ticket> tickets = new ArrayList<>();
        Log.i(TAG, "getAllTickets called");

        // Construct SQL query
        String query = "SELECT * FROM Ticket";
        // Setup access to database
        SQLiteDatabase db = this.getWritableDatabase();
        // Setup cursor to navigate through database
        Cursor cursor = db.rawQuery(query, null);

        // Move to first row of Ticket table
        cursor.moveToFirst();
        // Iterate through every row of the database
        while(!cursor.isLast()) {
            String filmName = cursor.getString(cursor.getColumnIndex(FILMTITLE));
            String seatNumbers = cursor.getString(cursor.getColumnIndex(SEATS));
            String cinemaRoom = cursor.getString(cursor.getColumnIndex(CINEMAROOM));
            String qrCodeData = cursor.getString(cursor.getColumnIndex(QRCODEDATA));

            // Add new Ticket to ArrayList
            tickets.add(new Ticket(filmName, seatNumbers + ",", cinemaRoom, qrCodeData, null, "", ""));
            // Move to next row in database
            cursor.moveToNext();
        }
        // Close the database connection
        db.close();
        // Return the ArrayList with Tickets
        return tickets;
    }

    // Method used to add Tickets to the database
    public void addTicket(Ticket ticket) {
        Log.i(TAG, "addTicket called");

        // Create the values to be stored in the database
        ContentValues values = new ContentValues();
        values.put(FILMTITLE, ticket.getFilmName());
        values.put(SEATS, ticket.getSeatNumber());
        values.put(CINEMAROOM, ticket.getCinemaRoom());
        values.put(QRCODEDATA, ticket.getQrCodeData());

        // Initialize writable access to the database
        SQLiteDatabase db = this.getWritableDatabase();
        // Insert the data into the database
        db.insert("Ticket", null, values);
        Log.i(TAG, "Ticket added to database: " + FILMTITLE);
        // Close the database connection
        db.close();
    }
}
