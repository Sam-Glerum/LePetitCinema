package com.example.icadi.lepetitcinema.Persistency.DaoInterfaces;

import com.example.icadi.lepetitcinema.Domain.CinemaRoom;

/**
 * Created by icadi on 28-3-18.
 */

public interface CinemaRoomDAO {
    CinemaRoom get(int id);
    boolean remove(int id);
    boolean update(CinemaRoom cinemaRoom);
    boolean create(CinemaRoom cinemaRoom);
}
