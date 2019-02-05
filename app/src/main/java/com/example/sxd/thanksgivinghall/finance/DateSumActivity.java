package com.example.sxd.thanksgivinghall.finance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.adapter.FinDateSumListAdapter;
import com.example.sxd.thanksgivinghall.bean.FinDateSumListEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DateSumActivity extends AppCompatActivity implements DateSumContract.View {

    @BindView(R.id.fin_date_sum_title_year)
    TextView tvYear;
    @BindView(R.id.fin_date_sum_title_date)
    TextView tvDate;
    @BindView(R.id.fin_date_sum_title_in_amount)
    TextView tvInAmount;
    @BindView(R.id.fin_date_sum_title_out_amount)
    TextView tvOutAmount;
    @BindView(R.id.fin_date_sum_title_amount)
    TextView tvAmount ;
   
    @BindView(R.id.rv_fin_date_sum_list)
    RecyclerView rvDateSumList;
    FinDateSumListAdapter mAdapter;
    DateSumContract.Presenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_sum);
        ButterKnife.bind(this);
        mPresenter = new DateSumPresenterImpl(DateSumActivity.this,this);
        initView();
    }

    private void initView() {
        String dateStr = null;
        Intent intent = getIntent();
        dateStr = intent.getStringExtra("dateStr");
        mPresenter.request(dateStr);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT);
    }

    @Override
    public void requestSuccess(FinDateSumListEntity value) {
        fillView(value);
    }

    private void fillView(final FinDateSumListEntity value) {
        FinDateSumListEntity.Data mainData = value.getMainData();
        tvYear.setText(mainData.getYear());
        tvDate.setText(mainData.getMonth());
        tvInAmount.setText(String.format("%.2f",mainData.getInAmount()));
        tvOutAmount.setText(String.format("%.2f",mainData.getOutAmount()));
        tvAmount.setText(String.format("%.2f",mainData.getAmount()));
        final List<FinDateSumListEntity.Data> list = value.getData();
        mAdapter = new FinDateSumListAdapter(R.layout.fin_date_sum_item,list,mainData.getDateStr());
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String dateStr = value.getData().get(position).getDateStr();
                getDateStrDetailList(dateStr);
            }
        });
        rvDateSumList.setLayoutManager(new LinearLayoutManager(this));
        rvDateSumList.setAdapter(mAdapter);
    }

    private void getDateStrDetailList(String dateStr) {
        showMessage(dateStr);
        Intent intent = new Intent(DateSumActivity.this,FinRecordListActivity.class);
        intent.putExtra("dateStr",dateStr);
        startActivity(intent);
    }

    @Override
    public void setPresenter(DateSumContract.Presenter paramT) {

    }
}
