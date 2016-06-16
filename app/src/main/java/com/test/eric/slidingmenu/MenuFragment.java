package com.test.eric.slidingmenu;


import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by eric on 2016/5/5.
 */
public class MenuFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_left_fragment, container, false);
        View line = view.findViewById(R.id.view_line);
        line.getBackground().setAlpha(255 * 3 / 10);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int statusbarHeight = getStatusBarHeight();
            LinearLayout top_head = (LinearLayout) view.findViewById(R.id.main_left_fragment);
            top_head.setPadding(0, statusbarHeight, 0, 0);
        }
        return view;
    }


    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
