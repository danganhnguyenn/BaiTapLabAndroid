package com.example.chuyendoinhietdo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
        // Khai báo các biến giao diện tại đây
    EditText editF, editC;
    Button btnC, btnF, btnXoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ id
        editC = findViewById(R.id.editC);
        editF = findViewById(R.id.editF);

        btnC = findViewById(R.id.btnC);
        btnF = findViewById(R.id.btnF);
        btnXoa = findViewById(R.id.btnXoa);

        // Xử lí click
        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int C = Integer.parseInt(editC.getText().toString());
                Double F = C * 1.8 /32;
                editF.setText(F +"");

            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int F = Integer.parseInt(editF.getText().toString());
                Double C = (F-32)/1.8;
                editC.setText(C +"");

            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               editC.setText("");
                editF.setText("");

            }
        });

    }
}