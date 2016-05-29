package com.example.tick.furniture.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tick.furniture.MainActivity;
import com.example.tick.furniture.Mine.Login;
import com.example.tick.furniture.Mine.MyUser;
import com.example.tick.furniture.Mine.UserInfo;
import com.example.tick.furniture.R;


import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Tick on 2016/5/13.
 */
public class MineFragment extends Fragment implements View.OnClickListener {
    private LinearLayout login,login_ok;
    private View view;
    private TextView phone_num;
    private BmobUser user;
    private LinearLayout wpay_layout,wget_layout,wde_layout,wassess_layout,refund_layout;
    private LinearLayout colstore_layout,colbaby_layout,coltxt_layout,vip_layout,openstroe_layout;
    private LinearLayout app_1,app_2,app_3,app_4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mine_fg, container, false);
        initView();
        return view;
    }
    //toast提示信息
    public void showToast(String mess){
        Toast.makeText(getActivity(),mess,Toast.LENGTH_SHORT).show();
    }
//唤醒后判断
    @Override
    public void onResume() {
        super.onResume();
        if (MyUser.getCurrentUser(getActivity()) == null) {
            login_ok.setVisibility(View.GONE);
            login.setVisibility(View.VISIBLE);
        }else {
            login_ok.setVisibility(View.VISIBLE);
            login.setVisibility(View.GONE);
            user =  MyUser.getCurrentUser(getActivity());
            phone_num.setText(user.getMobilePhoneNumber());
        }
    }

    //初始化控件
    public void initView() {
        login = (LinearLayout) view.findViewById(R.id.login_layout);
        login_ok = (LinearLayout) view.findViewById(R.id.login_ok_layout);

        wpay_layout = (LinearLayout) view.findViewById(R.id.wpay_layout);
        wget_layout = (LinearLayout) view.findViewById(R.id.wget_layout);
        wde_layout = (LinearLayout) view.findViewById(R.id.wde_layout);
        wassess_layout = (LinearLayout) view.findViewById(R.id.wassess_layout);
        refund_layout = (LinearLayout) view.findViewById(R.id.refund_layout);


        colstore_layout = (LinearLayout) view.findViewById(R.id.colstore_layout);
        colbaby_layout = (LinearLayout) view.findViewById(R.id.colbaby_layout);
        coltxt_layout = (LinearLayout) view.findViewById(R.id.coltxt_layout);
        vip_layout = (LinearLayout) view.findViewById(R.id.vip_layout);
        openstroe_layout = (LinearLayout) view.findViewById(R.id.openstroe_layout);

        app_1 = (LinearLayout) view.findViewById(R.id.app_1);
        app_2 = (LinearLayout) view.findViewById(R.id.app_2);
        app_3 = (LinearLayout) view.findViewById(R.id.app_3);
        app_4 = (LinearLayout) view.findViewById(R.id.app_4);

        phone_num = (TextView) view.findViewById(R.id.phone_num);
        //判断是否登陆成功
        if (MyUser.getCurrentUser(getActivity()) == null) {
            login_ok.setVisibility(View.GONE);
            login.setVisibility(View.VISIBLE);
        }else {
            login_ok.setVisibility(View.VISIBLE);
            login.setVisibility(View.GONE);
            user =  MyUser.getCurrentUser(getActivity());
            phone_num.setText(user.getMobilePhoneNumber());
        }
        login.setOnClickListener(this);
        login_ok.setOnClickListener(this);

        wpay_layout.setOnClickListener(this);
        wget_layout.setOnClickListener(this);
        wde_layout.setOnClickListener(this);
        wassess_layout.setOnClickListener(this);
        refund_layout.setOnClickListener(this);

        colstore_layout.setOnClickListener(this);
        colbaby_layout.setOnClickListener(this);
        coltxt_layout.setOnClickListener(this);
        vip_layout.setOnClickListener(this);
        openstroe_layout.setOnClickListener(this);

        app_1.setOnClickListener(this);
        app_2.setOnClickListener(this);
        app_3.setOnClickListener(this);
        app_4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_layout:
                //隐藏
                this.isHidden();
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
                break;
            case R.id.login_ok_layout:
//                Toast.makeText(getActivity(),"尚未完成",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), UserInfo.class));
                break;
            case R.id.wpay_layout:
                showToast("功能未完善");
            break;
            case R.id.wget_layout:
                showToast("功能未完善");
                break;
            case R.id.wde_layout:
                showToast("功能未完善");
                break;
            case R.id.wassess_layout:
                showToast("功能未完善");
                break;
            case R.id.refund_layout:
                showToast("功能未完善");
                break;
            case R.id.colstore_layout:
                showToast("功能未完善");
                break;
            case R.id.colbaby_layout:
                showToast("功能未完善");
                break;
            case R.id.coltxt_layout:
                showToast("功能未完善");
                break;
            case R.id.vip_layout:
                showToast("功能未完善");
                break;
            case R.id.openstroe_layout:
                showToast("功能未完善");
                break;
            case R.id.app_1:
                showToast("与微信同理");
                break;
            case R.id.app_2:
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("weixin.qq.com/d")));
                break;
            case R.id.app_3:
                showToast("与微信同理");
                break;
            case R.id.app_4:
                showToast("与微信同理");
                break;
        }
    }
}
