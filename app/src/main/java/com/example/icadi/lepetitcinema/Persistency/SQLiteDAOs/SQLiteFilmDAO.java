package com.example.icadi.lepetitcinema.Persistency.SQLiteDAOs;

import android.database.Cursor;

import com.example.icadi.lepetitcinema.Domain.Film;
import com.example.icadi.lepetitcinema.Persistency.DaoInterfaces.FilmDAO;
import com.example.icadi.lepetitcinema.Persistency.Database.DatabaseConnector;

import java.sql.ResultSet;

/**
 * Created by icadi on 28-3-18.
 */

public class SQLiteFilmDAO implements FilmDAO {
    DatabaseConnector databaseConnector = new DatabaseConnector("LePetitCinema.db", "", "", "", 0);

    @Override
    public Film get(int id) {
        Film film = null;
        String query = "SELECT * FROM Film WHERE id = " + id + ";";
        ResultSet queryResult = databaseConnector.selectStatement(query);

        try {
            while (queryResult.next()) {
                int filmId = id;
                String name = queryResult.getString("name");
                String description = queryResult.getString("description");
                int duration = queryResult.getInt("duration");
                film = new Film(name, description, 0, "", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return film;
    }

    @Override
    public boolean remove(int id) {
        String query = "DELETE * FROM Film WHERE id = " + id + ";";
        boolean isDeleted = databaseConnector.deleteStatement(query);
        if (isDeleted) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Film film, String databaseField, String updatedValue) {
        String filmName = film.getName();
        String query = "UPDATE Film SET " + databaseField + " = " + updatedValue + " WHERE name = " + filmName;
        boolean isUpdated = databaseConnector.updateStatement(query);
        if (isUpdated) {
            return true;
        }
        return false;
    }

    @Override
    public boolean create(Film film) {
        String filmName = film.getName();
        String description = film.getDescription();
        double rating = film.getRating();
        String posterImageUrl = film.getPosterImageUrl();
        String backgroundImageUrl = film.getBackgroundImageUrl();

        String query = "INSERT INTO Film VALUES(" + filmName + ", " + description + ", '', " + rating + ", " + posterImageUrl + ", " + backgroundImageUrl + ";";
        boolean isInserted = databaseConnector.insertStatement(query);
        if (isInserted) {
            return true;
        }
        return false;
    }
}
