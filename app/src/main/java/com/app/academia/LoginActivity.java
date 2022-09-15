package com.app.academia;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.app.academia.classes.repository.DAO;
import com.app.academia.classes.validations.Check;
import com.app.academia.classes.validations.FieldCheck;
import com.app.academia.databinding.ActivityLoginBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    TextInputEditText user, password;
    TextInputLayout userBox, passwordBox;
    MaterialButton login, signin;
    int attempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        init();
    }

    private void init() {
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();


        user = binding.logUser;
        password = binding.logSenha;
        userBox = binding.logUserBox;
        passwordBox = binding.logSenhaBox;
        login = binding.logAcessar;
        signin = binding.logCad;
        attempts = 0;

        listeners();
    }


    private void listeners() {
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SigninActivity.class));
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sUser = user.getText().toString();
                String sPassword = password.getText().toString();
                DAO db = new DAO(getApplicationContext(), getString(R.string.fln), sUser);

                String userDB = db.get(getString(R.string.user));
                String passwordDB = db.get(getString(R.string.password));

                FieldCheck fieldCheck = new FieldCheck();
                if (fieldCheck.execute(Arrays.asList(
                        new Check(userBox, !userDB.equals(sUser) || sUser.isEmpty(), "Usuário não encontrado"),
                        new Check(passwordBox, !passwordDB.equals(sPassword) || sUser.isEmpty(), "Senha incorreta")
                ))) {
                    db.setGlobal(getString(R.string.logged), sUser);

                    if (!Boolean.parseBoolean(db.getGlobal(getString(R.string.profile)))) {
                        startActivity(new Intent(LoginActivity.this, CadPerfilActivity.class));
                    } else {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                    finish();

                } else {
                    attempts++;
                    Toast.makeText(LoginActivity.this, "Usuário/Senha inválidos!", Toast.LENGTH_SHORT).show();
                    checkAttempts(3, 10000);
                }
                fieldCheck.clear();
            }
        });
    }

    @SuppressLint("DefaultLocale")
    private void checkAttempts(int limitAttempts, long blockedTime) {
        if (attempts == limitAttempts) {
            buttonEnabled(false);
            fieldTextEnabled(false);
            Toast.makeText(LoginActivity.this, String.format("Login bloqueado: aguarde %ds!", blockedTime / 1000), Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    attempts = 0;
                    buttonEnabled(true);
                    fieldTextEnabled(false);
                }
            }, blockedTime);
        }
    }

    private void ifFirstLogin() {

    }

    private void buttonEnabled(boolean status) {
        login.setEnabled(status);
        signin.setEnabled(status);
        login.setBackgroundColor(status ? getColor(android.R.color.holo_green_dark) : getColor(android.R.color.darker_gray));
        signin.setBackgroundColor(status ? getColor(android.R.color.holo_green_dark) : getColor(android.R.color.darker_gray));
    }

    private void fieldTextEnabled(boolean status) {
        userBox.setEnabled(status);
        passwordBox.setEnabled(status);
    }
}