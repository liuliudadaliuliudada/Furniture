package com.example.tick.furniture.Mine;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tick.furniture.R;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.LogInListener;

/**
 * Created by Tick on 2016/5/23.
 */
public class PhoneLogin extends Activity implements View.OnClickListener {
    private Button bt_verify_login,bt_vcode_get;
    private EditText et_phone,et_vcode;
    private TextView tv_phone_login_link;
    private Context thisContext;//用于吐司显示窗口
    private ImageView iv_phone_login_clear,iv_phone_login_back;
    private SMS sms;
    private IntentFilter receiverFilter;
    private String vcode,phoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_login);
        thisContext = this;
        initView();
        BmobSMS.initialize(this, "6fe7fb4c95aab82551625bca29b1ff81");
        //注册广播监听器
        receiverFilter = new IntentFilter();
        receiverFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        //将对象传入，方便sms修改对象内容
        sms = new SMS(this);
        registerReceiver(sms, receiverFilter);
    }

    //注销广播监听
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(sms);
    }

    public void initView() {
        et_phone = (EditText) findViewById(R.id.et_phone_login);
        iv_phone_login_clear = (ImageView) findViewById(R.id.iv_phone_login_clear);
        et_vcode = (EditText) findViewById(R.id.et_vcode_login);
        bt_verify_login = (Button) findViewById(R.id.bt_verify_login);
        bt_vcode_get = (Button) findViewById(R.id.bt_vcode_get);
        tv_phone_login_link = (TextView) findViewById(R.id.tv_phone_login_link);
        iv_phone_login_back = (ImageView) findViewById(R.id.iv_phone_login_back);

        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (et_phone.getText().length() > 0) {
                    iv_phone_login_clear.setVisibility(View.VISIBLE);
                }else{
                    iv_phone_login_clear.setVisibility(View.INVISIBLE);
                }
            }
        });

        bt_verify_login.setOnClickListener(this);
        bt_vcode_get.setOnClickListener(this);
        tv_phone_login_link.setOnClickListener(this);
        iv_phone_login_back.setOnClickListener(this);
        iv_phone_login_clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_verify_login:
                vcode = et_vcode.getText().toString().trim();
                if(phoneNum.length()!=11||vcode.length()!=6){
                    showToast("错误手机号码或者验证码");

                }else{
                    LoginByPhone(phoneNum,vcode);
                }
                showToast("点击登录按钮");
                break;
            case R.id.bt_vcode_get:
                showToast("点击获取验证码");
                phoneNum = et_phone.getText().toString().trim();
                sendMessage(phoneNum);
                break;
            case R.id.tv_phone_login_link:
                showToast("资金不足，请不到客服");
                break;
            case R.id.iv_phone_login_back:
                finish();
                break;
            case R.id.iv_phone_login_clear:
                et_phone.setText("");
                break;
        }
    }

    public void sendMessage(String phoneNum) {
        BmobSMS.requestSMSCode(this, phoneNum, "Test1.0", new RequestSMSCodeListener() {
            @Override
            public void done(Integer integer, BmobException e) {
                if (e == null) {
                    showToast("发送成功");
                }
                else{
                    showToast("发送失败");
                }
            }
        });
    }
//吐司显示提示信息
    public void showToast(String mess){
        Toast.makeText(this,mess,Toast.LENGTH_SHORT).show();
    }
    //收到短信后自己动设置验证码
    public void setEt_vcode(String mess) {
        et_vcode.setText(mess);
    }
    //手机号码一键登录
    public void LoginByPhone(String p,String c){
        MyUser.signOrLoginByMobilePhone(this, p, c, new LogInListener<MyUser>() {
            @Override
            public void done(MyUser myUser, cn.bmob.v3.exception.BmobException e) {
                if(myUser!=null){
                    showToast("登陆成功");
                    //跳转到登录界面
                    startActivity(new Intent(thisContext,Login.class));
                }else{
                    showToast("登录失败,"+e.toString());
                }
            }
        });
    }
}
