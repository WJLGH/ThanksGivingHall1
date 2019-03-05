package com.example.sxd.thanksgivinghall.finance;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.Constants;
import com.example.sxd.thanksgivinghall.bean.FinAccountDetailEntity;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FinAccountAddActivity extends AppCompatActivity implements  FinAccountAddContract.View{


    @BindView(R.id.etc_fin_account_add_ac_type)
    EditText etAcType;
    @BindView(R.id.et_fin_account_add_card_num)
    EditText etCardNum;
    @BindView(R.id.et_fin_account_add_ac_name)
    EditText etAcName;
    @BindView(R.id.et_fin_account_add_amount)
    EditText etAmount;
    @BindView(R.id.btn_fin_account_add_submit)
    Button btnSubmit;
    @BindView(R.id.btn_fin_account_add_clear)
    Button btnClear;

    FinAccountDetailEntity.Data data = new FinAccountDetailEntity.Data();
    private  FinAccountAddContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin_account_add);
        ButterKnife.bind(this);
        mPresenter = new FinAccountAddPresenterImpl(FinAccountAddActivity.this,this);
        initView();
    }

    public void initView() {


    }



    @OnClick(R.id.btn_fin_account_add_clear)
    public void clearView() {
        etAmount.setText("");
        etAcName.setText("");
        etAcType.setText("");
        etCardNum.setText("");
    }

    @OnClick(R.id.btn_fin_account_add_submit)
    public void submitData() {
        Double amount = Double.parseDouble(etAmount.getText().toString());
        data.setAmount(amount);
        data.setAcName(etAcName.getText().toString());
        data.setAcType(etAcType.getText().toString());
        data.setCardNum(etCardNum.getText().toString());
        mPresenter.request(data);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void requestSuccess(Base value) {
        showMessage("账户添加成功");
        finish();
    }

    @Override
    public void setPresenter(FinAccountAddContract.Presenter paramT) {

    }
}
