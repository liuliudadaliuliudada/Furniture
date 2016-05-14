package com.example.tick.furniture.Fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tick.furniture.MainActivity;
import com.example.tick.furniture.Mine.Login;
import com.example.tick.furniture.R;

/**
 * Created by Tick on 2016/5/13.
 */
public class MineFragment extends Fragment implements View.OnClickListener{
    private LinearLayout login;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mine_fg,container,false);
        initView();
        return view;
    }
    public void initView(){
        login  = (LinearLayout) view.findViewById(R.id.login_layout);

        login.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.login_layout:
                //隐藏
                this.isHidden();
                Intent intent  = new Intent(getActivity(),Login.class);
                startActivity(intent);
                break;
        }
    }
}
