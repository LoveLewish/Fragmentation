package com.lewish.start.mydemo.flow.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lewish.start.mydemo.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2016/11/7 17:52.
 */

public class CycleFragment extends SupportFragment {

    public static final String CYCLE_NUM = "cycle_num";
    private static final String TAG = "CycleFragment";

    private int cycleNum;

    private Toolbar mToolbar;
    private TextView mTvTitle;
    private Button mBtnNextPopNow;
    private Button btnNext;

    public static CycleFragment newInstance(int cycleNum){
        CycleFragment instance = new CycleFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CYCLE_NUM,cycleNum);
        instance.setArguments(bundle);
        return instance;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if(arguments!=null) {
            cycleNum = arguments.getInt(CYCLE_NUM);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flow_cycle, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        mBtnNextPopNow = (Button) view.findViewById(R.id.btn_next_popnow);
        btnNext = (Button) view.findViewById(R.id.btn_next);

        mTvTitle.setText("循环Fragment"+cycleNum);
        initToolbar();
        setListener();
    }
    private void initToolbar() {
        mToolbar.setTitle("循环Fragment"+cycleNum);
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

    private void setListener(){
        mBtnNextPopNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWithPop(CycleFragment.newInstance(cycleNum+1));
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(CycleFragment.newInstance(cycleNum+1));
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden) {
            hideSoftInput();
        }
    }
}
