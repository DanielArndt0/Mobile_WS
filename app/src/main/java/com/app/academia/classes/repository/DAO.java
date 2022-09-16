package com.app.academia.classes.repository;

import android.content.Context;
import com.app.academia.R;

public class DAO {
    private final Context context;
    private final String filename;
    private final String dbquerry;
    private final String id;

    public DAO(Context context, String filename, String id) {
        this.context = context;
        this.filename = filename;
        this.id = id;
        this.dbquerry = context.getString(R.string.querry);
    }

    public void setGlobal(String key, String value) {
        DB.set(context, filename)
                .putString(key, value)
                .apply();
    }

    public String getGlobal(String key) {
        return DB.get(context, filename)
                .getString(key, "");
    }



    public void set(String key, String value) {
        DB.set(context, filename)
                .putString(String.format(dbquerry, id, key), value)
                .apply();
    }

    public String get(String key) {
        return DB.get(context, filename)
                .getString(String.format(dbquerry, id, key), "");
    }
}
