package com.example.sxd.thanksgivinghall.finance;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.adapter.FinAccSumListAdapter;
import com.example.sxd.thanksgivinghall.bean.FinAccSumListEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AccSumActivity extends Fragment implements AccSumContract.View{

    @BindView(R.id.rv_acc_sum_list)
    RecyclerView rvList;
    FinAccSumListAdapter finAccSumListAdapter;
    AccSumContract.Presenter mPresenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_acc_sum, container, false);
        Unbinder bind = ButterKnife.bind(this, view);
        mPresenter = new AccSumPresenterImpl(getActivity(),this);
        initView();
        return view;
    }
    public void initView() {
//        Intent intent = getIntent();
//        String id = intent.getStringExtra("id");
        mPresenter.request(null);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void requestSuccess(List<FinAccSumListEntity.Data> data) {
        finAccSumListAdapter = new FinAccSumListAdapter(R.layout.fin_acc_sum_item,data);
        rvList.setAdapter(finAccSumListAdapter);
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void setPresenter(AccSumContract.Presenter paramT) {

    }
}
