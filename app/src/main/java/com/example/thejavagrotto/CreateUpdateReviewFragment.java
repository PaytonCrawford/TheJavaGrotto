package com.example.thejavagrotto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.thejavagrotto.pojo.ReviewItem;

public class CreateUpdateReviewFragment extends Fragment {

    ReviewItem reviewItem;

    public static final int UPDATE_REVIEW = 1;
    public static final int CREATE_REVIEW = 2;


    public static final String REVIEW = "Review";
    public static final String ACTION_TYPE = "action_type";

    public CreateUpdateReviewFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_update_review, container, false);

        EditText reviewTitle = view.findViewById(R.id.editCoffeeName);
        EditText reviewText = view.findViewById(R.id.editReview);
        Button submit = view.findViewById(R.id.submitButton);

        if (getArguments() != null) {

            if(getArguments().getInt(ACTION_TYPE) == UPDATE_REVIEW) {
                reviewItem = getArguments().getParcelable(REVIEW);
                submit.setText("Update Step");
                if(reviewItem != null) {
                    reviewTitle.setText(reviewItem.getReviewTitle());
                    reviewText.setText(reviewItem.getReviewText());
                }
            } else {
                reviewItem = new ReviewItem();
                submit.setText("Submit New Review!");
            }

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reviewItem.setReviewTitle(reviewTitle.getText().toString());
                    reviewItem.setReviewText(reviewText.getText().toString());

                    CoffeeDB db = new CoffeeDB(getContext());
                    if(getArguments().getInt(ACTION_TYPE) == UPDATE_REVIEW) {
                        db.updateReview(reviewItem);
                    } else if (getArguments().getInt(ACTION_TYPE) == CREATE_REVIEW) {
                        db.addReview(reviewItem);
                    }
                    db.close();
                    Navigation.findNavController(v).popBackStack();

                }
            });
        }

        return view;
    }
}