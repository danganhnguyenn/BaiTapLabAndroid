package com.example.th5_ltandroid;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
public class ContentFragment extends Fragment {
    public ContentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);

        Button btnGoToDetail = view.findViewById(R.id.btnGoToDetail);
        btnGoToDetail.setOnClickListener(v -> {
            // Chuyển sang DetailFragment
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new DetailFragment())
                    .addToBackStack(null) // Thêm vào back stack để quay lại
                    .commit();
        });

        return view;
    }
}
