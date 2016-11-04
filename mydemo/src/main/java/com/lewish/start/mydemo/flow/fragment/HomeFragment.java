package com.lewish.start.mydemo.flow.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lewish.start.mydemo.R;
import com.lewish.start.mydemo.common.base.fragment.BaseMainFragment;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.DefaultVerticalAnimator;

/**
 * Created by Administrator on 2016/11/4 15:14.
 */

public class HomeFragment extends BaseMainFragment {
    private static final String TAG = "HomeFragment";
    private CoordinatorLayout rootLayout;
    private Toolbar mToolbar;
    private RecyclerView mRecy;
    private FloatingActionButton mFab;
    public static HomeFragment newInstance(){
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootLayout = (CoordinatorLayout) inflater.inflate(R.layout.fragment_flow_home, container, false);
        initView(rootLayout);
        setListener();
        return rootLayout;
    }


    private void initView(View rootLayout) {
        mToolbar = (Toolbar) rootLayout.findViewById(R.id.toolbar);
        mRecy = (RecyclerView) rootLayout.findViewById(R.id.recy);
        mFab = (FloatingActionButton) rootLayout.findViewById(R.id.fab);

        _mActivity.setSupportActionBar(mToolbar);
        mToolbar.setTitle("Home");
        mToolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOpenDraweListener != null) {
                    mOpenDraweListener.onOpenDrawer();
                }
            }
        });
        mToolbar.inflateMenu(R.menu.home);


    }
    private void setListener() {
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_hierarchy:
                        _mActivity.showFragmentStackHierarchyView();
                        _mActivity.logFragmentStackHierarchy(TAG);
                        break;
                    case R.id.action_anim:
                        final PopupMenu popupMenu = new PopupMenu(_mActivity, mToolbar, GravityCompat.END);
                        popupMenu.inflate(R.menu.home_pop);
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.action_anim_veritical:
                                        _mActivity.setFragmentAnimator(new DefaultVerticalAnimator());
                                        Toast.makeText(_mActivity, "设置全局动画成功! 竖向", Toast.LENGTH_SHORT).show();
                                        break;
                                    case R.id.action_anim_horizontal:
                                        _mActivity.setFragmentAnimator(new DefaultHorizontalAnimator());
                                        Toast.makeText(_mActivity, "设置全局动画成功! 横向", Toast.LENGTH_SHORT).show();
                                        break;
                                    case R.id.action_anim_none:
                                        _mActivity.setFragmentAnimator(new DefaultNoAnimator());
                                        Toast.makeText(_mActivity, "设置全局动画成功! 无", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                                popupMenu.dismiss();
                                return true;
                            }
                        });
                        popupMenu.show();
                        break;
                }
                return true;
            }
        });
    }
}
