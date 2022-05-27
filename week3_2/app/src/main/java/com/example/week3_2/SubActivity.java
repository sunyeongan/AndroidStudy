package com.example.week3_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {

    private Intent intent;

    private TextView txtmain;
    private TextView submain;
    private Button btn_sub;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        submain = findViewById(R.id.txt_sub);
        txtmain = findViewById(R.id.txt_main);

        btn_sub = findViewById(R.id.btn_sub);

        intent = getIntent();


        String mainText = intent.getStringExtra("maintxt");

        txtmain.setText(mainText

        );
        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SubActivity.this, ThridActivity.class);

                intent.putExtra("subtxt",submain.getText().toString());

                startActivity(intent);
            }
        });
    }
}
