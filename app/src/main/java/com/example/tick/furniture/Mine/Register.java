package com.example.tick.furniture.Mine;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Layout;
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
import cn.bmob.sms.listener.VerifySMSCodeListener;

/**
 * Created by Tick on 2016/5/14.
 */
public class Register extends Activity implements View.OnClickListener {
    private ImageView iv_back;
    private CheckBox cb_pro;
    private Button bt_registernext;
    private Context thisContext;//用于吐司显示窗口
    private EditText et_phone, et_vcode;
    private View dialogView;
    private SMS sms;
    private IntentFilter receiverFilter;
    private TextView register_protocol, link_ser;
    private AlertDialog ad;
    private String phoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        thisContext = this;
        initView();
        BmobSMS.initialize(this, "c6a4ff3935571d3c911e209822000610");
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
        iv_back = (ImageView) findViewById(R.id.iv_phoneRegister_back);
        bt_registernext = (Button) findViewById(R.id.bt_registernext);
        cb_pro = (CheckBox) findViewById(R.id.cb_protocol);
        et_phone = (EditText) findViewById(R.id.et_phone);
        register_protocol = (TextView) findViewById(R.id.register_protocol);
        link_ser = (TextView) findViewById(R.id.link_ser);


        cb_pro.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        bt_registernext.setOnClickListener(this);
        register_protocol.setOnClickListener(this);
        link_ser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_phoneRegister_back:
                finish();
                break;
            case R.id.cb_protocol:
                if (cb_pro.isChecked() == false) {
                    bt_registernext.setEnabled(false);
                } else {
                    bt_registernext.setEnabled(true);
                }
                break;
            case R.id.bt_registernext:
                phoneNum = et_phone.getText().toString().trim();
                if (phoneNum.length() != 11) {
                    Toast.makeText(this, "请输入正确手机号码", Toast.LENGTH_SHORT).show();
                } else {
                    sendMessage();
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

    public void sendMessage() {
        BmobSMS.requestSMSCode(this, phoneNum, "Test1.0", new RequestSMSCodeListener() {
            @Override
            public void done(Integer integer, BmobException e) {
                if (e == null) {
                    Toast.makeText(thisContext, "发送成功", Toast.LENGTH_SHORT).show();
                    if (dialogView == null) {
                        dialogView = LayoutInflater.from(thisContext).inflate(R.layout.dialog_register, null);
                        et_vcode = (EditText) dialogView.findViewById(R.id.et_vcode);
                    }
                    if (ad == null) {
                        ad = new AlertDialog.Builder(thisContext).setView(dialogView).setMessage("").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                BmobSMS.verifySmsCode(thisContext, phoneNum, et_vcode.getText().toString(), new VerifySMSCodeListener() {
                                    @Override
                                    public void done(BmobException e) {
                                        if (e == null) {
                                            //验证成功
                                            Log.i("bmob", "验证通过");

                                        } else {
                                            //验证失败
                                            Log.i("bmob", "验证失败：code =" + e.getErrorCode() + ",msg = " + e.getLocalizedMessage());
                                        }
                                    }
                                });
                            }
                        }).show();
                    } else {
                        ad.show();
                    }
                }
            }
        });

        //测试
//        if (dialogView == null) {
//            dialogView = LayoutInflater.from(thisContext).inflate(R.layout.dialog_register, null);
//            et_vcode = (EditText) dialogView.findViewById(R.id.et_vcode);
//        }
//        if (ad == null) {
//            ad = new AlertDialog.Builder(thisContext).setView(dialogView).setMessage("").setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                }
//            }).show();
//        }else{
//            ad.show();
//        }
    }

    public EditText getEt_vcode() {
        return et_vcode;
    }

    public void setEt_vcode(String mess) {
        et_vcode.setText(mess);
    }
}
