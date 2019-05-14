package com.example.carapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etbrand, etLitre;
    Button btnInsert, btnRetrieve;
    TextView tvResults;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etbrand = findViewById(R.id.etBrand);
        etLitre = findViewById(R.id.etLitre);
        btnInsert = findViewById(R.id.btnInsert);
        btnRetrieve = findViewById(R.id.btnRetrieve);
        tvResults = findViewById(R.id.tvResult);
        myDB = new DBHelper(this);


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String brand = etbrand.getText().toString();
                String litre = etLitre.getText().toString();
                myDB.insertTasks(brand, litre);
                boolean insert = true;
                if (insert == true){
                    Toast.makeText(MainActivity.this, "inserted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "not inserted", Toast.LENGTH_SHORT).show();
                }
                myDB.close();

            }
        });

        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> data = myDB.getCarBrand();
                String title = "";
                for (int i = 0; i < data.size(); i++){
                    title += data.get(i) + "\n";
                }
                myDB.close();
                tvResults.setText("CAR INFO: \n" + title + "\n");


            }
        });

    }
}
