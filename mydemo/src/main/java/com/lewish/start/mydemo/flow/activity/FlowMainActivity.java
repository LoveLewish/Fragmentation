package com.lewish.start.mydemo.flow.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lewish.start.mydemo.R;
import com.lewish.start.mydemo.common.base.fragment.BaseMainFragment;

import me.yokeyword.fragmentation.SupportActivity;

import static com.lewish.start.mydemo.R.id.drawer_layout;
import static com.lewish.start.mydemo.R.id.fl_content;
import static com.lewish.start.mydemo.R.id.navigation_view;

public class FlowMainActivity extends SupportActivity implements BaseMainFragment.OnFragmentOpenDrawerListener{
    private DrawerLayout mDrawerLayout;
    private FrameLayout mContent;

    private NavigationView mNavigationView;
    private LinearLayout mNavigationHeader;

    private ImageView ivUserImg;
    private TextView tvUserName;
    private TextView tvUserEmail;
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_main);

        initViews();
        setListener();
    }

    private void setListener() {

        mNavigationHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FlowMainActivity.this, "跳去登陆页面", Toast.LENGTH_SHORT).show();
            }
        });

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                mDrawerLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        int itemId = item.getItemId();
                        String tips = null;
                        switch (itemId) {
                            case  R.id.nv_home:
                                tips = "首页";
                                break;
                            case  R.id.nv_discover:
                                tips = "发现";
                                break;
                            case  R.id.nv_shop:
                                tips = "商店";
                                break;
                            case  R.id.nv_login:
                                tips = "登录";
                                break;
                            case R.id.swipe_activity_fragment:
                                tips = "SwipeBackActivity+Fragment";
                                break;
                            case R.id.swipe_fragment:
                                tips = "SwipeBackFragment";
                                break;
                        }
                        final String finalTips = tips;
                        Snackbar.make(mDrawerLayout,tips,Snackbar.LENGTH_SHORT)
                                .setAction(tips, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(FlowMainActivity.this, finalTips, Toast.LENGTH_SHORT).show();
                                    }
                                }).show();
                    }
                });
                return true;
            }
        });
    }

    private void initViews() {
        mDrawerLayout = (DrawerLayout) findViewById(drawer_layout);
        mContent = (FrameLayout) findViewById(fl_content);
        mNavigationView = (NavigationView) findViewById(navigation_view);
        mNavigationHeader = (LinearLayout) mNavigationView.getHeaderView(0);

        ivUserImg = (ImageView)mNavigationHeader.findViewById(R.id.iv_user_img);
        tvUserName = (TextView)mNavigationHeader.findViewById(R.id.tv_user_name);
        tvUserEmail = (TextView)mNavigationHeader.findViewById(R.id.tv_user_email);
    }

    @Override
    public void onBackPressedSupport() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else {
            if(getSupportFragmentManager().getBackStackEntryCount()>1) {
                pop();
            }else{
                if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
                    finish();
                } else {
                    TOUCH_TIME = System.currentTimeMillis();
                    Toast.makeText(this, R.string.press_again_exit,Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void upDateUserInfo(){

    }

    @Override
    public void onOpenDrawer() {
        if (!mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }
}
