package com.example.icadi.lepetitcinema.Persistency.SQLiteDAOs;

import com.example.icadi.lepetitcinema.Domain.Seat;
import com.example.icadi.lepetitcinema.Persistency.DaoInterfaces.SeatDAO;

/**
 * Created by icadi on 28-3-18.
 */

public class SQLiteSeatDAO implements SeatDAO{

    @Override
    public Seat get(int id) {
        return null;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }

    @Override
    public boolean update(Seat seat) {
        return false;
    }

    @Override
    public boolean create(Seat seat) {
        return false;
    }
}
