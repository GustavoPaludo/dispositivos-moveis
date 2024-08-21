package com.udesc.projetofinal;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.udesc.projetofinal.servicews.UserServiceWS;
import com.udesc.projetofinal.models.UserModel;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        Button buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                new LoginTask().execute(email, password);
            }
        });
    }

    private class LoginTask extends AsyncTask<String, Void, UserModel> {
        @Override
        protected UserModel doInBackground(String... params) {
            String email = params[0];
            String password = params[1];
            UserServiceWS userServiceWS = new UserServiceWS();
            return userServiceWS.doLogin(email, password, LoginActivity.this);
        }

        @Override
        protected void onPostExecute(UserModel userModel) {
            if (userModel != null) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Login falhou", Toast.LENGTH_SHORT).show();
            }
        }
    }
}