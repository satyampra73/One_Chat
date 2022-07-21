package com.satyam.onechat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText et_email, et_password;
    AppCompatButton loginBtn;
    Toolbar toolbar;
    String email, password;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        toolbar = findViewById(R.id.toolbarlogin);

        et_email = findViewById(R.id.etEmail);
        progressBar = findViewById(R.id.progressBar);
        et_password = findViewById(R.id.etPassword);
        loginBtn = findViewById(R.id.login_account);
        btnBack=toolbar.findViewById(R.id.btnBack);

        mAuth = FirebaseAuth.getInstance();


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = et_email.getText().toString();
                password = et_password.getText().toString();


                if (TextUtils.isEmpty(email)) {

                    Toast.makeText(LoginActivity.this, "Email Required", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(password)) {

                    Toast.makeText(LoginActivity.this, "Password Required", Toast.LENGTH_SHORT).show();
                } else {
                    LoginMeIn(email, password);
                }


            }
        });


    }

    private void LoginMeIn(String email, String password) {

        progressBar.setVisibility(View.VISIBLE);


        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful()) {

                    progressBar.setVisibility(View.INVISIBLE);

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                    Toast.makeText(LoginActivity.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();

                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(LoginActivity.this, "Failed To Login", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}