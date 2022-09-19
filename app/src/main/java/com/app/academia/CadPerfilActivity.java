package com.app.academia;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AlertDialogLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PackageManagerCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.app.academia.classes.components.CheckboxGroup;
import com.app.academia.classes.pickers.DatePicker;
import com.app.academia.classes.pickers.TimePicker;
import com.app.academia.classes.repository.DAO;
import com.app.academia.classes.repository.DB;
import com.app.academia.classes.utils.DateUtils;
import com.app.academia.classes.validations.Check;
import com.app.academia.classes.validations.FieldCheck;
import com.app.academia.databinding.ActivityCadPerfilBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.timepicker.TimeFormat;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class CadPerfilActivity extends AppCompatActivity {

    ActivityCadPerfilBinding binding;
    CheckboxGroup runningGroup, cordGroup, walkingGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadPerfilBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        init();
    }

    private void init() {
        // Inicia as listas de checkboxes
        runList();
        walkList();
        cordList();

        // Inicializa os listeners
        dateListener();
        timeListener();
        buttonListener();
        checkboxesListeners();
    }

    private void buttonListener() {
        binding.logAcessar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                imc();

                Period between = DateUtils.getPeriodBetween(binding.dataNascimento.getText().toString(), LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), "dd/MM/yyyy");

                FieldCheck fieldCheck = new FieldCheck();
                if (fieldCheck.execute(Arrays.asList(
                        new Check(binding.dataNascimentoBox, binding.dataNascimento.getText().toString().isEmpty() || between.getYears() < 18, "Campo obrigatório\n- O cadastro só é possível para maiores de idade"),
                        new Check(binding.pesoBox, binding.peso.getText().toString().isEmpty(), "Campo obrigatório"),
                        new Check(binding.alturaBox, binding.altura.getText().toString().isEmpty(), "Campo obrigatório")
                ))) {
                    Toast.makeText(CadPerfilActivity.this, "Registrado", Toast.LENGTH_SHORT).show();
                    new DAO(getApplicationContext(), getString(R.string.fln), "").setGlobal(getString(R.string.profile), "true");
                }
                fieldCheck.clear();
            }
        });
    }

    private void runList() {
        runningGroup = new CheckboxGroup(Arrays.asList(
                binding.runDom,
                binding.runSeg,
                binding.runTer,
                binding.runQua,
                binding.runQui,
                binding.runSex,
                binding.runSab
        ), binding.runCheck.isChecked());
        binding.runInpText.setEnabled(binding.runCheck.isChecked());
    }

    private void walkList() {
        walkingGroup = new CheckboxGroup(Arrays.asList(
                binding.dom,
                binding.seg,
                binding.ter,
                binding.qua,
                binding.qui,
                binding.sex,
                binding.sab
        ), binding.camCheck.isChecked());
        binding.camInpText.setEnabled(binding.camCheck.isChecked());
    }

    private void cordList() {
        cordGroup = new CheckboxGroup(Arrays.asList(
                binding.cordDom,
                binding.cordSeg,
                binding.cordTer,
                binding.cordQua,
                binding.cordQui,
                binding.cordSex,
                binding.cordSab
        ), binding.cordCheck.isChecked());
        binding.cordInpText.setEnabled(binding.cordCheck.isChecked());
    }


    private void checkboxesListeners() {
        binding.runCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runList();
            }
        });

        binding.camCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                walkList();
            }
        });

        binding.cordCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cordList();
            }
        });
    }


    @SuppressLint("DefaultLocale")
    private void imc() {
        String peso = binding.peso.getText().toString(), altura = binding.altura.getText().toString();

        if (peso.isEmpty() || altura.isEmpty()) {
            return;
        }

        double sPeso = Double.parseDouble(binding.peso.getText().toString());
        double sAltura = Double.parseDouble(binding.altura.getText().toString());
        double result = sPeso / (sAltura * sAltura);

        if (result < 18.5) {
            binding.imcText.setText(String.format("Seu IMC é de: %.2f, o que indica %s!", result, "MAGREZA!"));
        } else if (result > 18.5 && result < 24.9) {
            binding.imcText.setText(String.format("Seu IMC é de: %.2f, o que indica %s!", result, "NORMAL!"));
        } else if (result > 25.0 && result < 29.9) {
            binding.imcText.setText(String.format("Seu IMC é de: %.2f, o que indica %s!", result, "SOBREPESO!"));
        } else if (result > 30.0 && result < 39.9) {
            binding.imcText.setText(String.format("Seu IMC é de: %.2f, o que indica %s!", result, "OBESIDADE!"));
        } else if (result >= 40.0) {
            binding.imcText.setText(String.format("Seu IMC é de: %.2f, o que indica %s!", result, "OBESIDADE GRAVE!"));
        }
    }


    private void dateListener() {
        DialogFragment dateDialog = new DatePicker(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker datePicker, int i, int i1, int i2) {
                Calendar c = Calendar.getInstance();
                c.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                binding.dataNascimento.setText(new SimpleDateFormat("dd/MM/yyyy").format(c.getTime()));
            }
        }, Calendar.getInstance());

        binding.dataNascimento.setOnClickListener(new View.OnClickListener() {
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
        createTimeListener(binding.camInpText);
        createTimeListener(binding.runInpText);
        createTimeListener(binding.cordInpText);
    }
}