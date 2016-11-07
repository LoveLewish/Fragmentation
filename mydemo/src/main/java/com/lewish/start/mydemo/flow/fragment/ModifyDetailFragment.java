package com.lewish.start.mydemo.flow.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lewish.start.mydemo.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2016/11/7 15:27.
 */

public class ModifyDetailFragment extends SupportFragment {
    private static final String TAG = "ModifyDetailFragment";
    private String mStrArticleTitle;
    private Toolbar mToolbar;
    private EditText mEtModifyTitle;
    private Button btnModify;
    private Button btnNext;
    public static ModifyDetailFragment newInstance(Bundle bundle){
        ModifyDetailFragment instance = new ModifyDetailFragment();
        instance.setArguments(bundle);
        return instance;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle!=null) {
            mStrArticleTitle = bundle.getString(DetailFragment.ARTICLE_TITLE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flow_modify, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mEtModifyTitle = (EditText) view.findViewById(R.id.et_modify_title);
        btnModify = (Button) view.findViewById(R.id.btn_modify);
        btnNext = (Button) view.findViewById(R.id.btn_next);

        initToolbar();
        setListener();
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

    private void setListener(){
        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftInput();
                String mStrModifiedTitle = mEtModifyTitle.getText().toString().trim();
                mToolbar.setTitle(mStrModifiedTitle);
                Bundle bundle = new Bundle();
                bundle.putString(DetailFragment.ARTICLE_TITLE,mStrModifiedTitle);
                setFramgentResult(RESULT_OK,bundle);
                Toast.makeText(_mActivity, "标题修改完毕", Toast.LENGTH_SHORT).show();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftInput();
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
