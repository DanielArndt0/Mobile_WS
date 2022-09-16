package com.app.academia.classes.tests;

import android.content.Context;

import com.app.academia.R;

public class DAO {
    private final Context context;
    private final String filename;
    private final String dbquerry;
    private final String id;

    public DAO(Context context, String filename, String dbquerry, String id) {
        this.context = context;
        this.filename = filename;
        this.dbquerry = context.getString(R.string.querry);
        this.id = id;
    }

    public String get(String key) {
        return DB.get(context, filename)
                .getString(String.format(dbquerry, id, key), "");
    }

    public void set(String key, String value) {
        DB.set(context, filename)
                .putString(String.format(dbquerry, id, key), value);
    }
}
