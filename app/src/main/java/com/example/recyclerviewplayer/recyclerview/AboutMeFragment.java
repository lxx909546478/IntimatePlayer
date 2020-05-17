package com.example.recyclerviewplayer.recyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.recyclerviewplayer.R;

public class AboutMeFragment extends Fragment {

    private static final String TEG="LXX";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View settingLayout = inflater.inflate(R.layout.fragment_about_me, container, false);
        return settingLayout;
    }
}
