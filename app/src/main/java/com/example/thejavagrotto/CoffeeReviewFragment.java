package com.example.thejavagrotto;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CoffeeReviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

import com.example.thejavagrotto.pojo.ReviewItem;

import java.lang.reflect.Array;
import java.security.Permission;
import java.util.ArrayList;

import static com.example.thejavagrotto.MainActivity.fab;

public class CoffeeReviewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CoffeeReviewFragment() {
        // Required empty public constructor
    }

    public static CoffeeReviewFragment newInstance(String param1, String param2) {
        CoffeeReviewFragment fragment = new CoffeeReviewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coffee_review, container, false);

        fab.setImageResource(R.drawable.ic_baseline_post_add_24);
        fab.show();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt(CreateUpdateReviewFragment.ACTION_TYPE, CreateUpdateReviewFragment.CREATE_REVIEW);
                Navigation.findNavController(view).navigate(R.id.action_nav_review_to_createUpdateReviewFragment, bundle);
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        CoffeeDB coffeeDB = new CoffeeDB(getContext());
        ArrayList<ReviewItem> reviewItems = coffeeDB.getAllReviews();
        coffeeDB.close();

        CustomReviewAdapter adapter = new CustomReviewAdapter(reviewItems, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

}