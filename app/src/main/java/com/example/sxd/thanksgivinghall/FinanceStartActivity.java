
package com.example.sxd.thanksgivinghall;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FinanceStartActivity extends AppCompatActivity {


    @BindView(R.id.bt_fin_start_add)
    Button btnAdd;
    @BindView(R.id.bt_fin_start_detail)
    Button btnDetail;
    @BindView(R.id.bt_fin_start_list)
    Button btnList;
    @BindView(R.id.vp_fin_start)
    ViewPager viewPager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance_start);
        ButterKnife.bind(this);
        initViewPager();
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
