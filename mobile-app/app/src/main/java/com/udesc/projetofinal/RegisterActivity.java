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

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextName, editTextSurname, editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextName = findViewById(R.id.editTextName);
        editTextSurname = findViewById(R.id.editTextSurname);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        Button buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String surname = editTextSurname.getText().toString();
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                new RegisterTask().execute(name, surname, email, password);
            }
        });
    }

    private class RegisterTask extends AsyncTask<String, Void, UserModel> {
        @Override
        protected UserModel doInBackground(String... params) {
            String name = params[0];
            String surname = params[1];
            String email = params[2];
            String password = params[3];

            UserServiceWS userServiceWS = new UserServiceWS();
            UserModel userModel = new UserModel();
            userModel.setName(name);
            userModel.setLastName(surname);
            userModel.setEmail(email);
            userModel.setPassword(password);

            return userServiceWS.doRegister(userModel, RegisterActivity.this);
        }

        @Override
        protected void onPostExecute(UserModel registeredUser) {
            if (registeredUser != null) {
                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(RegisterActivity.this, "Registro falhou", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
