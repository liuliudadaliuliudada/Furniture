package com.example.tick.furniture.Fragment;

import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tick.furniture.Car.CarInfo;
import com.example.tick.furniture.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tick on 2016/5/27.
 */
class CarAdapter extends RecyclerView.Adapter {
    public CarAdapter() {
        add();
    }

    private ArrayList<CarInfo> cc = new ArrayList<>();
    class CarIteam extends RecyclerView.ViewHolder implements View.OnClickListener{
        private View root;
        private CheckBox cb;
        private ImageView iv_car_photo;
        private Button bt_car_option;
        private TextView tv_car_type,tv_car_num,tv_car_price;
//        private OnRecyclerViewItemClickListener myClickListener = null;
        public CarIteam(View v) {
            super(v);
            this.root = v;
            //将listener参数传入，监听
//            myClickListener = mycl;

            cb = (CheckBox) root.findViewById(R.id.cb_car);
            iv_car_photo = (ImageView) root.findViewById(R.id.iv_car_photo);
            tv_car_type = (TextView) root.findViewById(R.id.tv_car_type);
            tv_car_num = (TextView) root.findViewById(R.id.tv_car_num);
            tv_car_price = (TextView) root.findViewById(R.id.tv_car_price);
            bt_car_option = (Button) root.findViewById(R.id.bt_car_option);
            bt_car_option.setOnClickListener(this);
        }


        public TextView getTv_car_type() {
            return tv_car_type;
        }

        public TextView getTv_car_num() {
            return tv_car_num;
        }

        public TextView getTv_car_price() {
            return tv_car_price;
        }

        public TextView getTv_car_option() {
            return bt_car_option;
        }

        @Override
        public void onClick(View v) {
//            if(myClickListener!=null){
//                myClickListener.onItemClick(itemView,getPosition());
//            }
            switch(v.getId()){
                case R.id.bt_car_option:
                    Log.d("aaaaaaa","点击了第"+getAdapterPosition()+"个删除");
                    delete(getAdapterPosition());
                    break;

            }
        }
    }

    //绑定控件
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CarIteam carIteam = new CarIteam(LayoutInflater.from(parent.getContext()).inflate(R.layout.car_recyclerview_layout, null));
        return carIteam;
    }
    //创建carinfo对象
//   CarInfo[] cInfo = new CarInfo[]{new CarInfo("豪华吊灯","1099"),new CarInfo("长版沙发","4099"),new CarInfo("玉白电视柜","1299"),new CarInfo("单人创意櫈","899")};
    //删除item项目，初测
    public void delete(int position){
        cc.remove(position);
        notifyItemRemoved(position);
    }

//    CarInfo carInfo;
    CarInfo[] cInfo = new CarInfo[]{new CarInfo("豪华吊灯","1099"),new CarInfo("长版沙发","4099"),new CarInfo("玉白电视柜","1299"),new CarInfo("单人创意櫈","899")};

    public void add(){
        for(CarInfo ci:cInfo)
              cc.add(ci);
    }
    //对控件进行操作
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CarIteam carIteam = (CarIteam) holder;
//        carIteam.getTv_car_type().setText(cInfo[position].getName());
//        carIteam.getTv_car_price().setText(cInfo[position].getPrice());
        carIteam.getTv_car_type().setText(cc.get(position).getName());
        carIteam.getTv_car_price().setText(cc.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        Log.d("aaaaaaaa",cc.size()+"");
        return cc.size();
    }

    //define interface
    public static interface OnRecyclerViewItemClickListener{
        public void onItemClick(View v ,int position);
    }



}