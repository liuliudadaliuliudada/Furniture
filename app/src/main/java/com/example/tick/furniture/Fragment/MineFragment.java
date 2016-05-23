package com.example.tick.furniture.Fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tick.furniture.MainActivity;
import com.example.tick.furniture.Mine.Login;
import com.example.tick.furniture.Mine.MyUser;
import com.example.tick.furniture.R;


import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Tick on 2016/5/13.
 */
public class MineFragment extends Fragment implements View.OnClickListener {
    private LinearLayout login,login_ok;
    private View view;
    //注解的使用
//    @ViewById(R.id.wpay_layout)//待付款
//    LinearLayout wpay_layout;
//
//    @ViewById(R.id.wget_layout)//待收货
//    LinearLayout wget;
//
//    @ViewById(R.id.wde_layout)//待发货
//    LinearLayout wde;
//
//    @ViewById(R.id.wassess_layout)//待评价
//    LinearLayout wassess;
//
//    @ViewById(R.id.refund_layout)//退款
//    LinearLayout refund;

//    @ViewById(R.id.login_layout)
//    LinearLayout login;

//    @ViewById(R.id.login_ok_layout)
//    LinearLayout login_ok;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mine_fg, container, false);
        initView();
        return view;
    }

    public void initView() {
        login = (LinearLayout) view.findViewById(R.id.login_layout);
        login_ok = (LinearLayout) view.findViewById(R.id.login_ok_layout);

      //判断是否登陆成功
//        if (user == null) {
//            login_ok.setVisibility(View.GONE);
//            login.setVisibility(View.VISIBLE);
//        }else {
//            login_ok.setVisibility(View.VISIBLE);
//            login.setVisibility(View.GONE);
//
//        }
        login.setOnClickListener(this);
        login_ok.setOnClickListener(this);
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
                Toast.makeText(getActivity(),"尚未完成",Toast.LENGTH_SHORT).show();
                break;
//            case R.id.wpay_layout:
//                Toast.makeText(getActivity(),"尚未完成",Toast.LENGTH_SHORT).show();
//                break;
        }
    }
}
