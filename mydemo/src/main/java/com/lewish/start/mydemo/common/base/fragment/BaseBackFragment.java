package com.lewish.start.mydemo.common.base.fragment;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lewish.start.mydemo.R;


/**
 * Created by YoKeyword on 16/2/7.
 */
public class BaseBackFragment extends BaseFragment {

    protected void initToolbarNav(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });

        initToolbarMenu(toolbar);
    }
}
