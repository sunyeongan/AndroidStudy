package com.asy100.week04;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbarMain;
    private FloatingActionButton fabMain;
    private ListView listViewMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarMain = findViewById(R.id.toolbar_main);
        fabMain = findViewById(R.id.fab_main);
        listViewMain = findViewById(R.id.list_view_main);


        setSupportActionBar(toolbarMain);
        getSupportActionBar().setTitle("Latte");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); 뒤로가기

        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "클릭", Toast.LENGTH_SHORT).show();
            }
        });

        //Part2 : ListView
        //ListView, list item, adapter
        //데이터(List) -> adapter -> ListView

        //ListView에 표시할 데이터
        List<String> data = new ArrayList<>();
        data.add("사과");
        data.add("배");
        data.add("딸기");
        data.add("레몬");
        data.add("코코넛");

        //ListView에 연결할 Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                MainActivity.this, android.R.layout.simple_list_item_1,data);

        //ListView에 Adapter 연결
        listViewMain.setAdapter(adapter);

        //ClickListener
        //onItemClickListener

        listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);

                intent.putExtra("menu1",data.get(i));

                startActivity(intent);
            }
        });
    }


}