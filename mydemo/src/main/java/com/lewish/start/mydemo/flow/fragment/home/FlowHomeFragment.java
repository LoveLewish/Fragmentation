package com.lewish.start.mydemo.flow.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
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
import com.lewish.start.mydemo.flow.adapter.FlowHomeAdaper;
import com.lewish.start.mydemo.flow.entity.Article;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.DefaultVerticalAnimator;

/**
 * Created by Administrator on 2016/11/4 15:14.
 */

public class FlowHomeFragment extends BaseMainFragment {
    private static final String TAG = "FlowHomeFragment";

    private String[] mTitles = new String[]{
            "航拍“摩托大军”返乡高峰 如蚂蚁搬家（组图）",
            "苹果因漏电召回部分电源插头",
            "IS宣称对叙利亚爆炸案负责"
    };

    private String[] mContents = new String[]{
            "1月30日，距离春节还有不到十天，“摩托大军”返乡高峰到来。航拍广西梧州市东出口服务站附近的骑行返乡人员，如同蚂蚁搬家一般。",
            "昨天记者了解到，苹果公司在其官网发出交流电源插头转换器更换计划，召回部分可能存在漏电风险的电源插头。",
            "极端组织“伊斯兰国”31日在社交媒体上宣称，该组织制造了当天在叙利亚首都大马士革发生的连环爆炸案。"
    };

    private CoordinatorLayout rootLayout;
    private Toolbar mToolbar;
    private RecyclerView mRecy;
    private FlowHomeAdaper mFlowHomeAdaper;
    private FloatingActionButton mFab;
    public static FlowHomeFragment newInstance(){
        return new FlowHomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootLayout = (CoordinatorLayout) inflater.inflate(R.layout.fragment_flow_home, container, false);
        initView(rootLayout);
        setListener();
        loadData();
        return rootLayout;
    }


    private void initView(View rootLayout) {
        mToolbar = (Toolbar) rootLayout.findViewById(R.id.toolbar);
        mRecy = (RecyclerView) rootLayout.findViewById(R.id.recy);
        mFab = (FloatingActionButton) rootLayout.findViewById(R.id.fab);

        mToolbar.setTitle("Home");
//        _mActivity.setSupportActionBar(mToolbar);
//        当调用inflateMenu加载布局时，需要实现点击监听，无需setSupportActionBar(),否则加载的是onCreateOptionsMenu()中的布局
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

        mRecy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 5) {
                    mFab.hide();
                } else if (dy < -5) {
                    mFab.show();
                }
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(rootLayout,"你点击了小飞机",Snackbar.LENGTH_SHORT)
                        .setAction("小飞机", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(_mActivity, "你为什么点小飞机？", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
    }
    private void loadData(){
        List<Article> articleList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            int index = (int) (Math.random() * 3);
            Article article = new Article(mTitles[index], mContents[index]);
            articleList.add(article);
        }

        mFlowHomeAdaper = new FlowHomeAdaper(_mActivity,articleList);
        mFlowHomeAdaper.setOnItemClickListener(new FlowHomeAdaper.onItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Bundle bundle = new Bundle();
                bundle.putString(DetailFragment.ARTICLE_TITLE,mTitles[position%3]);
                start(DetailFragment.newInstance(bundle));
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);//设置布局管理器
        manager.setOrientation(LinearLayoutManager.VERTICAL);//设置布局方向
        mRecy.setLayoutManager(manager);//设置布局管理器
        //mRecy.addItemDecoration( new DividerGridItemDecoration(this ));//设置分隔线
        mRecy.setItemAnimator( new DefaultItemAnimator());//设置增加或删除条目的动画
        mRecy.setAdapter(mFlowHomeAdaper);

    }
}
