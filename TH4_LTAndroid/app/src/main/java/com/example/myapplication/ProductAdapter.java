package com.example.myapplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
public class ProductAdapter extends ArrayAdapter<Product> {
    private Context context;
    private ArrayList<Product> products;

    public ProductAdapter(Context context, ArrayList<Product> products) {
        super(context, 0, new ArrayList<>(products)); // Copy dữ liệu để tránh thay đổi trực tiếp
        this.context = context;
        this.products = new ArrayList<>(products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate the list item layout if convertView is null
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_product, parent, false);
        }

        // Get the current product
        Product product = getItem(position);

        // Bind data to the views
        ImageView productImage = convertView.findViewById(R.id.productImage);
        TextView productName = convertView.findViewById(R.id.productName);
        TextView productPrice = convertView.findViewById(R.id.productPrice);

        // Set the product name and price
        productName.setText(product.getName());
        productPrice.setText(String.format("$%.2f", product.getPrice()));

        // Load the product image using Glide
        Glide.with(context)
                .load(product.getImageUrl())
                .placeholder(android.R.drawable.ic_menu_gallery) // Placeholder image while loading
                .error(android.R.drawable.ic_menu_close_clear_cancel) // Error image if loading fails
                .into(productImage);

        return convertView;
    }

    // Method to update the list for filtering
    public void updateList(ArrayList<Product> filteredList) {
        clear(); // Xóa danh sách cũ của adapter
        addAll(filteredList); // Thêm danh sách đã lọc vào adapter
        notifyDataSetChanged(); // Cập nhật giao diện
    }



}
