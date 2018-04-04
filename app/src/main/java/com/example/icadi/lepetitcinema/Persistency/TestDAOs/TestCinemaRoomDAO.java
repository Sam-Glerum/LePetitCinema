package com.example.icadi.lepetitcinema.Persistency.TestDAOs;

import com.example.icadi.lepetitcinema.Domain.CinemaRoom;
import com.example.icadi.lepetitcinema.Persistency.DaoInterfaces.CinemaRoomDAO;

/**
 * Created by icadi on 28-3-18.
 */

public class TestCinemaRoomDAO implements CinemaRoomDAO {

    @Override
    public CinemaRoom get(int id) {
        return null;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }

    @Override
    public boolean update(CinemaRoom cinemaRoom) {
        return false;
    }

    @Override
    public boolean create(CinemaRoom cinemaRoom) {
        return false;
    }
}
