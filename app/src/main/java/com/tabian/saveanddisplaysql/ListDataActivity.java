package com.tabian.saveanddisplaysql;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by User on 2/28/2017.
 */

public class ListDataActivity extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";

    DatabaseHelper mDatabaseHelper;

    private ListView mListView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        mListView = (ListView) findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(this);

        populateListView();
    }

    @Override
    public void onBackPressed() {
        Intent back = new Intent(ListDataActivity.this, MainActivity.class);
        startActivity(back);
    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        //get the data and append to a list
        ArrayList<Produkt> data = mDatabaseHelper.getData();

        //create the list adapter and set the adapter
        CustomAdapter adapter = new CustomAdapter(data, this);
        mListView.setAdapter(adapter);

        double ergebnis = 0;

        for(Produkt p : data) {
            ergebnis += p.getAnzahl() * p.getPreis();
        }

        EditText ergebnisFeld = (EditText) findViewById(R.id.ergebnisFeld);
        DecimalFormat df = new DecimalFormat("###.##");
        ergebnisFeld.setText(/**Double.toString(ergebnis)*/df.format(ergebnis));
        //set an onItemClickListener to the ListView
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Produkt produkt = (Produkt) adapterView.getItemAtPosition(i);
                if(produkt == null ) {
                    Log.d(TAG, "Product null");
                    toastMessage("Keine ID mit dem Namen!");
                    return;
                } else {
                    Log.d(TAG, "onItemClick: You Clicked on " + produkt.toString());
                    Intent editScreenIntent = new Intent(ListDataActivity.this, EditDataActivity.class);
                    editScreenIntent.putExtra("id",produkt.getId());
                    editScreenIntent.putExtra("name",produkt.getName());
                    editScreenIntent.putExtra("preis", produkt.getPreis());
                    editScreenIntent.putExtra("anzahl", produkt.getAnzahl());
                    startActivity(editScreenIntent);
                }


//                Cursor data = mDatabaseHelper.getItemID(name); //get the id associated with that name
//                int itemID = -1;
//                while(data.moveToNext()){
//                    itemID = data.getInt(0);
//                }
//                if(itemID > -1){
//                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
//                    Intent editScreenIntent = new Intent(ListDataActivity.this, EditDataActivity.class);
//                    editScreenIntent.putExtra("id",itemID);
//                    editScreenIntent.putExtra("name",name);
//                    startActivity(editScreenIntent);
//                }
//                else{
//                    toastMessage("Keine ID mit dem Namen!");
//                }
            }
        });
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}