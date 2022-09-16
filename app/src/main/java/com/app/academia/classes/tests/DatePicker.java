package com.app.academia.classes.tests;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePicker extends DialogFragment {
    private final DatePickerDialog.OnDateSetListener listener;
    private final Calendar c;

    public DatePicker(DatePickerDialog.OnDateSetListener listener, Calendar calendar) {
        this.listener = listener;
        this.c = calendar;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle args) {
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog pickerDialog = new DatePickerDialog(getActivity(), listener, year, month, day);
        pickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
        return pickerDialog;
    }
}