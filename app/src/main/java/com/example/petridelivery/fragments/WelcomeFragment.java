package com.example.petridelivery.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.petridelivery.R;
import com.example.petridelivery.fragments.abs.BaseFragment;

import java.util.Calendar;

public class WelcomeFragment extends BaseFragment {

    public WelcomeFragment() {
        super(R.layout.fragment_welcome);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView welcomeTextView = activity.findViewById(R.id.welcomeTextView);
        Resources res = getResources();

        String salutare = "";
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if(hour < 12){
            salutare = res.getString(R.string.dimi);
        }else if(hour == 12){
            salutare = res.getString(R.string.amiaz);
        }else if(hour < 18){
            salutare = res.getString(R.string.dupamasa);
        }else{
            salutare = res.getString(R.string.seara);
        }

        String text = String.format(
                getResources().getString(R.string.welcome_msg),
                salutare,
                activity.getApp().getCont().getNumeDeUtilizator()
        );
        welcomeTextView.setText(text);
    }
}
