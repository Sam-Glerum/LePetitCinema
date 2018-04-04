package com.example.icadi.lepetitcinema.Persistency.DaoInterfaces;

import com.example.icadi.lepetitcinema.Domain.Film;

/**
 * Created by icadi on 28-3-18.
 */

public interface FilmDAO {
    Film get(int id);
    boolean remove(int id);
    boolean update(Film film);
    boolean create(Film film);
}
