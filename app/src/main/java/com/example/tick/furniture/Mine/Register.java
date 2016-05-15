package com.example.tick.furniture.Mine;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tick.furniture.R;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;

/**
 * Created by Tick on 2016/5/14.
 */
public class Register extends Activity implements View.OnClickListener{
    private ImageView iv_back;
    private CheckBox cb_pro;
    private Button bt_registernext;
    private Context thisContext;//用于吐司显示窗口
    private EditText et_phone,et_vcode;
    private View dialogView;
    private SMS sms;
    private IntentFilter receiverFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        thisContext = this;
        initView();
        BmobSMS.initialize(this,"c6a4ff3935571d3c911e209822000610");
        //注册广播监听器
        receiverFilter = new IntentFilter();
        sms = new SMS();
        registerReceiver(sms,receiverFilter);
    }
//注销广播监听
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(sms);
    }

    public void initView(){
        iv_back = (ImageView) findViewById(R.id.iv_phoneRegister_back);
        bt_registernext = (Button) findViewById(R.id.bt_registernext);
        cb_pro = (CheckBox) findViewById(R.id.cb_protocol);
        et_phone = (EditText) findViewById(R.id.et_phone);

        cb_pro.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        bt_registernext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_phoneRegister_back:
                finish();
                break;
            case R.id.cb_protocol:
                if(cb_pro.isChecked()==false){
                    bt_registernext.setEnabled(false);
                }else{
                    bt_registernext.setEnabled(true);
                }
                break;
            case R.id.bt_registernext:
                String num = et_phone.getText().toString().trim();
                sendMessage(num);
                break;
        }
    }
    public void sendMessage(String num){
//       BmobSMS.requestSMSCode(this, num, "Test1.0", new RequestSMSCodeListener() {
//           @Override
//           public void done(Integer integer, BmobException e) {
//               if(e==null){
//                   Toast.makeText(thisContext,"发送成功",Toast.LENGTH_SHORT).show();
//                   if(dialogView==null){
//                        dialogView = LayoutInflater.from(thisContext).inflate(R.layout.dialog_register,null);
//                   }
//                   new AlertDialog.Builder(thisContext).setView(dialogView).setMessage("").setPositiveButton("确定",null).show();
//               }
//           }
//       });

        //测试
        if(dialogView==null){
            dialogView = LayoutInflater.from(thisContext).inflate(R.layout.dialog_register,null);
            et_vcode = (EditText) dialogView.findViewById(R.id.et_vcode);
        }
        new AlertDialog.Builder(thisContext).setView(dialogView).setMessage("").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
    }

    public EditText getEt_vcode() {
        return et_vcode;
    }

    public void setEt_vcode(String mess) {
        et_vcode.setText(mess);
    }
}
