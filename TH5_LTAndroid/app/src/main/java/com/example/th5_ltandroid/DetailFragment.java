package com.example.th5_ltandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {
    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        Button btnGoBack = view.findViewById(R.id.btnGoBack);
        btnGoBack.setOnClickListener(v -> {
            // Quay lại Fragment trước đó (ContentFragment)
            getActivity().getSupportFragmentManager().popBackStack();
        });

        return view;
    }
}