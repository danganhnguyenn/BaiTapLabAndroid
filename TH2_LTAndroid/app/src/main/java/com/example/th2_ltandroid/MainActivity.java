package com.example.th2_ltandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText etName, etEmail, etPassword, etConfirmPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();

                // Kiểm tra rỗng
                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Kiểm tra định dạng email
                if (!isValidEmail(email)) {
                    Toast.makeText(MainActivity.this, "Email không hợp lệ!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Kiểm tra độ dài mật khẩu
                if (password.length() < 6) {
                    Toast.makeText(MainActivity.this, "Mật khẩu phải có ít nhất 6 ký tự!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Kiểm tra mật khẩu khớp nhau
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(MainActivity.this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Đăng ký thành công
                Toast.makeText(MainActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Phương thức kiểm tra định dạng email
    private boolean isValidEmail(String email) {
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return Pattern.compile(emailPattern).matcher(email).matches();
    }
}
