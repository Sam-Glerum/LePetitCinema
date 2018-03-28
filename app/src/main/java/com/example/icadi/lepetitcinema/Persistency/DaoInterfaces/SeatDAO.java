package com.example.icadi.lepetitcinema.Persistency.DaoInterfaces;

import com.example.icadi.lepetitcinema.Domain.Seat;

/**
 * Created by icadi on 28-3-18.
 */

public interface SeatDAO {
    Seat get(int id);
    boolean remove(int id);
    boolean update(Seat seat);
    boolean create(Seat seat);
}
