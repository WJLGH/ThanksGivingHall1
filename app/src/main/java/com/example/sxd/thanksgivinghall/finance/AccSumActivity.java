package com.example.sxd.thanksgivinghall.finance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.adapter.FinAccSumListAdapter;
import com.example.sxd.thanksgivinghall.bean.FinAccSumListEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccSumActivity extends AppCompatActivity implements AccSumContract.View{

    @BindView(R.id.rv_acc_sum_list)
    RecyclerView rvList;
    FinAccSumListAdapter finAccSumListAdapter;
    AccSumContract.Presenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc_sum);
        ButterKnife.bind(this);
        mPresenter = new AccSumPresenterImpl(this,this);
        initView();
    }

    public void initView() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        mPresenter.request(id);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void requestSuccess(List<FinAccSumListEntity.Data> data) {
        finAccSumListAdapter = new FinAccSumListAdapter(R.layout.fin_acc_sum_item,data);
        rvList.setAdapter(finAccSumListAdapter);
        rvList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void setPresenter(AccSumContract.Presenter paramT) {

    }
}
