package com.app.academia.classes.repository;

import android.content.Context;
import android.content.SharedPreferences;

public class DB {
    public static SharedPreferences.Editor set(Context context, String filename) {
        return context.getSharedPreferences(filename, Context.MODE_PRIVATE).edit();
    }

    public static SharedPreferences get(Context context, String filename) {
        return context.getSharedPreferences(filename, Context.MODE_PRIVATE);
    }
}
