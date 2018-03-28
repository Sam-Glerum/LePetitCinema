package com.example.icadi.lepetitcinema.Persistency.SQLiteDAOs;

import com.example.icadi.lepetitcinema.Domain.Film;
import com.example.icadi.lepetitcinema.Persistency.DaoInterfaces.FilmDAO;

/**
 * Created by icadi on 28-3-18.
 */

public class SQLiteFilmDAO implements FilmDAO {

    @Override
    public Film get(int id) {
        return null;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }

    @Override
    public boolean update(Film film) {
        return false;
    }

    @Override
    public boolean create(Film film) {
        return false;
    }
}
