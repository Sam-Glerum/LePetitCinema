package com.example.icadi.lepetitcinema.Persistency.SQLiteDAOs;

import com.example.icadi.lepetitcinema.Domain.User;
import com.example.icadi.lepetitcinema.Persistency.DaoInterfaces.UserDAO;

/**
 * Created by icadi on 28-3-18.
 */

public class SQLiteUserDAO implements UserDAO {

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean create(User user) {
        return false;
    }
}
