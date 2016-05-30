package com.example.tick.furniture.Mine;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tick.furniture.R;

import cn.bmob.v3.BmobUser;

/**
 * Created by Tick on 2016/5/25.
 */
public class UserInfo extends Activity implements View.OnClickListener {
    private TextView userexit;
    private ImageView iv_userinfo_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mineuserinfo_layout);
        initView();
    }
    public void initView(){
        userexit = (TextView) findViewById(R.id.userexit);
        userexit.setOnClickListener(this);
        iv_userinfo_back = (ImageView) findViewById(R.id.iv_userinfo_back);
        iv_userinfo_back.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.userexit:
                //是退出向前activity还是清除缓存？应该是清除缓存
                MyUser.logOut(this);
                showToast("退出了当前用户");
                break;
            case R.id.iv_userinfo_back:
                finish();
                break;
        }
    }
    public void showToast(String mess){
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
    }
}
