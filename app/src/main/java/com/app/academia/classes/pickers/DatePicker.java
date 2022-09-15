package com.app.academia.classes.pickers;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;

public class DatePicker extends DialogFragment {
    private final DatePickerDialog.OnDateSetListener listener;
    private final Calendar calendar;

    public DatePicker(DatePickerDialog.OnDateSetListener listener, Calendar calendar) {
        this.listener = listener;
        this.calendar = calendar;
    }

    public Date getTime() {
        return calendar.getTime();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog pickerDialog = new DatePickerDialog(getActivity(), listener, year, month, day);
        pickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        return pickerDialog;
    }
}
