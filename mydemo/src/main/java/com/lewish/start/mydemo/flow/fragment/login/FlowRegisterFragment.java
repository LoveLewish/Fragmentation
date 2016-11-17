package com.lewish.start.mydemo.flow.fragment.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lewish.start.mydemo.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2016/11/17 14:06.
 */

public class FlowRegisterFragment extends SupportFragment {
    private static final String TAG = "FlowRegisterFragment";
    private TextInputLayout til_usernamewrapper;
    private TextInputLayout til_passwordwrapper;
    private Button btn_login;
    private RelativeLayout rl_login;
    private Toolbar toolbar;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    /**
     * 验证邮箱
     * @param email
     * @return
     */
    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * 验证密码
     * @param password
     * @return
     */
    public boolean validatePassword(String password) {
        return password.length() > 5;
    }

    public static FlowRegisterFragment newInstance(){
        FlowRegisterFragment flowRegisterFragment = new FlowRegisterFragment();
        return flowRegisterFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flow_register, container, false);
        initView(view);
        setListener();
        return view;
    }

    private void setListener() {
        final EditText edtUsername = til_usernamewrapper.getEditText();
        final EditText edtPassword = til_passwordwrapper.getEditText();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateEmail(edtUsername.getText().toString())) {
                    til_usernamewrapper.setError("Not a valid username!");
                }else if(!validatePassword(edtPassword.getText().toString())) {
                    til_passwordwrapper.setError("Not a valid password!");
                }else {
                    til_usernamewrapper.setErrorEnabled(false);
                    til_passwordwrapper.setErrorEnabled(false);

                    Snackbar.make(rl_login,"Login success!",Snackbar.LENGTH_LONG)
                            .setAction("Action", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(_mActivity, "You Click!", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .show();
                }
            }
        });
    }

    private void initView(View view) {
        btn_login = (Button) view.findViewById(R.id.btn_login);
        til_usernamewrapper = (TextInputLayout)view.findViewById(R.id.til_usernamewrapper);
        til_passwordwrapper = (TextInputLayout)view.findViewById(R.id.til_passwordwrapper);
        rl_login = (RelativeLayout)view.findViewById(R.id.rl_login);

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        initToolbar(toolbar);
    }
    private void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("注册");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
        toolbar.inflateMenu(R.menu.hierarchy);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
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
}
