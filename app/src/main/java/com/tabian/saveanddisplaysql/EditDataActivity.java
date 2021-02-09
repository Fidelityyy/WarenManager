package com.tabian.saveanddisplaysql;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by User on 2/28/2017.
 */

public class EditDataActivity extends AppCompatActivity {

    private static final String TAG = "EditDataActivity";

    private Button btnSave, btnDelete;
    private EditText editable_item;
    private EditText editPreis;
    private EditText editAnzahl;

    DatabaseHelper mDatabaseHelper;

    private String selectedName;
    private int selectedID;
    private double selectedPreis;
    private int selectedAnzahl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_layout);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        editable_item = (EditText) findViewById(R.id.editable_item);
        editPreis = (EditText) findViewById(R.id.editPreis);
        editAnzahl = (EditText) findViewById(R.id.editAnzahl);

        mDatabaseHelper = new DatabaseHelper(this);

        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id", -1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        selectedName = receivedIntent.getStringExtra("name");

        selectedPreis = receivedIntent.getDoubleExtra("preis", 0);

        selectedAnzahl = receivedIntent.getIntExtra("anzahl", 0);

        //set the text to show the current selected name
        editable_item.setText(selectedName);
        editAnzahl.setText(Integer.toString(selectedAnzahl));
        editPreis.setText(Double.toString(selectedPreis));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editable_item.getText().toString();
                String preis = editPreis.getText().toString();
                String anzahl = editAnzahl.getText().toString();
                if (!item.equals("") || !preis.equals("") || !anzahl.equals("")) {
                    mDatabaseHelper.updateName(item, selectedID, selectedName, selectedPreis, selectedAnzahl);
                    Intent intent = new Intent(EditDataActivity.this, ListDataActivity.class);
                    startActivity(intent);
                } else {
                    toastMessage("Alle Felder ausfüllen!");
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteName(selectedID, selectedName, selectedPreis, selectedAnzahl);
                editable_item.setText("");
                editAnzahl.setText("");
                editPreis.setText("");
                toastMessage("Aus der Datenbank entfernt.");
                Intent intent = new Intent(EditDataActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * customizable toast
     *
     * @param message
     */
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent back = new Intent(EditDataActivity.this, ListDataActivity.class);
        startActivity(back);
    }
}