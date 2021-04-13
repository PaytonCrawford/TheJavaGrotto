package com.example.thejavagrotto;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static final int PERMISSION_CALL_PHONE = 0;

    private String mParam1;
    private String mParam2;

    public ShopsFragment() {
        // Required empty public constructor
    }

    public static ShopsFragment newInstance(String param1, String param2) {
        ShopsFragment fragment = new ShopsFragment();
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

        View view = inflater.inflate(R.layout.fragment_shops, container, false);

        /**
         * @author Payton Crawford
         * Web and call intent (With permission.)
         */

        Button locateCafeButton = view.findViewById(R.id.locateShopButton);
        locateCafeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri web = Uri.parse("https://explorethebruce.com/mobile/story/where-to-find-your-coffee-on-the-lakeshore/149/");
                Intent intent = new Intent(Intent.ACTION_VIEW, web);
                if(intent.resolveActivity(getActivity().getPackageManager()) != null ) {
                    startActivity(intent);
                } else {
                    Snackbar.make(requireView(), "Action invalid.", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        Button callCafeButton = view.findViewById(R.id.callCafeButton);
        callCafeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CALL_PHONE)) {
                        final AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                        alertDialog.setTitle("Phone Permission");
                        alertDialog.setMessage("We need access to your phone to make a call to the local cafe.");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ACCEPT", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialog.dismiss();
                                ActivityCompat.requestPermissions(getActivity(), new String[] {
                                        Manifest.permission.CALL_PHONE}, PERMISSION_CALL_PHONE);
                            }
                        });
                        alertDialog.show();
                    }
                    else {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_CALL_PHONE);
                    }
                } else {
                    Uri num = Uri.parse("tel:2299249665");
                    Intent intent = new Intent(Intent.ACTION_DIAL, num);
                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        Snackbar.make(requireView(), "Action invalid.", Snackbar.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }
}