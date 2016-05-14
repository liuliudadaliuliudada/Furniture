package com.example.tick.furniture;

import android.media.Image;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tick.furniture.Fragment.CarFragment;
import com.example.tick.furniture.Fragment.HomeFrament;
import com.example.tick.furniture.Fragment.MineFragment;
import com.example.tick.furniture.Fragment.TypeFragment;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private FragmentManager fgMan;
    private Fragment fhome,ftype,fcar,fmine;
    private LinearLayout home_layout, type_layout, car_layout, mine_layout;
    private ImageView home_img,type_img,car_img,mine_img;
    private TextView tv_home,tv_type,tv_car,tv_mine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        fgMan = getSupportFragmentManager();
        initView();
    }

    //初始化控件
    public void initView() {
        home_layout = (LinearLayout) findViewById(R.id.home_layout);
        type_layout = (LinearLayout) findViewById(R.id.type_layout);
        car_layout = (LinearLayout) findViewById(R.id.car_layout);
        mine_layout = (LinearLayout) findViewById(R.id.mine_layout);
//        home_img = (ImageButton) findViewById(R.id.home_img);
        home_img = (ImageView) findViewById(R.id.home_img);
        type_img = (ImageView) findViewById(R.id.type_img);
        car_img = (ImageView) findViewById(R.id.car_img);
        mine_img = (ImageView) findViewById(R.id.mine_img);
        tv_home = (TextView) findViewById(R.id.tv_home);
        tv_type = (TextView) findViewById(R.id.tv_type);
        tv_car = (TextView) findViewById(R.id.tv_car);
        tv_mine = (TextView) findViewById(R.id.tv_mine);


        home_layout.setOnClickListener(this);
        type_layout.setOnClickListener(this);
        car_layout.setOnClickListener(this);
        mine_layout.setOnClickListener(this);

    }
    //清空所有操作
    public void  clear(){
        home_img.setImageResource(R.drawable.hon);
        type_img.setImageResource(R.drawable.tyn);
        car_img.setImageResource(R.drawable.carn);
        mine_img.setImageResource(R.drawable.myn);
        tv_home.setTextColor(this.getResources().getColor(R.color.textcolor));
        tv_type.setTextColor(this.getResources().getColor(R.color.textcolor));
        tv_car.setTextColor(this.getResources().getColor(R.color.textcolor));
        tv_mine.setTextColor(this.getResources().getColor(R.color.textcolor));
    }
    //隐藏所有的fragment
    public void hideFragment(FragmentTransaction fTransaction){
        if(fhome!=null)  fTransaction.hide(fhome);
        if(ftype!=null)  fTransaction.hide(ftype);
        if(fcar!=null)  fTransaction.hide(fcar);
        if(fmine!=null)  fTransaction.hide(fmine);
    }
    //选中一个item处理
    public void selectItem(int index){
        FragmentTransaction fTransaction = fgMan.beginTransaction();
        clear();
        hideFragment(fTransaction);
        switch(index){
            case 0:
                home_img.setImageResource(R.drawable.hoy);
                if(fhome==null){//如果为空，创建对象
                    fhome = new HomeFrament();
                    fTransaction.add(R.id.frament,fhome);
                }else{//不为空，显示出来
                    fTransaction.show(fhome);
                }
                tv_home.setTextColor(this.getResources().getColor(R.color.palemore));
                break;
            case 1:
                type_img.setImageResource(R.drawable.tyy);
                if(ftype==null){//如果为空，创建对象
                    ftype = new TypeFragment();
                    fTransaction.add(R.id.frament,ftype);
                }else{//不为空，显示出来
                    fTransaction.show(ftype);
                }
                tv_type.setTextColor(this.getResources().getColor(R.color.palemore));
                break;
            case 2:
                car_img.setImageResource(R.drawable.cary);
                if(fcar==null){//如果为空，创建对象
                    fcar = new CarFragment();
                    fTransaction.add(R.id.frament,fcar);
                }else{//不为空，显示出来
                    fTransaction.show(fcar);
                }
                tv_car.setTextColor(this.getResources().getColor(R.color.palemore));
                break;
            case 3:
                mine_img.setImageResource(R.drawable.myy);
                if(fmine==null){//如果为空，创建对象
                    fmine = new MineFragment();
                    fTransaction.add(R.id.frament,fmine);
                }else{//不为空，显示出来
                    fTransaction.show(fmine);
                }
                tv_mine.setTextColor(this.getResources().getColor(R.color.palemore));
                break;
        }
        fTransaction.commit();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_layout:
                selectItem(0);
                break;
            case R.id.type_layout:
                selectItem(1);
                break;
            case R.id.car_layout:
                selectItem(2);
                break;
            case R.id.mine_layout:
                selectItem(3);
                break;
        }
    }
}
