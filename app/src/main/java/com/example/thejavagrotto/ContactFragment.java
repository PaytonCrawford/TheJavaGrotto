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

public class ContactFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static final int PERMISSION_CALL_PHONE = 0;

    private String mParam1;
    private String mParam2;

    public ContactFragment() {
        // Required empty public constructor
    }

    public static ContactFragment newInstance(String param1, String param2) {
        ContactFragment fragment = new ContactFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        /**
         * @author Payton Crawford
         * Call and email intents (With call permission.)
         */


        Button callContactButton = view.findViewById(R.id.callContactButton);
        callContactButton.setOnClickListener(new View.OnClickListener() {
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
                    Uri num = Uri.parse("tel:3848244742");
                    Intent intent = new Intent(Intent.ACTION_DIAL, num);
                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        Snackbar.make(requireView(), "Action invalid.", Snackbar.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Button emailContactButton = view.findViewById(R.id.emailContactButton);
        emailContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                String[] emailAddresses = {"testingemail816@gmail.com"};
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, emailAddresses);
                intent.putExtra(Intent.EXTRA_TEXT, "I was wondering about my local coffee shops, I have questions regarding...");
                if(intent.resolveActivity(getActivity().getPackageManager()) != null ) {
                    startActivity(intent);
                } else {
                    Snackbar.make(requireView(), "Action invalid.", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}