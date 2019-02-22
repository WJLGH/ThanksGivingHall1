package com.example.sxd.thanksgivinghall.finance;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.adapter.FinAccSumListAdapter;
import com.example.sxd.thanksgivinghall.bean.FinAccSumListEntity;
import com.example.sxd.thanksgivinghall.utils.DeptMapUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AccSumActivity extends Fragment implements AccSumContract.View{

    @BindView(R.id.tv_main_dept)
    TextView tvMainDept;
    @BindView(R.id.tv_main_amount)
    TextView tvMainAmount;
    @BindView(R.id.rv_acc_sum_list)
    RecyclerView rvList;
    FinAccSumListAdapter adapter;
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
        String dept  = "amy";
        mPresenter.request(null,dept);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void requestSuccess(final FinAccSumListEntity value) {
        final List<FinAccSumListEntity.Data> data = value.getData();
        FinAccSumListEntity.Data mainData = value.getMainData();
        tvMainDept.setText(DeptMapUtils.getFullName(mainData.getDept()));
        tvMainAmount.setText(String.format("%.2f",mainData.getAmount()));
        adapter = new FinAccSumListAdapter(R.layout.fin_acc_sum_item,data);
        rvList.setAdapter(adapter);
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                ImageView imageView = view.findViewById(R.id.imageView);
//                imageView.setDrawingCacheEnabled(true);
//                Bitmap bitmap = Bitmap.createBitmap(imageView.getDrawingCache());
//                imageView.setDrawingCacheEnabled(false);
                String id = data.get(position).getId();
                getAccDetailList(id);
            }
        });
    }

    public void getAccDetailList(String id) {
        Intent intent = new Intent(getActivity(), FinRecordListActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    @Override
    public void setPresenter(AccSumContract.Presenter paramT) {

    }
}
