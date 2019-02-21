package com.example.sxd.thanksgivinghall.finance;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.adapter.FinTypeSumListAdapter;
import com.example.sxd.thanksgivinghall.bean.FinTypeSumListEntity;
import com.example.sxd.thanksgivinghall.notice.NoticeAddActivity;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TypeSumActivity extends Fragment implements TypeSumContract.View {

    private static final String TAG = "TypeSumActivity";

    @BindView(R.id.tv_main_in_amount)
    TextView tvInAmount;
    @BindView(R.id.tv_main_out_amount)
    TextView tvOutAmount;

    @BindView(R.id.tv_fin_type_sum_list)
    RecyclerView rvTypeSumList;

    private FinTypeSumListAdapter mAdapter;
    private TypeSumContract.Presenter mPresenter;
    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_type_sum, container, false);
        unbinder = ButterKnife.bind(this, view);
        ButterKnife.bind(this,view);
        mPresenter = new TypeSumPresenterImpl(getActivity(), this);
        initView();
        return  view;
    }

    private void initView() {
//        Intent intent = getIntent();
//        String reType = intent.getStringExtra("reType");
        mPresenter.request(null);
    }


    private void fillView(final FinTypeSumListEntity value) {
        FinTypeSumListEntity.Data mainData = value.getMainData();
        tvInAmount.setText(String.format("%.2f",mainData.getInAmount()));
        tvOutAmount.setText(String.format("%.2f",mainData.getOutAmount()));
        mAdapter = new FinTypeSumListAdapter(R.layout.fin_type_sum_item,value.getData());
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String busType = value.getData().get(position).getBusType();
                getBusTypeDetail(busType);
            }
        });
        rvTypeSumList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTypeSumList.setAdapter(mAdapter);
    }

    private void getBusTypeDetail(String busType) {
        Intent intent = new Intent(getActivity(), FinRecordListActivity.class);
        intent.putExtra("busType", busType);
        startActivity(intent);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(),message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void requestSuccess(FinTypeSumListEntity value) {
        fillView(value);
    }

    @Override
    public void setPresenter(TypeSumContract.Presenter paramT) {

    }
}
