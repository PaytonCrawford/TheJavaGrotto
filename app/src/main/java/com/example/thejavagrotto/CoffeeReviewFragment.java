package com.example.thejavagrotto;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CoffeeReviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

import com.example.thejavagrotto.pojo.ReviewItem;

import java.lang.reflect.Array;
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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CoffeeReviewFragment.
     */
    // TODO: Rename and change types and number of parameters
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

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        CustomStepsRecyclerViewAdapter customStepsRecyclerViewAdapter = new CustomStepsRecyclerViewAdapter(dataItems);
        recyclerView.setAdapter(customStepsRecyclerViewAdapter);

        return view;
    }

    public class CustomReviewRecyclerViewAdapter extends RecyclerView.Adapter {

        private ArrayList<ReviewItem> reviewItems;

        public CustomReviewRecyclerViewAdapter(ArrayList<ReviewItem> reviewItems) {
            this.reviewItems = reviewItems;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, null);
            CustomViewHolder customViewHolder = new CustomViewHolder(view);
            return customViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            
        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {

        protected TextView reviewTitle;
        protected TextView reviewText;
        protected TextView web;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.reviewTitle = itemView.findViewById(R.id.)
        }

        @Override
        public void onClick(View view) {

        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }

}