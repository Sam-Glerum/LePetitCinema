package com.example.icadi.lepetitcinema.Persistency.Database;

import java.io.File;
import java.util.HashMap;

/**
 * Created by icadi on 29-3-18.
 */

public class DatabaseProperties {
    File file;

    public DatabaseProperties(File file) {
        this.file = file;
    }

    public String readFile() {
        return "";
    }

    public HashMap<String, Object> getProperties() {
        return new HashMap<>();
    }
}
