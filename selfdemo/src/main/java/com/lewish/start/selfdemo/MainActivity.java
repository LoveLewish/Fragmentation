package com.lewish.start.selfdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout til_usernamewrapper;
    private TextInputLayout til_passwordwrapper;
    private Button btn_login;
    private RelativeLayout rl_login;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login = (Button) findViewById(R.id.btn_login);

        til_usernamewrapper = (TextInputLayout)findViewById(R.id.til_usernamewrapper);
        til_passwordwrapper = (TextInputLayout)findViewById(R.id.til_passwordwrapper);
        rl_login = (RelativeLayout)findViewById(R.id.rl_login);

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
                                    Toast.makeText(MainActivity.this, "You Click!", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .show();
                }
            }
        });
    }

    /**
     * 隐藏键盘
      */
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
