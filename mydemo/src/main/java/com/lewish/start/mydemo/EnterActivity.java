package com.lewish.start.mydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class EnterActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView mtvBtnFlow = (TextView) findViewById(R.id.tv_btn_flow);
        TextView mtvBtnWechat = (TextView) findViewById(R.id.tv_btn_wechat);
        TextView mtvBtnZhihu = (TextView) findViewById(R.id.tv_btn_zhihu);

        mtvBtnFlow.setOnClickListener(this);
        mtvBtnWechat.setOnClickListener(this);
        mtvBtnZhihu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_btn_flow :

                break;
            case R.id.tv_btn_wechat :

                break;
            case R.id.tv_btn_zhihu :

                break;
        }
    }
}
