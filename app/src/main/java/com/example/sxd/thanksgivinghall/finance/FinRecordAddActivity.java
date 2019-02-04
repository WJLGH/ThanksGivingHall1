package com.example.sxd.thanksgivinghall.finance;

import android.app.DatePickerDialog;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.sxd.thanksgivinghall.bean.FinRecordDetailEntity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

public class FinRecordAddActivity extends AppCompatActivity implements  FinRecordAddContract.View{


    @BindView(R.id.et_fin_record_add_amount)
    EditText etAmount;
    @BindView(R.id.et_fin_record_add_desc)
    EditText etDescription;
    @BindView(R.id.et_fin_record_add_out_ac)
    EditText etOutId;
    @BindView(R.id.et_fin_record_add_in_ac)
    EditText etInId;
    @BindView(R.id.sp_fin_record_add_re_type)
    Spinner spReType;
    @BindView(R.id.sp_fin_record_add_bus_type)
    Spinner spBusType;
    @BindView(R.id.dp_fin_record_add_note_date)
    DatePicker dpNoteDate;
    @BindView(R.id.btn_fin_record_add_submit)
    Button btnSubmit;
    @BindView(R.id.btn_fin_record_add_clear)
    Button btnClear;

    FinRecordDetailEntity.Data data = new FinRecordDetailEntity.Data();
    private  FinRecordAddContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin_record_add);
        ButterKnife.bind(this);
        mPresenter = new FinRecordAddPresenterImpl(FinRecordAddActivity.this,this);
        initView();
    }

    public void initView() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayofMonth = calendar.get(Calendar.DAY_OF_MONTH);
        //默认是今天
        data.setNoteDate(String.format("%d-%02d-%02d",year,month+1,dayofMonth));

        dpNoteDate.init(year, month, dayofMonth, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                data.setNoteDate(String.format("%d-%02d-%02d",year,monthOfYear+1,dayOfMonth));
            }
        });
        spReType.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                AppCompatTextView textView = (AppCompatTextView) view;
                String type = textView.getText().toString();
                data.setReType(type);
                initBusSpinner(type);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spBusType.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                AppCompatTextView textView = (AppCompatTextView) view;
                String busType = textView.getText().toString();
                data.setBusType(busType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public  void initBusSpinner(String type) {
        String[] showList = null;
        Resources res = getResources();
        if(Constants.FIN_REOCRD_TYPE_IN.equals(type)) {
            showList = res.getStringArray(R.array.busTypeIn);
        } else if(Constants.FIN_REOCRD_TYPE_OUT.equals(type)) {
            showList = res.getStringArray(R.array.busTypeOut);
        } else if(Constants.FIN_REOCRD_TYPE_CH.equals(type)) {
            showList = res.getStringArray(R.array.busTypeCh);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,showList);
        spBusType.setAdapter(arrayAdapter);
    }

    @OnClick(R.id.btn_fin_record_add_clear)
    public void clearView() {
        etAmount.setText("");
        etDescription.setText("");
        etOutId.setText("");
        etInId.setText("");
    }

    @OnClick(R.id.btn_fin_record_add_submit)
    public void submitData() {
        Double amount = Double.parseDouble(etAmount.getText().toString());
        //如果是支出金额为负数
        if(Constants.FIN_REOCRD_TYPE_OUT.equals(data.getReType())) {
            amount = - amount;
        }
        data.setAmount(amount);
//        通过下拉框选择
//        data.setReType(etReType.getText().toString());
        data.setDescription(etDescription.getText().toString());
        data.setOutId(etOutId.getText().toString());
        data.setInId(etInId.getText().toString());
//        通过DataPicker设置了
//        data.setNoteDate(etNoteDate.getText().toString());
//       通过下拉框选择
//        data.setBusType(etBusType.getText().toString());
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
