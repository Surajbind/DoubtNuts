package com.example.doubtnuts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Dash_BoardActivity extends AppCompatActivity {


    CardView qr_scanner,web_view,todo,encryption,wiki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        qr_scanner = findViewById(R.id.qr_scanner);
        web_view = findViewById(R.id.web_view);
        todo = findViewById(R.id.todo);
        encryption = findViewById(R.id.encryption);
        wiki = findViewById(R.id.wiki);

        qr_scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dash_BoardActivity.this,ScanQrActivity.class);
                startActivity(intent);
            }
        });


        web_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dash_BoardActivity.this,WebtoPdfActivity.class);
                startActivity(intent);
            }
        });

        encryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dash_BoardActivity.this,EncryptionActivity.class);
                startActivity(intent);
            }
        });



        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dash_BoardActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });



        wiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dash_BoardActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });

    }
}