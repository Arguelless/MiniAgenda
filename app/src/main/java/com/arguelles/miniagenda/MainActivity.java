package com.arguelles.miniagenda;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText nomEditText, cognomEditText, telefonEditText, emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomEditText = findViewById(R.id.nomTextText);
        cognomEditText = findViewById(R.id.cognomTextText2);
        telefonEditText = findViewById(R.id.telefonTextPhone);
        emailEditText = findViewById(R.id.emailTextEdit);

        Button guardarButton = findViewById(R.id.guardarButton);
        Button contactosButton = findViewById(R.id.contactosButton);

        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarContacto();
            }
        });

        contactosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para ir a la nueva actividad (ContactListActivity)
                Intent intent = new Intent(MainActivity.this, ContactListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void guardarContacto() {
        String nom = nomEditText.getText().toString();
        String cognoms = cognomEditText.getText().toString();
        String telefon = telefonEditText.getText().toString();
        String email = emailEditText.getText().toString();

        if (nom.isEmpty() || cognoms.isEmpty() || telefon.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        String contacto = nom + ";" + cognoms + ";" + telefon + ";" + email;

        try {
            FileOutputStream fileOutputStream = openFileOutput("contactes.txt", Context.MODE_APPEND);

            fileOutputStream.write(contacto.getBytes());
            fileOutputStream.write("\n".getBytes()); // Salto de línea para futuros contactos

            fileOutputStream.close();

            Toast.makeText(this, "Contacto guardado correctamente", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Toast.makeText(this, "Error al guardar el contacto", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
