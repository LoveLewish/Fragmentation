package com.lewish.start.mydemo.flow.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lewish.start.mydemo.R;

import me.yokeyword.fragmentation.SupportFragment;

import static com.lewish.start.mydemo.R.id.coordinator_layout;
import static com.lewish.start.mydemo.R.id.fab;

/**
 * Created by Administrator on 2016/11/7 14:08.
 */

public class DetailFragment extends SupportFragment {
    private static final String TAG = "DetailFragment";
    public static final String ARTICLE_TITLE = "article_title";
    public static final String KEY_RESULT_TITLE = "key_result_title";

    public static final int REQ_MODIFY_FRAGMENT = 100;

    private String mStrArticleTitle;

    private CoordinatorLayout mRootLayout;
    private Toolbar mToolbar;
    private NestedScrollView mNesScrollview;
    private TextView mTvContent;
    private FloatingActionButton mFab;

    public static DetailFragment newInstance(Bundle bundle) {
        DetailFragment instance = new DetailFragment();
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mStrArticleTitle = bundle.getString(ARTICLE_TITLE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flow_detail, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRootLayout = (CoordinatorLayout) view.findViewById(coordinator_layout);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mNesScrollview = (NestedScrollView) view.findViewById(R.id.nes_scrollview);
        mTvContent = (TextView) view.findViewById(R.id.tv_content);
        mFab = (FloatingActionButton) view.findViewById(fab);

        initToolbar();

        initFab();
    }

    private void initFab() {
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(DetailFragment.ARTICLE_TITLE, mStrArticleTitle);
                startForResult(ModifyDetailFragment.newInstance(bundle), REQ_MODIFY_FRAGMENT);
            }
        });
    }

    private void initToolbar() {
        mToolbar.setTitle(mStrArticleTitle);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
        mToolbar.inflateMenu(R.menu.hierarchy);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_hierarchy:
                        _mActivity.showFragmentStackHierarchyView();
                        _mActivity.logFragmentStackHierarchy(TAG);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * 这里演示:
     * 比较复杂的Fragment页面会在第一次start时,导致动画卡顿
     * Fragmentation提供了onEnterAnimationEnd()方法,该方法会在入栈动画结束时回调
     * 所以在onCreateView进行一些简单的View初始化(比如 toolbar设置标题,返回按钮; 显示加载数据的进度条等),
     * 然后在onEnterAnimationEnd()方法里进行 复杂的耗时的初始化 (比如FragmentPagerAdapter的初始化 加载数据等)
     */
    @Override
    protected void onEnterAnimationEnd(Bundle savedInstanceState) {
        mTvContent.setText(R.string.large_text);
    }

    @Override
    protected void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        if(requestCode == REQ_MODIFY_FRAGMENT && resultCode == RESULT_OK && data != null) {
            mStrArticleTitle = data.getString(ARTICLE_TITLE);
            mToolbar.setTitle(mStrArticleTitle);

            getArguments().putString(ARTICLE_TITLE,mStrArticleTitle);
            Toast.makeText(_mActivity, "标题修改完成", Toast.LENGTH_SHORT).show();
        }
    }
}
