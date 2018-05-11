package com.prayatna.u3118159.myshoppinglist;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button btn = (Button)findViewById(R.id.buttonAdd);
        Typeface fontAwesome = FontManager.FONTAWESOME.typeface(getAssets());
        btn.setTypeface(fontAwesome);
    }

    public void addItem(View view){
        boolean isValid = true;
        EditText editTextItemName = (EditText) findViewById(R.id.editTextItemName);
        if( editTextItemName.getText().toString().length() == 0 ){
            editTextItemName.setError( "Item Name is required" );
            isValid = false;
        }
        EditText editTextItemLocation = (EditText) findViewById(R.id.editTextLocation);
        if( editTextItemLocation.getText().toString().length() == 0 ){
            editTextItemLocation.setError( "Location is required" );
            isValid = false;
        }
        //notes are optional
        EditText editTextNotes = (EditText) findViewById(R.id.editTextNotes);

        EditText editTextQty = (EditText) findViewById(R.id.editTextQty);
        if( editTextQty.getText().toString().length() == 0 ){
            editTextQty.setError( "Quantity is required" );
            isValid = false;
        }

        if (isValid) {
            ShoppingListDbHelper mydb = new ShoppingListDbHelper(this, "ShoppingListDB", null, 1);

            //adding in Item class
            String itemName = editTextItemName.getText().toString();
            String itemLocation = editTextItemLocation.getText().toString();
            String itemNotes = editTextNotes.getText().toString();

            //check if note is empty, then put an empty string
            if( itemNotes.length() == 0 ){
                itemNotes=" ";

            }

            int itemQty = Integer.parseInt(editTextQty.getText().toString()); //TODO validate if quantity is proper integer


            Item item = new Item(-1, itemName, itemQty, itemLocation, itemNotes);

            mydb.addItem(item);


            Intent intent = new Intent(this, ShoppingList.class);
            startActivity(intent);
        }
    }

}
