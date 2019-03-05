package com.example.sxd.thanksgivinghall.finance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.base.BaseActivity;
import com.example.sxd.thanksgivinghall.bean.FinAccountDetailEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FinAccountDetailActivity extends BaseActivity implements FinAccountDetailContract.View {

    @BindView(R.id.tv_fin_account_detail_acName)
    TextView tvAcName;
    @BindView(R.id.tv_fin_account_detail_acType)
    TextView tvAcType;
    @BindView(R.id.tv_fin_account_detail_cardNum)
    TextView tvCardNum;
    @BindView(R.id.tv_fin_account_detail_amount)
    TextView tvAmount;


    FinAccountDetailPresenterImpl mPresenter;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin_account_detail);
        ButterKnife.bind(this);
        setTitle("账户详情");
        mPresenter = new FinAccountDetailPresenterImpl(FinAccountDetailActivity.this, this);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        mPresenter.request(id);
    }
    @Override
    protected void setRightTitleOnClick(View v) {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    /**
     *
     * @param value
     */
    @Override
    public void requestSuccess(FinAccountDetailEntity value) {
        if(value == null || value.getData() == null) {
            Toast.makeText(this,"记录不存在", Toast.LENGTH_SHORT).show();
            return;
        }
        FinAccountDetailEntity.Data data = value.getData();

        tvAmount.setText(data.getAmount().toString());
        tvAcName.setText(data.getAcName());
        tvAcType.setText(data.getAcType());
        tvCardNum.setText(data.getCardNum());

    }

    @Override
    public void setPresenter(FinAccountDetailContract.Presenter paramT) {

    }
}
