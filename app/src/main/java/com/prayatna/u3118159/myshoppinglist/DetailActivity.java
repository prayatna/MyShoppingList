package com.prayatna.u3118159.myshoppinglist;

import android.content.Intent;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    ShoppingListDbHelper mydb = new ShoppingListDbHelper(this, "ShoppingListDB", null, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();

        Item item = (Item) intent.getSerializableExtra("oneItem");

        TextView textView = (TextView) findViewById(R.id.viewTitle);
        String textValue = item.getItemName();
        textView.setText(textValue);

        textView = (TextView) findViewById(R.id.viewLocation);
        textValue = item.getItemLocation();
        textView.setText(textValue);

        textView = (TextView) findViewById(R.id.viewQty);
        textValue = Integer.toString(item.getItemQty());
        textView.setText(textValue);

        textView = (TextView) findViewById(R.id.viewNotes);
        textValue = item.getItemNotes();
        textView.setText(textValue);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btn = (Button)findViewById(R.id.buttonDelete);
        Typeface fontAwesome = FontManager.FONTAWESOME.typeface(getAssets());
        btn.setTypeface(fontAwesome);

        btn = (Button)findViewById(R.id.buttonEdit);
        btn.setTypeface(fontAwesome);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

   

    }

    public void buttonDeleteItem(View view){
        Intent intent = getIntent();
        Item item = (Item) intent.getSerializableExtra("oneItem");
        mydb.deleteItem(item);

        intent = new Intent (this,ShoppingList.class);
        startActivity(intent);
    }

    public void buttonEditItem(View view){

        Intent intent = getIntent();
        Item item = (Item) intent.getSerializableExtra("oneItem");
        intent = new Intent(DetailActivity.this, EditItem.class);
        intent.putExtra("editOneItem", item);
        startActivity(intent);
    }

}
