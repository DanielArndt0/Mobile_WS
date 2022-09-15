package com.app.academia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.app.academia.classes.repository.DAO;
import com.app.academia.classes.validations.Check;
import com.app.academia.classes.validations.FieldCheck;
import com.app.academia.databinding.ActivitySigninBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;
import java.util.regex.Pattern;

public class SigninActivity extends AppCompatActivity {

    ActivitySigninBinding binding;

    TextInputEditText name, user, password, email, cel, repassword;
    TextInputLayout nameBox, userBox, passwordBox, emailBox, celBox, repasswordBox;
    MaterialButton cancel, signin;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        init();
    }

    private void init() {
        name = binding.cadNome;
        email = binding.cadEmail;
        cel = binding.cadCelular;
        password = binding.cadSenha;
        repassword = binding.cadRSenha;
        user = binding.cadUser;

        nameBox = binding.cadNomeBox;
        emailBox = binding.cadEmailBox;
        celBox = binding.cadCelularBox;
        passwordBox = binding.cadSenhaBox;
        repasswordBox = binding.cadRSenhaBox;
        userBox = binding.cadUserBox;

        signin = binding.cadCad;
        cancel = binding.cadCancel;


        setSupportActionBar(binding.cadToolbar);
        actionBar = getSupportActionBar();
        if (actionBar == null) {
            throw new NullPointerException();
        }
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_32);

        listeners();
    }

    public static boolean constraint(String str, String conditions) {
        return Pattern.compile(conditions)
                .matcher(str)
                .find();
    }

    private void listeners() {
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buildAlert();
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sName = name.getText().toString();
                String sPassword = password.getText().toString();
                String sEmail = email.getText().toString();
                String sUser = user.getText().toString();
                String sRePass = repassword.getText().toString();

                DAO db = new DAO(getApplicationContext(), getString(R.string.fln), sUser);

                FieldCheck fieldCheck = new FieldCheck();
                if (fieldCheck.execute(Arrays.asList(
                        new Check(nameBox, !constraint(sName, "[\\w_.] [\\w_.]"), "Precisa conter nome e sobrenome"),
                        new Check(emailBox, !constraint(sEmail, "^([\\p{Alpha}][\\w_.]{5,}@[\\w_.]{3,}[.com|.com.br])$"), "Email inválido"),
                        new Check(userBox, !constraint(sUser, "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[\\p{Alpha}][\\w_.]{5,}$") || sUser.equals(db.get("user")), "Usuário inválido"),
                        new Check(passwordBox, !constraint(sPassword, "^(?=.*[A-Z])(?=.*[a-z])(?=.*[\\d])[\\w_.]{8,}$"), "Senha inválida"),
                        new Check(repasswordBox, !sRePass.equals(sPassword), "Este campo deve ser igual a senha")
                ))) {

                    db.set(getString(R.string.user), sUser);
                    db.set(getString(R.string.name), sName);
                    db.set(getString(R.string.password), sPassword);
                    db.set(getString(R.string.email), sEmail);

                    Toast.makeText(SigninActivity.this, "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                }
                fieldCheck.clear();
            }
        });
    }

    private void buildAlert() {
        AlertDialog.Builder alert = new AlertDialog.Builder(SigninActivity.this);
        alert.setTitle("Alerta")
                .setMessage("Deseja cancelar o cadastro?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(SigninActivity.this, LoginActivity.class));
                        finish();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            buildAlert();
        }
        return true;
    }
}