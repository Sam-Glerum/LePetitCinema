package com.example.icadi.lepetitcinema.Persistency.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by icadi on 3-4-18.
 */

public class DatabaseCreator extends SQLiteOpenHelper {

    // Constant TAG used for logging
    private static final String TAG = DatabaseCreator.class.getSimpleName();

    // Constant for accessing database version
    private static final int DATABASE_VERSION = 1;

    public DatabaseCreator(Context context) {
        super(context, "LePetitCinema", null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i(TAG, "onCreate called");
        // Create table Film
        sqLiteDatabase.execSQL("CREATE TABLE `Film` ( " +
                "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                "`name` TEXT NOT NULL, " +
                "`description` TEXT, " +
                "`duration` INTEGER )");
        // Create table User
        sqLiteDatabase.execSQL("CREATE TABLE `User` ( " +
                "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                "`name` TEXT NOT NULL, " +
                "`password` TEXT NOT NULL, " +
                "`email` TEXT NOT NULL UNIQUE )");
        // Create table CinemaInformation
        sqLiteDatabase.execSQL("CREATE TABLE `CinemaInformation` ( " +
                "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                "`name` TEXT NOT NULL, " +
                "`telephoneNumber` INTEGER, " +
                "`address` TEXT, " +
                "`postcode` TEXT, " +
                "`city` TEXT, " +
                "`email` TEXT )");
        // Create table Review
        sqLiteDatabase.execSQL("CREATE TABLE `Review` ( \n" +
                "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, \n" +
                "`filmId` INTEGER, \n" +
                "`userId` INTEGER, \n" +
                "`message` TEXT NOT NULL, \n" +
                "CONSTRAINT fk_user FOREIGN KEY(`userId`) REFERENCES `User`(`id`) ON UPDATE CASCADE, \n" +
                "CONSTRAINT fk_film FOREIGN KEY(`filmId`) REFERENCES `Film`(`id`) ON UPDATE CASCADE )");
        // Create table CinemaRoom
        sqLiteDatabase.execSQL("CREATE TABLE `CinemaRoom` ( " +
                "`id` INTEGER NOT NULL UNIQUE, " +
                "`roomNumber` INTEGER NOT NULL )");
        // Create table Seat
        sqLiteDatabase.execSQL("CREATE TABLE `Seat` ( " +
                "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                "`roomId` INTEGER NOT NULL, " +
                "`seatNumber` INTEGER NOT NULL, " +
                "FOREIGN KEY(`roomId`) REFERENCES `CinemaRoom`(`id`) ON UPDATE CASCADE)");
        // Create table Reservation
        sqLiteDatabase.execSQL("CREATE TABLE `Reservation` ( " +
                "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                "`userId` INTEGER, " +
                "FOREIGN KEY(`userId`) REFERENCES `User`(`id`) ON UPDATE CASCADE)");
        // Create table Show
        sqLiteDatabase.execSQL("CREATE TABLE `Show` ( " +
                "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                "`roomId` INTEGER, " +
                "`filmId` INTEGER, " +
                "`startTime` TEXT NOT NULL, " +
                "`endTime` TEXT NOT NULL, " +
                "FOREIGN KEY(`filmId`) REFERENCES `Film`(`id`) ON UPDATE CASCADE, " +
                "FOREIGN KEY(`roomId`) REFERENCES `CinemaRoom`(`id`) ON UPDATE CASCADE)");
        // Create table Ticket
        sqLiteDatabase.execSQL("CREATE TABLE `Ticket` ( " +
                "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                "`reservationId` INTEGER, " +
                "`seatId` INTEGER, " +
                "`roomId` INTEGER, " +
                "`filmId` INTEGER, " +
                "`showId` INTEGER, " +
                "`qrCodeData` TEXT NOT NULL, " +
                "`price` REAL NOT NULL, " +
                "`date` TEXT NOT NULL, " +
                "`time` TEXT NOT NULL, " +
                "FOREIGN KEY(`showId`) REFERENCES `Show`(`id`) ON UPDATE CASCADE, " +
                "FOREIGN KEY(`seatId`) REFERENCES `Seat`(`id`) ON UPDATE CASCADE, " +
                "FOREIGN KEY(`filmId`) REFERENCES `Film`(`id`) ON UPDATE CASCADE, " +
                "FOREIGN KEY(`reservationId`) REFERENCES `Reservation`(`id`) ON UPDATE CASCADE, " +
                "FOREIGN KEY(`roomId`) REFERENCES `CinemaRoom`(`id`) ON UPDATE CASCADE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
