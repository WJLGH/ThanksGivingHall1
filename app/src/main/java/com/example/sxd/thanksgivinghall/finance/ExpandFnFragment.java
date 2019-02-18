package com.example.sxd.thanksgivinghall.finance;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.adapter.HomeAdapter;
import com.example.sxd.thanksgivinghall.bean.Model;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ExpandFnFragment extends Fragment {


    @BindView(R.id.rv_fn_list)
    RecyclerView rvFnList;

    List<Model> options = new ArrayList<>();
    private HomeAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expand_fn, container, false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    private void initView() {
        initMenuOptions();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        rvFnList.setLayoutManager(gridLayoutManager);
        adapter = new HomeAdapter(R.layout.item_rv_mine, options);
        rvFnList.setAdapter(adapter);
    }

    private void initMenuOptions() {
        if(options.size() == 0) {
            options.add(new Model("账户添加",R.mipmap.add_black));
            options.add(new Model("统计",R.mipmap.add_notice));
            options.add(new Model("账户添加",R.mipmap.add_black));
        }
    }

}
