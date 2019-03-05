package com.example.sxd.thanksgivinghall.finance;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.sxd.thanksgivinghall.MainActivity;
import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.adapter.FinAccountListAdapter;
import com.example.sxd.thanksgivinghall.base.BaseActivity;
import com.example.sxd.thanksgivinghall.bean.FinAccount;
import com.example.sxd.thanksgivinghall.bean.FinAccountListEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FinAccountListActivity extends BaseActivity implements FinAccountListContract.View {


    @BindView(R.id.rv_fin_account_list)
    RecyclerView rvFinAccountList;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    private FinAccountListPresenterImpl mPresenter;
    private FinAccountListAdapter mAdapter;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_fin_acccount_list);
        ButterKnife.bind(this);
        setTitle("账户列表");//设置标题
        setBackArrow();//设置返回按钮和点击事件
        mPresenter = new FinAccountListPresenterImpl(FinAccountListActivity.this,this);
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

    public  void initData() {
        mPresenter.requestAccountList();
    }

    @Override
    protected void setRightTitleOnClick(View v) {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void requestSuccess(FinAccountListEntity value) {
        fillRecyclerView(value.getData());
    }

    public  void fillRecyclerView(final  List<FinAccountListEntity.Data> data) {
        mAdapter = new FinAccountListAdapter(R.layout.fin_account_item,data);
        rvFinAccountList.setLayoutManager(new LinearLayoutManager(this));
        rvFinAccountList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter mAdapter, View view, int position) {
                Intent intent = new Intent(FinAccountListActivity.this,FinAccountDetailActivity.class);
                intent.putExtra("id", data.get(position).getId());
                startActivity(intent);
            }
        });
        mAdapter. setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, final int position) {
                Toast.makeText(FinAccountListActivity.this, "onItemLongClick" + position, Toast.LENGTH_SHORT).show();
                // 创建构建器
                AlertDialog.Builder builder = new AlertDialog.Builder(FinAccountListActivity.this);
                // 设置参数
                builder.setTitle("删除记录")
                        .setMessage("是否要要删除此条记录")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {// 积极
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mPresenter.deleteById(data.get(position).getId());
                                showMessage("删除成功");
                            }
                        }).setNegativeButton("否", new DialogInterface.OnClickListener() {// 消极
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showMessage("取消");
                    }
                });
                builder.create().show();

                //true 长按事件不会触发点击事件 false :会触发
                return true;
            }
        });
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                rvFinAccountList.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                            //数据全部加载完毕
                            mAdapter.loadMoreEnd();
                    }
                }, 800);
            }
        }, rvFinAccountList);
    }

    @Override
    public void setPresenter(FinAccountListContract.Presenter paramT) {

    }
}
