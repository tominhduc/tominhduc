package com.tominhduc.tominhduc.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tominhduc.tominhduc.Model.ListMusic;
import com.tominhduc.tominhduc.R;

public class FragmentLyric extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragmentlyric,container,false);
    }

    public static FragmentLyric newInstance() {


        Bundle args = new Bundle();

        FragmentLyric fragment = new FragmentLyric();
        fragment.setArguments(args);
        return fragment;
    }

}
