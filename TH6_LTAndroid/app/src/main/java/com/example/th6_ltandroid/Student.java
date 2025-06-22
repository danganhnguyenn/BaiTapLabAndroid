package com.example.th6_ltandroid;
public class Student {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String avatarPath;

    public Student(int id, String name, String email, String phone, String avatarPath) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.avatarPath = avatarPath;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAvatarPath() { return avatarPath; }
}
