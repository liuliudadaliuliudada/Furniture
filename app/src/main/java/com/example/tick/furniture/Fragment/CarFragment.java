package com.example.tick.furniture.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.tick.furniture.R;

/**
 * Created by Tick on 2016/5/13.
 */
public class CarFragment extends Fragment {
    private View view;
    private RecyclerView rv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.car_fg,container,false);
        initView();
        return view ;
    }
    public void initView(){
        rv = (RecyclerView) view.findViewById(R.id.car_recyclerview);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(new CarAdapter());
    }

}
