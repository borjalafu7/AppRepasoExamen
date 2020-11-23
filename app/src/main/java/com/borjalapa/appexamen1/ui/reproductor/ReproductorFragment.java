package com.borjalapa.appexamen1.ui.reproductor;

import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.borjalapa.appexamen1.R;
import com.borjalapa.appexamen1.ui.peliculas.DashboardViewModel;

public class ReproductorFragment extends Fragment {

    private ReproductorViewModel reproductorViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        reproductorViewModel = new ViewModelProvider(this).get(ReproductorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_reproductor, container, false);
        final TextView textView = root.findViewById(R.id.text_reproductor);
        reproductorViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }


}