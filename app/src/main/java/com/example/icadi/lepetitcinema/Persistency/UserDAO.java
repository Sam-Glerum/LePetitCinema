package com.example.icadi.lepetitcinema.Persistency;

import com.example.icadi.lepetitcinema.Domain.User;

/**
 * Created by icadi on 28-3-18.
 */

public interface UserDAO {
    User get(int id);
    boolean remove(int id);
    boolean update(User user);
    boolean create(User user);
}
