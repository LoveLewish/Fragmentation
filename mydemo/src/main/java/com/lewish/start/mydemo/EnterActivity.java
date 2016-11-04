package com.lewish.start.mydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.lewish.start.mydemo.flow.activity.FlowMainActivity;

public class EnterActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button mBtnFlow = (Button) findViewById(R.id.btn_flow);
        Button mBtnWechat = (Button) findViewById(R.id.btn_wechat);
        Button mBtnZhihu = (Button) findViewById(R.id.btn_zhihu);

        mBtnFlow.setOnClickListener(this);
        mBtnWechat.setOnClickListener(this);
        mBtnZhihu.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_flow:
                startActivity(new Intent(this, FlowMainActivity.class));
                break;
            case R.id.btn_wechat:

                break;
            case R.id.btn_zhihu:

                break;
        }
    }
}
