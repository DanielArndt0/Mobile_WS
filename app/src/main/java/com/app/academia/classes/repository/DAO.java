package com.app.academia.classes.repository;

import android.content.Context;
import com.app.academia.R;

public class DAO {
    private final Context context;
    private final String filename;
    private final String dbquerry;
    private String id;

    public DAO(Context context, String filename) {
        this.context = context;
        this.filename = filename;
        this.dbquerry = context.getString(R.string.querry);
    }

    public void setId(String id) {
        this.id = id;
    }

    public DAO(Context context, String filename, String id) {
        this.context = context;
        this.filename = filename;
        this.id = id;
        this.dbquerry = context.getString(R.string.querry);
    }

    public void set(String key, String value) {
        if (this.id == null) {
            throw new NullPointerException();
        }
        DB.set(context, filename)
                .putString(String.format(dbquerry, id, key), value)
                .apply();
    }

    public String get(String key) {
        if (this.id == null) {
            throw new NullPointerException();
        }
        return DB.get(context, filename)
                .getString(String.format(dbquerry, id, key), new String());
    }
}
