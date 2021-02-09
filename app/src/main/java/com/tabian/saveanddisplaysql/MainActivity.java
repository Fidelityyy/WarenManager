package com.tabian.saveanddisplaysql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    DatabaseHelper mDatabaseHelper;
    private Button btnAdd, btnViewData;
    private EditText produktNameFeld, anzahlFeld, preisFeld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        produktNameFeld = (EditText) findViewById(R.id.produktName);
        anzahlFeld = (EditText) findViewById(R.id.anzahlFeld);
        preisFeld = (EditText) findViewById(R.id.preisFeld);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnViewData = (Button) findViewById(R.id.btnView);
        mDatabaseHelper = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = produktNameFeld.getText().toString();
//                int anzahl = Integer.parseInt(anzahlFeld.getText().toString());
//                double preis = Double.parseDouble(preisFeld.getText().toString());
                int anzahl;
                double preis;
                if(anzahlFeld.getText().toString().equals("")) {
                    anzahl = 0;
                } else {
                    anzahl = Integer.parseInt(anzahlFeld.getText().toString());
                }

                if(preisFeld.getText().toString().equals("")) {
                    preis = 0;
                } else {
                    preis = Double.parseDouble(preisFeld.getText().toString());
                }

                if (produktNameFeld.length() != 0 || anzahlFeld.length() != 0 || preisFeld.length() != 0) {
                    AddData(newEntry, anzahl, preis);
                    produktNameFeld.setText("");
                    anzahlFeld.setText("");
                    preisFeld.setText("");
                } else {
                    toastMessage("Alle Felder ausfüllen!");
                }

            }
        });

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });

    }

    public void AddData(String newEntry, int anzahl, double preis) {
        boolean insertData = mDatabaseHelper.addData(newEntry, anzahl, preis);

        if (insertData) {
            toastMessage("Daten zur Datenbank hinzugefügt!");
        } else {
            toastMessage("Irgendwas ist schief gelaufen!");
        }
    }

    /**
     * customizable toast
     *
     * @param message
     */
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}