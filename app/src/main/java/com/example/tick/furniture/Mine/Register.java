package com.example.tick.furniture.Mine;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.example.tick.furniture.R;

/**
 * Created by Tick on 2016/5/14.
 */
public class Register extends Activity implements View.OnClickListener{
    private ImageView iv_back;
    private CheckBox cb_pro;
    private Button bt_registernext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        initView();
    }
    public void initView(){
        iv_back = (ImageView) findViewById(R.id.iv_phoneRegister_back);
        bt_registernext = (Button) findViewById(R.id.bt_registernext);
        cb_pro = (CheckBox) findViewById(R.id.cb_protocol);

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
        }
    }

}
