package com.example.th5_ltandroid;
import android.content.Context;
import android.content.SharedPreferences;
public class SessionManager {
    private static final String PREF_NAME = "LoginPrefs";
    private static final String KEY_USERNAME = "username";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    // Lưu username khi đăng nhập
    public void setLogin(String username) {
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }

    // Lấy username đã lưu
    public String getUsername() {
        return sharedPreferences.getString(KEY_USERNAME, null);
    }

    // Xóa thông tin đăng nhập khi logout
    public void logout() {
        editor.clear();
        editor.apply();
    }

    // Kiểm tra trạng thái đăng nhập
    public boolean isLoggedIn() {
        return getUsername() != null;
    }
}
