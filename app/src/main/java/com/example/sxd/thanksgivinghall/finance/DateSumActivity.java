package com.example.sxd.thanksgivinghall.finance;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.adapter.FinDateSumListAdapter;
import com.example.sxd.thanksgivinghall.bean.FinDateSumListEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DateSumActivity extends Fragment implements DateSumContract.View {

    @BindView(R.id.fin_date_sum_title_year)
    TextView tvYear;
    @BindView(R.id.fin_date_sum_title_month)
    Spinner spMonth;
    @BindView(R.id.fin_date_sum_title_in_amount)
    TextView tvInAmount;
    @BindView(R.id.fin_date_sum_title_out_amount)
    TextView tvOutAmount;
    @BindView(R.id.fin_date_sum_title_amount)
    TextView tvAmount ;
    @BindView(R.id.rv_fin_date_sum_list)
    RecyclerView rvDateSumList;
    FinDateSumListAdapter mAdapter;
    DateSumContract.Presenter mPresenter;
    String month;
    String year;
    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_date_sum, container, false);
        unbinder = ButterKnife.bind(this, view);
        ButterKnife.bind(this,view);
        super.onCreate(savedInstanceState);
        mPresenter = new DateSumPresenterImpl(getActivity(),this);
        initView();
        return  view;
    }
    @OnClick(R.id.fab)
    public void addRecord() {
        Intent intent = new Intent(getActivity(),FinRecordAddActivity.class);
        startActivity(intent);
    }
    private void initView() {

//        String dateStr = null;
//        Intent intent = getIntent();
//        dateStr = intent.getStringExtra("dateStr");
        initSpinner();
        year = new  SimpleDateFormat("yyyy").format(new Date());
        month = new SimpleDateFormat("MM").format(new Date());
        spMonth.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (view == null) {
                    return ;
                }

                TextView textView = (TextView) view;
                month = textView.getText().toString();
                String dateStr = year +"-"+month;
                mPresenter.request(dateStr);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                synDp();
            }
        });
        synDp() ;
        mPresenter.request(year +"-"+month);
    }

    private void synDp() {
        String[] strings = getActivity().getResources().getStringArray(R.array.month);

        for (int i = 0;i<strings.length;i++) {
            if(month.equals(strings[i])) {
                spMonth.setSelection(i);
                break;
            }
        }
    }

    private void initSpinner() {
        List<String> liststates =  Arrays.asList(getActivity().getResources().getStringArray(R.array.month));
        ArrayAdapter adap = new ArrayAdapter<String>(getActivity(), R.layout.item_spinselect, liststates);
        adap.setDropDownViewResource(R.layout.item_dialogspinselect);
        spMonth.setAdapter(adap);

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT);
    }

    @Override
    public void requestSuccess(FinDateSumListEntity value) {
        fillView(value);
    }

    private void fillView(final FinDateSumListEntity value) {
        FinDateSumListEntity.Data mainData = value.getMainData();
        tvYear.setText(mainData.getYear());
//        tvDate.setText(mainData.getMonth());
        tvInAmount.setText(String.format("%.2f",mainData.getInAmount()));
        tvOutAmount.setText(String.format("%.2f",mainData.getOutAmount()));
        tvAmount.setText(String.format("%.2f",mainData.getAmount()));
        final List<FinDateSumListEntity.Data> list = value.getData();
        mAdapter = new FinDateSumListAdapter(R.layout.fin_date_sum_item,list,mainData.getDateStr());
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String dateStr = value.getData().get(position).getDateStr();
                getDateStrDetailList(dateStr);
            }
        });
        rvDateSumList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvDateSumList.setAdapter(mAdapter);
    }

    private void getDateStrDetailList(String dateStr) {
//        showMessage(dateStr);
        Intent intent = new Intent(getActivity(),FinRecordListActivity.class);
        intent.putExtra("dateStr",dateStr);
        startActivity(intent);
    }

    @Override
    public void setPresenter(DateSumContract.Presenter paramT) {

    }
}
