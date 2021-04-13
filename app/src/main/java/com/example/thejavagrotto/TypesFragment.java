package com.example.thejavagrotto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TypesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TypesFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";

    private String mParam1;
    private int mParam2;
    private String mParam3;

    public TypesFragment() {
        // Required empty public constructor
    }

    public static TypesFragment newInstance(String param1, int param2, String param3) {
        TypesFragment fragment = new TypesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_types, container, false);

        if(mParam1 != null) {
            TextView name = view.findViewById(R.id.typeName);
            name.setText(mParam1);
        }

        if(mParam2 != 0) {
            ImageView image = view.findViewById(R.id.typePic);
            image.setImageResource(mParam2);
        }

        if(mParam3 != null) {
            TextView description = view.findViewById(R.id.typeDesc);
            description.setText(mParam3);
        }

        return view;
    }
}