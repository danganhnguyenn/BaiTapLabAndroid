package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private SearchView searchView;
    private ProductAdapter adapter;
    private ArrayList<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        listView = findViewById(R.id.listView);
        searchView = findViewById(R.id.searchView);

        // Initialize product list
        productList = new ArrayList<>();

        // Add sample products
        addSampleProducts();

        // Set up adapter
        adapter = new ProductAdapter(this, productList);
        listView.setAdapter(adapter);

        // Set up search functionality
        setupSearchView();
    }

    private void addSampleProducts() {
        productList.add(new Product("Laptop Dell", 899.99, "https://laptopusapro.com/data/7510-3x3.jpg"));
        productList.add(new Product("iPhone 14", 1099.99, "https://cdn.tgdd.vn/Products/Images/42/251192/iphone-14-pro-max-tim-thumb-600x600.jpg"));
        productList.add(new Product("Samsung Galaxy", 799.99, "https://cdn.tgdd.vn/Products/Images/42/306994/samsung-galaxy-s23-fe-mint-thumbai-600x600.jpg"));
        productList.add(new Product("Sony Headphone", 199.99, "https://laptopusapro.com/data/TN-UB01-160x114.jpg"));
        productList.add(new Product("Samsung Galaxy Z7", 999.99, "https://cdn.tgdd.vn/Products/Images/42/320722/samsung-galaxy-z-flip6-thumb-1-600x600.jpg"));
        productList.add(new Product("Vituarbox Headphone", 199.99, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_rhKnG6WwKGaL6e40xq6hlyxRBvyWIoWBRQ&s"));
        productList.add(new Product("Laptop Lenovo thinkpad", 679.99, "https://laptopusapro.com/data/thinkpad-300x300.jpg"));
        productList.add(new Product("iPhone 15", 1229.99, "https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:90/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Apple/iphone_15/iphone-15-plus-9.JPG"));
        productList.add(new Product("iPhone 16 pro max", 765.99, "https://cdn.tgdd.vn/Products/Images/42/329149/iphone-16-pro-max-sa-mac-thumb-1-600x600.jpg"));
        productList.add(new Product("Tai nghe Airpod", 550.99, "https://cdn.tgdd.vn/Products/Images/54/236016/bluetooth-airpods-2-apple-mv7n2-imei-1-750x500.jpg"));
    }

    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Optional: Handle search submit if needed
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter products as user types
                filterProducts(newText);
                return true;
            }
        });

        // Optional: Expand the SearchView by default
        searchView.setIconified(false);
        searchView.clearFocus();
    }

    private void filterProducts(String searchText) {
        ArrayList<Product> filteredList = new ArrayList<>();

        String searchQuery = searchText.toLowerCase().trim();
        if (searchQuery.isEmpty()) {
            filteredList.addAll(productList);
        } else {
            for (Product product : productList) {
                if (product.getName().toLowerCase().contains(searchQuery)) {
                    filteredList.add(product);
                }
            }
        }

        // Cập nhật danh sách sản phẩm hiển thị
        adapter.updateList(filteredList);
    }

}