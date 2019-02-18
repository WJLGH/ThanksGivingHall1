package com.example.sxd.thanksgivinghall;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sxd.thanksgivinghall.adapter.FragmentAdapter;
import com.example.sxd.thanksgivinghall.finance.AccSumActivity;
import com.example.sxd.thanksgivinghall.finance.DateSumActivity;
import com.example.sxd.thanksgivinghall.finance.ExpandFnFragment;
import com.example.sxd.thanksgivinghall.finance.TypeSumActivity;
import com.example.sxd.thanksgivinghall.notice.PublishedNoticeActivity;
import com.example.sxd.thanksgivinghall.notice.ToDoNoticeActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class FinStartFragment extends Fragment {
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    private TabLayout mTabLayout;
    Integer size = 2;

    Unbinder unbinder;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fin_start, container, false);
        mTabLayout =  view.findViewById(R.id.tabs);
        unbinder = ButterKnife.bind(this, view);
        initViewPager();
        return view;
    }

    private void initViewPager() {

        List<String> titles = new ArrayList<>();
        titles.add("明细");
        titles.add("结余");
        titles.add("收支");
        titles.add("●●●");
        for (int i = 0; i < size; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(i)));
        }
        List<Fragment> fragments = new ArrayList<>();

        fragments.add(new DateSumActivity());
        fragments.add(new AccSumActivity());
        fragments.add(new TypeSumActivity());
        fragments.add(new ExpandFnFragment());
        FragmentAdapter mFragmentAdapter =
                new FragmentAdapter(getChildFragmentManager(), fragments, titles);
        //给ViewPager设置适配器
        mViewPager.setAdapter(mFragmentAdapter);
        //将TabLayout和ViewPager关联起来。
        mTabLayout.setupWithViewPager(mViewPager);
        //给TabLayout设置适配器
        mTabLayout.setTabsFromPagerAdapter(mFragmentAdapter);
    }

}
