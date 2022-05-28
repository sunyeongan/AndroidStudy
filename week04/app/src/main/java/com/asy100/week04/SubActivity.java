package com.asy100.week04;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SubActivity extends AppCompatActivity {

    private Toolbar toolbarSub;
    private Intent intent;
    private TextView txtSub;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        toolbarSub = findViewById(R.id.toolbar_sub);
        txtSub = findViewById(R.id.text_view_sub);


        setSupportActionBar(toolbarSub);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intent = getIntent();

        String menuText = intent.getStringExtra("menu1");

        txtSub.setText(menuText
        );

    }
}
