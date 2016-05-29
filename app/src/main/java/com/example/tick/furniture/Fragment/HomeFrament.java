package com.example.tick.furniture.Fragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;
import com.example.tick.furniture.R;

/**
 * Created by Tick on 2016/5/13.
 */
public class HomeFrament extends Fragment implements View.OnClickListener{
    private int[] mPhotoIntArry;
    private LinearLayout mScrollview;
    private  View view;
    private ImageView imageView;
    private ImageView [] imageViews;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.home_fg, container,false);
        initView();
        return view;
    }
    public void initView(){
        mScrollview = (LinearLayout) view.findViewById(R.id.scrollview_layout);
        mPhotoIntArry = new int[]{R.drawable.a,R.drawable.b,R.drawable.c};
        for(Integer id:mPhotoIntArry){
            mScrollview.addView(insertImage(id));
        }
    }
    private View insertImage(Integer id){
        LinearLayout layout  = new LinearLayout(getActivity().getApplicationContext());
        layout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        layout.setPadding(1, 0, 1, 0);
//        layout.setGravity(Gravity.CENTER);
        imageView = new ImageView(getActivity().getApplicationContext());
        imageView.setLayoutParams(new LayoutParams(1000,480));
        imageView.setBackgroundResource(id);
        layout.addView(imageView);
        return layout;
    }

    @Override
    public void onClick(View v) {

    }
}