package com.lewish.start.mydemo.flow.fragment.shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lewish.start.mydemo.R;
import com.lewish.start.mydemo.flow.fragment.home.CycleFragment;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2016/11/16 14:08.
 */

public class ContentFragment extends SupportFragment {
    private static final String TAG = "ContentFragment";
    private static final String CONTENT ="content";

    private String mStrContent;
    private TextView mTvContent;
    private Button mBtnNext;

    public static ContentFragment newInstance(String content){
        ContentFragment contentFragment = new ContentFragment();
        if(content!=null) {
            Bundle bundle = new Bundle();
            bundle.putString(CONTENT,content);
            contentFragment.setArguments(bundle);
        }
        return contentFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flow_content, container, false);
        initView(view,savedInstanceState);
        initListener();
        return view;
    }

    private void initListener() {
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getParentFragment() instanceof ShopFragment) {
                    ((ShopFragment) getParentFragment()).start(CycleFragment.newInstance(1));
//                    start(CycleFragment.newInstance(1));//好玩的
                }
            }
        });
    }

    private void initView(View view, Bundle savedInstanceState) {
        mTvContent = (TextView) view.findViewById(R.id.tv_content);
        mBtnNext = (Button) view.findViewById(R.id.btn_next);

        mTvContent.setText(mStrContent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if(arguments!=null) {
            mStrContent = arguments.getString(CONTENT);
        }
    }
}
