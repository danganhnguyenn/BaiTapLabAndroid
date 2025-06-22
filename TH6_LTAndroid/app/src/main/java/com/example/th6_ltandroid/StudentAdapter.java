package com.example.th6_ltandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class StudentAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Student> students;

    public StudentAdapter(Context context, ArrayList<Student> students) {
        this.context = context;
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false);
        }

        ImageView imgAvatar = convertView.findViewById(R.id.imgAvatar);
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvEmail = convertView.findViewById(R.id.tvEmail);
        TextView tvPhone = convertView.findViewById(R.id.tvPhone);

        Student student = students.get(position);
        tvName.setText(student.getName());
        tvEmail.setText(student.getEmail());
        tvPhone.setText(student.getPhone());

        // Hiển thị ảnh avatar
        if (student.getAvatarPath() != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(student.getAvatarPath());
            imgAvatar.setImageBitmap(bitmap);
        }

        return convertView;
    }
}
