package com.arguelles.miniagenda;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        ListView listView = findViewById(R.id.listView);
        Button contactosButton = findViewById(R.id.volver);
        ArrayList<String> contactList = readContactsFromFile();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactList);
        listView.setAdapter(adapter);

        contactosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private ArrayList<String> readContactsFromFile() {
        ArrayList<String> contacts = new ArrayList<>();
        try {
            FileInputStream fileInputStream = openFileInput("contactes.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(";");

                String formattedContact = "Nom: " + parts[0] + "\n" +
                        "Cognoms: " + parts[1] + "\n" +
                        "Telèfon: " + parts[2] + "\n" +
                        "Email: " + parts[3];

                contacts.add(formattedContact);
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
    }
}
