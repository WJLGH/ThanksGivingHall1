package com.example.sxd.thanksgivinghall.finance;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.adapter.FinTypeSumListAdapter;
import com.example.sxd.thanksgivinghall.bean.FinTypeSumListEntity;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TypeSumActivity extends AppCompatActivity implements TypeSumContract.View {

    private static final String TAG = "TypeSumActivity";

    @BindView(R.id.tv_fin_type_title_amount)
    TextView tvAmount;
    @BindView(R.id.tv_fin_type_title_re_type)
    TextView tvReType;
    @BindView(R.id.tv_fin_type_sum_list)
    RecyclerView rvTypeSumList;

    private FinTypeSumListAdapter mAdapter;
    private TypeSumContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_type_sum);
        ButterKnife.bind(this);
        mPresenter = new TypeSumPresenterImpl(TypeSumActivity.this,this);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        String reType = intent.getStringExtra("reType");
        mPresenter.request(reType);
    }


    private void fillView(final FinTypeSumListEntity value) {
        FinTypeSumListEntity.Data mainData = value.getMainData();
        tvReType.setText(mainData.getReType());
        tvAmount.setText(String.format("%.2f",mainData.getAmount()));
        mAdapter = new FinTypeSumListAdapter(R.layout.fin_type_sum_item,value.getData());
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String busType = value.getData().get(position).getBusType();
                getBusTypeDetail(busType);
            }
        });
        rvTypeSumList.setLayoutManager(new LinearLayoutManager(TypeSumActivity.this));
        rvTypeSumList.setAdapter(mAdapter);
    }

    private void getBusTypeDetail(String busType) {
        Intent intent = new Intent(TypeSumActivity.this, FinRecordListActivity.class);
        intent.putExtra("busType", busType);
        startActivity(intent);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void requestSuccess(FinTypeSumListEntity value) {
        fillView(value);
    }

    @Override
    public void setPresenter(TypeSumContract.Presenter paramT) {

    }
}
