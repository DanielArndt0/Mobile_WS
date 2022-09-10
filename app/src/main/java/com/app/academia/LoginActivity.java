package com.app.academia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app.academia.databinding.ActivityLoginBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

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
            }
        });
    }
}