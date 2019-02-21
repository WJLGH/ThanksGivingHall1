package com.example.sxd.thanksgivinghall.finance;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.adapter.FinRecordListAdapter;
import com.example.sxd.thanksgivinghall.base.BaseActivity;
import com.example.sxd.thanksgivinghall.bean.FinRecordListEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FinRecordListActivity extends BaseActivity implements FinRecordListContract.View {


    @BindView(R.id.rv_fin_record_list)
    RecyclerView rvFinRecordList;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    private FinRecordListContract.Presenter mPresenter;
    private FinRecordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_fin_record_list);
        ButterKnife.bind(this);
        setTitle("记录明细");//设置标题
        setBackArrow();//设置返回按钮和点击事件
        mPresenter = new FinRecordListPresenterImpl(FinRecordListActivity.this,this);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    public void initView() {
        swipeLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
                swipeLayout.setRefreshing(false);//刷新事件结束，隐藏刷新进度条
            }
        });
    }
    /**
     * 设置请求的参数 可以是：
     *  busType
     *  startDate endDate
     *  account  or acName
     *  dateStr
     */
    public  void initData() {
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
    protected void setRightTitleOnClick(View v) {

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
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                rvFinRecordList.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                            //数据全部加载完毕
                            mAdapter.loadMoreEnd();
                    }
                }, 800);
            }
        }, rvFinRecordList);
    }

    @Override
    public void setPresenter(FinRecordListContract.Presenter paramT) {

    }
}
