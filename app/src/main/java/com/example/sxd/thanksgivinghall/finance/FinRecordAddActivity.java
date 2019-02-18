package com.example.sxd.thanksgivinghall.finance;

import android.app.DatePickerDialog;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.base.BaseActivity;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.Constants;
import com.example.sxd.thanksgivinghall.bean.FinAccountListEntity;
import com.example.sxd.thanksgivinghall.bean.FinRecordDetailEntity;
import com.example.sxd.thanksgivinghall.notice.NoticeAddActivity;
import com.example.sxd.thanksgivinghall.utils.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

public class FinRecordAddActivity extends BaseActivity implements  FinRecordAddContract.View {


    @BindView(R.id.et_fin_record_add_amount)
    EditText etAmount;
    @BindView(R.id.et_fin_record_add_desc)
    EditText etDescription;
    @BindView(R.id.tv_fin_record_add_in_ac)
    TextView tvInAc;
    @BindView(R.id.tv_fin_record_add_out_ac)
    TextView tvOutAc;
    @BindView(R.id.sp_fin_record_add_re_type)
    Spinner spReType;
    @BindView(R.id.sp_fin_record_add_bus_type)
    Spinner spBusType;
    @BindView(R.id.dp_fin_record_add_note_date)
    DatePicker dpNoteDate;
    @BindView(R.id.btn_fin_record_add_submit)
    Button btnSubmit;
    @BindView(R.id.sp_fin_record_add_in_ac)
    Spinner spInAc;
    @BindView(R.id.sp_fin_record_add_out_ac)
    Spinner spOutAc;
    @BindView(R.id.sp_fin_record_add_dept)
    Spinner spDept;


    FinRecordDetailEntity.Data data = new FinRecordDetailEntity.Data();
    private  FinRecordAddContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_fin_record_add);
        ButterKnife.bind(this);
        setTitle("新增记录");//设置标题
        setBackArrow();//设置返回按钮和点击事件
        mPresenter = new FinRecordAddPresenterImpl(FinRecordAddActivity.this,this);
        initView();
    }

    public void initView() {
        initNoteDateDatePicker();
        spReType.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                TextView textView = (TextView) view;
                if (view == null) {
                    return;
                }
                String type = textView.getText().toString();
                data.setReType(type);
                initBusSpinner(type);
                //初始化 收入支出账户列表
                clearView(type);
                initInOutAcSpinner(type);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spBusType.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //showMessage("选中的是第"+id+"个");
                TextView textView = (TextView) view;
                if (view == null) {
                    return;
                }
                String busType = textView.getText().toString();
                data.setBusType(busType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spDept.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //showMessage("选中的是第"+id+"个");
                TextView textView = (TextView) view;
                if (view == null) {
                   return;
                }
                String dept = textView.getText().toString();
                data.setDept(dept);
//                showMessage("选中的是"+dept);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    protected void setRightTitleOnClick(View v) {

    }

    private void initNoteDateDatePicker() {
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
    }

    /**
     * 根据reType初始化BusType下拉框
     * @author wjl
     * @date 2019-2-13
     */
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

    //@OnClick(R.id.btn_fin_record_add_clear)
    /**
     * 清空账户下拉列表，从数据库中重新获取账户列表
     * @author wjl
     * @date 2019/2/13 8:37
     */
    public void clearView(String type) {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        if(Constants.FIN_REOCRD_TYPE_IN.equals(type)) {
            spInAc.setVisibility(View.VISIBLE);
            tvInAc.setVisibility(View.VISIBLE);
            spOutAc.setVisibility(View.GONE);
            tvOutAc.setVisibility(View.GONE);
        } else if(Constants.FIN_REOCRD_TYPE_OUT.equals(type)) {
            spInAc.setVisibility(View.GONE);
            tvInAc.setVisibility(View.GONE);
            spOutAc.setVisibility(View.VISIBLE);
            tvOutAc.setVisibility(View.VISIBLE);
        } else if(Constants.FIN_REOCRD_TYPE_CH.equals(type)) {
            spInAc.setVisibility(View.VISIBLE);
            tvInAc.setVisibility(View.VISIBLE);
            spOutAc.setVisibility(View.VISIBLE);
            tvOutAc.setVisibility(View.VISIBLE);
        }
        data.setOutId("");
        data.setInId("");
    }

    @OnClick(R.id.btn_fin_record_add_submit)
    public void submitData() {
        String numStr = etAmount.getText().toString();
        if (! StringUtils.notIsBlankAndEmpty(numStr)) {
            showMessage("请输入金额");
            return ;
        }
        Double amount = Double.parseDouble(numStr);
        //如果是支出金额为负数
        if(Constants.FIN_REOCRD_TYPE_OUT.equals(data.getReType())) {
            amount = - amount;
        }
        data.setAmount(amount);
//        通过下拉框选择
//        data.setReType(etReType.getText().toString());
        data.setDescription(etDescription.getText().toString());
//        data.setOutId(etOutId.getText().toString());
//        data.setInId(etInId.getText().toString());
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
    public void fillInOutAcSpinner( FinAccountListEntity finAccountListEntity,String type) {
        final List<FinAccountListEntity.Data> list = finAccountListEntity.getData();
        List<String> acNameList =  new LinkedList<>();
        for (FinAccountListEntity.Data entity: list ) {
            acNameList.add(entity.getAcName());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,acNameList);
        if(Constants.FIN_REOCRD_TYPE_IN.equals(type)) {
            spOutAc.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String outId = list.get(position).getId();
                    data.setOutId(outId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else if(Constants.FIN_REOCRD_TYPE_OUT.equals(type)) {
            spOutAc.setAdapter(arrayAdapter);

            spOutAc.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String outId = list.get(position).getId();
                    data.setOutId(outId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else if(Constants.FIN_REOCRD_TYPE_CH.equals(type)) {
            spInAc.setAdapter(arrayAdapter);
            spInAc.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String inId = list.get(position).getId();
                    data.setInId(inId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spOutAc.setAdapter(arrayAdapter);

            spOutAc.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String outId = list.get(position).getId();
                    data.setOutId(outId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        spInAc.setAdapter(arrayAdapter);
        spInAc.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String inId = list.get(position).getId();
                data.setInId(inId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spOutAc.setAdapter(arrayAdapter);

        spOutAc.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String outId = list.get(position).getId();
                data.setOutId(outId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void initInOutAcSpinner(String type) {

        mPresenter.requestAcList(type);
    }



    @Override
    public void setPresenter(FinRecordAddContract.Presenter paramT) {

    }
}
