package com.example.absenceviewer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation des composants
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        // Gestion du clic sur le bouton de connexion
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (authenticateUser(username, password)) {
                    Toast.makeText(MainActivity.this, "Connexion réussie !", Toast.LENGTH_SHORT).show();
                    // Rediriger vers StudentActivity après connexion réussie
                    Intent intent = new Intent(MainActivity.this, StudentActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Nom d'utilisateur ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Méthode d'authentification simulée
    private boolean authenticateUser(String username, String password) {
        return username.equals("admin") && password.equals("1234"); // Exemple simple, à remplacer par une vraie authentification
    }
}
