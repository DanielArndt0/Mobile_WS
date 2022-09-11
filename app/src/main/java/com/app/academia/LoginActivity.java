package com.app.academia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
                DAO db = new DAO(getApplicationContext(), "db", sUser);

                String userDB = db.get("user");
                String passwordDB = db.get("password");

                FieldCheck fieldCheck = new FieldCheck();
                if (fieldCheck.execute(Arrays.asList(
                        new Check(userBox, !userDB.equals(sUser) || sUser.isEmpty(), "Usuário não encontrado"),
                        new Check(passwordBox, !passwordDB.equals(sPassword) || sUser.isEmpty(), "Senha incorreta")
                ))) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
                fieldCheck.clear();
            }
        });
    }
}