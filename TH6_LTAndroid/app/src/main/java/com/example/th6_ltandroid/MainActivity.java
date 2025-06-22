package com.example.th6_ltandroid;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listViewStudents;
    private Button btnAddStudent;
    private DatabaseHelper dbHelper;
    private ArrayList<Student> studentList;
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewStudents = findViewById(R.id.listViewStudents);
        btnAddStudent = findViewById(R.id.btnAddStudent);
        dbHelper = new DatabaseHelper(this);
        studentList = new ArrayList<>();
        adapter = new StudentAdapter(this, studentList);
        listViewStudents.setAdapter(adapter);

        loadStudents();

        btnAddStudent.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
            startActivity(intent);
        });

        // Xử lý long-click để cập nhật
        listViewStudents.setOnItemLongClickListener((parent, view, position, id) -> {
            Student student = studentList.get(position);
            Intent intent = new Intent(MainActivity.this, UpdateStudentActivity.class);
            intent.putExtra("student_id", student.getId());
            intent.putExtra("student_name", student.getName());
            intent.putExtra("student_email", student.getEmail());
            intent.putExtra("student_phone", student.getPhone());
            intent.putExtra("student_avatar", student.getAvatarPath());
            startActivity(intent);
            return true;
        });
    }

    private void loadStudents() {
        studentList.clear();
        Cursor cursor = dbHelper.getAllStudents();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            String avatar = cursor.getString(cursor.getColumnIndex("avatar"));
            studentList.add(new Student(id, name, email, phone, avatar));
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadStudents(); // Cập nhật danh sách khi quay lại
    }
}