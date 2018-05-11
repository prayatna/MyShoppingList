package com.prayatna.u3118159.myshoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShoppingList extends AppCompatActivity {
    ShoppingListDbHelper mydb = new ShoppingListDbHelper(this, "ShoppingListDB", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final List<Item> items = mydb.getAllItems();

        ItemAdapter adapter =
                new ItemAdapter(this, R.layout.list_item, items);

        // add all of these items to the list view.
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter);

        //when particular item in the list is clicked
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ShoppingList.this, DetailActivity.class);
                Item oneItem = items.get(position);
                intent.putExtra("oneItem", oneItem);
                startActivity(intent);
            }

        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
        if(id == R.id.action_addItems){
            Intent intent = new Intent(this, AddItem.class);
            startActivity(intent);
        }

        if(id== R.id.action_about){
            Intent intent = new Intent(this,About.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}
