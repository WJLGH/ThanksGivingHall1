package com.example.sxd.thanksgivinghall.finance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.adapter.FinTypeSumListAdapter;
import com.example.sxd.thanksgivinghall.bean.FinTypeSumListEntity;

import butterknife.ButterKnife;

public class TypeSumActivity extends AppCompatActivity implements TypeSumContract.View {


    RecyclerView rvTypeSumList;

    private FinTypeSumListAdapter mAdapter;
    private TypeSumContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_sum);
        ButterKnife.bind(this);
        mPresenter = new TypeSumPresenterImpl(TypeSumActivity.this,this);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void requestSuccess(FinTypeSumListEntity value) {

    }

    @Override
    public void setPresenter(TypeSumContract.Presenter paramT) {

    }
}
