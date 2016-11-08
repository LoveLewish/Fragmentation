package com.lewish.start.mydemo.flow.fragment.discovery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.lewish.start.mydemo.R;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2016/11/8 10:15.
 */

public class DiscoveryFragment extends SupportFragment {
    private static final String TAG = "DiscoveryFragment";

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<String> tabIndicatorsList = new ArrayList<>();
    private List<PagerChildFragment> childFragmentList = new ArrayList<>();

    private MyPagerAdapter myPagerAdapter;

    public static DiscoveryFragment newInstance(Bundle bundle) {
        DiscoveryFragment instance = new DiscoveryFragment();
        if (bundle != null) {
            instance.setArguments(bundle);
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CoordinatorLayout rootLayout = (CoordinatorLayout) inflater.inflate(R.layout.fragment_flow_discovery, container, false);
        initView(rootLayout);
        loadData();
        return rootLayout;
    }

    private void loadData() {
        for (int i = 0; i < 3; i++) {
            tabIndicatorsList.add("Tab" + (i + 1));
            Bundle bundle = new Bundle();
            bundle.putString(PagerChildFragment.STR_CONTENT, "Tab" + (i + 1));
            childFragmentList.add(PagerChildFragment.newInstance(bundle));
        }

        myPagerAdapter = new MyPagerAdapter(getChildFragmentManager(), tabIndicatorsList, childFragmentList);
        mViewPager.setAdapter(myPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initView(View rootLayout) {
        mToolbar = (Toolbar) rootLayout.findViewById(R.id.toolbar);
        mTabLayout = (TabLayout) rootLayout.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) rootLayout.findViewById(R.id.view_pager);

        initToolbar();
        initTab();
    }

    private void initToolbar() {
        mToolbar.setTitle("TabLayoutDemo");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
        mToolbar.inflateMenu(R.menu.discovery);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab_add:
                        String str = "Tab" + (tabIndicatorsList.size() + 1);
                        Bundle bundle = new Bundle();
                        bundle.putString(PagerChildFragment.STR_CONTENT, str);
                        tabIndicatorsList.add(str);
                        childFragmentList.add(PagerChildFragment.newInstance(bundle));
                        myPagerAdapter.notifyDataSetChanged();
                        mTabLayout.setupWithViewPager(mViewPager);
                        break;
                    case R.id.tab_mode_fixed:
                        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
                        break;
                    case R.id.tab_mode_scrollable:
                        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                        break;
                    case R.id.action_hierarchy:
                        _mActivity.showFragmentStackHierarchyView();
                        _mActivity.logFragmentStackHierarchy(TAG);
                        break;
                }
                return true;
            }
        });
    }

    private void initTab() {
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setTabTextColors(ContextCompat.getColor(_mActivity, R.color.gray), ContextCompat.getColor(_mActivity, R.color.white));
        mTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(_mActivity, R.color.white));
        ViewCompat.setElevation(mTabLayout, 10);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    class MyPagerAdapter extends FragmentPagerAdapter {
        private List<String> tabIndicatorsList;
        private List<PagerChildFragment> childFragmentList;

        public MyPagerAdapter(FragmentManager fm, List<String> tabIndicatorsList, List<PagerChildFragment> childFragmentList) {
            super(fm);
            this.tabIndicatorsList = tabIndicatorsList;
            this.childFragmentList = childFragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return childFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return childFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabIndicatorsList.get(position);
        }
    }
}
