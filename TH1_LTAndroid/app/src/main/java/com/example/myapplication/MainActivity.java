package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.DatePickerDialog;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText edtBirthDate;
    Button btnSubmit;
    CheckBox chkAll, chkAndroid, chkWebsite, chkJava;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtBirthDate = findViewById(R.id.edtBirthDate);
        btnSubmit = findViewById(R.id.btnSubmit);
        chkAll = findViewById(R.id.chkAll);
        chkAndroid = findViewById(R.id.chkAndroid);
        chkWebsite = findViewById(R.id.chkWebsite);
        chkJava = findViewById(R.id.chkJava);

        // Chọn ngày sinh
        edtBirthDate.setOnClickListener(v -> showDatePickerDialog());

        // Xử lý khi nhấn chọn "Chọn tất cả"
        chkAll.setOnCheckedChangeListener((buttonView, isChecked) -> {
            chkAndroid.setChecked(isChecked);
            chkWebsite.setChecked(isChecked);
            chkJava.setChecked(isChecked);
        });

        // Xử lý khi nhấn vào từng checkbox con
        chkAndroid.setOnCheckedChangeListener((buttonView, isChecked) -> updateSelectAll());
        chkWebsite.setOnCheckedChangeListener((buttonView, isChecked) -> updateSelectAll());
        chkJava.setOnCheckedChangeListener((buttonView, isChecked) -> updateSelectAll());

        // Nút hoàn thành
        btnSubmit.setOnClickListener(v -> Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show());
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            String date = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
            edtBirthDate.setText(date);
        }, year, month, day);
        datePickerDialog.show();
    }

    private void updateSelectAll() {
        chkAll.setChecked(chkAndroid.isChecked() && chkWebsite.isChecked() && chkJava.isChecked());
    }
}
