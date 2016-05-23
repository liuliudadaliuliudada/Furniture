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
import android.widget.Toast;

import com.example.tick.furniture.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;


/**
 * Created by Tick on 2016/5/13.
 */
public class Login extends Activity implements View.OnClickListener, TextWatcher {
    private ImageView iv_back, iv_eye, iv_userclear, iv_pwclear;
    private Button bt_login;
    private boolean isEye;
    private EditText et_pw, et_user;
    private TextView tv_loginfast, tv_backpw, tv_register_user;

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
        tv_loginfast = (TextView) findViewById(R.id.tv_loginfast);
        tv_backpw = (TextView) findViewById(R.id.tv_backpw);
        tv_register_user = (TextView) findViewById(R.id.tv_register_user);

        iv_eye = (ImageView) findViewById(R.id.iv_eye);
        isEye = false;

        iv_back.setOnClickListener(this);
        iv_eye.setOnClickListener(this);
        bt_login.setOnClickListener(this);
        tv_loginfast.setOnClickListener(this);
        tv_backpw.setOnClickListener(this);
        tv_register_user.setOnClickListener(this);
        //设置Textwatch
        et_user.addTextChangedListener(this);
        et_pw.addTextChangedListener(this);
        iv_userclear.setOnClickListener(this);
        iv_pwclear.setOnClickListener(this);
        //设置Ontouch
//        et_user.setOnTouchListener(this);
//        et_pw.setOnTouchListener(this);
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
                BmobUser.loginByAccount(this, et_user.getText().toString().trim(), et_pw.getText().toString().trim(), new LogInListener<MyUser>() {
                    @Override
                    public void done(MyUser myUser, BmobException e) {
                        if (myUser != null) {
                            Log.d("aaaaaa", "登陆成功");
                            show("登陆成功");
                        }
                    }
                });
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
            case R.id.tv_loginfast:
                Intent intent = new Intent(this, PhoneLogin.class);
                startActivity(intent);

                break;
            case R.id.tv_backpw:
                break;
            case R.id.tv_register_user:
                startActivity(new Intent(this, RegisterClient.class));
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
        } else if (et_user.getText().length() == 0) {
            iv_userclear.setVisibility(View.INVISIBLE);
        }

        if (et_pw.getText().length() > 0) {
            iv_pwclear.setVisibility(View.VISIBLE);
        } else if (et_pw.getText().length() == 0) {
            iv_pwclear.setVisibility(View.INVISIBLE);
        }
//若账号密码都不为空才能可用
        if (et_pw.getText().length() > 0 && et_user.getText().length() > 0) {
            bt_login.setEnabled(true);
        } else {
            bt_login.setEnabled(false);
        }
    }

    //toast
    public void show(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
//获取焦距时候，清楚图片显示隐藏判断
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        switch(v.getId()){
//            case R.id.et_user:
//                iv_pwclear.setVisibility(View.INVISIBLE);
//                iv_userclear.setVisibility(View.VISIBLE);
//                break;
//            case R.id.et_pw:
//                iv_userclear.setVisibility(View.INVISIBLE);
//                iv_pwclear.setVisibility(View.VISIBLE);
//                break;
//        }
//        return false;
//    }
}
