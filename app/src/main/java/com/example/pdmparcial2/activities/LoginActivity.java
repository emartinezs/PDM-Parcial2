package com.example.pdmparcial2.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pdmparcial2.R;
import com.example.pdmparcial2.api.APIRequest;
import com.example.pdmparcial2.database.viewmodels.NewViewModel;
import com.example.pdmparcial2.utils.ActivityManager;

public class LoginActivity extends AppCompatActivity {

    private APIRequest apiRequest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final View loadingLayout = findViewById(R.id.login_loadingLayout);
        final EditText usernameEditText = findViewById(R.id.login_usernameEditText);
        final EditText passwordEditText = findViewById(R.id.login_passwordEditText);
        final Button loginButton = findViewById(R.id.login_loginButton);

        final NewViewModel newViewModel = ViewModelProviders.of(this).get(NewViewModel.class);
        apiRequest = new APIRequest(this, loadingLayout, newViewModel, null, null);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                //Verifica si los campos estan vacios
                if (username.matches("") || password.matches("")) {
                    ActivityManager.showToast(getApplicationContext(), getString(R.string.error_empty_fields));
                } else {
                    apiRequest.login(username, password);
                    new WaitAsyncTask().execute();
                }
            }
        });
    }

    //Espera hasta que el API termine el proceso de login
    private class WaitAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            while (!apiRequest.isLogged()) {
                //WAIT
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void voids) {
            closeActivity();
        }
    }

    //Cierra la actividad
    public void closeActivity() {
        finish();
    }
}
