package com.test.eric.slidingmenu;

import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;


public class MainActivity extends SlidingActivity {

    private ImageView imgPerson;
    private SlidingMenu mMenu;
    private MenuFragment mMenuFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgPerson = (ImageView) findViewById(R.id.img_person);
        imgPerson.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mMenu.showMenu(!mMenu.isMenuShowing());
            }
        });
        initMenu();
    }

    private void initMenu() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = this.getWindow();
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.flags |= WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            window.setAttributes(layoutParams);
        }

        mMenuFragment = new MenuFragment();

        setBehindContentView(R.layout.main_left_layout);// 设置左菜单
        getFragmentManager().beginTransaction().replace(R.id.main_left_fragment, mMenuFragment).commit();
        mMenu = getSlidingMenu();
        mMenu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        mMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        mMenu.setShadowDrawable(R.drawable.shadow);
        mMenu.setShadowWidthRes(R.dimen.shadow_width);
        //获取屏幕宽高
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        // 设置滑动菜单视图的宽度
        mMenu.setBehindWidth(dm.widthPixels * 3 / 5);
        // 设置渐入渐出效果的值
        mMenu.setFadeDegree(0.35f);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //获取状态栏高度
            int statusbarHeight = getStatusBarHeight();
            LinearLayout top_head = (LinearLayout) this.findViewById(R.id.top_head);
            top_head.setPadding(0, statusbarHeight, 0, 0);
        }

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
