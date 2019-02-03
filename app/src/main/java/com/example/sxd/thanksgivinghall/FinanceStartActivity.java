
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

import com.example.sxd.thanksgivinghall.bean.Constants;
import com.example.sxd.thanksgivinghall.finance.FinRecordAddActivity;
import com.example.sxd.thanksgivinghall.finance.FinRecordDetailActivity;
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
//            case R.id.bt_fin_start_list:
//                Intent intent2 = new Intent(FinanceStartActivity.this,FinRecordListActivity.class);
//                startActivity(intent2);
//                break;
        }

    }
    public void initViewPager() {
        final List<View> list = new LinkedList<>();
        LayoutInflater inflater = this.getLayoutInflater();
        list.add(inflater.inflate(R.layout.activity_fin_record_add,null));
        list.add(inflater.inflate(R.layout.activity_fin_record_detail,null));

//        list.add(inflater.inflate(R.layout.activity_fin_record_list,null));
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
                // TODO Auto-generated method stub
                container.removeView(list.get(position));
            }
        };
        viewPager.setAdapter(pagerAdapter);
    }
}
