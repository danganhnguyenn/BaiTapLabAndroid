package com.example.th5_ltandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvWelcome;
    private Button btnLogout;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);

        // Kiểm tra trạng thái đăng nhập
        if (!sessionManager.isLoggedIn()) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
            return;
        }

        tvWelcome = findViewById(R.id.tvWelcome);
        btnLogout = findViewById(R.id.btnLogout);

        // Hiển thị username trong header
        tvWelcome.setText("Welcome, " + sessionManager.getUsername());

        // Load Fragment mặc định (ContentFragment)
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new ContentFragment())
                    .commit();
        }

        // Xử lý nút Logout
        btnLogout.setOnClickListener(v -> {
            sessionManager.logout(); // Xóa thông tin đăng nhập
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        });
    }
}