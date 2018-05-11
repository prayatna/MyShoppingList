package com.prayatna.u3118159.myshoppinglist;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditItem extends AppCompatActivity {

private int id;  //(is it good practice to declare a global int id ??)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        Item item = (Item) intent.getSerializableExtra("editOneItem");

        id = item.getId(); //use of global id

        EditText editView = (EditText) findViewById(R.id.editTextName);
        String textView = item.getItemName();
        editView.setText(textView);

        editView = (EditText) findViewById(R.id.editTextLocation);
        textView = item.getItemLocation();
        editView.setText(textView);

        editView = (EditText) findViewById(R.id.editTextNotes);
        textView = item.getItemNotes();
        editView.setText(textView);

        editView = (EditText) findViewById(R.id.editTextQty);
        textView = String.valueOf(item.getItemQty());
        editView.setText(textView);

        Button btn = (Button)findViewById(R.id.buttonUpdate);
        Typeface fontAwesome = FontManager.FONTAWESOME.typeface(getAssets());
        btn.setTypeface(fontAwesome);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void buttonUpdateItem(View view){
        boolean isValid = true;
        EditText editTextItemName = (EditText) findViewById(R.id.editTextName);
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
        if( editTextQty.getText().toString().length() == 0 ) {
            editTextQty.setError("Quantity is required");
            isValid = false;
        }

        if (isValid) {
            ShoppingListDbHelper mydb = new ShoppingListDbHelper(this, "ShoppingListDB", null, 1);

            //adding in Item class
            String itemName = editTextItemName.getText().toString();
            String itemLocation = editTextItemLocation.getText().toString();
            //check if note is empty, then put an empty string
            String itemNotes = editTextNotes.getText().toString();
            if(itemNotes.length()==0){
                itemNotes = " ";
            }
            int itemQty = Integer.parseInt(editTextQty.getText().toString());

            Item item = new Item(id, itemName, itemQty, itemLocation, itemNotes);

            mydb.updateItem(item);

            Intent intent = new Intent(this, ShoppingList.class);
            startActivity(intent);
        }
    }

}
