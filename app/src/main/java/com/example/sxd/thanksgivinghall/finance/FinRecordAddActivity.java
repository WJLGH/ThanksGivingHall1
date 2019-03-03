package com.example.sxd.thanksgivinghall.finance;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.base.BaseActivity;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.Constants;
import com.example.sxd.thanksgivinghall.bean.FinAccountListEntity;
import com.example.sxd.thanksgivinghall.bean.FinGood;
import com.example.sxd.thanksgivinghall.bean.FinRecordDetailEntity;
import com.example.sxd.thanksgivinghall.login.LoginActivity;
import com.example.sxd.thanksgivinghall.notice.NoticeAddActivity;
import com.example.sxd.thanksgivinghall.utils.SharedPreUtils;
import com.example.sxd.thanksgivinghall.utils.StringUtils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

public class FinRecordAddActivity extends BaseActivity implements FinRecordAddContract.View {

    public static String showPopBusType = "采购支出";
    private PopupWindow mPopupWindow; // popwindow
    private static final String TAG = "FinRecordAddActivity";

    @BindView(R.id.gone_view)
    View mGoneView;
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
    @BindView(R.id.et_good_name)
    EditText etGoodName;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.et_quantity)
    EditText etQuantity;
    @BindView(R.id.et_unit)
    EditText etUnit;
    @BindView(R.id.et_supplier)
    EditText etSupplier;
    @BindView(R.id.et_sp_address)
    EditText etSpaAddress;
    @BindView(R.id.et_buyer)
    EditText etBuyer;
    @BindView(R.id.good_detail)
    LinearLayout goodDetail;

    FinRecordDetailEntity.Data data = new FinRecordDetailEntity.Data();
    private FinRecordAddContract.Presenter mPresenter;
    boolean first = true;
    String id;
    int defPos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_fin_record_add);
        ButterKnife.bind(this);
        setTitle("新增记录");//设置标题
        setBackArrow();//设置返回按钮和点击事件
        mPresenter = new FinRecordAddPresenterImpl(FinRecordAddActivity.this, this);
        initView();
        getDefault();
    }

    public void getDefault() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        this.id = id;
        if (StringUtils.notIsBlankAndEmpty(id)) {
            mPresenter.requestDefault(id);
        }
    }

    @Override
    public void fillDefault(FinRecordDetailEntity value) {
        if(value == null) {
            return ;
        }
        FinRecordDetailEntity.Data record = value.getData();
        FinGood good = record.getGood();
        data = record;
        //设置控件的值
        String[] deptArray = getResources().getStringArray(R.array.dept);
        for (int i = 0; i < deptArray.length; i++) {
            if (deptArray[i].equals(data.getDept())) {
                spDept.setSelection(i,true);
                break;
            }
        }
        String[] reArray = getResources().getStringArray(R.array.reType);
        for (int i = 0; i < reArray.length; i++) {
            if (reArray[i].equals(data.getReType())) {
                spReType.setSelection(i,true);
                break;
            }
        }
        Resources res = getResources();
        String type = data.getReType();
        String[] busArray = null;
        if (Constants.FIN_REOCRD_TYPE_IN.equals(type)) {
            busArray = res.getStringArray(R.array.busTypeIn);
        } else if (Constants.FIN_REOCRD_TYPE_OUT.equals(type)) {
            busArray = res.getStringArray(R.array.busTypeOut);
        } else if (Constants.FIN_REOCRD_TYPE_CH.equals(type)) {
            busArray = res.getStringArray(R.array.busTypeCh);
        }
        for (int i = 0; i < busArray.length; i++) {
            if (busArray[i].equals(data.getBusType())) {

                spBusType.setSelection(i,true);
                defPos = i;
                break;
            }
        }

        etAmount.setText(String.format("%.2f", data.getAmount()));
        etDescription.setText(data.getDescription());
        SpinnerAdapter inAcAdapter = spInAc.getAdapter();
        int cnt = inAcAdapter.getCount();
        for (int i = 0; i < cnt; i++) {
            String item = (String) inAcAdapter.getItem(i);
            if (item.equals(data.getInId())) {
                spInAc.setSelection(i,true);
                break;
            }
        }
        SpinnerAdapter outAdapter = spOutAc.getAdapter();
        cnt = outAdapter.getCount();
        for (int i = 0; i < cnt; i++) {
            String item = (String) outAdapter.getItem(i);
            if (item.equals(data.getOutId())) {
                spOutAc.setSelection(i,true);
                break;
            }
        }
        try {

            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(data.getNoteDate());
            int year = date.getYear() + 1900;
            int month = date.getMonth();
            int day = date.getDate();

            dpNoteDate.init(year, month, day, new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    data.setNoteDate(String.format("%d-%02d-%02d", year, monthOfYear + 1, dayOfMonth));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            showMessage(e.getMessage());
        }
        if (good != null) {
            goodDetail.setVisibility(View.VISIBLE);
            etGoodName.setText(good.getGoodName());
            etPrice.setText(String.format("%.2f", good.getPrice()));
            etQuantity.setText(String.format("%.2f", good.getQuantity()));
            etUnit.setText(good.getUnit());
            etSpaAddress.setText(good.getSpAddress());
            etSupplier.setText(good.getSupplier());
            etBuyer.setText(good.getBuyer());
        }
       // spBusType.setSelection(2,false);
    }

    public void initView() {
        initNoteDateDatePicker();
        initInOutAcSpinner(null);
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
//                initInOutAcSpinner(type);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spBusType.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(first) {
                    first = ! first;
                    if(defPos != -1) {
                        spBusType.setSelection(defPos);
                    }
                    return ;
                }

                //showMessage("选中的是第"+id+"个");
                TextView textView = (TextView) view;
                if (view == null) {
                    return;
                }
                String busType = textView.getText().toString();
                data.setBusType(busType);
                if (showPopBusType.equals(busType)) {
                    goodDetail.setVisibility(View.VISIBLE);
                } else {
                    goodDetail.setVisibility(View.GONE);
                }
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

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 获取状态栏高速的方法
     *
     * @return
     */
    private int getStatusBarHeight() {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sbar;
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param context
     * @param bgAlpha (透明度 取值返回0-1, 0全透明,1不透明)
     */
    public void backgroundAlpha(Activity context, float bgAlpha) {

        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow()
                .addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

    private void showPopGood() {
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;// px
        LayoutInflater inflater = getLayoutInflater();
        View mpopview = inflater.inflate(R.layout.popup_good_detail, null);// 加载动画布局
        mPopupWindow = new PopupWindow(mpopview, width, 800);// 设置布局在屏幕中显示的位置，并且获取焦点
        // 设置PopupWindow的显示样式
        ColorDrawable dw = new ColorDrawable(0x00FF3030);
        mPopupWindow.setBackgroundDrawable(dw);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);// 设置允许在外点击消失

        final EditText etGoodName = mpopview.findViewById(R.id.et_good_name);
        final EditText etPrice = mpopview.findViewById(R.id.et_price);
        final EditText etAmount = mpopview.findViewById(R.id.et_quantity);
        final EditText etUnit = mpopview.findViewById(R.id.et_unit);
        final EditText etSupplier = mpopview.findViewById(R.id.et_supplier);
        final EditText etSpaAddress = mpopview.findViewById(R.id.et_sp_address);
        final EditText etBuyer = mpopview.findViewById(R.id.et_buyer);
        // 设置当mPopupWindow取消时，界面恢复原来的颜色 不是可透明的
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                String name = etGoodName.getText().toString();
                String priceStr = etPrice.getText().toString();
                String amountStr = etQuantity.getText().toString();
                String unit = etUnit.getText().toString();
                String address = etSpaAddress.getText().toString();
                String supplier = etSupplier.getText().toString();
                String buyer = etBuyer.getText().toString();
                if (StringUtils.notIsBlankAndEmpty(name) &&
                        StringUtils.notIsBlankAndEmpty(priceStr) &&
                        StringUtils.notIsBlankAndEmpty(amountStr) &&
                        StringUtils.notIsBlankAndEmpty(unit) &&
                        StringUtils.notIsBlankAndEmpty(address) &&
                        StringUtils.notIsBlankAndEmpty(supplier) &&
                        StringUtils.notIsBlankAndEmpty(buyer)) {
                    FinGood finGood = new FinGood(name, priceStr, amountStr, unit, address, supplier, buyer);
                    data.setGood(finGood);
                    Double total = finGood.getPrice() * finGood.getQuantity();
                    String num = String.format("%.2f", total);
                    FinRecordAddActivity.this.etAmount.setText(num);
                } else {
                    showMessage("未完成商品信息的填写，结果无法被保存！！！");
                }
//                backgroundAlpha(getParent(), 1f);// 不透明
            }
        });
//        mPopupWindow.showAtLocation(mpopview, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        mPopupWindow.showAsDropDown(mGoneView);// 弹出的mPopupWindow左上角正对mGoneView的左下角
//         偏移量默认为0,0

    }

    @Override
    protected void setRightTitleOnClick(View v) {

    }

    @SuppressLint("NewApi")
    private void initNoteDateDatePicker() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayofMonth = calendar.get(Calendar.DAY_OF_MONTH);
        //默认是今天
        data.setNoteDate(String.format("%d-%02d-%02d", year, month + 1, dayofMonth));

//        dpNoteDate.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
//            @Override
//            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                Log.i(TAG, "选择");
//            }
//        });

        dpNoteDate.init(year, month, dayofMonth, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                data.setNoteDate(String.format("%d-%02d-%02d", year, monthOfYear + 1, dayOfMonth));
                Log.i(TAG, "选择");
            }
        });
    }

    /**
     * 根据reType初始化BusType下拉框
     *
     * @author wjl
     * @date 2019-2-13
     */
    public void initBusSpinner(String type) {
        String[] showList = null;
        Resources res = getResources();
        if (Constants.FIN_REOCRD_TYPE_IN.equals(type)) {
            showList = res.getStringArray(R.array.busTypeIn);
        } else if (Constants.FIN_REOCRD_TYPE_OUT.equals(type)) {
            showList = res.getStringArray(R.array.busTypeOut);
        } else if (Constants.FIN_REOCRD_TYPE_CH.equals(type)) {
            showList = res.getStringArray(R.array.busTypeCh);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, showList);
        spBusType.setAdapter(arrayAdapter);
    }

    //@OnClick(R.id.btn_fin_record_add_clear)

    /**
     * 清空账户下拉列表，从数据库中重新获取账户列表
     *
     * @author wjl
     * @date 2019/2/13 8:37
     */
    public void clearView(String type) {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        if (Constants.FIN_REOCRD_TYPE_IN.equals(type)) {
            spInAc.setVisibility(View.VISIBLE);
            tvInAc.setVisibility(View.VISIBLE);
            spOutAc.setVisibility(View.GONE);
            tvOutAc.setVisibility(View.GONE);
        } else if (Constants.FIN_REOCRD_TYPE_OUT.equals(type)) {
            spInAc.setVisibility(View.GONE);
            tvInAc.setVisibility(View.GONE);
            spOutAc.setVisibility(View.VISIBLE);
            tvOutAc.setVisibility(View.VISIBLE);
        } else if (Constants.FIN_REOCRD_TYPE_CH.equals(type)) {
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
        if (!StringUtils.notIsBlankAndEmpty(numStr)) {
            showMessage("请输入金额");
            return;
        }
       if(showPopBusType.equals(data.getBusType())) {
           String name = etGoodName.getText().toString();
           String priceStr = etPrice.getText().toString();
           String amountStr = etQuantity.getText().toString();
           String unit = etUnit.getText().toString();
           String address = etSpaAddress.getText().toString();
           String supplier = etSupplier.getText().toString();
           String buyer = etBuyer.getText().toString();
           if (StringUtils.notIsBlankAndEmpty(name) &&
                   StringUtils.notIsBlankAndEmpty(priceStr) &&
                   StringUtils.notIsBlankAndEmpty(amountStr) &&
                   StringUtils.notIsBlankAndEmpty(unit) &&
                   StringUtils.notIsBlankAndEmpty(address) &&
                   StringUtils.notIsBlankAndEmpty(supplier) &&
                   StringUtils.notIsBlankAndEmpty(buyer)) {
               FinGood finGood = new FinGood(name, priceStr, amountStr, unit, address, supplier, buyer);
               data.setGood(finGood);
               Double total = finGood.getPrice() * finGood.getQuantity();
               String num = String.format("%.2f", total);
               FinRecordAddActivity.this.etAmount.setText(num);
           } else {
               showMessage("未完成商品信息的填写，结果无法被保存！！！");
               return;
           }
       }
        Double amount = Double.parseDouble(numStr);
        //如果是支出金额为负数
        if (Constants.FIN_REOCRD_TYPE_OUT.equals(data.getReType())) {
            amount = -amount;
        }
        data.setId(id);
        data.setAmount(amount);
        data.setDescription(etDescription.getText().toString());
        mPresenter.request(data);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void requestSuccess(Base value) {
        showMessage("明细添加成功");
        finish();
    }

    @Override
    public void fillInOutAcSpinner(FinAccountListEntity finAccountListEntity, String type) {
        final List<FinAccountListEntity.Data> list = finAccountListEntity.getData();
        List<String> acNameList = new LinkedList<>();
        for (FinAccountListEntity.Data entity : list) {
            acNameList.add(entity.getAcName());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, acNameList);
        if (Constants.FIN_REOCRD_TYPE_IN.equals(type)) {
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
        } else if (Constants.FIN_REOCRD_TYPE_OUT.equals(type)) {
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
        } else if (Constants.FIN_REOCRD_TYPE_CH.equals(type)) {
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
