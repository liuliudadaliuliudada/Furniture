package com.example.tick.furniture.Mine;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tick.furniture.R;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.SaveListener;


/**
 * Created by Tick on 2016/5/20.
 */
public class RegisterClient extends Activity implements View.OnClickListener {
    private EditText et_phoneuser, et_pw, et_checkpw, et_vcode;
    private Button bt_register_ok, bt_vcode;
    private Context context;
    private MyUser userClinet;
    private TextView register_protocol, link_ser;
    private CheckBox cb_pro;
    private ImageView register_back;
    private MyUser currentUser;//当前用户
    private IntentFilter receiverFilter;
    private String phoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_register_client);
        initView();
        cn.bmob.sms.BmobSMS.initialize(this, "6fe7fb4c95aab82551625bca29b1ff81");

    }

    public void initView() {
        context = this;
        et_phoneuser = (EditText) findViewById(R.id.et_phoneuser);
        et_pw = (EditText) findViewById(R.id.et_pw);
        et_checkpw = (EditText) findViewById(R.id.et_checkpw);
        bt_register_ok = (Button) findViewById(R.id.bt_register_ok);
        register_protocol = (TextView) findViewById(R.id.register_protocol);
        link_ser = (TextView) findViewById(R.id.link_ser);
        cb_pro = (CheckBox) findViewById(R.id.cb_protocol);
        register_back = (ImageView) findViewById(R.id.register_back);
        et_vcode = (EditText) findViewById(R.id.et_vcode);
        bt_vcode = (Button) findViewById(R.id.bt_vcode);

        register_back.setOnClickListener(this);
        cb_pro.setOnClickListener(this);
        bt_register_ok.setOnClickListener(this);
        register_protocol.setOnClickListener(this);
        link_ser.setOnClickListener(this);
        bt_vcode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_register_ok:
                phoneNum = et_phoneuser.getText().toString().trim();
                String pw = et_pw.getText().toString().trim();
                String pw_check = et_checkpw.getText().toString().trim();
                if (phoneNum.length() != 11) {
                    showToast("请输入正确手机号码");
                } else if (CheckPw(pw, pw_check)) {

                } else if (et_vcode.getText().toString().length() != 6) {
                    showToast("输入正确验证码");
                } else {
                    registerByPhone(phoneNum, pw, et_vcode.getText().toString().trim());
                }
                break;
            case R.id.bt_vcode:
                phoneNum = et_phoneuser.getText().toString().trim();
                SendVCode(phoneNum);
                break;
            case R.id.register_back:
                this.finish();
                break;
            case R.id.cb_protocol:
                if (cb_pro.isChecked() == false) {
                    bt_register_ok.setEnabled(false);
                } else {
                    bt_register_ok.setEnabled(true);
                }
                break;
            case R.id.register_protocol:
                Toast.makeText(this, "暂时没有完善", Toast.LENGTH_SHORT).show();
                break;
            case R.id.link_ser:
                Toast.makeText(this, "暂时没有完善", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //吐司显示
    public void showToast(String mess) {
        Toast.makeText(this, mess, Toast.LENGTH_LONG).show();
    }

    //设置注册用户的信息
    public boolean CheckPw(String pw, String checkPw) {
        if (!(pw.equals(checkPw))) {
            et_pw.setText("");
            et_checkpw.setText("");
            return true;
        } else
            return false;
    }

    //发送验证码
    public void SendVCode(String phoneNum) {
        if (phoneNum.length() != 11) {
            showToast("请输入正确手机号码");
        } else {
            BmobSMS.requestSMSCode(context, phoneNum, "Test1.0", new RequestSMSCodeListener() {
                @Override
                public void done(Integer smsId, BmobException ex) {
                    if (ex == null) {//验证码发送成功
                        Log.i("smile", "短信id：" + smsId);//用于查询本次短信发送详情
                    } else {
                        showToast("错误:" + ex.toString());
                    }
                }
            });
        }
    }

    //手机注册
    public void registerByPhone(String phoneNum, String pw, String vcode) {
        //注册信息并且保存在myuser中
        currentUser = new MyUser();
        currentUser.setMobilePhoneNumber(phoneNum);
        currentUser.setPassword(pw);
        currentUser.signOrLogin(context, vcode, new SaveListener() {
            @Override
            public void onSuccess() {
                showToast("注册并登陆成功");
                Log.d("aaaaa","注册成功");
            }

            @Override
            public void onFailure(int i, String s) {
                showToast("注册失败"+s);
                Log.d("aaaaa", "注册失败"+s);
            }
        });
    }
}
