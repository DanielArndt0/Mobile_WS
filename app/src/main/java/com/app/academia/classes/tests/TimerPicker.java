package com.app.academia.classes.tests;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimerPicker extends DialogFragment {
    TimePickerDialog.OnTimeSetListener listener;
    Calendar c;

    public TimerPicker(TimePickerDialog.OnTimeSetListener listener, Calendar c) {
        this.listener = listener;
        this.c = c;
    }

    @Override
    public Dialog onCreateDialog(Bundle args) {
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), listener, hour, minute, true);
    }
}
