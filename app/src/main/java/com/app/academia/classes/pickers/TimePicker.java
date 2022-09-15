package com.app.academia.classes.pickers;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class TimePicker extends DialogFragment {
    private final TimePickerDialog.OnTimeSetListener listener;
    private final Calendar calendar;


    public TimePicker(TimePickerDialog.OnTimeSetListener listener, Calendar calendar) {
        this.listener = listener;
        this.calendar = calendar;
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), listener, hour, minute, true);
    }

}
