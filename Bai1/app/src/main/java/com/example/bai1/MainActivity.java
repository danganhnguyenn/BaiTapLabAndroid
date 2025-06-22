package com.example.bai1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
        // Khai báo các biến giao diện tại đây
    EditText eA, eB, eKQ;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ xử lí cho các biến giao diện
        eA = findViewById(R.id.eA);
        eB = findViewById(R.id.eB);
        eKQ = findViewById(R.id.eKQ);
        btn = findViewById(R.id.btn);

        // Xử lí tương tác với người dùng
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(eA.getText().toString());
                int b = Integer.parseInt(eB.getText().toString());

                int c = a+b;

                eKQ.setText(c+"");
            }
        });
    }
}