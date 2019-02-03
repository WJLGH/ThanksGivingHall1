package com.example.sxd.thanksgivinghall.finance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.FinRecordDetailEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FinRecordAddActivity extends AppCompatActivity implements  FinRecordAddContract.View{

    @BindView(R.id.et_fin_record_add_re_type)
    EditText etReType;
    @BindView(R.id.et_fin_record_add_amount)
    EditText etAmount;
    @BindView(R.id.et_fin_record_add_desc)
    EditText etDescription;
    @BindView(R.id.et_fin_record_add_out_ac)
    EditText etOutId;
    @BindView(R.id.et_fin_record_add_in_ac)
    EditText etInId;
    @BindView(R.id.et_fin_record_add_note_date)
    EditText etNoteDate;
    @BindView(R.id.et_fin_record_add_bus_type)
    EditText etBusType;

    @BindView(R.id.btn_fin_record_add_submit)
    Button btnSubmit;
    @BindView(R.id.btn_fin_record_add_clear)
    Button btnClear;

    private  FinRecordAddContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin_record_add);
        ButterKnife.bind(this);
        mPresenter = new FinRecordAddPresenterImpl(FinRecordAddActivity.this,this);
    }

    @OnClick(R.id.btn_fin_record_add_clear)
    public void clearView() {
        etAmount.setText("");
        etReType.setText("");
        etDescription.setText("");
        etOutId.setText("");
        etInId.setText("");
        etNoteDate.setText("");
        etBusType.setText("");
    }
    @OnClick(R.id.btn_fin_record_add_submit)
    public void submitData() {
        FinRecordDetailEntity.Data data = new FinRecordDetailEntity.Data();
        data.setAmount(Double.parseDouble(etAmount.getText().toString()));
        data.setReType(etReType.getText().toString());
        data.setDescription(etDescription.getText().toString());
        data.setOutId(etOutId.getText().toString());
        data.setInId(etInId.getText().toString());
        data.setNoteDate(etNoteDate.getText().toString());
        data.setBusType(etBusType.getText().toString());
        mPresenter.request(data);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void requestSuccess(Base value) {
        showMessage("明细添加成功");
        finish();
    }

    @Override
    public void setPresenter(FinRecordAddContract.Presenter paramT) {

    }
}
