package com.lewish.start.mydemo.flow.fragment.discovery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lewish.start.mydemo.R;

import me.yokeyword.fragmentation.SupportFragment;

import static com.lewish.start.mydemo.R.id.tv_content;

/**
 * Created by Administrator on 2016/11/8 11:07.
 */

public class PagerChildFragment extends SupportFragment {
    private static final String TAG = "PagerChildFragment";
    public static final String STR_CONTENT = "str_content";

    private TextView mTvContent;
    private String mStrContent;

    public static PagerChildFragment newInstance(Bundle bundle){
        PagerChildFragment instance = new PagerChildFragment();
        if(bundle!=null) {
            instance.setArguments(bundle);
        }
        return  instance;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle!=null) {
            mStrContent = bundle.getString(STR_CONTENT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flow_child, container, false);
        initView(view);
        loadData();
        return view;
    }

    private void loadData() {
        mTvContent.setText(mStrContent);
    }

    private void initView(View view) {
        mTvContent = (TextView) view.findViewById(tv_content);
    }
}
