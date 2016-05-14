package com.example.tick.furniture.Mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tick.furniture.MainActivity;
import com.example.tick.furniture.R;

/**
 * Created by Tick on 2016/5/13.
 */
public class Login extends Activity implements View.OnClickListener, TextWatcher {
    private ImageView iv_back, iv_eye, iv_userclear, iv_pwclear;
    private Button bt_login;
    private boolean isEye;
    private EditText et_pw, et_user;
    private TextView tv_register, tv_backpw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        initView();
    }

    public void initView() {
        iv_back = (ImageView) findViewById(R.id.login_back);
        bt_login = (Button) findViewById(R.id.bt_login);
        et_pw = (EditText) findViewById(R.id.et_pw);
        et_user = (EditText) findViewById(R.id.et_user);
        iv_userclear = (ImageView) findViewById(R.id.iv_userclear);
        iv_pwclear = (ImageView) findViewById(R.id.iv_pwclear);
        tv_register = (TextView) findViewById(R.id.tv_register);
        tv_backpw = (TextView) findViewById(R.id.tv_backpw);

        iv_eye = (ImageView) findViewById(R.id.iv_eye);
        isEye = false;

        iv_back.setOnClickListener(this);
        iv_eye.setOnClickListener(this);
        bt_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        tv_backpw.setOnClickListener(this);
        //设置Textwatch
        et_user.addTextChangedListener(this);
        et_pw.addTextChangedListener(this);
        iv_userclear.setOnClickListener(this);
        iv_pwclear.setOnClickListener(this);
    }

    //清空图是否显示
    public void EtClear() {

    }

    //眼睛换色
    public void setEye() {
        if (isEye == false) {
            iv_eye.setImageResource(R.drawable.eyey);
            isEye = true;
            et_pw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            iv_eye.setImageResource(R.drawable.eyen);
            isEye = false;
            et_pw.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                this.finish();
                break;
            case R.id.bt_login:
                break;
            case R.id.iv_eye:
                setEye();
                break;
            case R.id.iv_userclear:
                et_user.setText("");
                break;
            case R.id.iv_pwclear:
                et_pw.setText("");
                break;
            case R.id.tv_register:
                Intent intent = new Intent(this, Register.class);
                startActivity(intent);
                break;
            case R.id.tv_backpw:
                break;

        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        //判断输入框是否为空
        if (et_user.getText().length() > 0) {
            iv_userclear.setVisibility(View.VISIBLE);
        } else {
            iv_userclear.setVisibility(View.INVISIBLE);
        }

        if (et_pw.getText().length() > 0) {
            iv_pwclear.setVisibility(View.VISIBLE);
        } else {
            iv_pwclear.setVisibility(View.INVISIBLE);
        }
//若账号密码都不为空才能可用
        if (et_pw.getText().length() > 0 && et_user.getText().length() > 0) {
            bt_login.setEnabled(true);
        } else {
            bt_login.setEnabled(false);
        }
    }
}
