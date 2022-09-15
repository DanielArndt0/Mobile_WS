package com.app.academia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AlertDialogLayout;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.app.academia.classes.pickers.DatePicker;
import com.app.academia.classes.pickers.TimePicker;
import com.app.academia.databinding.ActivityCadPerfilBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.timepicker.TimeFormat;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class CadPerfilActivity extends AppCompatActivity {

    ActivityCadPerfilBinding binding;
    TextInputLayout nascimentoBox;
    TextInputEditText nascimento, hr1, hr2, hr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadPerfilBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        init();
        dateListener();
        timeListener();
    }

    private void init() {
        nascimentoBox = binding.dataNascimentoBox;
        nascimento = binding.dataNascimento;
        hr1 = binding.camInpText;
        hr2 = binding.runInpText;
        hr3 = binding.cordInpText;

        /**
         * Falta linkar checkboxes
         */
    }


    private void dateListener() {
        DialogFragment dateDialog = new DatePicker(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker datePicker, int i, int i1, int i2) {
                DateFormat format = SimpleDateFormat.getDateInstance();
                Calendar c = Calendar.getInstance();
                c.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                Date date = c.getTime();
                nascimento.setText(format.format(date));
            }
        }, Calendar.getInstance());

        nascimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateDialog.show(getSupportFragmentManager(), "Date Picker");
            }
        });
    }

    private void createTimeListener(TextInputEditText editText) {
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timeDialog = new TimePicker(new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(android.widget.TimePicker timePicker, int i, int i1) {
                        Calendar c = Calendar.getInstance();
                        c.set(0, 0, 0, timePicker.getHour(), timePicker.getMinute(), 0);
                        editText.setText(new SimpleDateFormat("HH:mm").format(c.getTime()));
                    }
                }, Calendar.getInstance());
                timeDialog.show(getSupportFragmentManager(), "Time Picker");
            }
        });
    }

    private void timeListener() {
        createTimeListener(hr1);
        createTimeListener(hr2);
        createTimeListener(hr3);
    }
}