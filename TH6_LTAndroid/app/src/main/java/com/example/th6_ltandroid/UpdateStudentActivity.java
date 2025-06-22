package com.example.th6_ltandroid;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class UpdateStudentActivity extends AppCompatActivity {
    private ImageView imgAvatar;
    private EditText etName, etEmail, etPhone;
    private Button btnChooseImage, btnSave;
    private DatabaseHelper dbHelper;
    private String avatarPath;
    private int studentId;
    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student); // Tái sử dụng layout

        imgAvatar = findViewById(R.id.imgAvatar);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        btnChooseImage = findViewById(R.id.btnChooseImage);
        btnSave = findViewById(R.id.btnSave);
        dbHelper = new DatabaseHelper(this);

        // Lấy thông tin sinh viên từ Intent
        studentId = getIntent().getIntExtra("student_id", -1);
        etName.setText(getIntent().getStringExtra("student_name"));
        etEmail.setText(getIntent().getStringExtra("student_email"));
        etPhone.setText(getIntent().getStringExtra("student_phone"));
        avatarPath = getIntent().getStringExtra("student_avatar");

        if (avatarPath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(avatarPath);
            imgAvatar.setImageBitmap(bitmap);
        }

        btnChooseImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        });

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String phone = etPhone.getText().toString();

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean result = dbHelper.updateStudent(studentId, name, email, phone, avatarPath);
            if (result) {
                Toast.makeText(this, "Cập nhật sinh viên thành công", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Cập nhật sinh viên thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imgAvatar.setImageBitmap(bitmap);

                File file = new File(getFilesDir(), "avatar_" + System.currentTimeMillis() + ".png");
                FileOutputStream fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.close();
                avatarPath = file.getAbsolutePath();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
