
package com.example.sxd.thanksgivinghall;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.sxd.thanksgivinghall.bean.Constants;
import com.example.sxd.thanksgivinghall.finance.AccSumActivity;
import com.example.sxd.thanksgivinghall.finance.DateSumActivity;
import com.example.sxd.thanksgivinghall.finance.FinRecordAddActivity;
import com.example.sxd.thanksgivinghall.finance.FinRecordDetailActivity;
import com.example.sxd.thanksgivinghall.finance.FinRecordListActivity;
import com.example.sxd.thanksgivinghall.finance.TypeSumActivity;
import com.example.sxd.thanksgivinghall.login.SettingActivity;
import com.example.sxd.thanksgivinghall.utils.SharedPreUtils;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FinanceStartActivity extends AppCompatActivity {


    @BindView(R.id.bt_fin_start_add)
    Button btnAdd;
    @BindView(R.id.bt_fin_start_detail)
    Button btnDetail;
    @BindView(R.id.bt_fin_start_list)
    Button btnList;
    @BindView(R.id.bt_fin_start_set)
    Button btnSet;
    @BindView(R.id.btn_start_type_list)
    Button btnTypeList;
    @BindView(R.id.btn_start_type_sum)
    Button btnTypeSum;
    @BindView(R.id.btn_start_date_list)
    Button btnDateList;
    @BindView(R.id.btn_start_date_sum)
    Button btnDateSum;
    @BindView(R.id.btn_start_acc_list)
    Button btnAccList;
    @BindView(R.id.btn_start_acc_sum)
    Button btnAccSum;
    @BindView(R.id.et_start_acc)
    EditText etAcc;
    @BindView(R.id.et_start_type)
    EditText etType;
    @BindView(R.id.et_start_date)
    EditText etDate;
    @BindView(R.id.vp_fin_start)
    ViewPager viewPager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance_start);
        ButterKnife.bind(this);
        initIPAndPort();
//        initViewPager();
    }
    public void initIPAndPort() {
        SharedPreUtils.putString(this,Constants.SP_IP,"10.0.2.2");
        SharedPreUtils.putString(this,Constants.SP_PORT,"8080");
    }

    @OnClick({R.id.btn_start_acc_sum,R.id.btn_start_date_sum,R.id.btn_start_type_sum})
    public void intentSum(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_start_acc_sum:
                intent = new Intent(this,AccSumActivity.class);
                intent.putExtra("id",etAcc.getText().toString());
                break;
            case R.id.btn_start_date_sum:
                intent = new Intent(this,DateSumActivity.class);
                intent.putExtra("dateStr",etDate.getText().toString());
                break;
            case R.id.btn_start_type_sum:
                intent = new Intent(this,TypeSumActivity.class);
                intent.putExtra("reType",etType.getText().toString());
                break;
        }
        startActivity(intent);
    }
    @OnClick({R.id.btn_start_acc_list,R.id.btn_start_type_list,R.id.btn_start_date_list})
    public void intentList(View v) {
        Intent intent = new Intent(FinanceStartActivity.this,FinRecordListActivity.class);
        switch (v.getId()) {
            case R.id.btn_start_acc_list:
                intent.putExtra("acName",etAcc.getText().toString());
                break;
            case R.id.btn_start_type_list:
                intent.putExtra("busType",etType.getText().toString());
                break;
            case R.id.btn_start_date_list:
                intent.putExtra("dateStr",etDate.getText().toString());
                break;
        }
        startActivity(intent);
    }
    @OnClick({R.id.bt_fin_start_add,R.id.bt_fin_start_detail,R.id.bt_fin_start_list,R.id.bt_fin_start_set})
    public void intentAct(View v) {
        switch (v.getId()) {
            case R.id.bt_fin_start_add:
                Intent intent = new Intent(FinanceStartActivity.this,FinRecordAddActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_fin_start_detail:
                Intent intent1 = new Intent(FinanceStartActivity.this,FinRecordDetailActivity.class);
                startActivity(intent1);
                break;
            case R.id.bt_fin_start_set:
                Intent intent3 = new Intent(FinanceStartActivity.this,SettingActivity.class);
                startActivity(intent3);
                break;
            case R.id.bt_fin_start_list:
                Intent intent2 = new Intent(FinanceStartActivity.this,FinRecordListActivity.class);
                startActivity(intent2);
                break;
        }

    }
    public void initViewPager() {
        final List<View> list = new LinkedList<>();
        LayoutInflater inflater = this.getLayoutInflater();
        list.add(inflater.inflate(R.layout.activity_fin_record_add,null));
        list.add(inflater.inflate(R.layout.activity_fin_record_detail,null));
        list.add(inflater.inflate(R.layout.activity_fin_record_list,null));
        PagerAdapter pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == o;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {

                container.addView(list.get(position));
                return list.get(position);
            }
            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView(list.get(position));
            }
        };
        viewPager.setAdapter(pagerAdapter);
    }
}
