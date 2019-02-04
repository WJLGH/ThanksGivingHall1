package com.example.sxd.thanksgivinghall.finance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.adapter.FinRecordListAdapter;
import com.example.sxd.thanksgivinghall.bean.FinRecordListEntity;
import com.example.sxd.thanksgivinghall.notice.NoticeDetailActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FinRecordListActivity extends AppCompatActivity implements FinRecordListContract.View {

    @BindView(R.id.rv_fin_record_list)
    RecyclerView rvFinRecordList;

    private FinRecordListContract.Presenter mPresenter;
    private FinRecordListAdapter mAdapter;
    ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin_record_list);
        ButterKnife.bind(this);
        mPresenter = new FinRecordListPresenterImpl(FinRecordListActivity.this,this);
        initView();
    }

    /**
     * 设置请求的参数 可以是：
     *  busType
     *  startDate endDate
     *  account  or acName
     *  dateStr
     */
    public  void initView() {
        Intent intent = getIntent();
        //账户类型
        String id = intent.getStringExtra("id");
        String acName = intent.getStringExtra("acName");
        if(id != null || acName != null ) {
            mPresenter.requestAccList(id,acName);
            return;
        }
        //交易类型
        String busType = intent.getStringExtra("busType");
        if(busType != null) {
            mPresenter.requestBusTypeList(busType);
            return;
        }
        //时间类型
        String dateStr = intent.getStringExtra("dateStr");
        if(dateStr != null) {
            mPresenter.requestDateList(dateStr);
            return;
        }
        //时间段类型
        String startDate = intent.getStringExtra("startDate");;
        String endDate = intent.getStringExtra("endDate");;
        //startDate = endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        if(startDate != null && endDate != null) {
            mPresenter.requestStartToEndList(startDate,endDate);
            return;
        }

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void requestSuccess(FinRecordListEntity value) {
        fillRecyclerView(value.getData());
    }

    public  void fillRecyclerView(final  List<FinRecordListEntity.Data> data) {
        mAdapter = new FinRecordListAdapter(R.layout.fin_record_item,data);
        rvFinRecordList.setLayoutManager(new LinearLayoutManager(this));
        rvFinRecordList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter mAdapter, View view, int position) {
                Intent intent = new Intent(FinRecordListActivity.this,FinRecordDetailActivity.class);
                intent.putExtra("id", data.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void setPresenter(FinRecordListContract.Presenter paramT) {

    }
}
