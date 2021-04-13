package com.example.thejavagrotto;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CoffeeTypesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CoffeeTypesFragment extends Fragment {

    ViewPager2 typesViewPagerTwo;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";

    private String mParam1;
    private int mParam2;
    private String mParam3;

    public CoffeeTypesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CoffeeTypesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CoffeeTypesFragment newInstance(String param1, int param2, String param3) {
        CoffeeTypesFragment fragment = new CoffeeTypesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        args.putString(ARG_PARAM2, param3);
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

    /**
     * @author Payton Crawford
     * NEW VIEWPAGER2 FOR THEJAVAGROTTO COFFEE TYPES
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coffee_types, container, false);
        typesViewPagerTwo = view.findViewById(R.id.coffeeTypesViewPager);
        typesViewPagerTwo.setAdapter(new CustomTypeViewPager2Adapter(getActivity()));
        //typesViewPagerTwo.setPageTransformer(new ZoomOutPageTransformer());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, typesViewPagerTwo, (tab, position) -> tab.setText("Cup " + (position + 1))).attach();
        }

    private class CustomTypeViewPager2Adapter extends FragmentStateAdapter {

        public CustomTypeViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        /**
         * @author Payton Crawford
         * ViewPager elements.
         */

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return TypesFragment.newInstance("Arabica Coffee", R.drawable.arabica, "This is the most common coffee bean type found in homes and stores around the world. It has a very sweetened taste," +
                            " good for all kinds of brews including black coffee. Although It has a lower caffeine concentration than most other coffee types.");
                    //https://unsplash.com/photos/5rwIPczKI-8
                case 1:
                    return TypesFragment.newInstance("Latte", R.drawable.latte, "Similarly to Arabica coffee, this is one of the most popular ways to serve coffee in the world. Latte's " +
                            "can be flavoured and are comprised of espresso and warm milk.");
                    //https://unsplash.com/photos/nzyzAUsbV0M
                case 2:
                    return TypesFragment.newInstance("Espresso", R.drawable.espresso, "Espresso's contain very concentrated amounts of caffeine, which is why they are usually served in smaller" +
                            " pours. They can be enjoyed on their own or used as a great mixture and starting point for other kinds of coffee.");
                    //https://unsplash.com/photos/KWZ-rg9o76A
                case 3:
                    return TypesFragment.newInstance("Iced Coffee", R.drawable.icedcoffee, "Typically Arabica brew that is chilled and served on top of ice. Iced coffee is also " +
                            "enjoyed by most with a heavier amount of cream and sweetness, a very cool, refreshing summer beverage.");
                    //https://unsplash.com/photos/1e5V69AQjgA
                case 4:
                    return TypesFragment.newInstance("Nitro Brew", R.drawable.nitro, "Nitro's are very tasty cold brew coffee's that are infused with nitrogen bubbles to create a chilled, frothy " +
                            "beverage with a thick consistency.");
                    //https://unsplash.com/photos/N3bo4mkQEKM
                case 5:
                    return TypesFragment.newInstance("Galão", R.drawable.galao, "Galão is a hot Portuguese coffee with similarities to cappuccino's and latte's. It contains more frothy" +
                            " milk, which turns it brighter in colour and gives it an increased amount of sweetness.");
                    //https://unsplash.com/photos/J-4ozdP9EQ0
                default:
                    return TypesFragment.newInstance("Error.", 0, "No content found.");
            }
        }

        @Override
        public int getItemCount() {
            return 6;
        }
    }
}