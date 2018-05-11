package com.prayatna.u3118159.myshoppinglist;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView imageView = (ImageView)findViewById(R.id.imageViewWall);
        Animation fadeIn = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in);
        imageView.startAnimation(fadeIn);

        getSupportActionBar().hide();

        TextView mainTitle = (TextView)findViewById(R.id.textTitle1);
        Typeface refugeTypeface=FontManager.REFUGE.typeface(getAssets());
        mainTitle.setTypeface(refugeTypeface);
        mainTitle = (TextView)findViewById(R.id.textTitle2);
        mainTitle.setTypeface(refugeTypeface);

        Button btn = (Button)findViewById(R.id.buttonAddItem1);
        Typeface aquaTypeface = FontManager.AQUA.typeface(getAssets());
        Animation translate = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate);

        btn.setTypeface(aquaTypeface);
        btn.startAnimation(translate);

        btn = (Button)findViewById(R.id.buttonMyItem);
        btn.setTypeface(aquaTypeface);
        btn.startAnimation(translate);

        btn = (Button)findViewById(R.id.buttonAbout);
        btn.setTypeface(aquaTypeface);
        btn.startAnimation(translate);


//        Animation translate = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate);
//        btnAdd.startAnimation(translate);

//
//        Button btnMyItem = (Button)findViewById(R.id.buttonMyItem);
//        aquaTypeface = FontManager.AQUA.typeface(getAssets());
//        btnMyItem.setTypeface(aquaTypeface);
//        Animation translate2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate);
//        btnMyItem.startAnimation(translate2);
//
//        Button btnAbout = (Button)findViewById(R.id.buttonAbout);
//        aquaTypeface = FontManager.AQUA.typeface(getAssets());
//        btnAbout.setTypeface(aquaTypeface);
//        Animation translate3 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate);
//        btnAbout.startAnimation(translate3);

    }



    public void openAddItem(View view){
        Intent intent = new Intent (this,AddItem.class);
        startActivity(intent);

    }

    public void openShoppingItem(View view){
        Intent intent = new Intent (this,ShoppingList.class);
        startActivity(intent);

    }

   public void openAbout(View view){
       Intent intent = new Intent(this,About.class);
       startActivity(intent);
   }
}
